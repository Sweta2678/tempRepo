package com.tapestry.moic.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tapestry.moic.constant.MoicConstant;
import com.tapestry.moic.constant.MoicSources;
import com.tapestry.moic.dto.MOQDto;
import com.tapestry.moic.dto.MOQOverviewDto;
import com.tapestry.moic.dto.PreBuyDto;
import com.tapestry.moic.dto.TokenDto;
import com.tapestry.moic.entity.CustomerMaster;
import com.tapestry.moic.entity.JoorOrder;
import com.tapestry.moic.entity.JoorOrderItem;
import com.tapestry.moic.entity.MOQ;
import com.tapestry.moic.entity.MOQReport;
import com.tapestry.moic.entity.PreBuy;
import com.tapestry.moic.entity.ProductSizeScaleMoa;
import com.tapestry.moic.repository.ChannelRepository;
import com.tapestry.moic.repository.CustomerMasterRepository;
import com.tapestry.moic.repository.JoorOrderItemRepository;
import com.tapestry.moic.repository.JoorOrderRepository;
import com.tapestry.moic.repository.MOQReportRepository;
import com.tapestry.moic.repository.MOQRepository;
import com.tapestry.moic.repository.PreBuyRepository;
import com.tapestry.moic.repository.ProductSizeScaleMoaRepository;
import com.tapestry.moic.service.interfaces.IMOQInternalService;
import com.tapestry.moic.utils.ExcelHelper;
import com.tapestry.moic.utils.MoicUtil;

/**
 * The Class MOQInternalService.
 * 
 * @version 0.0.1
 * 
 */
@Component
public class MOQInternalService implements IMOQInternalService {
	/**
	 * Field LOGGER
	 */
	private Logger LOGGER = LoggerFactory.getLogger(MOQInternalService.class);

	@Autowired
	private MOQRepository moqRepository;
	@Autowired
	private MOQReportRepository moqReportRepository;

	@Autowired
	private PreBuyRepository preBuyRepository;

	@Autowired
	private CustomerMasterRepository customerMasterRepository;

	@Autowired
	private JoorOrderItemRepository joorOrderItemRepository;

	@Autowired
	private JoorOrderRepository joorOrderRepository;

	@Autowired
	private ChannelRepository channelRepository;
	
	@Autowired
	private ProductSizeScaleMoaRepository productSizeScaleMoaRepository;

	@Value("${moq.download.list.columnname}")
	String downloadListColumnname;

	@Value("${moq.download.list.sheetName}")
	String sheetName;

	@Value("${prebuy.upload.sheetname}")
	String prebuySheetName;

	@Value("${prebuy.upload.headers}")
	String preBuyHeaders;

	@Value("${moq.report.moqlist.columnname}")
	String moqListColumns;

	@Value("${moq.report.moqlist.sheetName}")
	String moqListSheetName;

	@Value("${moqLowerTableLabels}")
	String moqLowerTableLabels;

	@Value("${moq.download.sheetname}")
	String moqSheetName;

	@Value("${moq.download.columnname}")
	String moqColumnname;

	@Value("${moq.upload.filename}")
	String moqFileName;

	@Override
	public Map<String, Object> getMOQDefaultData(String season, String channel) {
		LOGGER.debug("Inside service method getMOQDefaultData");
		Map<String, Object> moqResponseMap = new HashMap<>();

		List<MOQDto> moqDtos = new ArrayList<>();

		List<MOQ> moqs = moqRepository.findByDeleteAndSeasonCode(Boolean.FALSE, season);

		List<String> moqLowerTableLabelsList = MoicUtil.getScreenLabelList(moqLowerTableLabels);

		ObjectMapper mapper = new ObjectMapper();
		for (MOQ moq : moqs) {
			MOQDto moqDto = mapper.convertValue(moq, MOQDto.class);
			moqDto.setDelete(null == moqDto.getDelete() ? Boolean.FALSE : moqDto.getDelete());
			moqDtos.add(moqDto);
		}

		moqResponseMap.put(MoicConstant.COLUMNS, moqLowerTableLabelsList);
		if (moqDtos != null && moqDtos.size() > 0) {
			moqResponseMap.put(MoicConstant.MESSAGE, MoicConstant.SUCCESS_MESSAGE);
			moqResponseMap.put(MoicConstant.DATA, moqDtos);
		} else {
			moqResponseMap.put(MoicConstant.MESSAGE, MoicConstant.NO_RECORDS_FOUND);
			moqResponseMap.put(MoicConstant.DATA, null);
		}

		return moqResponseMap;
	}

