package com.tapestry.moic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tapestry.moic.entity.JoorOrder;

@Repository
public interface JoorOrderRepository extends JpaRepository<JoorOrder, Long> {
	
	List<JoorOrder> findByCustomerCodeAndOrderSeasonCode(String companyCode, String orderSeasonCode);
	
	JoorOrder findByCustomerCode(String customerCode);
	
	List<JoorOrder> findByOrderSeasonCode(String orderSeasonCode);

}
