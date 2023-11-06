package com.tapestry.moic.controller.interfaces;

import java.util.List;

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
import com.tapestry.moic.dto.MOQDto;
import com.tapestry.moic.response.interfaces.MoicResponse;

/**
 * The Interface IMOQInternalController.
 * 
 * @version 0.0.1
 */

@RestController
@RequestMapping(value = MoicConstant.API_BASE_URL)
public interface IMOQInternalController {

	@GetMapping(value = MoicConstant.GET_MOQ_DEFAULT_DATA)
	@ResponseBody
	MoicResponse getMOQDefaultData(@RequestParam(name = "season") String season,
			@RequestParam(name = "channel") String channel);

	@PostMapping(value = MoicConstant.UPDATE_MOQ_DATA)
	@ResponseBody
	MoicResponse updateMOQ(@RequestBody(required = true) MOQDto moqDto,@RequestParam(name = "season") String season,
			@RequestParam(name = "channel") String channel, @RequestParam(name = "token") String token);
	
	@PostMapping(value = MoicConstant.UPLOAD_MOQ_LIST)
	@ResponseBody
	MoicResponse uploadMOQList(@RequestParam("file") MultipartFile file,@RequestParam(name = "season") String season,
			@RequestParam(name = "channel") String channel, @RequestParam(name = "token") String token);

	@PostMapping(value = MoicConstant.UPLOAD_PREBUY_LIST)
	@ResponseBody
	MoicResponse uploadPreBuyList(@RequestParam("file") MultipartFile file,@RequestParam(name = "season") String season,
			@RequestParam(name = "channel") String channel, @RequestParam(name = "token") String token);
	@GetMapping(value=MoicConstant.DOWNLOAD_PREBUY_LIST)
	@ResponseBody
	public ResponseEntity<InputStreamResource> downloadPreBuyList();
	
	@GetMapping(value = MoicConstant.DOWNLOAD_MOQ_LIST)
	@ResponseBody
	public ResponseEntity<InputStreamResource> downloadMOQList(@RequestParam(name = "season") String season);

	@GetMapping(value = MoicConstant.MOQ_REPORT_DOWNLOAD)
	@ResponseBody
	public ResponseEntity<InputStreamResource> downloadMOQReport(
			@RequestParam(name = "season") List<String> season, @RequestParam(name = "channel") String channel);

}
