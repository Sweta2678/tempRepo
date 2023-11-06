package com.tapestry.moic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tapestry.moic.entity.ProductSizeScale;

@Repository
public interface ProductSizeScaleRepository extends JpaRepository<ProductSizeScale, String>{

}
