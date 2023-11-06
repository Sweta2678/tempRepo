package com.coach.middleware.extractloader.Exceptions;

import com.sun.jndi.cosnaming.ExceptionMapper;
import com.sun.org.apache.bcel.internal.ExceptionConstants;

public class CoachExtractException extends Exception{
	public String ErrorCode;
	public CoachExtractException(){
		
	}
	public CoachExtractException(String ex){
		super(ExceptionCodeMapper.mapper.get(ex));
		ErrorCode = ex;
	}
	public CoachExtractException(String ex,String cause){
		super(ex,new Throwable(cause));
	}
	
	@Override
	public String getMessage(){
		return ExceptionCodeMapper.mapper.get(ErrorCode);
	}
	
	@Override
    public String toString() {
		return ExceptionCodeMapper.mapper.get(ErrorCode);
    }
	

}
