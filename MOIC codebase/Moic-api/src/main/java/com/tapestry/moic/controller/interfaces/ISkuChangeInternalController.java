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
import com.tapestry.moic.dto.SkuChangeDto;
import com.tapestry.moic.response.interfaces.MoicResponse;

/**
 * The Interface ISkuChangeInternalController.
 * 
 * @version 0.0.1
 */

@RestController
@RequestMapping(value = MoicConstant.API_BASE_URL)
public interface ISkuChangeInternalController {

	@GetMapping(value = MoicConstant.GET_SKUCHANGE_DEFAULT_DATA,headers = "Accept=application/json")
	@ResponseBody
	MoicResponse getSKUChangeDefaultData(@RequestParam(name = "season") String season,
			@RequestParam(name = "channel") String channel);

	@PostMapping(value = MoicConstant.UPLOAD_SKUCHNAGE_EXCEL)
	@ResponseBody
	MoicResponse uploadSKUChangeList(@RequestParam("file") MultipartFile file,@RequestParam(name = "season") String season,
			@RequestParam(name = "channel") String channel, @RequestParam(name = "token") String token);

	@PostMapping(value = MoicConstant.UPDATE_SKUCHANGE_DATA)
	@ResponseBody
	MoicResponse updateSKUChange(@RequestBody(required = true) SkuChangeDto skuChangeDto,@RequestParam(name = "season") String season,
			@RequestParam(name = "channel") String channel, @RequestParam(name = "token") String token);
	
	@GetMapping(value = MoicConstant.DOWNLOAD_SKU_CHANGE_REPORT)
	@ResponseBody
	public ResponseEntity<InputStreamResource> downloadSKUChangeReport(@RequestParam(name = "season") List<String> season,
			@RequestParam(name = "channel") String channel);

	@GetMapping(value = MoicConstant.DOWNLOAD_SKU_CHANGE_LIST)
	@ResponseBody
	public ResponseEntity<InputStreamResource> downloadSKUChangeList(@RequestParam(name = "season") String season);


}
