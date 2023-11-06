package com.tapestry.moic.dto;

import java.util.Date;

/**
 * The Class CustomerMasterDto.
 *
 * @version 0.0.1
 */
public class CustomerMasterDto {

	private String soldToNumber;
	private String soldToDescription;
	private String shipToNumber;
	private String shipToDescription;
	private String salesOrg;
	private String distributionChannel;
	private String division;
	private int channelID;
	private Boolean excludeFromSOCreation;
	private String salesOrderTypePrimary;
	private String salesOrderTypeSecondary;
	private String salesOrderTypeTertiary;
	private Boolean impactedByPreBuy;
	private int segmentID;
	private String site;
	private double target;	
	private String suggestedRetailPriceCurrency;
	private String wholeSalePriceCurrency;
	private Date soHeaderReqDelDate;
	private Date soCancelDate;
	private Date contractValidFrom;
	private Date contractValidTo;
	private String poNumber;
	private String poType;
	private String article;
	private String upcCode;
	private int quantity;
	private String customerName;
	private String customerCode;
	private String customerAlias;
	private String companyNumberName;
	private String companyNumberCode;
	private String customerGroupName;
	private String customerGroupCode;
	private String customerPhone;
	private String customerContact;
	private String customerContactEmail;
	private String salesRepCode;
	private String shippingName;
	private String shippingCode;
	private String shippingPhone;
	private String shippingAddress1;
	private String shippingAddress2;
	private String shippingCity;
	private String shippingState;
	private String shippingPostalCode;
	private String shippingCountry;
	private String shippingCreateDoors;
	private String locationPhone;
	private String locationAddress1;
	private String locationAddress2;
	private String locationCity;
	private String locationState;
	private String locationPostalCode;
	private String locationCountry;
	private String priceTypeCurrencyCode;
	private String priceTypeLabel;
	private String priceTypeRetailCurrencyCode;
	private int amount;
	private String lineItemLevel;
	private String warehouseCode;
	private String warehouseName;

	public CustomerMasterDto() {
	}

	/**
	 * @param soldToNumber
	 * @param soldToDescription
	 * @param shipToNumber
	 * @param shipToDescription
	 * @param salesOrg
	 * @param distributionChannel
	 * @param division
	 * @param channelID
	 * @param excludeFromSOCreation
	 * @param salesOrderTypePrimary
	 * @param salesOrderTypeSecondary
	 * @param salesOrderTypeTertiary
	 * @param impactedByPreBuy
	 * @param segmentID
	 * @param site
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
	 * @param customerName
	 * @param customerCode
	 * @param customerAlias
	 * @param companyNumberName
	 * @param companyNumberCode
	 * @param customerGroupName
	 * @param customerGroupCode
	 * @param customerPhone
	 * @param customerContact
	 * @param customerContactEmail
	 * @param salesRepCode
	 * @param shippingName
	 * @param shippingCode
	 * @param shippingPhone
	 * @param shippingAddress1
	 * @param shippingAddress2
	 * @param shippingCity
	 * @param shippingState
	 * @param shippingPostalCode
	 * @param shippingCountry
	 * @param shippingCreateDoors
	 * @param locationPhone
	 * @param locationAddress1
	 * @param locationAddress2
	 * @param locationCity
	 * @param locationState
	 * @param locationPostalCode
	 * @param locationCountry
	 * @param priceTypeCurrencyCode
	 * @param priceTypeLabel
	 * @param priceTypeRetailCurrencyCode
	 * @param amount
	 * @param lineItemLevel
	 * @param warehouseCode
	 * @param warehouseName
	 */
	public CustomerMasterDto(String soldToNumber, String soldToDescription, String shipToNumber,
			String shipToDescription, String salesOrg, String distributionChannel, String division, int channelID,
			Boolean excludeFromSOCreation, String salesOrderTypePrimary, String salesOrderTypeSecondary,
			String salesOrderTypeTertiary, Boolean impactedByPreBuy, int segmentID, String site, int target,
			String suggestedRetailPriceCurrency, String wholeSalePriceCurrency, Date soHeaderReqDelDate, Date soCancelDate,
			Date contractValidFrom, Date contractValidTo, String poNumber, String poType, String article,
			String upcCode, int quantity, String customerName, String customerCode, String customerAlias,
			String companyNumberName, String companyNumberCode, String customerGroupName, String customerGroupCode,
			String customerPhone, String customerContact, String customerContactEmail, String salesRepCode,
			String shippingName, String shippingCode, String shippingPhone, String shippingAddress1,
			String shippingAddress2, String shippingCity, String shippingState, String shippingPostalCode,
			String shippingCountry, String shippingCreateDoors, String locationPhone, String locationAddress1,
			String locationAddress2, String locationCity, String locationState, String locationPostalCode,
			String locationCountry, String priceTypeCurrencyCode, String priceTypeLabel,
			String priceTypeRetailCurrencyCode, int amount, String lineItemLevel, String warehouseCode,
			String warehouseName) {
		super();
		this.soldToNumber = soldToNumber;
		this.soldToDescription = soldToDescription;
		this.shipToNumber = shipToNumber;
		this.shipToDescription = shipToDescription;
		this.salesOrg = salesOrg;
		this.distributionChannel = distributionChannel;
		this.division = division;
		this.channelID = channelID;
		this.excludeFromSOCreation = excludeFromSOCreation;
		this.salesOrderTypePrimary = salesOrderTypePrimary;
		this.salesOrderTypeSecondary = salesOrderTypeSecondary;
		this.salesOrderTypeTertiary = salesOrderTypeTertiary;
		this.impactedByPreBuy = impactedByPreBuy;
		this.segmentID = segmentID;
		this.site = site;
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
		this.customerName = customerName;
		this.customerCode = customerCode;
		this.customerAlias = customerAlias;
		this.companyNumberName = companyNumberName;
		this.companyNumberCode = companyNumberCode;
		this.customerGroupName = customerGroupName;
		this.customerGroupCode = customerGroupCode;
		this.customerPhone = customerPhone;
		this.customerContact = customerContact;
		this.customerContactEmail = customerContactEmail;
		this.salesRepCode = salesRepCode;
		this.shippingName = shippingName;
		this.shippingCode = shippingCode;
		this.shippingPhone = shippingPhone;
		this.shippingAddress1 = shippingAddress1;
		this.shippingAddress2 = shippingAddress2;
		this.shippingCity = shippingCity;
		this.shippingState = shippingState;
		this.shippingPostalCode = shippingPostalCode;
		this.shippingCountry = shippingCountry;
		this.shippingCreateDoors = shippingCreateDoors;
		this.locationPhone = locationPhone;
		this.locationAddress1 = locationAddress1;
		this.locationAddress2 = locationAddress2;
		this.locationCity = locationCity;
		this.locationState = locationState;
		this.locationPostalCode = locationPostalCode;
		this.locationCountry = locationCountry;
		this.priceTypeCurrencyCode = priceTypeCurrencyCode;
		this.priceTypeLabel = priceTypeLabel;
		this.priceTypeRetailCurrencyCode = priceTypeRetailCurrencyCode;
		this.amount = amount;
		this.lineItemLevel = lineItemLevel;
		this.warehouseCode = warehouseCode;
		this.warehouseName = warehouseName;
	}

