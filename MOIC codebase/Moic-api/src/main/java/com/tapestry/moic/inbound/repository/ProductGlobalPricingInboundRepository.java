package com.tapestry.moic.inbound.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tapestry.moic.inbound.entity.ProductGlobalPricingStaging;

@Repository
public interface ProductGlobalPricingInboundRepository extends JpaRepository<ProductGlobalPricingStaging, String>{

}
