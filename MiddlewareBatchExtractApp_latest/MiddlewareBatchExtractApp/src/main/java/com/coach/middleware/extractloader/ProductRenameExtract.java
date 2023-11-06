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

import com.coach.middleware.util.CoachExtractproperties;


public class ProductRenameExtract implements IExtractLoader {
	private static final Logger logger = Logger
			.getLogger(ProductRenameExtract.class);
	public static String runmode = null;
	public JobParameters param = null;
	public JobParametersBuilder builder = new JobParametersBuilder();
	private String targetDirectory = null;
	private String targetFile = null;
	private String rowCount = null;
	@Override
	public boolean launchExtract(String extractname,String runmode, String propertyPath) {

		  
		
		logger.info("********* ProductRename Extract started********" + new Date());  
		  String mode = runmode;
		  
		
		  String[] springConfig  = 
			{	
				"com/coach/middleware/batch/config/spring-batch-context.xml" 
			};
	 
		ApplicationContext context = 
			new ClassPathXmlApplicationContext(springConfig);
		
		CoachExtractproperties coachExtractproperties = new CoachExtractproperties(propertyPath);	
		
		
	    JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		Job job = null ;
		if("delta".equalsIgnoreCase(mode)){
			job = (Job) context.getBean("ProductRename");
		logger.info("ProductRename started in Delta mode");
		System.out.println("ProductRename started in Delta mode");
		}else if("full".equalsIgnoreCase(mode)) {
			job =  (Job) context.getBean("ProductRename");
		logger.info("ProductRename started in Full mode");
		System.out.println("ProductRename started in Full mode");
		}
		else
			job =  (Job) context.getBean("ProductRename");
	 
		try {
			System.out.println("Job running in mode "+ mode);
			
			targetDirectory = 	CoachExtractproperties.properties.getProperty("com.coach.middleware.productrename.targetDirectory");
			targetFile = 	CoachExtractproperties.properties.getProperty("com.coach.middleware.productrename.targetFile");
			String targetRDYFile = CoachExtractproperties.properties.getProperty("com.coach.middleware.productrename.targetRDYFile");
			if(targetFile == "" || targetDirectory == "" || targetFile == null || targetDirectory == null){
				throw new Exception("File or directory value in property file cannot be empty\n");
			}
			org.springframework.batch.item.file.FlatFileItemWriter itemwriter = (org.springframework.batch.item.file.FlatFileItemWriter)context.getBean("productRenameFileItemWriter");
			org.springframework.batch.item.file.FlatFileItemWriter genRDY = (org.springframework.batch.item.file.FlatFileItemWriter)context.getBean("genProdRenameRDY");
			FileSystemResource rsRdy = new FileSystemResource(new File(targetDirectory+"/"+targetRDYFile+".rdy"));
			FileSystemResource rsWriter = new FileSystemResource(new File(targetDirectory+"/"+targetFile));
			//FileSystemResource rsWriter = new FileSystemResource(new File("C:\\Users\\ksarvepalli\\ProductRename.csv"));
			itemwriter.setResource(rsWriter);
			genRDY.setResource(rsRdy);
			logger.info("Property path : "+propertyPath);
			builder.addLong("ProductRename Start time", System.currentTimeMillis());
			param =  builder.toJobParameters();	
		JobExecution execution = jobLauncher.run(job, param);
		
		System.out.println("execution" + execution);
			if(execution.getStatus() == BatchStatus.COMPLETED){
	            System.out.println("ProductRename Job completed successfully");
	            logger.info("ProductRename Job completed successfully");
	            //Here you can perform some other business logic like cleanup
	        }else {
	            System.out.println("Job failed with following exceptions ");
	            logger.info("ProductRename Job Failed");
	            List<Throwable> exceptionList = execution.getAllFailureExceptions();
	            for(Throwable th : exceptionList){
	                System.err.println("exception :" +th.getLocalizedMessage());
	            }
	        }
	 
		} catch (Exception e) {
			System.out.println("Job failed with following exceptions ");
            logger.error("ProductRename Job Failed "+e.getLocalizedMessage() );
			e.printStackTrace();
			return false;
		}
	 
		logger.info("******ProductRename End time *********"+ new Date());
		return true;
		
	}
	

}
