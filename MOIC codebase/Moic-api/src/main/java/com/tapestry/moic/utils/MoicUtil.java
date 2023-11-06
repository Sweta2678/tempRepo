package com.tapestry.moic.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.tapestry.moic.constant.MoicConstant;
import com.tapestry.moic.dto.MOQOverviewDto;
import com.tapestry.moic.dto.OverviewDto;
import com.tapestry.moic.dto.SeasonsDto;
import com.tapestry.moic.dto.TokenDto;
import com.tapestry.moic.entity.JoorOrder;

public class MoicUtil {

	private static Logger LOGGER = LoggerFactory.getLogger(MoicUtil.class);
	@SuppressWarnings("deprecation")
	public static Date dateToMMDDYYYY(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMDDYYYY");
		return new Date(dateFormat.format(date));
	}


	@SuppressWarnings("unused")
	public static String stringdateToMMDDYYYY(Date date) {
		if(date!=null) {
			SimpleDateFormat sm = new SimpleDateFormat("MM-dd-yyyy");
			String strDate = sm.format(date);
			return strDate;
		}
		return MoicConstant.BLANK;
	}
	
	public static Date dateConvert(String dateStr) throws ParseException {
	  		String result=dateStr.substring(0,10);		    	
    	    Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(result);     	    		    		
    	    return date1;    	
	}


	public static List<String> getScreenLabelList(String screenLabels) {
		List<String> screenLabelList = new ArrayList<String>();

		StringTokenizer tokenizer = new StringTokenizer(screenLabels, MoicConstant.COMMA);
		while (tokenizer.hasMoreTokens()) {
			screenLabelList.add(tokenizer.nextToken());
		}
		return screenLabelList;
	}

	public static String getMonthName(int monthNumber) {
		String month = MoicConstant.BLANK;
		DateFormatSymbols dfs = new DateFormatSymbols();
		String[] months = dfs.getMonths();
		if (monthNumber >= 0 && monthNumber <= 11 ) {
			month = months[monthNumber];
		}
		else if(monthNumber == -1) {
			month = months[11];
		}
		return month;
	}

	public static Map<Integer, Double> initMonthMap(Map<Integer, String> monthHeader) {
		Map<Integer, Double> map = new HashMap<>();
		for (Map.Entry<Integer, String> entry : monthHeader.entrySet()) {
			map.put(entry.getKey(), 0d);
		}
		return map;
	}

	public static Double sumMonthTotals(Map<Integer, Double> monthsTotal) {
		Double sum = 0d;
		for (Map.Entry<Integer, Double> entry : monthsTotal.entrySet()) {
			sum += entry.getValue();
		}
		return sum;
	}

	public static Map<String, OverviewDto> initOverviewDtoMap(List<String> channelList, Map<Integer, String> monthHeader) {
		Map<String, OverviewDto> overviewDtoMap = new HashMap<>();
		for (String channel : channelList) {
			OverviewDto dto = new OverviewDto();
			dto.setMonthOrder(initMonthMap(monthHeader));
			overviewDtoMap.put(channel, dto);
		}
		return overviewDtoMap;
	}

	public static Map<String, Boolean> initDropDownMap(List<String> dropDownList, String selectedValue) {
		Map<String, Boolean> map = new HashMap<>();
		for (String listItem : dropDownList) {
			if(listItem.equals(selectedValue))
				map.put(listItem, Boolean.TRUE);
			else
				map.put(listItem, Boolean.FALSE);
		}
		return map;
	}

	@SuppressWarnings("deprecation")
	public static Map<Integer, String> generateMonthHeader(List<JoorOrder> joorOrders) {
		int monthCount = 0;
		Map<Integer, String> monthHeader = new HashMap<>();
		for (JoorOrder joorOrder : joorOrders) {
			if(null != joorOrder.getDateShipStart()) {
				int monthNumber = joorOrder.getDateShipStart().getMonth();
				if (!monthHeader.containsKey(monthNumber)) {
					monthHeader.put(monthNumber, MoicUtil.getMonthName(monthNumber));
					monthCount++;
				}
				if (monthCount == 12)
					break;
			}
		}
		return monthHeader;
	}

