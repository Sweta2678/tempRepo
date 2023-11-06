package com.tapestry.moic.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Class ConfigDto.
 *
 * @version 0.0.1
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConfigDto {
	
	private String soldToNumber;
	private String soldToDescription;
	private String shipToNumber;
	private String shipToDescription;
	private String salesOrg;
	private String distributionChannel;
	private String division;
	private String channel; //will be filled from channel look up entity
	private Boolean excludeFromSOCreation;
	private String salesOrderTypePrimary;
	private String salesOrderTypeSecondary;
	private String salesOrderTypeTertiary;
	private String segment; //will be filled from segment look up entity
	private String site;
	private Integer target;
	private String suggestedRetailPriceCurrency;
	private String wholeSalePriceCurrency;
	private Boolean isActive;
	private Boolean sendToJoor;
	private Boolean impactedByPreBuy;
	private Integer index;
	private String suggestedRetailPriceCurrencyJoorPriceLabel;
	private String wholeSalePriceCurrencyJoorPriceLabel;
	private String salesDocTypeforAltSite;
	private String altSite;
	
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

	public ConfigDto() {
		
	}

	/**
	 * @param soldToNumber
	 * @param soldToDescription
	 * @param shipToNumber
	 * @param shipToDescription
	 * @param salesOrg
	 * @param distributionChannel
	 * @param division
	 * @param channel
	 * @param excludeFromSOCreation
	 * @param salesOrderTypePrimary
	 * @param salesOrderTypeSecondary
	 * @param salesOrderTypeTertiary
	 * @param segment
	 * @param site
	 * @param target
	 * @param suggestedRetailPriceCurrency
	 * @param wholeSalePriceCurrency
	 * @param isActive
	 * @param sendToJoor
	 * @param impactedByPreBuy
	 * @param index
	 * @param suggestedRetailPriceCurrencyJoorPriceLabel
	 * @param wholeSalePriceCurrencyJoorPriceLabel
	 * @param salesDocTypeforAltSite
	 * @param altSite
	 * @param userId
	 * @param userName
	 * @param createDate
	 * @param modifiedDate
	 * @param createdBy
	 * @param modifiedBy
	 * @param source
	 * @param lastDownloadDate
	 * @param downloadFlag
	 */
	public ConfigDto(String soldToNumber, String soldToDescription, String shipToNumber, String shipToDescription,
			String salesOrg, String distributionChannel, String division, String channel, Boolean excludeFromSOCreation,
			String salesOrderTypePrimary, String salesOrderTypeSecondary, String salesOrderTypeTertiary, String segment,
			String site, Integer target, String suggestedRetailPriceCurrency, String wholeSalePriceCurrency,
			Boolean isActive, Boolean sendToJoor, Boolean impactedByPreBuy, Integer index,
			String suggestedRetailPriceCurrencyJoorPriceLabel, String wholeSalePriceCurrencyJoorPriceLabel,
			String salesDocTypeforAltSite, String altSite, String userId, String userName, Date createDate,
			Date modifiedDate, String createdBy, String modifiedBy, String source, String lastDownloadDate,
			Boolean downloadFlag) {
		super();
		this.soldToNumber = soldToNumber;
		this.soldToDescription = soldToDescription;
		this.shipToNumber = shipToNumber;
		this.shipToDescription = shipToDescription;
		this.salesOrg = salesOrg;
		this.distributionChannel = distributionChannel;
		this.division = division;
		this.channel = channel;
		this.excludeFromSOCreation = excludeFromSOCreation;
		this.salesOrderTypePrimary = salesOrderTypePrimary;
		this.salesOrderTypeSecondary = salesOrderTypeSecondary;
		this.salesOrderTypeTertiary = salesOrderTypeTertiary;
		this.segment = segment;
		this.site = site;
		this.target = target;
		this.suggestedRetailPriceCurrency = suggestedRetailPriceCurrency;
		this.wholeSalePriceCurrency = wholeSalePriceCurrency;
		this.isActive = isActive;
		this.sendToJoor = sendToJoor;
		this.impactedByPreBuy = impactedByPreBuy;
		this.index = index;
		this.suggestedRetailPriceCurrencyJoorPriceLabel = suggestedRetailPriceCurrencyJoorPriceLabel;
		this.wholeSalePriceCurrencyJoorPriceLabel = wholeSalePriceCurrencyJoorPriceLabel;
		this.salesDocTypeforAltSite = salesDocTypeforAltSite;
		this.altSite = altSite;
		this.userId = userId;
		this.userName = userName;
		this.createDate = createDate;
		this.modifiedDate = modifiedDate;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.source = source;
		this.lastDownloadDate = lastDownloadDate;
		this.downloadFlag = downloadFlag;
	}

	public String getSoldToNumber() {
		return soldToNumber;
	}

	public String getSoldToDescription() {
		return soldToDescription;
	}

	public void setSoldToDescription(String soldToDescription) {
		this.soldToDescription = soldToDescription;
	}

	public String getShipToNumber() {
		return shipToNumber;
	}

	public void setShipToNumber(String shipToNumber) {
		this.shipToNumber = shipToNumber;
	}

	public String getShipToDescription() {
		return shipToDescription;
	}

	public void setShipToDescription(String shipToDescription) {
		this.shipToDescription = shipToDescription;
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

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public Boolean getExcludeFromSOCreation() {
		return excludeFromSOCreation;
	}

	public void setExcludeFromSOCreation(Boolean excludeFromSOCreation) {
		this.excludeFromSOCreation = excludeFromSOCreation;
	}

	public String getSalesOrderTypePrimary() {
		return salesOrderTypePrimary;
	}

	public void setSalesOrderTypePrimary(String salesOrderTypePrimary) {
		this.salesOrderTypePrimary = salesOrderTypePrimary;
	}

	public String getSalesOrderTypeSecondary() {
		return salesOrderTypeSecondary;
	}

	public void setSalesOrderTypeSecondary(String salesOrderTypeSecondary) {
		this.salesOrderTypeSecondary = salesOrderTypeSecondary;
	}

	public String getSalesOrderTypeTertiary() {
		return salesOrderTypeTertiary;
	}

	public void setSalesOrderTypeTertiary(String salesOrderTypeTertiary) {
		this.salesOrderTypeTertiary = salesOrderTypeTertiary;
	}

	public String getSegment() {
		return segment;
	}

	public void setSegment(String segment) {
		this.segment = segment;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public Integer getTarget() {
		return target;
	}

	public void setTarget(Integer target) {
		this.target = target;
	}

	public String getSuggestedRetailPriceCurrency() {
		return suggestedRetailPriceCurrency;
	}

	public void setSuggestedRetailPriceCurrency(String suggestedRetailPriceCurrency) {
		this.suggestedRetailPriceCurrency = suggestedRetailPriceCurrency;
	}

	public String getWholeSalePriceCurrency() {
		return wholeSalePriceCurrency;
	}

	public void setWholeSalePriceCurrency(String wholeSalePriceCurrency) {
		this.wholeSalePriceCurrency = wholeSalePriceCurrency;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getSendToJoor() {
		return sendToJoor;
	}

	public void setSendToJoor(Boolean sendToJoor) {
		this.sendToJoor = sendToJoor;
	}

	public Boolean getImpactedByPreBuy() {
		return impactedByPreBuy;
	}

	public void setImpactedByPreBuy(Boolean impactedByPreBuy) {
		this.impactedByPreBuy = impactedByPreBuy;
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

	public void setSoldToNumber(String soldToNumber) {
		this.soldToNumber = soldToNumber;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}
	
	public String getSuggestedRetailPriceCurrencyJoorPriceLabel() {
		return suggestedRetailPriceCurrencyJoorPriceLabel;
	}

	public void setSuggestedRetailPriceCurrencyJoorPriceLabel(String suggestedRetailPriceCurrencyJoorPriceLabel) {
		this.suggestedRetailPriceCurrencyJoorPriceLabel = suggestedRetailPriceCurrencyJoorPriceLabel;
	}

	public String getWholeSalePriceCurrencyJoorPriceLabel() {
		return wholeSalePriceCurrencyJoorPriceLabel;
	}

	public void setWholeSalePriceCurrencyJoorPriceLabel(String wholeSalePriceCurrencyJoorPriceLabel) {
		this.wholeSalePriceCurrencyJoorPriceLabel = wholeSalePriceCurrencyJoorPriceLabel;
	}
	
	public String getSalesDocTypeforAltSite() {
		return salesDocTypeforAltSite;
	}

	public void setSalesDocTypeforAltSite(String salesDocTypeforAltSite) {
		this.salesDocTypeforAltSite = salesDocTypeforAltSite;
	}

	public String getAltSite() {
		return altSite;
	}

	public void setAltSite(String altSite) {
		this.altSite = altSite;
	}

	@Override
	public String toString() {
		return "ConfigDto [soldToNumber=" + soldToNumber + ", soldToDescription=" + soldToDescription
				+ ", shipToNumber=" + shipToNumber + ", shipToDescription=" + shipToDescription + ", salesOrg="
				+ salesOrg + ", distributionChannel=" + distributionChannel + ", division=" + division + ", channel="
				+ channel + ", excludeFromSOCreation=" + excludeFromSOCreation + ", salesOrderTypePrimary="
				+ salesOrderTypePrimary + ", salesOrderTypeSecondary=" + salesOrderTypeSecondary
				+ ", salesOrderTypeTertiary=" + salesOrderTypeTertiary + ", segment=" + segment + ", site=" + site
				+ ", target=" + target + ", suggestedRetailPriceCurrency=" + suggestedRetailPriceCurrency
				+ ", wholeSalePriceCurrency=" + wholeSalePriceCurrency + ", isActive=" + isActive + ", sendToJoor="
				+ sendToJoor + ", impactedByPreBuy=" + impactedByPreBuy + ", index=" + index
				+ ", suggestedRetailPriceCurrencyJoorPriceLabel=" + suggestedRetailPriceCurrencyJoorPriceLabel
				+ ", wholeSalePriceCurrencyJoorPriceLabel=" + wholeSalePriceCurrencyJoorPriceLabel
				+ ", salesDocTypeforAltSite=" + salesDocTypeforAltSite + ", altSite=" + altSite + ", userId=" + userId
				+ ", userName=" + userName + ", createDate=" + createDate + ", modifiedDate=" + modifiedDate
				+ ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", source=" + source
				+ ", lastDownloadDate=" + lastDownloadDate + ", downloadFlag=" + downloadFlag + "]";
	}
}
