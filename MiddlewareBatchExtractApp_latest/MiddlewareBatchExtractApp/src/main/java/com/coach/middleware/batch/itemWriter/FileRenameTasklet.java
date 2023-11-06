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

import com.coach.middleware.batch.dao.VO.RpFtpAbcVO;
import com.coach.middleware.batch.itemProcessor.LpSkuSapExtractItemProcessor;

public class FileRenameTasklet implements Tasklet {

	private static final Logger logger = Logger.getLogger(FileRenameTasklet.class);
	
	private String message;

	private Resource resource;
	
	private String targetDestDirectory;
	
	

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
		ExitStatus status = arg0.getExitStatus();	
		
		
			renameResource(resource.getFile());
		

		return RepeatStatus.FINISHED;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	private void renameResource(File oldFile) throws IOException, FileNotFoundException {

		// File fileToBeRenamed =new File(fileName); //old name
		try{
		logger.debug("File Rename and archive begins ");
		DateFormat df = new SimpleDateFormat("MM_dd_yyyy hh#mm#ss#a");
		df.setTimeZone(TimeZone.getDefault());
		String archiveName = "/";
		String fileName = FilenameUtils.getBaseName(oldFile.getName());
		logger.debug("File archived to location  "+this.targetDestDirectory);
		String dirArchive = this.targetDestDirectory + fileName
				+ df.format(new Date()) ;
		//logger.debug("What comes here ==>"+dirArchive);
		FileUtils.moveFile(oldFile, new File(dirArchive));
		}catch(Exception e){
			logger.error("File archiving error", e);
		}

	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	/*public RpFtpAbcVO getRpFtpAbcVO() {
		return rpFtpAbcVO;
	}

	public void setRpFtpAbcVO(RpFtpAbcVO rpFtpAbcVO) {
		this.rpFtpAbcVO = rpFtpAbcVO;
	}*/

	public String getTargetDestDirectory() {
		return targetDestDirectory;
	}

	public void setTargetDestDirectory(String targetDestDirectory) {
		this.targetDestDirectory = targetDestDirectory;
	}
	
	

	

}
