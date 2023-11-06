package com.coach.middleware.batch.dao;

import java.util.List;

import com.coach.middleware.batch.dao.VO.AttributeStoreVO;
import com.coach.middleware.batch.dao.VO.FullPriceMOAVO;
import com.coach.middleware.batch.dao.VO.NightlyOutBoundVO;
import com.coach.middleware.batch.dao.VO.PRODMASTFullPriceMOAVO;
import com.coach.middleware.batch.dao.VO.RpFtpAbcVO;
import com.coach.middleware.batch.dao.VO.SizeScaleVO;

public interface NightlyOutBoundDAO {
	

	public List<AttributeStoreVO> getTotoExtractAttrKeys(NightlyOutBoundVO nightlyOutBoundVO);

	public List<SizeScaleVO> getSizeScaleMappingList(NightlyOutBoundVO nightlyOutBoundVO);
	
	public List<PRODMASTFullPriceMOAVO> getCostMoaList(NightlyOutBoundVO nightlyOutBoundVO);
	
	public List<RpFtpAbcVO> getRpFtpAbcList(NightlyOutBoundVO nightlyOutBoundVO);

}
