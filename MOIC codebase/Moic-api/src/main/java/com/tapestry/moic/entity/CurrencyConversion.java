package com.tapestry.moic.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.tapestry.moic.constant.MoicConstant;
import com.tapestry.moic.entity.key.CurrencyConversionKeys;

/**
 * The Class CurrencyConversion.
 *
 * @version 0.0.1
 */
@Entity
@Table(name = MoicConstant.CURRENCY_CONVERSION)
@IdClass(CurrencyConversionKeys.class)
public class CurrencyConversion implements Serializable {

	private static final long serialVersionUID = 1L; 
	
	@Id	
	@Column(name = MoicConstant.CURRENCY_CODE, length = 3)
	private String currencyCode;

	@Id
	@Column(name = MoicConstant.JOOR_PRICE_LABEL)
	private String joorPriceLabel;
	
	@Column(name = MoicConstant.CONVERSION_VALUE_PER_USD)
	private Double conversionValuePerUSD;
		
	@Column(name = MoicConstant.MOIC_DISPLAY_RETAIL_PRICE)
	private String moicDisplaySuggestedRetailPrice;
	
	@Column(name = MoicConstant.MOIC_DISPLAY_WHOLESALE_PRICE)
	private String moicDisplayWholesalePrice;

	public CurrencyConversion() {

	}

	/**
	 * @param currencyCode
	 * @param joorPriceLabel
	 * @param conversionValuePerUSD
	 * @param moicDisplaySuggestedRetailPrice
	 * @param moicDisplayWholesalePrice
	 */
	public CurrencyConversion(String currencyCode, String joorPriceLabel, Double conversionValuePerUSD,
			String moicDisplaySuggestedRetailPrice, String moicDisplayWholesalePrice) {
		super();
		this.currencyCode = currencyCode;
		this.joorPriceLabel = joorPriceLabel;
		this.conversionValuePerUSD = conversionValuePerUSD;
		this.moicDisplaySuggestedRetailPrice = moicDisplaySuggestedRetailPrice;
		this.moicDisplayWholesalePrice = moicDisplayWholesalePrice;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getJoorPriceLabel() {
		return joorPriceLabel;
	}

	public void setJoorPriceLabel(String joorPriceLabel) {
		this.joorPriceLabel = joorPriceLabel;
	}

	public Double getConversionValuePerUSD() {
		return conversionValuePerUSD;
	}

	public void setConversionValuePerUSD(Double conversionValuePerUSD) {
		this.conversionValuePerUSD = conversionValuePerUSD;
	}

	public String getMoicDisplaySuggestedRetailPrice() {
		return moicDisplaySuggestedRetailPrice;
	}

	public void setMoicDisplaySuggestedRetailPrice(String moicDisplaySuggestedRetailPrice) {
		this.moicDisplaySuggestedRetailPrice = moicDisplaySuggestedRetailPrice;
	}

	public String getMoicDisplayWholesalePrice() {
		return moicDisplayWholesalePrice;
	}

	public void setMoicDisplayWholesalePrice(String moicDisplayWholesalePrice) {
		this.moicDisplayWholesalePrice = moicDisplayWholesalePrice;
	}

	@Override
	public String toString() {
		return "CurrencyConversion [currencyCode=" + currencyCode + ", joorPriceLabel=" + joorPriceLabel
				+ ", conversionValuePerUSD=" + conversionValuePerUSD + ", moicDisplaySuggestedRetailPrice="
				+ moicDisplaySuggestedRetailPrice + ", moicDisplayWholesalePrice=" + moicDisplayWholesalePrice + "]";
	}
}