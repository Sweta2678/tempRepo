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

import com.coach.middleware.RunBatch;
import com.coach.middleware.util.CoachExtractproperties;
import com.sun.org.apache.bcel.internal.generic.RETURN;


public class LPSKUSAPExtract implements IExtractLoader{
	private static final Logger logger = Logger
			.getLogger(LPSKUSAPExtract.class);
	public static String runmode = null;
	public JobParameters param = null;
	public JobParametersBuilder builder = new JobParametersBuilder();
	private String targetDirectory = null;
	private String targetFile = null;
	private String rowCount = null;
	@Override
	public boolean launchExtract(String extractname,String runmode, String propertyPath) {

		  
		
		logger.info("********* LPSKUSAP Extract started********" + new Date());  
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
		if("delta".equalsIgnoreCase(mode)){
			job = (Job) context.getBean("LpSkuSapExtract");
		logger.info("LPSKUSAP started in Delta mode");
		System.out.println("LPSKUSAP started in Delta mode");
		}else if("full".equalsIgnoreCase(mode)) {
			job =  (Job) context.getBean("LpSkuSapExtract");
		logger.info("LPSKUSAP started in Full mode");
		System.out.println("LPSKUSAP started in Full mode");
		}
		else
			job =  (Job) context.getBean("LpSkuSapExtract");
	 
		try {
			System.out.println("Job running in mode "+ mode);
			
			targetDirectory = 	CoachExtractproperties.properties.getProperty("com.coach.middleware.lpskusap.targetDirectory");
			targetFile = 	CoachExtractproperties.properties.getProperty("com.coach.middleware.lpskusap.targetFile");
			rowCount = CoachExtractproperties.properties.getProperty("com.coach.middleware.lpskusap.rowCount");
			String swDepartmentQuery = CoachExtractproperties.properties.getProperty("com.coach.middleware.lpskusap.swDepartmentQuery");
			if(targetFile == "" || targetDirectory == "" || targetFile == null || targetDirectory == null){
				throw new Exception("File or directory value in property file cannot be empty\n");
			}
			org.springframework.batch.item.file.FlatFileItemWriter itemwriter = (org.springframework.batch.item.file.FlatFileItemWriter)context.getBean("LpSkuSapExtractFileItemWriter");
			org.springframework.batch.item.file.FlatFileItemWriter genTotoRDY = (org.springframework.batch.item.file.FlatFileItemWriter)context.getBean("genLpSkuSAPRDY");
			org.springframework.batch.item.database.JdbcCursorItemReader itemReader = (org.springframework.batch.item.database.JdbcCursorItemReader)context.getBean("LpSkuSapExtractDBItemReader");
			String query = itemReader.getSql();
			logger.info("Query from XML : "+query);
			query = query + rowCount+ " " + swDepartmentQuery;
			itemReader.setSql(query);
			logger.info("Query after setting row count  : "+query);
			FileSystemResource rsRdy = new FileSystemResource(new File(targetDirectory+"/"+targetFile+".rdy"));
			FileSystemResource rsWriter = new FileSystemResource(new File(targetDirectory+"/"+targetFile));
			itemwriter.setResource(rsWriter);
			genTotoRDY.setResource(rsRdy);
			
			logger.info("Property path : "+propertyPath);
			builder.addLong("LPSKUSAP Start time", System.currentTimeMillis());
			param =  builder.toJobParameters();	
		JobExecution execution = jobLauncher.run(job, param);
			if(execution.getStatus() == BatchStatus.COMPLETED){
	            System.out.println("LPSKUSAP Job completed successfully");
	            logger.info("LPSKUSAP Job completed successfully");
	            //Here you can perform some other business logic like cleanup
	        }else {
	            System.out.println("Job failed with following exceptions ");
	            logger.info("LPSKUSAP Job Failed");
	            List<Throwable> exceptionList = execution.getAllFailureExceptions();
	            for(Throwable th : exceptionList){
	                System.err.println("exception :" +th.getLocalizedMessage());
	            }
	        }
	 
		} catch (Exception e) {
			System.out.println("Job failed with following exceptions ");
            logger.error("LPSKUSAP Job Failed "+e.getLocalizedMessage() );
			e.printStackTrace();
			return false;
		}
	 
		logger.info("******LPSKUSAP End time *********"+ new Date());
		return true;
		
	}
	

}
