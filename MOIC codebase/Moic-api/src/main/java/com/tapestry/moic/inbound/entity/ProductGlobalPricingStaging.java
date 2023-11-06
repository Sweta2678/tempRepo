package com.tapestry.moic.inbound.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.tapestry.moic.constant.MoicConstant;

@Entity
@Table(name = MoicConstant.PRODUCT_GLOBAL_PRICING_STAGING)
public class ProductGlobalPricingStaging implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = MoicConstant.PLM_UNIQUE_ID, length = 100)
	@NotNull
	private String plmUniqueID;

	@Column(name = MoicConstant.STYLE_ID, length = 100)
	@NotNull
	private String styleID;

	@Column(name = MoicConstant.US1_RETAIL_PRICE)
	private Double us1RetailPrice;

	@Column(name = MoicConstant.US1_WHOLESALE_PRICE)
	private Double us1WholesalePrice;

	@Column(name = MoicConstant.GB1_RETAIL_PRICE)
	private Double gb1RetailPrice;

	@Column(name = MoicConstant.GB1_WHOLESALE_PRICE)
	private Double gb1WholesalePrice;

	@Column(name = MoicConstant.EU1_RETAIL_PRICE)
	private Double eu1RetailPrice;

	@Column(name = MoicConstant.EU1_WHOLESALE_PRICE)
	private Double eu1WholesalePrice;

	@Column(name = MoicConstant.CA1_RETAIL_PRICE)
	private Double ca1RetailPrice;

	@Column(name = MoicConstant.CA1_WHOLESALE_PRICE)
	private Double ca1WholesalePrice;

	@Column(name = MoicConstant.JPY1_RETAIL_PRICE)
	private Double jpy1RetailPrice;

	@Column(name = MoicConstant.JPY1_WHOLESALE_PRICE)
	private Double jpy1WholesalePrice;

	@Column(name = MoicConstant.EFFECTIVE_DATE)
	private Date effectiveDate;

	public ProductGlobalPricingStaging() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductGlobalPricingStaging(@NotNull String plmUniqueID, @NotNull String styleID, Double us1RetailPrice,
			Double us1WholesalePrice, Double gb1RetailPrice, Double gb1WholesalePrice, Double eu1RetailPrice,
			Double eu1WholesalePrice, Double ca1RetailPrice, Double ca1WholesalePrice, Double jpy1RetailPrice,
			Double jpy1WholesalePrice, Date effectiveDate) {
		super();
		this.plmUniqueID = plmUniqueID;
		this.styleID = styleID;
		this.us1RetailPrice = us1RetailPrice;
		this.us1WholesalePrice = us1WholesalePrice;
		this.gb1RetailPrice = gb1RetailPrice;
		this.gb1WholesalePrice = gb1WholesalePrice;
		this.eu1RetailPrice = eu1RetailPrice;
		this.eu1WholesalePrice = eu1WholesalePrice;
		this.ca1RetailPrice = ca1RetailPrice;
		this.ca1WholesalePrice = ca1WholesalePrice;
		this.jpy1RetailPrice = jpy1RetailPrice;
		this.jpy1WholesalePrice = jpy1WholesalePrice;
		this.effectiveDate = effectiveDate;
	}

	public String getPlmUniqueID() {
		return plmUniqueID;
	}

	public void setPlmUniqueID(String plmUniqueID) {
		this.plmUniqueID = plmUniqueID;
	}

	public String getStyleID() {
		return styleID;
	}

	public void setStyleID(String styleID) {
		this.styleID = styleID;
	}

	public Double getUs1RetailPrice() {
		return us1RetailPrice;
	}

	public void setUs1RetailPrice(Double us1RetailPrice) {
		this.us1RetailPrice = us1RetailPrice;
	}

	public Double getUs1WholesalePrice() {
		return us1WholesalePrice;
	}

	public void setUs1WholesalePrice(Double us1WholesalePrice) {
		this.us1WholesalePrice = us1WholesalePrice;
	}

	public Double getGb1RetailPrice() {
		return gb1RetailPrice;
	}

	public void setGb1RetailPrice(Double gb1RetailPrice) {
		this.gb1RetailPrice = gb1RetailPrice;
	}

	public Double getGb1WholesalePrice() {
		return gb1WholesalePrice;
	}

	public void setGb1WholesalePrice(Double gb1WholesalePrice) {
		this.gb1WholesalePrice = gb1WholesalePrice;
	}

	public Double getEu1RetailPrice() {
		return eu1RetailPrice;
	}

	public void setEu1RetailPrice(Double eu1RetailPrice) {
		this.eu1RetailPrice = eu1RetailPrice;
	}

	public Double getEu1WholesalePrice() {
		return eu1WholesalePrice;
	}

	public void setEu1WholesalePrice(Double eu1WholesalePrice) {
		this.eu1WholesalePrice = eu1WholesalePrice;
	}

	public Double getCa1RetailPrice() {
		return ca1RetailPrice;
	}

	public void setCa1RetailPrice(Double ca1RetailPrice) {
		this.ca1RetailPrice = ca1RetailPrice;
	}

	public Double getCa1WholesalePrice() {
		return ca1WholesalePrice;
	}

	public void setCa1WholesalePrice(Double ca1WholesalePrice) {
		this.ca1WholesalePrice = ca1WholesalePrice;
	}

	public Double getJpy1RetailPrice() {
		return jpy1RetailPrice;
	}

	public void setJpy1RetailPrice(Double jpy1RetailPrice) {
		this.jpy1RetailPrice = jpy1RetailPrice;
	}

	public Double getJpy1WholesalePrice() {
		return jpy1WholesalePrice;
	}

	public void setJpy1WholesalePrice(Double jpy1WholesalePrice) {
		this.jpy1WholesalePrice = jpy1WholesalePrice;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	@Override
	public String toString() {
		return "ProductGlobalPricingStaging [plmUniqueID=" + plmUniqueID + ", styleID=" + styleID + ", us1RetailPrice="
				+ us1RetailPrice + ", us1WholesalePrice=" + us1WholesalePrice + ", gb1RetailPrice=" + gb1RetailPrice
				+ ", gb1WholesalePrice=" + gb1WholesalePrice + ", eu1RetailPrice=" + eu1RetailPrice
				+ ", eu1WholesalePrice=" + eu1WholesalePrice + ", ca1RetailPrice=" + ca1RetailPrice
				+ ", ca1WholesalePrice=" + ca1WholesalePrice + ", jpy1RetailPrice=" + jpy1RetailPrice
				+ ", jpy1WholesalePrice=" + jpy1WholesalePrice + ", effectiveDate=" + effectiveDate + "]";
	}
}
