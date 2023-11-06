package com.tapestry.moic.inbound.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tapestry.moic.inbound.entity.ProductSkuStaging;

@Repository
public interface ProductSkuInboundRepository extends JpaRepository<ProductSkuStaging, String>{

}
