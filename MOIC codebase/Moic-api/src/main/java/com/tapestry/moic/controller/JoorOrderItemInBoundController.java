package com.tapestry.moic.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.tapestry.moic.constant.MoicConstant;
import com.tapestry.moic.controller.interfaces.IJoorOrderItemInBoundController;
import com.tapestry.moic.entity.JoorOrderItem;
import com.tapestry.moic.response.SuccessResponse;
import com.tapestry.moic.response.interfaces.MoicResponse;
import com.tapestry.moic.service.interfaces.IJoorOrderItemInBoundService;

/**
 * The Class JoorOrderItemInBoundController.
 *
 * @version 0.0.1
 */
@Component
public class JoorOrderItemInBoundController implements IJoorOrderItemInBoundController{
	
	/**
	 * Field LOGGER
	 */
	private Logger LOGGER = LoggerFactory.getLogger(JoorOrderItemInBoundController.class);
	
	/**
	 * Field productMasterInBoundService;
	 */
	@Autowired
	private IJoorOrderItemInBoundService joorOrderItemService;
	
	@Override
	public MoicResponse addJoorOrderItemInfo(JoorOrderItem joorOrderItem) {
		LOGGER.debug("inside addProductMasterInfo :: " + joorOrderItem);
		return new SuccessResponse(true, HttpStatus.OK, MoicConstant.SUCCESS_MESSAGE, 
				joorOrderItemService.addJoorOrderItemInfo(joorOrderItem));
	}
	
	@Override
	public MoicResponse getAllJoorOrderItemInfo() {
		LOGGER.debug("inside addProductMasterInfo :: ");
		
		return new SuccessResponse(true, HttpStatus.OK, MoicConstant.SUCCESS_MESSAGE, 
				joorOrderItemService.getAllJoorOrderItemInfo());
	}	
}
