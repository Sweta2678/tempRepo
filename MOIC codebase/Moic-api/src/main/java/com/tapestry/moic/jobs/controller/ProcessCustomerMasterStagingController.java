package com.tapestry.moic.jobs.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.tapestry.moic.constant.MoicConstant;
import com.tapestry.moic.constant.MoicSources;
import com.tapestry.moic.entity.CustomerMaster;
import com.tapestry.moic.entity.JobAudit;
import com.tapestry.moic.entity.key.CustomerMasterKeys;
import com.tapestry.moic.inbound.entity.CustomerMaster_Staging;
import com.tapestry.moic.jobs.controller.interfaces.IProcessCustomerMasterStagingController;
import com.tapestry.moic.repository.CustomerMasterRepository;
import com.tapestry.moic.response.ErrorResponse;
import com.tapestry.moic.response.JobsSuccessResponse;
import com.tapestry.moic.response.interfaces.MoicResponse;
import com.tapestry.moic.service.CustomerMasterStagingService;
import com.tapestry.moic.service.JobAuditService;

@Component
public class ProcessCustomerMasterStagingController implements IProcessCustomerMasterStagingController{

	private Logger LOGGER = LoggerFactory.getLogger(ProcessCustomerMasterStagingController.class);
	
	@Autowired
	CustomerMasterStagingService customerMasterStagingService;
	
	@Autowired
	CustomerMasterRepository customerMasterRepo;
	
	@Autowired
	JobAuditService jobAuditService;
	
	
	boolean isCustomerMasterStagingSuccessful;
	String exitCodeCustomerMaster;
	
