package com.tapestry.moic.service.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tapestry.moic.entity.ProductMaster;
import com.tapestry.moic.inbound.entity.ProductMasterStaging;

/**
 * The Interface IProductMasterInBoundService.
 * 
 * @version 0.0.1
 * 
 */
@Service
public interface IProductMasterInBoundService {
	
	ProductMaster addProductMasterInfo(ProductMaster productMaster);
	
	List<ProductMasterStaging> getAllProductMaster();
	
	void deleteProductMasterInboundById(String plmUniqueID);
	
}
