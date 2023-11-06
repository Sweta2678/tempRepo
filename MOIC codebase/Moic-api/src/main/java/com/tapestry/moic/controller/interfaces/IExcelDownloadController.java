package com.tapestry.moic.controller.interfaces;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tapestry.moic.constant.MoicConstant;


/**
 * The Interface IExcelDownloadController.
 * 
 * @version 0.0.1
 */
@RestController
@RequestMapping(value = MoicConstant.API_BASE_URL)
public interface IExcelDownloadController {
	
		
	@GetMapping(value="/downloadExcel")
	@ResponseBody
	public ResponseEntity<InputStreamResource> downloadExcel();
}
