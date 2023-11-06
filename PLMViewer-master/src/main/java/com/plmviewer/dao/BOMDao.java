/**
 * 
 */
package com.plmviewer.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.plmviewer.model.BOMLink;
import com.plmviewer.model.BOMPart;
import com.plmviewer.model.DocumentSearchResult;
import com.plmviewer.model.SKUaRevVo;
import com.plmviewer.model.SkuInfoVo;

/**
 * @author uthanasekarapandian
 *
 */
public interface BOMDao {	
	public List<BOMPart> getBOMPart (String prodIda3MasterRef);
	public List<BOMLink> getBOMLink (String prodIda3MasterRef, BigDecimal skuIda3a12);
	public HashMap<String,String> getskuMap(String prodIda3MasterRef);
	public List<SkuInfoVo> getSkuInfo(String prodIda3MasterRef);
}