	@Override
	public List<PreBuyDto> getPreBuyDefaultData() {
		LOGGER.debug("Inside service method getPreBuyDefaultData");

		return ((List<PreBuy>) preBuyRepository.findAll()).stream().map(this::convertToPreBuyDTO)
				.collect(Collectors.toList());

	}

	private PreBuyDto convertToPreBuyDTO(PreBuy preBuy) {

		PreBuyDto preBuyDto = new PreBuyDto();

		preBuyDto.setSku(preBuy.getSKU());
		preBuyDto.setSeasonCode(preBuy.getSeasonCode());

		return preBuyDto;
	}

	@Override
	public Boolean updateMOQData(MOQDto moqDto, String token) {
		LOGGER.debug("Inside service method updateMOQData");
		TokenDto tokenDto = MoicUtil.getDecryptedToken(MoicConstant.SESSION_TOKEN_ENCRYPTION_KEY, token);
		List<ProductSizeScaleMoa> productSizeScaleMoas = productSizeScaleMoaRepository.findByCombinedSku(moqDto.getSku().trim());
		if (!productSizeScaleMoas.isEmpty()) {
			LOGGER.debug(moqDto.getSeasonCode() + "Data matched " + productSizeScaleMoas);

			MOQ moqCheck = moqRepository.findByIdAuto(moqDto.getIdAuto());
			if (moqCheck != null) {
				moqCheck.setSku(moqDto.getSku());
				moqCheck.setSeasonCode(moqDto.getSeasonCode());
				moqCheck.setDelete(moqDto.getDelete());
				moqCheck.setEffectiveDate(moqDto.getEffectiveDate());
				// update required audit fields
				moqCheck.setModifiedBy(tokenDto.getDisplayName());
				moqCheck.setSource(MoicSources.MOIC_UPDATE);
				moqCheck.setModifiedDate(new Date());
				moqCheck.setUserId(tokenDto.getEmailAddress());
				moqCheck.setUserName(tokenDto.getDisplayName());
				moqRepository.save(moqCheck);
				LOGGER.debug("Old MOQ data updated :: " + moqDto);
				return Boolean.TRUE;
			}
		}
		LOGGER.debug(moqDto.getSeasonCode() + "Data not Found ProductSKU " + productSizeScaleMoas);
		return Boolean.FALSE;
	}

	@Override
	public String saveMOQ(String sheetName, MultipartFile file ,int headersLength, String token) {
		try {
			LOGGER.debug("Inside saveConfigUploadExcel .... ");
			List<MOQ> moqs = ExcelHelper.excelToMOQs(sheetName, file, headersLength);

			List<String> saveResult = new ArrayList<String>();
			List<String> updateResult = new ArrayList<String>();
			List<String> notFoundResult = new ArrayList<String>();
			TokenDto tokenDto = MoicUtil.getDecryptedToken(MoicConstant.SESSION_TOKEN_ENCRYPTION_KEY, token);

			for (MOQ moq : moqs) {
					List<ProductSizeScaleMoa> productSizeScaleMoas = productSizeScaleMoaRepository.findByCombinedSku(moq.getSku().trim());
					if (!productSizeScaleMoas.isEmpty()) {
						MOQ moqCheck = moqRepository.findBySeasonCodeAndSku(moq.getSeasonCode().trim(), moq.getSku().trim());
						if (moqCheck != null) {
							moqCheck.setSeasonCode(null==moq.getSeasonCode()?"":moq.getSeasonCode());
							moqCheck.setSku(null==moq.getSku()?"":moq.getSku());
							moqCheck.setDelete(null==moq.getDelete()?Boolean.FALSE:moq.getDelete());
							moqCheck.setEffectiveDate(moq.getEffectiveDate());
							// update required audit fields								
							moqCheck.setModifiedDate(new Date());
							moqCheck.setModifiedBy(tokenDto.getDisplayName());
							moqCheck.setSource(MoicSources.MOIC_FILE_UPLOAD);
							moqCheck.setUserId(tokenDto.getEmailAddress());
							moqCheck.setUserName(tokenDto.getDisplayName());
														
							moqRepository.save(moqCheck);
							LOGGER.debug("MOQ Data update successfully........");
							updateResult.add(moq.getSku());
						} else {
							moq.setCreateDate(new Date());
							moq.setCreatedBy(tokenDto.getDisplayName());
							moq.setSource(MoicSources.MOIC_FILE_UPLOAD);
							moq.setUserId(tokenDto.getEmailAddress());
							moq.setUserName(tokenDto.getDisplayName());
							
							moqRepository.save(moq);
							
							LOGGER.debug("MOQ Data saved successfully........");
							saveResult.add(moq.getSku());
						}

					} else {
						notFoundResult.add(moq.getSku());
					}
				} 
			String result="";
			if(!saveResult.isEmpty() || !updateResult.isEmpty())
				result += MoicConstant.UPLOAD_FILE_SUCCESS;			
			if(!notFoundResult.isEmpty())	
				result += MoicConstant.UPLOAD_MOQ_FILE_SKU_NOT_FOUND + notFoundResult.toString();	

			return  result;

		} catch (Exception e) {
			LOGGER.error("fail to store  MOQ List excel data: " + e.getMessage());
			throw new RuntimeException(MoicConstant.SOMETHING_WENT_WRONG + "\nFail to store MOQ List excel data");
		}
	}

