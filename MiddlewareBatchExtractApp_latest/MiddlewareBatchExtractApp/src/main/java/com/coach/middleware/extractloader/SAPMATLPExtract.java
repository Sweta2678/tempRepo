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

public class SAPMATLPExtract implements IExtractLoader{
	private static final Logger logger = Logger
	.getLogger(SAPMATLPExtract.class);
	
	/*@Resource(name = "RPFTBABCProps")
	private Properties rpFtpAbcProps;
	*/
	//@Value("#{rpFtpAbcProps['com.coach.middleware.rpftpabc.targetDirectory']}")
	private String targetDirectory = null;
	
	//@Value("#{rpFtpAbcProps['com.coach.middleware.rpftpabc.targetFile']}")
	private String targetFile = null;
	
	public JobParameters param = null;
	public JobExecution execution=null;
	public JobParametersBuilder builder = new JobParametersBuilder();
	public static boolean successFlag = true;
	@Override
	public boolean launchExtract(String extractname, String runmode,
			String propertyPath) throws Exception {

		String jobName = null;
		logger.info("********* SAPMATLPExtract started********" + new Date());
		
		//propertyPath = "src/main/resources/com/coach/middleware/batch/config/properties/RPFTPABC.properties";
		
		String[] springConfig = { "com/coach/middleware/batch/config/spring-batch-context.xml" };
		ApplicationContext context = new ClassPathXmlApplicationContext(
				springConfig);
		CoachExtractproperties coachExtractproperties = new CoachExtractproperties(propertyPath); 
		
		
		if (runmode.equalsIgnoreCase("full")) {
			logger.info("Extract started");
			jobName = "SapMatLpExtractJob";
		} else if (runmode.equalsIgnoreCase("delta")) {
			logger.info("Extract started");
			jobName = "SapMatLpExtractJob";
		}else  {
			logger.info("Extract started");
			jobName = "SapMatLpExtractJob";
		}
		
		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		if (jobName != null) {
			Job job = (Job) context.getBean(jobName);
			try {
				//PRODMASTExtractDao.deletePRODMASTTable();
			/*	JobExecution execution = jobLauncher.run(job,
						new JobParameters());*/
				targetDirectory = 	CoachExtractproperties.properties.getProperty("com.coach.middleware.sapmatlp.targetDirectory");
				targetFile = 	CoachExtractproperties.properties.getProperty("com.coach.middleware.sapmatlp.targetFile");
				if(targetFile == "" || targetDirectory == "" || targetFile == null || targetDirectory == null){
					throw new Exception("File or directory value in property file cannot be empty\n");
				}
			builder.addString("targetDirectory", targetDirectory);
			builder.addString("targetFile", targetFile);
			builder.addString("targetDestDirectory", CoachExtractproperties.properties.getProperty("com.coach.middleware.sapmatlp.targetDestDirectory"));
			//builder.addString("targetDestFile", CoachExtractproperties.properties.getProperty("com.coach.middleware.rpftpabc.targetDestFile"));
				/*builder.addString("targetDirectory", "csv/");
				builder.addString("targetFile", "RPFTPABC");*/	
			builder.addLong("SAPMATLP Start time", System.currentTimeMillis());
			param =  builder.toJobParameters();
			 execution = jobLauncher.run(job, param);
			 System.out.println("msg : "+successFlag);
				if (execution.getStatus() == BatchStatus.COMPLETED && successFlag) {
					logger.info("SAPMATLP job completed successfully ");
					System.out.println("SAPMATLP job completed successfully");
					// Here you can perform some other business logic like
					// cleanup
				} else  {
					logger.info("SAPMATLP job failed with following exceptions :");
					List<Throwable> exceptionList = execution
							.getAllFailureExceptions();
					for (Throwable th : exceptionList) {
						logger.info("exception :"
								+ th.getLocalizedMessage());
					}
					System.out.println("SAPMATLP job failed with exceptions ");
				}
				//writeRDYFile("PRODMAST","");
			} catch (Exception e) {
				logger.error("SAPMATLP job failed with following exceptions :"+e.getLocalizedMessage());
				System.out.println("SAPMATLP job failed with exceptions ");
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
