/**
 * 
 */
package com.plmviewer.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plmviewer.dao.SpecificationDao;
import com.plmviewer.model.DocumentObjectRelationship;

/**
 * @author uthanasekarapandian
 *
 */
@Service("specificationService")
public class SpecificationServiceImpl implements SpecificationService {
	@Autowired
	SpecificationDao specificationDao;
	
	private static final Logger logger = LoggerFactory.getLogger(SpecificationServiceImpl.class); 

	@Override
	public List<Map<String, String>> getSpecDetails(String specName) {
		
		logger.info("getSpecDetails Starts");
		List<Map<String,String>> specComponentList = specificationDao.getSpecDetails(specName);
		
		logger.info("getSpecDetails Size "+ specComponentList.size() );
			logger.info("getSpecDetails Ends");
		return specComponentList;
	}
	
	@Override
	public List<DocumentObjectRelationship> getSpecDocList(String specName) {
		
		logger.info("getSpecDocList Starts");
		 List<DocumentObjectRelationship>  specDocList= specificationDao.getSpecDocumentDetails(specName);
		
		logger.info("getSpecDocList Size "+ specDocList.size() );
			logger.info("getSpecDocList Ends");
		return specDocList;
	}

}
