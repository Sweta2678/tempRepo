package com.tapestry.moic.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import com.tapestry.moic.constant.MoicConstant;
import com.tapestry.moic.dto.OverviewDto;
import com.tapestry.moic.entity.Channel;
import com.tapestry.moic.entity.CustomerMaster;
import com.tapestry.moic.entity.FinalizedBuy;
import com.tapestry.moic.entity.FinalizedBuyArchived;
import com.tapestry.moic.entity.JoorOrder;
import com.tapestry.moic.entity.PreBuy;
import com.tapestry.moic.outbound.repository.FinalizedBuyOutboundRepository;
import com.tapestry.moic.repository.ChannelRepository;
import com.tapestry.moic.repository.CustomerMasterRepository;
import com.tapestry.moic.repository.FinalizedBuyArchivedRepository;
import com.tapestry.moic.repository.FinalizedBuyRepository;
import com.tapestry.moic.repository.JoorOrderRepository;
import com.tapestry.moic.repository.JoorOrderStyleItemRepository;
import com.tapestry.moic.repository.PreBuyRepository;
import com.tapestry.moic.service.interfaces.IFinalizedBuyInternalService;
import com.tapestry.moic.service.interfaces.IOverviewInternalService;
import com.tapestry.moic.utils.MoicUtil;

/**
 * The Class OverviewInternalService.
 * 
 * @version 0.0.1
 * 
 */
@Component
public class OverviewInternalService implements IOverviewInternalService {
	/**
	 * Field LOGGER
	 */
	private Logger LOGGER = LoggerFactory.getLogger(OverviewInternalService.class);

	@Autowired
	PreBuyRepository preBuyRepository;

	@Autowired
	private JoorOrderRepository joorOrderRepository;

	@Autowired
	private ChannelRepository channelRepository;

	@Autowired
	private CustomerMasterRepository customerMasterRepository;
	@Autowired
	private FinalizedBuyArchivedRepository finalizedBuyArchivedRepository;

	@Autowired
	FinalizedBuyRepository finalizedBuyRepository;
	@Autowired
	FinalizedBuyOutboundRepository finalizedBuyOutboundRepository;
	
	@Autowired
	private JoorOrderStyleItemRepository joorOrderStyleItemRepo;
	
	@Autowired
	private IFinalizedBuyInternalService finalizedBuyInternalService;

	@Value("${archived.download.sheetname}")
	String sheetName;
	@Value("${archived.download.columnname}")
	String columnname;

	@Value("${order.download.sheetname}")
	String orderSheetName;
	@Value("${order.download.columnname}")
	String orderColumnname;

