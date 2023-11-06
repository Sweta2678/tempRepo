/**
 * 
 */
package com.plmviewer.dao;

import java.math.BigDecimal;
import java.util.List;

import com.plmviewer.model.DocumentSearchResult;
import com.plmviewer.model.DocumentToFileLinkVo;

import java.util.Map;
import com.plmviewer.model.DocumentVo;

/**
 * @author uthanasekarapandian
 *
 */
public interface DocumentDao {
	
    public String getAttributeValue(String attributetype, String attributename,String valuekey);
	public List<DocumentSearchResult> getDocumentSearchResultList(
			String docName, BigDecimal ida3a11);
	public Map<String,String> getDocumentList() throws Exception;
	public DocumentVo getDocumentDetails(String docName,BigDecimal ida3a11);
	public List<DocumentToFileLinkVo> getDocumenttoFileLink(BigDecimal ida3MasterRef,String fileType);
        public String getGeneralAttributeValue(String attributename,String valuekey); 

}