	public static Map<String, MOQOverviewDto> initMOQOverviewDtoMap(List<String> skuList, Map<Integer, String> monthHeader) {
		Map<String, MOQOverviewDto> moqOverviewDtoMap = new HashMap<>();
		for (String sku : skuList) {
			MOQOverviewDto dto = new MOQOverviewDto();
			dto.setSku(sku);
			dto.setMonthOrder(initMonthMap(monthHeader));
			moqOverviewDtoMap.put(sku, dto);
		}
		return moqOverviewDtoMap;
	}
	
	public static String getCombinedSku(String itemNumber, String itemColorCode, String itemSize) {
		String combinedSku;
		itemNumber = itemNumber.trim();
		itemColorCode = itemColorCode.trim();
		itemSize = itemSize.trim();
		int len = 4;
		
		StringBuilder sb = new StringBuilder();
		while(sb.length() < 6 - itemNumber.length()) {
			sb.append(MoicConstant.SPACE);
		}
		itemNumber += sb;
		combinedSku = itemNumber ;
		
		String paddedColorCode;
		if(!itemColorCode.isEmpty() && itemColorCode.contains(MoicConstant.SPACE))
			paddedColorCode = itemColorCode.substring(0, itemColorCode.indexOf(MoicConstant.SPACE));
		else
			paddedColorCode = itemColorCode;
		
		StringBuilder stringBuilder = new StringBuilder();
		while(stringBuilder.length() < 5 - paddedColorCode.length()) {
			stringBuilder.append(MoicConstant.SPACE);
		}
		paddedColorCode += stringBuilder;
		combinedSku += paddedColorCode;
		
		if(!itemSize.isEmpty() && !itemSize.equalsIgnoreCase("U")) {
			if(itemSize.length() == len) {
				combinedSku += itemSize;
			}
			else {
				StringBuilder builder = new StringBuilder();
				while(builder.length() < len - itemSize.length()) {
					builder.append(MoicConstant.SPACE);
				}
				itemSize += builder;
				combinedSku += itemSize;
			}
			
			if(!itemColorCode.isEmpty() && itemColorCode.contains(MoicConstant.SPACE))
				combinedSku += itemColorCode.substring(itemColorCode.indexOf(MoicConstant.SPACE) + 1, itemColorCode.length());
		}
		
		return combinedSku.trim();
	}
		
	public static String getCombinedSkuForMoa(String styleId, String skuName, String sizeName) {
		String combinedSku;
		styleId = styleId.trim();
		skuName = skuName.trim();
		sizeName = sizeName.trim();
		
		StringBuilder sb = new StringBuilder();
		while(sb.length() < 6 - styleId.length()) {
			sb.append(MoicConstant.SPACE);
		}
		styleId += sb;	
		combinedSku = styleId;
		
		if(!skuName.isEmpty()) {
			StringBuilder stringBuilder = new StringBuilder();
			while(stringBuilder.length() < 5 - skuName.length()) {
				stringBuilder.append(MoicConstant.SPACE);
			}
			skuName += stringBuilder;
			combinedSku += skuName;
		}
		if(!sizeName.isEmpty()) 
			combinedSku += sizeName;
			
		return combinedSku.trim();
	}
	
	public static String getFormatedSizeAndWidth(String size, String width) {
		String sizeAndWidth;
		size = size.trim();
		width = width.trim();
		int len = 4;
						
		if(size.length() != len) {
			StringBuilder builder = new StringBuilder();
			while(builder.length() < len - size.length()) {
				builder.append(MoicConstant.SPACE);
			}
			size += builder;
		}
		sizeAndWidth = size + width;
		
		return sizeAndWidth;
	}
	
