package com.tapestry.moic.outbound.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JoorOrderOutboundDto {
	
	private Long orderId;
	private String orderComments;
	private String orderInternalComments;
	private String orderCurrency;
	private String orderCurrencyRetail;
	private String orderCurrencyName;
	private Double orderDiscount;
	private Double orderDiscountPercent;
	private Double orderShippingTotal;
	private Double orderTotal;
	private String orderSource;
	private String orderSeasonCode;
	private String orderType;
	private String orderTypeName;
	private String orderTypeCode;
	private String orderEvent;
	private String orderEventCode;
	private String orderDeliveryName;
	private String orderDeliveryCode;
	private Date dateCreated;
	private Date dateApproved;
	private Date dateModified;
	private Date dateShipStart;
	private Date dateShipEnd;
	private String salesRepresentative;
	private String salesRepresentativeCode;
	private String customerName;
	private String customerCode;
	private String companyName;
	private String companyCode;
	private String customerGroupName;
	private String customerGroupCode;
	private String customerPurchaseOrder;
	private String integrationPurchaseOrder;
	private String customerBuyerName;
	private String shippingCode;
	private String shippingName;
	private String shippingPhone;
	private String shippingEmail;
	private String shippingAddress1;
	private String shippingAddress2;
	private String shippingCity;
	private String shippingRegion;
	private String shippingPostalCode;
	private String shippingCountry;
	private String shippingCountryIsoThree;
	private String shippingCountryIsoTwo;
	private String shippingMethodName;
	private String shippingMethodCode;
	private String billingCode;
	private String billingName;
	private String billingPhone;
	private String billingAddress1;
	private String billingAddress2;
	private String billingCity;
	private String billingRegion;
	private String billingPostalCode;
	private String billingCountry;
	private String billingCountryIsoThree;
	private String billingCountryIsoTwo;
	private String paymentMethodName;
	private String paymentMethodCode;
	private String taxIdLabel;
	private String warehouseName;
	private String warehouseCode;
	private String createdByUsername;
	private String createdByEmail;
	private Boolean isPersonalOrder;
	private Double orderTotalUSD;
	
	private List<JoorOrderItemOutboundDto> orderOutboundItemDtos;
	private List<JoorOrderDivisionOutboundDto> divisionOutboundDtos;
	private List<JoorOrderDoorOutboundDto> orderDoorOutboundDtos;
	
	public JoorOrderOutboundDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JoorOrderOutboundDto(Long orderId, String orderComments, String orderInternalComments, String orderCurrency,
			String orderCurrencyRetail, String orderCurrencyName, Double orderDiscount, Double orderDiscountPercent,
			Double orderShippingTotal, Double orderTotal, String orderSource, String orderSeasonCode, String orderType,
			String orderTypeName, String orderTypeCode, String orderEvent, String orderEventCode,
			String orderDeliveryName, String orderDeliveryCode, Date dateCreated, Date dateApproved, Date dateModified,
			Date dateShipStart, Date dateShipEnd, String salesRepresentative, String salesRepresentativeCode,
			String customerName, String customerCode, String companyName, String companyCode, String customerGroupName,
			String customerGroupCode, String customerPurchaseOrder, String integrationPurchaseOrder,
			String customerBuyerName, String shippingCode, String shippingName, String shippingPhone,
			String shippingEmail, String shippingAddress1, String shippingAddress2, String shippingCity,
			String shippingRegion, String shippingPostalCode, String shippingCountry, String shippingCountryIsoThree,
			String shippingCountryIsoTwo, String shippingMethodName, String shippingMethodCode, String billingCode,
			String billingName, String billingPhone, String billingAddress1, String billingAddress2, String billingCity,
			String billingRegion, String billingPostalCode, String billingCountry, String billingCountryIsoThree,
			String billingCountryIsoTwo, String paymentMethodName, String paymentMethodCode, String taxIdLabel,
			String warehouseName, String warehouseCode, String createdByUsername, String createdByEmail,
			Boolean isPersonalOrder, Double orderTotalUSD, List<JoorOrderItemOutboundDto> orderOutboundItemDtos,
			List<JoorOrderDivisionOutboundDto> divisionOutboundDtos,
			List<JoorOrderDoorOutboundDto> orderDoorOutboundDtos) {
		super();
		this.orderId = orderId;
		this.orderComments = orderComments;
		this.orderInternalComments = orderInternalComments;
		this.orderCurrency = orderCurrency;
		this.orderCurrencyRetail = orderCurrencyRetail;
		this.orderCurrencyName = orderCurrencyName;
		this.orderDiscount = orderDiscount;
		this.orderDiscountPercent = orderDiscountPercent;
		this.orderShippingTotal = orderShippingTotal;
		this.orderTotal = orderTotal;
		this.orderSource = orderSource;
		this.orderSeasonCode = orderSeasonCode;
		this.orderType = orderType;
		this.orderTypeName = orderTypeName;
		this.orderTypeCode = orderTypeCode;
		this.orderEvent = orderEvent;
		this.orderEventCode = orderEventCode;
		this.orderDeliveryName = orderDeliveryName;
		this.orderDeliveryCode = orderDeliveryCode;
		this.dateCreated = dateCreated;
		this.dateApproved = dateApproved;
		this.dateModified = dateModified;
		this.dateShipStart = dateShipStart;
		this.dateShipEnd = dateShipEnd;
		this.salesRepresentative = salesRepresentative;
		this.salesRepresentativeCode = salesRepresentativeCode;
		this.customerName = customerName;
		this.customerCode = customerCode;
		this.companyName = companyName;
		this.companyCode = companyCode;
		this.customerGroupName = customerGroupName;
		this.customerGroupCode = customerGroupCode;
		this.customerPurchaseOrder = customerPurchaseOrder;
		this.integrationPurchaseOrder = integrationPurchaseOrder;
		this.customerBuyerName = customerBuyerName;
		this.shippingCode = shippingCode;
		this.shippingName = shippingName;
		this.shippingPhone = shippingPhone;
		this.shippingEmail = shippingEmail;
		this.shippingAddress1 = shippingAddress1;
		this.shippingAddress2 = shippingAddress2;
		this.shippingCity = shippingCity;
		this.shippingRegion = shippingRegion;
		this.shippingPostalCode = shippingPostalCode;
		this.shippingCountry = shippingCountry;
		this.shippingCountryIsoThree = shippingCountryIsoThree;
		this.shippingCountryIsoTwo = shippingCountryIsoTwo;
		this.shippingMethodName = shippingMethodName;
		this.shippingMethodCode = shippingMethodCode;
		this.billingCode = billingCode;
		this.billingName = billingName;
		this.billingPhone = billingPhone;
		this.billingAddress1 = billingAddress1;
		this.billingAddress2 = billingAddress2;
		this.billingCity = billingCity;
		this.billingRegion = billingRegion;
		this.billingPostalCode = billingPostalCode;
		this.billingCountry = billingCountry;
		this.billingCountryIsoThree = billingCountryIsoThree;
		this.billingCountryIsoTwo = billingCountryIsoTwo;
		this.paymentMethodName = paymentMethodName;
		this.paymentMethodCode = paymentMethodCode;
		this.taxIdLabel = taxIdLabel;
		this.warehouseName = warehouseName;
		this.warehouseCode = warehouseCode;
		this.createdByUsername = createdByUsername;
		this.createdByEmail = createdByEmail;
		this.isPersonalOrder = isPersonalOrder;
		this.orderTotalUSD = orderTotalUSD;
		this.orderOutboundItemDtos = orderOutboundItemDtos;
		this.divisionOutboundDtos = divisionOutboundDtos;
		this.orderDoorOutboundDtos = orderDoorOutboundDtos;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getOrderComments() {
		return orderComments;
	}

	public void setOrderComments(String orderComments) {
		this.orderComments = orderComments;
	}

	public String getOrderInternalComments() {
		return orderInternalComments;
	}

	public void setOrderInternalComments(String orderInternalComments) {
		this.orderInternalComments = orderInternalComments;
	}

	public String getOrderCurrency() {
		return orderCurrency;
	}

	public void setOrderCurrency(String orderCurrency) {
		this.orderCurrency = orderCurrency;
	}

	public String getOrderCurrencyRetail() {
		return orderCurrencyRetail;
	}

	public void setOrderCurrencyRetail(String orderCurrencyRetail) {
		this.orderCurrencyRetail = orderCurrencyRetail;
	}

	public String getOrderCurrencyName() {
		return orderCurrencyName;
	}

	public void setOrderCurrencyName(String orderCurrencyName) {
		this.orderCurrencyName = orderCurrencyName;
	}

	public Double getOrderDiscount() {
		return orderDiscount;
	}

	public void setOrderDiscount(Double orderDiscount) {
		this.orderDiscount = orderDiscount;
	}

	public Double getOrderDiscountPercent() {
		return orderDiscountPercent;
	}

	public void setOrderDiscountPercent(Double orderDiscountPercent) {
		this.orderDiscountPercent = orderDiscountPercent;
	}

	public Double getOrderShippingTotal() {
		return orderShippingTotal;
	}

	public void setOrderShippingTotal(Double orderShippingTotal) {
		this.orderShippingTotal = orderShippingTotal;
	}

	public Double getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(Double orderTotal) {
		this.orderTotal = orderTotal;
	}

	public String getOrderSource() {
		return orderSource;
	}

	public void setOrderSource(String orderSource) {
		this.orderSource = orderSource;
	}

	public String getOrderSeasonCode() {
		return orderSeasonCode;
	}

	public void setOrderSeasonCode(String orderSeasonCode) {
		this.orderSeasonCode = orderSeasonCode;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getOrderTypeName() {
		return orderTypeName;
	}

	public void setOrderTypeName(String orderTypeName) {
		this.orderTypeName = orderTypeName;
	}

	public String getOrderTypeCode() {
		return orderTypeCode;
	}

	public void setOrderTypeCode(String orderTypeCode) {
		this.orderTypeCode = orderTypeCode;
	}

	public String getOrderEvent() {
		return orderEvent;
	}

	public void setOrderEvent(String orderEvent) {
		this.orderEvent = orderEvent;
	}

	public String getOrderEventCode() {
		return orderEventCode;
	}

	public void setOrderEventCode(String orderEventCode) {
		this.orderEventCode = orderEventCode;
	}

	public String getOrderDeliveryName() {
		return orderDeliveryName;
	}

	public void setOrderDeliveryName(String orderDeliveryName) {
		this.orderDeliveryName = orderDeliveryName;
	}

	public String getOrderDeliveryCode() {
		return orderDeliveryCode;
	}

	public void setOrderDeliveryCode(String orderDeliveryCode) {
		this.orderDeliveryCode = orderDeliveryCode;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateApproved() {
		return dateApproved;
	}

	public void setDateApproved(Date dateApproved) {
		this.dateApproved = dateApproved;
	}

	public Date getDateModified() {
		return dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	public Date getDateShipStart() {
		return dateShipStart;
	}

	public void setDateShipStart(Date dateShipStart) {
		this.dateShipStart = dateShipStart;
	}

	public Date getDateShipEnd() {
		return dateShipEnd;
	}

	public void setDateShipEnd(Date dateShipEnd) {
		this.dateShipEnd = dateShipEnd;
	}

	public String getSalesRepresentative() {
		return salesRepresentative;
	}

	public void setSalesRepresentative(String salesRepresentative) {
		this.salesRepresentative = salesRepresentative;
	}

	public String getSalesRepresentativeCode() {
		return salesRepresentativeCode;
	}

	public void setSalesRepresentativeCode(String salesRepresentativeCode) {
		this.salesRepresentativeCode = salesRepresentativeCode;
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
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

	public String getCustomerPurchaseOrder() {
		return customerPurchaseOrder;
	}

	public void setCustomerPurchaseOrder(String customerPurchaseOrder) {
		this.customerPurchaseOrder = customerPurchaseOrder;
	}

	public String getIntegrationPurchaseOrder() {
		return integrationPurchaseOrder;
	}

	public void setIntegrationPurchaseOrder(String integrationPurchaseOrder) {
		this.integrationPurchaseOrder = integrationPurchaseOrder;
	}

	public String getCustomerBuyerName() {
		return customerBuyerName;
	}

	public void setCustomerBuyerName(String customerBuyerName) {
		this.customerBuyerName = customerBuyerName;
	}

	public String getShippingCode() {
		return shippingCode;
	}

	public void setShippingCode(String shippingCode) {
		this.shippingCode = shippingCode;
	}

	public String getShippingName() {
		return shippingName;
	}

	public void setShippingName(String shippingName) {
		this.shippingName = shippingName;
	}

	public String getShippingPhone() {
		return shippingPhone;
	}

	public void setShippingPhone(String shippingPhone) {
		this.shippingPhone = shippingPhone;
	}

	public String getShippingEmail() {
		return shippingEmail;
	}

	public void setShippingEmail(String shippingEmail) {
		this.shippingEmail = shippingEmail;
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

	public String getShippingRegion() {
		return shippingRegion;
	}

	public void setShippingRegion(String shippingRegion) {
		this.shippingRegion = shippingRegion;
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

	public String getShippingCountryIsoThree() {
		return shippingCountryIsoThree;
	}

	public void setShippingCountryIsoThree(String shippingCountryIsoThree) {
		this.shippingCountryIsoThree = shippingCountryIsoThree;
	}

	public String getShippingCountryIsoTwo() {
		return shippingCountryIsoTwo;
	}

	public void setShippingCountryIsoTwo(String shippingCountryIsoTwo) {
		this.shippingCountryIsoTwo = shippingCountryIsoTwo;
	}

	public String getShippingMethodName() {
		return shippingMethodName;
	}

	public void setShippingMethodName(String shippingMethodName) {
		this.shippingMethodName = shippingMethodName;
	}

	public String getShippingMethodCode() {
		return shippingMethodCode;
	}

	public void setShippingMethodCode(String shippingMethodCode) {
		this.shippingMethodCode = shippingMethodCode;
	}

	public String getBillingCode() {
		return billingCode;
	}

	public void setBillingCode(String billingCode) {
		this.billingCode = billingCode;
	}

	public String getBillingName() {
		return billingName;
	}

	public void setBillingName(String billingName) {
		this.billingName = billingName;
	}

	public String getBillingPhone() {
		return billingPhone;
	}

	public void setBillingPhone(String billingPhone) {
		this.billingPhone = billingPhone;
	}

	public String getBillingAddress1() {
		return billingAddress1;
	}

	public void setBillingAddress1(String billingAddress1) {
		this.billingAddress1 = billingAddress1;
	}

	public String getBillingAddress2() {
		return billingAddress2;
	}

	public void setBillingAddress2(String billingAddress2) {
		this.billingAddress2 = billingAddress2;
	}

	public String getBillingCity() {
		return billingCity;
	}

	public void setBillingCity(String billingCity) {
		this.billingCity = billingCity;
	}

	public String getBillingRegion() {
		return billingRegion;
	}

	public void setBillingRegion(String billingRegion) {
		this.billingRegion = billingRegion;
	}

	public String getBillingPostalCode() {
		return billingPostalCode;
	}

	public void setBillingPostalCode(String billingPostalCode) {
		this.billingPostalCode = billingPostalCode;
	}

	public String getBillingCountry() {
		return billingCountry;
	}

	public void setBillingCountry(String billingCountry) {
		this.billingCountry = billingCountry;
	}

	public String getBillingCountryIsoThree() {
		return billingCountryIsoThree;
	}

	public void setBillingCountryIsoThree(String billingCountryIsoThree) {
		this.billingCountryIsoThree = billingCountryIsoThree;
	}

	public String getBillingCountryIsoTwo() {
		return billingCountryIsoTwo;
	}

	public void setBillingCountryIsoTwo(String billingCountryIsoTwo) {
		this.billingCountryIsoTwo = billingCountryIsoTwo;
	}

	public String getPaymentMethodName() {
		return paymentMethodName;
	}

	public void setPaymentMethodName(String paymentMethodName) {
		this.paymentMethodName = paymentMethodName;
	}

	public String getPaymentMethodCode() {
		return paymentMethodCode;
	}

	public void setPaymentMethodCode(String paymentMethodCode) {
		this.paymentMethodCode = paymentMethodCode;
	}

	public String getTaxIdLabel() {
		return taxIdLabel;
	}

	public void setTaxIdLabel(String taxIdLabel) {
		this.taxIdLabel = taxIdLabel;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public String getWarehouseCode() {
		return warehouseCode;
	}

	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}

	public String getCreatedByUsername() {
		return createdByUsername;
	}

	public void setCreatedByUsername(String createdByUsername) {
		this.createdByUsername = createdByUsername;
	}

	public String getCreatedByEmail() {
		return createdByEmail;
	}

	public void setCreatedByEmail(String createdByEmail) {
		this.createdByEmail = createdByEmail;
	}

	public Boolean getIsPersonalOrder() {
		return isPersonalOrder;
	}

	public void setIsPersonalOrder(Boolean isPersonalOrder) {
		this.isPersonalOrder = isPersonalOrder;
	}

	public Double getOrderTotalUSD() {
		return orderTotalUSD;
	}

	public void setOrderTotalUSD(Double orderTotalUSD) {
		this.orderTotalUSD = orderTotalUSD;
	}

	public List<JoorOrderItemOutboundDto> getOrderOutboundItemDtos() {
		return orderOutboundItemDtos;
	}

	public void setOrderOutboundItemDtos(List<JoorOrderItemOutboundDto> orderOutboundItemDtos) {
		this.orderOutboundItemDtos = orderOutboundItemDtos;
	}

	public List<JoorOrderDivisionOutboundDto> getDivisionOutboundDtos() {
		return divisionOutboundDtos;
	}

	public void setDivisionOutboundDtos(List<JoorOrderDivisionOutboundDto> divisionOutboundDtos) {
		this.divisionOutboundDtos = divisionOutboundDtos;
	}

	public List<JoorOrderDoorOutboundDto> getOrderDoorOutboundDtos() {
		return orderDoorOutboundDtos;
	}

	public void setOrderDoorOutboundDtos(List<JoorOrderDoorOutboundDto> orderDoorOutboundDtos) {
		this.orderDoorOutboundDtos = orderDoorOutboundDtos;
	}

	@Override
	public String toString() {
		return "JoorOrderOutboundDto [orderId=" + orderId + ", orderComments=" + orderComments
				+ ", orderInternalComments=" + orderInternalComments + ", orderCurrency=" + orderCurrency
				+ ", orderCurrencyRetail=" + orderCurrencyRetail + ", orderCurrencyName=" + orderCurrencyName
				+ ", orderDiscount=" + orderDiscount + ", orderDiscountPercent=" + orderDiscountPercent
				+ ", orderShippingTotal=" + orderShippingTotal + ", orderTotal=" + orderTotal + ", orderSource="
				+ orderSource + ", orderSeasonCode=" + orderSeasonCode + ", orderType=" + orderType + ", orderTypeName="
				+ orderTypeName + ", orderTypeCode=" + orderTypeCode + ", orderEvent=" + orderEvent
				+ ", orderEventCode=" + orderEventCode + ", orderDeliveryName=" + orderDeliveryName
				+ ", orderDeliveryCode=" + orderDeliveryCode + ", dateCreated=" + dateCreated + ", dateApproved="
				+ dateApproved + ", dateModified=" + dateModified + ", dateShipStart=" + dateShipStart
				+ ", dateShipEnd=" + dateShipEnd + ", salesRepresentative=" + salesRepresentative
				+ ", salesRepresentativeCode=" + salesRepresentativeCode + ", customerName=" + customerName
				+ ", customerCode=" + customerCode + ", companyName=" + companyName + ", companyCode=" + companyCode
				+ ", customerGroupName=" + customerGroupName + ", customerGroupCode=" + customerGroupCode
				+ ", customerPurchaseOrder=" + customerPurchaseOrder + ", integrationPurchaseOrder="
				+ integrationPurchaseOrder + ", customerBuyerName=" + customerBuyerName + ", shippingCode="
				+ shippingCode + ", shippingName=" + shippingName + ", shippingPhone=" + shippingPhone
				+ ", shippingEmail=" + shippingEmail + ", shippingAddress1=" + shippingAddress1 + ", shippingAddress2="
				+ shippingAddress2 + ", shippingCity=" + shippingCity + ", shippingRegion=" + shippingRegion
				+ ", shippingPostalCode=" + shippingPostalCode + ", shippingCountry=" + shippingCountry
				+ ", shippingCountryIsoThree=" + shippingCountryIsoThree + ", shippingCountryIsoTwo="
				+ shippingCountryIsoTwo + ", shippingMethodName=" + shippingMethodName + ", shippingMethodCode="
				+ shippingMethodCode + ", billingCode=" + billingCode + ", billingName=" + billingName
				+ ", billingPhone=" + billingPhone + ", billingAddress1=" + billingAddress1 + ", billingAddress2="
				+ billingAddress2 + ", billingCity=" + billingCity + ", billingRegion=" + billingRegion
				+ ", billingPostalCode=" + billingPostalCode + ", billingCountry=" + billingCountry
				+ ", billingCountryIsoThree=" + billingCountryIsoThree + ", billingCountryIsoTwo="
				+ billingCountryIsoTwo + ", paymentMethodName=" + paymentMethodName + ", paymentMethodCode="
				+ paymentMethodCode + ", taxIdLabel=" + taxIdLabel + ", warehouseName=" + warehouseName
				+ ", warehouseCode=" + warehouseCode + ", createdByUsername=" + createdByUsername + ", createdByEmail="
				+ createdByEmail + ", isPersonalOrder=" + isPersonalOrder + ", orderTotalUSD=" + orderTotalUSD
				+ ", orderOutboundItemDtos=" + orderOutboundItemDtos + ", divisionOutboundDtos=" + divisionOutboundDtos
				+ ", orderDoorOutboundDtos=" + orderDoorOutboundDtos + "]";
	}

	
}
