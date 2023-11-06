package com.tapestry.moic.jobs.controller.interfaces;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tapestry.moic.constant.MoicConstant;
import com.tapestry.moic.response.interfaces.MoicResponse;

@RestController
@RequestMapping(value = MoicConstant.API_BASE_URL)
public interface IProcessProductMasterController {

	@GetMapping(value = MoicConstant.PROCESS_PRODUCT_MASTER_STAGING)
	@ResponseBody
	MoicResponse processProductMasterStaging();
}
