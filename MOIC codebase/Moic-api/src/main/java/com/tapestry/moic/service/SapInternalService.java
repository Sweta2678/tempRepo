package com.tapestry.moic.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import com.tapestry.moic.constant.MoicConstant;
import com.tapestry.moic.constant.MoicSources;
import com.tapestry.moic.dto.TokenDto;
import com.tapestry.moic.entity.FinalizedBuy;
import com.tapestry.moic.entity.FinalizedBuyArchived;
import com.tapestry.moic.outbound.entity.FinalizedBuyOutBound;
import com.tapestry.moic.outbound.repository.FinalizedBuyOutboundRepository;
import com.tapestry.moic.repository.FinalizedBuyArchivedRepository;
import com.tapestry.moic.repository.FinalizedBuyRepository;
import com.tapestry.moic.repository.JoorOrderDivisionsRepository;
import com.tapestry.moic.repository.JoorOrderDoorsRepository;
import com.tapestry.moic.repository.JoorOrderItemRepository;
import com.tapestry.moic.repository.JoorOrderRepository;
import com.tapestry.moic.repository.JoorOrderStyleItemRepository;
import com.tapestry.moic.service.interfaces.ISapInternalService;
import com.tapestry.moic.utils.MoicUtil;

@Component
public class SapInternalService implements ISapInternalService {

	@Autowired
	FinalizedBuyRepository finalizedBuyRepository;

	@Autowired
	FinalizedBuyArchivedRepository finalizedBuyArchivedRepository;
	
	@Autowired
	FinalizedBuyOutboundRepository finalizedBuyOutboundRepository;
	
	@Autowired
	JoorOrderRepository joorRepository;

	@Autowired
	JoorOrderItemRepository joorItemRepository;

	@Autowired
	JoorOrderDivisionsRepository joorOrderDivRepo;

	@Autowired
	JoorOrderDoorsRepository joorOrderDoorsRep;
	
	@Autowired
	JoorOrderStyleItemRepository styleItemRepository;

	@Value("${sap.preview.report.columnname}")
	String columnname;
	
	@Value("${sap.preview.report.sheetname}")
	String sheetName;
	
	private Logger LOGGER = LoggerFactory.getLogger(SapInternalService.class);

