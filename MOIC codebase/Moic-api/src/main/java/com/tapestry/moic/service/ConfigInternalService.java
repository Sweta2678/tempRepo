package com.tapestry.moic.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
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
import com.tapestry.moic.dto.ConfigDto;
import com.tapestry.moic.dto.TokenDto;
import com.tapestry.moic.entity.Channel;
import com.tapestry.moic.entity.CustomerMaster;
import com.tapestry.moic.entity.Segment;
import com.tapestry.moic.entity.key.CustomerMasterKeys;
import com.tapestry.moic.repository.ChannelRepository;
import com.tapestry.moic.repository.CurrencyConversionRepository;
import com.tapestry.moic.repository.CustomerMasterRepository;
import com.tapestry.moic.repository.SegmentRepository;
import com.tapestry.moic.service.interfaces.IConfigInternalService;
import com.tapestry.moic.utils.ExcelHelper;
import com.tapestry.moic.utils.MoicUtil;

/**
 * The Class ConfigInternalService.
 * 
 * @version 0.0.1
 * 
 */
@Component
public class ConfigInternalService implements IConfigInternalService {
	/**
	 * Field LOGGER
	 */
	private Logger logger = LoggerFactory.getLogger(ConfigInternalService.class);

	@Autowired
	private CustomerMasterRepository customerMasterRepository;

	@Autowired
	private ChannelRepository channelRepository;

	@Autowired
	private SegmentRepository segmentRepository;
	
	@Autowired
	private CurrencyConversionRepository currencyConversionRepository;

	@Value("${config.report.customerReport.columnname}")
	String customerReportCustomerReportsheetName;

	@Value("${config.upload.customerReport.sheetname}")
	String customerReportSheetName;

	@Override
	public List<ConfigDto> getConfigDefaultData() {
		logger.debug("Inside service method getConfigDefaultData");
	
		int index = 0;
		List<ConfigDto> configDtos = new ArrayList<>();
		List<CustomerMaster> customerMasters = customerMasterRepository.findByIsActive(Boolean.TRUE);

		ObjectMapper mapper = new ObjectMapper();
		for (CustomerMaster customerMaster : customerMasters) {
			ConfigDto configDto = mapper.convertValue(customerMaster, ConfigDto.class);
			configDto.setIndex(++index);
			
			if (customerMaster.getChannelID() != null && customerMaster.getChannelID() > 0) {
				Optional<Channel> channelOptional = channelRepository.findById(customerMaster.getChannelID());
				if(channelOptional.isPresent())
					configDto.setChannel(channelOptional.get().getChannelName());
				else
					configDto.setChannel("");
				
			}
			if (customerMaster.getSegmentID() != null && customerMaster.getSegmentID() > 0) {
				Optional<Segment> segmentOptional = segmentRepository.findById(customerMaster.getSegmentID());
				if(segmentOptional.isPresent())
					configDto.setSegment(segmentOptional.get().getSegmentCode());
				else
					configDto.setSegment("");
			}
			configDto.setExcludeFromSOCreation(null == configDto.getExcludeFromSOCreation()?Boolean.FALSE:configDto.getExcludeFromSOCreation());
			configDto.setIsActive(null == configDto.getIsActive()?Boolean.FALSE:configDto.getIsActive());
			configDto.setSendToJoor(null == configDto.getSendToJoor()?Boolean.FALSE:configDto.getSendToJoor());
			
			if((configDto.getSuggestedRetailPriceCurrency() != null && !configDto.getSuggestedRetailPriceCurrency().isEmpty()) &&
				(configDto.getSuggestedRetailPriceCurrencyJoorPriceLabel() != null && !configDto.getSuggestedRetailPriceCurrencyJoorPriceLabel().isEmpty())	) {
				configDto.setSuggestedRetailPriceCurrency(configDto.getSuggestedRetailPriceCurrency() + "-" + configDto.getSuggestedRetailPriceCurrencyJoorPriceLabel());
			}
			if((configDto.getWholeSalePriceCurrency() != null && !configDto.getWholeSalePriceCurrency().isEmpty()) &&
					(configDto.getWholeSalePriceCurrencyJoorPriceLabel() != null && !configDto.getWholeSalePriceCurrencyJoorPriceLabel().isEmpty())	) {
				configDto.setWholeSalePriceCurrency(configDto.getWholeSalePriceCurrency() + "-" + configDto.getWholeSalePriceCurrencyJoorPriceLabel());
			}
			configDtos.add(configDto);
		}
		
		logger.debug(MessageFormat.format(" List<ConfigDto> values: {0}", configDtos));
		return configDtos;
	}

