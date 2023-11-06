package com.coach.middleware.batch.itemWriter;
import java.util.*;
 
import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;

import com.coach.middleware.RunBatch;
import com.coach.middleware.batch.dao.VO.LpskuExtractVO;
import com.coach.middleware.batch.dao.VO.TotoExtractVO;
import com.coach.middleware.batch.itemProcessor.LPSKUExtractItemProcessor;
import com.coach.middleware.extractloader.LpskuExtract;
 
public class LPSKUExtractItemWriter implements ItemWriter<LpskuExtractVO> {
	private static final Logger logger = Logger
	.getLogger(LPSKUExtractItemWriter.class);
	
	public List<LpskuExtractVO> lpskuExtractVOList = new ArrayList<LpskuExtractVO>();
	public JdbcBatchItemWriter<LpskuExtractVO> LPSKUExtractDBItemWriter = null;
	public static int threadcount = 0;
	@Override
	public void write(List<? extends LpskuExtractVO> items) throws Exception {
		try{
			
				List<? extends LpskuExtractVO> lst = (List<? extends LpskuExtractVO>) items;
				List<LpskuExtractVO> ls = (List<LpskuExtractVO>)lst.get(0);
				//RunBatch.recordcount = RunBatch.recordcount + ls.size();
				if(LpskuExtract.isparallelprocessong){
					List<LpskuExtractVO> ls1 = new ArrayList();
					for(int i =0;i<ls.size();i++){
						ls1.add((LpskuExtractVO)ls.get(i).clone());
					}
					writerthread write = new writerthread(LPSKUExtractDBItemWriter, ls1);
					while(threadcount>3){
						Thread.sleep(2000);
					}
					write.start();
					threadcount++;		
				}else{
					logger.info("Start Single Threaded Writing");
					
					LPSKUExtractDBItemWriter.write(ls);
					logger.info("Single Threaded Writing Completed ");
				}
				
			}catch(Exception e){
				e.printStackTrace();
				logger.error(e.getMessage());
				
			}
		
			logger.info("Writer Completed .........."+LPSKUExtractItemProcessor.lpskuExtractVOList.size());
		LPSKUExtractItemProcessor.lpskuExtractVOList.clear();
		Runtime.getRuntime().gc();
		
		
	}
//	public List<LpskuExtractVO> getLpskuExtractVOList() {
//		return lpskuExtractVOList;
//	}
//	public void setLpskuExtractVOList(List<LpskuExtractVO> lpskuExtractVOList) {
//		this.lpskuExtractVOList = lpskuExtractVOList;
//	}
	public JdbcBatchItemWriter<LpskuExtractVO> getLPSKUExtractDBItemWriter() {
		return LPSKUExtractDBItemWriter;
	}
	public void setLPSKUExtractDBItemWriter(
			JdbcBatchItemWriter<LpskuExtractVO> lPSKUExtractDBItemWriter) {
		LPSKUExtractDBItemWriter = lPSKUExtractDBItemWriter;
	}
	
	
	

}