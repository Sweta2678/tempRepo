package com.tapestry.moic.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tapestry.moic.entity.ProductMaster;
import com.tapestry.moic.inbound.entity.ProductMasterStaging;
import com.tapestry.moic.inbound.repository.ProductMasterInboundRepository;
import com.tapestry.moic.repository.ProductMasterRepository;
import com.tapestry.moic.service.interfaces.IProductMasterInBoundService;

/**
 * The Class ProductMasterInBoundService.
 * 
 * @version 0.0.1
 * 
 */
@Component
public class ProductMasterInBoundService implements IProductMasterInBoundService{

	/**
	 * Field LOGGER
	 */
	private Logger LOGGER = LoggerFactory.getLogger(ProductMasterInBoundService.class);
	
	/**
	 * Field productMasterRepository;
	 */
	@Autowired
	private ProductMasterRepository productMasterRepository;
	
	@Autowired
	private ProductMasterInboundRepository productMasterInboundRepo;
	
	@Override
	public ProductMaster addProductMasterInfo(ProductMaster productMaster) {
		LOGGER.debug("inside addProductMasterInfo ");
		if(productMaster != null) {
			return productMasterRepository.save(productMaster);
		}
		return productMaster;
	}

	@Override
	public List<ProductMasterStaging> getAllProductMaster() {
		return productMasterInboundRepo.findAll();
	}

	@Override
	public void deleteProductMasterInboundById(String plmUniqueId) {

		productMasterInboundRepo.deleteById(plmUniqueId);
	}

	
}
