package com.coach.middleware.batch.dao;

import java.util.*;

import com.coach.middleware.batch.dao.VO.AttributeStoreVO;
import com.coach.middleware.batch.dao.VO.ColourCodingVo;
import com.coach.middleware.batch.dao.VO.FullPriceMOAVO;
import com.coach.middleware.batch.dao.VO.LPSKUFullPriceMOAVO;
import com.coach.middleware.batch.dao.VO.LpskuExtractVO;
import com.coach.middleware.batch.dao.VO.SeasonGroupMasterVO;
import com.coach.middleware.batch.dao.VO.SizeScaleMappingVO;
import com.coach.middleware.batch.dao.VO.SizeScaleMasterVO;
import com.coach.middleware.batch.dao.VO.TotoExtractVO;

public interface AttributeExtractDAO {

	
	
	//public List<LpskuExtractVO> getTotoExtractDetails();
	public List<AttributeStoreVO> getTotoExtractAttrKeys(LpskuExtractVO lpskuvo);
	public List<ColourCodingVo> getColourCoding(LpskuExtractVO lpskuvo);
	//public Map getTotoExtractClassNameMap(LpskuExtractVO lpskuvo);
	//public Map getTotoExtractSubClassMap(LpskuExtractVO lpskuvo);
	public List<LPSKUFullPriceMOAVO>  getFullPriceMOAList(LpskuExtractVO lpskuvo);
	public List<SizeScaleMasterVO>  getsizescaleMasterList(LpskuExtractVO lpskuvo);
	public List<SeasonGroupMasterVO>  getSeasonGroupList(LpskuExtractVO lpskuvo);
	public List<String>  getDeltaExtractSlots(LpskuExtractVO lpskuvo);
	public List<SizeScaleMappingVO>  getSkizeScaleMapping(LpskuExtractVO lpskuvo);
	
	
	
	public void  deleteLPSKUExtractStaging();
	public  void deleteLPSKUExtractStagingbySlot(List<String> lst);	
	
	
	
	
}

