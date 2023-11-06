package com.tapestry.moic.dto;

import java.util.Date;


/**
 * The Class FinalizedBuyDto.
 *
 * @version 0.0.1
 */

public class FinalizedBuyDto {

	private Date	uploadDate;
	private String	channel;
	private Boolean	preBuySKU;
	private String	season;
	private String	joor_pmdb_SKU;//Joor OR PMDB mapping with "SKU" field in class FinalizedBuy
	private String	styleColor;
	private String	styleNumber;
	private String	styleName;
	private String	materialName;
	private String	colorName;
	private String	colorCode;
	private Date	skuIntroDate;
	private String	department;
	private String parentsClass;// mapping with "class" field in class FinalizedBuy
	private double	retailPrice;
	private double	targetCost;
	private String	coo;
	private int	totalBuyQuantity;
	private Date	scheduledDeliveryDate;
	private String	orderNotes;
	private String	exFactJan;
	private String	exFactFeb;
	private String	exFactMar;
	private Date	inStoreDate;
	private String	vendor;
	private String	purchaseGroup;
	private String	styleDescription;
	private String	storageLocation;
	private String	site;
	private String	stockSegment;
	private String	newness;
	private String	shippingInstruction;
	private Boolean	excludeFromSOCreation;
	private String	salesDocType;
	private String	salesOrg;
	private String	distributionChannel;
	private String	division;
	private String	soldToNumber;
	private String	shipToNumber;
	private Date	soCancelDate;
	private String	poNumber;
	private String	poType;
	private String	orderReason;
	private String	reqSegment;
	private int	target;
	private String	moqSKU;//MOQ mapping with "SKU" field in class FinalizedBuy
	private Boolean	moqDelete;//MOQ mapping with "effectiveDate" field in class FinalizedBuy
	private Date	moqEffectiveDate;//MOQ mapping with "effectiveDate" field in class FinalizedBuy
	private String	oldSKU;
	private String	newSKU;
	private Boolean	drop;
	private String	level;
	private String	levelID;
	private Boolean	skuChangeDelete;//SKUChange mapping with "effectiveDate" field in class FinalizedBuy
	private Date	skuChangeEffectiveDate;//SKUChange mapping with "effectiveDate" field in class FinalizedBuy
	private Date	cancelDeliveryDate;
	private Date SoHeaderReqDelDate;
	

	/* Audit fields */	
	private String userId;
	private String userName;
	private Date createDate;
	private Date modifiedDate;
	private String createdBy;
	private String modifiedBy;
	private String source;
	private String lastDownloadDate;
	private Boolean downloadFlag;

	public FinalizedBuyDto() {
	}

	/**
	 * @param uploadDate
	 * @param channel
	 * @param preBuySKU
	 * @param season
	 * @param joor_pmdb_SKU
	 * @param styleColor
	 * @param styleNumber
	 * @param styleName
	 * @param materialName
	 * @param colorName
	 * @param colorCode
	 * @param skuIntroDate
	 * @param department
	 * @param parentsClass
	 * @param retailPrice
	 * @param targetCost
	 * @param coo
	 * @param totalBuyQuantity
	 * @param scheduledDeliveryDate
	 * @param orderNotes
	 * @param exFactJan
	 * @param exFactFeb
	 * @param exFactMar
	 * @param inStoreDate
	 * @param vendor
	 * @param purchaseGroup
	 * @param styleDescription
	 * @param storageLocation
	 * @param site
	 * @param stockSegment
	 * @param newness
	 * @param shippingInstruction
	 * @param excludeFromSOCreation
	 * @param salesDocType
	 * @param salesOrg
	 * @param distributionChannel
	 * @param division
	 * @param soldToNumber
	 * @param shipToNumber
	 * @param soCancelDate
	 * @param poNumber
	 * @param poType
	 * @param orderReason
	 * @param reqSegment
	 * @param target
	 * @param moqSKU
	 * @param moqDelete
	 * @param moqEffectiveDate
	 * @param oldSKU
	 * @param newSKU
	 * @param drop
	 * @param level
	 * @param levelID
	 * @param skuChangeDelete
	 * @param skuChangeEffectiveDate
	 */


	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public Boolean getPreBuySKU() {
		return preBuySKU;
	}