	@Override
	public List<FinalizedBuy> sapUploadBySeasonAndChennel(List<String> season, String channel, String token) {
		LOGGER.debug(" sapUploadBySeasonAndChennel" + season+" : "+channel);
				
		List<FinalizedBuy> finalizedBuyList = new ArrayList<>();
		List<FinalizedBuy> resultList = new ArrayList<>();
		
		if(MoicConstant.CORPORATE.equalsIgnoreCase(channel))
			finalizedBuyList = finalizedBuyRepository.findBySeasonIn(season);
		else
			finalizedBuyList = finalizedBuyRepository.findByChannelAndSeasonIn(channel, season);
		
		TokenDto tokenDto = MoicUtil.getDecryptedToken(MoicConstant.SESSION_TOKEN_ENCRYPTION_KEY, token);		
	
		for (FinalizedBuy finalizedBuy : finalizedBuyList) {
			
			if(finalizedBuy.getJoorOrderType().equalsIgnoreCase("4"))
			{
				FinalizedBuyArchived finalizedBuyArchived=new FinalizedBuyArchived();
				//Date & Time of when button was clicked
				finalizedBuyArchived.setUploadDate(new Date());
				finalizedBuyArchived.setSKU(finalizedBuy.getSKU());
				finalizedBuyArchived.setDelete(finalizedBuy.getDelete());
				finalizedBuyArchived.setEffectiveDate(finalizedBuy.getEffectiveDate());
				finalizedBuyArchived.setDrop_1(finalizedBuy.getDrop_1());
				finalizedBuyArchived.setLevel(finalizedBuy.getLevel());
				finalizedBuyArchived.setLevelID(finalizedBuy.getLevelID());				
				finalizedBuyArchived.setOldSKU(finalizedBuy.getOldSKU());
				finalizedBuyArchived.setNewSKU(finalizedBuy.getNewSKU());
				finalizedBuyArchived.setPreBuySKU(finalizedBuy.getPreBuySKU());
				finalizedBuyArchived.setPoType(finalizedBuy.getPoType());
				finalizedBuyArchived.setCoo(finalizedBuy.getCoo());
				finalizedBuyArchived.setNewness(finalizedBuy.getNewness());
				finalizedBuyArchived.setOrderReason(finalizedBuy.getOrderReason());
				finalizedBuyArchived.setShippingInstruction(finalizedBuy.getShippingInstruction());
				//finalizedBuyArchived.setUploadDate(finalizedBuy.getUploadDate());						
				finalizedBuyArchived.setTotalBuyQuantity(finalizedBuy.getTotalBuyQuantity());						
				finalizedBuyArchived.setOrderNotes(finalizedBuy.getOrderNotes());
				finalizedBuyArchived.setScheduledDeliveryDate(finalizedBuy.getScheduledDeliveryDate());
				finalizedBuyArchived.setSoCancelDate(finalizedBuy.getSoCancelDate());
				finalizedBuyArchived.setPoNumber(finalizedBuy.getPoNumber());
				finalizedBuyArchived.setVendor(finalizedBuy.getVendor());
				finalizedBuyArchived.setSite(finalizedBuy.getSite());
				finalizedBuyArchived.setExcludeFromSOCreation(finalizedBuy.getExcludeFromSOCreation());
				finalizedBuyArchived.setSalesOrg(finalizedBuy.getSalesOrg());
				finalizedBuyArchived.setDistributionChannel(finalizedBuy.getDistributionChannel());
				finalizedBuyArchived.setDivision(finalizedBuy.getDivision());
				finalizedBuyArchived.setSoldToNumber(finalizedBuy.getSoldToNumber());
				finalizedBuyArchived.setShipToNumber(finalizedBuy.getShipToNumber());
				finalizedBuyArchived.setStockSegment(finalizedBuy.getStockSegment());
				finalizedBuyArchived.setSalesDocType(finalizedBuy.getSalesDocType());
				finalizedBuyArchived.setUpcCode(finalizedBuy.getUpcCode());
				finalizedBuyArchived.setChannel(finalizedBuy.getChannel());
				finalizedBuyArchived.setReqSegment(finalizedBuy.getReqSegment());
				finalizedBuyArchived.setTargetCost(finalizedBuy.getTargetCost());
				finalizedBuyArchived.setColorName(finalizedBuy.getColorName());
				finalizedBuyArchived.setColorCode(finalizedBuy.getColorCode());
				finalizedBuyArchived.setExFactMonth(finalizedBuy.getExFactMonth());
				finalizedBuyArchived.setSkuIntroDate(finalizedBuy.getSkuIntroDate());
				finalizedBuyArchived.setDepartment(finalizedBuy.getDepartment());
				finalizedBuyArchived.setParentsClass(finalizedBuy.getParentsClass());
				finalizedBuyArchived.setSeason(finalizedBuy.getSeason());
				finalizedBuyArchived.setStyleDescription(finalizedBuy.getStyleDescription());
				finalizedBuyArchived.setStorageLocation(finalizedBuy.getStorageLocation()); 
				finalizedBuyArchived.setStyleColor(finalizedBuy.getStyleColor());
				finalizedBuyArchived.setMaterialName(finalizedBuy.getMaterialName());
				finalizedBuyArchived.setStyleNumber(finalizedBuy.getStyleNumber());
				finalizedBuyArchived.setInStoreDate(finalizedBuy.getInStoreDate());
				finalizedBuyArchived.setPurchaseGroup(finalizedBuy.getPurchaseGroup());
				finalizedBuyArchived.setStyleName(finalizedBuy.getStyleName());
				finalizedBuyArchived.setRetailPrice(finalizedBuy.getRetailPrice());
				finalizedBuyArchived.setDepartmentDesc(finalizedBuy.getDepartmentDesc());
				finalizedBuyArchived.setClassDesc(finalizedBuy.getClassDesc());
				finalizedBuyArchived.setSubClassDesc(finalizedBuy.getSubClassDesc());
				finalizedBuyArchived.setSubClassCode(finalizedBuy.getSubClassCode());
				// update required audit fields for finalizedBuyArchived				
				finalizedBuyArchived.setCreateDate(new Date());				
				finalizedBuyArchived.setDownloadFlag(finalizedBuy.getDownloadFlag());
				finalizedBuyArchived.setLastDownloadDate(finalizedBuy.getLastDownloadDate());
				finalizedBuyArchived.setModifiedBy(finalizedBuy.getModifiedBy());
				finalizedBuyArchived.setModifiedDate(finalizedBuy.getModifiedDate());
				finalizedBuyArchived.setSoHeaderReqDelDate(finalizedBuy.getSoHeaderReqDelDate());			
				finalizedBuyArchived.setSource(finalizedBuy.getSource());
				finalizedBuyArchived.setTarget(finalizedBuy.getTarget());
				finalizedBuyArchived.setUploadDate(finalizedBuy.getUploadDate());
				finalizedBuyArchived.setOrderID(finalizedBuy.getOrderID());
				finalizedBuyArchived.setSoldToDescription(finalizedBuy.getSoldToDescription());
				finalizedBuyArchived.setWidth(finalizedBuy.getWidth());
				finalizedBuyArchived.setCountry(finalizedBuy.getCountry());
				finalizedBuyArchived.setCity(finalizedBuy.getCity());
				finalizedBuyArchived.setSize(finalizedBuy.getSize());
				finalizedBuyArchived.setUserId(tokenDto.getEmailAddress());
				finalizedBuyArchived.setUserName(tokenDto.getDisplayName());
				finalizedBuyArchived.setCreatedBy(tokenDto.getDisplayName());
				finalizedBuyArchived.setSource(MoicSources.MOIC_FILE_UPLOAD);

				FinalizedBuyOutBound finalizedBuyOutbound = null;
				FinalizedBuyOutBound existingFinalizedBuyOutbound = finalizedBuyOutboundRepository.findBySoldToNumberAndSeasonAndOldSKUAndNewSKUAndPoNumber(finalizedBuy.getSoldToNumber(),
						finalizedBuy.getSeason(), finalizedBuy.getOldSKU(), finalizedBuy.getNewSKU(), finalizedBuy.getPoNumber());
				if(existingFinalizedBuyOutbound != null)
					finalizedBuyOutbound = existingFinalizedBuyOutbound;
				else
					finalizedBuyOutbound = new FinalizedBuyOutBound();
				
				// Insert finalizedBuyOutbound
				//Date & Time of when button was clicked
				finalizedBuyOutbound.setUploadDate(new Date());
				finalizedBuyOutbound.setSKU(finalizedBuy.getSKU());
				finalizedBuyOutbound.setDelete(finalizedBuy.getDelete());
				finalizedBuyOutbound.setEffectiveDate(finalizedBuy.getEffectiveDate());
				finalizedBuyOutbound.setDrop_1(finalizedBuy.getDrop_1());
				finalizedBuyOutbound.setLevel(finalizedBuy.getLevel());
				finalizedBuyOutbound.setLevelID(finalizedBuy.getLevelID());				
				finalizedBuyOutbound.setOldSKU(finalizedBuy.getOldSKU());
				finalizedBuyOutbound.setNewSKU(finalizedBuy.getNewSKU());
				finalizedBuyOutbound.setPreBuySKU(finalizedBuy.getPreBuySKU());
				finalizedBuyOutbound.setPoType(finalizedBuy.getPoType());
				finalizedBuyOutbound.setCoo(finalizedBuy.getCoo());
				finalizedBuyOutbound.setNewness(finalizedBuy.getNewness());
				finalizedBuyOutbound.setOrderReason(finalizedBuy.getOrderReason());
				finalizedBuyOutbound.setShippingInstruction(finalizedBuy.getShippingInstruction());
				//finalizedBuyOutbound.setUploadDate(finalizedBuy.getUploadDate());						
				finalizedBuyOutbound.setTotalBuyQuantity(finalizedBuy.getTotalBuyQuantity());						
				finalizedBuyOutbound.setOrderNotes(finalizedBuy.getOrderNotes());
				finalizedBuyOutbound.setScheduledDeliveryDate(finalizedBuy.getScheduledDeliveryDate());
				finalizedBuyOutbound.setSoCancelDate(finalizedBuy.getSoCancelDate());
				finalizedBuyOutbound.setPoNumber(finalizedBuy.getPoNumber());
				finalizedBuyOutbound.setVendor(finalizedBuy.getVendor());
				finalizedBuyOutbound.setSite(finalizedBuy.getSite());
				finalizedBuyOutbound.setExcludeFromSOCreation(finalizedBuy.getExcludeFromSOCreation());
				finalizedBuyOutbound.setSalesOrg(finalizedBuy.getSalesOrg());
				finalizedBuyOutbound.setDistributionChannel(finalizedBuy.getDistributionChannel());
				finalizedBuyOutbound.setDivision(finalizedBuy.getDivision());
				finalizedBuyOutbound.setSoldToNumber(finalizedBuy.getSoldToNumber());
				finalizedBuyOutbound.setShipToNumber(finalizedBuy.getShipToNumber());
				finalizedBuyOutbound.setStockSegment(finalizedBuy.getStockSegment());
				finalizedBuyOutbound.setSalesDocType(finalizedBuy.getSalesDocType());
				finalizedBuyOutbound.setUpcCode(finalizedBuy.getUpcCode());
				finalizedBuyOutbound.setChannel(finalizedBuy.getChannel());
				finalizedBuyOutbound.setReqSegment(finalizedBuy.getReqSegment());
				finalizedBuyOutbound.setTargetCost(finalizedBuy.getTargetCost());
				finalizedBuyOutbound.setColorName(finalizedBuy.getColorName());
				finalizedBuyOutbound.setColorCode(finalizedBuy.getColorCode());
				finalizedBuyOutbound.setExFactMonth(finalizedBuy.getExFactMonth());
				finalizedBuyOutbound.setSkuIntroDate(finalizedBuy.getSkuIntroDate());
				finalizedBuyOutbound.setDepartment(finalizedBuy.getDepartment());
				finalizedBuyOutbound.setParentsClass(finalizedBuy.getParentsClass());
				finalizedBuyOutbound.setSeason(finalizedBuy.getSeason());
				finalizedBuyOutbound.setStyleDescription(finalizedBuy.getStyleDescription());
				finalizedBuyOutbound.setStorageLocation(finalizedBuy.getStorageLocation()); 
				finalizedBuyOutbound.setStyleColor(finalizedBuy.getStyleColor());
				finalizedBuyOutbound.setMaterialName(finalizedBuy.getMaterialName());
				finalizedBuyOutbound.setStyleNumber(finalizedBuy.getStyleNumber());
				finalizedBuyOutbound.setInStoreDate(finalizedBuy.getInStoreDate());
				finalizedBuyOutbound.setPurchaseGroup(finalizedBuy.getPurchaseGroup());
				finalizedBuyOutbound.setStyleName(finalizedBuy.getStyleName());
				finalizedBuyOutbound.setRetailPrice(finalizedBuy.getRetailPrice());
				// update required audit fields for finalizedBuyArchived				
				finalizedBuyOutbound.setCreateDate(new Date());				
				finalizedBuyOutbound.setDownloadFlag(finalizedBuy.getDownloadFlag());
				finalizedBuyOutbound.setLastDownloadDate(finalizedBuy.getLastDownloadDate());
				finalizedBuyOutbound.setModifiedBy(finalizedBuy.getModifiedBy());
				finalizedBuyOutbound.setModifiedDate(finalizedBuy.getModifiedDate());
				finalizedBuyOutbound.setSoHeaderReqDelDate(finalizedBuy.getSoHeaderReqDelDate());			
				finalizedBuyOutbound.setSource(finalizedBuy.getSource());
				finalizedBuyOutbound.setTarget(finalizedBuy.getTarget());
				finalizedBuyOutbound.setUploadDate(finalizedBuy.getUploadDate());
				finalizedBuyOutbound.setUserId(tokenDto.getEmailAddress());
				finalizedBuyOutbound.setUserName(tokenDto.getDisplayName());
				finalizedBuyOutbound.setCreatedBy(tokenDto.getDisplayName());
				finalizedBuyOutbound.setSource(MoicSources.MOIC_FILE_UPLOAD);
				
				finalizedBuyOutboundRepository.save(finalizedBuyOutbound);
				LOGGER.debug("sapUploadBySeasonAndChennel() : data saved finalizedBuyOutbound : " + finalizedBuyOutbound.getSeason()+" : "+finalizedBuyOutbound.getChannel());
				finalizedBuyArchivedRepository.save(finalizedBuyArchived);
				LOGGER.debug("sapUploadBySeasonAndChennel() : data saved finalizedBuyArchived :" + finalizedBuyArchived.getSeason()+" : "+finalizedBuyArchived.getChannel());
				resultList.add(finalizedBuy);				
				deleteJoorOrderFromFinalizedBuy(finalizedBuy.getOrderID());
				finalizedBuyRepository.delete(finalizedBuy);
				LOGGER.debug("sapUploadBySeasonAndChennel() : data delete finalizedBuy : ");	
			}
		}
		return resultList;
	}

