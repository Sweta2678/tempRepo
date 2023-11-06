package com.tapestry.moic.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.tapestry.moic.constant.MoicConstant;

import lombok.Data;

@Entity
@Table(name = MoicConstant.PRODUCT_GLOBAL_PRICING)
@Data
public class ProductGlobalPricing implements Serializable {

	/**
	 * 
	 */
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
	
	@Column(name = MoicConstant.CREATE_BY, length = 50)
	private String createdBy;
	
	@Column(name = MoicConstant.MODIFIED_BY, length = 50)
	private String modifiedBy;
	
	@Column(name = MoicConstant.CREATE_DATE)
	private Date createDate;
	
	@Column(name = MoicConstant.MODIFIED_DATE)
	private Date modifieDate;

	public ProductGlobalPricing() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param plmUniqueID
	 * @param styleID
	 * @param us1RetailPrice
	 * @param us1WholesalePrice
	 * @param gb1RetailPrice
	 * @param gb1WholesalePrice
	 * @param eu1RetailPrice
	 * @param eu1WholesalePrice
	 * @param ca1RetailPrice
	 * @param ca1WholesalePrice
	 * @param jpy1RetailPrice
	 * @param jpy1WholesalePrice
	 * @param effectiveDate
	 * @param createdBy
	 * @param modifiedBy
	 * @param createDate
	 * @param modifieDate
	 */
	public ProductGlobalPricing(@NotNull String plmUniqueID, @NotNull String styleID, Double us1RetailPrice,
			Double us1WholesalePrice, Double gb1RetailPrice, Double gb1WholesalePrice, Double eu1RetailPrice,
			Double eu1WholesalePrice, Double ca1RetailPrice, Double ca1WholesalePrice, Double jpy1RetailPrice,
			Double jpy1WholesalePrice, Date effectiveDate, String createdBy, String modifiedBy, Date createDate,
			Date modifieDate) {
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
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createDate = createDate;
		this.modifieDate = modifieDate;
	}

	/**
	 * @return the plmUniqueID
	 */
	public String getPlmUniqueID() {
		return plmUniqueID;
	}

	/**
	 * @param plmUniqueID the plmUniqueID to set
	 */
	public void setPlmUniqueID(String plmUniqueID) {
		this.plmUniqueID = plmUniqueID;
	}

	/**
	 * @return the styleID
	 */
	public String getStyleID() {
		return styleID;
	}

	/**
	 * @param styleID the styleID to set
	 */
	public void setStyleID(String styleID) {
		this.styleID = styleID;
	}

	/**
	 * @return the us1RetailPrice
	 */
	public Double getUs1RetailPrice() {
		return us1RetailPrice;
	}

	/**
	 * @param us1RetailPrice the us1RetailPrice to set
	 */
	public void setUs1RetailPrice(Double us1RetailPrice) {
		this.us1RetailPrice = us1RetailPrice;
	}

	/**
	 * @return the us1WholesalePrice
	 */
	public Double getUs1WholesalePrice() {
		return us1WholesalePrice;
	}

	/**
	 * @param us1WholesalePrice the us1WholesalePrice to set
	 */
	public void setUs1WholesalePrice(Double us1WholesalePrice) {
		this.us1WholesalePrice = us1WholesalePrice;
	}

	/**
	 * @return the gb1RetailPrice
	 */
	public Double getGb1RetailPrice() {
		return gb1RetailPrice;
	}

	/**
	 * @param gb1RetailPrice the gb1RetailPrice to set
	 */
	public void setGb1RetailPrice(Double gb1RetailPrice) {
		this.gb1RetailPrice = gb1RetailPrice;
	}

	/**
	 * @return the gb1WholesalePrice
	 */
	public Double getGb1WholesalePrice() {
		return gb1WholesalePrice;
	}

	/**
	 * @param gb1WholesalePrice the gb1WholesalePrice to set
	 */
	public void setGb1WholesalePrice(Double gb1WholesalePrice) {
		this.gb1WholesalePrice = gb1WholesalePrice;
	}

