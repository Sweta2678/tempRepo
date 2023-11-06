package com.tapestry.moic.controller;

import java.io.ByteArrayInputStream;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
import com.tapestry.moic.controller.interfaces.IConfigInternalController;
import com.tapestry.moic.dto.ConfigDto;
import com.tapestry.moic.entity.CurrencyConversion;
import com.tapestry.moic.repository.ChannelRepository;
import com.tapestry.moic.repository.CurrencyConversionRepository;
import com.tapestry.moic.repository.SegmentRepository;
import com.tapestry.moic.response.ErrorResponse;
import com.tapestry.moic.response.SuccessResponse;
import com.tapestry.moic.response.interfaces.MoicResponse;
import com.tapestry.moic.service.interfaces.IConfigInternalService;
import com.tapestry.moic.utils.ExcelHelper;
import com.tapestry.moic.utils.MoicUtil;

/**
 * The Class ConfigInternalController.
 *
 * @version 0.0.1
 */
@Component
public class ConfigInternalController implements IConfigInternalController {

	/**
	 * Field LOGGER
	 */
	private Logger logger = LoggerFactory.getLogger(ConfigInternalController.class);

	@Autowired
	private IConfigInternalService configInternalService;

	@Autowired
	private ChannelRepository channelRepository;

	@Autowired
	private SegmentRepository segmentRepository;
	
	@Autowired
	CurrencyConversionRepository currencyConversionRepository;

	@Value("${config.report.customerReport.filename}")
	String configFileName;
	@Value("${config.report.customerReport.columnname}")
	String configHeader;
	@Value("${config.upload.customerReport.sheetname}")
	String configSheetName;
	@Value("${configLabels}")
	String configScreenLabels;	
	@Value("${config.report.customerReport.filename}")
	String fileName;

	@Override
	public MoicResponse getConfigDefaultData() {
		logger.debug("Inside Controller method getConfigDefaultData()");
		Map<String, Object> responseMap = new HashMap<>();
		Map<String, String>  moicDisplaySuggestedRetailPriceMap = new TreeMap<>(Collections.reverseOrder());
		Map<String, String> moicDisplayWholesalePriceMap = new TreeMap<>(Collections.reverseOrder());
		List<ConfigDto> responseList = configInternalService.getConfigDefaultData();
		List<String> configLabelsList = MoicUtil.getScreenLabelList(configScreenLabels);
		
		List<CurrencyConversion> currencyConversionList = currencyConversionRepository.findAll();
		for(CurrencyConversion currencyConversion: currencyConversionList) {
			String currencyCode = currencyConversion.getCurrencyCode();
			String joorPriceLabel = currencyConversion.getJoorPriceLabel();
			
			if(currencyConversion.getMoicDisplaySuggestedRetailPrice() !=null) {
				String suggestedRetailPrice = currencyConversion.getMoicDisplaySuggestedRetailPrice().trim();
				logger.debug(MessageFormat.format("****SuggestedRetailPrice value {0}", suggestedRetailPrice));
				moicDisplaySuggestedRetailPriceMap.put(currencyCode + "-" + joorPriceLabel, suggestedRetailPrice);		
			}
						
			if(currencyConversion.getMoicDisplayWholesalePrice() !=null) {
				String wholesalePrice = currencyConversion.getMoicDisplayWholesalePrice().trim();
				logger.debug(MessageFormat.format("****WholesalePrice value {0}", wholesalePrice));
				moicDisplayWholesalePriceMap.put(currencyCode + "-" + joorPriceLabel, wholesalePrice);			
			}
		}

		responseMap.put(MoicConstant.COLUMNS, configLabelsList);
		responseMap.put(MoicConstant.DATA, responseList);
		responseMap.put(MoicConstant.DD_CHANNELS, channelRepository.getChannelNames());
		responseMap.put(MoicConstant.DD_SEGMENTS, segmentRepository.getSegmentCodes());		
		
		responseMap.put(MoicConstant.DD_WHOLESALE, moicDisplayWholesalePriceMap);		
		responseMap.put(MoicConstant.DD_RETAIL,moicDisplaySuggestedRetailPriceMap );
		
		if (responseList != null && !responseList.isEmpty()) {
			return new SuccessResponse(true, HttpStatus.OK, MoicConstant.SUCCESS_MESSAGE, responseMap);
		} else {
			return new SuccessResponse(true, HttpStatus.OK, MoicConstant.NO_RECORDS_FOUND, null);
		}
	}

