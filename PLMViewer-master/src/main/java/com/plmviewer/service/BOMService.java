/**
 * 
 */
package com.plmviewer.service;

import java.math.BigDecimal;
import java.util.List;

import com.plmviewer.exceptions.BusinessException;
import com.plmviewer.model.BOMLink;
import com.plmviewer.model.BOMModel;
import com.plmviewer.model.BOMPart;
import com.plmviewer.model.SkuInfoVo;

/**
 * @author uthanasekarapandian
 *
 */
public interface BOMService {
	public BOMModel getBOMdata(String prodIda3MasterRef,String bomPartId) throws BusinessException;
	public BOMModel getDummyData(String prodIda3MasterRef,String bomPartId) throws BusinessException;

}
