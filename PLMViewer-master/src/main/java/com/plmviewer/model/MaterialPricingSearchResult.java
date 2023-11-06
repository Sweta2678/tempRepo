/**
 * 
 */
package com.plmviewer.model;

import java.math.BigDecimal;

/**
 * @author uthanasekarapandian
 *
 */
public class MaterialPricingSearchResult {
	
	private String fromDate;
	private String toDate;
	private String color;
	private String costType	;
	private BigDecimal price;
	private String updatedBy;
	private String currency;
	private BigDecimal matSupplierId;
	/**
	 * @return the matSupplierId
	 */
	public BigDecimal getMatSupplierId() {
		return matSupplierId;
	}
	/**
	 * @param matSupplierId the matSupplierId to set
	 */
	public void setMatSupplierId(BigDecimal matSupplierId) {
		this.matSupplierId = matSupplierId;
	}
	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	/**
	 * @return the from
	 */
	public String getFromDate() {
		return fromDate;
	}

	/**
	 * @return the to
	 */
	public String getToDate() {
		return toDate;
	}
	/**
	 * @param to the to to set
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}
	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}
	/**
	 * @return the costType
	 */
	public String getCostType() {
		return costType;
	}
	/**
	 * @param costType the costType to set
	 */
	public void setCostType(String costType) {
		this.costType = costType;
	}
	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	/**
	 * @return the updatedBy
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}
	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}
	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}	


}