	@Override
	public void updateConfigData(ConfigDto dto, String token) {
		logger.debug("Inside service method updateConfigData");
		
		CustomerMasterKeys customerMasterKeys	=new CustomerMasterKeys();
		customerMasterKeys.setDistributionChannel(dto.getDistributionChannel());
		customerMasterKeys.setDivision(dto.getDivision());
		customerMasterKeys.setSalesOrg(dto.getSalesOrg());
		customerMasterKeys.setShipToNumber(dto.getShipToNumber());
		customerMasterKeys.setSite(dto.getSite());
		customerMasterKeys.setSoldToNumber(dto.getSoldToNumber());
		
		TokenDto tokenDto = MoicUtil.getDecryptedToken(MoicConstant.SESSION_TOKEN_ENCRYPTION_KEY, token);
		try {
		CustomerMaster customer = customerMasterRepository.findById(customerMasterKeys).get();
		if (customer != null) {
			customer.setSoldToNumber(dto.getSoldToNumber());
			customer.setSoldToDescription(dto.getSoldToDescription());
			customer.setShipToNumber(dto.getShipToNumber());
			customer.setShipToDescription(dto.getShipToDescription());
			customer.setSalesOrg(dto.getSalesOrg());
			customer.setDistributionChannel(dto.getDistributionChannel());
			customer.setDivision(dto.getDivision());
			if (dto.getChannel() != null && !dto.getChannel().isEmpty()) {
				customer.setChannelID(channelRepository.findByChannelNameIgnoreCase(dto.getChannel()).getChannelId());
			}
			customer.setExcludeFromSOCreation(dto.getExcludeFromSOCreation());
			customer.setSalesOrderTypePrimary(dto.getSalesOrderTypePrimary());
			customer.setSalesOrderTypeSecondary(dto.getSalesOrderTypeSecondary());
			customer.setSalesOrderTypeTertiary(dto.getSalesOrderTypeTertiary());
			customer.setImpactedByPreBuy(dto.getImpactedByPreBuy());
			if (dto.getSegment() != null && !dto.getSegment().isEmpty()) {
				customer.setSegmentID(segmentRepository.findBySegmentCodeIgnoreCase(dto.getSegment()).getSegmentId());
			}
			customer.setSite(dto.getSite());
			customer.setTarget(dto.getTarget());
			
			if(dto.getSuggestedRetailPriceCurrency() != null && dto.getSuggestedRetailPriceCurrency().contains("-")) {
				String [] suggestedRetailPrice = dto.getSuggestedRetailPriceCurrency().split("-");
				customer.setSuggestedRetailPriceCurrency(suggestedRetailPrice[0]);
				customer.setSuggestedRetailPriceCurrencyJoorPriceLabel(suggestedRetailPrice[1]);
			}
			if(dto.getWholeSalePriceCurrency() != null && dto.getWholeSalePriceCurrency().contains("-")) {
				String [] wholeSalePrice = dto.getWholeSalePriceCurrency().split("-");
				customer.setWholeSalePriceCurrency(wholeSalePrice[0]);
				customer.setWholeSalePriceCurrencyJoorPriceLabel(wholeSalePrice[1]);
			}
			customer.setIsActive(dto.getIsActive());
			customer.setSendToJoor(dto.getSendToJoor());
			customer.setSalesDocTypeforAltSite(dto.getSalesDocTypeforAltSite());
			customer.setAltSite(dto.getAltSite());
			// update required audit fields
			customer.setModifiedDate(new Date());
			customer.setModifiedBy(tokenDto.getDisplayName());
			customer.setSource(MoicSources.MOIC_UPDATE);
			customer.setDownloadFlag(Boolean.TRUE);
			customer.setUserId(tokenDto.getEmailAddress());
			customer.setUserName(tokenDto.getDisplayName());
			

			customerMasterRepository.save(customer);
			logger.debug("Config data updated to CustomerMaster entity");
		}
		}catch (Exception e) {	
			logger.error(e.getMessage());
			throw new RuntimeException(MoicConstant.SOMETHING_WENT_WRONG + "\nEncountered some issues while update ConfigData.");
		}
	}
	
	
	
