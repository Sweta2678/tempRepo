package com.coach.middleware.batch.itemProcessor;

import java.util.*;


import org.apache.log4j.Logger;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.coach.middleware.batch.dao.RowMapper.NightlyOutBoundRowMapper;
import com.coach.middleware.batch.dao.VO.FullPriceMOAVO;
import com.coach.middleware.batch.dao.VO.NightlyOutBoundVO;
import com.coach.middleware.batch.dao.VO.PRODMASTFullPriceMOAVO;
import com.coach.middleware.batch.dao.VO.RpFtpAbcVO;

public class ProdmastDWAfterChunkReadProcessListener implements ItemWriteListener {
	
	@Autowired
	ProdMastDWItemProcessor prodMastDWItemProcessor;
	
	@Autowired
	NightlyOutBoundRowMapper nightlyOutBoundRowMapper;
	
	@Autowired
	NightlyOutBoundVO nightlyOutBoundVO;
	
	
	
	private static final Logger logger = Logger
			.getLogger(ProdmastDWAfterChunkReadProcessListener.class);


	@Override
	public void beforeWrite(List items) {
		
	}

	@Override
	public void afterWrite(List items) {
		
		logger.info("******************** After chunk Write *****************");
		logger.info("********************************************************");
		
	
		logger.info("Processed Items Witten to File");	
		
	
		prodMastDWItemProcessor.setNextChunk(true);
		
		
		nightlyOutBoundRowMapper.setSkuUniqueIdList(new ArrayList()); 
		nightlyOutBoundRowMapper.setStyleNumList(new HashSet());
		
	
		
		logger.info("******************** Written objects cleared *****************");
		logger.info("Begin reading next chunk");
		
	}
	
	@Override
	public void onWriteError(Exception exception, List items) {
		// TODO Auto-generated method stub
		
	}

	


	public ProdMastDWItemProcessor getProdMastDWItemProcessor() {
		return prodMastDWItemProcessor;
	}

	public void setProdMastDWItemProcessor(
			ProdMastDWItemProcessor prodMastDWItemProcessor) {
		this.prodMastDWItemProcessor = prodMastDWItemProcessor;
	}

	public NightlyOutBoundRowMapper getNightlyOutBoundRowMapper() {
		return nightlyOutBoundRowMapper;
	}

	public void setNightlyOutBoundRowMapper(
			NightlyOutBoundRowMapper nightlyOutBoundRowMapper) {
		this.nightlyOutBoundRowMapper = nightlyOutBoundRowMapper;
	}

	public NightlyOutBoundVO getNightlyOutBoundVO() {
		return nightlyOutBoundVO;
	}

	public void setNightlyOutBoundVO(NightlyOutBoundVO nightlyOutBoundVO) {
		this.nightlyOutBoundVO = nightlyOutBoundVO;
	}


	

	

}
