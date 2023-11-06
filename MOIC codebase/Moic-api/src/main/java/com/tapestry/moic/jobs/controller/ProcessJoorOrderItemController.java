package com.tapestry.moic.jobs.controller;

import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.io.FilenameUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.tapestry.moic.constant.MoicConstant;
import com.tapestry.moic.dto.FashionSeasonDto;
import com.tapestry.moic.entity.FashionSeason;
import com.tapestry.moic.entity.JobAudit;
import com.tapestry.moic.entity.JoorOrder;
import com.tapestry.moic.entity.JoorOrderDivisions;
import com.tapestry.moic.entity.JoorOrderDoors;
import com.tapestry.moic.entity.JoorOrderItem;
import com.tapestry.moic.entity.JoorOrderStyleItem;
import com.tapestry.moic.entity.SizeConversion;
import com.tapestry.moic.jobs.controller.interfaces.IProcessJoorOrderItemController;
import com.tapestry.moic.outbound.dto.JoorOrderDivisionOutboundDto;
import com.tapestry.moic.outbound.dto.JoorOrderDoorOutboundDto;
import com.tapestry.moic.outbound.dto.JoorOrderItemOutboundDto;
import com.tapestry.moic.outbound.dto.JoorOrderOutboundDto;
import com.tapestry.moic.outbound.dto.JoorOrderStyleItemOutboundDto;
import com.tapestry.moic.repository.CurrencyConversionRepository;
import com.tapestry.moic.repository.FashionSeasonRepository;
import com.tapestry.moic.repository.FinalizedBuyArchivedRepository;
import com.tapestry.moic.repository.JoorOrderDivisionsRepository;
import com.tapestry.moic.repository.JoorOrderDoorsRepository;
import com.tapestry.moic.repository.JoorOrderItemRepository;
import com.tapestry.moic.repository.JoorOrderRepository;
import com.tapestry.moic.repository.JoorOrderStyleItemRepository;
import com.tapestry.moic.repository.SizeConversionRepository;
import com.tapestry.moic.response.ErrorResponse;
import com.tapestry.moic.response.JobsSuccessResponse;
import com.tapestry.moic.response.interfaces.MoicResponse;
import com.tapestry.moic.service.interfaces.IJobAuditService;
import com.tapestry.moic.utils.MoicUtil;

@Component
public class ProcessJoorOrderItemController implements IProcessJoorOrderItemController{

	
	private static final Logger log = LoggerFactory.getLogger(ProcessJoorOrderItemController.class);

	@Value("${jobs.joor.order.file.location}")
	private String joorFileLocation;

	@Value("${jobs.joor.order.file.name}")
	private String joorFileName;

	@Value("${jobs.joor.order.archive.file.location}")
	private String archivedFileLocation;
	
	@Value("${jobs.joor.order.failed.file.location}")
	private String failedFileLocation;

	@Autowired
	JoorOrderRepository joorRepository;

	@Autowired
	JoorOrderItemRepository joorItemRepository;

	@Autowired
	JoorOrderDivisionsRepository joorOrderDivRepo;

	@Autowired
	JoorOrderDoorsRepository joorOrderDoorsRep;
	
	@Autowired
	CurrencyConversionRepository currencyConversionRepository;
	
	@Autowired
	JoorOrderStyleItemRepository styleItemRepository;
	
	@Autowired
	FashionSeasonRepository fashionSeasonRepository;
	
	@Autowired
	SizeConversionRepository sizeConversionRepository;
	
	@Autowired
	IJobAuditService jobAuditService;
	
	@Autowired
	FinalizedBuyArchivedRepository finalizedBuyArchivedRepository;
	
	@Value("${skip.order.finalizedbuyarchive}")
	private Boolean skipOrderFinalizedBuy;
	
