package com.coach.middleware.batch.dao;

import java.util.List;

import com.coach.middleware.batch.dao.VO.CjiPriceMOAVO;
import com.coach.middleware.batch.dao.VO.FullPriceMOAVO;
import com.coach.middleware.batch.dao.VO.LPPRICESExtractVO;

public interface LPPRICESExtractDao {
	public List<FullPriceMOAVO>  getFullPriceMOAList(List<String> ownerId);
	public void  deleteLPPRICESTable();
	public  List<CjiPriceMOAVO> getCjiPriceMOAList(List<String> ownerIds);
}
