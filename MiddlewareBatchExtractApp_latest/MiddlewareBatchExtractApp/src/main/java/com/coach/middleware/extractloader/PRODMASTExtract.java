package com.coach.middleware.extractloader;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Properties;

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
import org.springframework.core.io.FileSystemResource;

import com.coach.middleware.util.CoachExtractproperties;

public class PRODMASTExtract implements IExtractLoader{
	private static final Logger logger = Logger
	.getLogger(PRODMASTExtract.class);
	public JobParameters param = null;
	public JobParametersBuilder builder = new JobParametersBuilder();
	private String targetDirectory = null;
	private String targetFile = null;
	private String defaultDate = null;
	private String defaultIntroDate = null;
	private String sizedDepartments = null;
	private String stylePrefixes = null;
	private String ignoreStyleSuffix = null;
	private String skippedRecordsPath = null;
	private String skippedRecordsFileName = null;
	private String swStyles = null;
	private String swDepartments = null;
	private int sizeLength;
	
	public boolean launchExtract(String extractname, String runmode,
			String propertyPath) throws Exception {

		String jobName = null;
		logger.info("********* PRODMAST Extract started********" + new Date());
		
		String[] springConfig = { "com/coach/middleware/batch/config/spring-batch-context.xml" };
		ApplicationContext context = new ClassPathXmlApplicationContext(
				springConfig);
		CoachExtractproperties coachExtractproperties = new CoachExtractproperties(propertyPath);
		if (runmode.equalsIgnoreCase("full")) {
			logger.info("Extract started with Full Mode ");
			jobName = "NightlyOutboundJobFull";
		} else if (runmode.equalsIgnoreCase("delta")) {
			logger.info("Extract started with Delta Mode ");
			jobName = "NightlyOutboundJobDelta";
		}
		
		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		if (jobName != null) {
			Job job = (Job) context.getBean(jobName);
			try {
				targetDirectory = 	CoachExtractproperties.properties.getProperty("com.coach.middleware.prodmast.targetDirectory");
				targetFile = 	CoachExtractproperties.properties.getProperty("com.coach.middleware.prodmast.targetFile");
				
				if(targetFile == "" || targetDirectory == "" || targetFile == null || targetDirectory == null){
					throw new Exception("File or directory value in property file cannot be empty\n");
				}
				
				/*
				 * private String defaultDate = null;
	private String defaultIntroDate = null;
	private String sizedDepartments = null;
	private String stylePrefixes = null;
	private String ignoreStyleSuffix = null;
				 * */
				
				defaultDate = 	CoachExtractproperties.properties.getProperty("com.coach.middleware.prodmast.defaultDate");
				defaultIntroDate = 	CoachExtractproperties.properties.getProperty("com.coach.middleware.prodmast.defaultIntroDate");
				sizedDepartments = 	CoachExtractproperties.properties.getProperty("com.coach.middleware.prodmast.sizedDepartments");
				stylePrefixes = 	CoachExtractproperties.properties.getProperty("com.coach.middleware.prodmast.stylePrefixes");
				ignoreStyleSuffix = 	CoachExtractproperties.properties.getProperty("com.coach.middleware.prodmast.ignoreStyleSuffix");
				skippedRecordsFileName = 	CoachExtractproperties.properties.getProperty("com.coach.middleware.prodmast.skippedRecordsFileName");
				skippedRecordsPath = 	CoachExtractproperties.properties.getProperty("com.coach.middleware.prodmast.skippedRecordsPath");
				sizeLength = Integer.parseInt(CoachExtractproperties.properties.getProperty("com.coach.middleware.prodmast.sizeLength"));
				String swDepartmentsQuery = CoachExtractproperties.properties.getProperty("com.coach.middleware.prodmast.swDepartments");
				
				org.springframework.batch.item.file.FlatFileItemWriter itemwriter = (org.springframework.batch.item.file.FlatFileItemWriter)context.getBean("ProdMastFileItemWriter");
				org.springframework.batch.item.file.FlatFileItemWriter genTotoRDY = (org.springframework.batch.item.file.FlatFileItemWriter)context.getBean("genProdMastRDY");
				FileSystemResource rsRdy = new FileSystemResource(new File(targetDirectory+"/"+targetFile+".rdy"));
				FileSystemResource rsWriter = new FileSystemResource(new File(targetDirectory+"/"+targetFile));
				itemwriter.setResource(rsWriter);
				genTotoRDY.setResource(rsRdy);
				
				com.coach.middleware.batch.itemProcessor.ProdMastItemProcessor prodMastItemProcessor = (com.coach.middleware.batch.itemProcessor.ProdMastItemProcessor)context.getBean("ProdMastItemProcessor");
				//com.coach.middleware.util.SwstylePreparedStatementSetter swstylePreparedStatementSetter = (com.coach.middleware.util.SwstylePreparedStatementSetter)context.getBean("swstylePreparedStatementSetter");
				//swstylePreparedStatementSetter.setId(swDepartments);
				//org.springframework.batch.item.database.JdbcCursorItemReader itemreader = (org.springframework.batch.item.database.JdbcCursorItemReader)context.getBean("ProdMastItemReader");
				
				
				String Condition = "";
				org.springframework.batch.item.database.JdbcCursorItemReader itemreader = (org.springframework.batch.item.database.JdbcCursorItemReader)context.getBean("ProdMastItemReader");
				logger.info("Item Reade Query :: "+itemreader.getSql().toString());
				String query = itemreader.getSql().toString();
				query = query.replaceAll("#Condition#", Condition);
				logger.info("Item Reade Query :: "+query);
				itemreader.setSql(query);
				logger.info("Item Reade Query :: "+itemreader.getSql().toString());
				
				prodMastItemProcessor.setDefaultDate(defaultDate);
				prodMastItemProcessor.setDefaultIntroDate(defaultIntroDate);
				prodMastItemProcessor.setSizedDepartments(sizedDepartments);
				prodMastItemProcessor.setSwDepartments(swDepartments);
				prodMastItemProcessor.setStylePrefixes(stylePrefixes);
				prodMastItemProcessor.setIgnoreStyleSuffix(ignoreStyleSuffix);
				prodMastItemProcessor.setSkippedRecordsPath(skippedRecordsPath);
				prodMastItemProcessor.setSkippedRecordsFileName(skippedRecordsFileName);
				prodMastItemProcessor.setSizeLength(sizeLength);
				builder.addString("targetDirectory", targetDirectory);
				builder.addString("targetFile", targetFile);
				builder.addLong("PRODMAST Start time", System.currentTimeMillis());
				param =  builder.toJobParameters();
					
				JobExecution execution = jobLauncher.run(job, param);
				if (execution.getStatus() == BatchStatus.COMPLETED) {
					logger.info("PRODMAST job completed successfully "
							);
					System.out.println("PRODMAST job Successfully completed");
				}else  {
					logger.info("PRODMAST job failed with following exceptions :");
					System.out.println("PRODMAST job failed with exceptions");
					List<Throwable> exceptionList = execution
							.getAllFailureExceptions();
					for (Throwable th : exceptionList) {
						logger.info("exception :"
								+ th.getLocalizedMessage());
					}
				}
				//writeRDYFile("PRODMAST","");
			} catch (Exception e) {
				logger.error("PRODMAST job failed with following exceptions :"+e.getLocalizedMessage());
				System.out.println("PRODMAST job failed with exceptions");
				e.printStackTrace();
				return false;
			}
			logger.info("******End time *********"+ new Date());
			return true;
		} else {
			return false;
		}
	
	}

}
