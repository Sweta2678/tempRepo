package com.tapestry.moic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tapestry.moic.entity.ProductSKU;

@Repository
public interface ProductSKURepository extends JpaRepository<ProductSKU, String> {

	List<ProductSKU> findBySKUID(String sKUID);
	
	List<ProductSKU> findByPlmUniqueId(String plmUniqueId);

	List<ProductSKU> findBySeasonCode(String seasonCode);
	
	List<ProductSKU> findBySeasonCodeAndSKUName(String seasonCode,String SKUName);
		
	ProductSKU getBySKUID(String SKUID);
	
	List<ProductSKU> findByStyleIdAndSKUName(String styleId, String SKUName);
}