	boolean fileProcessedSuccessfully;
	boolean joorJobProcessedSuccessfully = true;
	String exitCodeJoorOrder;
	
	
	@Override
	public MoicResponse processJoorOrderJson() {

		
		try {
			
			List<File> files = Files.list(Paths.get(joorFileLocation.toString().trim())).map(Path::toFile)
					.collect(Collectors.toList());
			
			if (files.size() > 0) {
				joorJobProcessedSuccessfully = true;
				
				for (File file : files) {
					
					Map<String, String> dataSummaryMap = new HashMap<String, String>();
					int approvedOrdersCount = 0;
					int pendingOrdersCount = 0;
					int recordsCount=0;
					String errMsg = null;
					try{
						JSONParser parser = new JSONParser();
						FileReader fileReader = new FileReader(file);
						Object object = parser.parse(fileReader);
						JSONObject jsonObject = (JSONObject) object;
						JSONArray joorOutboundArray = (JSONArray) jsonObject.get("orders");
						fileProcessedSuccessfully = true;
						exitCodeJoorOrder = new String();
	
						for (Object obj : joorOutboundArray) {
							
							try {
								recordsCount++;
								JoorOrderOutboundDto joorOrderDto = new JoorOrderOutboundDto();
	 							List<JoorOrderItemOutboundDto> itemOutboundDtoList = new ArrayList<JoorOrderItemOutboundDto>();
								List<JoorOrderDoorOutboundDto> doorOutboundDtoList = new ArrayList<JoorOrderDoorOutboundDto>();
								List<JoorOrderDivisionOutboundDto> divisionOutboundDtoList = new ArrayList<JoorOrderDivisionOutboundDto>();
								List<JoorOrderStyleItemOutboundDto> styleItemOutboundDtoList = new ArrayList<JoorOrderStyleItemOutboundDto>();
	
								JoorOrder joorOrder = new JoorOrder();
								
								if(obj instanceof JSONObject && (((JSONObject) obj).get("order_id")) != null
										&& ((JSONObject) obj).get("order_type").toString().equals(MoicConstant.CANCELLED_JOOR_ORDER_TYPE )){
									deleteCancelledJoorOrder(obj, file);
								}
								else if( obj instanceof JSONObject && (((JSONObject) obj).get("order_id")) != null) 
								{
									Boolean isOrderExists = Boolean.FALSE;
									if(skipOrderFinalizedBuy) {
										isOrderExists = finalizedBuyArchivedRepository.existsFinalizedBuyArchivedByOrderID(Long.parseLong(((JSONObject)obj).get("order_id").toString()));
									}
									if(!isOrderExists) {
										JSONObject joorObj = (JSONObject) obj;
										joorOrderDto.setOrderId(Long.parseLong(joorObj.get("order_id").toString()));
										joorOrderDto.setOrderComments(joorObj.get("order_comments").toString());
										joorOrderDto
												.setOrderInternalComments(joorObj.get("order_internal_comments").toString());
										joorOrderDto.setOrderCurrency(joorObj.get("order_currency").toString());
										joorOrderDto.setOrderCurrencyRetail(joorObj.get("order_currency_retail").toString());
										joorOrderDto.setOrderCurrencyName(joorObj.get("order_currency_name").toString());
										joorOrderDto
												.setOrderDiscount(Double.parseDouble(joorObj.get("order_discount").toString()));
										joorOrderDto.setOrderDiscountPercent(
												Double.parseDouble(joorObj.get("order_discount_percent").toString()));
										joorOrderDto.setOrderShippingTotal(
												Double.parseDouble(joorObj.get("order_shipping_total").toString()));
										joorOrderDto.setOrderTotal(Double.parseDouble(joorObj.get("order_total").toString()));
										joorOrderDto.setOrderSource(joorObj.get("order_source").toString());
										joorOrderDto.setOrderSeasonCode(joorObj.get("order_season_code").toString());
										joorOrderDto.setOrderType(joorObj.get("order_type").toString());
										if(joorOrderDto.getOrderType().equals("4"))
											approvedOrdersCount++;
										else if(joorOrderDto.getOrderType().equals("3"))
											pendingOrdersCount++;
										joorOrderDto.setOrderTypeName(joorObj.get("order_type_name").toString());
										joorOrderDto.setOrderTypeCode(joorObj.get("order_type_code").toString());
										joorOrderDto.setOrderEvent(joorObj.get("order_event").toString());
										joorOrderDto.setOrderEventCode(joorObj.get("order_event_code").toString());
										joorOrderDto.setOrderDeliveryName(joorObj.get("order_delivery_name").toString());
										joorOrderDto.setOrderDeliveryCode(joorObj.get("order_delivery_code").toString());
										
										joorOrderDto.setDateCreated(MoicUtil.dateConvert(joorObj.get("date_created").toString()));
										
										if(joorObj.get("date_created") != null && !joorObj.get("date_created").toString().isEmpty() && joorObj.get("date_created").toString().length() >= 10) {
											joorOrderDto.setDateCreated(MoicUtil.dateConvert(joorObj.get("date_created").toString()));
										}
										if(joorObj.get("date_approved") != null && !joorObj.get("date_approved").toString().isEmpty()  && joorObj.get("date_approved").toString().length() >= 10) {
											
											joorOrderDto.setDateApproved(MoicUtil.dateConvert(joorObj.get("date_approved").toString()));
										}
										if(joorObj.get("date_modified") != null && !joorObj.get("date_modified").toString().isEmpty()  && joorObj.get("date_modified").toString().length() >= 10) {
										
											joorOrderDto.setDateModified(MoicUtil.dateConvert(joorObj.get("date_modified").toString()));
										}
										if(joorObj.get("date_ship_start") != null && !joorObj.get("date_ship_start").toString().isEmpty()  && joorObj.get("date_ship_start").toString().length() >= 10) {
										
											joorOrderDto.setDateShipStart(MoicUtil.dateConvert(joorObj.get("date_ship_start").toString()));
										}
										if(joorObj.get("date_ship_end") != null && !joorObj.get("date_ship_end").toString().isEmpty() && joorObj.get("date_ship_end").toString().length() >= 10) {
										
											joorOrderDto.setDateShipEnd(MoicUtil.dateConvert(joorObj.get("date_ship_end").toString()));
										}
										joorOrderDto.setSalesRepresentative(joorObj.get("sales_representative").toString());
										joorOrderDto.setSalesRepresentativeCode(
												joorObj.get("sales_representative_code").toString());
										joorOrderDto.setCustomerName(joorObj.get("customer_name").toString());
										joorOrderDto.setCustomerCode(joorObj.get("customer_code").toString().trim());
										joorOrderDto.setCompanyName(joorObj.get("company_name").toString());
										joorOrderDto.setCompanyCode(joorObj.get("company_code").toString());
										joorOrderDto.setCustomerGroupName(joorObj.get("customer_group_name").toString());
										joorOrderDto.setCustomerGroupCode(joorObj.get("customer_group_code").toString());
										joorOrderDto
												.setCustomerPurchaseOrder(joorObj.get("customer_purchase_order").toString());
										joorOrderDto.setIntegrationPurchaseOrder(
												joorObj.get("integration_purchase_order").toString());
										joorOrderDto.setCustomerBuyerName(joorObj.get("customer_buyer_name").toString());
										joorOrderDto.setShippingCode(joorObj.get("shipping_code").toString().trim());
										joorOrderDto.setShippingName(joorObj.get("shipping_name").toString());
										joorOrderDto.setShippingPhone(joorObj.get("shipping_phone").toString());
										joorOrderDto.setShippingEmail(joorObj.get("shipping_email").toString());
										joorOrderDto.setShippingAddress1(joorObj.get("shipping_address_1").toString());
										joorOrderDto.setShippingAddress2(joorObj.get("shipping_address_2").toString());
										joorOrderDto.setShippingCity(joorObj.get("shipping_city").toString());
										joorOrderDto.setShippingRegion(joorObj.get("shipping_region").toString());
										joorOrderDto.setShippingPostalCode(joorObj.get("shipping_postal_code").toString());
										joorOrderDto.setShippingCountry(joorObj.get("shipping_country").toString());
										joorOrderDto.setShippingCountryIsoThree(
												joorObj.get("shipping_country_iso_three").toString());
										joorOrderDto
												.setShippingCountryIsoTwo(joorObj.get("shipping_country_iso_two").toString());
										joorOrderDto.setShippingMethodName(joorObj.get("shipping_method_name").toString());
										joorOrderDto.setShippingMethodCode(joorObj.get("shipping_method_code").toString());
										joorOrderDto.setBillingCode(joorObj.get("billing_code").toString());
										joorOrderDto.setBillingName(joorObj.get("billing_name").toString());
										joorOrderDto.setBillingPhone(joorObj.get("billing_phone").toString());
										joorOrderDto.setBillingAddress1(joorObj.get("billing_address_1").toString());
										joorOrderDto.setBillingAddress2(joorObj.get("billing_address_2").toString());
										joorOrderDto.setBillingCity(joorObj.get("billing_city").toString());
										joorOrderDto.setBillingRegion(joorObj.get("billing_region").toString());
										joorOrderDto.setBillingPostalCode(joorObj.get("billing_postal_code").toString());
										joorOrderDto.setBillingCountry(joorObj.get("billing_country").toString());
										joorOrderDto
												.setBillingCountryIsoThree(joorObj.get("billing_country_iso_three").toString());
										joorOrderDto.setBillingCountryIsoTwo(joorObj.get("billing_country_iso_two").toString());
										joorOrderDto.setPaymentMethodName(joorObj.get("payment_method_name").toString());
										joorOrderDto.setPaymentMethodCode(joorObj.get("payment_method_code").toString());
		//								joorOrderDto.setTaxIdLabel(joorObj.get("tax_id_label").toString());
										joorOrderDto.setWarehouseName(joorObj.get("warehouse_name").toString());
										joorOrderDto.setWarehouseCode(joorObj.get("warehouse_code").toString());
										joorOrderDto.setCreatedByUsername(joorObj.get("created_by_username").toString());
										joorOrderDto.setCreatedByEmail(joorObj.get("created_by_email").toString());
										joorOrderDto.setIsPersonalOrder(
												Boolean.parseBoolean(joorObj.get("is_personal_order").toString()));
		
										if(!joorObj.get("order_currency").toString().isEmpty()) {
											joorOrderDto.setOrderTotalUSD(getCurrencyConversionByUSD(joorObj.get("order_total").toString(), joorObj.get("order_currency").toString(), joorObj.get("order_currency_name").toString()));
										}
		
										JSONObject divisionObj = new JSONObject();
										JSONArray divisionArray = new JSONArray();
										if(!joorObj.get("order_divisions").toString().isEmpty())
										{
										divisionArray = (JSONArray) joorObj.get("order_divisions");
										for (Object divObj : divisionArray) {
											JoorOrderDivisionOutboundDto divisionOutboundDto = new JoorOrderDivisionOutboundDto();
											if (divObj instanceof JSONObject && (joorObj.get("order_id") != null)) {
												divisionObj = (JSONObject) divObj;
												divisionOutboundDto
														.setOrderID(Long.parseLong(joorObj.get("order_id").toString()));
												divisionOutboundDto
														.setDivisionCode(divisionObj.get("division_code").toString());
												divisionOutboundDto
														.setDivisionName(divisionObj.get("division_name").toString());
											}
											divisionOutboundDtoList.add(divisionOutboundDto);
										}
										
										if(!joorObj.get("order_doors").toString().isEmpty()) {
										JSONObject doorOrderObj = new JSONObject();
										JSONArray doorArray = new JSONArray();								
										doorArray = (JSONArray) joorObj.get("order_doors");
										for (Object doorObj : doorArray) {
											JoorOrderDoorOutboundDto doorOutboundDto = new JoorOrderDoorOutboundDto();
											if (doorObj instanceof JSONObject && (joorObj.get("order_id") != null)) {
												doorOrderObj = (JSONObject) doorObj;
												doorOutboundDto.setOrderID(Long.parseLong(joorObj.get("order_id").toString()));
												doorOutboundDto
														.setDoorId(Integer.parseInt(doorOrderObj.get("door_id").toString()));
												doorOutboundDto.setDoorName(doorOrderObj.get("door_name").toString());
												doorOutboundDto.setDoorCode((doorOrderObj.get("door_code") != null)
														? doorOrderObj.get("door_code").toString()
														: "");
												doorOutboundDto.setDoorDeleted(
														Boolean.parseBoolean(doorOrderObj.get("door_deleted").toString()));
											}
											doorOutboundDtoList.add(doorOutboundDto);
										}
										}
		
										if(!joorObj.get("line_items").toString().isEmpty()) {
										JSONObject itemsObj = new JSONObject();
										JSONArray itemsArray = new JSONArray();
										itemsArray = (JSONArray) joorObj.get("line_items");
										for (Object itemObj : itemsArray) {
											JoorOrderItemOutboundDto itemOutboundDto = new JoorOrderItemOutboundDto();
											if (itemObj instanceof JSONObject) {
												itemsObj = (JSONObject) itemObj;
												itemOutboundDto.setOrderID(Long.parseLong(joorObj.get("order_id").toString()));
												itemOutboundDto.setItemName(itemsObj.get("item_name").toString());
												itemOutboundDto.setItemNumber(itemsObj.get("item_number").toString());
												itemOutboundDto.setItemStyleId(
														Long.parseLong(itemsObj.get("item_style_id").toString()));
												itemOutboundDto.setItemStyleIdentifier(
														itemsObj.get("item_style_identifier").toString());
												itemOutboundDto.setItemColor(itemsObj.get("item_color").toString());
												itemOutboundDto.setItemColorCode(itemsObj.get("item_color_code").toString());
												itemOutboundDto.setItemUpc(itemsObj.get("item_upc").toString());
												itemOutboundDto.setItemSize(itemsObj.get("item_size").toString());
												itemOutboundDto.setEuSize(getSizeConversionByUSD(itemsObj.get("item_size").toString(), MoicConstant.EUR_ORDER_CURRENCY));
												itemOutboundDto.setUkSize(getSizeConversionByUSD(itemsObj.get("item_size").toString(), MoicConstant.GBP_ORDER_CURRENCY));
												itemOutboundDto.setItemSizeCode(itemsObj.get("item_size_code").toString());
												if(itemsObj.get("item_price") != null && !itemsObj.get("item_price").toString().isEmpty()) {
													itemOutboundDto.setItemPrice(
															Double.parseDouble(itemsObj.get("item_price").toString()));
												}
												if(itemsObj.get("item_retail_price") != null && !itemsObj.get("item_retail_price").toString().isEmpty()) {
													itemOutboundDto.setItemRetailPrice(
															Double.parseDouble(itemsObj.get("item_retail_price").toString()));
												}
												if(itemsObj.get("item_unit_price") != null && itemsObj.get("item_unit_price") != "") {
													itemOutboundDto.setItemUnitPrice(
															Double.parseDouble(itemsObj.get("item_unit_price").toString()));
												}
												if(itemsObj.get("item_price_adjustment") != null && !itemsObj.get("item_price_adjustment").toString().isEmpty()) {
													itemOutboundDto.setItemPriceAdjustment(
															Double.parseDouble(itemsObj.get("item_price_adjustment").toString()));
												}
												if(itemsObj.get("item_quantity") != null && !itemsObj.get("item_quantity").toString().isEmpty()) {
													itemOutboundDto.setItemQuantity(
															Integer.parseInt(itemsObj.get("item_quantity").toString()));
												}
												if(itemsObj.get("item_price_extended") != null && !itemsObj.get("item_price_extended").toString().isEmpty()) {
													itemOutboundDto.setItemPriceExtended(
															Double.parseDouble(itemsObj.get("item_price_extended").toString()));
													itemOutboundDto.setItemTotalUSD(getCurrencyConversionByUSD(itemsObj.get("item_price_extended").toString(), joorObj.get("order_currency").toString(), joorObj.get("order_currency_name").toString()));
													
												}
												itemOutboundDto.setItemMultiplier(itemsObj.get("item_multiplier").toString());
												
												itemOutboundDto.setItemComment(itemsObj.get("item_comment").toString());
												if(itemsObj.get("item_door_id") != null && !itemsObj.get("item_door_id").toString().isEmpty()) {
													itemOutboundDto.setItemDoorId(
															Integer.parseInt(itemsObj.get("item_door_id").toString()));
												}
												itemOutboundDto
														.setItemColorComment(itemsObj.get("item_color_comment").toString());
												itemOutboundDto.setWidth(itemsObj.get("item_color_code").toString().substring(itemsObj.get("item_color_code").toString().indexOf(MoicConstant.SPACE)+ 1));
												
												if(!itemsObj.get("style_tags").toString().isEmpty()) {
													JSONObject styleItemsObj = new JSONObject();
													JSONArray styleItemsArray = new JSONArray();
												 	styleItemsArray = (JSONArray) itemsObj.get("style_tags");
												 	
												 	for(Object styleItem : styleItemsArray) {
												 		JoorOrderStyleItemOutboundDto styleItemOutboundDto = new JoorOrderStyleItemOutboundDto();
												 		if(styleItem instanceof JSONObject) {
												 			styleItemsObj = (JSONObject) styleItem;
												 			styleItemOutboundDto.setOrderID(Long.parseLong(joorObj.get("order_id").toString()));
												 			styleItemOutboundDto.setItemNumber(itemsObj.get("item_number").toString());
												 			styleItemOutboundDto.setItemColorCode(itemsObj.get("item_color_code").toString());
												 			styleItemOutboundDto.setItemSize(itemsObj.get("item_size").toString());
												 			styleItemOutboundDto.setItemStyleId(Long.parseLong(itemsObj.get("item_style_id").toString()));
												 			styleItemOutboundDto.setStyletagcode(styleItemsObj.get("style_tag_code").toString());
												 			styleItemOutboundDto.setStyletaggroup(styleItemsObj.get("style_tag_group").toString());
												 			styleItemOutboundDto.setStyletagname(styleItemsObj.get("style_tag_name").toString());
												 			styleItemOutboundDto.setStyletagvalue(styleItemsObj.get("style_tag_value").toString());
												 		}
												 		styleItemOutboundDtoList.add(styleItemOutboundDto);
												 	}
												}
											}
											itemOutboundDtoList.add(itemOutboundDto);
										}
									}
									}
		
										Boolean isJoorOrderExist = joorRepository.existsById(Long.parseLong(((JSONObject) obj).get("order_id").toString()));
										if(!isJoorOrderExist) {
											BeanUtils.copyProperties(joorOrderDto, joorOrder);
											joorOrder.setCreateDate(new Date());
											joorOrder.setCreatedBy(MoicConstant.INBOUND_RECORDS_CREATED_BY);
										}
										else {
											joorOrder = joorRepository.findById(Long.parseLong(((JSONObject) obj).get("order_id").toString())).get();
											BeanUtils.copyProperties(joorOrderDto, joorOrder, "orderId");
											joorOrder.setModifiedDate(new Date());
											joorOrder.setModifiedBy(MoicConstant.INBOUND_RECORDS_CREATED_BY);
										}
										joorRepository.save(joorOrder);
			
										
										List<JoorOrderItem> joorOrderItemList = new ArrayList<JoorOrderItem>();
										joorItemRepository.deleteOrderItemsByOrderId(joorOrder.getOrderId());
										for (JoorOrderItemOutboundDto itemOutboundDto : itemOutboundDtoList) {
											
											JoorOrderItem joorOrderItem = new JoorOrderItem();
											BeanUtils.copyProperties(itemOutboundDto, joorOrderItem);
											joorOrderItem.setImpactedByMoqSkuChange(Boolean.FALSE);
											joorOrderItem.setCombinedSku(MoicUtil.getCombinedSku(joorOrderItem.getItemNumber(), 
													joorOrderItem.getItemColorCode(), joorOrderItem.getItemSize()));
											joorOrderItem.setCreateDate(new Date());
											joorOrderItem.setCreatedBy(MoicConstant.INBOUND_RECORDS_CREATED_BY);
											joorOrderItemList.add(joorOrderItem);
										}
										joorItemRepository.saveAll(joorOrderItemList);
			
										List<JoorOrderDoors> joorOrderDoorsList = new ArrayList<JoorOrderDoors>();
										if(joorOrderDoorsRep.existsById(joorOrder.getOrderId())) {
											joorOrderDoorsRep.deleteById(joorOrder.getOrderId());
										}
										for (JoorOrderDoorOutboundDto doorOutboundDto : doorOutboundDtoList) {
											JoorOrderDoors joorOrderDoors = new JoorOrderDoors();
											BeanUtils.copyProperties(doorOutboundDto, joorOrderDoors);
											joorOrderDoors.setCreateDate(new Date());
											joorOrderDoors.setCreatedBy(MoicConstant.INBOUND_RECORDS_CREATED_BY);
											joorOrderDoorsList.add(joorOrderDoors);
										}
										joorOrderDoorsRep.saveAll(joorOrderDoorsList);
										
			
										List<JoorOrderDivisions> joorOrderDivisionsList = new ArrayList<JoorOrderDivisions>();
										if(joorOrderDivRepo.existsById(joorOrder.getOrderId())) {
											joorOrderDivRepo.deleteById(joorOrder.getOrderId());
										}
										for (JoorOrderDivisionOutboundDto divisionOutboundDto : divisionOutboundDtoList) {
											JoorOrderDivisions joorOrderDivisions = new JoorOrderDivisions();
											BeanUtils.copyProperties(divisionOutboundDto, joorOrderDivisions);
											joorOrderDivisions.setCreateDate(new Date());
											joorOrderDivisions.setCreatedBy(MoicConstant.INBOUND_RECORDS_CREATED_BY);
											joorOrderDivisionsList.add(joorOrderDivisions);
										}
										joorOrderDivRepo.saveAll(joorOrderDivisionsList);
	
										
										List<JoorOrderStyleItem> joorOrderStyleItemsList = new ArrayList<JoorOrderStyleItem>();
										styleItemRepository.deleteOrderStyleItemByOrderId(joorOrder.getOrderId());
										for(JoorOrderStyleItemOutboundDto styleItemOutboundDto : styleItemOutboundDtoList) {
											JoorOrderStyleItem styleItem = new JoorOrderStyleItem();
											BeanUtils.copyProperties(styleItemOutboundDto, styleItem);
											styleItem.setCreateDate(new Date());
											styleItem.setCreatedBy(MoicConstant.INBOUND_RECORDS_CREATED_BY);
											joorOrderStyleItemsList.add(styleItem);
										}
										styleItemRepository.saveAll(joorOrderStyleItemsList);
									}
								}	
							} catch (Exception e) {
								errMsg = e.getMessage();
								fileProcessedSuccessfully = false;
								joorJobProcessedSuccessfully = false;
								log.error("Failed to process Joor Order due to error" + e.getMessage() + " Joor Order Id: "
										+ ((JSONObject) obj).get("order_id") + " File Name: "+ file.getName());
							}
							finally {
								fileReader.close();
							}
						}
	
						
						Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	
						String srcFileName = FilenameUtils.removeExtension(file.getName());
						String fileExtension = FilenameUtils.getExtension(file.getName());
						
						Path temp;
						
						if(!fileProcessedSuccessfully) {
							temp = Files.move( 
									Paths.get(joorFileLocation.toString().trim() + "/" + file.getName().toString().trim()),
									Paths.get(failedFileLocation.toString().trim() + "/" + srcFileName + "_" + timestamp.getTime()
											+ "." + fileExtension));
							if (temp != null) {
								log.debug("Joor outbound file is moved to failed folder"+file.getName());
							}
							
						}
						else {
							temp = Files.move( 
								Paths.get(joorFileLocation.toString().trim() + "/" + file.getName().toString().trim()),
								Paths.get(archivedFileLocation.toString().trim() + "/" + srcFileName + "_" + timestamp.getTime()
										+ "." + fileExtension));
							if(temp != null) {
								log.debug(" Joor outbound file successfully moved to archived folder"+file.getName());
							}
							
						}
						
					}catch (Exception e) {
						errMsg = e.getMessage();
						fileProcessedSuccessfully = false;
						log.error("Failed to process file due to Exception: " + e.getMessage() + "File name :: " + file.getName());
					}
					finally {
						dataSummaryMap.put("approvedOrdersCount", String.valueOf(approvedOrdersCount));
						dataSummaryMap.put("pendingOrdersCount", String.valueOf(pendingOrdersCount));
						Timestamp timestamp = new Timestamp(System.currentTimeMillis());
						JobAudit audit = setJobAuditDetail(fileProcessedSuccessfully, joorJobProcessedSuccessfully, file.getName().toString().trim(), timestamp, errMsg, recordsCount, dataSummaryMap.toString());
						jobAuditService.saveJobAuditDetails(audit);
					}
	
				}
				
			}
			
			List<String> styleTagValuesList = styleItemRepository.getTagValuesByTagCodeFashionSeason();
			List<String> fashionSeasonCodeList = fashionSeasonRepository.getAllSeasonCode();
			List<String> newFashionSeasonCodeList = new ArrayList<String>();
			if(!styleTagValuesList.isEmpty()) {
				for(String styleTag : styleTagValuesList) {
					if(!fashionSeasonCodeList.contains(styleTag)) {
						newFashionSeasonCodeList.add(styleTag);
					}
				}
				List<FashionSeasonDto> seasonDtoList = new ArrayList<FashionSeasonDto>();
				List<FashionSeason> fashionSeasonList = new ArrayList<FashionSeason>();
								
				for(String seasonCode : newFashionSeasonCodeList) {
					FashionSeasonDto seasonDto = new FashionSeasonDto();
					seasonDto.setSeasonCode(seasonCode);
					seasonDtoList.add(seasonDto);
				}
				for(FashionSeasonDto fashionSeasonDto : seasonDtoList) {
					FashionSeason fashionSeason = new FashionSeason();
					BeanUtils.copyProperties(fashionSeasonDto, fashionSeason);
					fashionSeasonList.add(fashionSeason);
				}
				fashionSeasonRepository.saveAll(fashionSeasonList);
			}
						
			if(!joorJobProcessedSuccessfully) {
				exitCodeJoorOrder = MoicConstant.EXIT_CODE_JOOR_ORDER_FAILURE;
				return new ErrorResponse(false, HttpStatus.EXPECTATION_FAILED, exitCodeJoorOrder);
			}
			else {
				exitCodeJoorOrder = MoicConstant.EXIT_CODE_JOOR_ORDER_SUCCESS;
				return new JobsSuccessResponse(true, HttpStatus.OK, exitCodeJoorOrder);
			}
			
		} catch (Exception e) {
			log.error("Failed to process joor order json due to Exception: " + e.getMessage());
			return new ErrorResponse(false, HttpStatus.EXPECTATION_FAILED, exitCodeJoorOrder);
		}

	}

