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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tapestry.moic.constant.MoicConstant;
import com.tapestry.moic.controller.interfaces.IMOQInternalController;
import com.tapestry.moic.dto.MOQDto;
import com.tapestry.moic.repository.ChannelRepository;
import com.tapestry.moic.repository.FashionSeasonRepository;
import com.tapestry.moic.response.ErrorResponse;
import com.tapestry.moic.response.SuccessResponse;
import com.tapestry.moic.response.interfaces.MoicResponse;
import com.tapestry.moic.service.interfaces.IFinalizedBuyInternalService;
import com.tapestry.moic.service.interfaces.IMOQInternalService;
import com.tapestry.moic.utils.ExcelHelper;
import com.tapestry.moic.utils.MoicUtil;

/**
 * The Class MOQInternalController.
 *
 * @version 0.0.1
 */
@Component
public class MOQInternalController implements IMOQInternalController {

	/**
	 * Field LOGGER
	 */
	private Logger LOGGER = LoggerFactory.getLogger(MOQInternalController.class);

	/**
	 * Field IMOQInternalService;
	 */
	@Autowired
	private IMOQInternalService moqInternalService;

	@Autowired
	private ChannelRepository channelRepository;

	@Autowired
	private IFinalizedBuyInternalService finalizedBuyInternalService;
	
	@Autowired
	private FashionSeasonRepository fashionSeasonRepository;

	@Value("${moq.report.filename}")
	String fileName;

	@Value("${moq.upload.filename}")
	String moqFileName;

	@Value("${moq.upload.sheetname}")
	String moqSheetName;

	@Value("${moq.download.list.columnname}")
	String moqHeaders;

	@Value("${prebuy.upload.filename}")
	String preBuyFileName;

	@Value("${prebuy.upload.sheetname}")
	String preBuySheetName;

	@Value("${prebuy.upload.headers}")
	String preBuyHeaders;
	
	@Value("${moq.report.filename}")
	String moqReportfileName;
	
	@Value("${moq.report.preBuyReport.filename}")
	String preBuyfileName;

	@Override
	public MoicResponse getMOQDefaultData(String season, String channel) {
		LOGGER.debug("Inside Controller method getMOQDefaultData(" + season + ", " + channel + ")");
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
		responseMap.put(MoicConstant.MOQ_DATA, moqInternalService.getMOQDefaultData(season, channel));

		responseMap.put(MoicConstant.OVERVIEW_DATA, moqInternalService.generateMOQOverviewData(season, channel));

		return new SuccessResponse(true, HttpStatus.OK, MoicConstant.SUCCESS_MESSAGE, responseMap);
	}

	@Override
	public MoicResponse updateMOQ(MOQDto moqDto,String season, String channel, String token) {
		LOGGER.debug("Inside Controller method updateMOQ()");
		
		Map<String, Object> responseMap = new HashMap<>();
		Map<String, Object> moqResponse;

		// Any back-end validation will done here before invoking service
		Boolean isUpdated = moqInternalService.updateMOQData(moqDto, token);
		moqResponse = moqInternalService.getMOQDefaultData(season, channel);
		moqResponse.put(MoicConstant.MESSAGE, MoicConstant.SUCCESS_MESSAGE);

		responseMap.put(MoicConstant.MOQ_DATA, moqResponse);
		if (isUpdated) 
			return new SuccessResponse(true, HttpStatus.OK, MoicConstant.RECORD_UPDATED_SUCCESSFULLY, responseMap);
		else
			return new SuccessResponse(true, HttpStatus.OK, MoicConstant.NO_RECORDS_FOUND, responseMap);
	}

	@Override
	public MoicResponse uploadMOQList(@RequestParam("file") MultipartFile file, String season, String channel, String token) {
		String message = "";

		String[] moicHeaders = moqHeaders.split(",");

		if (ExcelHelper.checkFileName(moqFileName, file)) {
			if (ExcelHelper.hasExcelFormat(file)) {
				LOGGER.debug(moqSheetName + ": moqSheetName and  Headers :" + moicHeaders);
				if (ExcelHelper.checkSheetAndHeaders(moqSheetName, moicHeaders, file)) {
					
					try {
						String moqResult=moqInternalService.saveMOQ(moqSheetName, file,moicHeaders.length, token);
						Map<String, Object> responseList = moqInternalService.getMOQDefaultData(season,	channel);
						Map<String, Object> responseMap = new HashMap<>();
						responseMap.put(MoicConstant.MOQ_DATA, responseList);											
						
						if (responseList != null && responseList.size() > 0) {
							return new SuccessResponse(true, HttpStatus.OK, moqResult, responseMap);
						} else {
							return new SuccessResponse(true, HttpStatus.OK, moqResult, responseMap);
						}
					
					} catch (Exception e) {

						message = MoicConstant.UPLOAD_FILE_EXCEPTION + file.getOriginalFilename() + ".\n"
								+ e.getMessage();
						return new ErrorResponse(true, HttpStatus.EXPECTATION_FAILED, message);
					}

				} else {
					message = MoicConstant.UPLOAD_WRONG_FILE_HEADER_SHEET_NAME;
					return new ErrorResponse(true, HttpStatus.BAD_REQUEST, message);
				}
			} else {
				message = MoicConstant.UPLOAD_WRONG_FILE_EXTENTION;
				return new ErrorResponse(true, HttpStatus.BAD_REQUEST, message);
			}

		}
		message = MoicConstant.UPLOAD_WRONG_FILENAME + moqFileName;
		return new ErrorResponse(true, HttpStatus.BAD_REQUEST, message);
	}

