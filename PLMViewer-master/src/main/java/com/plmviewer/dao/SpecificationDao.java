/**
 * 
 */
package com.plmviewer.dao;

import java.util.List;
import java.util.Map;

import com.plmviewer.model.DocumentObjectRelationship;

/**
 * @author uthanasekarapandian
 *
 */
public interface SpecificationDao {
	public List<Map<String,String>> getSpecDetails(String specName);
	public List<DocumentObjectRelationship>  getSpecDocumentDetails(String specName);

}
