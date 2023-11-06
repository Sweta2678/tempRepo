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
@Table(name = MoicConstant.PRODUCT_SKU_STAGING)
public class ProductSkuStaging implements Serializable{

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
	private Double wholesalePrice;
	
	@Column(name = MoicConstant.RETAIL_PRICE)
	private Double retailPrice;
	
	@Column(name = MoicConstant.DELIVERY_START_DATE)
	private Date deliveryStartDate;
	
	@Column(name = MoicConstant.DELIVERY_END_DATE)
	private Date deliveryEndDate;
	
	@Column(name = MoicConstant.SIZE_SCALE_ID)
	private String sizeScaleID;

	public ProductSkuStaging() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param plmUniqueId
	 * @param seasonCode
	 * @param styleId
	 * @param sKUOID
	 * @param department
	 * @param sku_class
	 * @param subclass
	 * @param sKUID
	 * @param sKUStatus
	 * @param sKUName
	 * @param sKUTeam
	 * @param sKUIntroDate
	 * @param sKUDeleteDate
	 * @param channelExclusive
	 * @param targetCost
	 * @param wholesalePrice
	 * @param retailPrice
	 * @param deliveryStartDate
	 * @param deliveryEndDate
	 * @param sizeScaleID
	 */
	public ProductSkuStaging(@NotNull String plmUniqueId, String seasonCode, @NotNull String styleId, String sKUOID,
			@NotNull String department, String sku_class, String subclass, @NotNull String sKUID, String sKUStatus,
			@NotNull String sKUName, String sKUTeam, Date sKUIntroDate, Date sKUDeleteDate, String channelExclusive,
			Double targetCost, Double wholesalePrice, Double retailPrice, Date deliveryStartDate, Date deliveryEndDate,
			String sizeScaleID) {
		super();
		this.plmUniqueId = plmUniqueId;
		this.seasonCode = seasonCode;
		this.styleId = styleId;
		SKUOID = sKUOID;
		this.department = department;
		this.sku_class = sku_class;
		this.subclass = subclass;
		SKUID = sKUID;
		SKUStatus = sKUStatus;
		SKUName = sKUName;
		SKUTeam = sKUTeam;
		SKUIntroDate = sKUIntroDate;
		SKUDeleteDate = sKUDeleteDate;
		this.channelExclusive = channelExclusive;
		this.targetCost = targetCost;
		this.wholesalePrice = wholesalePrice;
		this.retailPrice = retailPrice;
		this.deliveryStartDate = deliveryStartDate;
		this.deliveryEndDate = deliveryEndDate;
		this.sizeScaleID = sizeScaleID;
	}

	public String getPlmUniqueId() {
		return plmUniqueId;
	}

	public void setPlmUniqueId(String plmUniqueId) {
		this.plmUniqueId = plmUniqueId;
	}

	public String getSeasonCode() {
		return seasonCode;
	}

	public void setSeasonCode(String seasonCode) {
		this.seasonCode = seasonCode;
	}

	public String getStyleId() {
		return styleId;
	}

	public void setStyleId(String styleId) {
		this.styleId = styleId;
	}

	public String getSKUOID() {
		return SKUOID;
	}

	public void setSKUOID(String sKUOID) {
		SKUOID = sKUOID;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getSku_class() {
		return sku_class;
	}

	public void setSku_class(String sku_class) {
		this.sku_class = sku_class;
	}

	public String getSubclass() {
		return subclass;
	}

	public void setSubclass(String subclass) {
		this.subclass = subclass;
	}

	public String getSKUID() {
		return SKUID;
	}

	public void setSKUID(String sKUID) {
		SKUID = sKUID;
	}

	public String getSKUStatus() {
		return SKUStatus;
	}

	public void setSKUStatus(String sKUStatus) {
		SKUStatus = sKUStatus;
	}

	public String getSKUName() {
		return SKUName;
	}

	public void setSKUName(String sKUName) {
		SKUName = sKUName;
	}

	public String getSKUTeam() {
		return SKUTeam;
	}

	public void setSKUTeam(String sKUTeam) {
		SKUTeam = sKUTeam;
	}

	public Date getSKUIntroDate() {
		return SKUIntroDate;
	}

	public void setSKUIntroDate(Date sKUIntroDate) {
		SKUIntroDate = sKUIntroDate;
	}

	public Date getSKUDeleteDate() {
		return SKUDeleteDate;
	}

	public void setSKUDeleteDate(Date sKUDeleteDate) {
		SKUDeleteDate = sKUDeleteDate;
	}

	public String getChannelExclusive() {
		return channelExclusive;
	}

	public void setChannelExclusive(String channelExclusive) {
		this.channelExclusive = channelExclusive;
	}

	public Double getTargetCost() {
		return targetCost;
	}

	public void setTargetCost(Double targetCost) {
		this.targetCost = targetCost;
	}

	public Double getWholesalePrice() {
		return wholesalePrice;
	}

	public void setWholesalePrice(Double wholesalePrice) {
		this.wholesalePrice = wholesalePrice;
	}

	public Double getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(Double retailPrice) {
		this.retailPrice = retailPrice;
	}

	public Date getDeliveryStartDate() {
		return deliveryStartDate;
	}

	public void setDeliveryStartDate(Date deliveryStartDate) {
		this.deliveryStartDate = deliveryStartDate;
	}

	public Date getDeliveryEndDate() {
		return deliveryEndDate;
	}

	public void setDeliveryEndDate(Date deliveryEndDate) {
		this.deliveryEndDate = deliveryEndDate;
	}

	public String getSizeScaleID() {
		return sizeScaleID;
	}

	public void setSizeScaleID(String sizeScaleID) {
		this.sizeScaleID = sizeScaleID;
	}

	@Override
	public String toString() {
		return "ProductSkuStaging [plmUniqueId=" + plmUniqueId + ", seasonCode=" + seasonCode + ", styleId=" + styleId
				+ ", SKUOID=" + SKUOID + ", department=" + department + ", sku_class=" + sku_class + ", subclass="
				+ subclass + ", SKUID=" + SKUID + ", SKUStatus=" + SKUStatus + ", SKUName=" + SKUName + ", SKUTeam="
				+ SKUTeam + ", SKUIntroDate=" + SKUIntroDate + ", SKUDeleteDate=" + SKUDeleteDate
				+ ", channelExclusive=" + channelExclusive + ", targetCost=" + targetCost + ", wholesalePrice="
				+ wholesalePrice + ", retailPrice=" + retailPrice + ", deliveryStartDate=" + deliveryStartDate
				+ ", deliveryEndDate=" + deliveryEndDate + ", sizeScaleID=" + sizeScaleID + "]";
	}
}