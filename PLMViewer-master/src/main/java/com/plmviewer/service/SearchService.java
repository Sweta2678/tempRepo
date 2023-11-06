/**
 * 
 */
package com.plmviewer.service;

import java.util.List;
import java.util.Map;

import com.plmviewer.model.AdvSearchVo;
import com.plmviewer.model.ProductVo;
import com.plmviewer.model.searchVo;

/**
 * @author uthanasekarapandian
 *
 */
public interface SearchService {
	 public  Map<String,Map<String,String>> getattribute(String attrtype,String attrname);
	public List<ProductVo> getProductList(AdvSearchVo search);
        public List<searchVo> getProductListDetail(AdvSearchVo search);
}