	@Override
	public MoicResponse uploadPreBuyList(@RequestParam("file") MultipartFile file, String season, String channel, String token) {
		String message = "";
		String[] preBuyHeadersList = preBuyHeaders.split(",");

		if (ExcelHelper.checkFileName(preBuyFileName, file)) {
			if (ExcelHelper.hasExcelFormat(file)) {
				LOGGER.debug("File Format matched.");
				if (ExcelHelper.checkSheetAndHeaders(preBuySheetName, preBuyHeadersList, file)) {
					LOGGER.debug(" Sheet And Headers values Matched.");
					try {
						String savePreBuyResult=moqInternalService.savePreBuy(preBuySheetName, file,preBuyHeadersList.length, token);						
						Map<String, Object> responseList = moqInternalService.getMOQDefaultData(season,	channel);
						
						return new SuccessResponse(true, HttpStatus.OK, savePreBuyResult, responseList);
						
					} catch (Exception e) {

						message = MoicConstant.UPLOAD_FILE_EXCEPTION + file.getOriginalFilename() + ".\n"
								+ e.getMessage();
						return new ErrorResponse(true, HttpStatus.EXPECTATION_FAILED, message);
					}

				} else {
					message = MoicConstant.UPLOAD_WRONG_FILE_HEADER_SHEET_NAME;
					return new ErrorResponse(true, HttpStatus.BAD_REQUEST, message);
				}
			} else {
				message = MoicConstant.UPLOAD_WRONG_FILE_EXTENTION;
				return new ErrorResponse(true, HttpStatus.BAD_REQUEST, message);
			}

		}

		message = MoicConstant.UPLOAD_WRONG_FILENAME + preBuyFileName;
		return new ErrorResponse(true, HttpStatus.BAD_REQUEST, message);
	}

	@Override
	public ResponseEntity<InputStreamResource> downloadMOQList(String season)  {

		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern(MoicConstant.DDMMYYYYHHMMSS);

		String formattedDate = myDateObj.format(myFormatObj);
		String  moqListFileName= moqFileName + "_" + season + "_" + formattedDate;
		
	
		ByteArrayInputStream in = moqInternalService.downloadMoqList(season);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=" + moqListFileName + ".xlsx");
		LOGGER.debug("Download MOQ List generated Successfully .");
		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
	}

	@Override
	public ResponseEntity<InputStreamResource> downloadMOQReport(List<String> season, String channel){
		
		LocalDateTime myDateObj = LocalDateTime.now();

		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern(MoicConstant.DDMMYYYYHHMMSS);

		String formattedDate = myDateObj.format(myFormatObj);

		String fileName = moqReportfileName+"_" + channel + "_" + season + "_" + formattedDate;
		
		//Below method will populate MOQReport entity
		finalizedBuyInternalService.populateMOQReport();

		ByteArrayInputStream in = moqInternalService.moqDownloadBySeasonAndChannel(season, channel);
		HttpHeaders headers = new HttpHeaders();
		
		LOGGER.debug("Download MOQ Report generated Successfully .");

		headers.add("Content-Disposition", "attachment; filename=\"" + fileName + ".xlsx"+"\"");

		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
	}
	
	@Override
	public ResponseEntity<InputStreamResource> downloadPreBuyList() {
		
		LocalDateTime myDateObj = LocalDateTime.now();
	    
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern(MoicConstant.DDMMYYYYHHMMSS);

	    String formattedDate = myDateObj.format(myFormatObj);

	    String preBuyListFileName = preBuyFileName + "_" + formattedDate;
		ByteArrayInputStream in = moqInternalService.downloadAllPreBuyList();
	    
		HttpHeaders headers = new HttpHeaders();
	    
		headers.add("Content-Disposition", "attachment; filename=" + preBuyListFileName + ".xlsx");
	    
	    return ResponseEntity
	                  .ok()
	                  .headers(headers)
	                  .body(new InputStreamResource(in));
	}
}