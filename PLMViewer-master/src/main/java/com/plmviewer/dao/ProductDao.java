/**
 * 
 */
package com.plmviewer.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.plmviewer.model.DocumentObjectRelationship;
import com.plmviewer.model.LCSMoaObjectVo;
import com.plmviewer.model.ProductadditionDetailsVo;
import com.plmviewer.model.ProductVo;
import com.plmviewer.model.SKUaRevVo;
import com.plmviewer.model.SkuAdditionalDetailsVo;

/**
 * @author uthanasekarapandian
 *
 */

public interface ProductDao {
	public List<ProductVo> getProductbyStyleNumber(String styleNumber) throws Exception;
    public List<ProductVo> getProductbySlot(String slot) throws Exception;

    public Map<String, String> getProductSource(String searchkey, String searchtype)throws Exception;

    public Map<String, String> getSKUforStyle(String searchkey, String searchtype)throws Exception;

    public List<String> getSpecification(String searchkey, String searchtype)throws Exception;

    public Map<String, String> getskuDetails(String searchkey,String searchtype, String skuStyleName)throws Exception;

    
	public List<String> getProductSeason(String searchkey, String searchtype)throws Exception;
	public String getAttributeValue(String objectName,String attributeName,String valueKey)throws Exception;
	public List<ProductVo> getProductList(String styleName) throws Exception;
	    public Map<String,String> getAttributelist(String attributelevel,String attributename)throws Exception;
    public ProductadditionDetailsVo getProductDataList(String dept, String searchkey, String searchtype, String deptType) throws Exception;

    public List<LCSMoaObjectVo> getMerchandisingPricingList(String searchkey, String searchtype,
            String deptType)throws Exception;

    public SkuAdditionalDetailsVo getSkuAccessoriesDeptsql(String searchkey, String searchtype, String dept, String skuStyleName,
            String deptType)throws Exception;

    public List<LCSMoaObjectVo> getSkuMerchandisingPrice(String searchkey, String searchtype, String skuStyleName)throws Exception;

    public List<LCSMoaObjectVo> getSkuSizeScaleMoa(String searchkey, String searchtype, String skuStyleName)throws Exception;

    public List<String> getSkuGroup(String searchkey, String searchtype)throws Exception;
    
    public List<DocumentObjectRelationship>  getProductDocumentDetails(String prodNum, String type)throws Exception;
	public Map<String, String> initReportLoad();

}