	@SuppressWarnings("deprecation")
	@Override
	public Map<String, Object> getOverviewDefaultData(String season, String channel) {
		LOGGER.debug("Inside service method getOverviewDefaultData(" + season + ", " + channel + ")");

		Double targetTotal = 0d;
		Map<Integer, String> monthHeader = MoicUtil.generateMonthHeader(joorOrderRepository.findAll());
		Map<Integer, Double> monthsTotal = MoicUtil.initMonthMap(monthHeader);

		List<String> channelList = new ArrayList<>();
		if (channel.equals(MoicConstant.CORPORATE))
			channelList = channelRepository.getChannelNames();
		else
			channelList.add(channel);

		Map<String, OverviewDto> overviewDtoMap = MoicUtil.initOverviewDtoMap(channelList, monthHeader);

		LOGGER.debug("monthHeader :: " + monthHeader);
		LOGGER.debug("monthsTotal :: " + monthsTotal);
		LOGGER.debug("channelList :: " + channelList.toString());

		Map<String, Object> overviewResponseMap = new HashMap<>();

		List<Long> orderIds = joorOrderStyleItemRepo.getOrderIdBySeasonCode(season);

			for (Long orderId : orderIds) {

				Optional<JoorOrder> optionalJoorOrder = joorOrderRepository.findById(orderId);
				if (optionalJoorOrder.isPresent()) {
					JoorOrder joorOrder = optionalJoorOrder.get();

					List<CustomerMaster> customerMaster = customerMasterRepository
							.findBySoldToNumberAndShipToNumberAndIsActiveOrderByCreateDateDesc(joorOrder.getCustomerCode()
									, joorOrder.getShippingCode(), Boolean.TRUE);
					if (customerMaster.size() > 0) {
						Map<Integer, Double> monthOrder = null;
						OverviewDto overviewDto = null;

						if (customerMaster.get(0).getChannelID() != null && customerMaster.get(0).getChannelID() > 0) {
							Optional<Channel> channelOptional = channelRepository
									.findById(customerMaster.get(0).getChannelID());
							if (channelOptional.isPresent()
									&& channelList.contains(channelOptional.get().getChannelName())) {
								String channelName = channelOptional.get().getChannelName();
								overviewDto = overviewDtoMap.get(channelName);
								monthOrder = overviewDtoMap.get(channelName).getMonthOrder();
								overviewDto.setChannel(channelName);
							}
						} else if (channelList.size() > 1) {
							if (!overviewDtoMap.containsKey(MoicConstant.UNSPECIFIED)) {
								OverviewDto dto = new OverviewDto();
								dto.setMonthOrder(MoicUtil.initMonthMap(monthHeader));
								overviewDtoMap.put(MoicConstant.UNSPECIFIED, dto);
							}

							overviewDto = overviewDtoMap.get(MoicConstant.UNSPECIFIED);
							monthOrder = overviewDtoMap.get(MoicConstant.UNSPECIFIED).getMonthOrder();
							overviewDto.setChannel(MoicConstant.UNSPECIFIED);
						}

						if (null != joorOrder.getDateShipStart() && overviewDto != null) {
							Integer monthNumber = joorOrder.getDateShipStart().getMonth();
							for (Map.Entry<Integer, String> entry : monthHeader.entrySet()) {
								if (entry.getKey().equals(monthNumber)) {
									Double orderTotal = (null == joorOrder.getOrderTotalUSD()) ? 0
											: joorOrder.getOrderTotalUSD();
									monthOrder.put(entry.getKey(), monthOrder.get(entry.getKey()) + orderTotal);
									monthsTotal.put(entry.getKey(), monthsTotal.get(entry.getKey()) + orderTotal);
									overviewDto.setAllMonthsTotal(overviewDto.getAllMonthsTotal() + orderTotal);
								}
							}

							overviewDto.setMonthOrder(monthOrder);
							Integer target = (null == customerMaster.get(0).getTarget()) ? 0
									: customerMaster.get(0).getTarget();
							overviewDto.setTarget(overviewDto.getTarget() + target);
							targetTotal += target;
							overviewDtoMap.put(overviewDto.getChannel(), overviewDto);

							LOGGER.debug("orderId :: " + joorOrder.getOrderId() + ", customerCode :: "
									+ joorOrder.getCustomerCode() + ", dateShipStart :: " + joorOrder.getDateShipStart()
									+ ", orderTotal :: " + joorOrder.getOrderTotalUSD() + ", channel :: "
									+ overviewDto.getChannel() + ", monthOrder ::" + overviewDto.getMonthOrder()
									+ ", allMonthsTotal :: " + overviewDto.getAllMonthsTotal() + ", target :: "
									+ overviewDto.getTarget());
						}

					}
				}
			}

		LOGGER.debug("monthsTotal :: " + monthsTotal);
		LOGGER.debug("overviewDtoMap :: " + overviewDtoMap);

		// preparing formated JSON and putting in response map
		Map<Integer, Object> overviewData = new HashMap<>();
		Integer mapIndex = -1;
		// preparing dynamic columns with month names
		List<String> overviewColumns = new ArrayList<String>();
		overviewColumns.add(MoicConstant.CHANNEL_HEADER);
		for (Map.Entry<Integer, String> entry : monthHeader.entrySet()) {
			overviewColumns.add(entry.getValue());
		}
		overviewColumns.add(MoicConstant.TOTAL);
		overviewColumns.add(MoicConstant.TARGET_HEADER);
		overviewData.put(++mapIndex, overviewColumns);

		// preparing channel order overview
		List<String> sortedKeys = new ArrayList<>(overviewDtoMap.keySet());
		Collections.sort(sortedKeys);
		for (String key : sortedKeys) {
			List<String> overviewRecords = new ArrayList<String>();
			overviewRecords.add(key);
			Map<Integer, Double> monthOrders = overviewDtoMap.get(key).getMonthOrder();
			for (Map.Entry<Integer, Double> monthOrder : monthOrders.entrySet()) {
				overviewRecords.add(String.format("%.2f", monthOrder.getValue()));
			}
			overviewRecords.add(String.format("%.2f", overviewDtoMap.get(key).getAllMonthsTotal()));
			overviewRecords.add(String.format("%.2f", overviewDtoMap.get(key).getTarget()));
			overviewData.put(++mapIndex, overviewRecords);
		}

		// preparing lower total of orders
		List<String> overviewTotals = new ArrayList<String>();
		overviewTotals.add(MoicConstant.TOTAL);
		for (Map.Entry<Integer, Double> entry : monthsTotal.entrySet()) {
			overviewTotals.add(String.format("%.2f", entry.getValue()));
		}
		overviewTotals.add(String.format("%.2f", MoicUtil.sumMonthTotals(monthsTotal)));
		overviewTotals.add(String.format("%.2f", targetTotal));
		overviewData.put(++mapIndex, overviewTotals);

		overviewResponseMap.put(MoicConstant.DATA, overviewData);

		return overviewResponseMap;
	}

