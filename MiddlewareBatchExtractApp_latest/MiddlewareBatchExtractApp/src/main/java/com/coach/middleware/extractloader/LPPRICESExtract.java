package com.coach.middleware.extractloader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

import com.coach.middleware.batch.dao.LPPRICESExtractDao;
import com.coach.middleware.batch.dao.VO.FullPriceMOAVO;
import com.coach.middleware.batch.itemProcessor.LPPRICESExtractItemProcessor;
import com.coach.middleware.batch.itemWriter.LPPRICESExtractItemWriter;
import com.coach.middleware.util.CoachExtractproperties;
import com.coach.middleware.util.PMDBUtil;

public class LPPRICESExtract implements IExtractLoader {
	private static final Logger logger = Logger
			.getLogger(LPPRICESExtract.class);
	public JobParametersBuilder builder = new JobParametersBuilder();
	public JobParameters param = null;
	@Override
	public boolean launchExtract(String extractname, String runmode,
			String propertyPath) throws Exception {
		String jobName = null;
		CoachExtractproperties coachExtractproperties = new CoachExtractproperties(
				propertyPath);
		
		String fileName = CoachExtractproperties.properties.getProperty("targetFile");
		String fileDir = CoachExtractproperties.properties.getProperty("targetDir");
		if(fileName == "" || fileDir == "" || fileName == null || fileDir == null){
			throw new Exception("File or directory value in property file cannot be empty\n");
		}
		builder.addLong("LPPRICES Start time",System.currentTimeMillis()).toJobParameters();
		builder.addString("methods",CoachExtractproperties.properties.getProperty("methods"));
		//System.out.println("Methods :"+CoachExtractproperties.properties.getProperty("methods"));
		String filePath = CoachExtractproperties.properties.getProperty("targetDir")+"/"+CoachExtractproperties.properties.getProperty("targetFile");
		builder.addString("filePath",filePath.toString());
		builder.addString("LppricesstagQuery", "select ESSSKU,CENTURY,PERIOD,RETAILPRICE,WHPRICE,JPY from LPPRICES");
		
		logger.info("********* LPPRICES Extract started********" + new Date());
				String[] springConfig = { "com/coach/middleware/batch/config/spring-batch-context.xml" };
				System.out.println("springConfig : "+springConfig);
		ApplicationContext context = new ClassPathXmlApplicationContext(
				springConfig);
		org.springframework.batch.item.file.FlatFileItemWriter itemwriter = (org.springframework.batch.item.file.FlatFileItemWriter)context.getBean("LPPRICESExtractFileItemWriter");		
		FileSystemResource rsWriter = new FileSystemResource(new File(fileDir+"/"+fileName));
		itemwriter.setResource(rsWriter);
		if (runmode.equalsIgnoreCase("full")) {
			logger.info("Extract started with Full Mode ");
			jobName = "LPPRICESExtractJob";
			builder.addString("LPPRICESQuery", "select productmaster.styleNumber as STYLE,skumaster.name as SKU, productmaster.uniqueid as STYLE_UNIQUE, skumaster.stdcost as STDCOST from skumaster skumaster, productmaster productmaster where productmaster.uniqueid=skumaster.styleuniqueid and productmaster.stylenumber is not null  and productmaster.DEPARTMENT not in (select department from DEPARTMENTS_TO_SKIP_DOWNSTREAM where jobname='LPPRICES') order by STYLE_UNIQUE,SKU asc");
		} else if (runmode.equalsIgnoreCase("delta")) {
			logger.info("Extract started with Delta Mode ");
			builder.addString("LPPRICESQuery", "select productmaster.styleNumber as STYLE,skumaster.name as SKU, productmaster.uniqueid as STYLE_UNIQUE, skumaster.stdcost as STDCOST from skumaster skumaster, productmaster productmaster where productmaster.uniqueid=skumaster.styleuniqueid and productmaster.stylenumber in (select distinct stylenumber from delta_extract where stylenumber is not null) and productmaster.DEPARTMENT not in (select department from DEPARTMENTS_TO_SKIP_DOWNSTREAM where jobname='LPPRICES') order by STYLE_UNIQUE,SKU asc");
			jobName = "LPPRICESExtractJob";
		}
		else{
			logger.info("No Mode entered for the extract : full/Delta , taking delta as default");
			builder.addString("LPPRICESQuery", "select productmaster.styleNumber as STYLE,skumaster.name as SKU, productmaster.uniqueid as STYLE_UNIQUE, skumaster.stdcost as STDCOST from skumaster skumaster, productmaster productmaster where productmaster.uniqueid=skumaster.styleuniqueid and productmaster.stylenumber in (select distinct stylenumber from delta_extract where stylenumber is not null) and productmaster.DEPARTMENT not in (select department from DEPARTMENTS_TO_SKIP_DOWNSTREAM where jobname='LPPRICES') order by STYLE_UNIQUE,SKU asc");
			jobName = "LPPRICESExtractJob";
		}
		
		param = builder.toJobParameters();
		System.out.println("param" + param);
		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		if (jobName != null) {
			Job job = (Job) context.getBean(jobName);
			try {
				//LPPRICESExtractDao.deleteLPPRICESTable();
				JobExecution execution = jobLauncher.run(job,param);
				if (execution.getStatus() == BatchStatus.COMPLETED) {
					PMDBUtil.writeRDYFile(fileName, "" , fileDir);
					logger.info("LPPrices job completed successfully : ");
					// Here you can perform some other business logic like
					// cleanup
				} else {
					logger.info("LPPrices job failed with following exceptions :");
					System.out.println("LPPrices job failed with exceptions ");
					List<Throwable> exceptionList = execution
							.getAllFailureExceptions();
					for (Throwable th : exceptionList) {
						logger.info("exception :"
								+ th.getLocalizedMessage());
					}
				}
				logger.info(" No of records read on LPPRICES : "+LPPRICESExtractItemProcessor.recordCount);
				logger.info(" No of records write on LPPRICES : "+LPPRICESExtractItemWriter.rCount);
				System.out.println(" No of records read on LPPRICES : "+LPPRICESExtractItemProcessor.recordCount);
				System.out.println(" No of records write on LPPRICES : "+LPPRICESExtractItemWriter.rCount);
				
			} catch (Exception e) {
				logger.error("LPPrices job failed with following exceptions :"+e.getLocalizedMessage());
				System.out.println("LPPrices job failed with exceptions ");
				e.printStackTrace();
				return false;
			}
			logger.info("******End time *********"+ new Date());
			return true;
		} else {
			return false;
		}
	}
	public static void writeRDYFile(String fileName,String data) {
		System.out.println("Write rdy file started");
		PrintWriter out = null;
		try {
		if (fileName == null) {
			logger.error(" Rdy File Name is null");
			return;
		}
		if (fileName != null && fileName.trim().length() > 0) {
			int i = fileName.indexOf('.');

			if (i == -1) {
				fileName = fileName.trim() + ".rdy";
			} else {
				fileName = fileName.substring(0, i).trim() + ".rdy";
			}
			logger.info("fileName:" + fileName);
		}
			out = new PrintWriter(new FileWriter("csv/"+fileName));
			out.print(data);
		} catch (IOException e) {
			logger.error("writeToFile(String, String)", e); //$NON-NLS-1$
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
}
