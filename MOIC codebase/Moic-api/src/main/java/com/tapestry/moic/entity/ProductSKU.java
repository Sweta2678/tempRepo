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
@Table(name = MoicConstant.PRODUCT_SKU)
@Data
public class ProductSKU implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = MoicConstant.SKU_ID, length = 100)
	@NotNull
	private String SKUID;
		
	@Column(name = MoicConstant.PLM_UNIQUE_ID, length = 100)
	@NotNull
	private String plmUniqueId;
	
	@Column(name = MoicConstant.SEASON_CODE, length = 100)
	private String seasonCode;
	
	@Column(name = MoicConstant.STYLE_ID, length = 100)
	@NotNull
	private String styleId; 
	
	@Column(name = MoicConstant.SKU_OID, length = 100)
	private String SKUOID;
	
	@Column(name = MoicConstant.DEPARTMENT, length = 100)
	@NotNull
	private String department;
	
	@Column(name = MoicConstant.SKU_CLASS, length = 100)
	private String sku_class;
	
	@Column(name = MoicConstant.SUB_CLASS, length = 100)
	private String subclass;
	
	@Column(name = MoicConstant.SKU_STATUS, length = 100)
	private String SKUStatus;
	
	@Column(name = MoicConstant.SKU_NAME, length = 100)
	@NotNull
	private String SKUName;
	
	@Column(name = MoicConstant.SKU_TEAM, length = 100)
	private String SKUTeam;
	
	@Column(name = MoicConstant.SKU_INTRO_DATE)
	private Date SKUIntroDate;
	
	@Column(name = MoicConstant.SKU_DELETE_DATE)
	private Date SKUDeleteDate;
	
	@Column(name = MoicConstant.CHANNEL_EXCLUSIVE, length = 100)
	private String channelExclusive;
	
	@Column(name = MoicConstant.TARGET_COST)
	private Double targetCost;

	@Column(name = MoicConstant.WHOLESALE_PRICE)
	private Double wholesalePrize;
	
	@Column(name = MoicConstant.RETAIL_PRICE)
	private Double retailPrize;
	
	@Column(name = MoicConstant.DELIVERY_START_DATE)
	private Date deliveryStartDate;
	
	@Column(name = MoicConstant.DELIVERY_END_DATE)
	private Date deliveryEndDate;
	
	@Column(name = MoicConstant.SIZE_SCALE_ID, length = 100)
	private String sizeScaleId;
	
	@Column(name = MoicConstant.CREATE_BY, length = 50)
	private String createdBy;
	
	@Column(name = MoicConstant.MODIFIED_BY, length = 50)
	private String modifiedBy;
	
	@Column(name = MoicConstant.CREATE_DATE)
	private Date createDate;
	
	@Column(name = MoicConstant.MODIFIED_DATE)
	private Date modifieDate;

	public ProductSKU() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param sKUID
	 * @param plmUniqueId
	 * @param seasonCode
	 * @param styleId
	 * @param sKUOID
	 * @param department
	 * @param sku_class
	 * @param subclass
	 * @param sKUStatus
	 * @param sKUName
	 * @param sKUTeam
	 * @param sKUIntroDate
	 * @param sKUDeleteDate
	 * @param channelExclusive
	 * @param targetCost
	 * @param wholesalePrize
	 * @param retailPrize
	 * @param deliveryStartDate
	 * @param deliveryEndDate
	 * @param sizeScaleId
	 * @param createdBy
	 * @param modifiedBy
	 * @param createDate
	 * @param modifieDate
	 */
	public ProductSKU(@NotNull String sKUID, @NotNull String plmUniqueId, String seasonCode, @NotNull String styleId,
			String sKUOID, @NotNull String department, String sku_class, String subclass, String sKUStatus,
			@NotNull String sKUName, String sKUTeam, Date sKUIntroDate, Date sKUDeleteDate, String channelExclusive,
			Double targetCost, Double wholesalePrize, Double retailPrize, Date deliveryStartDate, Date deliveryEndDate,
			String sizeScaleId, String createdBy, String modifiedBy, Date createDate, Date modifieDate) {
		super();
		SKUID = sKUID;
		this.plmUniqueId = plmUniqueId;
		this.seasonCode = seasonCode;
		this.styleId = styleId;
		SKUOID = sKUOID;
		this.department = department;
		this.sku_class = sku_class;
		this.subclass = subclass;
		SKUStatus = sKUStatus;
		SKUName = sKUName;
		SKUTeam = sKUTeam;
		SKUIntroDate = sKUIntroDate;
		SKUDeleteDate = sKUDeleteDate;
		this.channelExclusive = channelExclusive;
		this.targetCost = targetCost;
		this.wholesalePrize = wholesalePrize;
		this.retailPrize = retailPrize;
		this.deliveryStartDate = deliveryStartDate;
		this.deliveryEndDate = deliveryEndDate;
		this.sizeScaleId = sizeScaleId;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createDate = createDate;
		this.modifieDate = modifieDate;
	}

	/**
	 * @return the sKUID
	 */
	public String getSKUID() {
		return SKUID;
	}

	/**
	 * @param sKUID the sKUID to set
	 */
	public void setSKUID(String sKUID) {
		SKUID = sKUID;
	}

	/**
	 * @return the plmUniqueId
	 */
	public String getPlmUniqueId() {
		return plmUniqueId;
	}

	/**
	 * @param plmUniqueId the plmUniqueId to set
	 */
	public void setPlmUniqueId(String plmUniqueId) {
		this.plmUniqueId = plmUniqueId;
	}

	/**
	 * @return the seasonCode
	 */
	public String getSeasonCode() {
		return seasonCode;
	}

	/**
	 * @param seasonCode the seasonCode to set
	 */
	public void setSeasonCode(String seasonCode) {
		this.seasonCode = seasonCode;
	}

	/**
	 * @return the styleId
	 */
	public String getStyleId() {
		return styleId;
	}

	/**
	 * @param styleId the styleId to set
	 */
	public void setStyleId(String styleId) {
		this.styleId = styleId;
	}

	/**
	 * @return the sKUOID
	 */
	public String getSKUOID() {
		return SKUOID;
	}

	/**
	 * @param sKUOID the sKUOID to set
	 */
	public void setSKUOID(String sKUOID) {
		SKUOID = sKUOID;
	}

	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * @return the sku_class
	 */
	public String getSku_class() {
		return sku_class;
	}

	/**
	 * @param sku_class the sku_class to set
	 */
	public void setSku_class(String sku_class) {
		this.sku_class = sku_class;
	}

	/**
	 * @return the subclass
	 */
	public String getSubclass() {
		return subclass;
	}

	/**
	 * @param subclass the subclass to set
	 */
	public void setSubclass(String subclass) {
		this.subclass = subclass;
	}

	/**
	 * @return the sKUStatus
	 */
	public String getSKUStatus() {
		return SKUStatus;
	}

	/**
	 * @param sKUStatus the sKUStatus to set
	 */
	public void setSKUStatus(String sKUStatus) {
		SKUStatus = sKUStatus;
	}

	/**
	 * @return the sKUName
	 */
	public String getSKUName() {
		return SKUName;
	}

	/**
	 * @param sKUName the sKUName to set
	 */
	public void setSKUName(String sKUName) {
		SKUName = sKUName;
	}

	/**
	 * @return the sKUTeam
	 */
	public String getSKUTeam() {
		return SKUTeam;
	}

	/**
	 * @param sKUTeam the sKUTeam to set
	 */
	public void setSKUTeam(String sKUTeam) {
		SKUTeam = sKUTeam;
	}

	/**
	 * @return the sKUIntroDate
	 */
	public Date getSKUIntroDate() {
		return SKUIntroDate;
	}

	/**
	 * @param sKUIntroDate the sKUIntroDate to set
	 */
	public void setSKUIntroDate(Date sKUIntroDate) {
		SKUIntroDate = sKUIntroDate;
	}

	/**
	 * @return the sKUDeleteDate
	 */
	public Date getSKUDeleteDate() {
		return SKUDeleteDate;
	}

	/**
	 * @param sKUDeleteDate the sKUDeleteDate to set
	 */
	public void setSKUDeleteDate(Date sKUDeleteDate) {
		SKUDeleteDate = sKUDeleteDate;
	}

	/**
	 * @return the channelExclusive
	 */
	public String getChannelExclusive() {
		return channelExclusive;
	}

	/**
	 * @param channelExclusive the channelExclusive to set
	 */
	public void setChannelExclusive(String channelExclusive) {
		this.channelExclusive = channelExclusive;
	}

	/**
	 * @return the targetCost
	 */
	public Double getTargetCost() {
		return targetCost;
	}

	/**
	 * @param targetCost the targetCost to set
	 */
	public void setTargetCost(Double targetCost) {
		this.targetCost = targetCost;
	}

	/**
	 * @return the wholesalePrize
	 */
	public Double getWholesalePrize() {
		return wholesalePrize;
	}

	/**
	 * @param wholesalePrize the wholesalePrize to set
	 */
	public void setWholesalePrize(Double wholesalePrize) {
		this.wholesalePrize = wholesalePrize;
	}

	/**
	 * @return the retailPrize
	 */
	public Double getRetailPrize() {
		return retailPrize;
	}

	/**
	 * @param retailPrize the retailPrize to set
	 */
	public void setRetailPrize(Double retailPrize) {
		this.retailPrize = retailPrize;
	}

	/**
	 * @return the deliveryStartDate
	 */
	public Date getDeliveryStartDate() {
		return deliveryStartDate;
	}

	/**
	 * @param deliveryStartDate the deliveryStartDate to set
	 */
	public void setDeliveryStartDate(Date deliveryStartDate) {
		this.deliveryStartDate = deliveryStartDate;
	}

	/**
	 * @return the deliveryEndDate
	 */
	public Date getDeliveryEndDate() {
		return deliveryEndDate;
	}

	/**
	 * @param deliveryEndDate the deliveryEndDate to set
	 */
	public void setDeliveryEndDate(Date deliveryEndDate) {
		this.deliveryEndDate = deliveryEndDate;
	}

	/**
	 * @return the sizeScaleId
	 */
	public String getSizeScaleId() {
		return sizeScaleId;
	}

	/**
	 * @param sizeScaleId the sizeScaleId to set
	 */
	public void setSizeScaleId(String sizeScaleId) {
		this.sizeScaleId = sizeScaleId;
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
		return "ProductSKU [SKUID=" + SKUID + ", plmUniqueId=" + plmUniqueId + ", seasonCode=" + seasonCode
				+ ", styleId=" + styleId + ", SKUOID=" + SKUOID + ", department=" + department + ", sku_class="
				+ sku_class + ", subclass=" + subclass + ", SKUStatus=" + SKUStatus + ", SKUName=" + SKUName
				+ ", SKUTeam=" + SKUTeam + ", SKUIntroDate=" + SKUIntroDate + ", SKUDeleteDate=" + SKUDeleteDate
				+ ", channelExclusive=" + channelExclusive + ", targetCost=" + targetCost + ", wholesalePrize="
				+ wholesalePrize + ", retailPrize=" + retailPrize + ", deliveryStartDate=" + deliveryStartDate
				+ ", deliveryEndDate=" + deliveryEndDate + ", sizeScaleId=" + sizeScaleId + ", createdBy=" + createdBy
				+ ", modifiedBy=" + modifiedBy + ", createDate=" + createDate + ", modifieDate=" + modifieDate + "]";
	}
}