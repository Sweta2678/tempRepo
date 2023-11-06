package com.tapestry.moic.service.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tapestry.moic.entity.ProductSizeScale;
import com.tapestry.moic.inbound.entity.ProductSizeScaleStaging;

@Service
public interface IProductSizeScaleInboundService {
	
	List<ProductSizeScaleStaging> getAllProductSizeScaleStaging();
	
	public ProductSizeScale addProductSizeScaleInfo(ProductSizeScale productSizeScale);
	
	void deleteProductSizeScaleInboundById(String plmUniqueId);
}
