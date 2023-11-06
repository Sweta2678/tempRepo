package com.tapestry.moic.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tapestry.moic.inbound.entity.CustomerMaster_Staging;
import com.tapestry.moic.inbound.entity.key.CustomerMasterStagingKeys;
import com.tapestry.moic.inbound.repository.CustomerMasterInboundRepository;
import com.tapestry.moic.service.interfaces.ICustomerMasterStagingService;

@Component
public class CustomerMasterStagingService implements ICustomerMasterStagingService{

	private Logger LOGGER = LoggerFactory.getLogger(CustomerMasterStagingService.class);
	
	@Autowired
	CustomerMasterInboundRepository customerMasterInboundRepository;

	@Override
	public List<CustomerMaster_Staging> getCustomerMasterStagingList() {
		
		List<CustomerMaster_Staging> customerMasterStagingList = customerMasterInboundRepository.findAll();
		return customerMasterStagingList;
	}
	
	public void dropAllCustomerMasterRecord() {
		customerMasterInboundRepository.deleteAll();
		LOGGER.debug("All customer master staging records are deleted");
	}
	
	public void deleteCustomerMasterStagingById(CustomerMasterStagingKeys customerMasterStagingKeys) {
		customerMasterInboundRepository.deleteById(customerMasterStagingKeys);
	}
	
}
