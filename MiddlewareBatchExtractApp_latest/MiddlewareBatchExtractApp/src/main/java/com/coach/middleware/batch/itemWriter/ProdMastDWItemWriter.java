package com.coach.middleware.batch.itemWriter;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;

import com.coach.middleware.batch.dao.VO.NightlyOutBoundVO;
import com.coach.middleware.batch.itemProcessor.ProdMastDWItemProcessor;

public class ProdMastDWItemWriter implements ItemWriter<NightlyOutBoundVO>{

private static final Logger logger = Logger.getLogger(ProdMastDWItemWriter.class);
	
	FlatFileItemWriter<NightlyOutBoundVO>  ProdmastDWFileItemWriter = null;
	
	@SuppressWarnings("unchecked")
	@Override
	public void write(List<? extends NightlyOutBoundVO> items) throws Exception {
		
		if(logger.isDebugEnabled()){
			logger.debug("inside ProdMastDW Write process ");
		}
		
		try{
			List<? extends NightlyOutBoundVO> item = new ArrayList();
			item = (List<? extends NightlyOutBoundVO>) items.get(0);
				
			ProdmastDWFileItemWriter.write(item);
			item.clear();
			ProdMastDWItemProcessor.recordsSkipped.close();
			Runtime.getRuntime().gc();
		}catch(Exception e){
			System.out.println("Exception ==>"+e);
		}
		
		logger.info("ProdMastDW object size for writing"+items.size());
		
		if(logger.isDebugEnabled()){
			logger.debug("Exits ProdMastDW Write process ");
		}
	}

	public FlatFileItemWriter<NightlyOutBoundVO> getProdmastDWFileItemWriter() {
		return ProdmastDWFileItemWriter;
	}

	public void setProdmastDWFileItemWriter(
			FlatFileItemWriter<NightlyOutBoundVO> ProdmastDWFileItemWriter) {
		this.ProdmastDWFileItemWriter = ProdmastDWFileItemWriter;
	}
	
	

}
