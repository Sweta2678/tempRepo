package com.tapestry.moic.controller.interfaces;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tapestry.moic.constant.MoicConstant;
import com.tapestry.moic.dto.ConfigDto;
import com.tapestry.moic.response.interfaces.MoicResponse;

/**
 * The Interface IConfigInternalController.
 * 
 * @version 0.0.1
 */
@RestController
@RequestMapping(value = MoicConstant.API_BASE_URL)
public interface IConfigInternalController {

	@GetMapping(value = MoicConstant.GET_CONFIG_DEFAULT_DATA)
	@ResponseBody
	MoicResponse getConfigDefaultData();

	@PostMapping(value = MoicConstant.UPDATE_CONFIG_DATA)
	@ResponseBody
	MoicResponse updateConfigData(@RequestBody(required = true) ConfigDto configDto, @RequestParam(name = "token") String token);

	@PostMapping(value = MoicConstant.UPLOAD_CONFIG_EXCEL)
	@ResponseBody
	MoicResponse uploadFile(@RequestParam("file") MultipartFile file, @RequestParam(name = "token") String token);

	@GetMapping(value = MoicConstant.DOWNLOAD_CUSTOMER_REPORT)
	@ResponseBody
	public ResponseEntity<InputStreamResource> downloadCustomerReport();

}
