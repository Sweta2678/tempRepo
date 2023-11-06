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

import com.coach.middleware.util.CoachExtractproperties;
import com.coach.middleware.util.PMDBUtil;

public class ESSSTYLEExtract implements IExtractLoader {
	private static final Logger logger = Logger
	.getLogger(ESSSTYLEExtract.class);
	public JobParametersBuilder builder = new JobParametersBuilder();
	public JobParameters param = null;
	private String swDepartments = null;
	@Override
	public boolean launchExtract(String extractname, String runmode,
			String propertyPath) throws Exception {
		
		
		CoachExtractproperties coachExtractproperties = new CoachExtractproperties(propertyPath);
		swDepartments = CoachExtractproperties.properties.getProperty("com.coach.middleware.prodmast.swDepartments");
		
		String jobName = null;
		builder.addLong("ESSSTYLE Start time",System.currentTimeMillis()).toJobParameters();
		param = builder.toJobParameters();
		logger.info("********* ESSSTYLE Extract started********" + new Date());
				String[] springConfig = { "com/coach/middleware/batch/config/spring-batch-context.xml" };
		ApplicationContext context = new ClassPathXmlApplicationContext(
				springConfig);
		
		com.coach.middleware.batch.itemProcessor.ESSSTYLEExtractItemProcessor essStyleExtractItemProcessor = (com.coach.middleware.batch.itemProcessor.ESSSTYLEExtractItemProcessor)context.getBean("ESSSTYLEExtractItemProcessor");
		String Condition = "";
		org.springframework.batch.item.database.JdbcCursorItemReader itemreader = (org.springframework.batch.item.database.JdbcCursorItemReader)context.getBean("ESSSTYLEExtractDBItemReader");
		logger.info("Item Reade Query :: "+itemreader.getSql().toString());
		String query = itemreader.getSql().toString();
		query = query.replaceAll("#Condition#", Condition);
		logger.info("Item Reade Query :: "+query);
		itemreader.setSql(query);
		logger.info("Item Reade Query :: "+itemreader.getSql().toString());
		essStyleExtractItemProcessor.setSwDepartments(swDepartments);
			logger.info("Extract started with Full Mode ");
			jobName = "ESSSTYLEExtractJob";
		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		{
			Job job = (Job) context.getBean(jobName);
			try {
				//ESSSTYLEExtractDao.deleteESSSTYLETable();
				JobExecution execution = jobLauncher.run(job,
						param);
				if (execution.getStatus() == BatchStatus.COMPLETED) {
					logger.info("ESSSTYLE job completed successfully : "
							+ new Date());
					//PMDBUtil.writeRDYFile(extractname,"","csv");
					System.out.println("ESSSTYLE job completed successfully ");
					// Here you can perform some other business logic like
					// cleanup
				} else  {
					logger.info("ESSSTYLE job failed with following exceptions :");
					List<Throwable> exceptionList = execution
							.getAllFailureExceptions();
					for (Throwable th : exceptionList) {
						logger.info("exception :"
								+ th.getLocalizedMessage());
					}
					System.out.println("ESSSTYLE job failed ");
				}
			} catch (Exception e) {
				logger.error("ESSSTYLE job failed with following exceptions :");
				e.printStackTrace();
				System.out.println("ESSSTYLE job failed ");
				return false;
			}
			logger.info("******End time *********"+ new Date());
			return true;
		}
	}
}
