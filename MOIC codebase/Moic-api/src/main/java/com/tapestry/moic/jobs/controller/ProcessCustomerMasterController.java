package com.tapestry.moic.jobs.controller;

import java.io.File;
import java.io.FileWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.io.FilenameUtils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.tapestry.moic.constant.MoicConstant;
import com.tapestry.moic.entity.Channel;
import com.tapestry.moic.entity.CustomerMaster;
import com.tapestry.moic.entity.JobAudit;
import com.tapestry.moic.jobs.controller.interfaces.IProcessCustomerMasterController;
import com.tapestry.moic.repository.ChannelRepository;
import com.tapestry.moic.repository.CurrencyConversionRepository;
import com.tapestry.moic.repository.CustomerMasterRepository;
import com.tapestry.moic.response.ErrorResponse;
import com.tapestry.moic.response.JobsSuccessResponse;
import com.tapestry.moic.response.interfaces.MoicResponse;
import com.tapestry.moic.service.JobAuditService;
import com.tapestry.moic.utils.MoicUtil;

@Component
public class ProcessCustomerMasterController implements IProcessCustomerMasterController{

private static final Logger log = LoggerFactory.getLogger(ProcessCustomerMasterController.class);
	
	@Autowired
	CustomerMasterRepository repo;
	
	@Value("${customer.master.outbound.joor.file.location}")
	private String fileLocation;
	
	@Value("${customer.master.outbound.joor.file.name}")
	private String fileName;
	
	@Value("${customer.master.outbound.joor.batch.size}")
	private int fileBatchSize;
	
	String exitCodeCustomerMasterOutbound;
	boolean isCustomerMasterOutboundSuccess = true;
	boolean fileProcessedSuccessfully = true;
	List<CustomerMaster> customerMastersList = new ArrayList<CustomerMaster>();
	
	@Autowired
	CurrencyConversionRepository currencyRepo;
	
	@Autowired
	ChannelRepository channelRepository;
	
	@Autowired
	JobAuditService jobAuditService;
	
	@Override
	public MoicResponse processCustomerMasterJson() {

		
		try {
			
			List<String> channelNames = MoicUtil.getOutletChannlesList();
			List<Channel> channels = channelRepository.findByChannelNameIn(channelNames);
			List<Integer> channelIds = new ArrayList<Integer>();
			
			for(Channel channel : channels) {
				channelIds.add(channel.getChannelId());
			}
			
			List<CustomerMaster> customerMastersList = (List<CustomerMaster>) repo.findAllCustomerGroupBySoldToNumberOrderByCreateDate();
						
			if(!customerMastersList.isEmpty()) {
				
				List<CustomerMaster> retailCustomersList = customerMastersList.stream().filter(retailCustomer -> channelIds.stream().noneMatch(cId -> cId.equals(retailCustomer.getChannelID()))).collect(Collectors.toList());
				List<CustomerMaster> outletCustomersList = customerMastersList.stream().filter(customer -> retailCustomersList.stream().noneMatch(retCustomer -> retCustomer.getChannelID().equals(customer.getChannelID()))).collect(Collectors.toList());
				
				List<List<CustomerMaster>> retailCustomersBatchLists = Lists.partition(retailCustomersList, fileBatchSize);
				List<List<CustomerMaster>> outletCustomersBatchLists = Lists.partition(outletCustomersList, fileBatchSize);
				
				if(!retailCustomersBatchLists.isEmpty()) 
				{
					generateCustomerMasterOutbound(retailCustomersBatchLists, MoicConstant.RETAIL);
				}
				if(!outletCustomersBatchLists.isEmpty())
				{
					generateCustomerMasterOutbound(outletCustomersBatchLists, MoicConstant.OUTLET);
				}
			}
			
		}
		catch (Exception e) {
			isCustomerMasterOutboundSuccess = false;
			log.error("Failed to convert customer master table data to JSON : " + e.getMessage());
		}
		
		if(isCustomerMasterOutboundSuccess) {
			exitCodeCustomerMasterOutbound = MoicConstant.EXIT_CODE_CUSTOMER_MASTER_OUTBOUND_SUCCESS;
			return new JobsSuccessResponse(true, HttpStatus.OK, exitCodeCustomerMasterOutbound);
		}else {
			exitCodeCustomerMasterOutbound = MoicConstant.EXIT_CODE_CUSTOMER_MASTER_OUTBOUND_FAILURE;
			return new ErrorResponse(false, HttpStatus.EXPECTATION_FAILED, exitCodeCustomerMasterOutbound);
		}
	}
	