	@Override
	public ByteArrayInputStream downloadPreviewS4(List<String> season, String channel) {
		LOGGER.debug("Inside downloadPreviewS4() columnname from tmpl" + columnname);

		List<FinalizedBuy> finalizedBuyList = new ArrayList<>();

		if(MoicConstant.CORPORATE.equalsIgnoreCase(channel))
			finalizedBuyList = finalizedBuyRepository.findBySeasonIn(season);
		else
			finalizedBuyList = finalizedBuyRepository.findByChannelAndSeasonIn(channel, season);

		String[] splitRow = columnname.split(",");
		Workbook workBook = new XSSFWorkbook();

		Sheet sheet = workBook.createSheet(sheetName);
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
		for (FinalizedBuy finalizedBuy : finalizedBuyList) {
			
			if(finalizedBuy.getJoorOrderType().equalsIgnoreCase("4"))
			{
				int cellIndex = 0;
				Row dataRow = sheet.createRow(result + 1);
				LOGGER.debug("Inside downloadPreviewS4() dataRow " + result);
				dataRow.createCell(cellIndex++).setCellValue(null == finalizedBuy.getOrderID() ? 0 : finalizedBuy.getOrderID());
				dataRow.createCell(cellIndex++).setCellValue(null == finalizedBuy.getChannel() ? "" : finalizedBuy.getChannel());
	
				dataRow.createCell(cellIndex++).setCellValue(finalizedBuy.getPreBuySKU());
	
				dataRow.createCell(cellIndex++).setCellValue(null == finalizedBuy.getSeason() ? "" : finalizedBuy.getSeason());
				dataRow.createCell(cellIndex++).setCellValue(null == finalizedBuy.getSKU() ? "" : finalizedBuy.getSKU());
				dataRow.createCell(cellIndex++).setCellValue(null == finalizedBuy.getNewSKU() ? "" : finalizedBuy.getNewSKU());
				dataRow.createCell(cellIndex++).setCellValue(null == finalizedBuy.getOldSKU() ? "" : finalizedBuy.getOldSKU());
				dataRow.createCell(cellIndex++)
						.setCellValue(null == finalizedBuy.getStyleColor() ? "" : finalizedBuy.getStyleColor());
				dataRow.createCell(cellIndex++)
						.setCellValue(null == finalizedBuy.getStyleNumber() ? "" : finalizedBuy.getStyleNumber());
				dataRow.createCell(cellIndex++)
						.setCellValue(null == finalizedBuy.getStyleName() ? "" : finalizedBuy.getStyleName());
				dataRow.createCell(cellIndex++)
						.setCellValue(null == finalizedBuy.getStyleName() ? "" : finalizedBuy.getStyleDescription());
				dataRow.createCell(cellIndex++)
						.setCellValue(null == finalizedBuy.getMaterialName() ? "" : finalizedBuy.getMaterialName());
				dataRow.createCell(cellIndex++)
						.setCellValue(null == finalizedBuy.getColorName() ? "" : finalizedBuy.getColorName());
				dataRow.createCell(cellIndex++)
						.setCellValue(null == finalizedBuy.getColorCode() ? "" : finalizedBuy.getColorCode());
				dataRow.createCell(cellIndex++).setCellValue(MoicUtil.stringdateToMMDDYYYY(finalizedBuy.getSkuIntroDate()));
				dataRow.createCell(cellIndex++)
						.setCellValue(null == finalizedBuy.getDepartment() ? "" : finalizedBuy.getDepartment());
				dataRow.createCell(cellIndex++)
						.setCellValue(null == finalizedBuy.getDepartmentDesc() ? "" : finalizedBuy.getDepartmentDesc());
				dataRow.createCell(cellIndex++)
						.setCellValue(null == finalizedBuy.getParentsClass() ? "" : finalizedBuy.getParentsClass());
				dataRow.createCell(cellIndex++)
						.setCellValue(null == finalizedBuy.getClassDesc() ? "" : finalizedBuy.getClassDesc());
				dataRow.createCell(cellIndex++)
						.setCellValue(null == finalizedBuy.getSubClassCode() ? "" : finalizedBuy.getSubClassCode());
				dataRow.createCell(cellIndex++)
						.setCellValue(null == finalizedBuy.getSubClassDesc() ? "" : finalizedBuy.getSubClassDesc());
	
				dataRow.createCell(cellIndex++).setCellValue(finalizedBuy.getRetailPrice());
				dataRow.createCell(cellIndex++).setCellValue(null == finalizedBuy.getWholesalePrice() ? 0 : finalizedBuy.getWholesalePrice());
				dataRow.createCell(cellIndex++).setCellValue(finalizedBuy.getRetailCurrency());
				dataRow.createCell(cellIndex++).setCellValue(finalizedBuy.getWholesaleCurrency());
				dataRow.createCell(cellIndex++).setCellValue(finalizedBuy.getTargetCost());
				dataRow.createCell(cellIndex++).setCellValue(null == finalizedBuy.getCoo() ? "" : finalizedBuy.getCoo());
				dataRow.createCell(cellIndex++).setCellValue(finalizedBuy.getTotalBuyQuantity());
				dataRow.createCell(cellIndex++)
						.setCellValue(MoicUtil.stringdateToMMDDYYYY(finalizedBuy.getScheduledDeliveryDate()));
				dataRow.createCell(cellIndex++)
						.setCellValue(null == finalizedBuy.getOrderNotes() ? "" : finalizedBuy.getOrderNotes());
				dataRow.createCell(cellIndex++)
						.setCellValue(null == finalizedBuy.getExFactMonth() ? "" : finalizedBuy.getExFactMonth());
	
				dataRow.createCell(cellIndex++).setCellValue(MoicUtil.stringdateToMMDDYYYY(finalizedBuy.getInStoreDate()));
				dataRow.createCell(cellIndex++).setCellValue(null == finalizedBuy.getVendor() ? "" : finalizedBuy.getVendor());
				dataRow.createCell(cellIndex++)
						.setCellValue(null == finalizedBuy.getPurchaseGroup() ? "" : finalizedBuy.getPurchaseGroup());
				dataRow.createCell(cellIndex++).setCellValue(
						null == finalizedBuy.getStyleDescription() ? "" : finalizedBuy.getStyleDescription());
				dataRow.createCell(cellIndex++).setCellValue(
						null == finalizedBuy.getStorageLocation() ? "" : finalizedBuy.getStorageLocation());
	
				dataRow.createCell(cellIndex++).setCellValue(null == finalizedBuy.getSite() ? "" : finalizedBuy.getSite());
				dataRow.createCell(cellIndex++)
						.setCellValue(null == finalizedBuy.getStockSegment() ? "" : finalizedBuy.getStockSegment());
				dataRow.createCell(cellIndex++).setCellValue(null == finalizedBuy.getNewness() ? "" : finalizedBuy.getNewness());
				dataRow.createCell(cellIndex++).setCellValue(
						null == finalizedBuy.getShippingInstruction() ? "" : finalizedBuy.getShippingInstruction());
				dataRow.createCell(cellIndex++).setCellValue(null ==finalizedBuy.getExcludeFromSOCreation()? false:finalizedBuy.getExcludeFromSOCreation());
				
				dataRow.createCell(cellIndex++)
						.setCellValue(null == finalizedBuy.getSalesDocType() ? "" : finalizedBuy.getSalesDocType());
				dataRow.createCell(cellIndex++)
						.setCellValue(null == finalizedBuy.getSalesOrg() ? "" : finalizedBuy.getSalesOrg());
				dataRow.createCell(cellIndex++).setCellValue(
						null == finalizedBuy.getDistributionChannel() ? "" : finalizedBuy.getDistributionChannel());
				dataRow.createCell(cellIndex++)
						.setCellValue(null == finalizedBuy.getDivision() ? "" : finalizedBuy.getDivision());
				dataRow.createCell(cellIndex++)
						.setCellValue(null == finalizedBuy.getSoldToNumber() ? "" : finalizedBuy.getSoldToNumber());
	
				dataRow.createCell(cellIndex++)
						.setCellValue(null == finalizedBuy.getShipToNumber() ? "" : finalizedBuy.getShipToNumber());
				dataRow.createCell(cellIndex++).setCellValue(MoicUtil.stringdateToMMDDYYYY(finalizedBuy.getSoCancelDate()));
				dataRow.createCell(cellIndex++).setCellValue(finalizedBuy.getPoNumber());
				dataRow.createCell(cellIndex++).setCellValue(null == finalizedBuy.getPoType() ? "" : finalizedBuy.getPoType());
	
				dataRow.createCell(cellIndex++)
						.setCellValue(null == finalizedBuy.getOrderReason() ? "" : finalizedBuy.getOrderReason());
				dataRow.createCell(cellIndex++)
						.setCellValue(null == finalizedBuy.getReqSegment() ? "" : finalizedBuy.getReqSegment());
				dataRow.createCell(cellIndex++).setCellValue(finalizedBuy.getTarget());
				dataRow.createCell(cellIndex++)
						.setCellValue(null == finalizedBuy.getOrderTotalUSD() ? 0 : finalizedBuy.getOrderTotalUSD());
				dataRow.createCell(cellIndex++).setCellValue(MoicUtil.stringdateToMMDDYYYY(finalizedBuy.getCancelDeliveryDate()));
				dataRow.createCell(cellIndex++).setCellValue(null == finalizedBuy.getDelete() ? false : finalizedBuy.getDelete());
				dataRow.createCell(cellIndex++).setCellValue(null == finalizedBuy.getDrop_1() ? false : finalizedBuy.getDrop_1());
				dataRow.createCell(cellIndex++).setCellValue(MoicUtil.stringdateToMMDDYYYY(finalizedBuy.getEffectiveDate()));
				dataRow.createCell(cellIndex++).setCellValue(null == finalizedBuy.getJoorOrderType() ? "" : finalizedBuy.getJoorOrderType());
				dataRow.createCell(cellIndex++).setCellValue(null == finalizedBuy.getLevel() ? "" : finalizedBuy.getLevel());
				dataRow.createCell(cellIndex++).setCellValue(null == finalizedBuy.getLevelID() ? "" : finalizedBuy.getLevelID());
				dataRow.createCell(cellIndex++).setCellValue(MoicUtil.stringdateToMMDDYYYY(finalizedBuy.getSoHeaderReqDelDate()));
				dataRow.createCell(cellIndex++).setCellValue(null == finalizedBuy.getUpcCode() ? "" : finalizedBuy.getUpcCode());
				dataRow.createCell(cellIndex++).setCellValue(MoicUtil.stringdateToMMDDYYYY(finalizedBuy.getUploadDate()));
				
				sheet.autoSizeColumn(result);
				result = result + 1;
			}
		}
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			workBook.write(outputStream);
		} catch (IOException e) {
			LOGGER.error("Inside downloadPreviewS4() IOException in File writing :" + e.getMessage());
		} finally {
			try {
				workBook.close();
			} catch (IOException e) {
				LOGGER.error("Inside downloadPreviewS4() IOException in File closing :" + e.getMessage());
			}
		}
		return new ByteArrayInputStream(outputStream.toByteArray());
	}
	
	private void deleteJoorOrderFromFinalizedBuy(Long orderId) {
		try {
			if(joorRepository.existsById(orderId)) {
				joorRepository.deleteById(orderId);
			}
			
			joorItemRepository.deleteOrderItemsByOrderId(orderId);
			styleItemRepository.deleteOrderStyleItemByOrderId(orderId);
			
			if(joorOrderDoorsRep.existsById(orderId)) {
				joorOrderDoorsRep.deleteById(orderId);
			}
			if(joorOrderDivRepo.existsById(orderId)) {
				joorOrderDivRepo.deleteById(orderId);
			}
			
			LOGGER.debug("sapUploadBySeasonAndChennel() : data delete finalizedBuy with Order Id: "+orderId);
		}
		catch(Exception e) {
			LOGGER.error("Failed to delete Joor Order from Finalizedbuy due to error" + e.getMessage() + " Joor Order Id: "
					+ orderId);
		}
	}
}
