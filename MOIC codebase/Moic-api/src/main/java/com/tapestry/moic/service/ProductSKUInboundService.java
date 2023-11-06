package com.tapestry.moic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tapestry.moic.entity.ProductSKU;
import com.tapestry.moic.inbound.entity.ProductSkuStaging;
import com.tapestry.moic.inbound.repository.ProductSkuInboundRepository;
import com.tapestry.moic.repository.ProductSKURepository;
import com.tapestry.moic.service.interfaces.IProductSKUInboundService;

@Component
public class ProductSKUInboundService implements IProductSKUInboundService{

	@Autowired
	ProductSkuInboundRepository productSkuInboundRepo;
	
	@Autowired
	ProductSKURepository productSKURepo;
	
	@Override
	public List<ProductSkuStaging> getAllProdcutSkuList() {
		
		List<ProductSkuStaging> productSkuStagingList =  productSkuInboundRepo.findAll();
		return productSkuStagingList;
	}

	public ProductSKU addProductSKUInfo(ProductSKU productSKU) {
		ProductSKU SKU = productSKURepo.save(productSKU);
		return SKU;
	}

	@Override
	public void deletProductSKUInboundById(String SKUId) {

		productSkuInboundRepo.deleteById(SKUId);
	}
	
	
}