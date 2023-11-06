package com.tapestry.moic.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tapestry.moic.dto.ConfigDto;
import com.tapestry.moic.entity.Channel;
import com.tapestry.moic.entity.CustomerMaster;
import com.tapestry.moic.entity.MOQ;
import com.tapestry.moic.entity.PreBuy;
import com.tapestry.moic.entity.Segment;
import com.tapestry.moic.entity.SkuChange;
import com.tapestry.moic.repository.ChannelRepository;
import com.tapestry.moic.repository.CustomerMasterRepository;
import com.tapestry.moic.repository.MOQRepository;
import com.tapestry.moic.repository.PreBuyRepository;
import com.tapestry.moic.repository.ProductSizeScaleMoaRepository;
import com.tapestry.moic.repository.SegmentRepository;
import com.tapestry.moic.repository.SkuChangeRepository;
import com.tapestry.moic.service.interfaces.IUserAuditInternalService;

@Component
public class UserAuditInternalService implements IUserAuditInternalService {

	@Autowired
	ProductSizeScaleMoaRepository productSizeScaleMoaRepository;

	private Logger LOGGER = LoggerFactory.getLogger(UserAuditInternalService.class);

	@Autowired
	SkuChangeRepository skuChangeRepository;

	@Autowired
	private CustomerMasterRepository customerMasterRepository;


	@Autowired
	private ChannelRepository channelRepository;

	@Autowired
	private SegmentRepository segmentRepository;

	@Autowired
	private MOQRepository moqRepository;	

	@Autowired
	private PreBuyRepository preBuyRepository;



	@Value("${user.audit.moq.sheetname}")
	String moqSheetName;

	@Value("${user.audit.moq.columnname}")
	String moqColumnname;

	@Value("${user.audit.prebuy.sheetname}")
	String prebuySheetName;

	@Value("${user.audit.prebuy.headers}")
	String preBuyHeaders;
	
	@Value("${user.audit.customer.columnname}")
	String customerReportCustomerReportsheetName;

	@Value("${user.audit.customer.sheetname}")
	String customerReportSheetName;
	
	@Value("${user.audit.skuchange.columnname}")
	private String skuChangeListColumnName;
	
	@Value("${user.audit.skuchange.sheetName}")
	private String skuChangeListSheetName;

	@Override
	public ByteArrayInputStream downloadUserAuditReport(String userId, String fromdate, String todate) {

		LOGGER.debug("Inside downloadUserAuditReport");

		Date fromDate = null;
		Date toDate = null;
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			fromDate = new SimpleDateFormat("MM/dd/yyyy").parse(fromdate);
			toDate = new SimpleDateFormat("MM/dd/yyyy").parse(todate);
		} catch (ParseException e1) {
			LOGGER.debug("Unable to ParseException"+e1.getMessage());
			
		}

		
		
