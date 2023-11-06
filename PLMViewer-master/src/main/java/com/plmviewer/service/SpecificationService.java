/**
 * 
 */
package com.plmviewer.service;

import java.util.List;
import java.util.Map;

import com.plmviewer.model.DocumentObjectRelationship;

/**
 * @author uthanasekarapandian
 *
 */
public interface SpecificationService {
	
	public List<Map<String,String>>  getSpecDetails(String specName);
	public List<DocumentObjectRelationship> getSpecDocList(String specName);
}

