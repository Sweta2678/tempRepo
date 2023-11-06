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
import org.springframework.web.multipart.MultipartFile;

import com.tapestry.moic.constant.MoicConstant;
import com.tapestry.moic.controller.interfaces.ISkuChangeInternalController;
import com.tapestry.moic.dto.SkuChangeDto;
import com.tapestry.moic.repository.ChannelRepository;
import com.tapestry.moic.repository.FashionSeasonRepository;
import com.tapestry.moic.response.ErrorResponse;
import com.tapestry.moic.response.SuccessResponse;
import com.tapestry.moic.response.interfaces.MoicResponse;
import com.tapestry.moic.service.interfaces.IFinalizedBuyInternalService;
import com.tapestry.moic.service.interfaces.ISkuChangeInternalService;
import com.tapestry.moic.utils.ExcelHelper;
import com.tapestry.moic.utils.MoicUtil;

@Component
public class SkuChangeInternalController implements ISkuChangeInternalController {

	private Logger LOGGER = LoggerFactory.getLogger(SkuChangeInternalController.class);

	@Autowired
	private ISkuChangeInternalService skuChangeInternalService;

	@Autowired
	private ChannelRepository channelRepository;
		
	@Autowired
	private IFinalizedBuyInternalService finalizedBuyInternalService;
	
	@Autowired
	private FashionSeasonRepository fashionSeasonRepository;

	@Value("${skuchange.report.filename}")
	private String reportFilename;

	@Value("${skuchange.report.SKU.filename}")
	private String findBySkufileName;

	@Value("${skuchange.list.SKU.sheetName}")
	private String skuchangeListSheetName;

	@Value("${skuchange.report.columnname}")
	private String skuChangeheaders;

	@Value("${skuchange.list.columnname}")
	private String skuChangeUploadheaders;

	@Value("${skuchange.report.SKU.columnname}")
	private String SKUheaders;

	@Value("${skuchange.list.filename}")
	private String findUploadfileName;

	@Value("${skuchange.list.filename}")
	private String skuchangeListfileName;

	LocalDateTime myDateObj = LocalDateTime.now();

	DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern(MoicConstant.DDMMYYYYHHMMSS);

	String formattedDate = myDateObj.format(myFormatObj);

	@Override
	public MoicResponse getSKUChangeDefaultData(String season, String channel) {
		LOGGER.debug("Inside Controller method getSKUChangeDefaultData(" + season + ", " + channel + ")");
		Map<String, Object> responseMap = new HashMap<>();

		List<String> channelList = new ArrayList<String>();
		channelList.add(MoicConstant.CORPORATE);
		channelList.addAll(channelRepository.getChannelNames());
		List<String> seasonList = fashionSeasonRepository.getSeasonCodes();

		if (season.isEmpty() && !seasonList.isEmpty())
			season = seasonList.get(0);
		if (channel.isEmpty() && !channelList.isEmpty())
			channel = channelList.get(0);

		Map<String, Object> moqResponse = skuChangeInternalService.getSKUChangeDefaultData(season);
		Map<String, Object> overviewResponseMap = skuChangeInternalService.generateSKUChangeOverviewData(season, channel);

		responseMap.put(MoicConstant.DD_CHANNELS, channelList);
		responseMap.put(MoicConstant.DD_SEASONS, seasonList);
		responseMap.put(MoicConstant.SEASONS_ARRAY, MoicUtil.convertSeasonsListToJson(seasonList));

		responseMap.put(MoicConstant.SKU_CHANGE_DATA, moqResponse);
		responseMap.put(MoicConstant.OVERVIEW_DATA, overviewResponseMap);

		return new SuccessResponse(true, HttpStatus.OK, MoicConstant.SUCCESS_MESSAGE, responseMap);
	}

	@Override
	public MoicResponse updateSKUChange(SkuChangeDto skuChangeDto ,String season,String channel, String token) {
		LOGGER.debug("Inside Controller method updateSKUChange()");

		Boolean isUpdated = skuChangeInternalService.updateSKUChangeData(skuChangeDto, token);

		Map<String, Object> responseMap = new HashMap<>();

		List<String> channelList = new ArrayList<String>();
		channelList.add(MoicConstant.CORPORATE);
		channelList.addAll(channelRepository.getChannelNames());
		List<String> seasonList = fashionSeasonRepository.getSeasonCodes();

		if (season!=null && season.isEmpty())
			season = seasonList.get(0);
		if (channel!=null && channel.isEmpty())
			channel = channelList.get(0);

		Map<String, Object> moqResponse = skuChangeInternalService.getSKUChangeDefaultData(season);
		Map<String, Object> overviewResponseMap = skuChangeInternalService.generateSKUChangeOverviewData(season, channel);

		responseMap.put(MoicConstant.DD_CHANNELS, channelList);
		responseMap.put(MoicConstant.DD_SEASONS, seasonList);

		responseMap.put(MoicConstant.SKU_CHANGE_DATA, moqResponse);
		responseMap.put(MoicConstant.OVERVIEW_DATA, overviewResponseMap);
		
		if (isUpdated)
			return new SuccessResponse(true, HttpStatus.OK, MoicConstant.RECORD_UPDATED_SUCCESSFULLY, responseMap);
		else {

			LOGGER.error(MoicConstant.RECORD_NOT_UPDATED);
			return  new SuccessResponse(true, HttpStatus.BAD_REQUEST, MoicConstant.RECORD_NOT_UPDATED, responseMap);
					
		}
	}


