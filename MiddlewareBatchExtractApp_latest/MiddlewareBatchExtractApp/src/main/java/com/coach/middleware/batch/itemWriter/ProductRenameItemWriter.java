package com.coach.middleware.batch.itemWriter;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.coach.middleware.batch.dao.VO.NightlyOutBoundVO;
import com.coach.middleware.batch.dao.VO.ProductRenameVo;
import com.coach.middleware.batch.itemProcessor.ProdMastDWItemProcessor;

public class ProductRenameItemWriter implements ItemWriter<ProductRenameVo> {
	private static final Logger logger = Logger.getLogger(ProductRenameItemWriter.class);
	

	FlatFileItemWriter<ProductRenameVo>  productRenameFileItemWriter = null;

	@Override
	public void write(List<? extends ProductRenameVo> items) throws Exception {
		
		if(logger.isDebugEnabled()){
			logger.debug("inside ProdMastDW Write process ");
		}
		
		try{
			List<? extends ProductRenameVo> item = new ArrayList();
			item = (List<? extends ProductRenameVo>) items.get(0);
			
			
				productRenameFileItemWriter.write(items);
	
			Runtime.getRuntime().gc();
		}catch(Exception e){
			System.out.println("Exception ==>"+e);
		}
		
		logger.info("ProdMastDW object size for writing"+items.size());
		
		if(logger.isDebugEnabled()){
			logger.debug("Exits ProdMastDW Write process ");
		}
	}

	/**
	 * @return the productRenameFileItemWriter
	 */
	public FlatFileItemWriter<ProductRenameVo> getProductRenameFileItemWriter() {
		return productRenameFileItemWriter;
	}

	/**
	 * @param productRenameFileItemWriter the productRenameFileItemWriter to set
	 */
	public void setProductRenameFileItemWriter(
			FlatFileItemWriter<ProductRenameVo> productRenameFileItemWriter) {
		this.productRenameFileItemWriter = productRenameFileItemWriter;
	}
	
	

}