	public static String getEncryptedToken(String keyPhrase, TokenDto tokenDto) {
		try {
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String plainJSON = ow.writeValueAsString(tokenDto);
			LOGGER.debug("DTO json :: \n" + plainJSON);
			
			byte[] jsonArr = plainJSON.getBytes();
			byte[] keyArr = keyPhrase.getBytes();
			byte[] xorArr = new byte[jsonArr.length];

	        // Use key byte array to cycle encryption or decryption
	        for (int i = 0; i < jsonArr.length; i++) {
	            // Data is XOR with key, and then XOR with low 8 bits of cyclic variable (increasing complexity)
	        	xorArr[i] = (byte) (jsonArr[i] ^ keyArr[i % keyArr.length] ^ (i & 0xFF));
	        }
		
			String encrypedToken = new String(Base64.getUrlEncoder().encode(xorArr), StandardCharsets.UTF_8);
			LOGGER.debug("encrypedToken : " + encrypedToken);
			
			return encrypedToken;
						
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static TokenDto getDecryptedToken(String keyPhrase, String encryptedToken){
		LOGGER.debug("encryptedToken received :: " + encryptedToken);
		TokenDto tokenDto = null; 
		try {
			byte[] keyArr = keyPhrase.getBytes();
			byte[] xorArr = Base64.getUrlDecoder().decode(encryptedToken.getBytes());
			byte[] jsonArr = new byte[xorArr.length];
			
	        // Use key byte array to cycle encryption or decryption
	        for (int i = 0; i < xorArr.length; i++) {
	            // Data is XOR with key, and then XOR with low 8 bits of cyclic variable (increasing complexity)
	        	jsonArr[i] = (byte) (xorArr[i] ^ keyArr[i % keyArr.length] ^ (i & 0xFF));
	        }
			
	        String decodedString = new String(jsonArr, StandardCharsets.UTF_8);
			LOGGER.debug("decodedString : \n" + decodedString);
			
			ObjectMapper objectMapper = new ObjectMapper();
			
			tokenDto = objectMapper.readValue(decodedString, TokenDto.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tokenDto;
	}
	
	public static String getConcatenatedStyleColor(String itemNumber, String itemColorCode) {
		String concatenatedStyleColor;
		if(null != itemNumber && !itemNumber.isEmpty())
			itemNumber = itemNumber.trim();
		else
			itemNumber = MoicConstant.BLANK;
				
		concatenatedStyleColor = itemNumber;
		
		if(!itemColorCode.isEmpty() && itemColorCode.contains(MoicConstant.SPACE))
			concatenatedStyleColor += MoicConstant.SPACE + itemColorCode.substring(0, itemColorCode.indexOf(MoicConstant.SPACE));
		else
			concatenatedStyleColor += MoicConstant.SPACE + itemColorCode;
		
		return concatenatedStyleColor;
	}
	
	public static String getWholeNumberIdValue(String idValue) {
		
		String  wholeValue =  idValue.split("\\.")[0];
		return wholeValue;
	}
	
	public static String getSeasonCodeInLowerCaseWithoutSpace(String seasonCode) {
		
		String[] seasonArr = seasonCode.split(MoicConstant.SPACE);
		seasonArr[0] = seasonArr[0].toLowerCase();
		String convertedSeasonCode = String.join(MoicConstant.EMPTY_STRING, seasonArr);
		return convertedSeasonCode;
		
	}
	
	public static String validateDateAndReturnString(Date date, String dateFormat) {
		if(date!= null) {
			SimpleDateFormat sm = new SimpleDateFormat(dateFormat);
			String strDate = sm.format(date);
			return strDate;
		}
		return MoicConstant.BLANK;
	}
	
	public static List<SeasonsDto> convertSeasonsListToJson(List<String> seasons) {
		
		List<SeasonsDto> seasonsList = new ArrayList<SeasonsDto>();
		for(String season : seasons) {
			SeasonsDto seasonObj = new SeasonsDto();
			seasonObj.setName(season);
			seasonObj.setChecked(false);
			
			seasonsList.add(seasonObj);
		}
		
		return seasonsList;
	}
	
	public static List<String> getOutletChannlesList(){ 
		List <String> channelsList = new ArrayList<String>();
		channelsList.add(MoicConstant.OFF_PRICE_WHOLESALE);
		channelsList.add(MoicConstant.SW_Outlet);
	
		return channelsList;
	}
}