	@Override
	public MoicResponse processCustomerMasterStaging() {
		
		int recordsCount = 0;
		String errMsg = null;
		
		try {
			List<CustomerMaster_Staging> customerMasterStagingList = customerMasterStagingService.getCustomerMasterStagingList();
			isCustomerMasterStagingSuccessful = true;
			
			
			if(!customerMasterStagingList.isEmpty()) {
				
				for(CustomerMaster_Staging customerStaging: customerMasterStagingList) {
					
					try {
						recordsCount++;
						CustomerMasterKeys customerMasterKeys = new CustomerMasterKeys();
						
						customerMasterKeys.setDistributionChannel(customerStaging.getDistributionChannel().trim());
						customerMasterKeys.setDivision(customerStaging.getDivision().trim());
						customerMasterKeys.setSalesOrg(customerStaging.getSalesOrg().trim());
						customerMasterKeys.setShipToNumber(customerStaging.getShipToNumber().trim());
						customerMasterKeys.setSite(customerStaging.getSite().trim());
						customerMasterKeys.setSoldToNumber(customerStaging.getSoldToNumber().trim());
						boolean isCustomerExist = customerMasterRepo.existsById(customerMasterKeys);
						
						if(!isCustomerExist) {
							CustomerMaster customerMaster = new CustomerMaster();
							customerMaster.setArticle(customerStaging.getArticle());
							customerMaster.setBillingCity(customerStaging.getBillingCity());
							customerMaster.setBillingCode(customerStaging.getBillingCode());
							customerMaster.setBillingCountry(customerStaging.getBillingCountry());
							if(null != customerStaging.getBuyerEmail() && customerStaging.getBuyerEmail() != "") {
								customerMaster.setBuyerEmail(customerStaging.getBuyerEmail());
							}
							else {
								customerMaster.setBuyerEmail(MoicConstant.CUSTOMER_DEFAULT_EMAIL);
							}
							customerMaster.setBuyerName(customerStaging.getBuyerName());
							customerMaster.setChannelID(customerStaging.getChannelID());
							customerMaster.setContractValidFrom(customerStaging.getContractValidFrom());
							customerMaster.setContractValidTo(customerStaging.getContractValidTo());
							customerMaster.setCreateDate(new Date());
							customerMaster.setCreatedBy(MoicConstant.INBOUND_RECORDS_CREATED_BY);
							customerMaster.setDistributionChannel(customerStaging.getDistributionChannel().trim());
							customerMaster.setDivision(customerStaging.getDivision().trim());
							customerMaster.setDownloadFlag(customerStaging.getDownloadFlag());
							customerMaster.setExcludeFromSOCreation(MoicConstant.SET_EXCLUDE_FROM_SO_CREATION);
							customerMaster.setImpactedByPreBuy(customerStaging.getImpactedByPreBuy());
							customerMaster.setIsActive(customerStaging.getIsActive());
							customerMaster.setPoNumber(customerStaging.getPoNumber());
							customerMaster.setPoType(customerStaging.getPoType());
							customerMaster.setQuantity(customerStaging.getQuantity());
							customerMaster.setSalesOrderTypePrimary(customerStaging.getSalesOrderTypePrimary());
							customerMaster.setSalesOrderTypeSecondary(customerStaging.getSalesOrderTypeSecondary());
							customerMaster.setSalesOrderTypeTertiary(customerStaging.getSalesOrderTypeTertiary());
							customerMaster.setSalesOrg(customerStaging.getSalesOrg().trim());
							customerMaster.setSegmentID(customerStaging.getSegmentID());
							customerMaster.setShippingAddress1(customerStaging.getShippingAddress1());
							customerMaster.setShippingAddress2(customerStaging.getShippingAddress2());
							customerMaster.setShippingCity(customerStaging.getShippingCity());
							customerMaster.setShippingCountry(customerStaging.getShippingCountry());
							customerMaster.setShippingPostalCode(customerStaging.getShippingPostalCode());
							customerMaster.setShippingState(customerStaging.getShippingState());
							customerMaster.setShipToDescription(customerStaging.getShipToDescription());
							customerMaster.setShipToNumber(customerStaging.getShipToNumber().trim());
							customerMaster.setSite(customerStaging.getSite().trim());
							customerMaster.setSoCancelDate(customerStaging.getSoCancelDate());
							customerMaster.setSoHeaderReqDelDate(customerStaging.getSoHeaderReqDelDate());
							customerMaster.setSoldToDescription(customerStaging.getSoldToDescription());
							customerMaster.setSoldToNumber(customerStaging.getSoldToNumber().trim());
							customerMaster.setSuggestedRetailPriceCurrency(customerStaging.getSuggestedRetailPriceCurrency());
							customerMaster.setTarget(null);
							customerMaster.setUpcCode(customerStaging.getUpcCode());
							customerMaster.setUserName(MoicSources.ETL);
							customerMaster.setWholeSalePriceCurrency(customerStaging.getWholeSalePriceCurrency());
							customerMaster.setSendToJoor(MoicConstant.SET_SEND_TO_JOOR);
							
							CustomerMaster master = customerMasterRepo.save(customerMaster);
							if(master != null) {
								LOGGER.debug("Customer Master new record created. Details: "+customerMaster.getDistributionChannel());
							}
							
						}
						else {
							Optional<CustomerMaster> optCustomerMaster = customerMasterRepo.findById(customerMasterKeys);
							
							if(optCustomerMaster.isPresent()) {
								CustomerMaster existingCustomerMaster = optCustomerMaster.get();
								
								if(existingCustomerMaster.getDownloadFlag() != customerStaging.getDownloadFlag() || existingCustomerMaster.getExcludeFromSOCreation() != customerStaging.getExcludeFromSOCreation()
									|| existingCustomerMaster.getImpactedByPreBuy() != customerStaging.getImpactedByPreBuy() || existingCustomerMaster.getIsActive() != customerStaging.getIsActive()
									|| existingCustomerMaster.getArticle() != customerStaging.getArticle() || existingCustomerMaster.getBillingCity() != existingCustomerMaster.getBillingCity()
									|| existingCustomerMaster.getBillingCode() != customerStaging.getBillingCode() || existingCustomerMaster.getBillingCountry() != customerStaging.getBillingCountry()
									|| existingCustomerMaster.getBuyerEmail() != customerStaging.getBuyerEmail() || existingCustomerMaster.getBuyerName() != customerStaging.getBuyerName()
									|| existingCustomerMaster.getChannelID() != customerStaging.getChannelID() || existingCustomerMaster.getContractValidFrom() != customerStaging.getContractValidFrom()
									|| existingCustomerMaster.getContractValidTo() != customerStaging.getContractValidTo() || existingCustomerMaster.getPoNumber() != customerStaging.getPoNumber()
									|| existingCustomerMaster.getPoType() != customerStaging.getPoType() || existingCustomerMaster.getQuantity() != customerStaging.getQuantity()
									|| existingCustomerMaster.getSalesOrderTypePrimary() != customerStaging.getSalesOrderTypePrimary() || existingCustomerMaster.getSalesOrderTypeSecondary() != customerStaging.getSalesOrderTypePrimary()
									|| existingCustomerMaster.getSalesOrderTypeTertiary() != customerStaging.getSalesOrderTypeTertiary() || existingCustomerMaster.getSoldToDescription() != customerStaging.getSoldToDescription()
									|| existingCustomerMaster.getSegmentID() != customerStaging.getSegmentID() || existingCustomerMaster.getShippingAddress1() != customerStaging.getShippingAddress1()
									|| existingCustomerMaster.getShippingAddress2() != customerStaging.getShippingAddress2() || existingCustomerMaster.getShippingCity() != customerStaging.getShippingCity()
									|| existingCustomerMaster.getShippingCountry() != customerStaging.getShippingCountry() || existingCustomerMaster.getShippingPostalCode() != customerStaging.getShippingPostalCode()
									|| existingCustomerMaster.getShippingState() != customerStaging.getShippingState() || existingCustomerMaster.getShipToDescription() != customerStaging.getShipToDescription()
									|| existingCustomerMaster.getSoCancelDate() != customerStaging.getSoCancelDate() || existingCustomerMaster.getSoHeaderReqDelDate() != customerStaging.getSoHeaderReqDelDate()
									|| existingCustomerMaster.getSuggestedRetailPriceCurrency() != customerStaging.getSuggestedRetailPriceCurrency() 
									|| existingCustomerMaster.getUpcCode() != customerStaging.getUpcCode() || existingCustomerMaster.getWholeSalePriceCurrency() != customerStaging.getWholeSalePriceCurrency())
								{
									existingCustomerMaster.setModifiedBy(MoicConstant.INBOUND_RECORDS_CREATED_BY);
									existingCustomerMaster.setModifiedDate(new Date());
									if(null == existingCustomerMaster.getBuyerEmail() || existingCustomerMaster.getBuyerEmail() == "") {
										existingCustomerMaster.setBuyerEmail(MoicConstant.CUSTOMER_DEFAULT_EMAIL);
									}
									
									CustomerMaster customerMaster = customerMasterRepo.save(existingCustomerMaster);
									if(customerMaster != null) {
										LOGGER.debug("Customer Master table is updated with Staging data");
									}
									
								}
							}
						}
						
					}
					catch (Exception e) {
						errMsg = e.getMessage();
						isCustomerMasterStagingSuccessful = false;
						LOGGER.error("Failed to process customer master table due to Exception : " + e.getMessage() + "Distribution Channel:"+customerStaging.getDistributionChannel()+" Division:"+customerStaging.getDivision()+" Sales Org:"+customerStaging.getSalesOrg()+" Ship To Number:"+customerStaging.getShipToNumber()+" Site:"+customerStaging.getSite()+" Sold To Number:"+ customerStaging.getSoldToNumber());
					}
					
				}
			}
		}
		catch (Exception e) {
			isCustomerMasterStagingSuccessful = false;
			LOGGER.error("failed to process customer master staging table due to Exception : " + e.getMessage());
		}
		finally {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			JobAudit audit = setJobAuditDetail(isCustomerMasterStagingSuccessful, timestamp, errMsg, recordsCount);
			jobAuditService.saveJobAuditDetails(audit);
		}
	
		if(isCustomerMasterStagingSuccessful) {
			exitCodeCustomerMaster = MoicConstant.EXIT_CODE_CUSTOMER_MASTER_INBOUND_SUCCESS;
			return new JobsSuccessResponse(true, HttpStatus.OK, exitCodeCustomerMaster);
		}
		else {
			exitCodeCustomerMaster = MoicConstant.EXIT_CODE_CUSTOMER_MASTER_INBOUND_FAILURE;
			return new ErrorResponse(false, HttpStatus.EXPECTATION_FAILED, exitCodeCustomerMaster);
		}
		
	}
	
	private JobAudit setJobAuditDetail(Boolean isCustomerMasterStagingSuccessful, Timestamp timestamp, String errMsg, int recordsCount) {
		JobAudit jobAudit = new JobAudit();
		jobAudit.setJobName(MoicConstant.CUSTOMER_MASTER_STAGING);
		jobAudit.setJobStatus(isCustomerMasterStagingSuccessful ? MoicConstant.EXIT_CODE_CUSTOMER_MASTER_INBOUND_SUCCESS : MoicConstant.EXIT_CODE_CUSTOMER_MASTER_INBOUND_FAILURE);
		jobAudit.setFileName(MoicConstant.BLANK);
		jobAudit.setExecutionDateTime(timestamp);
		jobAudit.setFileStatus(MoicConstant.BLANK);
		jobAudit.setRecordsCount(recordsCount);
		jobAudit.setExceptionMessage(errMsg);
		jobAudit.setDataSummary(MoicConstant.BLANK);
		
		return jobAudit;
	}

}
