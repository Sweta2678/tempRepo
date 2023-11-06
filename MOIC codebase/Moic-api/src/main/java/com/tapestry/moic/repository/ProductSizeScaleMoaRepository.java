package com.tapestry.moic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tapestry.moic.entity.ProductSizeScaleMoa;
import com.tapestry.moic.entity.key.ProductSizeScaleMoaKeys;

@Repository
public interface ProductSizeScaleMoaRepository extends JpaRepository<ProductSizeScaleMoa, ProductSizeScaleMoaKeys>{
	
	List<ProductSizeScaleMoa> findDistinctBySKUUniqueID(String SKUUniqueID);
	
	List<ProductSizeScaleMoa> findByCombinedSku(String combinedSku);
	
	List<ProductSizeScaleMoa> findBySizeNameAndSKUUniqueIDAndCombinedSku(String sizeName, String SKUUniqueID, String combinedSku);
}
