/**
 *
 */
package com.plmviewer.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.plmviewer.exceptions.BusinessException;
import com.plmviewer.model.DocumentObjectRelationship;
import com.plmviewer.model.ProductVo;
import com.plmviewer.model.SKUaRevVo;
import com.plmviewer.model.SkuAdditionalDetailsVo;

/**
 * @author uthanasekarapandian
 *
 */
public interface ProductService {

    public List<ProductVo> getProduct(String searchkey, String searchtype) throws Exception;

    public Map<String, String> getProductSkuDetails(String searchkey, String searchtype, String skuStyleName) throws Exception;

    public SkuAdditionalDetailsVo getProductSkuAdditionalDetails(String searchkey, String searchtyper, String skuStyleName, String dept) throws Exception;

    public List<ProductVo> getProductList(String searchkey, String searchtype) throws Exception;

    public Map<String, String> getAttribute(String attrtype, String attrname) throws Exception;

    public List<DocumentObjectRelationship> getProductDocumentDetails(String prodNum, String type) throws Exception;

	public Map<String, String> initReportLoad();
}