	public String getSoldToNumber() {
		return soldToNumber;
	}
	public void setSoldToNumber(String soldToNumber) {
		this.soldToNumber = soldToNumber;
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
	public int getChannelID() {
		return channelID;
	}
	public void setChannelID(int channelID) {
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
	public int getSegmentID() {
		return segmentID;
	}
	public void setSegmentID(int segmentID) {
		this.segmentID = segmentID;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public double getTarget() {
		return target;
	}

	public void setTarget(double target) {
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
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getCustomerAlias() {
		return customerAlias;
	}
	public void setCustomerAlias(String customerAlias) {
		this.customerAlias = customerAlias;
	}
	public String getCompanyNumberName() {
		return companyNumberName;
	}
	public void setCompanyNumberName(String companyNumberName) {
		this.companyNumberName = companyNumberName;
	}
	public String getCompanyNumberCode() {
		return companyNumberCode;
	}
	public void setCompanyNumberCode(String companyNumberCode) {
		this.companyNumberCode = companyNumberCode;
	}
	public String getCustomerGroupName() {
		return customerGroupName;
	}
	public void setCustomerGroupName(String customerGroupName) {
		this.customerGroupName = customerGroupName;
	}
	public String getCustomerGroupCode() {
		return customerGroupCode;
	}
	public void setCustomerGroupCode(String customerGroupCode) {
		this.customerGroupCode = customerGroupCode;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public String getCustomerContact() {
		return customerContact;
	}
	public void setCustomerContact(String customerContact) {
		this.customerContact = customerContact;
	}
	public String getCustomerContactEmail() {
		return customerContactEmail;
	}
	public void setCustomerContactEmail(String customerContactEmail) {
		this.customerContactEmail = customerContactEmail;
	}
	public String getSalesRepCode() {
		return salesRepCode;
	}
	public void setSalesRepCode(String salesRepCode) {
		this.salesRepCode = salesRepCode;
	}
	public String getShippingName() {
		return shippingName;
	}
	public void setShippingName(String shippingName) {
		this.shippingName = shippingName;
	}
	public String getShippingCode() {
		return shippingCode;
	}
	public void setShippingCode(String shippingCode) {
		this.shippingCode = shippingCode;
	}
	public String getShippingPhone() {
		return shippingPhone;
	}
	public void setShippingPhone(String shippingPhone) {
		this.shippingPhone = shippingPhone;
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
	public String getShippingCreateDoors() {
		return shippingCreateDoors;
	}
	public void setShippingCreateDoors(String shippingCreateDoors) {
		this.shippingCreateDoors = shippingCreateDoors;
	}
	public String getLocationPhone() {
		return locationPhone;
	}
	public void setLocationPhone(String locationPhone) {
		this.locationPhone = locationPhone;
	}
	public String getLocationAddress1() {
		return locationAddress1;
	}
	public void setLocationAddress1(String locationAddress1) {
		this.locationAddress1 = locationAddress1;
	}
	public String getLocationAddress2() {
		return locationAddress2;
	}
	public void setLocationAddress2(String locationAddress2) {
		this.locationAddress2 = locationAddress2;
	}
	public String getLocationCity() {
		return locationCity;
	}
	public void setLocationCity(String locationCity) {
		this.locationCity = locationCity;
	}
	public String getLocationState() {
		return locationState;
	}
	public void setLocationState(String locationState) {
		this.locationState = locationState;
	}
	public String getLocationPostalCode() {
		return locationPostalCode;
	}
	public void setLocationPostalCode(String locationPostalCode) {
		this.locationPostalCode = locationPostalCode;
	}
	public String getLocationCountry() {
		return locationCountry;
	}
	public void setLocationCountry(String locationCountry) {
		this.locationCountry = locationCountry;
	}
	public String getPriceTypeCurrencyCode() {
		return priceTypeCurrencyCode;
	}
	public void setPriceTypeCurrencyCode(String priceTypeCurrencyCode) {
		this.priceTypeCurrencyCode = priceTypeCurrencyCode;
	}
	public String getPriceTypeLabel() {
		return priceTypeLabel;
	}
	public void setPriceTypeLabel(String priceTypeLabel) {
		this.priceTypeLabel = priceTypeLabel;
	}
	public String getPriceTypeRetailCurrencyCode() {
		return priceTypeRetailCurrencyCode;
	}
	public void setPriceTypeRetailCurrencyCode(String priceTypeRetailCurrencyCode) {
		this.priceTypeRetailCurrencyCode = priceTypeRetailCurrencyCode;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getLineItemLevel() {
		return lineItemLevel;
	}
	public void setLineItemLevel(String lineItemLevel) {
		this.lineItemLevel = lineItemLevel;
	}
	public String getWarehouseCode() {
		return warehouseCode;
	}
	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	@Override
	public String toString() {
		return "AccountMaster [soldToNumber=" + soldToNumber + ", soldToDescription=" + soldToDescription
				+ ", shipToNumber=" + shipToNumber + ", shipToDescription=" + shipToDescription + ", salesOrg="
				+ salesOrg + ", distributionChannel=" + distributionChannel + ", division=" + division + ", channelID="
				+ channelID + ", excludeFromSOCreation=" + excludeFromSOCreation + ", salesOrderTypePrimary="
				+ salesOrderTypePrimary + ", salesOrderTypeSecondary=" + salesOrderTypeSecondary
				+ ", salesOrderTypeTertiary=" + salesOrderTypeTertiary + ", impactedByPreBuy=" + impactedByPreBuy
				+ ", segmentID=" + segmentID + ", site=" + site + ", target=" + target
				+ ", suggestedRetailPriceCurrency=" + suggestedRetailPriceCurrency + ", wholeSalePriceCurrency="
				+ wholeSalePriceCurrency + ", soHeaderReqDelDate=" + soHeaderReqDelDate + ", soCancelDate="
				+ soCancelDate + ", contractValidFrom=" + contractValidFrom + ", contractValidTo=" + contractValidTo
				+ ", poNumber=" + poNumber + ", poType=" + poType + ", article=" + article + ", upcCode=" + upcCode
				+ ", quantity=" + quantity + ", customerName=" + customerName + ", customerCode=" + customerCode
				+ ", customerAlias=" + customerAlias + ", companyNumberName=" + companyNumberName
				+ ", companyNumberCode=" + companyNumberCode + ", customerGroupName=" + customerGroupName
				+ ", customerGroupCode=" + customerGroupCode + ", customerPhone=" + customerPhone + ", customerContact="
				+ customerContact + ", customerContactEmail=" + customerContactEmail + ", salesRepCode=" + salesRepCode
				+ ", shippingName=" + shippingName + ", shippingCode=" + shippingCode + ", shippingPhone="
				+ shippingPhone + ", shippingAddress1=" + shippingAddress1 + ", shippingAddress2=" + shippingAddress2
				+ ", shippingCity=" + shippingCity + ", shippingState=" + shippingState + ", shippingPostalCode="
				+ shippingPostalCode + ", shippingCountry=" + shippingCountry + ", shippingCreateDoors="
				+ shippingCreateDoors + ", locationPhone=" + locationPhone + ", locationAddress1=" + locationAddress1
				+ ", locationAddress2=" + locationAddress2 + ", locationCity=" + locationCity + ", locationState="
				+ locationState + ", locationPostalCode=" + locationPostalCode + ", locationCountry=" + locationCountry
				+ ", priceTypeCurrencyCode=" + priceTypeCurrencyCode + ", priceTypeLabel=" + priceTypeLabel
				+ ", priceTypeRetailCurrencyCode=" + priceTypeRetailCurrencyCode + ", amount=" + amount
				+ ", lineItemLevel=" + lineItemLevel + ", warehouseCode=" + warehouseCode + ", warehouseName="
				+ warehouseName + "]";
	}
}
