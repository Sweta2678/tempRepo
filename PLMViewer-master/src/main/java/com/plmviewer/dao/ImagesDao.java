/**
 * 
 */
package com.plmviewer.dao;

import com.plmviewer.model.ImageSearchVo;
import java.math.BigDecimal;
import java.util.List;

import com.plmviewer.model.ImagesPageToDocLinkVo;
import java.util.Map;

/**
 * @author uthanasekarapandian
 *
 */
public interface ImagesDao {
    
	public List<ImagesPageToDocLinkVo> getImagesSearchResultList(ImageSearchVo prodNum);
	public List<ImagesPageToDocLinkVo> getProductSpecImagesList(BigDecimal docMasterId);
	public List<ImagesPageToDocLinkVo> getProductImagesList(BigDecimal ida3MasterRef);
    public Map<String,Map<String,String>> getProductAttributeList();
    
}