		LOGGER.debug("Inside service method downloadUserAuditReport");
		if(fromDate!=null && toDate!=null) {
		List<ConfigDto> configDtos = new ArrayList<>();
		List<CustomerMaster> customerMasters = customerMasterRepository.findCustomerAuditDetail(userId, fromDate,
				toDate);
		
		ObjectMapper mapper = new ObjectMapper();
		for (CustomerMaster customerMaster : customerMasters) {
			ConfigDto configDto = mapper.convertValue(customerMaster, ConfigDto.class);
			if(customerMaster.getChannelID() != null && customerMaster.getChannelID() > 0) {
				Optional<Channel> channelOptional = channelRepository.findById(customerMaster.getChannelID());
				if(channelOptional.isPresent())
					configDto.setChannel(channelOptional.get().getChannelName());
				else
					configDto.setChannel("");
				
			}
			if(customerMaster.getSegmentID() != null && customerMaster.getSegmentID() > 0) {
				Optional<Segment> segmentOptional = segmentRepository.findById(customerMaster.getSegmentID());
				if(segmentOptional.isPresent())
					configDto.setSegment(segmentOptional.get().getSegmentCode());
				else
					configDto.setSegment("");
			}
			configDtos.add(configDto);
		}
		
		LOGGER.debug(MessageFormat.format("downloadUserAuditReport List<ConfigDto> values : {0}", configDtos));
		
		List<SkuChange> skuChangelist = skuChangeRepository.findSkuChangeAuditDetail(userId, fromDate, toDate);
		List<MOQ> moqList = moqRepository.findMOQAuditDetail(userId, fromDate, toDate);
		List<PreBuy> preBuyList = preBuyRepository.findPreBuyAuditDetail(userId, fromDate, toDate);

		

		
		Workbook workBook = new XSSFWorkbook();
		
		writeCustomer(workBook, configDtos);
		writeMOQ(workBook, moqList);
		writeSkuChange(workBook, skuChangelist);
		writePreBuy(workBook, preBuyList);

	

		try {
			workBook.write(outputStream);
		} catch (IOException e) {
			LOGGER.error("Inside downloadUserAuditReport() IOException in File writing :" + e.getMessage());
		} finally {
			try {
				workBook.close();
			} catch (IOException e) {
				LOGGER.error("Inside downloadUserAuditReport() IOException in File closing :" + e.getMessage());
			}
		}
		}
		return new ByteArrayInputStream(outputStream.toByteArray());

	}

	
	
	private void writeCustomer(Workbook workBook, List<ConfigDto> configDtoList) {
		
		String[] splitRow = customerReportCustomerReportsheetName.split(",");
	
		Sheet sheet = workBook.createSheet(customerReportSheetName);
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

		int i = 0;
		for (ConfigDto configDto : configDtoList) {

			Row dataRow = sheet.createRow(i + 1);
			
			dataRow.createCell(0).setCellValue(null==configDto.getSoldToNumber()? "": configDto.getSoldToNumber());
			dataRow.createCell(1).setCellValue(null==configDto.getSoldToDescription()? "":configDto.getSoldToDescription());
			dataRow.createCell(2).setCellValue(null==configDto.getShipToNumber()? "":configDto.getShipToNumber());
			dataRow.createCell(3).setCellValue(null==configDto.getShipToDescription()? "":configDto.getShipToDescription());
			dataRow.createCell(4).setCellValue(null==configDto.getSalesOrg()? "":configDto.getSalesOrg());
			dataRow.createCell(5).setCellValue(null==configDto.getDistributionChannel()? "":configDto.getDistributionChannel());
			dataRow.createCell(6).setCellValue(null==configDto.getDivision()? "":configDto.getDivision());
			dataRow.createCell(7).setCellValue(null==configDto.getChannel()? "":configDto.getChannel());
			dataRow.createCell(8).setCellValue(null == configDto.getExcludeFromSOCreation()? Boolean.FALSE:configDto.getExcludeFromSOCreation());	
			dataRow.createCell(9).setCellValue(null==configDto.getSalesOrderTypePrimary()? "":configDto.getSalesOrderTypePrimary());
			dataRow.createCell(10).setCellValue(null==configDto.getSalesOrderTypeSecondary()? "":configDto.getSalesOrderTypeSecondary());
			dataRow.createCell(11).setCellValue(null==configDto.getSalesOrderTypeTertiary()? "":configDto.getSalesOrderTypeTertiary());
			dataRow.createCell(12).setCellValue(null==configDto.getImpactedByPreBuy()? Boolean.FALSE:configDto.getImpactedByPreBuy());			
			dataRow.createCell(13).setCellValue(null==configDto.getSegment()? "":configDto.getSegment());
			dataRow.createCell(14).setCellValue(null==configDto.getSite()? "":configDto.getSite());			
			dataRow.createCell(15).setCellValue(null ==configDto.getTarget()? 0:configDto.getTarget());
			dataRow.createCell(16).setCellValue(null == configDto.getSuggestedRetailPriceCurrency()? "":configDto.getSuggestedRetailPriceCurrency());			
			dataRow.createCell(17).setCellValue(null == configDto.getWholeSalePriceCurrency()? "":configDto.getWholeSalePriceCurrency());			
			dataRow.createCell(18).setCellValue(null == configDto.getIsActive()? Boolean.FALSE:configDto.getIsActive());			
			dataRow.createCell(19).setCellValue(null == configDto.getSendToJoor()? Boolean.FALSE:configDto.getSendToJoor());
			dataRow.createCell(20).setCellValue(null == configDto.getSource() ? "" : configDto.getSource());
			dataRow.createCell(21).setCellValue(null==configDto.getModifiedDate() ?"": new SimpleDateFormat("MM/dd/yyyy").format(configDto.getModifiedDate() ));
			
			sheet.autoSizeColumn(i);
			i = i + 1;
		}

	}
	

	private void writeMOQ(Workbook workBook, List<MOQ> moqList) {
		LOGGER.debug("Inside writeMOQ() method");
		String[] splitRow = moqColumnname.split(",");

		Sheet sheet = workBook.createSheet(moqSheetName);
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

		int i = 0;
		for (MOQ moq : moqList) {

			Row dataRow = sheet.createRow(i + 1);

			dataRow.createCell(0).setCellValue(null == moq.getSeasonCode() ? "" : moq.getSeasonCode());
			dataRow.createCell(1).setCellValue(null == moq.getSku() ? "" : moq.getSku());
			dataRow.createCell(2).setCellValue(null == moq.getDelete() ? Boolean.FALSE : moq.getDelete());
			dataRow.createCell(3).setCellValue(null == moq.getEffectiveDate() ? ""
					: new SimpleDateFormat("MM/dd/yyyy").format(moq.getEffectiveDate()));
			dataRow.createCell(4).setCellValue(null == moq.getSource() ? "" : moq.getSource());
			dataRow.createCell(5).setCellValue(
					null == moq.getModifiedDate() ? "" :new SimpleDateFormat("MM/dd/yyyy").format(moq.getModifiedDate()));

			sheet.autoSizeColumn(i);
			i = i + 1;
		}
		

	}
	
	private void writeSkuChange(Workbook workBook, List<SkuChange> skuChangelist) {
		LOGGER.debug("Inside writeSkuChange() method");
		String[] splitRow = skuChangeListColumnName.split(",");
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
		for (SkuChange skuChange : skuChangelist) {

			Row dataRow = sheet.createRow(result + 1);

			dataRow.createCell(0).setCellValue(null ==skuChange.getSeason()? "" :skuChange.getSeason());
			dataRow.createCell(1).setCellValue(null ==skuChange.getOldSKU()? "" :skuChange.getOldSKU());
			dataRow.createCell(2).setCellValue(null ==skuChange.getNewSKU()? "" :skuChange.getNewSKU());
			dataRow.createCell(3).setCellValue(null ==skuChange.getDrop_1()?  Boolean.FALSE :skuChange.getDrop_1());
			dataRow.createCell(4).setCellValue(null ==skuChange.getLevel()? "" :skuChange.getLevel());
			dataRow.createCell(5).setCellValue(null ==skuChange.getLevelId()? "" :skuChange.getLevelId());
			dataRow.createCell(6).setCellValue(null ==skuChange.getDelete()?  Boolean.FALSE :skuChange.getDelete());
			dataRow.createCell(7).setCellValue(null ==skuChange.getEffectiveDate()?null: new SimpleDateFormat("MM/dd/yyyy").format(skuChange.getEffectiveDate()));
			dataRow.createCell(8).setCellValue(null == skuChange.getSource() ? "" : skuChange.getSource());
			dataRow.createCell(9).setCellValue(null == skuChange.getModifiedDate() ? "" :new SimpleDateFormat("MM/dd/yyyy").format(skuChange.getModifiedDate()));
			sheet.autoSizeColumn(result);
			result = result + 1;
		}

		
	}
	private void writePreBuy(Workbook workBook, List<PreBuy> preBuyList) {
		LOGGER.debug("Inside writePreBuy() method");
		String[] splitRow = preBuyHeaders.split(",");
		Sheet sheet = workBook.createSheet(prebuySheetName);
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

		int i = 0;
		for (PreBuy preBuy : preBuyList) {

			Row dataRow = sheet.createRow(i + 1);

			dataRow.createCell(0).setCellValue(null == preBuy.getSeasonCode() ? "" : preBuy.getSeasonCode());
			dataRow.createCell(1).setCellValue(null == preBuy.getSKU() ? "" : preBuy.getSKU());
			dataRow.createCell(2)
					.setCellValue(null == preBuy.getIsPreBuySKU() ? Boolean.FALSE : preBuy.getIsPreBuySKU());
			dataRow.createCell(3).setCellValue(null == preBuy.getCoo() ? "" : preBuy.getCoo());
			dataRow.createCell(4).setCellValue(null == preBuy.getSource() ? "" : preBuy.getSource());
			dataRow.createCell(5).setCellValue(null == preBuy.getModifiedDate() ? ""
					: new SimpleDateFormat("MM/dd/yyyy").format(preBuy.getModifiedDate()));

			sheet.autoSizeColumn(i);
			i = i + 1;
		}
		
	}
	

}