	@Override
	public String savePreBuy(String sheetName, MultipartFile file,int headersLength, String token) {

		List<String> saveUpdateResult = new ArrayList<String>();
		
		List<String> notFoundResult = new ArrayList<String>();
		
		TokenDto tokenDto = MoicUtil.getDecryptedToken(MoicConstant.SESSION_TOKEN_ENCRYPTION_KEY, token);
		
		try {
			List<PreBuyDto> preBuys = ExcelHelper.excelToPreBuys(sheetName, file,headersLength);
			
			for(PreBuyDto preBuy:preBuys) { 

				PreBuy checkpreBuy	=preBuyRepository.findBySeasonCodeAndSKU(preBuy.getSeasonCode(), preBuy.getSku());
				if(checkpreBuy==null) {
				PreBuy preBuyObj= new PreBuy();
				preBuyObj.setSeasonCode(preBuy.getSeasonCode());
				preBuyObj.setSKU(preBuy.getSku());
				preBuyObj.setIsPreBuySKU(preBuy.getIsPreBuySKU());
				preBuyObj.setCoo(preBuy.getCoo());
				preBuyObj.setCreateDate(new Date());
				preBuyObj.setCreatedBy(tokenDto.getDisplayName());
				preBuyObj.setSource(MoicSources.MOIC_FILE_UPLOAD);
				preBuyObj.setUserId(tokenDto.getEmailAddress());
				preBuyObj.setUserName(tokenDto.getDisplayName());
				preBuyRepository.save(preBuyObj);					
				LOGGER.debug("PreBuyData saved successfully."+preBuy.getSeasonCode());
				}else {
					BeanUtils.copyProperties(preBuy, checkpreBuy, "SKU", "seasonCode" );
					checkpreBuy.setModifiedDate(new Date());
					checkpreBuy.setModifiedBy(tokenDto.getDisplayName());
					checkpreBuy.setSource(MoicSources.MOIC_FILE_UPLOAD);
					checkpreBuy.setUserId(tokenDto.getEmailAddress());
					checkpreBuy.setUserName(tokenDto.getDisplayName());
					preBuyRepository.save(checkpreBuy);
					LOGGER.debug("PreBuyData updated successfully."+checkpreBuy.getSeasonCode());
					
				}
				saveUpdateResult.add(preBuy.getSku());
			}
			
			String result="";
			if(!saveUpdateResult.isEmpty())
				result+=MoicConstant.UPLOAD_FILE_SUCCESS;			
			if(!notFoundResult.isEmpty())	
				result+=MoicConstant.UPLOAD_FILE_PREBUY_SKU_NOT_FOUND+notFoundResult.toString();	

			return  result;
			
		} catch (Exception e) {
			LOGGER.error("fail to store excel data: " + e.getMessage());
			throw new RuntimeException(MoicConstant.SOMETHING_WENT_WRONG +"\nFail to store PreBuy List excel data");
		}

	}

