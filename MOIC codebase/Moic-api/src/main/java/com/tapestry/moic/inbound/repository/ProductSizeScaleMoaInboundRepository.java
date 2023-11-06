package com.tapestry.moic.inbound.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tapestry.moic.inbound.entity.ProductSizeScaleMoaStaging;
import com.tapestry.moic.inbound.entity.key.ProductSizeScaleMoaStagingKeys;

@Repository
public interface ProductSizeScaleMoaInboundRepository extends JpaRepository<ProductSizeScaleMoaStaging, ProductSizeScaleMoaStagingKeys>{

}