	@Override
	public void saveConfigUploadExcel(String sheetName, MultipartFile file,int headersLength, String token) {
		
			logger.debug("Inside saveConfigUploadExcel .... ");

			List<CustomerMaster> customerMasters = excelToCustomerMasters(sheetName, file,headersLength);
			TokenDto tokenDto = MoicUtil.getDecryptedToken(MoicConstant.SESSION_TOKEN_ENCRYPTION_KEY, token);
			
			logger.debug("Inside service method updateConfigData");
			if(!customerMasters.isEmpty()) {
			for(CustomerMaster customerMaster :customerMasters) {
			
			CustomerMasterKeys customerMasterKeys	=new CustomerMasterKeys();
			customerMasterKeys.setDistributionChannel(customerMaster.getDistributionChannel());
			customerMasterKeys.setDivision(customerMaster.getDivision());
			customerMasterKeys.setSalesOrg(customerMaster.getSalesOrg());
			customerMasterKeys.setShipToNumber(customerMaster.getShipToNumber());
			customerMasterKeys.setSite(customerMaster.getSite());
			customerMasterKeys.setSoldToNumber(customerMaster.getSoldToNumber());
			try {
			Optional<CustomerMaster> customerdata = customerMasterRepository.findById(customerMasterKeys);
			if (customerdata.isPresent()) {
				CustomerMaster customer = customerdata.get();
				customer.setSoldToNumber(customerMaster.getSoldToNumber());
				customer.setSoldToDescription(customerMaster.getSoldToDescription());
				customer.setShipToNumber(customerMaster.getShipToNumber());
				customer.setShipToDescription(customerMaster.getShipToDescription());
				customer.setSalesOrg(customerMaster.getSalesOrg());
				customer.setDistributionChannel(customerMaster.getDistributionChannel());
				customer.setDivision(customerMaster.getDivision());
				customer.setChannelID(customerMaster.getChannelID());
		
				customer.setExcludeFromSOCreation(customerMaster.getExcludeFromSOCreation());
				customer.setSalesOrderTypePrimary(customerMaster.getSalesOrderTypePrimary());
				customer.setSalesOrderTypeSecondary(customerMaster.getSalesOrderTypeSecondary());
				customer.setSalesOrderTypeTertiary(customerMaster.getSalesOrderTypeTertiary());
				customer.setImpactedByPreBuy(customerMaster.getImpactedByPreBuy());
				customer.setSegmentID(customerMaster.getSegmentID());
			
				customer.setSite(customerMaster.getSite());
				customer.setTarget(customerMaster.getTarget());

				customer.setSuggestedRetailPriceCurrency(customerMaster.getSuggestedRetailPriceCurrency());
				customer.setSuggestedRetailPriceCurrencyJoorPriceLabel(customerMaster.getSuggestedRetailPriceCurrencyJoorPriceLabel());
				customer.setWholeSalePriceCurrency(customerMaster.getWholeSalePriceCurrency());
				customer.setWholeSalePriceCurrencyJoorPriceLabel(customerMaster.getWholeSalePriceCurrencyJoorPriceLabel());
				
				customer.setIsActive(customerMaster.getIsActive());
				customer.setSendToJoor(customerMaster.getSendToJoor());
				customer.setSalesDocTypeforAltSite(customerMaster.getSalesDocTypeforAltSite());
				customer.setAltSite(customerMaster.getAltSite());
				
				// update required audit fields
				customer.setModifiedDate(new Date());
				customer.setModifiedBy(tokenDto.getDisplayName());
				customer.setDownloadFlag(Boolean.TRUE);
				customer.setUserId(tokenDto.getEmailAddress());
				customer.setSource(MoicSources.MOIC_FILE_UPLOAD);

				customerMasterRepository.save(customer);
				logger.debug("Config data updated to CustomerMaster entity");
			}else {					
				customerMaster.setCreateDate(new Date());
				customerMaster.setCreatedBy(tokenDto.getDisplayName());
				customerMaster.setUserId(tokenDto.getEmailAddress());
				customerMaster.setSource(MoicSources.MOIC_FILE_UPLOAD);
				customerMasterRepository.save(customerMaster);
				logger.debug("Config data Saved to CustomerMaster entity ");
				
			}
			}catch (Exception e) {	
				logger.error(e.getMessage());
				throw new RuntimeException(MoicConstant.SOMETHING_WENT_WRONG + "\nEncountered some issues while updating ConfigData.");
			}

			
			}
		}		
			
	
	}
	
	
	

