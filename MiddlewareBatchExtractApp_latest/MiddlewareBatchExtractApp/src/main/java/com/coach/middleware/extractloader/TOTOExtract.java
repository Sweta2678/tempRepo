package com.coach.middleware.extractloader;

import java.io.File;
import java.util.Date;
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
import org.springframework.core.io.Resource;

import com.coach.middleware.RunBatch;
import com.coach.middleware.util.CoachExtractproperties;
import com.sun.org.apache.bcel.internal.generic.RETURN;


public class TOTOExtract implements IExtractLoader{
	private static final Logger logger = Logger
	.getLogger(TOTOExtract.class);
	public static String runmode = null;
	public JobParameters param = null;
	public JobParametersBuilder builder = new JobParametersBuilder();
	private String targetDirectory = null;
	private String targetFile = null;
	private String swDepartments = null;
	
	@Override
	public boolean launchExtract(String extractname,String runmode, String propertyPath) {

		  
		
		  String mode = runmode;
		  
		
		  String[] springConfig  = 
			{	
//				"spring/batch/config/database.xml", 
//				"com/coach/middleware/batch/config/context-datasource.xml",
				"com/coach/middleware/batch/config/spring-batch-context.xml" 
			};
	 
		ApplicationContext context = 
			new ClassPathXmlApplicationContext(springConfig);
		CoachExtractproperties coachExtractproperties = new CoachExtractproperties(propertyPath);
			
		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		Job job = null ;
		if("delta".equalsIgnoreCase(mode))
			job = (Job) context.getBean("TotoExtractJobDeltaMode");
		else if("full".equalsIgnoreCase(mode)) 
			job =  (Job) context.getBean("TotoExtractJobFullMode");
		logger.info("********* TOTO Extract started********" + new Date());
		try {
			System.out.println("Job running in mode "+ mode);
			targetDirectory = 	CoachExtractproperties.properties.getProperty("com.coach.middleware.toto.targetDirectory");
			targetFile = 	CoachExtractproperties.properties.getProperty("com.coach.middleware.toto.targetFile");
			swDepartments = CoachExtractproperties.properties.getProperty("com.coach.middleware.prodmast.swDepartments");
			if(targetFile == "" || targetDirectory == "" || targetFile == null || targetDirectory == null){
				throw new Exception("File or directory value in property file cannot be empty\n");
			}
			org.springframework.batch.item.file.FlatFileItemWriter itemwriter = (org.springframework.batch.item.file.FlatFileItemWriter)context.getBean("TotoExtractFileItemWriter");
			org.springframework.batch.item.file.FlatFileItemWriter genTotoRDY = (org.springframework.batch.item.file.FlatFileItemWriter)context.getBean("genTotoRDY");
			com.coach.middleware.batch.itemProcessor.TotoExtractItemProcessor totoExtractItemProcessor = (com.coach.middleware.batch.itemProcessor.TotoExtractItemProcessor)context.getBean("TotoExtractItemProcessor");
			FileSystemResource rsRdy = new FileSystemResource(new File(targetDirectory+"/"+targetFile+".rdy"));
			FileSystemResource rsWriter = new FileSystemResource(new File(targetDirectory+"/"+targetFile));
			itemwriter.setResource(rsWriter);
			genTotoRDY.setResource(rsRdy);
			
			String Condition = "";
			
			org.springframework.batch.item.database.JdbcCursorItemReader itemreader = (org.springframework.batch.item.database.JdbcCursorItemReader)context.getBean("TotoExtractDBItemReader");
			logger.info("Item Reade Query :: "+itemreader.getSql().toString());
			String query = itemreader.getSql().toString();
			query = query.replaceAll("#Condition#", Condition);
			logger.info("Item Reade Query :: "+query);
			itemreader.setSql(query);
			logger.info("Item Reade Query :: "+itemreader.getSql().toString());
			
			totoExtractItemProcessor.setSwDepartments(swDepartments);
			
			builder.addString("targetDirectory", targetDirectory);
			builder.addString("targetFile", targetFile);
			builder.addLong("TOTO Start time", System.currentTimeMillis());
			param =  builder.toJobParameters();
				
			JobExecution execution = jobLauncher.run(job, param);
			if(execution.getStatus() == BatchStatus.COMPLETED){
	            System.out.println("TOTO Job completed successfully");
	            logger.info("TOTO Job completed successfully");
	            //Here you can perform some other business logic like cleanup
	        }else {
	            System.out.println("TOTO Job failed with following exceptions ");
	            logger.info("TOTO failed with the following exception");
	            List<Throwable> exceptionList = execution.getAllFailureExceptions();
	            for(Throwable th : exceptionList){
	                System.err.println("exception :" +th.getLocalizedMessage());
	            }
	        }
			logger.info("******End time *********"+ new Date());
		} catch (Exception e) {
			logger.error("TOTO failed with the following exception :"+e.getLocalizedMessage());
			System.out.println("TOTO failed with the following exception :"+e.getLocalizedMessage());
			e.printStackTrace();
			return false;
		}
	 
		
		return true;
		
	}
	

}
