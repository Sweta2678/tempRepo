package com.coach.middleware.extractloader.Exceptions;

import java.util.HashMap;

import com.thoughtworks.xstream.mapper.Mapper;

public class ExceptionCodeMapper {
	public static HashMap<String,String> mapper = new HashMap<String,String>();
	static{
	mapper.put("COACH001","Extract name is not Found");
	mapper.put("COACH002","Extract name Should not be null");
	mapper.put("COACH003","Extract Execution Should NOt Be Matched(full/delta)");
	
	}

}
