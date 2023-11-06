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
 * The Interface IOverviewInternalController.
 * 
 * @version 0.0.1
 */

@RestController
@RequestMapping(value = MoicConstant.API_BASE_URL)
public interface IOverviewInternalController {

	@GetMapping(value = MoicConstant.GET_OVERVIEW_DEFAULT_DATA)
	@ResponseBody
	MoicResponse getOverviewDefaultData(@RequestParam(name = "season") String season, @RequestParam(name = "channel") String channel);
	
	@GetMapping(value=MoicConstant.ARCHIVED_DOWNLOAD)
	@ResponseBody
	public ResponseEntity<InputStreamResource> archivedDownload(@RequestParam(name = "season") String season, @RequestParam(name = "channel") String channel);
	
	@GetMapping(value=MoicConstant.ORDER_DOWNLOAD_REPORT)
	@ResponseBody
	public ResponseEntity<InputStreamResource> orderDownloadReport(@RequestParam(name = "season") List<String> season, @RequestParam(name = "channel") String channel);
	
}
