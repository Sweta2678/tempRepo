package com.coach.middleware.extractloader;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.coach.middleware.util.CoachExtractproperties;
import com.coach.middleware.util.PMDBUtil;
import com.coach.middleware.batch.itemWriter.LPSKUExtractItemWriter;
import com.coach.middleware.extractloader.Exceptions.ExceptionCodeMapper;
import com.coach.middleware.extractloader.Exceptions.CoachExtractException;
import com.sun.jndi.cosnaming.ExceptionMapper;

public class LpskuExtract implements IExtractLoader{
	private static final Logger logger = Logger
	.getLogger(LpskuExtract.class);
	
	
	public static String runmode = null;
	public static boolean isparallelprocessong = false;
	public JobParameters param = null;
	public JobParametersBuilder builder = new JobParametersBuilder();
	
	@Override
	public boolean launchExtract(String extractname,String runmde, String propertyPath) throws Exception {
		CoachExtractproperties coachExtractproperties = new CoachExtractproperties(propertyPath);
		
		  builder.addString("methods", CoachExtractproperties.properties.getProperty("methods"));
		  builder.addString("delimeters", CoachExtractproperties.properties.getProperty("delimeters"));
		  builder.addString("stagQuery", CoachExtractproperties.properties.getProperty("stagQuery"));
		  String filePath = CoachExtractproperties.properties.getProperty("targetDir")+"/"+CoachExtractproperties.properties.getProperty("targetFile");
		  builder.addString("filePath",filePath.toString());
		  logger.info("LPSKU extract Started"); 
		runmode = runmde;
		String fileName = CoachExtractproperties.properties.getProperty("targetFile");
		String fileDir = CoachExtractproperties.properties.getProperty("targetDir");
		
		String Condition = "";
		 if(runmde.equals("full")){//
			 runmode = "full";
			 Condition =  CoachExtractproperties.properties.getProperty("condition");
			 logger.info("LPSKU extract Started in FULL mode");
		 }else if(runmde.equals("delta")){//
			 runmode = "delta";
			 logger.info("LPSKU extract Started in DELTA mode");
			 Condition =  CoachExtractproperties.properties.getProperty("condition") + " and pm.Slot in (Select slot from delta_extract)";
		 }else{
			 logger.error("LPSKU extract mode : No mode specified");
			 throw new Exception("Extract Execution Should NOt Be Matched(full/delta)");
		 }
		 //builder.addString("condition", Condition);
		 builder.addLong("LPSKU Start time",System.currentTimeMillis()).toJobParameters();
		 param =  builder.toJobParameters();
		 String[] springConfig  = {	
				"com/coach/middleware/batch/config/spring-batch-context.xml"//make it as relative path 
		 };
	 
		
		ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);
		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		
		org.springframework.batch.item.database.JdbcCursorItemReader itemreader = (org.springframework.batch.item.database.JdbcCursorItemReader)context.getBean("LPSKUExtractDBItemReader");
		logger.info("Item Reade Query :: "+itemreader.getSql().toString());
		String query = itemreader.getSql().toString();
		query = query.replaceAll("#Condition#", Condition);
		logger.info("Item Reade Query :: "+query);
		itemreader.setSql(query);
		logger.info("Item Reade Query :: "+itemreader.getSql().toString());
		
		
		Job job = (Job) context.getBean("LPSKUExtractJob");
		logger.info("job :: "+job);
		logger.info("param :: "+param);
		try {
	 
			JobExecution execution = jobLauncher.run(job, param);
			if(execution.getStatus() == BatchStatus.COMPLETED){
				logger.info("LPSKU job completed successfully");
				System.out.println("LPSKU job completed successfully");
				PMDBUtil.writeRDYFile(fileName,"",fileDir);
	            //Here you can perform some other business logic like cleanup
	        }else{
	        	logger.info("LPSKU job failed with following exceptions ");
	        	System.out.println("LPSKU job Failed");
	            List<Throwable> exceptionList = execution.getAllFailureExceptions();
	            for(Throwable th : exceptionList){
	                System.err.println("exception in LPSKUExtractJob :" +th.getMessage());
	            }
	        }
			
		} catch (Exception e) {
			logger.error("LPSKU job failed with following exceptions "+e.getLocalizedMessage());
			System.out.println("LPSKU job failed with following exceptions "+e.getLocalizedMessage());
			e.printStackTrace();
		}
	 
		logger.info("Done");
	 	return true;
	}
	public static void writeRDYFile(String fileName,String data) {
		logger.info("Write rdy file started");
		PrintWriter out = null;
		try {
		if (fileName == null) {
			return;
		}
		if (fileName != null && fileName.trim().length() > 0) {
			int i = fileName.indexOf('.');

			if (i == -1) {
				fileName = fileName.trim() + ".rdy";
			} else {
				fileName = fileName.substring(0, i).trim() + ".rdy";
			}
		}
			out = new PrintWriter(new FileWriter("csv/"+fileName));
			out.print(data);
		} catch (IOException e) {
			logger.error("Exception in creating RDY file "+e.getLocalizedMessage());
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

}
