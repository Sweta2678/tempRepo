package com.tapestry.moic.controller.interfaces;

import java.util.List;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tapestry.moic.constant.MoicConstant;
import com.tapestry.moic.response.interfaces.MoicResponse;

/**
 * The Interface IUPCValidationInternalController.
 * 
 * @version 0.0.1
 */

@RestController
@RequestMapping(value = MoicConstant.API_BASE_URL)
public interface IUPCValidationInternalController {

	@GetMapping(value = MoicConstant.GET_UPC_DEFAULT_DATA,headers = "Accept=application/json")
	@ResponseBody
	MoicResponse getUPCValidationDefaultData();

	@GetMapping(value = MoicConstant.DOWNLOAD_UPC_REPORT)
	@ResponseBody
	public ResponseEntity<InputStreamResource>  downloadUPCValidationBySeason(
			@RequestParam(name = "season") List<String> season);


}
