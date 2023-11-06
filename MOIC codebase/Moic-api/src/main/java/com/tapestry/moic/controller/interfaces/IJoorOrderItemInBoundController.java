package com.tapestry.moic.controller.interfaces;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tapestry.moic.constant.MoicConstant;
import com.tapestry.moic.entity.JoorOrderItem;
import com.tapestry.moic.response.interfaces.MoicResponse;

/**
 * The Interface IJoorOrderItemInBoundController.
 * 
 * @version 0.0.1
 */
@RestController
@RequestMapping(value = MoicConstant.API_BASE_URL)
public interface IJoorOrderItemInBoundController {
	
	@PostMapping(value = MoicConstant.ADD_JOOR_ORDER_ITEM_INFO, headers="Accept=application/json")
	@ResponseBody
	MoicResponse addJoorOrderItemInfo(@RequestBody(required = true) JoorOrderItem joorOrderItem);
	
	@GetMapping(value = MoicConstant.GET_ALL_JOOR_ORDER_ITEM_INFO)
	@ResponseBody
	MoicResponse getAllJoorOrderItemInfo();
	
}