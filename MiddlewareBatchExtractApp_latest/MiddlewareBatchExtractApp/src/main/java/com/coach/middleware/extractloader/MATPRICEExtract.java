package com.coach.middleware.extractloader;

import java.sql.Timestamp;
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

import com.coach.middleware.batch.dao.MATPRICEExtractDao;
import com.coach.middleware.util.CoachExtractproperties;
import com.coach.middleware.util.PMDBUtil;

public class MATPRICEExtract implements IExtractLoader {
	private static final Logger logger = Logger
			.getLogger(MATPRICEExtract.class);
	public JobParametersBuilder builder = new JobParametersBuilder();
	public JobParameters param = null;

	@Override
	public boolean launchExtract(String extractname, String runmode,
			String propertyPath) throws Exception {
		Timestamp startTime = PMDBUtil.getFormatedDate();
		Timestamp endTime = null;
		CoachExtractproperties coachExtractproperties = new CoachExtractproperties(
				propertyPath);
		String[] springConfig = { "com/coach/middleware/batch/config/spring-batch-context.xml" };
		ApplicationContext context = new ClassPathXmlApplicationContext(
				springConfig);
		MATPRICEExtractDao dao = (com.coach.middleware.batch.dao.MATPRICEExtractDao) context
				.getBean("MATPRICEExtractDao");
		String jobName = null;
		String fileName = CoachExtractproperties.properties.getProperty("targetFile");
		String fileDir = CoachExtractproperties.properties.getProperty("targetDir");
		builder.addString("methods",
				CoachExtractproperties.properties.getProperty("methods"));
		builder.addString("delimeters",
				CoachExtractproperties.properties.getProperty("delimeters"));
		builder.addString("targetDir",
				CoachExtractproperties.properties.getProperty("targetDir"));
		String filePath = CoachExtractproperties.properties.getProperty("targetDir")+"/"+CoachExtractproperties.properties.getProperty("targetFile");
		builder.addString("filePath",filePath.toString());
		builder.addLong("MATPRICE Start time",System.currentTimeMillis()).toJobParameters();
		param = builder.toJobParameters();
		logger.info("********* MATPRICE Extract started********" + startTime);
		// dao.insertLogTable(startTime,null,"Progress","201","Progress");
		logger.info("Extract started with job name : MATPRICEExtractJob");
		jobName = "MATPRICEExtractJob";
		// }
		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");

		if (jobName != null) {
			Job job = (Job) context.getBean(jobName);
			try {
				JobExecution execution = jobLauncher.run(job, param);
				//dao.insertLogTable(startTime, null, "Progress", "201","Progress");
				endTime = PMDBUtil.getFormatedDate();
				if (execution.getStatus() == BatchStatus.COMPLETED) {
					logger.info("MATPRICE  job completed successfully : "
							+ startTime);
					PMDBUtil.writeRDYFile(fileName, "",fileDir);
					System.out
					.println("MATPRICE job completed Successfully");
					//dao.updateLogTable(startTime, endTime, "SUCCESS", "100","SUCCESS");
				} else  {
					logger.info("MATPRICE job failed with following exceptions :");
					System.out
							.println("MATPRICE job failed with following exceptions :");
					//dao.updateLogTable(startTime, endTime, "FAILED", "200","FAILED");
					List<Throwable> exceptionList = execution
							.getAllFailureExceptions();
					for (Throwable th : exceptionList) {
						logger.info("exception :" + th.getLocalizedMessage());
					}
				}
			} catch (Exception e) {
				logger.error("MATPRICE extract failed due to : "
						+ e.getLocalizedMessage());
				System.out.println("MATPRICE job failed with following exceptions :"+e.getLocalizedMessage());
				return false;
			}
			logger.info("******MATPRICE End time *********" + endTime);
			return true;
		} else {
			return false;
		}
	}

}
