package com.coach.middleware.extractloader;

import java.sql.Timestamp;
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

import com.coach.middleware.batch.dao.MATMSTRXExtractDao;
import com.coach.middleware.util.CoachExtractproperties;
import com.coach.middleware.util.PMDBUtil;

public class MATMSTRXExtract implements IExtractLoader {
	private static final Logger logger = Logger
			.getLogger(MATMSTRXExtract.class);
	public JobParametersBuilder builder = new JobParametersBuilder();
	public JobParameters param = null;

	@Override
	public boolean launchExtract(String extractname, String runmode,
			String propertyPath) throws Exception {
		Timestamp startTime = PMDBUtil.getFormatedDate();
		Timestamp endTime =null;
		CoachExtractproperties coachExtractproperties = new CoachExtractproperties(
				propertyPath);
		String[] springConfig = { "com/coach/middleware/batch/config/spring-batch-context.xml" };
		ApplicationContext context = new ClassPathXmlApplicationContext(
				springConfig);
		MATMSTRXExtractDao dao = (com.coach.middleware.batch.dao.MATMSTRXExtractDao) context.getBean("MATMSTRXExtractDao");
		String jobName = null;
		String fileName = CoachExtractproperties.properties.getProperty("targetFile");
		String fileDir = CoachExtractproperties.properties.getProperty("targetDir");
		if(fileName == "" || fileDir == "" || fileName == null || fileDir == null){
			throw new Exception("File or directory value in property file cannot be empty\n");
		}
		builder.addString("methods",
				CoachExtractproperties.properties.getProperty("methods"));
		builder.addString("delimeters",
				CoachExtractproperties.properties.getProperty("delimeters"));
		String filePath = CoachExtractproperties.properties.getProperty("targetDir")+"/"+CoachExtractproperties.properties.getProperty("targetFile");
		builder.addString("filePath",filePath.toString());
		
		logger.info("MATMSTRXExtract.launchExtract delimeters :"+CoachExtractproperties.properties.getProperty("delimeters"));
		logger.info("MATMSTRXExtract.launchExtract target directory : "+filePath);

		builder.addLong("MATMSTRX Start time",System.currentTimeMillis()).toJobParameters();
		param = builder.toJobParameters();
		logger.info("********* MATMSTRX Extract started********" + startTime);
		logger.info("Extract started with job name : MATMSTRXExtractJob");
		jobName = "MATMSTRXExtractJob";
		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		
		if (jobName != null) {
			Job job = (Job) context.getBean(jobName);
			try {
				JobExecution execution = jobLauncher.run(job, param);
				endTime = PMDBUtil.getFormatedDate();
				if (execution.getStatus() == BatchStatus.COMPLETED) {
					logger.info("MATMSTRX  job completed successfully");
					System.out.println("MATMSTRX job completed Successfully");
					PMDBUtil.writeRDYFile(fileName, "" , fileDir);
				} else  {
					logger.info("MATMSTRX job failed with following exceptions :");
					System.out.println("MATMSTRX job failed with following exceptions :");
					List<Throwable> exceptionList = execution
							.getAllFailureExceptions();
					for (Throwable th : exceptionList) {
						logger.info("exception :" + th.getLocalizedMessage());
					}
				}
			} catch (Exception e) {
				logger.error("MATMSTRX extract failed due to : "
						+ e.getLocalizedMessage());
				System.out.println("MATMSTRX extract failed due to : "
						+ e.getLocalizedMessage());
				return false;
			}
			logger.info("******MATMSTRX End time *********" + endTime);
			return true;
		} else {
			return false;
		}
	}

}