	@Override
	public ResponseEntity<InputStreamResource> downloadSKUChangeReport(List<String> season, String channel) {

		LocalDateTime myDateObj = LocalDateTime.now();

		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern(MoicConstant.DDMMYYYYHHMMSS);

		String formattedDate = myDateObj.format(myFormatObj);
		
		//Below method will populate SKUChangeReport entity
		finalizedBuyInternalService.populateSKUChangeReport();

		String  skuChangereportFilename= reportFilename+"_"+season +"_"+channel +"_"+  formattedDate;
		ByteArrayInputStream in = skuChangeInternalService.downloadSKUChangeReport(season, channel);

		HttpHeaders headers = new HttpHeaders();

		headers.add("Content-Disposition", "attachment; filename=\""+skuChangereportFilename+".xlsx"+"\"");

		return ResponseEntity
				.ok()
				.headers(headers)
				.body(new InputStreamResource(in));
	}

	@Override
	public ResponseEntity<InputStreamResource> downloadSKUChangeList(String season) {

		LocalDateTime myDateObj = LocalDateTime.now();

		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern(MoicConstant.DDMMYYYYHHMMSS);

		String formattedDate = myDateObj.format(myFormatObj);

		String  skuChangereportFilename= skuchangeListfileName+"_"+season +"_"+  formattedDate;

		ByteArrayInputStream in = skuChangeInternalService.downloadSKUChangeList(season);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=" + skuChangereportFilename + formattedDate + ".xlsx");

		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
	}

	@Override
	public MoicResponse uploadSKUChangeList(MultipartFile file, String season, String channel, String token) {
		String message = "";

		String[] skuChangeUploadheadersList = skuChangeUploadheaders.split(",");
		Map<String, Object> responseMap = new HashMap<>();

		List<String> channelList = new ArrayList<String>();
		channelList.add(MoicConstant.CORPORATE);
		channelList.addAll(channelRepository.getChannelNames());
		List<String> seasonList = fashionSeasonRepository.getSeasonCodes();

		if (season!=null && season.isEmpty())
			season = seasonList.get(0);
		if (channel!=null && channel.isEmpty())
			channel = channelList.get(0);

		Map<String, Object> moqResponse = skuChangeInternalService.getSKUChangeDefaultData(season);
		Map<String, Object> overviewResponseMap = skuChangeInternalService.generateSKUChangeOverviewData(season, channel);

		responseMap.put(MoicConstant.DD_CHANNELS, channelList);
		responseMap.put(MoicConstant.DD_SEASONS, seasonList);						
		responseMap.put(MoicConstant.SKU_CHANGE_DATA, moqResponse);
		responseMap.put(MoicConstant.OVERVIEW_DATA, overviewResponseMap);

		if (ExcelHelper.checkFileName(skuchangeListfileName, file)) {
			if (ExcelHelper.hasExcelFormat(file)) {
				LOGGER.debug("Format Matched.........." );
				if (ExcelHelper.checkSheetAndHeaders(skuchangeListSheetName, skuChangeUploadheadersList, file)) {
					LOGGER.debug("Headers Matched..........." + skuChangeUploadheadersList);
					try {
						// NewSKU must column must be Blank When DROP option TRUE 
						List<SkuChangeDto> skuChangeDtoList = skuChangeInternalService.excelToSKUChanges(skuchangeListSheetName, file,skuChangeUploadheadersList.length, token);

						for(SkuChangeDto skuChange : skuChangeDtoList) {							
							if (!skuChange.getNewSKU().isEmpty() && skuChange.getDrop_1()) {
								message = MoicConstant.UPLOAD_DROP_RULE;
								LOGGER.error(MoicConstant.UPLOAD_DROP_RULE);								
								return new SuccessResponse(true, HttpStatus.BAD_REQUEST, message, responseMap);
							}
							
							if ((!skuChange.getLevel().isEmpty() && !skuChange.getLevelId().isEmpty())&& skuChange.getLevel().equalsIgnoreCase(MoicConstant.CORPORATE))
							{
								message = MoicConstant.UPLOAD_DROP_RULE2;
								LOGGER.error(skuChange.getSeason()+MoicConstant.UPLOAD_DROP_RULE2);
								return new SuccessResponse(true, HttpStatus.BAD_REQUEST, message, responseMap);	
							}
							
											
						}
						//End
						String skuChangeUloadResult = skuChangeInternalService.saveSKUChangeUploadExcel(skuChangeDtoList, token);					
						
						return new SuccessResponse(true, HttpStatus.OK, skuChangeUloadResult, responseMap);

					} catch (Exception e) {
						e.printStackTrace();
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

		message = MoicConstant.UPLOAD_WRONG_FILENAME + findUploadfileName;
		return new ErrorResponse(true, HttpStatus.BAD_REQUEST, message);
	}
}
