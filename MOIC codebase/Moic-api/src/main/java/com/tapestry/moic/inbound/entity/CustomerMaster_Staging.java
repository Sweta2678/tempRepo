package com.tapestry.moic.inbound.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.tapestry.moic.constant.MoicConstant;
import com.tapestry.moic.inbound.entity.key.CustomerMasterStagingKeys;

@Entity
@Table(name = MoicConstant.CUSTOMER_MASTER_STAGING)
@IdClass(CustomerMasterStagingKeys.class)
public class CustomerMaster_Staging implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = MoicConstant.SOLD_TO_NUMBER, length = 50)
	private String soldToNumber;
	
	@Id
	@Column(name = MoicConstant.SHIP_TO_NUMBER, length = 50)
	private String shipToNumber;
	
	@Id
	@Column(name = MoicConstant.SALES_ORG, length = 50)
	private String salesOrg;
	
	@Id
	@Column(name = MoicConstant.DISTRIBUTION_CHANNEL, length = 50)
	private String distributionChannel;
	
	@Id
	@Column(name = MoicConstant.DIVISON, length = 50)
	private String division;
	
	@Id
	@Column(name = MoicConstant.SITE, length = 80)
	private String site;

	@Column(name = MoicConstant.SOLD_TO_DESCRIPTION, length = 255)
	private String soldToDescription;

	@Column(name = MoicConstant.SHIP_TO_DESCRIPTION, length = 255)
	private String shipToDescription;

	@Column(name = MoicConstant.CHANNEL_ID, length = 50)
	private Integer channelID;

	@Column(name = MoicConstant.EXCLUDE_FROM_SOCREATION)
	private Boolean excludeFromSOCreation;

	@Column(name = MoicConstant.SALES_ORDER_TYPE_PRIMARY, length = 50)
	private String salesOrderTypePrimary;

	@Column(name = MoicConstant.SALES_ORDER_TYPE_SECONDARY, length = 50)
	private String salesOrderTypeSecondary;

	@Column(name = MoicConstant.SALES_ORDER_TYPE_TERTIARY, length = 50)
	private String salesOrderTypeTertiary;

	@Column(name = MoicConstant.IMPACTED_BY_PRE_BUY)
	private Boolean impactedByPreBuy;

	@Column(name = MoicConstant.SEGMENT_ID)
	private Integer segmentID;

	@Column(name = MoicConstant.TARGET, precision = 12, scale = 8)
	private Double target;

	@Column(name = MoicConstant.SUGGESTED_RETAIL_PRICE_CURRENCY, length = 50)
	private String suggestedRetailPriceCurrency;

	@Column(name = MoicConstant.WHOLESALE_PRICE_CURRENCY, length = 50)
	private String wholeSalePriceCurrency;

	@Column(name = MoicConstant.SO_HEADER_REQ_DEL_DATE)
	private Date soHeaderReqDelDate;

	@Column(name = MoicConstant.SO_CANCEL_DATE)
	private Date soCancelDate;

	@Column(name = MoicConstant.CONTRACT_VALID_FROM)
	private Date contractValidFrom;

	@Column(name = MoicConstant.CONTRACT_VALID_TO)
	private Date contractValidTo;

	@Column(name = MoicConstant.PO_NUMBER, length = 50)
	private String poNumber;

	@Column(name = MoicConstant.PO_TYPE, length = 50)
	private String poType;

	@Column(name = MoicConstant.ARTICLE, length = 50)
	private String article;

	@Column(name = MoicConstant.UPC_CODE, length = 50)
	private String upcCode;

	@Column(name = MoicConstant.QUANTITY)
	private Integer quantity;
	
	@Column(name = MoicConstant.BUYER_NAME, length = 100)
	private String buyerName;
	
	@Column(name = MoicConstant.BUYER_EMAIL, length = 100)
	private String buyerEmail;
	
	@Column(name = MoicConstant.BILLING_CODE, length = 50)
	private String billingCode;
	
	@Column(name = MoicConstant.BILLING_CITY, length = 50)
	private String billingCity;
	
	@Column(name = MoicConstant.BILLING_COUNTRY, length = 50)
	private String billingCountry;
	
	@Column(name = MoicConstant.SHIPPING_ADDRESS1, length = 100)
	private String shippingAddress1;
	
	@Column(name = MoicConstant.SHIPPING_ADDRESS2, length = 100)
	private String shippingAddress2;
	
	@Column(name = MoicConstant.SHIPPING_CITY, length = 50)
	private String shippingCity;

	@Column(name = MoicConstant.SHIPPING_STATE, length = 50)
	private String shippingState;
	
	@Column(name = MoicConstant.SHIPPING_POSTAL_CODE, length = 50)
	private String shippingPostalCode;
	
	@Column(name = MoicConstant.SHIPPING_COUNTRY, length = 50)
	private String shippingCountry;
	
	@Column(name = MoicConstant.IS_ACTIVE)
	private Boolean isActive;
	
	@Column(name = MoicConstant.DOWNLOAD_FLAG)
	private Boolean downloadFlag;
	

	/**
	 * 
	 */
	public CustomerMaster_Staging() {
		super();

	}


	/**
	 * @param soldToNumber
	 * @param shipToNumber
	 * @param salesOrg
	 * @param distributionChannel
	 * @param division
	 * @param site
	 * @param soldToDescription
	 * @param shipToDescription
	 * @param channelID
	 * @param excludeFromSOCreation
	 * @param salesOrderTypePrimary
	 * @param salesOrderTypeSecondary
	 * @param salesOrderTypeTertiary
	 * @param impactedByPreBuy
	 * @param segmentID
	 * @param target
	 * @param suggestedRetailPriceCurrency
	 * @param wholeSalePriceCurrency
	 * @param soHeaderReqDelDate
	 * @param soCancelDate
	 * @param contractValidFrom
	 * @param contractValidTo
	 * @param poNumber
	 * @param poType
	 * @param article
	 * @param upcCode
	 * @param quantity
	 * @param buyerName
	 * @param buyerEmail
	 * @param billingCode
	 * @param billingCity
	 * @param billingCountry
	 * @param shippingAddress1
	 * @param shippingAddress2
	 * @param shippingCity
	 * @param shippingState
	 * @param shippingPostalCode
	 * @param shippingCountry
	 * @param isActive
	 * @param downloadFlag
	 */
	public CustomerMaster_Staging(String soldToNumber, String shipToNumber, String salesOrg, String distributionChannel,
			String division, String site, String soldToDescription, String shipToDescription, Integer channelID,
			Boolean excludeFromSOCreation, String salesOrderTypePrimary, String salesOrderTypeSecondary,
			String salesOrderTypeTertiary, Boolean impactedByPreBuy, Integer segmentID, Double target,
			String suggestedRetailPriceCurrency, String wholeSalePriceCurrency, Date soHeaderReqDelDate,
			Date soCancelDate, Date contractValidFrom, Date contractValidTo, String poNumber, String poType,
			String article, String upcCode, Integer quantity, String buyerName, String buyerEmail, String billingCode,
			String billingCity, String billingCountry, String shippingAddress1, String shippingAddress2,
			String shippingCity, String shippingState, String shippingPostalCode, String shippingCountry,
			Boolean isActive, Boolean downloadFlag) {
		super();
		this.soldToNumber = soldToNumber;
		this.shipToNumber = shipToNumber;
		this.salesOrg = salesOrg;
		this.distributionChannel = distributionChannel;
		this.division = division;
		this.site = site;
		this.soldToDescription = soldToDescription;
		this.shipToDescription = shipToDescription;
		this.channelID = channelID;
		this.excludeFromSOCreation = excludeFromSOCreation;
		this.salesOrderTypePrimary = salesOrderTypePrimary;
		this.salesOrderTypeSecondary = salesOrderTypeSecondary;
		this.salesOrderTypeTertiary = salesOrderTypeTertiary;
		this.impactedByPreBuy = impactedByPreBuy;
		this.segmentID = segmentID;
		this.target = target;
		this.suggestedRetailPriceCurrency = suggestedRetailPriceCurrency;
		this.wholeSalePriceCurrency = wholeSalePriceCurrency;
		this.soHeaderReqDelDate = soHeaderReqDelDate;
		this.soCancelDate = soCancelDate;
		this.contractValidFrom = contractValidFrom;
		this.contractValidTo = contractValidTo;
		this.poNumber = poNumber;
		this.poType = poType;
		this.article = article;
		this.upcCode = upcCode;
		this.quantity = quantity;
		this.buyerName = buyerName;
		this.buyerEmail = buyerEmail;
		this.billingCode = billingCode;
		this.billingCity = billingCity;
		this.billingCountry = billingCountry;
		this.shippingAddress1 = shippingAddress1;
		this.shippingAddress2 = shippingAddress2;
		this.shippingCity = shippingCity;
		this.shippingState = shippingState;
		this.shippingPostalCode = shippingPostalCode;
		this.shippingCountry = shippingCountry;
		this.isActive = isActive;
		this.downloadFlag = downloadFlag;
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


	public String getSite() {
		return site;
	}


	public void setSite(String site) {
		this.site = site;
	}


	public String getSoldToDescription() {
		return soldToDescription;
	}


	public void setSoldToDescription(String soldToDescription) {
		this.soldToDescription = soldToDescription;
	}


	public String getShipToDescription() {
		return shipToDescription;
	}


	public void setShipToDescription(String shipToDescription) {
		this.shipToDescription = shipToDescription;
	}


	public Integer getChannelID() {
		return channelID;
	}


	public void setChannelID(Integer channelID) {
		this.channelID = channelID;
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


	public Boolean getImpactedByPreBuy() {
		return impactedByPreBuy;
	}


	public void setImpactedByPreBuy(Boolean impactedByPreBuy) {
		this.impactedByPreBuy = impactedByPreBuy;
	}


	public Integer getSegmentID() {
		return segmentID;
	}


	public void setSegmentID(Integer segmentID) {
		this.segmentID = segmentID;
	}


	public Double getTarget() {
		return target;
	}


	public void setTarget(Double target) {
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


	public Date getSoHeaderReqDelDate() {
		return soHeaderReqDelDate;
	}


	public void setSoHeaderReqDelDate(Date soHeaderReqDelDate) {
		this.soHeaderReqDelDate = soHeaderReqDelDate;
	}


	public Date getSoCancelDate() {
		return soCancelDate;
	}


	public void setSoCancelDate(Date soCancelDate) {
		this.soCancelDate = soCancelDate;
	}


	public Date getContractValidFrom() {
		return contractValidFrom;
	}


	public void setContractValidFrom(Date contractValidFrom) {
		this.contractValidFrom = contractValidFrom;
	}


	public Date getContractValidTo() {
		return contractValidTo;
	}


	public void setContractValidTo(Date contractValidTo) {
		this.contractValidTo = contractValidTo;
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


	public String getArticle() {
		return article;
	}


	public void setArticle(String article) {
		this.article = article;
	}


	public String getUpcCode() {
		return upcCode;
	}


	public void setUpcCode(String upcCode) {
		this.upcCode = upcCode;
	}


	public Integer getQuantity() {
		return quantity;
	}


	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	public String getBuyerName() {
		return buyerName;
	}


	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}


	public String getBuyerEmail() {
		return buyerEmail;
	}


	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}


	public String getBillingCode() {
		return billingCode;
	}


	public void setBillingCode(String billingCode) {
		this.billingCode = billingCode;
	}


	public String getBillingCity() {
		return billingCity;
	}


	public void setBillingCity(String billingCity) {
		this.billingCity = billingCity;
	}


	public String getBillingCountry() {
		return billingCountry;
	}


	public void setBillingCountry(String billingCountry) {
		this.billingCountry = billingCountry;
	}


	public String getShippingAddress1() {
		return shippingAddress1;
	}


	public void setShippingAddress1(String shippingAddress1) {
		this.shippingAddress1 = shippingAddress1;
	}


	public String getShippingAddress2() {
		return shippingAddress2;
	}


	public void setShippingAddress2(String shippingAddress2) {
		this.shippingAddress2 = shippingAddress2;
	}


	public String getShippingCity() {
		return shippingCity;
	}


	public void setShippingCity(String shippingCity) {
		this.shippingCity = shippingCity;
	}


	public String getShippingState() {
		return shippingState;
	}


	public void setShippingState(String shippingState) {
		this.shippingState = shippingState;
	}


	public String getShippingPostalCode() {
		return shippingPostalCode;
	}


	public void setShippingPostalCode(String shippingPostalCode) {
		this.shippingPostalCode = shippingPostalCode;
	}


	public String getShippingCountry() {
		return shippingCountry;
	}


	public void setShippingCountry(String shippingCountry) {
		this.shippingCountry = shippingCountry;
	}


	public Boolean getIsActive() {
		return isActive;
	}


	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}


	public Boolean getDownloadFlag() {
		return downloadFlag;
	}


	public void setDownloadFlag(Boolean downloadFlag) {
		this.downloadFlag = downloadFlag;
	}


	@Override
	public String toString() {
		return "CustomerMaster_Staging [soldToNumber=" + soldToNumber + ", shipToNumber=" + shipToNumber + ", salesOrg="
				+ salesOrg + ", distributionChannel=" + distributionChannel + ", division=" + division + ", site="
				+ site + ", soldToDescription=" + soldToDescription + ", shipToDescription=" + shipToDescription
				+ ", channelID=" + channelID + ", excludeFromSOCreation=" + excludeFromSOCreation
				+ ", salesOrderTypePrimary=" + salesOrderTypePrimary + ", salesOrderTypeSecondary="
				+ salesOrderTypeSecondary + ", salesOrderTypeTertiary=" + salesOrderTypeTertiary + ", impactedByPreBuy="
				+ impactedByPreBuy + ", segmentID=" + segmentID + ", target=" + target
				+ ", suggestedRetailPriceCurrency=" + suggestedRetailPriceCurrency + ", wholeSalePriceCurrency="
				+ wholeSalePriceCurrency + ", soHeaderReqDelDate=" + soHeaderReqDelDate + ", soCancelDate="
				+ soCancelDate + ", contractValidFrom=" + contractValidFrom + ", contractValidTo=" + contractValidTo
				+ ", poNumber=" + poNumber + ", poType=" + poType + ", article=" + article + ", upcCode=" + upcCode
				+ ", quantity=" + quantity + ", buyerName=" + buyerName + ", buyerEmail=" + buyerEmail
				+ ", billingCode=" + billingCode + ", billingCity=" + billingCity + ", billingCountry=" + billingCountry
				+ ", shippingAddress1=" + shippingAddress1 + ", shippingAddress2=" + shippingAddress2
				+ ", shippingCity=" + shippingCity + ", shippingState=" + shippingState + ", shippingPostalCode="
				+ shippingPostalCode + ", shippingCountry=" + shippingCountry + ", isActive=" + isActive
				+ ", downloadFlag=" + downloadFlag + "]";
	}			
}