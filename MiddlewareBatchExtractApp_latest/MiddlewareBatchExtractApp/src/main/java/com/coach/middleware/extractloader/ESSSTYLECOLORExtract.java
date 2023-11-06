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

import com.coach.middleware.util.PMDBUtil;

public class ESSSTYLECOLORExtract implements IExtractLoader {
	private static final Logger logger = Logger
	.getLogger(ESSSTYLECOLORExtract.class);
	public JobParametersBuilder builder = new JobParametersBuilder();
	public JobParameters param = null;
	@Override
	public boolean launchExtract(String extractname, String runmode,
			String propertyPath) throws Exception {
		
		

		String jobName = null;
		builder.addLong("ESSSTYLECOLOR Start time",System.currentTimeMillis()).toJobParameters();
		param = builder.toJobParameters();
		logger.info("********* ESSSTYLECOLOR Extract started********" + new Date());
				String[] springConfig = { "com/coach/middleware/batch/config/spring-batch-context.xml" };
		ApplicationContext context = new ClassPathXmlApplicationContext(
				springConfig);
		
		String Condition = "";
		org.springframework.batch.item.database.JdbcCursorItemReader itemreader = (org.springframework.batch.item.database.JdbcCursorItemReader)context.getBean("ESSSTYLECOLORExtractDBItemReader");
		logger.info("Item Reade Query :: "+itemreader.getSql().toString());
		String query = itemreader.getSql().toString();
		query = query.replaceAll("#Condition#", Condition);
		logger.info("Item Reade Query :: "+query);
		itemreader.setSql(query);
		logger.info("Item Reade Query :: "+itemreader.getSql().toString());
			logger.info("Extract started with Full Mode ");
			jobName = "ESSSTYLECOLORExtractJob";
		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		{
			Job job = (Job) context.getBean(jobName);
			try {
				//ESSSTYLECOLORExtractDao.deleteESSSTYLECOLORTable();
				JobExecution execution = jobLauncher.run(job,
						param);
				if (execution.getStatus() == BatchStatus.COMPLETED) {
					logger.info("ESSSTYLECOLOR job completed successfully : "
							+ new Date());
					//PMDBUtil.writeRDYFile(extractname,"","csv");
					System.out.println("ESSSTYLE job Successfully completed ");
					// Here you can perform some other business logic like
					// cleanup
				} else  {
					logger.info("ESSSTYLECOLOR job failed with following exceptions :");
					List<Throwable> exceptionList = execution
							.getAllFailureExceptions();
					for (Throwable th : exceptionList) {
						logger.info("exception :"
								+ th.getLocalizedMessage());
					}
					System.out.println("ESSSTYLE job failed ");
				}
			} catch (Exception e) {
				logger.error("ESSSTYLECOLOR job failed with following exceptions :");
				e.printStackTrace();
				return false;
			}
			logger.info("******End time *********"+ new Date());
			return true;
		}
	}
}