	private List<CustomerMaster> excelToCustomerMasters(String sheetName, MultipartFile file,int headersLength) {

		
		Workbook workbook = null;
		try {
			logger.debug(" Inside excelToCustomerMasters........");

			if (MoicConstant.EXCELTYPE.equals(file.getContentType()))
				workbook = new XSSFWorkbook(file.getInputStream());
			if (MoicConstant.EXCELTYPE2.equals(file.getContentType()))
				workbook = new HSSFWorkbook(file.getInputStream());

			Sheet sheet = workbook.getSheet(sheetName.trim());

			Iterator<Row> rows = sheet.iterator();

			List<CustomerMaster> customerMasters = new ArrayList<CustomerMaster>();
			logger.debug(" Inside excelToCustomerMasters.....sheet..." + sheet);
			int rowNumber = 0;
			while (rows.hasNext()) {
				Row currentRow = rows.next();
				if(ExcelHelper.isRowEmpty(currentRow)) {break;}
				// skip header
				if (rowNumber == 0) {
					
					rowNumber++;
					continue;
				}

				
				
				CustomerMaster customerMaster = new CustomerMaster();
				
				List<Cell> cells = new ArrayList<Cell>();
				int lastColumn = Math.max(currentRow.getLastCellNum(), headersLength);
				for (int cn = 0; cn < lastColumn; cn++) {
					Cell c = currentRow.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
					cells.add(c);
				}
				
				int cellIdx = 0;
				for(Cell currentCell :cells) {
							
					logger.debug(MessageFormat.format("Parse Excel file:cellIdx value : {0}",cellIdx));
					switch (cellIdx) {
					case 0:
						if (ExcelHelper.checkMandatory(currentCell)) {
							customerMaster.setSoldToNumber(ExcelHelper.checkStringNull(currentCell));
						}
						break;

					case 1:
						customerMaster.setSoldToDescription(ExcelHelper.checkStringNull(currentCell));
						break;

					case 2:
						if (ExcelHelper.checkMandatory(currentCell)) {
							customerMaster.setShipToNumber(ExcelHelper.checkStringNull(currentCell));
						}
						break;

					case 3:
						customerMaster.setShipToDescription(ExcelHelper.checkStringNull(currentCell));
						break;
						
					case 4:
						if (ExcelHelper.checkMandatory(currentCell)) {
							customerMaster.setSalesOrg(ExcelHelper.checkStringNull(currentCell));
						}
						break;
					case 5:
						if (ExcelHelper.checkMandatory(currentCell)) {
							customerMaster.setDistributionChannel(ExcelHelper.checkStringNull(currentCell));
						}
						break;
					case 6:
						if (ExcelHelper.checkMandatory(currentCell)) {
							customerMaster.setDivision(ExcelHelper.checkStringNull(currentCell));
						}
						break;
					case 7:
						if (ExcelHelper.checkStringNull(currentCell).trim().length() >= 1) {

							String channelName = currentCell.getStringCellValue();
							
							Channel channel=channelRepository.findByChannelNameIgnoreCase(channelName.trim());
							if(channel !=null) {
								Integer channelId = channel.getChannelId();
								if (channelId != null) {
									customerMaster.setChannelID(channelId);
								}							
							}else {
								throw new RuntimeException(MessageFormat.format("The file encountered some issues while processing channelId doesnot exist: {0}",channelName));
							}
						} else {
							customerMaster.setChannelID(null);
						}

						break;
					case 8:
						if (ExcelHelper.checkMandatory(currentCell)) {
							customerMaster.setExcludeFromSOCreation(ExcelHelper.covertBoolean(currentCell));
						}
						break;
					case 9:
						customerMaster.setSalesOrderTypePrimary(ExcelHelper.checkStringNull(currentCell));
						break;
					case 10:
						customerMaster.setSalesOrderTypeSecondary(ExcelHelper.checkStringNull(currentCell));
						break;
					case 11:
						customerMaster.setSalesOrderTypeTertiary(ExcelHelper.checkStringNull(currentCell));
						break;
					case 12:
						customerMaster.setImpactedByPreBuy(ExcelHelper.covertBoolean(currentCell));
						break;
					case 13:
						
						if (ExcelHelper.checkStringNull(currentCell).trim().length() >= 1) {
							
							String segmentName = currentCell.getStringCellValue();
							Segment segment =segmentRepository.findBySegmentCodeIgnoreCase(segmentName.trim());
							if (segment != null) {
								Integer segmentId = segment.getSegmentId();									
								if (segmentId != null) {
										customerMaster.setSegmentID(segmentId);
									} 
							}else {								
								throw new RuntimeException(MessageFormat.format("The file encountered some issues while processing segmentId doesnot exist: {0}",segmentName ));
							}
						} else {
							customerMaster.setSegmentID(null);
						}

						break;
					case 14:
						if (ExcelHelper.checkMandatory(currentCell)) {
							customerMaster.setSite(ExcelHelper.checkStringNull(currentCell));
						}
						break;
					case 15:
						customerMaster.setTarget((int)currentCell.getNumericCellValue());
						break;
					case 16:
						customerMaster.setSuggestedRetailPriceCurrency(
								currencyConversionRepository.getCurrencyCodeByMoicDisplaySuggestedRetailPrice(
										ExcelHelper.checkStringNull(currentCell)));
						customerMaster.setSuggestedRetailPriceCurrencyJoorPriceLabel(
								currencyConversionRepository.getJoorPriceLabelByMoicDisplaySuggestedRetailPrice(
										ExcelHelper.checkStringNull(currentCell)));
						break;
					case 17:
						customerMaster.setWholeSalePriceCurrency(
								currencyConversionRepository.getCurrencyCodeByMoicDisplayWholesalePrice(
										ExcelHelper.checkStringNull(currentCell)));
						customerMaster.setWholeSalePriceCurrencyJoorPriceLabel(
								currencyConversionRepository.getJoorPriceLabelByMoicDisplayWholesalePrice(
										ExcelHelper.checkStringNull(currentCell)));
						break;
					case 18:
						customerMaster.setIsActive(ExcelHelper.covertBoolean(currentCell));
						break;
					case 19:
						customerMaster.setSendToJoor(ExcelHelper.covertBoolean(currentCell));
						break;
					case 20:
						customerMaster.setSalesDocTypeforAltSite(ExcelHelper.checkStringNull(currentCell));
						break;
					case 21:
						customerMaster.setAltSite(ExcelHelper.checkStringNull(currentCell));
						break;
					default:
						break;
					}

					cellIdx++;
				}
								
				logger.debug(MessageFormat.format("Parse Excel file:customerMaster values : {0}", customerMaster));	
				customerMasters.add(customerMaster);
			} 

			workbook.close();
			return customerMasters;
		} catch (Exception e) {	
			logger.error(e.getMessage());
			throw new RuntimeException(MoicConstant.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public List<ConfigDto> getConfigReportData() {
		logger.debug("Inside service method getConfigReportData");
		
		List<ConfigDto> configDtos = new ArrayList<>();
		List<CustomerMaster> customerMasters = customerMasterRepository.findAllOrderedBySoldToNumber();
		
		ObjectMapper mapper = new ObjectMapper();
		for (CustomerMaster customerMaster : customerMasters) {
			ConfigDto configDto = mapper.convertValue(customerMaster, ConfigDto.class);
			configDto.setSuggestedRetailPriceCurrency(
					currencyConversionRepository.getMoicDisplaySuggestedRetailPriceByCurrencyCodeAndJoorPriceLabel(
					configDto.getSuggestedRetailPriceCurrency(), configDto.getSuggestedRetailPriceCurrencyJoorPriceLabel()));
			configDto.setWholeSalePriceCurrency(
					currencyConversionRepository.getMoicDisplayWholesalePriceByCurrencyCodeAndJoorPriceLabel(
							configDto.getWholeSalePriceCurrency(), configDto.getWholeSalePriceCurrencyJoorPriceLabel()));
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
		
		logger.debug(MessageFormat.format("List<ConfigDto> values : {0}", configDtos));
		return configDtos;
	}
	
	
	public ByteArrayInputStream downloadCustomerReportExcle(List<ConfigDto> configDtoList) {

		
		logger.debug(MessageFormat.format("*File columnname {0}", customerReportCustomerReportsheetName));
		String[] splitRow = customerReportCustomerReportsheetName.split(",");
		Workbook workBook = new XSSFWorkbook();

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
			dataRow.createCell(20).setCellValue(null == configDto.getSalesDocTypeforAltSite()? "":configDto.getSalesDocTypeforAltSite());
			dataRow.createCell(21).setCellValue(null == configDto.getAltSite()? "":configDto.getAltSite());
						
			sheet.autoSizeColumn(i);
			i = i + 1;
		}
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			workBook.write(outputStream);
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw new RuntimeException(MoicConstant.SOMETHING_WENT_WRONG + "\nThe file encountered some issues while processing.");	
		}
		finally
		{
			try {
				workBook.close();
			} catch (IOException e) {
				logger.error(MessageFormat.format("The file encountered some issues while processing : {0}", e.getMessage()));
				
			}
		}

		return new ByteArrayInputStream(outputStream.toByteArray());

	}

}