	public void setPreBuySKU(Boolean preBuySKU) {
		this.preBuySKU = preBuySKU;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public String getJoor_pmdb_SKU() {
		return joor_pmdb_SKU;
	}

	public void setJoor_pmdb_SKU(String joor_pmdb_SKU) {
		this.joor_pmdb_SKU = joor_pmdb_SKU;
	}

	public String getStyleColor() {
		return styleColor;
	}

	public void setStyleColor(String styleColor) {
		this.styleColor = styleColor;
	}

	public String getStyleNumber() {
		return styleNumber;
	}

	public void setStyleNumber(String styleNumber) {
		this.styleNumber = styleNumber;
	}

	public String getStyleName() {
		return styleName;
	}

	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public String getColorCode() {
		return colorCode;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}

	public Date getSkuIntroDate() {
		return skuIntroDate;
	}

	public void setSkuIntroDate(Date skuIntroDate) {
		this.skuIntroDate = skuIntroDate;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getParentsClass() {
		return parentsClass;
	}

	public void setParentsClass(String parentsClass) {
		this.parentsClass = parentsClass;
	}

	public double getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(double retailPrice) {
		this.retailPrice = retailPrice;
	}

	public double getTargetCost() {
		return targetCost;
	}

	public void setTargetCost(double targetCost) {
		this.targetCost = targetCost;
	}

	public String getCoo() {
		return coo;
	}

	public void setCoo(String coo) {
		this.coo = coo;
	}

	public int getTotalBuyQuantity() {
		return totalBuyQuantity;
	}

	public void setTotalBuyQuantity(int totalBuyQuantity) {
		this.totalBuyQuantity = totalBuyQuantity;
	}

	public Date getScheduledDeliveryDate() {
		return scheduledDeliveryDate;
	}

	public void setScheduledDeliveryDate(Date scheduledDeliveryDate) {
		this.scheduledDeliveryDate = scheduledDeliveryDate;
	}

	public String getOrderNotes() {
		return orderNotes;
	}

	public void setOrderNotes(String orderNotes) {
		this.orderNotes = orderNotes;
	}

	public String getExFactJan() {
		return exFactJan;
	}

	public void setExFactJan(String exFactJan) {
		this.exFactJan = exFactJan;
	}

	public String getExFactFeb() {
		return exFactFeb;
	}

	public void setExFactFeb(String exFactFeb) {
		this.exFactFeb = exFactFeb;
	}

	public String getExFactMar() {
		return exFactMar;
	}

	public void setExFactMar(String exFactMar) {
		this.exFactMar = exFactMar;
	}

	public Date getInStoreDate() {
		return inStoreDate;
	}

	public void setInStoreDate(Date inStoreDate) {
		this.inStoreDate = inStoreDate;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getPurchaseGroup() {
		return purchaseGroup;
	}

	public void setPurchaseGroup(String purchaseGroup) {
		this.purchaseGroup = purchaseGroup;
	}

	public String getStyleDescription() {
		return styleDescription;
	}

	public void setStyleDescription(String styleDescription) {
		this.styleDescription = styleDescription;
	}

	public String getStorageLocation() {
		return storageLocation;
	}

	public void setStorageLocation(String storageLocation) {
		this.storageLocation = storageLocation;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getStockSegment() {
		return stockSegment;
	}

	public void setStockSegment(String stockSegment) {
		this.stockSegment = stockSegment;
	}

	public String getNewness() {
		return newness;
	}

	public void setNewness(String newness) {
		this.newness = newness;
	}

	public String getShippingInstruction() {
		return shippingInstruction;
	}

	public void setShippingInstruction(String shippingInstruction) {
		this.shippingInstruction = shippingInstruction;
	}

	public Boolean getExcludeFromSOCreation() {
		return excludeFromSOCreation;
	}

	public void setExcludeFromSOCreation(Boolean excludeFromSOCreation) {
		this.excludeFromSOCreation = excludeFromSOCreation;
	}

	public String getSalesDocType() {
		return salesDocType;
	}

	public void setSalesDocType(String salesDocType) {
		this.salesDocType = salesDocType;
	}

	public String getSalesOrg() {
		return salesOrg;
	}

	public void setSalesOrg(String salesOrg) {
		this.salesOrg = salesOrg;
	}

	public String getDistributionChannel() {
		return distributionChannel;
	}

	public void setDistributionChannel(String distributionChannel) {
		this.distributionChannel = distributionChannel;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getSoldToNumber() {
		return soldToNumber;
	}

	public void setSoldToNumber(String soldToNumber) {
		this.soldToNumber = soldToNumber;
	}

	public String getShipToNumber() {
		return shipToNumber;
	}

	public void setShipToNumber(String shipToNumber) {
		this.shipToNumber = shipToNumber;
	}

	public Date getSoCancelDate() {
		return soCancelDate;
	}

	public void setSoCancelDate(Date soCancelDate) {
		this.soCancelDate = soCancelDate;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public String getPoType() {
		return poType;
	}

	public void setPoType(String poType) {
		this.poType = poType;
	}

	public String getOrderReason() {
		return orderReason;
	}

	public void setOrderReason(String orderReason) {
		this.orderReason = orderReason;
	}

	public String getReqSegment() {
		return reqSegment;
	}

	public void setReqSegment(String reqSegment) {
		this.reqSegment = reqSegment;
	}

	public int getTarget() {
		return target;
	}

	public void setTarget(int target) {
		this.target = target;
	}

	public String getMoqSKU() {
		return moqSKU;
	}

	public void setMoqSKU(String moqSKU) {
		this.moqSKU = moqSKU;
	}

	public Boolean getMoqDelete() {
		return moqDelete;
	}

	public void setMoqDelete(Boolean moqDelete) {
		this.moqDelete = moqDelete;
	}

	public Date getMoqEffectiveDate() {
		return moqEffectiveDate;
	}

	public void setMoqEffectiveDate(Date moqEffectiveDate) {
		this.moqEffectiveDate = moqEffectiveDate;
	}

	public String getOldSKU() {
		return oldSKU;
	}

	public void setOldSKU(String oldSKU) {
		this.oldSKU = oldSKU;
	}

	public String getNewSKU() {
		return newSKU;
	}

	public void setNewSKU(String newSKU) {
		this.newSKU = newSKU;
	}

	public Boolean getDrop() {
		return drop;
	}

	public void setDrop(Boolean drop) {
		this.drop = drop;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getLevelID() {
		return levelID;
	}

	public void setLevelID(String levelID) {
		this.levelID = levelID;
	}

	public Boolean getSkuChangeDelete() {
		return skuChangeDelete;
	}

	public void setSkuChangeDelete(Boolean skuChangeDelete) {
		this.skuChangeDelete = skuChangeDelete;
	}

	public Date getSkuChangeEffectiveDate() {
		return skuChangeEffectiveDate;
	}

	public void setSkuChangeEffectiveDate(Date skuChangeEffectiveDate) {
		this.skuChangeEffectiveDate = skuChangeEffectiveDate;
	}

	public Date getCancelDeliveryDate() {
		return cancelDeliveryDate;
	}

	public void setCancelDeliveryDate(Date cancelDeliveryDate) {
		this.cancelDeliveryDate = cancelDeliveryDate;
	}
	public Date getSoHeaderReqDelDate() {
		return SoHeaderReqDelDate;
	}

	public void setSoHeaderReqDelDate(Date soHeaderReqDelDate) {
		SoHeaderReqDelDate = soHeaderReqDelDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getLastDownloadDate() {
		return lastDownloadDate;
	}

	public void setLastDownloadDate(String lastDownloadDate) {
		this.lastDownloadDate = lastDownloadDate;
	}

	public Boolean getDownloadFlag() {
		return downloadFlag;
	}

	public void setDownloadFlag(Boolean downloadFlag) {
		this.downloadFlag = downloadFlag;
	}

	@Override
	public String toString() {
		return "FinalizedBuyDto [uploadDate=" + uploadDate + ", channel=" + channel + ", preBuySKU=" + preBuySKU
				+ ", season=" + season + ", joor_pmdb_SKU=" + joor_pmdb_SKU + ", styleColor=" + styleColor
				+ ", styleNumber=" + styleNumber + ", styleName=" + styleName + ", materialName=" + materialName
				+ ", colorName=" + colorName + ", colorCode=" + colorCode + ", skuIntroDate=" + skuIntroDate
				+ ", department=" + department + ", parentsClass=" + parentsClass + ", retailPrice=" + retailPrice
				+ ", targetCost=" + targetCost + ", coo=" + coo + ", totalBuyQuantity=" + totalBuyQuantity
				+ ", scheduledDeliveryDate=" + scheduledDeliveryDate + ", orderNotes=" + orderNotes + ", exFactJan="
				+ exFactJan + ", exFactFeb=" + exFactFeb + ", exFactMar=" + exFactMar + ", inStoreDate=" + inStoreDate
				+ ", vendor=" + vendor + ", purchaseGroup=" + purchaseGroup + ", styleDescription=" + styleDescription
				+ ", storageLocation=" + storageLocation + ", site=" + site + ", stockSegment=" + stockSegment
				+ ", newness=" + newness + ", shippingInstruction=" + shippingInstruction + ", excludeFromSOCreation="
				+ excludeFromSOCreation + ", salesDocType=" + salesDocType + ", salesOrg=" + salesOrg
				+ ", distributionChannel=" + distributionChannel + ", division=" + division + ", soldToNumber="
				+ soldToNumber + ", shipToNumber=" + shipToNumber + ", soCancelDate=" + soCancelDate + ", poNumber="
				+ poNumber + ", poType=" + poType + ", orderReason=" + orderReason + ", reqSegment=" + reqSegment
				+ ", target=" + target + ", moqSKU=" + moqSKU + ", moqDelete=" + moqDelete + ", moqEffectiveDate="
				+ moqEffectiveDate + ", oldSKU=" + oldSKU + ", newSKU=" + newSKU + ", drop=" + drop + ", level=" + level
				+ ", levelID=" + levelID + ", skuChangeDelete=" + skuChangeDelete + ", skuChangeEffectiveDate="
				+ skuChangeEffectiveDate + ", cancelDeliveryDate=" + cancelDeliveryDate + ", userId=" + userId
				+ ", userName=" + userName + ", createDate=" + createDate + ", modifiedDate=" + modifiedDate
				+ ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", source=" + source
				+ ", lastDownloadDate=" + lastDownloadDate + ", downloadFlag=" + downloadFlag + ", getUploadDate()="
				+ getUploadDate() + ", getChannel()=" + getChannel() + ", getPreBuySKU()=" + getPreBuySKU()
				+ ", getSeason()=" + getSeason() + ", getJoor_pmdb_SKU()=" + getJoor_pmdb_SKU() + ", getStyleColor()="
				+ getStyleColor() + ", getStyleNumber()=" + getStyleNumber() + ", getStyleName()=" + getStyleName()
				+ ", getMaterialName()=" + getMaterialName() + ", getColorName()=" + getColorName()
				+ ", getColorCode()=" + getColorCode() + ", getSkuIntroDate()=" + getSkuIntroDate()
				+ ", getDepartment()=" + getDepartment() + ", getParentsClass()=" + getParentsClass()
				+ ", getRetailPrice()=" + getRetailPrice() + ", getTargetCost()=" + getTargetCost() + ", getCoo()="
				+ getCoo() + ", getTotalBuyQuantity()=" + getTotalBuyQuantity() + ", getScheduledDeliveryDate()="
				+ getScheduledDeliveryDate() + ", getOrderNotes()=" + getOrderNotes() + ", getExFactJan()="
				+ getExFactJan() + ", getExFactFeb()=" + getExFactFeb() + ", getExFactMar()=" + getExFactMar()
				+ ", getInStoreDate()=" + getInStoreDate() + ", getVendor()=" + getVendor() + ", getPurchaseGroup()="
				+ getPurchaseGroup() + ", getStyleDescription()=" + getStyleDescription() + ", getStorageLocation()="
				+ getStorageLocation() + ", getSite()=" + getSite() + ", getStockSegment()=" + getStockSegment()
				+ ", getNewness()=" + getNewness() + ", getShippingInstruction()=" + getShippingInstruction()
				+ ", getExcludeFromSOCreation()=" + getExcludeFromSOCreation() + ", getSalesDocType()="
				+ getSalesDocType() + ", getSalesOrg()=" + getSalesOrg() + ", getDistributionChannel()="
				+ getDistributionChannel() + ", getDivision()=" + getDivision() + ", getSoldToNumber()="
				+ getSoldToNumber() + ", getShipToNumber()=" + getShipToNumber() + ", getSoCancelDate()="
				+ getSoCancelDate() + ", getPoNumber()=" + getPoNumber() + ", getPoType()=" + getPoType()
				+ ", getOrderReason()=" + getOrderReason() + ", getReqSegment()=" + getReqSegment() + ", getTarget()="
				+ getTarget() + ", getMoqSKU()=" + getMoqSKU() + ", getMoqDelete()=" + getMoqDelete()
				+ ", getMoqEffectiveDate()=" + getMoqEffectiveDate() + ", getOldSKU()=" + getOldSKU() + ", getNewSKU()="
				+ getNewSKU() + ", getDrop()=" + getDrop() + ", getLevel()=" + getLevel() + ", getLevelID()="
				+ getLevelID() + ", getSkuChangeDelete()=" + getSkuChangeDelete() + ", getSkuChangeEffectiveDate()="
				+ getSkuChangeEffectiveDate() + ", getCancelDeliveryDate()=" + getCancelDeliveryDate()
				+ ", getUserId()=" + getUserId() + ", getUserName()=" + getUserName() + ", getCreateDate()="
				+ getCreateDate() + ", getModifiedDate()=" + getModifiedDate() + ", getCreatedBy()=" + getCreatedBy()
				+ ", getModifiedBy()=" + getModifiedBy() + ", getSource()=" + getSource() + ", getLastDownloadDate()="
				+ getLastDownloadDate() + ", getDownloadFlag()=" + getDownloadFlag() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	
}
