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

public class NightlyAfterChunkReadProcessListener implements ItemWriteListener {
	
	@Autowired
	ProdMastItemProcessor prodMastItemProcessor;
	
	@Autowired
	NightlyOutBoundRowMapper nightlyOutBoundRowMapper;
	
	@Autowired
	NightlyOutBoundVO nightlyOutBoundVO;
	
	
	
	private static final Logger logger = Logger
			.getLogger(NightlyAfterChunkReadProcessListener.class);


	@Override
	public void beforeWrite(List items) {
		
	}

	@Override
	public void afterWrite(List items) {
		
		logger.info("******************** After chunk Write *****************");
		logger.info("********************************************************");
		
		
		
		/*List<String> deptRowList = new ArrayList<String>();
		List<String> classRowList = new ArrayList<String>();
		List<String> subClassRow = new ArrayList<String>();
		List<String> styleRow = new ArrayList<String>();
		List<String> skuRow = new ArrayList<String>();
		List<String> sizeRow = new ArrayList<String>();
		List costMoaList = new ArrayList<String>();
		List rpFtpAbcList = new ArrayList<String>();
		
		
		
		deptRowList.addAll(prodMastItemProcessor.getDepartmentRow());
		classRowList.addAll(prodMastItemProcessor.getClassRow());
		subClassRow.addAll(prodMastItemProcessor.getSubClassRow());
		styleRow.addAll(prodMastItemProcessor.getStyleRow());
		skuRow.addAll(prodMastItemProcessor.getSkuRow());
		sizeRow.addAll(prodMastItemProcessor.getSizeRow());*/
		/*costMoaList.addAll(nightlyOutBoundVO.getSkuUniqueIdList());
		rpFtpAbcList.addAll(nightlyOutBoundVO.getStyleNumList());*/
		
		/*int collRetention = 5;*/
		logger.info("Processed Items Witten to File");	
		
		
		/* 
		
		if (skuRow.size() > 5) {

			if (prodMastItemProcessor.styleRow.size() > 5) {

				if (prodMastItemProcessor.classRow.size() > 5) {

					if (prodMastItemProcessor.subClassRow.size() > 5) {

						if (deptRowList.size() > 5) {

							logger.info("Clearing Style level collection");
							Set<String> tmpSet = clearCollection(styleRow,
									prodMastItemProcessor.subClassRow.size());
							prodMastItemProcessor.setStyleRow(tmpSet);
							
							logger.info("Clearing Sub Class level collection");
							Set<String> tmpSetSubClass = clearCollection(
									subClassRow, prodMastItemProcessor.classRow.size());
							prodMastItemProcessor
									.setSubClassRow(tmpSetSubClass);

							logger.info("Clearing Class level collection");
							Set<String> tmpSetClass = clearCollection(
									classRowList, deptRowList.size());
							prodMastItemProcessor.setClassRow(tmpSetClass);

							logger.info("Clearing Dept level collection");
							Set<String> tmpSetDept = clearCollection(
									deptRowList, 5);
							prodMastItemProcessor.setDepartmentRow(tmpSetDept);
						}

					}

				}

			}

			logger.info("Clearing Sku level collection");
			Set<String> tmpSet = clearCollection(skuRow, 5);
			prodMastItemProcessor.setSkuRow(tmpSet);

		}
		
		if(prodMastItemProcessor.sizeRow.size() > 5 ){
			logger.info("Clearing Size level collection");
			Set<String> tmpSetSize = clearCollection(sizeRow,5);
			prodMastItemProcessor.setSizeRow(tmpSetSize);
		}*/
		
		prodMastItemProcessor.setNextChunk(true);
		
		
		nightlyOutBoundRowMapper.setSkuUniqueIdList(new ArrayList()); 
		nightlyOutBoundRowMapper.setStyleNumList(new HashSet());
		
		/*logger.info("Capacity deptRowList==>"+deptRowList.size());
		logger.info("Capacity classRowList==>"+classRowList.size());
		logger.info("Capacity subClassRow==>"+subClassRow.size());
		logger.info("Capacity styleRow==>"+styleRow.size());
		logger.info("Capacity skuRow==>"+skuRow.size());
		logger.info("Capacity sizeRow==>"+sizeRow.size());
		logger.info("Capacity costMoaList==>"+costMoaList.size());
		logger.info("Capacity rpFtpAbcList==>"+rpFtpAbcList.size());*/
		
		logger.info("******************** Written objects cleared *****************");
		logger.info("Begin reading next chunk");
		
	}
	
	/*private Set<String> clearCollection(List collectionList,int threshHold) {
		// TODO Auto-generated method stub
		Set<String> col = new HashSet();
		int chkSize = collectionList.size();
		logger.info(" clear col - chkSize==>"+chkSize);
		Iterator iter = collectionList.iterator();
		while (iter.hasNext()) {
			Object item = iter.next();
			logger.info("chkSize==>"+chkSize+" threshHold ==>"+threshHold);
			if(chkSize > threshHold){
				logger.info("Removing item "+iter.toString());
				iter.remove();
				chkSize--;	
			}	
		}

		return col;
	}
	
	private List clearCollectionList(List collectionList,int threshold) {
		// TODO Auto-generated method stub
		List col = new ArrayList();
		int chkSize = collectionList.size();
		Iterator iter = collectionList.iterator();
		while (iter.hasNext()) {
			Object item = iter.next();
			if(chkSize > threshold){
				iter.remove();
				chkSize--;	
			}	
		}

		return col;
	}*/

	@Override
	public void onWriteError(Exception exception, List items) {
		// TODO Auto-generated method stub
		
	}

	
	public ProdMastItemProcessor getProdMastItemProcessor() {
		return prodMastItemProcessor;
	}

	public void setProdMastItemProcessor(ProdMastItemProcessor prodMastItemProcessor) {
		this.prodMastItemProcessor = prodMastItemProcessor;
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
