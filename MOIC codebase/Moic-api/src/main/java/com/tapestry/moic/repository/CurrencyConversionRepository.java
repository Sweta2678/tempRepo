package com.tapestry.moic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tapestry.moic.entity.CurrencyConversion;

@Repository
public interface CurrencyConversionRepository extends JpaRepository<CurrencyConversion, Integer>{
	
	Double getConversionValuePerUSDByCurrencyCodeAndJoorPriceLabel(String currencyCode, String joorPriceLabel);
	
	String getCurrencyCodeByMoicDisplaySuggestedRetailPrice(String moicDisplaySuggestedRetailPrice);
	
	String getCurrencyCodeByMoicDisplayWholesalePrice(String moicDisplayWholesalePrice);
	
	String getMoicDisplaySuggestedRetailPriceByCurrencyCodeAndJoorPriceLabel(String currencyCode, String joorPriceLabel);

	String getMoicDisplayWholesalePriceByCurrencyCodeAndJoorPriceLabel(String currencyCode, String joorPriceLabel);
	
	String getJoorPriceLabelByMoicDisplaySuggestedRetailPrice(String moicDisplaySuggestedRetailPrice);
	
	String getJoorPriceLabelByMoicDisplayWholesalePrice(String moicDisplayWholesalePrice);
}
