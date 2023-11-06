package com.tapestry.moic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tapestry.moic.entity.ProductGlobalPricing;



@Repository
public interface ProductGlobalPricingRepository extends JpaRepository<ProductGlobalPricing, String> {

}

