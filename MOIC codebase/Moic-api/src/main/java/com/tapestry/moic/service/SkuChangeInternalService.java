package com.tapestry.moic.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tapestry.moic.constant.MoicConstant;
import com.tapestry.moic.constant.MoicSources;
import com.tapestry.moic.dto.MOQOverviewDto;
import com.tapestry.moic.dto.SkuChangeDto;
import com.tapestry.moic.dto.SkuChangeScreenDto;
import com.tapestry.moic.dto.TokenDto;
import com.tapestry.moic.entity.Channel;
import com.tapestry.moic.entity.CustomerMaster;
import com.tapestry.moic.entity.JoorOrder;
import com.tapestry.moic.entity.JoorOrderItem;
import com.tapestry.moic.entity.ProductSizeScaleMoa;
import com.tapestry.moic.entity.SKUChangeReport;
import com.tapestry.moic.entity.SkuChange;
import com.tapestry.moic.repository.ChannelRepository;
import com.tapestry.moic.repository.CustomerMasterRepository;
import com.tapestry.moic.repository.JoorOrderItemRepository;
import com.tapestry.moic.repository.JoorOrderRepository;
import com.tapestry.moic.repository.ProductSizeScaleMoaRepository;
import com.tapestry.moic.repository.SKUChangeReportRepository;
import com.tapestry.moic.repository.SkuChangeRepository;
import com.tapestry.moic.service.interfaces.ISkuChangeInternalService;
import com.tapestry.moic.utils.ExcelHelper;
import com.tapestry.moic.utils.MoicUtil;

@Component
public class SkuChangeInternalService implements ISkuChangeInternalService {

	@Autowired
	SkuChangeRepository skuChangeRepository;
	
	@Autowired
	private CustomerMasterRepository customerMasterRepository;

	@Autowired
	private JoorOrderItemRepository joorOrderItemRepository;

	@Autowired
	private JoorOrderRepository joorOrderRepository;
	
	@Autowired
	private ChannelRepository channelRepository;
	
	@Autowired
	private SKUChangeReportRepository skuChangeReportRepository;
	
	@Autowired
	private ProductSizeScaleMoaRepository productSizeScaleMoaRepository;
	
	private Logger LOGGER = LoggerFactory.getLogger(SkuChangeInternalService.class);
	
	@Value("${skuchange.report.columnname}")
	String reportColumnname;

	@Value("${skuchange.lower.table.labels}")
	String skuchangeLowerTableLabels;
	
	@Value("${skuchange.report.SKU.sheetName}")
	String skuchangeSheetName;	
	
	@Value("${skuchange.list.columnname}")
	private String skuChangeListColumnName;
	
	@Value("${skuchange.list.SKU.sheetName}")
	private String skuChangeListSheetName;

	@Override
	public ByteArrayInputStream downloadSKUChangeReport(List<String> season, String channel) {
		
		List<SKUChangeReport> skuChnageList = new ArrayList<SKUChangeReport>();
		LOGGER.debug("Inside downloadSKUChangeReport (). Columnname " + reportColumnname);
		
		if(MoicConstant.CORPORATE.equalsIgnoreCase(channel))
			skuChnageList = skuChangeReportRepository.findBySeasonIn(season);
		else			
			skuChnageList = skuChangeReportRepository.findByChannelAndSeasonIn(channel, season);
		
		String[] splitRow = reportColumnname.split(",");
		Workbook workBook = new XSSFWorkbook();

		Sheet sheet = workBook.createSheet(skuchangeSheetName);
		Row row = sheet.createRow(0);
		CellStyle headerCellStyle = workBook.createCellStyle();
		headerCellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());

		int count = 0;
		Cell cell;
		for (String str : splitRow) {
			cell = row.createCell(count);
			cell.setCellValue(str);
			cell.setCellStyle(headerCellStyle);
			count++;
		}

