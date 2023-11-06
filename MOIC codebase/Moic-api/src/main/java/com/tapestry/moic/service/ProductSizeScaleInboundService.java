package com.tapestry.moic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tapestry.moic.entity.ProductSizeScale;
import com.tapestry.moic.inbound.entity.ProductSizeScaleStaging;
import com.tapestry.moic.inbound.repository.ProducSizeScaleInboundRepository;
import com.tapestry.moic.repository.ProductSizeScaleRepository;
import com.tapestry.moic.service.interfaces.IProductSizeScaleInboundService;

@Component
public class ProductSizeScaleInboundService implements IProductSizeScaleInboundService{

	@Autowired
	ProducSizeScaleInboundRepository producSizeScaleInboundRepo;
	
	@Autowired
	ProductSizeScaleRepository productSizeScaleRepo;
	
	@Override
	public List<ProductSizeScaleStaging> getAllProductSizeScaleStaging() {
		
		List<ProductSizeScaleStaging> productSizeScaleStagingsList = producSizeScaleInboundRepo.findAll();
		return productSizeScaleStagingsList;
	}
	
	public ProductSizeScale addProductSizeScaleInfo(ProductSizeScale productSizeScale) {
	 	ProductSizeScale sizeScale = productSizeScaleRepo.save(productSizeScale);
	 	return sizeScale;
	}

	@Override
	public void deleteProductSizeScaleInboundById(String plmUniqueId) {

		producSizeScaleInboundRepo.deleteById(plmUniqueId);
	}
	
	

}