	public ByteArrayInputStream moqDownloadBySeasonAndChannel(List<String> season, String channel) {

		LOGGER.debug("Inside moqDownloadBySeasonAndChannel() columnname from tmpl" + moqColumnname);

		List<MOQReport> moqReportList = new ArrayList<>();
		if(MoicConstant.CORPORATE.equalsIgnoreCase(channel))
			moqReportList = moqReportRepository.findBySeasonIn(season);
		else
			moqReportList = moqReportRepository.findByChannelAndSeasonIn(channel, season);

		String[] splitRow = moqColumnname.split(",");
		Workbook workBook = new XSSFWorkbook();

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

		int result = 0;
		for (MOQReport moqReport : moqReportList) {
			
			int cellIndex = 0;
			Row dataRow = sheet.createRow(result + 1);
			LOGGER.debug("Inside moqDownloadBySeasonAndChannel() dataRow" + dataRow.getPhysicalNumberOfCells());

			dataRow.createCell(cellIndex++).setCellValue(moqReport.getCreatedBy());// status
			dataRow.createCell(cellIndex++).setCellValue(null == moqReport.getDepartment() ? "" : moqReport.getDepartment());// Business
																													// Unit
			dataRow.createCell(cellIndex++).setCellValue("");// File Name (SAP upload files)

			dataRow.createCell(cellIndex++).setCellValue(null == moqReport.getSKU() ? "" : moqReport.getSKU());
			dataRow.createCell(cellIndex++).setCellValue(null == moqReport.getStyleColor() ? "" : moqReport.getStyleColor());
			dataRow.createCell(cellIndex++).setCellValue(null == moqReport.getWidth() ? "" : moqReport.getWidth());
			dataRow.createCell(cellIndex++).setCellValue(null == moqReport.getSize() ? "" : moqReport.getSize());
			dataRow.createCell(cellIndex++).setCellValue(null == moqReport.getEuSize() ? "" : moqReport.getEuSize());
			dataRow.createCell(cellIndex++).setCellValue(null == moqReport.getStyleNumber() ? "" : moqReport.getStyleNumber());
			dataRow.createCell(cellIndex++).setCellValue(null == moqReport.getStyleName() ? "" : moqReport.getStyleName());
			dataRow.createCell(cellIndex++).setCellValue(null == moqReport.getMaterialName() ? "" : moqReport.getMaterialName());
			dataRow.createCell(cellIndex++).setCellValue(null == moqReport.getColorName() ? "" : moqReport.getColorName());
			dataRow.createCell(cellIndex++).setCellValue(null == moqReport.getColorCode() ? "" : moqReport.getColorCode());
			dataRow.createCell(cellIndex++).setCellValue(MoicUtil.stringdateToMMDDYYYY(moqReport.getSkuIntroDate()));
			dataRow.createCell(cellIndex++).setCellValue(null == moqReport.getDepartment() ? "" : moqReport.getDepartment());
			dataRow.createCell(cellIndex++).setCellValue(null == moqReport.getParentsClass() ? "" : moqReport.getParentsClass());
			dataRow.createCell(cellIndex++).setCellValue(moqReport.getRetailPrice());
			dataRow.createCell(cellIndex++).setCellValue(moqReport.getTargetCost());
			dataRow.createCell(cellIndex++).setCellValue(null == moqReport.getCoo() ? "" : moqReport.getCoo());
			dataRow.createCell(cellIndex++).setCellValue(moqReport.getTotalBuyQuantity());
			dataRow.createCell(cellIndex++).setCellValue(MoicUtil.stringdateToMMDDYYYY(moqReport.getScheduledDeliveryDate()));
			dataRow.createCell(cellIndex++).setCellValue(null == moqReport.getSalesDocType() ? "" : moqReport.getSalesDocType());
			dataRow.createCell(cellIndex++).setCellValue(null == moqReport.getSalesOrg() ? "" : moqReport.getSalesOrg());
			dataRow.createCell(cellIndex++)
					.setCellValue(null == moqReport.getDistributionChannel() ? "" : moqReport.getDistributionChannel());
			dataRow.createCell(cellIndex++).setCellValue(null == moqReport.getDivision() ? "" : moqReport.getDivision());
			dataRow.createCell(cellIndex++).setCellValue(null == moqReport.getSoldToNumber() ? "" : moqReport.getSoldToNumber());
			dataRow.createCell(cellIndex++).setCellValue(null == moqReport.getSoldToDescription() ? "" : moqReport.getSoldToDescription());
			dataRow.createCell(cellIndex++).setCellValue(null == moqReport.getCountry() ? "" : moqReport.getCountry());
			dataRow.createCell(cellIndex++).setCellValue(null == moqReport.getCity() ? "" : moqReport.getCity());
			dataRow.createCell(cellIndex++).setCellValue(null == moqReport.getShipToNumber() ? "" : moqReport.getShipToNumber());
			dataRow.createCell(cellIndex++).setCellValue(MoicUtil.stringdateToMMDDYYYY(moqReport.getSoHeaderReqDelDate()));
			dataRow.createCell(cellIndex++).setCellValue(MoicUtil.stringdateToMMDDYYYY(moqReport.getSoCancelDate()));
			dataRow.createCell(cellIndex++).setCellValue(moqReport.getPoNumber());
			dataRow.createCell(cellIndex++).setCellValue(null == moqReport.getSite() ? "" : moqReport.getSite());
			dataRow.createCell(cellIndex++).setCellValue(null == moqReport.getReqSegment() ? "" : moqReport.getReqSegment());
			dataRow.createCell(cellIndex++).setCellValue(null == moqReport.getSeason() ? "" : moqReport.getSeason());// MOQ
			dataRow.createCell(cellIndex++).setCellValue(null == moqReport.getSKU() ? "" : moqReport.getSKU());
			dataRow.createCell(cellIndex++).setCellValue(null == moqReport.getDelete() ? false : moqReport.getDelete());
			dataRow.createCell(cellIndex++).setCellValue(MoicUtil.stringdateToMMDDYYYY(moqReport.getEffectiveDate()));
			dataRow.createCell(cellIndex++)
					.setCellValue(null == moqReport.getOrderTotalUSD() ? 0 : moqReport.getOrderTotalUSD());
			sheet.autoSizeColumn(result);
			result = result + 1;
		}
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			workBook.write(outputStream);
		} catch (IOException e) {
			LOGGER.error("Inside moqDownloadBySeasonAndChannel() IOException in File writing :" + e.getMessage());
		} finally {
			try {
				workBook.close();
			} catch (IOException e) {
				LOGGER.error("Inside moqDownloadBySeasonAndChannel() IOException in File closing :" + e.getMessage());
			}
		}
		return new ByteArrayInputStream(outputStream.toByteArray());

	}

	public ByteArrayInputStream downloadMoqList(String season) {

		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern(MoicConstant.DDMMYYYYHHMMSS);

		String formattedDate = myDateObj.format(myFormatObj);
		String moqListFileName = moqFileName + "_" + season + "_" + formattedDate;
		List<MOQ> moqList = moqRepository.findBySeasonCode(season.trim());

		String[] splitRow = downloadListColumnname.split(",");
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

		int i = 0;
		for (MOQ moq : moqList) {

			Row dataRow = sheet.createRow(i + 1);

			dataRow.createCell(0).setCellValue(null==moq.getSeasonCode()?"":moq.getSeasonCode());
			dataRow.createCell(1).setCellValue(null==moq.getSku()?"":moq.getSku());
			dataRow.createCell(2).setCellValue(null==moq.getDelete()?Boolean.FALSE:moq.getDelete());
			dataRow.createCell(3).setCellValue(null==moq.getEffectiveDate()?"": new SimpleDateFormat("MM/dd/yyyy").format(moq.getEffectiveDate()));
			
			sheet.autoSizeColumn(i);
			i = i + 1;
		}
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			workBook.write(outputStream);
		} catch (IOException e) {
			LOGGER.error(moqListFileName + MoicConstant.UPLOAD_FILE_EXCEPTION + e.getMessage());
		} finally {
			try {
				workBook.close();
			} catch (IOException e) {
				LOGGER.error(moqListFileName + MoicConstant.UPLOAD_FILE_EXCEPTION + e.getMessage());
			}
		}
		return new ByteArrayInputStream(outputStream.toByteArray());

	}

	@SuppressWarnings("deprecation")
	@Override
	public Map<String, Object> generateMOQOverviewData(String season, String channel) {
		LOGGER.debug("Inside service method generateOverview");

		Map<Integer, String> monthHeader = MoicUtil.generateMonthHeader(joorOrderRepository.findAll());
		Map<Integer, Double> monthsTotal = MoicUtil.initMonthMap(monthHeader);

		LOGGER.debug("monthHeader :: " + monthHeader);
		List<MOQOverviewDto> moqOverviewDtos = new ArrayList<>();
		List<String> combinedSkuList = moqRepository.getSkusByDeleteAndSeasonCode(Boolean.FALSE, season);
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
					if (channel.equals(MoicConstant.CORPORATE)) {
						customerMaster = customerMasterRepository
								.findBySoldToNumberAndShipToNumberAndIsActiveOrderByCreateDateDesc(joorOrder.getCustomerCode(),
								joorOrder.getShippingCode(), Boolean.TRUE);
					} else {
						customerMaster = customerMasterRepository.findBySoldToNumberAndChannelIDOrderByCreateDateDesc(
								joorOrder.getCustomerCode(),
								channelRepository.findByChannelNameIgnoreCase(channel).getChannelId());
					}

					if (null != joorOrder.getDateShipStart() && customerMaster.size() > 0) {
						Integer monthNumber = joorOrder.getDateShipStart().getMonth();
						overviewDto.setSku(combinedSku);
						for (Map.Entry<Integer, String> entry : monthHeader.entrySet()) {
							if (entry.getKey().equals(monthNumber)) {
								Integer itemTotalQuantity = (null == joorOrderItem.getItemQuantity()) ? 0
										: joorOrderItem.getItemQuantity();
								monthOrder.put(entry.getKey(), monthOrder.get(entry.getKey()) + itemTotalQuantity);
								overviewDto.setAllMonthsTotal(overviewDto.getAllMonthsTotal() + itemTotalQuantity);
								monthsTotal.put(entry.getKey(), monthsTotal.get(entry.getKey()) + itemTotalQuantity);
							}
						}
						overviewDto.setMonthOrder(monthOrder);
						moqOverviewDtoMap.put(combinedSku, overviewDto);

						LOGGER.debug("combinedSku:: " + combinedSku + " channelId:: " + customerMaster.get(0).getChannelID()
								+ " monthNumber:: " + monthNumber + " itemTotalPrice:: " + joorOrderItem.getItemTotalUSD());
					}
				}
			}
		}
		LOGGER.debug("moqOverviewDtos :: " + moqOverviewDtos);
		LOGGER.debug("monthsTotal :: " + monthsTotal);

		// preparing formated JSON and putting in response map
		Map<Integer, Object> overviewData = new HashMap<>();
		Integer mapIndex = -1;
		// preparing dynamic columns with month names
		List<String> overviewColumns = new ArrayList<String>();
		overviewColumns.add(MoicConstant.SKU);
		for (Map.Entry<Integer, String> entry : monthHeader.entrySet()) {
			overviewColumns.add(entry.getValue());
		}
		overviewColumns.add(MoicConstant.TOTAL);
		overviewData.put(++mapIndex, overviewColumns);

		// preparing SKU order overview
		List<String> sortedKeys = new ArrayList<>(moqOverviewDtoMap.keySet());
		Collections.sort(sortedKeys);
		for (String key : sortedKeys) {
			List<String> overviewRecords = new ArrayList<String>();
			overviewRecords.add(key);
			Map<Integer, Double> monthOrders = moqOverviewDtoMap.get(key).getMonthOrder();
			for (Map.Entry<Integer, Double> entry : monthOrders.entrySet()) {
				overviewRecords.add(String.format("%.2f", entry.getValue()));
			}
			overviewRecords.add(String.format("%.2f", moqOverviewDtoMap.get(key).getAllMonthsTotal()));
			overviewData.put(++mapIndex, overviewRecords);
		}

		// preparing lower total of orders
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

	@Override
	public ByteArrayInputStream downloadAllPreBuyList() {
		
		List<PreBuy> preBuyList = preBuyRepository.findAll();

		String[] splitRow = preBuyHeaders.split(",");
		Workbook workBook = new XSSFWorkbook();

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

			dataRow.createCell(0).setCellValue(null==preBuy.getSeasonCode()?"":preBuy.getSeasonCode());
			dataRow.createCell(1).setCellValue(null==preBuy.getSKU()?"":preBuy.getSKU());
			dataRow.createCell(2).setCellValue(null==preBuy.getIsPreBuySKU()?Boolean.FALSE:preBuy.getIsPreBuySKU());
			dataRow.createCell(3).setCellValue(null==preBuy.getCoo()?"":preBuy.getCoo());		

			sheet.autoSizeColumn(i);
			i = i + 1;
		}
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			workBook.write(outputStream);
		} catch (IOException e) {
			LOGGER.error("Inside downloadAllPreBuyList() IOException in File writing :" + e.getMessage());
		} finally {
			try {
				workBook.close();
			} catch (IOException e) {
				LOGGER.error("Inside downloadAllPreBuyList() IOException in File closing :" + e.getMessage());
			}
		}
		return new ByteArrayInputStream(outputStream.toByteArray());

	}

}
