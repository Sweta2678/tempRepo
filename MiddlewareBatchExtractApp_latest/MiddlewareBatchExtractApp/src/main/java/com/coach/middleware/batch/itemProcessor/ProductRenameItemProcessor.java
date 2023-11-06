package com.coach.middleware.batch.itemProcessor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemProcessor;

import com.coach.middleware.batch.dao.VO.NightlyOutBoundVO;
import com.coach.middleware.batch.dao.VO.ProductRenameVo;

public class ProductRenameItemProcessor implements ItemProcessor<ProductRenameVo,ProductRenameVo> {

	@Override
	public ProductRenameVo process(ProductRenameVo renameRecord) throws Exception {
		List<ProductRenameVo> productRenameRecords = new ArrayList<ProductRenameVo>();
		productRenameRecords.add(renameRecord);		
		return renameRecord;
	}

	
	
	
}
