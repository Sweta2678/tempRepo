package com.coach.middleware.batch.itemReader;

import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.beans.factory.annotation.Value;

public class CustomExtractParamBuilder implements StepExecutionListener {
	
	Properties properties;
	
	public JobParameters param = null;
	public JobParametersBuilder builder = new JobParametersBuilder();
	
	
	/* Code to read values for TotoExtract properties - begins*/
	@Resource(name = "totoExtractProp")
	private Properties totoExtractProp;
	
	/*reading value and setting value to local var*/
	@Value("#{totoExtractProp['TotoExtractQueryFullMode']}")
	   private String totoExtractFull;
	
	@Value("#{totoExtractProp['TotoExtractQueryDeltaMode']}")
	   private String totoExtractDelta;
	
	@Value("#{totoExtractProp['skuStatus']}")
	   private String skuStatus;
 
	/* Code to read values for TotoExtract properties - begins*/
	
	@Override
	public void beforeStep(StepExecution stepExecution) {
		
		getCustomExtractParamDef();
		
	}


	public JobParameters getCustomExtractParamDef() {
		
		/* Code to set TotoExtract job params  - begins*/
		builder.addString("TotoExtractQueryFullMode", totoExtractFull);
		builder.addString("TotoExtractQueryDeltaMode", totoExtractDelta);
		builder.addString("skuStatus", skuStatus);
		/* Code to set TotoExtract job params  - Ends*/
		param =  builder.toJobParameters();
		return param;
	}


	public Properties getProperties() {
		return properties;
	}


	public void setProperties(Properties properties) {
		this.properties = properties;
	}


	public JobParameters getParam() {
		return param;
	}


	public void setParam(JobParameters param) {
		this.param = param;
	}


	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		return null;
	}
	

	
	
}