	private Double getCurrencyConversionByUSD(String price, String currency, String joorPriceLabel) {
		
		Double convertedValue = 0d;
		Double actualPrice = Double.parseDouble(new DecimalFormat("##.####").format(Double.parseDouble(price)));
		if(currency.equals(MoicConstant.US_ORDER_CURRENCY)) {
			convertedValue = actualPrice;
		}
		else {
			if (currency.equals(MoicConstant.EUR_ORDER_CURRENCY)) {
				convertedValue = actualPrice * currencyConversionRepository.getConversionValuePerUSDByCurrencyCodeAndJoorPriceLabel(MoicConstant.EUR_ORDER_CURRENCY, joorPriceLabel);
			}
			if (currency.equals(MoicConstant.JPY_ORDER_CURRENCY)) {
				convertedValue = actualPrice * currencyConversionRepository.getConversionValuePerUSDByCurrencyCodeAndJoorPriceLabel(MoicConstant.JPY_ORDER_CURRENCY, joorPriceLabel);
			}
			if (currency.equals(MoicConstant.GBP_ORDER_CURRENCY)) {
				convertedValue = actualPrice * currencyConversionRepository.getConversionValuePerUSDByCurrencyCodeAndJoorPriceLabel(MoicConstant.GBP_ORDER_CURRENCY, joorPriceLabel);
			}
			if (currency.equals(MoicConstant.CAD_ORDER_CURRENCY)) {
				convertedValue = actualPrice * currencyConversionRepository.getConversionValuePerUSDByCurrencyCodeAndJoorPriceLabel(MoicConstant.CAD_ORDER_CURRENCY, joorPriceLabel);
			}
		}

		return Double.parseDouble(new DecimalFormat("##.####").format(convertedValue));
	}
	
