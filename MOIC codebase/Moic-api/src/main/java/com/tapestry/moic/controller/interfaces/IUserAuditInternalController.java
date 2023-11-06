package com.tapestry.moic.controller.interfaces;

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
 * The IUserAuditInternalController.
 * 
 * @version 0.0.1
 */

@RestController
@RequestMapping(value = MoicConstant.API_BASE_URL)
public interface IUserAuditInternalController {


	@GetMapping(value = MoicConstant.DOWNLOAD_USER_AUDIT_REPORT)
	@ResponseBody
	public ResponseEntity<InputStreamResource>  downloadUserAuditReport(
			@RequestParam(name = "userID") String userId,@RequestParam(name = "fromdate") String fromdate,@RequestParam(name = "todate") String todate);
	
	@GetMapping(value = MoicConstant.USER_AUDIT_DEFAULT_DATA)
	@ResponseBody
	public MoicResponse getUserAuditDefaultData();

}
