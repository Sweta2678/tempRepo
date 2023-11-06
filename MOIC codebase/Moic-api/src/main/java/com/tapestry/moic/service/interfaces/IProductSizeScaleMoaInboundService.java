package com.tapestry.moic.service.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tapestry.moic.entity.ProductSizeScaleMoa;
import com.tapestry.moic.inbound.entity.ProductSizeScaleMoaStaging;
import com.tapestry.moic.inbound.entity.key.ProductSizeScaleMoaStagingKeys;

@Service
public interface IProductSizeScaleMoaInboundService{

	List<ProductSizeScaleMoaStaging> getAllProductSizeScaleMoaStaging();
	
	public ProductSizeScaleMoa addProductSizeScaleMoaInof(ProductSizeScaleMoa productSizeScaleMoa);
	
	void deleteProductSizescalemoaInboundById(ProductSizeScaleMoaStagingKeys moaStagingKeys);
}
