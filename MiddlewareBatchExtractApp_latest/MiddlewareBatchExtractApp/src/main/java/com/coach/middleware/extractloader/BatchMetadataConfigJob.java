package com.coach.middleware.extractloader;

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

public class BatchMetadataConfigJob implements IExtractLoader{
	private static final Logger logger = Logger
	.getLogger(BatchMetadataConfigJob.class);
	@Override
	public boolean launchExtract(String extractname, String runmode,
			String propertyPath) throws Exception {

		String jobName = null;
		logger.info("********* BATCH METADATA CONFIG Job started********" + new Date());
		
			String[] springConfig = { "com/coach/middleware/batch/config/spring-batch-metadata-context.xml" };
			
		try{
		ApplicationContext context = new ClassPathXmlApplicationContext(
				springConfig);
		}catch(Exception e){
			logger.error("batch metadata configuration status",e.getCause());
			if(e.getCause().toString().contains("table or view does not exist")){
				logger.info("Create metadata tables");
				String[] springConfigInit = { "com/coach/middleware/batch/config/spring-batch-metadata-init.xml" };
				ApplicationContext context = new ClassPathXmlApplicationContext(
						springConfigInit);
				logger.info("Batch metadata tables created");
			}
		}
			
			logger.info("******End time *********"+ new Date());
			return true;
		 
	
	}

}
