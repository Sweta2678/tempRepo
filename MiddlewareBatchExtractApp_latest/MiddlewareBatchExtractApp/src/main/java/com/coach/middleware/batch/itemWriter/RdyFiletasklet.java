package com.coach.middleware.batch.itemWriter;

import java.io.File;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.core.io.Resource;

public class RdyFiletasklet implements Tasklet {

	
	private String message;

	private Resource resource;

	@Override
	public RepeatStatus execute(StepContribution contribution,
			ChunkContext chunkContext) throws Exception {
		
		File rdyFile = resource.getFile();
		if(rdyFile.exists()){
			rdyFile.deleteOnExit();
		}else{
			throw new Exception(".rdy does not exists");
		}	
		
		return RepeatStatus.FINISHED;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}
	
	
}