	private void deleteCancelledJoorOrder(Object object, File file) {
		JSONObject cancelledOrder = (JSONObject) object;
		Long orderId = Long.parseLong(cancelledOrder.get("order_id").toString());
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
			
			log.debug("Joor orders successfully deleted with Order Id: "+orderId);
		}
		catch(Exception e) {
			fileProcessedSuccessfully = false;
			joorJobProcessedSuccessfully = false;
			log.error("Failed to delete Joor Order due to error" + e.getMessage() + " Joor Order Id: "
					+ ((JSONObject) object).get("order_id") + " File Name: "+ file.getName());
		}
		
	}
	
	private String getSizeConversionByUSD(String itemSize, String country) {
		
		String size = new String();
		SizeConversion sizeConversion = sizeConversionRepository.findByUsSize(itemSize);

		if(sizeConversion != null &&  country.equals(MoicConstant.EUR_ORDER_CURRENCY)) {
			size = sizeConversion.getEuSize();
		}
		else if(sizeConversion != null && country.equals(MoicConstant.GBP_ORDER_CURRENCY)) {
			size = sizeConversion.getUkSize();
		}
		else {
			size = itemSize;
		}
		
		return size;
	}
	
	private JobAudit setJobAuditDetail(Boolean fileProcessedSuccessfully, Boolean joorJobProcessedSuccessfully, String fileName, Timestamp timestamp, String errMsg, int recordsCount, String dataSummary) {
		JobAudit jobAudit = new JobAudit();
		jobAudit.setJobName(MoicConstant.JOOR_ORDER_JOB);
		jobAudit.setJobStatus(joorJobProcessedSuccessfully ? MoicConstant.EXIT_CODE_JOOR_ORDER_SUCCESS : MoicConstant.EXIT_CODE_JOOR_ORDER_FAILURE);
		jobAudit.setFileName(fileName);
		jobAudit.setExecutionDateTime(timestamp);
		jobAudit.setFileStatus(fileProcessedSuccessfully ? archivedFileLocation : failedFileLocation);
		jobAudit.setRecordsCount(recordsCount);
		jobAudit.setExceptionMessage(errMsg);
		jobAudit.setDataSummary(dataSummary);
		
		return jobAudit;
	}
}