	private void generateCustomerMasterOutbound(List<List<CustomerMaster>> customerMastersList, String channelType) {

		JSONObject bulkConnectionObj = new JSONObject();
		for(List<CustomerMaster> customerMasterBatchList : customerMastersList) 
		{
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			String srcFileName = FilenameUtils.removeExtension(fileName);
			String fileExtension = FilenameUtils.getExtension(fileName);
			int recordsCount = 0;
			
			File file = new File(fileLocation.trim()+channelType+"_"+srcFileName.trim()+"_"+timestamp.getTime()+"."+fileExtension);
			String errMsg = null;
			
			try {
				
				Map<String, List<CustomerMaster>> groupBySoldToNumberMap = customerMasterBatchList.stream().collect(Collectors.groupingBy(CustomerMaster::getSoldToNumber));
				JSONObject allConnectionObj = new JSONObject();
				JSONArray connectionArray = new JSONArray();
				
				for(Map.Entry<String, List<CustomerMaster>> entry : groupBySoldToNumberMap.entrySet()) {
					
					recordsCount++;
					JSONObject connectionObj = new JSONObject();
					CustomerMaster customerMaster = (CustomerMaster) entry.getValue().get(0);
					connectionObj.put("customer_code", customerMaster.getSoldToNumber());
					connectionObj.put("customer_alias", customerMaster.getSoldToDescription());
					connectionObj.put("customer_name", customerMaster.getSoldToDescription());
					connectionObj.put("customer_email", customerMaster.getBuyerEmail());
					connectionObj.put("customer_contact_name", customerMaster.getBuyerName());
					connectionObj.put("company_number_name", MoicConstant.BLANK);
					connectionObj.put("company_number_code", MoicConstant.BLANK);
					connectionObj.put("customer_group_name", MoicConstant.BLANK);
					connectionObj.put("customer_group_code", MoicConstant.BLANK);
//					connectionObj.put("sales_rep_code", MoicConstant.BLANK);
					
					JSONArray warehouseArray = new JSONArray();
					JSONObject warehouseObj = new JSONObject();
					
					warehouseObj.put("warehouse_name", customerMaster.getSite());
					warehouseObj.put("warehouse_code", customerMaster.getSite());
					
					warehouseArray.put(warehouseObj);
					
					connectionObj.put("warehouses", warehouseArray);
					
					if((null != customerMaster.getBuyerName() || customerMaster.getBuyerName() != "") && 
					(null != customerMaster.getBuyerEmail() || customerMaster.getBuyerEmail() != "")) {
						JSONArray buyersArray = new JSONArray();
						JSONObject buyersObj = new JSONObject();
						
						buyersObj.put("buyer_name", customerMaster.getBuyerName());
						buyersObj.put("buyer_email", customerMaster.getBuyerEmail());
						
						buyersArray.put(buyersObj);
						
						connectionObj.put("buyers", buyersArray);
					}
					
					
					JSONObject billingObj = new JSONObject();
					JSONArray billingAddArray = new JSONArray();
					
					JSONObject billingDetail1 = new JSONObject();
					billingDetail1.put("billing_code", (customerMaster.getBillingCode() != null) ? customerMaster.getBillingCode() : MoicConstant.BLANK);
					billingDetail1.put("billing_name", MoicConstant.BLANK);
					billingDetail1.put("billing_phone", MoicConstant.BLANK);
					billingDetail1.put("billing_address_1", MoicConstant.BLANK);
					billingDetail1.put("billing_address_2", MoicConstant.BLANK);
					billingDetail1.put("billing_city", (customerMaster.getBillingCity() != null) ? customerMaster.getBillingCity() : MoicConstant.BLANK);
					if(customerMaster.getBillingCountry() == MoicConstant.COUNTRY_USA) {
						billingDetail1.put("billing_state", (customerMaster.getShippingState() != null) ? customerMaster.getShippingState() : MoicConstant.BLANK);
					}else {
						billingDetail1.put("billing_state", MoicConstant.BLANK);
					}
					
					billingDetail1.put("billing_postal_code", MoicConstant.BLANK);
					billingDetail1.put("billing_country", (customerMaster.getBillingCountry() != null) ? customerMaster.getBillingCountry() : MoicConstant.BLANK);
											
					billingAddArray.put(billingDetail1);
					
					billingObj.put("billing_addresses", billingAddArray);
					
//					JSONArray paymentArray = new JSONArray();
//					JSONObject paymentMethod1 = new JSONObject();
//					
//					paymentMethod1.put("payment_method_name", MoicConstant.BLANK);
//					paymentMethod1.put("payment_method_code", MoicConstant.BLANK);
//										
//					paymentArray.put(paymentMethod1);
//				
//					billingObj.put("payment_methods", paymentArray);
					connectionObj.put("billing", billingObj);
					
					JSONObject shippingObj = new JSONObject();
					JSONArray shippingArray = new JSONArray();
					
					List<CustomerMaster> shippingCustomers = repo.findBySoldToNumberAndIsActiveAndSendToJoor(customerMaster.getSoldToNumber());
					for(CustomerMaster shippingDetails : shippingCustomers) {
					
						JSONObject shippingDetail = new JSONObject();
						shippingDetail.put("shipping_code", (shippingDetails.getShipToNumber() != null) ? shippingDetails.getShipToNumber() : MoicConstant.BLANK);
						shippingDetail.put("shipping_name",  (shippingDetails.getShipToDescription() != null) ? shippingDetails.getShipToDescription() : MoicConstant.BLANK);
						shippingDetail.put("shipping_phone", MoicConstant.BLANK);
						shippingDetail.put("shipping_email", customerMaster.getBuyerEmail());
						shippingDetail.put("shipping_address_1", (shippingDetails.getShippingAddress1() != null) ? shippingDetails.getShippingAddress1() : MoicConstant.BLANK);
						shippingDetail.put("shipping_address_2", (shippingDetails.getShippingAddress2() != null) ? shippingDetails.getShippingAddress2() : MoicConstant.BLANK);
						shippingDetail.put("shipping_city", (shippingDetails.getShippingCity() != null) ? shippingDetails.getShippingCity() : MoicConstant.BLANK);
						shippingDetail.put("shipping_state", (shippingDetails.getShippingState() != null) ? shippingDetails.getShippingState() : MoicConstant.BLANK);
						shippingDetail.put("shipping_postal_code", (shippingDetails.getShippingPostalCode() != null) ? shippingDetails.getShippingPostalCode() : MoicConstant.BLANK);
						shippingDetail.put("shipping_country", (shippingDetails.getShippingCountry() != null) ? shippingDetails.getShippingCountry() : MoicConstant.BLANK);
						shippingDetail.put("shipping_create_doors", MoicConstant.BLANK);
					
						shippingArray.put(shippingDetail);
					}
					
					shippingObj.put("shipping_addresses", shippingArray);
					
					
					/*
					 * Comment out as no longer needed.
					 * 
					 * JSONArray shippingMethodArr = new JSONArray(); for(CustomerMaster
					 * shippingDetails : entry.getValue()) { JSONObject shippingMethod1 = new
					 * JSONObject(); shippingMethod1.put("shipping_method_name",
					 * MoicConstant.BLANK); shippingMethod1.put("shipping_method_code",
					 * MoicConstant.BLANK);
					 * 
					 * shippingMethodArr.put(shippingMethod1); } shippingObj.put("shipping_methods",
					 * shippingMethodArr);
					 */
					
					connectionObj.put("shipping", shippingObj);
					
					JSONArray pricArray = new JSONArray();
					JSONObject priceDetail1 = new JSONObject();
					
					priceDetail1.put("price_type_label", (customerMaster.getSuggestedRetailPriceCurrencyJoorPriceLabel() != null) ?  customerMaster.getSuggestedRetailPriceCurrencyJoorPriceLabel() : MoicConstant.BLANK);
					priceDetail1.put("price_type_currency_code", (customerMaster.getSuggestedRetailPriceCurrency() != null) ? customerMaster.getSuggestedRetailPriceCurrency() : MoicConstant.BLANK);
					priceDetail1.put("price_type_retail_currency_code", (customerMaster.getSuggestedRetailPriceCurrency() != null) ? customerMaster.getSuggestedRetailPriceCurrency() : MoicConstant.BLANK);
					
					pricArray.put(priceDetail1);
					
					connectionObj.put("price_types", pricArray);
					
					JSONArray customerLocArr = new JSONArray();
					JSONObject customerLocDetail = new JSONObject();
					
					customerLocDetail.put("location_address_id", MoicConstant.BLANK);
					customerLocDetail.put("location_address_1", MoicConstant.BLANK);
					customerLocDetail.put("location_address_2", MoicConstant.BLANK);
					customerLocDetail.put("location_city", (customerMaster.getBillingCity() != null) ? customerMaster.getBillingCity() : MoicConstant.BLANK);
					customerLocDetail.put("location_state", MoicConstant.BLANK);
					customerLocDetail.put("location_zip", MoicConstant.BLANK);
					customerLocDetail.put("location_country", (customerMaster.getBillingCountry() != null) ? customerMaster.getBillingCountry() : MoicConstant.BLANK);
					customerLocDetail.put("location_phone", MoicConstant.BLANK);
					
					customerLocArr.put(customerLocDetail);
					connectionObj.put("customer_locations", customerLocArr);
					
					JSONArray discountArr = new JSONArray();
					JSONObject discountDet = new JSONObject();
					
					discountDet.put("amount", MoicConstant.BLANK);
					discountDet.put("line_item_level", MoicConstant.BLANK);
					discountArr.put(discountDet);
					connectionObj.put("discounts", discountArr);
				
					connectionArray.put(connectionObj);
					
				}
				
				allConnectionObj.put("connection", connectionArray);
				bulkConnectionObj.put("bulk_connections", allConnectionObj);
				log.debug("Customer master data converted to JSON");
				
				FileWriter fileWriter = new FileWriter(file);
				fileWriter.write(bulkConnectionObj.toString(4));
				fileWriter.close();
			}
			catch(Exception e) {
				errMsg = e.getMessage();
				isCustomerMasterOutboundSuccess = false;
				fileProcessedSuccessfully = false;
				log.error("Failed to convert customer master table data to JSON : " + e.getMessage());
			}
			finally {
				JobAudit audit = setJobAuditDetail(isCustomerMasterOutboundSuccess, fileProcessedSuccessfully, file.getName().toString().trim(), timestamp, recordsCount, errMsg);
				jobAuditService.saveJobAuditDetails(audit);
			
			}
		}
	}
	
	private JobAudit setJobAuditDetail(Boolean isCustomerMasterOutboundSuccess,Boolean fileProcessedSuccessfully, String fileName, Timestamp timestamp, int recordsCount, String errMsg) {
		JobAudit jobAudit = new JobAudit();
		jobAudit.setJobName(MoicConstant.CUSTOMER_MASTER_OUTBOUND);
		jobAudit.setJobStatus(isCustomerMasterOutboundSuccess ? MoicConstant.EXIT_CODE_CUSTOMER_MASTER_OUTBOUND_SUCCESS : MoicConstant.EXIT_CODE_CUSTOMER_MASTER_OUTBOUND_FAILURE);
		jobAudit.setFileName(fileName);
		jobAudit.setExecutionDateTime(timestamp);
		jobAudit.setFileStatus(fileProcessedSuccessfully ? MoicConstant.SUCCESS_MESSAGE : MoicConstant.FAILURE_MESSAGE);
		jobAudit.setRecordsCount(recordsCount);
		jobAudit.setExceptionMessage(errMsg);
		jobAudit.setDataSummary(MoicConstant.BLANK);
		
		return jobAudit;
	}
}
