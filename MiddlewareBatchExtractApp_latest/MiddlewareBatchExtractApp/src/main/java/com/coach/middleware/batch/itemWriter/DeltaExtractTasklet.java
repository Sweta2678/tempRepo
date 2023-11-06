package com.coach.middleware.batch.itemWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;

import com.coach.middleware.batch.dao.DeltaExtractDAO;
import com.coach.middleware.batch.dao.VO.RpFtpAbcVO;
import com.coach.middleware.batch.itemProcessor.LpSkuSapExtractItemProcessor;

public class DeltaExtractTasklet implements Tasklet {

	private static final Logger logger = Logger.getLogger(DeltaExtractTasklet.class);
	
	
	
	@Autowired
	DeltaExtractDAO deltaExtractDAO;
	/**
	 * Method to execute in step.
	 * 
	 * @param arg0
	 *            Represents a contribution to a StepExecution, buffering
	 *            changes until they can be applied at a chunk boundary.
	 * @param arg1
	 *            Represents a contribution to a StepExecution, buffering
	 *            changes until they can be applied at a chunk boundary.
	 */
	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1)  
			throws Exception {
		
		logger.debug("DeltaExtractTasklet execution started");
		
		try{
			
			deltaExtractDAO.truncateDeltaExtract();
			deltaExtractDAO.populateDeltaExtract();
			deltaExtractDAO.deleteDeltaExtract();
			
		}catch(Exception e){
			new Exception("Job Failed");
			return RepeatStatus.FINISHED;
		}
			
		return RepeatStatus.FINISHED;
	}
	
	public DeltaExtractDAO getDeltaExtractDAO() {
		return deltaExtractDAO;
	}
	public void setDeltaExtractDAO(DeltaExtractDAO deltaExtractDAO) {
		this.deltaExtractDAO = deltaExtractDAO;
	}

	
	/*public RpFtpAbcVO getRpFtpAbcVO() {
		return rpFtpAbcVO;
	}

	public void setRpFtpAbcVO(RpFtpAbcVO rpFtpAbcVO) {
		this.rpFtpAbcVO = rpFtpAbcVO;
	}*/

	

}
