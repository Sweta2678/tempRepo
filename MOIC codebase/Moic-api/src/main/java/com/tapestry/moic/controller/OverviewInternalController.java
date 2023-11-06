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
import com.tapestry.moic.controller.interfaces.IOverviewInternalController;
import com.tapestry.moic.repository.ChannelRepository;
import com.tapestry.moic.repository.FashionSeasonRepository;
import com.tapestry.moic.response.SuccessResponse;
import com.tapestry.moic.response.interfaces.MoicResponse;
import com.tapestry.moic.service.interfaces.IOverviewInternalService;
import com.tapestry.moic.utils.MoicUtil;

/**
 * The Class OverviewInternalController.
 *
 * @version 0.0.1
 */
@Component
public class OverviewInternalController implements IOverviewInternalController {

	/**
	 * Field LOGGER
	 */
	private Logger LOGGER = LoggerFactory.getLogger(OverviewInternalController.class);

	@Autowired
	private ChannelRepository channelRepository;

	@Autowired
	private IOverviewInternalService overviewInternalService;
	
	@Autowired
	private FashionSeasonRepository fashionSeasonRepository;

	@Value("${archived.download.filename}")
	String archivedFileName;
	
	@Value("${order.download.filename}")
	String orderFileName;


	@Value("${sap.report.columnname}")
	String sapScreenLabels;

	@Override
	public MoicResponse getOverviewDefaultData(String season, String channel) {
		LOGGER.debug("Inside Controller method getOverviewDefaultData(" + season + ", " + channel + ")");
		Map<String, Object> responseMap = new HashMap<>();

		List<String> channelList = new ArrayList<String>();
		channelList.add(MoicConstant.CORPORATE);
		channelList.addAll(channelRepository.getChannelNames());
		List<String> seasonList = fashionSeasonRepository.getSeasonCodes();
		
		if (season.isEmpty() && !seasonList.isEmpty())
			season = seasonList.get(0);
		if (channel.isEmpty() && !channelList.isEmpty())
			channel = channelList.get(0);

		responseMap.put(MoicConstant.DD_CHANNELS, channelList);
		responseMap.put(MoicConstant.DD_SEASONS, seasonList);
		responseMap.put(MoicConstant.SEASONS_ARRAY, MoicUtil.convertSeasonsListToJson(seasonList));

		responseMap.put(MoicConstant.OVERVIEW_DATA, overviewInternalService.getOverviewDefaultData(season, channel));

		return new SuccessResponse(true, HttpStatus.OK, MoicConstant.SUCCESS_MESSAGE, responseMap);
	}

	@Override
	public ResponseEntity<InputStreamResource> archivedDownload(String season, String channel) {
		
		LocalDateTime myDateObj = LocalDateTime.now();

		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern(MoicConstant.DDMMYYYYHHMMSS);

		String formattedDate = myDateObj.format(myFormatObj);

		String fileName = archivedFileName+ "_" +season + "_" +channel  + "_" + formattedDate;

		ByteArrayInputStream in = overviewInternalService.finalizedBuyArchivedDownload(season, channel);
		HttpHeaders headers = new HttpHeaders();

		if (in != null) {
			LOGGER.debug("Inside Controller method archivedDownload() :Data found");

			headers.add("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");

			return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
		} else {
			LOGGER.debug("Inside Controller method archivedDownload() :Data not found");
			return new ResponseEntity(headers, HttpStatus.NOT_FOUND);

		}

	}

	@Override
	public ResponseEntity<InputStreamResource> orderDownloadReport(List<String> season, String channel) {
		LocalDateTime myDateObj = LocalDateTime.now();

		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern(MoicConstant.DDMMYYYYHHMMSS);

		String formattedDate = myDateObj.format(myFormatObj);
				
		String fileName = orderFileName+ "_" +season + "_" +channel  + "_" + formattedDate;
		ByteArrayInputStream in = overviewInternalService.orderDownloadReport(season, channel);
		HttpHeaders headers = new HttpHeaders();

		if (in != null) {
			LOGGER.debug("Inside Controller method orderDownloadReport() :Data found");

			headers.add("Content-Disposition", "attachment; filename=\"" + fileName + ".xlsx"+ "\"");

			return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
		}
		LOGGER.debug("Inside Controller method orderDownloadReport() :Data not found");
		return new ResponseEntity(headers, HttpStatus.NOT_FOUND);

	}
}
