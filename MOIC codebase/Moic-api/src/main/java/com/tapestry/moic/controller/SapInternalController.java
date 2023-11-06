package com.tapestry.moic.controller;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
import com.tapestry.moic.controller.interfaces.ISapInternalController;
import com.tapestry.moic.entity.FinalizedBuy;
import com.tapestry.moic.repository.ChannelRepository;
import com.tapestry.moic.repository.FashionSeasonRepository;
import com.tapestry.moic.response.SuccessResponse;
import com.tapestry.moic.response.interfaces.MoicResponse;
import com.tapestry.moic.service.interfaces.IFinalizedBuyInternalService;
import com.tapestry.moic.service.interfaces.ISapInternalService;
import com.tapestry.moic.utils.MoicUtil;


@Component
public class SapInternalController implements ISapInternalController{

	private Logger LOGGER = LoggerFactory.getLogger(SapInternalController.class);

	@Autowired 
	private ISapInternalService sapInternalService;
	@Autowired
	private ChannelRepository channelRepository;

	@Autowired
	private IFinalizedBuyInternalService finalizedBuyInternalService;
	
	@Autowired
	private FashionSeasonRepository fashionSeasonRepository;
	
	@Value("${sap.report.columnname}")
	String sapScreenLabels;
	
	@Value("${sap.preview.report.name}")
	String previewReportName;
	
	@Override
	public MoicResponse getSapDefaultData() {
		LOGGER.debug("Inside Controller method getSapDefaultData()");
		Map<String, Object> responseMap = new HashMap<>();
		
		//Below method will populate FinalizedBuy entity
		finalizedBuyInternalService.filterAndPopulateFinalizedBuy();
		
		List<String> channelList = new ArrayList<String>();
		channelList.add(MoicConstant.CORPORATE);
		channelList.addAll(channelRepository.getChannelNames());
		List<String> seasonList = fashionSeasonRepository.getSeasonCodes();
		
		responseMap.put(MoicConstant.DD_CHANNELS, channelList);
		responseMap.put(MoicConstant.DD_SEASONS, seasonList);
		responseMap.put(MoicConstant.SEASONS_ARRAY, MoicUtil.convertSeasonsListToJson(seasonList));
		return new SuccessResponse(true, HttpStatus.OK, MoicConstant.SUCCESS_MESSAGE, responseMap);
		
	}

	@Override
	public MoicResponse sapUploadBySeasonAndChennel(List<String> season,String channel, String token) {

		LOGGER.debug("Inside Controller method sapUploadBySeasonAndChennel()");
		Map<String, Object> responseMap = new HashMap<>();
		List<FinalizedBuy> finalizedBuyList=sapInternalService.sapUploadBySeasonAndChennel(season, channel, token);
		List<String> sapLabelsList = MoicUtil.getScreenLabelList(sapScreenLabels);

		responseMap.put(MoicConstant.COLUMNS, sapLabelsList);
		responseMap.put(MoicConstant.DATA, finalizedBuyList);

		if (finalizedBuyList != null && finalizedBuyList.size() > 0) {
			return new SuccessResponse(true, HttpStatus.OK, MoicConstant.SUCCESS_MESSAGE, responseMap);
		} else {
			return new SuccessResponse(true, HttpStatus.OK, MoicConstant.NO_RECORDS_FOUND, null);
		}
	}

	@Override
	public ResponseEntity<InputStreamResource> downloadPreviewS4(List<String> season, String channel) {
		LocalDateTime myDateObj = LocalDateTime.now();

		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern(MoicConstant.DDMMYYYYHHMMSS);

		String formattedDate = myDateObj.format(myFormatObj);

		String fileName = previewReportName + "_" +season + "_" + channel  + "_" + formattedDate;

		ByteArrayInputStream in = sapInternalService.downloadPreviewS4(season, channel);
		HttpHeaders headers = new HttpHeaders();

		if (in != null) {
			LOGGER.debug("Inside Controller method downloadPreviewS4() :File metadata found");

			headers.add("Content-Disposition", "attachment; filename=\"" + fileName + ".xlsx"+"\"");

			return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
		} else {
			LOGGER.debug("Inside Controller method downloadPreviewS4() :File metadata not found");
			return new ResponseEntity(headers, HttpStatus.NOT_FOUND);
		}
	}
}
