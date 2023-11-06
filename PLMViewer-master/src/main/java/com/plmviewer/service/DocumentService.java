/**
 * 
 */
package com.plmviewer.service;

import java.math.BigDecimal;
import java.util.List;

import com.plmviewer.model.DocumentSearchResult;
import java.util.Map;
import com.plmviewer.model.DocumentVo;

/**
 * @author uthanasekarapandian
 *
 */
public interface DocumentService {
	
	public List<DocumentSearchResult>  getDocumentSearchResultList (String docName,BigDecimal ida3a11 );
	public Map<String,String>  getDocumentList () throws Exception;
	public DocumentVo getDocumentDetails(String docName,BigDecimal ida3a11);

}
