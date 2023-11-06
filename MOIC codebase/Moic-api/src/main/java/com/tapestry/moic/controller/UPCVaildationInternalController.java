package com.tapestry.moic.controller;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.tapestry.moic.constant.MoicConstant;
import com.tapestry.moic.controller.interfaces.IUPCValidationInternalController;
import com.tapestry.moic.repository.FashionSeasonRepository;
import com.tapestry.moic.response.SuccessResponse;
import com.tapestry.moic.response.interfaces.MoicResponse;
import com.tapestry.moic.service.interfaces.IUPCValidationService;
import com.tapestry.moic.utils.MoicUtil;

@Component
public class UPCVaildationInternalController implements IUPCValidationInternalController {

	private Logger LOGGER = LoggerFactory.getLogger(UPCVaildationInternalController.class);

	@Autowired
	private IUPCValidationService upcValidationService;
	
	@Autowired
	private FashionSeasonRepository fashionSeasonRepository;

	@Value("${upc.download.filename}")
	String fileName;

	@Override
	public ResponseEntity<InputStreamResource> downloadUPCValidationBySeason(List<String> season) {
		LOGGER.debug("Inside Controller method downloadUPCValidationBySeason()");
		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern(MoicConstant.DDMMYYYYHHMMSS);

		String formattedDate = myDateObj.format(myFormatObj);
		String upcFileName = fileName + "_" + season + "_" + formattedDate;

		ByteArrayInputStream in = upcValidationService.downloadUPCValidationBySeason(season);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=\"" + upcFileName + ".xlsx"+"\"");

		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
	}

	@Override
	public MoicResponse getUPCValidationDefaultData() {
		LOGGER.debug("Inside Controller method getSKUChangeDefaultData()");
		Map<String, Object> responseMap = new HashMap<>();
		List<String> seasonList = fashionSeasonRepository.getSeasonCodes();
		responseMap.put(MoicConstant.DD_SEASONS, seasonList);
		responseMap.put(MoicConstant.SEASONS_ARRAY, MoicUtil.convertSeasonsListToJson(seasonList));

		return new SuccessResponse(true, HttpStatus.OK, MoicConstant.SUCCESS_MESSAGE, responseMap);
	}
}