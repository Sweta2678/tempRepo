package com.coach.middleware.batch.dao;

import java.util.*;

import com.coach.middleware.batch.dao.VO.AttributeStoreVO;
import com.coach.middleware.batch.dao.VO.TotoExtractVO;

public interface TotoExtractDAO {

	
	/*public List<TotoExtractVO> getTotoExtractDetails();*/
	//public TotoExtractVO getTotoExtractDetails();
	public List<AttributeStoreVO> getTotoExtractAttrKeys(TotoExtractVO totoExtractVO);
	/*public Map getTotoExtractClassNameMap(TotoExtractVO totoExtractVO);
	public Map getTotoExtractSubClassMap(TotoExtractVO totoExtractVO);*/
	
	
}
