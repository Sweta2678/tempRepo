package com.tapestry.moic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tapestry.moic.entity.ProductSizeScaleMoa;
import com.tapestry.moic.inbound.entity.ProductSizeScaleMoaStaging;
import com.tapestry.moic.inbound.entity.key.ProductSizeScaleMoaStagingKeys;
import com.tapestry.moic.inbound.repository.ProductSizeScaleMoaInboundRepository;
import com.tapestry.moic.repository.ProductSizeScaleMoaRepository;
import com.tapestry.moic.service.interfaces.IProductSizeScaleMoaInboundService;

@Component
public class ProductSizeScaleMoaInboundService implements IProductSizeScaleMoaInboundService{

	@Autowired
	ProductSizeScaleMoaInboundRepository sizeScaleMoaInboundRepo;
	
	@Autowired
	ProductSizeScaleMoaRepository sizeScaleMoaRepo;
	
	@Override
	public List<ProductSizeScaleMoaStaging> getAllProductSizeScaleMoaStaging() {
		List<ProductSizeScaleMoaStaging> productSizeScaleMoaStagingsList = sizeScaleMoaInboundRepo.findAll();
		return productSizeScaleMoaStagingsList;
	}

	@Override
	public ProductSizeScaleMoa addProductSizeScaleMoaInof(ProductSizeScaleMoa productSizeScaleMoa) {

		ProductSizeScaleMoa sizeScaleMoa = sizeScaleMoaRepo.save(productSizeScaleMoa);
		return sizeScaleMoa;
	}

	@Override
	public void deleteProductSizescalemoaInboundById(ProductSizeScaleMoaStagingKeys moaStagingKeys) {
		sizeScaleMoaInboundRepo.deleteById(moaStagingKeys);
	}
	
	

}
