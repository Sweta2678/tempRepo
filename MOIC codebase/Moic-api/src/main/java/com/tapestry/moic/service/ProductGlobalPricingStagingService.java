package com.tapestry.moic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tapestry.moic.entity.ProductGlobalPricing;
import com.tapestry.moic.inbound.entity.ProductGlobalPricingStaging;
import com.tapestry.moic.inbound.repository.ProductGlobalPricingInboundRepository;
import com.tapestry.moic.repository.ProductGlobalPricingRepository;
import com.tapestry.moic.service.interfaces.IProductGlobalPricingStagingService;

@Component
public class ProductGlobalPricingStagingService implements IProductGlobalPricingStagingService{

	@Autowired
	ProductGlobalPricingInboundRepository globalPricingInboundRepository;
	
	@Autowired
	ProductGlobalPricingRepository globalPricingRepository;
	
	@Override
	public List<ProductGlobalPricingStaging> getAllProductGlobalPricingStagList() {
		
		List<ProductGlobalPricingStaging> productGlobPricStagList = globalPricingInboundRepository.findAll();
		return productGlobPricStagList;
	}

	@Override
	public ProductGlobalPricing addProductGlobalPricingInfo(ProductGlobalPricing productGlobalPricing) {
		
		ProductGlobalPricing globalPricing = globalPricingRepository.save(productGlobalPricing);
		return globalPricing;
	}

	@Override
	public void deleteProductGlobalPricingInboundById(String plmUniqueID) {

		globalPricingInboundRepository.deleteById(plmUniqueID);
	}
	
	
}
