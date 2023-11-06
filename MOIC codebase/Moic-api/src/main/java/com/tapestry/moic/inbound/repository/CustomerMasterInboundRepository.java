package com.tapestry.moic.inbound.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tapestry.moic.inbound.entity.CustomerMaster_Staging;
import com.tapestry.moic.inbound.entity.key.CustomerMasterStagingKeys;

@Repository
public interface CustomerMasterInboundRepository extends JpaRepository<CustomerMaster_Staging, CustomerMasterStagingKeys>{

	@Override
	default boolean existsById(CustomerMasterStagingKeys id) {

		return false;
	}
}
