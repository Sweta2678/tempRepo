package com.tapestry.moic.service.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tapestry.moic.entity.ProductGlobalPricing;
import com.tapestry.moic.inbound.entity.ProductGlobalPricingStaging;

@Service
public interface IProductGlobalPricingStagingService {

	List<ProductGlobalPricingStaging> getAllProductGlobalPricingStagList();
	
	public ProductGlobalPricing addProductGlobalPricingInfo(ProductGlobalPricing productGlobalPricing);
	
	void deleteProductGlobalPricingInboundById(String plmUniqueID);
	
}
