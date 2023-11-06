/**
 * 
 */
package com.plmviewer.service;

import java.math.BigDecimal;
import java.util.List;

import com.plmviewer.model.MaterialColorDetail;
import com.plmviewer.model.MaterialColorSearchResultVo;
import com.plmviewer.model.MaterialPricingSearchResult;
import com.plmviewer.model.MaterialReportVo;
import com.plmviewer.model.MaterialResultVo;
import com.plmviewer.model.MaterialSearchVo;
import com.plmviewer.model.MaterialSupplierVo;
import com.plmviewer.model.MaterialVo;
import com.plmviewer.model.User;

import java.util.Map;

/**
 * @author uthanasekarapandian
 *
 */
public interface MaterialService {
	public List<MaterialResultVo> getMaterialList (MaterialSearchVo materialSearchVo)  throws Exception;
	public MaterialVo getMaterialData(String  searchkey,String searchtype, String materialType)  throws Exception;
    public  Map<String,Map<String,String>> getmaterialattribute(String attrtype,String attrname)  throws Exception;
    public MaterialSupplierVo getMaterialSupplierVo(BigDecimal ida3MasterRef,String materialType)  throws Exception;
    public  List<MaterialColorSearchResultVo> getMaterialColorSearch(String matPartNumber)  throws Exception;
    public MaterialColorDetail getMaterialColorDetails(BigDecimal colorId)  throws Exception;    
    public List<MaterialPricingSearchResult>  getMaterialPricingList (BigDecimal ida3MasterRef)  throws Exception;
	public List<MaterialReportVo> gererateMaterialReport(String cmnumber, String department, User user);
}
