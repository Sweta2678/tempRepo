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
@Table(name = MoicConstant.PRODUCT_MASTER_STAGING)
public class ProductMasterStaging implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = MoicConstant.PLM_UNIQUE_ID, length = 100)
	@NotNull
	private String plmUniqueID;
	
	@Column(name = MoicConstant.SEASON_CODE, length = 100)
	private String seasonCode;
	
	@Column(name = MoicConstant.BRAND_ID, length = 100)
	@NotNull
	private String brandID;
	
	@Column(name = MoicConstant.DIVISION_ID, length = 100)
	@NotNull
	private String divisionID;
	
	@Column(name = MoicConstant.DEPARTMENT, length = 100)
	@NotNull
	private String department;
	
	@Column(name = MoicConstant.STYLE_ID, length = 100)
	@NotNull
	private String styleID;
	
	@Column(name = MoicConstant.STYLE_DESCRIPTION, length = 255)
	@NotNull
	private String styleDescription;
	
	@Column(name = MoicConstant.STYLE_ACTIVE)
	private Boolean styleActive;
	
	@Column(name = MoicConstant.RETAIL_PRICE)
	private Double retailPrice;
	
	@Column(name = MoicConstant.TARGET_COST)
	private Double targetCost;
	
	@Column(name = MoicConstant.WHOLESALE_PRICE)
	private Double wholesalePrice;
	
	@Column(name = MoicConstant.IN_STORE_DATE)
	private Date inStoreDate;
	
	@Column(name = MoicConstant.DEBUTED_ON_DATE)
	private Date debutedOnDate;
	
	@Column(name = MoicConstant.GROUP, length = 100)
	private String group;
	
	@Column(name = MoicConstant.CHANNEL, length = 100)
	private String channel;
	
	@Column(name = MoicConstant.SIZE_SCALE_ID, length = 100)
	private String sizeScaleId;
	
	@Column(name = MoicConstant.DELIVERY_START_DATE)
	private Date deliveryStartDate;

	@Column(name = MoicConstant.DELIVERY_END_DATE)
	private Date deliveryEndDate;
	
	@Column(name = MoicConstant.DELIVERY_CANCEL_DATE)
	private Date deliveryCancelDate;
	
	@Column(name = MoicConstant.STYLE_LINE_DESCRIPTION_ID, length = 255)
	private String styleLineDescriptionId;

	public ProductMasterStaging() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param plmUniqueID
	 * @param seasonCode
	 * @param brandID
	 * @param divisionID
	 * @param department
	 * @param styleID
	 * @param styleDescription
	 * @param styleActive
	 * @param retailPrice
	 * @param targetCost
	 * @param wholesalePrice
	 * @param inStoreDate
	 * @param debutedOnDate
	 * @param group
	 * @param channel
	 * @param sizeScaleId
	 * @param deliveryStartDate
	 * @param deliveryEndDate
	 * @param deliveryCancelDate
	 * @param styleLineDescriptionId
	 */
	public ProductMasterStaging(@NotNull String plmUniqueID, String seasonCode, @NotNull String brandID,
			@NotNull String divisionID, @NotNull String department, @NotNull String styleID,
			@NotNull String styleDescription, Boolean styleActive, Double retailPrice, Double targetCost,
			Double wholesalePrice, Date inStoreDate, Date debutedOnDate, String group, String channel,
			String sizeScaleId, Date deliveryStartDate, Date deliveryEndDate, Date deliveryCancelDate,
			String styleLineDescriptionId) {
		super();
		this.plmUniqueID = plmUniqueID;
		this.seasonCode = seasonCode;
		this.brandID = brandID;
		this.divisionID = divisionID;
		this.department = department;
		this.styleID = styleID;
		this.styleDescription = styleDescription;
		this.styleActive = styleActive;
		this.retailPrice = retailPrice;
		this.targetCost = targetCost;
		this.wholesalePrice = wholesalePrice;
		this.inStoreDate = inStoreDate;
		this.debutedOnDate = debutedOnDate;
		this.group = group;
		this.channel = channel;
		this.sizeScaleId = sizeScaleId;
		this.deliveryStartDate = deliveryStartDate;
		this.deliveryEndDate = deliveryEndDate;
		this.deliveryCancelDate = deliveryCancelDate;
		this.styleLineDescriptionId = styleLineDescriptionId;
	}



	public String getPlmUniqueID() {
		return plmUniqueID;
	}

	public void setPlmUniqueID(String plmUniqueID) {
		this.plmUniqueID = plmUniqueID;
	}

	public String getSeasonCode() {
		return seasonCode;
	}

	public void setSeasonCode(String seasonCode) {
		this.seasonCode = seasonCode;
	}

	public String getBrandID() {
		return brandID;
	}

	public void setBrandID(String brandID) {
		this.brandID = brandID;
	}

	public String getDivisionID() {
		return divisionID;
	}

	public void setDivisionID(String divisionID) {
		this.divisionID = divisionID;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getStyleID() {
		return styleID;
	}

	public void setStyleID(String styleID) {
		this.styleID = styleID;
	}

	public String getStyleDescription() {
		return styleDescription;
	}

	public void setStyleDescription(String styleDescription) {
		this.styleDescription = styleDescription;
	}

	public Boolean getStyleActive() {
		return styleActive;
	}

	public void setStyleActive(Boolean styleActive) {
		this.styleActive = styleActive;
	}

	public Double getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(Double retailPrice) {
		this.retailPrice = retailPrice;
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

	public Date getInStoreDate() {
		return inStoreDate;
	}

	public void setInStoreDate(Date inStoreDate) {
		this.inStoreDate = inStoreDate;
	}

	public Date getDebutedOnDate() {
		return debutedOnDate;
	}

	public void setDebutedOnDate(Date debutedOnDate) {
		this.debutedOnDate = debutedOnDate;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getSizeScaleId() {
		return sizeScaleId;
	}

	public void setSizeScaleId(String sizeScaleId) {
		this.sizeScaleId = sizeScaleId;
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

	public Date getDeliveryCancelDate() {
		return deliveryCancelDate;
	}

	public void setDeliveryCancelDate(Date deliveryCancelDate) {
		this.deliveryCancelDate = deliveryCancelDate;
	}

	public String getStyleLineDescriptionId() {
		return styleLineDescriptionId;
	}

	public void setStyleLineDescriptionId(String styleLineDescriptionId) {
		this.styleLineDescriptionId = styleLineDescriptionId;
	}

	@Override
	public String toString() {
		return "ProductMasterStaging [plmUniqueID=" + plmUniqueID + ", seasonCode=" + seasonCode + ", brandID="
				+ brandID + ", divisionID=" + divisionID + ", department=" + department + ", styleID=" + styleID
				+ ", styleDescription=" + styleDescription + ", styleActive=" + styleActive + ", retailPrice="
				+ retailPrice + ", targetCost=" + targetCost + ", wholesalePrice=" + wholesalePrice + ", inStoreDate="
				+ inStoreDate + ", debutedOnDate=" + debutedOnDate + ", group=" + group + ", channel=" + channel
				+ ", sizeScaleId=" + sizeScaleId + ", deliveryStartDate=" + deliveryStartDate + ", deliveryEndDate="
				+ deliveryEndDate + ", deliveryCancelDate=" + deliveryCancelDate + ", styleLineDescriptionId="
				+ styleLineDescriptionId + "]";
	}	
}