	@SuppressWarnings("deprecation")
	@Override
	public ByteArrayInputStream finalizedBuyArchivedDownload(String season, String channel) {

		LOGGER.debug("Inside finalizedBuyArchivedDownload() columnname from tmpl" + columnname);

		List<FinalizedBuyArchived> finalizedBuyArchivedList = new ArrayList<>();
		if(MoicConstant.CORPORATE.equalsIgnoreCase(channel))
			finalizedBuyArchivedList = finalizedBuyArchivedRepository.findBySeasonIgnoreCase(season.trim());
		else
		    finalizedBuyArchivedList = finalizedBuyArchivedRepository
				.findBySeasonAndChannelContainingIgnoreCase(season.trim(), channel.trim());

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
		if (!finalizedBuyArchivedList.isEmpty()) {
			for (FinalizedBuyArchived finalizedBuyArchived : finalizedBuyArchivedList) {
				
				if (finalizedBuyArchived.getCreateDate()!=null && (new Date().getYear() - finalizedBuyArchived.getCreateDate().getYear() >= 2)) {
					finalizedBuyArchivedRepository.delete(finalizedBuyArchived);
					LOGGER.debug("Old Record successfully deleted");
				} else {
					int cellIndex = 0;
					Row dataRow = sheet.createRow(result + 1);
					LOGGER.debug("Inside finalizedBuyArchivedDownload() dataRow" + dataRow.getPhysicalNumberOfCells());
					dataRow.createCell(cellIndex++).setCellValue(null == finalizedBuyArchived.getOrderID() ? 0 : finalizedBuyArchived.getOrderID());
					dataRow.createCell(cellIndex++).setCellValue(finalizedBuyArchived.getCreatedBy());// status
					dataRow.createCell(cellIndex++)
							.setCellValue(MoicUtil.stringdateToMMDDYYYY(finalizedBuyArchived.getCreateDate()));// StatusDate
					dataRow.createCell(cellIndex++).setCellValue(
							null == finalizedBuyArchived.getChannel() ? "" : finalizedBuyArchived.getChannel());

					// True if in Pre Buy Table for the given Season
					List<PreBuy> prebuys = preBuyRepository.findBySeasonCode(season);
					if (prebuys.isEmpty())
						dataRow.createCell(cellIndex++).setCellValue(true);
					else
						dataRow.createCell(cellIndex++).setCellValue(false);

					dataRow.createCell(cellIndex++).setCellValue(
							null == finalizedBuyArchived.getSeason() ? "" : finalizedBuyArchived.getSeason());
					dataRow.createCell(cellIndex++)
							.setCellValue(null == finalizedBuyArchived.getSKU() ? "" : finalizedBuyArchived.getSKU());
					dataRow.createCell(cellIndex++).setCellValue(
							null == finalizedBuyArchived.getStyleColor() ? "" : finalizedBuyArchived.getStyleColor());
					dataRow.createCell(cellIndex++).setCellValue(null == finalizedBuyArchived.getWidth() ? "" : finalizedBuyArchived.getWidth());
					dataRow.createCell(cellIndex++).setCellValue(null == finalizedBuyArchived.getSize() ? "" : finalizedBuyArchived.getSize());
					dataRow.createCell(cellIndex++).setCellValue(
							null == finalizedBuyArchived.getStyleNumber() ? "" : finalizedBuyArchived.getStyleNumber());
					dataRow.createCell(cellIndex++).setCellValue(
							null == finalizedBuyArchived.getStyleName() ? "" : finalizedBuyArchived.getStyleName());
					dataRow.createCell(cellIndex++).setCellValue(null == finalizedBuyArchived.getMaterialName() ? ""
							: finalizedBuyArchived.getMaterialName());
					dataRow.createCell(cellIndex++).setCellValue(
							null == finalizedBuyArchived.getColorName() ? "" : finalizedBuyArchived.getColorName());
					dataRow.createCell(cellIndex++).setCellValue(
							null == finalizedBuyArchived.getColorCode() ? "" : finalizedBuyArchived.getColorCode());
					dataRow.createCell(cellIndex++)
							.setCellValue(MoicUtil.stringdateToMMDDYYYY(finalizedBuyArchived.getSkuIntroDate()));
					dataRow.createCell(cellIndex++).setCellValue(
							null == finalizedBuyArchived.getDepartment() ? "" : finalizedBuyArchived.getDepartment());
					dataRow.createCell(cellIndex++).setCellValue(null == finalizedBuyArchived.getParentsClass() ? ""
							: finalizedBuyArchived.getParentsClass());

					dataRow.createCell(cellIndex++).setCellValue(finalizedBuyArchived.getRetailPrice());
					dataRow.createCell(cellIndex++).setCellValue(finalizedBuyArchived.getTargetCost());
					dataRow.createCell(cellIndex++)
							.setCellValue(null == finalizedBuyArchived.getCoo() ? "" : finalizedBuyArchived.getCoo());
					dataRow.createCell(cellIndex++).setCellValue(finalizedBuyArchived.getTotalBuyQuantity());
					dataRow.createCell(cellIndex++).setCellValue(
							MoicUtil.stringdateToMMDDYYYY(finalizedBuyArchived.getScheduledDeliveryDate()));
					dataRow.createCell(cellIndex++).setCellValue(
							null == finalizedBuyArchived.getOrderNotes() ? "" : finalizedBuyArchived.getOrderNotes());
					dataRow.createCell(cellIndex++).setCellValue(
							null == finalizedBuyArchived.getExFactMonth() ? "" : finalizedBuyArchived.getExFactMonth());

					dataRow.createCell(cellIndex++)
							.setCellValue(MoicUtil.stringdateToMMDDYYYY(finalizedBuyArchived.getInStoreDate()));
					dataRow.createCell(cellIndex++).setCellValue(
							null == finalizedBuyArchived.getVendor() ? "" : finalizedBuyArchived.getVendor());
					dataRow.createCell(cellIndex++).setCellValue(null == finalizedBuyArchived.getPurchaseGroup() ? ""
							: finalizedBuyArchived.getPurchaseGroup());
					dataRow.createCell(cellIndex++).setCellValue(null == finalizedBuyArchived.getStyleDescription() ? ""
							: finalizedBuyArchived.getStyleDescription());
					dataRow.createCell(cellIndex++).setCellValue(null == finalizedBuyArchived.getStorageLocation() ? ""
							: finalizedBuyArchived.getStorageLocation());

					dataRow.createCell(cellIndex++)
							.setCellValue(null == finalizedBuyArchived.getSite() ? "" : finalizedBuyArchived.getSite());
					dataRow.createCell(cellIndex++).setCellValue(null == finalizedBuyArchived.getStockSegment() ? ""
							: finalizedBuyArchived.getStockSegment());
					dataRow.createCell(cellIndex++).setCellValue(
							null == finalizedBuyArchived.getNewness() ? "" : finalizedBuyArchived.getNewness());
					dataRow.createCell(cellIndex++).setCellValue(null == finalizedBuyArchived.getShippingInstruction() ? ""
							: finalizedBuyArchived.getShippingInstruction());
					dataRow.createCell(cellIndex++).setCellValue(null ==finalizedBuyArchived.getExcludeFromSOCreation()? false:finalizedBuyArchived.getExcludeFromSOCreation());
					dataRow.createCell(cellIndex++).setCellValue(null == finalizedBuyArchived.getSalesDocType() ? ""
							: finalizedBuyArchived.getSalesDocType());
					dataRow.createCell(cellIndex++).setCellValue(
							null == finalizedBuyArchived.getSalesOrg() ? "" : finalizedBuyArchived.getSalesOrg());
					dataRow.createCell(cellIndex++).setCellValue(null == finalizedBuyArchived.getDistributionChannel() ? ""
							: finalizedBuyArchived.getDistributionChannel());
					dataRow.createCell(cellIndex++).setCellValue(
							null == finalizedBuyArchived.getDivision() ? "" : finalizedBuyArchived.getDivision());
					dataRow.createCell(cellIndex++).setCellValue(null == finalizedBuyArchived.getSoldToNumber() ? ""
							: finalizedBuyArchived.getSoldToNumber());
					dataRow.createCell(cellIndex++).setCellValue(null == finalizedBuyArchived.getSoldToDescription() ? "" : finalizedBuyArchived.getSoldToDescription());
					dataRow.createCell(cellIndex++).setCellValue(null == finalizedBuyArchived.getCountry() ? "" : finalizedBuyArchived.getCountry());
					dataRow.createCell(cellIndex++).setCellValue(null == finalizedBuyArchived.getCity() ? "" : finalizedBuyArchived.getCity());
					dataRow.createCell(cellIndex++).setCellValue(null == finalizedBuyArchived.getShipToNumber() ? ""
							: finalizedBuyArchived.getShipToNumber());
					dataRow.createCell(cellIndex++)
							.setCellValue(MoicUtil.stringdateToMMDDYYYY(finalizedBuyArchived.getSoCancelDate()));
					dataRow.createCell(cellIndex++).setCellValue(finalizedBuyArchived.getPoNumber());
					dataRow.createCell(cellIndex++).setCellValue(
							null == finalizedBuyArchived.getPoType() ? "" : finalizedBuyArchived.getPoType());

					dataRow.createCell(cellIndex++).setCellValue(
							null == finalizedBuyArchived.getOrderReason() ? "" : finalizedBuyArchived.getOrderReason());
					dataRow.createCell(cellIndex++).setCellValue(
							null == finalizedBuyArchived.getReqSegment() ? "" : finalizedBuyArchived.getReqSegment());
					dataRow.createCell(cellIndex++).setCellValue(finalizedBuyArchived.getTarget());
					dataRow.createCell(cellIndex++).setCellValue(null == finalizedBuyArchived.getOrderTotalUSD() ? 0
							: finalizedBuyArchived.getOrderTotalUSD());
					dataRow.createCell(cellIndex++).setCellValue(null == finalizedBuyArchived.getUpcCode() ? ""
							: finalizedBuyArchived.getUpcCode());

					sheet.autoSizeColumn(result);
					result = result + 1;
				}
			}
		}
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			workBook.write(outputStream);
		} catch (IOException e) {
			LOGGER.error("Inside finalizedBuyArchivedDownload() IOException in File writing :" + e.getMessage());
		} finally {
			try {
				workBook.close();
			} catch (IOException e) {
				LOGGER.error("Inside finalizedBuyArchivedDownload() IOException in File closing :" + e.getMessage());
			}
		}
		return new ByteArrayInputStream(outputStream.toByteArray());

	}

	@Override
	public ByteArrayInputStream orderDownloadReport(List<String> season, String channel) {

		//Below method will populate FinalizedBuy entity
		finalizedBuyInternalService.filterAndPopulateFinalizedBuy();

		LOGGER.debug("Inside orderDownloadReport() columnname from tmpl" + orderColumnname);

		List<FinalizedBuy> finalizedBuyList = new ArrayList<>();
		if(MoicConstant.CORPORATE.equalsIgnoreCase(channel))
			finalizedBuyList = finalizedBuyRepository.findBySeasonIn(season);
		else
			finalizedBuyList = finalizedBuyRepository.findByChannelAndSeasonIn(channel, season);

		String[] splitRow = orderColumnname.split(",");
		Workbook workBook = new XSSFWorkbook();

		Sheet sheet = workBook.createSheet(orderSheetName);
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
		if (!finalizedBuyList.isEmpty()) {
			for (FinalizedBuy finalizedBuy : finalizedBuyList) {

				int cellIndex = 0;
				Row dataRow = sheet.createRow(result + 1);
				LOGGER.debug("Inside finalizedBuyArchivedDownload() dataRow" + dataRow.getPhysicalNumberOfCells());
				dataRow.createCell(cellIndex++).setCellValue(finalizedBuy.getOrderID());
				dataRow.createCell(cellIndex++).setCellValue(null == finalizedBuy.getJoorOrderType() ? "" : finalizedBuy.getJoorOrderType());
				dataRow.createCell(cellIndex++).setCellValue(finalizedBuy.getCreatedBy());// status
				dataRow.createCell(cellIndex++).setCellValue(MoicUtil.stringdateToMMDDYYYY(finalizedBuy.getCreateDate()));// StatusDate
				dataRow.createCell(cellIndex++).setCellValue(null == finalizedBuy.getChannel() ? "" : finalizedBuy.getChannel());

				dataRow.createCell(cellIndex++).setCellValue(finalizedBuy.getPreBuySKU());

				dataRow.createCell(cellIndex++).setCellValue(null == finalizedBuy.getSeason() ? "" : finalizedBuy.getSeason());
				dataRow.createCell(cellIndex++).setCellValue(null == finalizedBuy.getSKU() ? "" : finalizedBuy.getSKU());
				dataRow.createCell(cellIndex++)
						.setCellValue(null == finalizedBuy.getStyleColor() ? "" : finalizedBuy.getStyleColor());
				dataRow.createCell(cellIndex++)
						.setCellValue(null == finalizedBuy.getWidth() ? "" : finalizedBuy.getWidth());
				dataRow.createCell(cellIndex++)
						.setCellValue(null == finalizedBuy.getSize() ? "" : finalizedBuy.getSize());
				dataRow.createCell(cellIndex++)
						.setCellValue(null == finalizedBuy.getEuSize() ? "" : finalizedBuy.getEuSize());
				dataRow.createCell(cellIndex++)
						.setCellValue(null == finalizedBuy.getStyleNumber() ? "" : finalizedBuy.getStyleNumber());
				dataRow.createCell(cellIndex++)
						.setCellValue(null == finalizedBuy.getStyleName() ? "" : finalizedBuy.getStyleName());
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
						.setCellValue(null == finalizedBuy.getSoldToDescription() ? "" : finalizedBuy.getSoldToDescription());
				dataRow.createCell(cellIndex++)
						.setCellValue(null == finalizedBuy.getCountry() ? "" : finalizedBuy.getCountry());
				dataRow.createCell(cellIndex++)
						.setCellValue(null == finalizedBuy.getCity() ? "" : finalizedBuy.getCity());
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

				sheet.autoSizeColumn(result);
				result = result + 1;
			}
		}
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			workBook.write(outputStream);
		} catch (IOException e) {
			LOGGER.error("Inside orderDownloadReport() IOException in File writing :" + e.getMessage());
		} finally {
			try {
				workBook.close();
			} catch (IOException e) {
				LOGGER.error("Inside orderDownloadReport() IOException in File closing :" + e.getMessage());
			}
		}
		return new ByteArrayInputStream(outputStream.toByteArray());

	}

}
