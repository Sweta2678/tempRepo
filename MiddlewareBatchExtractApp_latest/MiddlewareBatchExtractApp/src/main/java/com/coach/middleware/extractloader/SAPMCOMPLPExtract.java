package com.coach.middleware.extractloader;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.coach.middleware.util.CoachExtractproperties;

public class SAPMCOMPLPExtract implements IExtractLoader{
	private static final Logger logger = Logger
	.getLogger(SAPMCOMPLPExtract.class);
	
	/*@Resource(name = "RPFTBABCProps")
	private Properties rpFtpAbcProps;
	*/
	//@Value("#{rpFtpAbcProps['com.coach.middleware.rpftpabc.targetDirectory']}")
	private String targetDirectory = null;
	
	//@Value("#{rpFtpAbcProps['com.coach.middleware.rpftpabc.targetFile']}")
	private String targetFile = null;
	
	public JobParameters param = null;
	public JobParametersBuilder builder = new JobParametersBuilder();
	
	@Override
	public boolean launchExtract(String extractname, String runmode,
			String propertyPath) throws Exception {

		String jobName = null;
		logger.info("********* SAPMCOMPLPExtract started********" + new Date());
		
		//propertyPath = "src/main/resources/com/coach/middleware/batch/config/properties/RPFTPABC.properties";
		
		String[] springConfig = { "com/coach/middleware/batch/config/spring-batch-context.xml" };
		ApplicationContext context = new ClassPathXmlApplicationContext(
				springConfig);
		CoachExtractproperties coachExtractproperties = new CoachExtractproperties(propertyPath); 
		
		
		if (runmode.equalsIgnoreCase("full")) {
			logger.info("Extract started");
			jobName = "SapMcompLpExtractJob";
		} else if (runmode.equalsIgnoreCase("delta")) {
			logger.info("Extract started");
			jobName = "SapMcompLpExtractJob";
		}else  {
			logger.info("Extract started");
			jobName = "SapMcompLpExtractJob";
		}
		
		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		if (jobName != null) {
			Job job = (Job) context.getBean(jobName);
			try {
				//PRODMASTExtractDao.deletePRODMASTTable();
			/*	JobExecution execution = jobLauncher.run(job,
						new JobParameters());*/
				targetDirectory = 	CoachExtractproperties.properties.getProperty("com.coach.middleware.sapmcomplp.targetDirectory");
				targetFile = 	CoachExtractproperties.properties.getProperty("com.coach.middleware.sapmcomplp.targetFile");
				if(targetFile == "" || targetDirectory == "" || targetFile == null || targetDirectory == null){
					throw new Exception("File or directory value in property file cannot be empty\n");
				}
			builder.addString("targetDirectory", targetDirectory);
			builder.addString("targetFile", targetFile);
			builder.addString("targetDestDirectory", CoachExtractproperties.properties.getProperty("com.coach.middleware.sapmcomplp.targetDestDirectory"));
			//builder.addString("targetDestFile", CoachExtractproperties.properties.getProperty("com.coach.middleware.rpftpabc.targetDestFile"));
				/*builder.addString("targetDirectory", "csv/");
				builder.addString("targetFile", "RPFTPABC");*/	
			builder.addLong("SAPMCOMPLP Start time", System.currentTimeMillis());
			param =  builder.toJobParameters();
				
			JobExecution execution = jobLauncher.run(job, param);
				if (execution.getStatus() == BatchStatus.COMPLETED) {
					logger.info("SAPMCOMPLP job completed successfully : ");
					System.out.println("SAPMCOMPLP job completed successfully ");
					// Here you can perform some other business logic like
					// cleanup
				} else  {
					logger.info("SAPMCOMPLP job failed with following exceptions :");
					List<Throwable> exceptionList = execution
							.getAllFailureExceptions();
					for (Throwable th : exceptionList) {
						logger.info("exception :"
								+ th.getLocalizedMessage());
					}
					System.out.println("SAPMCOMPLP job failed with exceptions");
				}
				//writeRDYFile("PRODMAST","");
			} catch (Exception e) {
				logger.error("SAPMCOMPLP job failed with following exceptions :"+e.getLocalizedMessage());
				System.out.println("SAPMCOMPLP job failed with exceptions");
				e.printStackTrace();
				return false;
			}
			logger.info("******End time *********"+ new Date());
			return true;
		} else {
			return false;
		}
	
	}

	/*public Properties getRpFtpAbcProps() {
		return rpFtpAbcProps;
	}

	public void setRpFtpAbcProps(Properties rpFtpAbcProps) {
		this.rpFtpAbcProps = rpFtpAbcProps;
	}*/

}