		int result = 0;
		for (SKUChangeReport skuChangeReport : skuChnageList) {

			int cellIndex = 0;
			Row dataRow = sheet.createRow(result + 1);
			LOGGER.debug("Inside downloadSKUChangeReport() dataRow" + dataRow.getPhysicalNumberOfCells());

			dataRow.createCell(cellIndex++).setCellValue(skuChangeReport.getCreatedBy());// status
			dataRow.createCell(cellIndex++).setCellValue(null == skuChangeReport.getDepartment() ? "" : skuChangeReport.getDepartment());// Business Unit
			dataRow.createCell(cellIndex++).setCellValue("");//File Name (SAP upload files)

			dataRow.createCell(cellIndex++).setCellValue(null == skuChangeReport.getSKU() ? "" : skuChangeReport.getSKU());
			dataRow.createCell(cellIndex++)
			.setCellValue(null == skuChangeReport.getStyleColor() ? "" : skuChangeReport.getStyleColor());
			dataRow.createCell(cellIndex++).setCellValue(null == skuChangeReport.getWidth() ? "" : skuChangeReport.getWidth());
			dataRow.createCell(cellIndex++).setCellValue(null == skuChangeReport.getSize() ? "" : skuChangeReport.getSize());
			dataRow.createCell(cellIndex++).setCellValue(null == skuChangeReport.getEuSize() ? "" : skuChangeReport.getEuSize());
			dataRow.createCell(cellIndex++)
			.setCellValue(null == skuChangeReport.getStyleNumber() ? "" : skuChangeReport.getStyleNumber());
			dataRow.createCell(cellIndex++)
			.setCellValue(null == skuChangeReport.getStyleName() ? "" : skuChangeReport.getStyleName());
			dataRow.createCell(cellIndex++)
			.setCellValue(null == skuChangeReport.getStyleDescription() ? "" : skuChangeReport.getStyleDescription());
			dataRow.createCell(cellIndex++)
			.setCellValue(null == skuChangeReport.getMaterialName() ? "" : skuChangeReport.getMaterialName());
			dataRow.createCell(cellIndex++)
			.setCellValue(null == skuChangeReport.getColorName() ? "" : skuChangeReport.getColorName());
			dataRow.createCell(cellIndex++)
			.setCellValue(null == skuChangeReport.getColorCode() ? "" : skuChangeReport.getColorCode());
			dataRow.createCell(cellIndex++).setCellValue(MoicUtil.validateDateAndReturnString(skuChangeReport.getSkuIntroDate(), "MM/dd/yyyy"));
			dataRow.createCell(cellIndex++)
			.setCellValue(null == skuChangeReport.getDepartment() ? "" : skuChangeReport.getDepartment());
			dataRow.createCell(cellIndex++)
			.setCellValue(null == skuChangeReport.getParentsClass() ? "" : skuChangeReport.getParentsClass());			
			dataRow.createCell(cellIndex++).setCellValue(skuChangeReport.getRetailPrice());
			dataRow.createCell(cellIndex++).setCellValue(skuChangeReport.getWholesalePrice());
			dataRow.createCell(cellIndex++).setCellValue(skuChangeReport.getRetailCurrency());
			dataRow.createCell(cellIndex++).setCellValue(skuChangeReport.getWholesaleCurrency());
			dataRow.createCell(cellIndex++).setCellValue(skuChangeReport.getTargetCost());
			dataRow.createCell(cellIndex++).setCellValue(null == skuChangeReport.getCoo() ? "" : skuChangeReport.getCoo());
			dataRow.createCell(cellIndex++).setCellValue(skuChangeReport.getTotalBuyQuantity());
			dataRow.createCell(cellIndex++).setCellValue(MoicUtil.validateDateAndReturnString(skuChangeReport.getScheduledDeliveryDate(), "MM/dd/yyyy"));
			dataRow.createCell(cellIndex++)
			.setCellValue(null == skuChangeReport.getSalesDocType() ? "" : skuChangeReport.getSalesDocType());
			dataRow.createCell(cellIndex++)
			.setCellValue(null == skuChangeReport.getSalesOrg() ? "" : skuChangeReport.getSalesOrg());
			dataRow.createCell(cellIndex++).setCellValue(
					null == skuChangeReport.getDistributionChannel() ? "" : skuChangeReport.getDistributionChannel());
			dataRow.createCell(cellIndex++)
			.setCellValue(null == skuChangeReport.getDivision() ? "" : skuChangeReport.getDivision());
			dataRow.createCell(cellIndex++)
			.setCellValue(null == skuChangeReport.getSoldToNumber() ? "" : skuChangeReport.getSoldToNumber());
			dataRow.createCell(cellIndex++).setCellValue(null == skuChangeReport.getSoldToDescription() ? "" : skuChangeReport.getSoldToDescription());
			dataRow.createCell(cellIndex++).setCellValue(null == skuChangeReport.getCountry() ? "" : skuChangeReport.getCountry());
			dataRow.createCell(cellIndex++).setCellValue(null == skuChangeReport.getCity() ? "" : skuChangeReport.getCity());
			dataRow.createCell(cellIndex++)
			.setCellValue(null == skuChangeReport.getShipToNumber() ? "" : skuChangeReport.getShipToNumber());
			dataRow.createCell(cellIndex++).setCellValue(MoicUtil.validateDateAndReturnString(skuChangeReport.getSoHeaderReqDelDate(), "MM/dd/yyyy"));
			dataRow.createCell(cellIndex++).setCellValue(MoicUtil.validateDateAndReturnString(skuChangeReport.getSoCancelDate(), "MM/dd/yyyy"));
			dataRow.createCell(cellIndex++).setCellValue(skuChangeReport.getPoNumber());
			dataRow.createCell(cellIndex++).setCellValue(null == skuChangeReport.getSite() ? "" : skuChangeReport.getSite());
			dataRow.createCell(cellIndex++)
			.setCellValue(null == skuChangeReport.getReqSegment() ? "" : skuChangeReport.getReqSegment());			
			dataRow.createCell(cellIndex++)
			.setCellValue(null == skuChangeReport.getOldSKU()? "" : skuChangeReport.getOldSKU());
			dataRow.createCell(cellIndex++)
			.setCellValue(null == skuChangeReport.getNewSKU()? "" : skuChangeReport.getNewSKU());
			dataRow.createCell(cellIndex++)
			.setCellValue(null == skuChangeReport.getDrop_1() ? false : skuChangeReport.getDrop_1());			
			dataRow.createCell(cellIndex++)
			.setCellValue(null == skuChangeReport.getLevel() ? "" : skuChangeReport.getLevel());			
			dataRow.createCell(cellIndex++)
			.setCellValue(null == skuChangeReport.getLevelID() ? "" : skuChangeReport.getLevelID());
			dataRow.createCell(cellIndex++)
			.setCellValue(null == skuChangeReport.getDelete() ? false : skuChangeReport.getDelete());
			dataRow.createCell(cellIndex++)
			.setCellValue(MoicUtil.validateDateAndReturnString(skuChangeReport.getEffectiveDate(), "MM/dd/yyyy"));			
			dataRow.createCell(cellIndex++).setCellValue(null == skuChangeReport.getOrderTotalUSD() ? 0 : skuChangeReport.getOrderTotalUSD());
			
			sheet.autoSizeColumn(result);
			result = result+ 1;
		}
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			workBook.write(outputStream);
		} catch (IOException e) {
			LOGGER.error("IOException in File writing :" + e.getMessage());
		} finally {
			try {
				workBook.close();
			} catch (IOException e) {
				LOGGER.error("IOException in File closing :" + e.getMessage());
			}
		}
		return new ByteArrayInputStream(outputStream.toByteArray());

	}

	@Override
	public ByteArrayInputStream downloadSKUChangeList(String season) {
		List<SkuChange> skuChangelist = new ArrayList<>();

		skuChangelist = skuChangeRepository.findBySeason(season.trim());

		String[] splitRow = skuChangeListColumnName.split(",");
		Workbook workBook = new XSSFWorkbook();

		Sheet sheet = workBook.createSheet(skuChangeListSheetName);
		Row row = sheet.createRow(0);
		CellStyle headerCellStyle = workBook.createCellStyle();
		headerCellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());

		int count = 0;
		Cell cell;
		for (String str : splitRow) {
			cell = row.createCell(count);
			cell.setCellValue(str);
			cell.setCellStyle(headerCellStyle);
			count++;
		}

		int result = 0;
		for (SkuChange sku : skuChangelist) {

			Row dataRow = sheet.createRow(result + 1);

			dataRow.createCell(0).setCellValue(null ==sku.getSeason()? "" :sku.getSeason());
			dataRow.createCell(1).setCellValue(null ==sku.getOldSKU()? "" :sku.getOldSKU());
			dataRow.createCell(2).setCellValue(null ==sku.getNewSKU()? "" :sku.getNewSKU());
			dataRow.createCell(3).setCellValue(null ==sku.getDrop_1()?  Boolean.FALSE :sku.getDrop_1());
			dataRow.createCell(4).setCellValue(null ==sku.getLevel()? "" :sku.getLevel());
			dataRow.createCell(5).setCellValue(null ==sku.getLevelId()? "" :sku.getLevelId());
			dataRow.createCell(6).setCellValue(null ==sku.getDelete()?  Boolean.FALSE :sku.getDelete());
			dataRow.createCell(7).setCellValue(null ==sku.getEffectiveDate()? MoicConstant.BLANK : new SimpleDateFormat("MM/dd/yyyy").format(sku.getEffectiveDate()));			
			sheet.autoSizeColumn(result);
			result = result + 1;
		}
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			workBook.write(outputStream);
		} catch (IOException e) {
			LOGGER.error("Inside downloadSKUChangeList() IOException in File writing :" + e.getMessage());
		} finally {
			try {
				workBook.close();
			} catch (IOException e) {
				LOGGER.error("Inside downloadSKUChangeList()  IOException in File closing :" + e.getMessage());
			}
		}
		return new ByteArrayInputStream(outputStream.toByteArray());
	}

	@Override
	public Map<String, Object> getSKUChangeDefaultData(String season) {
		LOGGER.debug("Inside service method getSKUChangeDefaultData");
		Map<String, Object> skuChangeResponseMap = new HashMap<>();
		List<SkuChangeScreenDto> skuChangeScreenDtos = new ArrayList<>();
		List<SkuChange> skuChanges = skuChangeRepository.findByDeleteAndSeason(Boolean.FALSE, season);

		List<String> skuChangeLowerTableLabelsList = MoicUtil.getScreenLabelList(skuchangeLowerTableLabels);
		ObjectMapper mapper = new ObjectMapper();
		for (SkuChange skuChange : skuChanges) {
			SkuChangeScreenDto skuChangeScreenDto = mapper.convertValue(skuChange, SkuChangeScreenDto.class);
			skuChangeScreenDto.setIdAuto(skuChangeScreenDto.getIdAuto());
			skuChangeScreenDto.setSeason(null == skuChangeScreenDto.getSeason() ? "" : skuChangeScreenDto.getSeason());
			skuChangeScreenDto.setOldSKU(null == skuChangeScreenDto.getOldSKU() ? "" : skuChangeScreenDto.getOldSKU());
			skuChangeScreenDto.setNewSKU(null == skuChangeScreenDto.getNewSKU() ? "" : skuChangeScreenDto.getNewSKU());

			skuChangeScreenDto
					.setDrop(null == skuChangeScreenDto.getDrop() ? Boolean.FALSE : skuChangeScreenDto.getDrop());
			skuChangeScreenDto.setLevel(null == skuChangeScreenDto.getLevel() ? "" : skuChangeScreenDto.getLevel());
			skuChangeScreenDto
					.setLevelId(null == skuChangeScreenDto.getLevelId() ? "" : skuChangeScreenDto.getLevelId());

			skuChangeScreenDtos.add(skuChangeScreenDto);
		}

		skuChangeResponseMap.put(MoicConstant.COLUMNS, skuChangeLowerTableLabelsList);
		if (skuChangeScreenDtos != null && skuChangeScreenDtos.size() > 0) {
			skuChangeResponseMap.put(MoicConstant.MESSAGE, MoicConstant.SUCCESS_MESSAGE);
			skuChangeResponseMap.put(MoicConstant.DATA, skuChangeScreenDtos);
		} else {
			skuChangeResponseMap.put(MoicConstant.MESSAGE, MoicConstant.NO_RECORDS_FOUND);
			skuChangeResponseMap.put(MoicConstant.DATA, null);
		}

		return skuChangeResponseMap;
	}

	@Override
	public Boolean updateSKUChangeData(SkuChangeDto skuChangeDto, String token) {
		LOGGER.debug("Inside service method updateSKUChangeData");

		List<ProductSizeScaleMoa> productSizeScaleMoas = productSizeScaleMoaRepository.findByCombinedSku(skuChangeDto.getNewSKU().trim());
		TokenDto tokenDto = MoicUtil.getDecryptedToken(MoicConstant.SESSION_TOKEN_ENCRYPTION_KEY, token);
		if(!productSizeScaleMoas.isEmpty()) {
		SkuChange skuChangeCheck = skuChangeRepository.findByIdAuto(skuChangeDto.getIdAuto());
			if (skuChangeCheck != null) {				
				skuChangeCheck.setSeason(null == skuChangeDto.getSeason() ? "" : skuChangeDto.getSeason());
				skuChangeCheck.setOldSKU(null == skuChangeDto.getOldSKU() ? "" : skuChangeDto.getOldSKU());
				skuChangeCheck.setNewSKU(null == skuChangeDto.getNewSKU() ? "" : skuChangeDto.getNewSKU());
				skuChangeCheck.setDrop_1(null == skuChangeDto.getDrop_1() ? Boolean.FALSE : skuChangeDto.getDrop_1());
				skuChangeCheck.setDelete(null == skuChangeDto.getDelete() ? Boolean.FALSE : skuChangeDto.getDelete());
				skuChangeCheck.setLevel(null == skuChangeDto.getLevel() ? "" : skuChangeDto.getLevel());
				skuChangeCheck.setLevelId(null == skuChangeDto.getLevelId() ? "" : skuChangeDto.getLevelId());
//				skuChangeCheck.setEffectiveDate(skuChangeDto.getEffectiveDate());
				// update required audit fields
				skuChangeCheck.setModifiedBy(tokenDto.getDisplayName());	
				skuChangeCheck.setSource(MoicSources.MOIC_UPDATE);
				skuChangeCheck.setModifiedDate(new Date());
				skuChangeCheck.setUserId(tokenDto.getEmailAddress());
				skuChangeCheck.setUserName(tokenDto.getDisplayName());
				skuChangeRepository.save(skuChangeCheck);
				LOGGER.debug("SkuChange  data updated successfully");
				return Boolean.TRUE;
			} 
		}
		return Boolean.FALSE;
	}

	
	@Override 
	public String saveSKUChangeUploadExcel(List<SkuChangeDto> skuChanges, String token) { 

		LOGGER.debug("Inside saveSKUChangeUploadExcel .... ");
		try {	
			List<String> saveResult = new ArrayList<String>();
			List<String> updateResult = new ArrayList<String>();
			List<String> notFoundResult = new ArrayList<String>();
			TokenDto tokenDto = MoicUtil.getDecryptedToken(MoicConstant.SESSION_TOKEN_ENCRYPTION_KEY, token);
			
			for(SkuChangeDto skuChange : skuChanges) {
				
				if(skuChange.getNewSKU()!=null&&!skuChange.getNewSKU().isEmpty()) {
					
				List<ProductSizeScaleMoa> productSizeScaleMoas_newSku = productSizeScaleMoaRepository.findByCombinedSku(skuChange.getNewSKU().trim());
				List<ProductSizeScaleMoa> productSizeScaleMoas_oldSKU = productSizeScaleMoaRepository.findByCombinedSku(skuChange.getOldSKU().trim());
				if(!productSizeScaleMoas_newSku.isEmpty() && !productSizeScaleMoas_oldSKU.isEmpty()) {
					SkuChange skuChangeCheck =  skuChangeRepository.findByOldSKUAndSeasonAndLevelAndLevelId(skuChange.getOldSKU(),skuChange.getSeason(),skuChange.getLevel(),skuChange.getLevelId());

				if (skuChangeCheck != null) {	  
					skuChangeCheck.setOldSKU(skuChange.getOldSKU());
					skuChangeCheck.setNewSKU(skuChange.getNewSKU());
					skuChangeCheck.setSeason(skuChange.getSeason());
					skuChangeCheck.setDelete(skuChange.getDelete());
					skuChangeCheck.setLevel(skuChange.getLevel());
					skuChangeCheck.setLevelId(skuChange.getLevelId());
					skuChangeCheck.setDrop_1(skuChange.getDrop_1());
					skuChangeCheck.setEffectiveDate(skuChange.getEffectiveDate());
					// update required audit fields			
					skuChangeCheck.setModifiedDate(new Date());
					skuChangeCheck.setModifiedBy(MoicSources.MOIC_FILE_UPLOAD);
					skuChangeCheck.setSource(MoicSources.MOIC_FILE_UPLOAD);
					skuChangeCheck.setUserId(tokenDto.getEmailAddress());
					skuChangeCheck.setUserName(tokenDto.getDisplayName());
					
					skuChangeRepository.save(skuChangeCheck); 
					LOGGER.debug("Inside saveSKUChangeUploadExcel().  SkuChange  data updated successfully ");					
					updateResult.add(skuChange.getNewSKU());
				}else {
					SkuChange skuChangeObj= new SkuChange();
					skuChangeObj.setOldSKU(skuChange.getOldSKU());
					skuChangeObj.setNewSKU(skuChange.getNewSKU());
					skuChangeObj.setSeason(skuChange.getSeason());
					skuChangeObj.setDelete(skuChange.getDelete());
					skuChangeObj.setLevel(skuChange.getLevel());
					skuChangeObj.setLevelId(skuChange.getLevelId());
					skuChangeObj.setDrop_1(skuChange.getDrop_1());
					skuChangeObj.setEffectiveDate(skuChange.getEffectiveDate());
					// update required audit fields			
					skuChangeObj.setCreateDate(new Date());
					skuChangeObj.setCreatedBy(MoicSources.MOIC_FILE_UPLOAD);
					skuChangeObj.setSource(MoicSources.MOIC_FILE_UPLOAD);
					skuChangeObj.setUserId(tokenDto.getEmailAddress());
					skuChangeObj.setUserName(tokenDto.getDisplayName());
					skuChangeRepository.save(skuChangeObj);					
					LOGGER.debug("Inside saveSKUChangeUploadExcel(). New SKUChange Data saved successfully");					
					saveResult.add(skuChange.getNewSKU());
				}
			}else{ // productSKU.isEmpty()
					notFoundResult.add(skuChange.getNewSKU());			
				}
			}else {// Outer else
				

				SkuChange skuChangeCheck =  skuChangeRepository.findByOldSKUAndSeasonAndLevelAndLevelId(skuChange.getOldSKU(),skuChange.getSeason(),skuChange.getLevel(),skuChange.getLevelId());

				if (skuChangeCheck != null) {	  
					skuChangeCheck.setOldSKU(skuChange.getOldSKU());
					skuChangeCheck.setNewSKU(skuChange.getNewSKU());
					skuChangeCheck.setSeason(skuChange.getSeason());
					skuChangeCheck.setDelete(skuChange.getDelete());
					skuChangeCheck.setLevel(skuChange.getLevel());
					skuChangeCheck.setLevelId(skuChange.getLevelId());
					skuChangeCheck.setDrop_1(skuChange.getDrop_1());
					skuChangeCheck.setEffectiveDate(skuChange.getEffectiveDate());
					// update required audit fields			
					skuChangeCheck.setModifiedDate(new Date());
					skuChangeCheck.setModifiedBy(MoicSources.MOIC_FILE_UPLOAD);
					skuChangeCheck.setSource(MoicSources.MOIC_FILE_UPLOAD);
					skuChangeCheck.setUserId(tokenDto.getEmailAddress());
					skuChangeCheck.setUserName(tokenDto.getDisplayName());
					
					skuChangeRepository.save(skuChangeCheck); 
					LOGGER.debug("Inside saveSKUChangeUploadExcel().  SkuChange  data updated successfully ");
					
					updateResult.add(skuChange.getNewSKU());
				}else {
					SkuChange skuChangeObj= new SkuChange();
					skuChangeObj.setOldSKU(skuChange.getOldSKU());
					skuChangeObj.setNewSKU(skuChange.getNewSKU());
					skuChangeObj.setSeason(skuChange.getSeason());
					skuChangeObj.setDelete(skuChange.getDelete());
					skuChangeObj.setLevel(skuChange.getLevel());
					skuChangeObj.setLevelId(skuChange.getLevelId());
					skuChangeObj.setDrop_1(skuChange.getDrop_1());
					skuChangeObj.setEffectiveDate(skuChange.getEffectiveDate());
					// update required audit fields			
					skuChangeObj.setCreateDate(new Date());
					skuChangeObj.setCreatedBy(MoicSources.MOIC_FILE_UPLOAD);
					skuChangeObj.setSource(MoicSources.MOIC_FILE_UPLOAD);
					skuChangeObj.setUserId(tokenDto.getEmailAddress());
					skuChangeObj.setUserName(tokenDto.getDisplayName());
					skuChangeRepository.save(skuChangeObj);					
					LOGGER.debug("Inside saveSKUChangeUploadExcel(). New SKUChange Data saved successfully");					
					saveResult.add(skuChange.getNewSKU());
				}
			
			}
			}
			
			String result="";
			if(!saveResult.isEmpty() || !updateResult.isEmpty())
				result+=MoicConstant.UPLOAD_FILE_SUCCESS;	
			if(saveResult.isEmpty() && updateResult.isEmpty())
				result="";
			if(!notFoundResult.isEmpty())	
				result+=MoicConstant.UPLOAD_FILE_SKU_NOT_FOUND+notFoundResult.toString();	

			return  result;

		} catch (Exception e) {
			LOGGER.error("Fail to store SKUChange List excel data: " + e.getMessage());
			throw new RuntimeException(MoicConstant.SOMETHING_WENT_WRONG + "\nFail to store SKUChange List excel data");
		} 
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<SkuChangeDto> excelToSKUChanges(String sheetName, MultipartFile file,int headersLength, String token) {

		Workbook workbook = null;

		try {
			LOGGER.debug(" Inside excelToSKUChanges....");
			if (MoicConstant.EXCELTYPE.equals(file.getContentType()))
				workbook = new XSSFWorkbook(file.getInputStream());
			if (MoicConstant.EXCELTYPE2.equals(file.getContentType()))
				workbook = new HSSFWorkbook(file.getInputStream());

			Sheet sheet = workbook.getSheet(sheetName);

			Iterator<Row> rows = sheet.iterator();

			List<SkuChangeDto> skuChanges = new ArrayList<SkuChangeDto>();
			LOGGER.debug(" Inside excelToSKUChanges.....sheet..." + sheet);
			int rowNumber = 0;
			TokenDto tokenDto = MoicUtil.getDecryptedToken(MoicConstant.SESSION_TOKEN_ENCRYPTION_KEY, token);
			while (rows.hasNext()) {
				Row currentRow = rows.next();
				if (ExcelHelper.isRowEmpty(currentRow)) {
					break;
				}				
				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}

				SkuChangeDto skuChangeInfo = new SkuChangeDto();

				List<Cell> cells = new ArrayList<Cell>();
				int lastColumn = Math.max(currentRow.getLastCellNum(), headersLength);
				for (int cn = 0; cn < lastColumn; cn++) {
					Cell c = currentRow.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
					cells.add(c);
				}
				int cellIdx = 0;				
				for(Cell currentCell :cells) {

					LOGGER.debug(" cellIdx processing : " + cellIdx);
					switch (cellIdx) {

					case 0:
						ExcelHelper.checkMandatory(currentCell);
						skuChangeInfo.setSeason(ExcelHelper.checkStringNull(currentCell));
						break;
					case 1:
						ExcelHelper.checkMandatory(currentCell);
						skuChangeInfo.setOldSKU(ExcelHelper.checkStringNull(currentCell));
						break;
					case 2:
						skuChangeInfo.setNewSKU(ExcelHelper.checkStringNull(currentCell));
						break;
					case 3:
						skuChangeInfo.setDrop_1(ExcelHelper.covertBoolean(currentCell));
						break;
					case 4:
												
						skuChangeInfo.setLevel(ExcelHelper.checkStringNull(currentCell));				
						break;
					case 5:
						skuChangeInfo.setLevelId(ExcelHelper.checkStringNull(currentCell));
						break;
					case 6:
						skuChangeInfo.setDelete(ExcelHelper.covertBoolean(currentCell));
						break;
					case 7:
						if(currentCell != null) {
							Date rawDate = new Date(currentCell.toString());						
							skuChangeInfo.setEffectiveDate(ExcelHelper.dateFormat(rawDate));
						}
						break;
					default:
						break;
					}

					cellIdx++;
				}
					skuChangeInfo.setCreateDate(new Date());
					skuChangeInfo.setCreatedBy(tokenDto.getDisplayName());
					skuChangeInfo.setSource(MoicSources.MOIC_FILE_UPLOAD);
					skuChangeInfo.setUserId(tokenDto.getEmailAddress());
					skuChangeInfo.setUserName(tokenDto.getDisplayName());
					
					if (!skuChangeInfo.getLevel().isEmpty() && skuChangeInfo.getLevel().equalsIgnoreCase(MoicConstant.ACCOUNT) )
					{
						List<CustomerMaster>  customers=customerMasterRepository.findBySoldToNumberOrderByCreateDateDesc(skuChangeInfo.getLevelId());
						if(customers.isEmpty()) {
							LOGGER.error(skuChangeInfo.getSeason()+MoicConstant.UPLOAD_DROP_RULE3);
							continue;
						}
					}
					if (!skuChangeInfo.getLevel().isEmpty()&& skuChangeInfo.getLevel().equalsIgnoreCase(MoicConstant.CHANNEL) )
					{
						Channel channelObj=channelRepository.findByChannelNameIgnoreCase(skuChangeInfo.getLevelId());
						if(channelObj==null) {
							LOGGER.error(skuChangeInfo.getSeason()+MoicConstant.UPLOAD_DROP_RULE4);
							continue;
						}							
					}
					
					skuChanges.add(skuChangeInfo);
					LOGGER.debug(skuChangeInfo.getSeason()+"SKUChange data added in List");

			}

			workbook.close();
			return skuChanges;
		} catch (IOException e) {
			LOGGER.error("Fail to parse Excel file: " + e.getMessage());
			throw new RuntimeException(MoicConstant.SOMETHING_WENT_WRONG + "\nFail to parse Excel file");
		}
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public Map<String, Object> generateSKUChangeOverviewData(String season, String channel) {
		LOGGER.debug("Inside service method generateOverview");

		Map<Integer, String> monthHeader = MoicUtil.generateMonthHeader(joorOrderRepository.findAll());
		Map<Integer, Double> monthsTotal = MoicUtil.initMonthMap(monthHeader);
		
		LOGGER.debug("monthHeader :: " + monthHeader);
		List<MOQOverviewDto> moqOverviewDtos = new ArrayList<>();
		List<String> combinedSkuList = skuChangeRepository.getOldSkusByDeleteAndSeason(Boolean.FALSE, season);
		Map<String, MOQOverviewDto> moqOverviewDtoMap = MoicUtil.initMOQOverviewDtoMap(combinedSkuList, monthHeader);
		Map<String, Object> overviewResponseMap = new HashMap<>();
		
		for (String combinedSku : combinedSkuList) {
			MOQOverviewDto overviewDto = moqOverviewDtoMap.get(combinedSku);
			List<JoorOrderItem> joorOrderItems = joorOrderItemRepository.findByCombinedSku(combinedSku);
			Map<Integer, Double> monthOrder = MoicUtil.initMonthMap(monthHeader);
			
			for (JoorOrderItem joorOrderItem : joorOrderItems) {
				Optional<JoorOrder> optionalJoorOrder = joorOrderRepository.findById(joorOrderItem.getOrderID());
				if (optionalJoorOrder.isPresent()) {
					JoorOrder joorOrder = optionalJoorOrder.get();
					List<CustomerMaster> customerMaster = null;
					if(channel.equals(MoicConstant.CORPORATE)) {
						customerMaster = customerMasterRepository.findBySoldToNumberAndShipToNumberAndIsActiveOrderByCreateDateDesc(joorOrder.getCustomerCode()
								, joorOrder.getShippingCode(), Boolean.TRUE);
					}
					else {	
						customerMaster = customerMasterRepository.
								findBySoldToNumberAndChannelIDOrderByCreateDateDesc(joorOrder.getCustomerCode(),
										channelRepository.findByChannelNameIgnoreCase(channel).getChannelId());
					}
					
					if(null != joorOrder.getDateShipStart() && customerMaster.size() > 0) {
						Integer monthNumber = joorOrder.getDateShipStart().getMonth();
						overviewDto.setSku(combinedSku);
						for (Map.Entry<Integer, String> entry : monthHeader.entrySet()) {
						    if(entry.getKey().equals(monthNumber)) {
						    	Double itemTotalPrice = (null == joorOrderItem.getItemTotalUSD())?0:joorOrderItem.getItemTotalUSD();
						    	monthOrder.put(entry.getKey(), monthOrder.get(entry.getKey()) + itemTotalPrice);
						    	overviewDto.setAllMonthsTotal(overviewDto.getAllMonthsTotal() + itemTotalPrice);
						    	monthsTotal.put(entry.getKey(), monthsTotal.get(entry.getKey()) + itemTotalPrice);
						    }
						}
						overviewDto.setMonthOrder(monthOrder);
						moqOverviewDtoMap.put(combinedSku, overviewDto);
						
						LOGGER.debug("combinedSku:: " + combinedSku + " channelId:: " + customerMaster.get(0).getChannelID() 
								+ " monthNumber:: " +monthNumber + " itemTotalPrice:: " + joorOrderItem.getItemTotalUSD());
					}	
				}
			}
		}
		LOGGER.debug("moqOverviewDtos :: " + moqOverviewDtos);
		LOGGER.debug("monthsTotal :: " + monthsTotal);

		//preparing formated JSON and putting in response map
		Map<Integer, Object> overviewData = new HashMap<>();
		Integer mapIndex = -1;
		//preparing dynamic columns with month names
		List<String> overviewColumns = new ArrayList<String>();
		overviewColumns.add(MoicConstant.SKU);
		for (Map.Entry<Integer, String> entry : monthHeader.entrySet()) {
			overviewColumns.add(entry.getValue());
		}
		overviewColumns.add(MoicConstant.TOTAL);
		overviewData.put(++mapIndex, overviewColumns);
		
		//preparing SKU order overview
		List<String> sortedKeys = new ArrayList<>(moqOverviewDtoMap.keySet());
		Collections.sort(sortedKeys);
		for (String key: sortedKeys) {
			List<String> overviewRecords = new ArrayList<String>();
			overviewRecords.add(key);
			Map<Integer, Double> monthOrders = moqOverviewDtoMap.get(key).getMonthOrder();
			for (Map.Entry<Integer, Double> entry : monthOrders.entrySet()) {
				overviewRecords.add(String.format("%.2f", entry.getValue()));
			}
			overviewRecords.add(String.format("%.2f", moqOverviewDtoMap.get(key).getAllMonthsTotal()));
			overviewData.put(++mapIndex, overviewRecords);
		}
		
		//preparing lower total of orders
		List<String> overviewTotals = new ArrayList<String>();
		overviewTotals.add(MoicConstant.TOTAL);
		for (Map.Entry<Integer, Double> entry : monthsTotal.entrySet()) {
			overviewTotals.add(String.format("%.2f", entry.getValue()));
		}
		overviewTotals.add(String.format("%.2f", MoicUtil.sumMonthTotals(monthsTotal)));
		overviewData.put(++mapIndex, overviewTotals);
		
		overviewResponseMap.put(MoicConstant.MESSAGE, MoicConstant.SUCCESS_MESSAGE);
		overviewResponseMap.put(MoicConstant.DATA, overviewData);
		
		return overviewResponseMap;
	}

}