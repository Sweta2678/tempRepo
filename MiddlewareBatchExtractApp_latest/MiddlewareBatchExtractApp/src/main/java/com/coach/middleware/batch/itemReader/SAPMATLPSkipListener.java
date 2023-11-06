package com.coach.middleware.batch.itemReader;

import org.apache.log4j.Logger;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.SkipListener;

import com.coach.middleware.extractloader.SAPMATLPExtract;


public class SAPMATLPSkipListener implements SkipListener<Object, Object> {

	private static final Logger logger = Logger
			.getLogger(SAPMATLPSkipListener.class);

	public SAPMATLPExtract extract = new SAPMATLPExtract();
	@Override
	public void onSkipInRead(Throwable t) {
		//SAPMATLPExtract.successFlag=false;
		logger.error("SAPMATLPSkipListener.onSkipInRead Records skipped while reading : "+t.getMessage());
	}

	@Override
	public void onSkipInWrite(Object item, Throwable t) {
		SAPMATLPExtract.successFlag=false;
		logger.error("SAPMATLPSkipListener.onSkipInWrite Records skipped while writing : "+t.getMessage());

	}

	@Override
	public void onSkipInProcess(Object item, Throwable t) {
		SAPMATLPExtract.successFlag=false;
		logger.error("SAPMATLPSkipListener.onSkipInProcess Records skipped while Processing : "+t.getMessage());

	}

}
