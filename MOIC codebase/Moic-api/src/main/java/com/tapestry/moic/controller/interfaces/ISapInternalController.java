package com.tapestry.moic.controller.interfaces;

import java.util.List;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tapestry.moic.constant.MoicConstant;
import com.tapestry.moic.response.interfaces.MoicResponse;

/**
 * The Interface ISkuChangeInternalController.
 * 
 * @version 0.0.1
 */

@RestController
@RequestMapping(value = MoicConstant.API_BASE_URL)
public interface ISapInternalController {


	@PostMapping(value = MoicConstant.SAP_UPLOAD_BY_SEASON_AND_CHANNEL)
	@ResponseBody
	public MoicResponse sapUploadBySeasonAndChennel(
			@RequestParam(name = "season") List<String> season, @RequestParam(name = "channel") String channel, @RequestParam(name = "token") String token);
	
	@GetMapping(value = MoicConstant.SAP_UPLOAD_BY_DEFAULT_DATA)
	@ResponseBody	
	MoicResponse getSapDefaultData();
	
	@GetMapping(value=MoicConstant.DOWNLOAD_PREVIEW_S4)
	@ResponseBody
	public ResponseEntity<InputStreamResource> downloadPreviewS4(@RequestParam(name = "season") List<String> season, @RequestParam(name = "channel") String channel);

}