	@Override
	public MoicResponse updateConfigData(ConfigDto configDto, String token) {
		
		logger.debug("Inside Controller method updateConfigData()");
		// Any back-end validation will done here before invoking service
		MoicResponse response;
		try {
			configInternalService.updateConfigData(configDto, token);
			
			 response = getConfigDefaultData();
			 if (response.getMessage().equals(MoicConstant.SUCCESS_MESSAGE))
				 response.setMessage(MoicConstant.RECORD_UPDATED_SUCCESSFULLY);
			 
			 return response;
			
		} catch (Exception e) {
			response = getConfigDefaultData();
			response.setMessage(MoicConstant.RECORD_NOT_UPDATED);
			
			return response;
		}
	}

	@Override
	public MoicResponse uploadFile(@RequestParam("file") MultipartFile file, String token) {
		String message = "";

		String[] configHeaders = configHeader.split(",");		
		
		logger.debug(MessageFormat.format("Config uploadFile. configFileName :{0} configSheetName: {1} configHeader: {2} ",configFileName, configSheetName,configHeader));

		if (ExcelHelper.checkFileName(configFileName, file)) {
			if (ExcelHelper.hasExcelFormat(file)) {					
					logger.debug(MessageFormat.format("*File name matched {0}", file.getOriginalFilename()));
				if (ExcelHelper.checkSheetAndHeaders(configSheetName, configHeaders, file)) {					
					
					try {
						configInternalService.saveConfigUploadExcel(configSheetName, file,configHeaders.length, token);
						List<ConfigDto> responseList = configInternalService.getConfigDefaultData();
						if (responseList != null && !responseList.isEmpty()) {
							return new SuccessResponse(true, HttpStatus.OK, MoicConstant.UPLOAD_FILE_SUCCESS+ file.getOriginalFilename(), responseList);
						} else {
							return new SuccessResponse(true, HttpStatus.OK, MoicConstant.NO_RECORDS_FOUND, null);
						}
					} catch (Exception e) {

						message = MoicConstant.UPLOAD_FILE_EXCEPTION + file.getOriginalFilename() + ".\n"
								+ e.getMessage();
						logger.error(message);
						return new ErrorResponse(true, HttpStatus.EXPECTATION_FAILED, message);
					}

				} else {
					message = MoicConstant.UPLOAD_WRONG_FILE_HEADER_SHEET_NAME;
					logger.error(message);
					return new ErrorResponse(true, HttpStatus.BAD_REQUEST, message);
				}
			} else {
				message = MoicConstant.UPLOAD_WRONG_FILE_EXTENTION;
				logger.error(message);
				return new ErrorResponse(true, HttpStatus.BAD_REQUEST, message);
			}

		}

		message = MoicConstant.UPLOAD_WRONG_FILENAME + configFileName;
		logger.error(message);
		return new ErrorResponse(true, HttpStatus.BAD_REQUEST, message);
	}

	
	
	@Override
	public ResponseEntity<InputStreamResource> downloadCustomerReport() {
		
		LocalDateTime myDateObj = LocalDateTime.now();
	    
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern(MoicConstant.DDMMYYYYHHMMSS);

	    String formattedDate = myDateObj.format(myFormatObj);
		
		List<ConfigDto> configDto=new ArrayList<>();
		
		configDto = configInternalService.getConfigReportData();
		
		ByteArrayInputStream in = configInternalService.downloadCustomerReportExcle(configDto);
	    
		HttpHeaders headers = new HttpHeaders();
	    headers.add("Content-Disposition", "attachment; filename="+fileName+"_"+formattedDate+".xlsx");
	    
	    return ResponseEntity
	                  .ok()
	                  .headers(headers)
	                  .body(new InputStreamResource(in));
	}

}
