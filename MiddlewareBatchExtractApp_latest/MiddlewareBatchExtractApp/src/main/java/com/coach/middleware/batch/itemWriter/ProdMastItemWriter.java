package com.coach.middleware.batch.itemWriter;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;

import com.coach.middleware.batch.dao.VO.NightlyOutBoundVO;
import com.coach.middleware.batch.itemProcessor.ProdMastItemProcessor;

public class ProdMastItemWriter implements ItemWriter<NightlyOutBoundVO>{

private static final Logger logger = Logger.getLogger(ProdMastItemWriter.class);
	
	FlatFileItemWriter<NightlyOutBoundVO>  ProdMastFileItemWriter = null;
	
	@SuppressWarnings("unchecked")
	@Override
	public void write(List<? extends NightlyOutBoundVO> items) throws Exception {
		
		if(logger.isDebugEnabled()){
			logger.debug("inside ProdMast Write process ");
		}
		
		try{
			List<? extends NightlyOutBoundVO> item = new ArrayList();
			item = (List<? extends NightlyOutBoundVO>) items.get(0);
				
			ProdMastFileItemWriter.write(item);
			item.clear();
			ProdMastItemProcessor.recordsSkipped.close();
			Runtime.getRuntime().gc();
		}catch(Exception e){
			System.out.println("Exception ==>"+e);
		}
		
		logger.info("ProdMast object size for writing"+items.size());
		
		if(logger.isDebugEnabled()){
			logger.debug("Exits ProdMast Write process ");
		}
	}

	public FlatFileItemWriter<NightlyOutBoundVO> getProdMastFileItemWriter() {
		return ProdMastFileItemWriter;
	}

	public void setProdMastFileItemWriter(
			FlatFileItemWriter<NightlyOutBoundVO> prodMastFileItemWriter) {
		ProdMastFileItemWriter = prodMastFileItemWriter;
	}
	
	

}
