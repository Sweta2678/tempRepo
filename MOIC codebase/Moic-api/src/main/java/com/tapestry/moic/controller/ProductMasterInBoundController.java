package com.tapestry.moic.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.tapestry.moic.constant.MoicConstant;
import com.tapestry.moic.controller.interfaces.IProductMasterInBoundController;
import com.tapestry.moic.entity.ProductMaster;
import com.tapestry.moic.response.SuccessResponse;
import com.tapestry.moic.response.interfaces.MoicResponse;
import com.tapestry.moic.service.interfaces.IProductMasterInBoundService;

/**
 * The Class ProductMasterInBoundController.
 *
 * @version 0.0.1
 */
@Component
public class ProductMasterInBoundController implements IProductMasterInBoundController{
	
	/**
	 * Field LOGGER
	 */
	private Logger LOGGER = LoggerFactory.getLogger(ProductMasterInBoundController.class);
	
	/**
	 * Field productMasterInBoundService;
	 */
	@Autowired
	private IProductMasterInBoundService productMasterInBoundService;
	
	@Override
	public MoicResponse addProductMasterInfo(ProductMaster productMaster) {
		LOGGER.debug("inside addProductMasterInfo :: " + productMaster);
		return new SuccessResponse(true, HttpStatus.OK, MoicConstant.SUCCESS_MESSAGE, 
				productMasterInBoundService.addProductMasterInfo(productMaster));
	}
	
	
}
