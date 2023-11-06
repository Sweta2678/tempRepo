package com.coach.middleware.batch.dao;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import com.coach.middleware.batch.dao.VO.LookupVo;
import com.coach.middleware.batch.dao.VO.MaterialPriceVo;
import com.coach.middleware.batch.dao.VO.VariantLookUpVo;

public interface MATMSTRXExtractDao {
	public List<LookupVo>  getAttributeFromLookups();
	public String  getAttributeLookups(String subType,String attributeName,String materialType);
	public String  getStatusLookups(String status,String attributeName);
	public String  getVariantLookups(String variantKey,String materialType);
	public List<VariantLookUpVo>  getVariantFromLookups();
	public HashMap<String, MaterialPriceVo>  getStandardCost(List<String> uniqueIDs);
	
}
