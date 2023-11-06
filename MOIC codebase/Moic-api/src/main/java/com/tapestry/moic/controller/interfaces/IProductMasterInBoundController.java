package com.tapestry.moic.controller.interfaces;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tapestry.moic.constant.MoicConstant;
import com.tapestry.moic.entity.ProductMaster;
import com.tapestry.moic.response.interfaces.MoicResponse;

/**
 * The Interface IProductMasterInBoundController.
 * 
 * @version 0.0.1
 */
@RestController
@RequestMapping(value = MoicConstant.API_BASE_URL)
public interface IProductMasterInBoundController {
	
	@PostMapping(value = MoicConstant.ADD_PRODUCT_MASTER_INFO, headers="Accept=application/json")
	@ResponseBody
	MoicResponse addProductMasterInfo(@RequestBody(required = true) ProductMaster productMaster);
}
