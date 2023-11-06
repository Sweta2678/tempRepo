package com.tapestry.moic.controller.interfaces;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tapestry.moic.constant.MoicConstant;
import com.tapestry.moic.entity.JoorOrder;
import com.tapestry.moic.response.interfaces.MoicResponse;

/**
 * The Interface IJoorOrderInBoundController.
 * 
 * @version 0.0.1
 */
@RestController
@RequestMapping(value = MoicConstant.API_BASE_URL)
public interface IJoorOrderInBoundController {
	
	@PostMapping(value = MoicConstant.ADD_JOOR_ORDER_INFO, headers="Accept=application/json")
	@ResponseBody
	MoicResponse addJoorOrder(@RequestBody(required = true) JoorOrder joorOrder);
	
	@GetMapping(value = MoicConstant.GET_ALL_JOOR_ORDER_INFO)
	@ResponseBody
	MoicResponse getAllJoorOrder();
	
	@GetMapping(value = MoicConstant.GET_SPECIFIC_JOOR_ORDER_INFO)
	@ResponseBody
	MoicResponse getSpecificJoorOrder(@PathVariable("orderID") int orderID);
}

