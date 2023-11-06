package com.tapestry.moic.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.tapestry.moic.constant.MoicConstant;
import com.tapestry.moic.controller.interfaces.IJoorOrderInBoundController;
import com.tapestry.moic.entity.JoorOrder;
import com.tapestry.moic.response.SuccessResponse;
import com.tapestry.moic.response.interfaces.MoicResponse;
import com.tapestry.moic.service.interfaces.IJoorOrderInBoundService;

/**
 * The Class JoorOrderInBoundController.
 *
 * @version 0.0.1
 */
@Component
public class JoorOrderInBoundController implements IJoorOrderInBoundController{
	
	/**
	 * Field LOGGER
	 */
	private Logger LOGGER = LoggerFactory.getLogger(JoorOrderInBoundController.class);
	
	/**
	 * Field joorOrderInBoundService;
	 */
	@Autowired
	private IJoorOrderInBoundService joorOrderInBoundService;
	
	@Override
	public MoicResponse addJoorOrder(JoorOrder joorOrder) {
		LOGGER.debug("inside addProductColorInfo :: " + joorOrder);
		return new SuccessResponse(true, HttpStatus.OK, MoicConstant.SUCCESS_MESSAGE, 
				joorOrderInBoundService.addJoorOrder(joorOrder));
	}
	
	@Override
	public MoicResponse getAllJoorOrder() {
		return new SuccessResponse(true, HttpStatus.OK, MoicConstant.SUCCESS_MESSAGE, 
				joorOrderInBoundService.getAllJoorOrder());
	}
	
	@Override
	public MoicResponse getSpecificJoorOrder(int orderID) {
		System.out.println("orderid is"+orderID);
		return new SuccessResponse(true, HttpStatus.OK, MoicConstant.SUCCESS_MESSAGE, 
				joorOrderInBoundService.getSpecificJoorOrder(orderID));
	}
	
}