package com.tapestry.moic.service.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tapestry.moic.inbound.entity.CustomerMaster_Staging;

@Service
public interface ICustomerMasterStagingService {

	List<CustomerMaster_Staging> getCustomerMasterStagingList();
}
