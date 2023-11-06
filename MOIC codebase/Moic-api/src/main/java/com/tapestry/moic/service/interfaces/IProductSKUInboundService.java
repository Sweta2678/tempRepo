package com.tapestry.moic.service.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tapestry.moic.entity.ProductSKU;
import com.tapestry.moic.inbound.entity.ProductSkuStaging;

@Service
public interface IProductSKUInboundService {

	List<ProductSkuStaging> getAllProdcutSkuList();
	
	ProductSKU addProductSKUInfo(ProductSKU productSKU);
	
	void deletProductSKUInboundById(String plmUniqueId);
}