	/**
	 * @return the eu1RetailPrice
	 */
	public Double getEu1RetailPrice() {
		return eu1RetailPrice;
	}

	/**
	 * @param eu1RetailPrice the eu1RetailPrice to set
	 */
	public void setEu1RetailPrice(Double eu1RetailPrice) {
		this.eu1RetailPrice = eu1RetailPrice;
	}

	/**
	 * @return the eu1WholesalePrice
	 */
	public Double getEu1WholesalePrice() {
		return eu1WholesalePrice;
	}

	/**
	 * @param eu1WholesalePrice the eu1WholesalePrice to set
	 */
	public void setEu1WholesalePrice(Double eu1WholesalePrice) {
		this.eu1WholesalePrice = eu1WholesalePrice;
	}

	/**
	 * @return the ca1RetailPrice
	 */
	public Double getCa1RetailPrice() {
		return ca1RetailPrice;
	}

	/**
	 * @param ca1RetailPrice the ca1RetailPrice to set
	 */
	public void setCa1RetailPrice(Double ca1RetailPrice) {
		this.ca1RetailPrice = ca1RetailPrice;
	}

	/**
	 * @return the ca1WholesalePrice
	 */
	public Double getCa1WholesalePrice() {
		return ca1WholesalePrice;
	}

	/**
	 * @param ca1WholesalePrice the ca1WholesalePrice to set
	 */
	public void setCa1WholesalePrice(Double ca1WholesalePrice) {
		this.ca1WholesalePrice = ca1WholesalePrice;
	}

	/**
	 * @return the jpy1RetailPrice
	 */
	public Double getJpy1RetailPrice() {
		return jpy1RetailPrice;
	}

	/**
	 * @param jpy1RetailPrice the jpy1RetailPrice to set
	 */
	public void setJpy1RetailPrice(Double jpy1RetailPrice) {
		this.jpy1RetailPrice = jpy1RetailPrice;
	}

	/**
	 * @return the jpy1WholesalePrice
	 */
	public Double getJpy1WholesalePrice() {
		return jpy1WholesalePrice;
	}

	/**
	 * @param jpy1WholesalePrice the jpy1WholesalePrice to set
	 */
	public void setJpy1WholesalePrice(Double jpy1WholesalePrice) {
		this.jpy1WholesalePrice = jpy1WholesalePrice;
	}

	/**
	 * @return the effectiveDate
	 */
	public Date getEffectiveDate() {
		return effectiveDate;
	}

	/**
	 * @param effectiveDate the effectiveDate to set
	 */
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the modifiedBy
	 */
	public String getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * @param modifiedBy the modifiedBy to set
	 */
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the modifieDate
	 */
	public Date getModifieDate() {
		return modifieDate;
	}

	/**
	 * @param modifieDate the modifieDate to set
	 */
	public void setModifieDate(Date modifieDate) {
		this.modifieDate = modifieDate;
	}

	@Override
	public String toString() {
		return "ProductGlobalPricing [plmUniqueID=" + plmUniqueID + ", styleID=" + styleID + ", us1RetailPrice="
				+ us1RetailPrice + ", us1WholesalePrice=" + us1WholesalePrice + ", gb1RetailPrice=" + gb1RetailPrice
				+ ", gb1WholesalePrice=" + gb1WholesalePrice + ", eu1RetailPrice=" + eu1RetailPrice
				+ ", eu1WholesalePrice=" + eu1WholesalePrice + ", ca1RetailPrice=" + ca1RetailPrice
				+ ", ca1WholesalePrice=" + ca1WholesalePrice + ", jpy1RetailPrice=" + jpy1RetailPrice
				+ ", jpy1WholesalePrice=" + jpy1WholesalePrice + ", effectiveDate=" + effectiveDate + ", createdBy="
				+ createdBy + ", modifiedBy=" + modifiedBy + ", createDate=" + createDate + ", modifieDate="
				+ modifieDate + "]";
	}
}