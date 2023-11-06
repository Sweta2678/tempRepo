/**
 * 
 */
package com.plmviewer.dao;

import java.util.List;
import java.util.Map;

import com.plmviewer.model.AdvSearchVo;
import com.plmviewer.model.ProductVo;
import com.plmviewer.model.searchVo;

/**
 * @author uthanasekarapandian
 *
 */
public interface SearchDao {
	
    public Map<String, Map<String, String>> getAttributelist(String attributelevel,String attributename);

    public List<ProductVo> getProductList(AdvSearchVo attributes) ;
    public List<searchVo> getProductListDetail(AdvSearchVo attributes) ;
}
