/**
 * 
 */
package com.plmviewer.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.plmviewer.model.MaterialPricingSearchResult;
import com.plmviewer.model.MaterialReportVo;
import com.plmviewer.model.MaterialColorDetail;
import com.plmviewer.model.MaterialColorSearchResultVo;
import com.plmviewer.model.MaterialResultVo;
import com.plmviewer.model.MaterialSearchVo;
import com.plmviewer.model.MaterialSupplierVo;
import com.plmviewer.model.MaterialVo;


/**
 * @author uthanasekarapandian
 *
 */

public interface MaterialDao {
	public List<MaterialResultVo> getMaterialList (MaterialSearchVo materialSearchVo) throws Exception;
	public MaterialVo getMaterialVo(String  searchkey,String searchtype, String materialType) throws Exception;
	public Map<String, Map<String, String>> getmaterialAttributelist(String attributelevel, String attributename) throws Exception;
	public Map<BigDecimal,String>  getMaterialSupplierList(BigDecimal ida3MasterRef) throws Exception;
	public MaterialSupplierVo  getMaterialSupplierVo(BigDecimal ida3MasterReference,String materialType) throws Exception;
	public  List<MaterialColorSearchResultVo> getMaterialColorSearch(String matPartNumber) throws Exception;
	public MaterialColorDetail getMaterialColorDetails(BigDecimal colorId) throws Exception;
	public String getMatCompositeData(String attName, String valKey) throws Exception;
	public String getMatAttributeData(String attName, String valKey,String matType) throws Exception;
	
	public List<MaterialPricingSearchResult>  getMaterialPricingList (BigDecimal ida3MasterRef) throws Exception;
	public List<MaterialReportVo> getMaterialReportInfo(String cmnumber, String department);
	public List<MaterialReportVo> mergeLinks(List<MaterialReportVo> bomData) throws Exception;
	
}
