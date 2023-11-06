package com.tapestry.moic.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.tapestry.moic.constant.MoicConstant;

/**
 * The Class JoorOrder.
 *
 * @version 0.0.1
 */
@Entity
@Table(name = MoicConstant.JOOR_ORDER)
public class JoorOrder implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = MoicConstant.ORDER_ID)
	private Long orderId;

	@Column(name = MoicConstant.ORDER_COMMENTS)
	private String orderComments;

	@Column(name = MoicConstant.ORDER_INTERNAL_COMMENTS)
	private String orderInternalComments;

	@Column(name = MoicConstant.ORDER_CURRENCY)
	private String orderCurrency;

	@Column(name = MoicConstant.ORDER_CURRENCY_RETAIL)
	private String orderCurrencyRetail;

	@Column(name = MoicConstant.ORDER_CURRENCY_NAME)
	private String orderCurrencyName;

	@Column(name = MoicConstant.ORDER_DISCOUNT)
	private Double orderDiscount;

	@Column(name = MoicConstant.ORDER_DISCOUNT_PERCENT)
	private Double orderDiscountPercent;

	@Column(name = MoicConstant.ORDER_SHIPPING_TOTAL)
	private Double orderShippingTotal;

	@Column(name = MoicConstant.ORDER_TOTAL)
	private Double orderTotal;

	@Column(name = MoicConstant.ORDER_SOURCE)
	private String orderSource;

	@Column(name = MoicConstant.ORDER_SEASON_CODE)
	private String orderSeasonCode;

	@Column(name = MoicConstant.ORDER_TYPE)
	private String orderType;

	@Column(name = MoicConstant.ORDER_TYPE_NAME)
	private String orderTypeName;

	@Column(name = MoicConstant.ORDER_TYPE_CODE)
	private String orderTypeCode;

	@Column(name = MoicConstant.ORDER_EVENT)
	private String orderEvent;

	@Column(name = MoicConstant.ORDER_EVENT_CODE)
	private String orderEventCode;

	@Column(name = MoicConstant.ORDER_DELIVERY_NAME)
	private String orderDeliveryName;

	@Column(name = MoicConstant.ORDER_DELIVERY_CODE)
	private String orderDeliveryCode;

	@Column(name = MoicConstant.DATE_CREATED)
	private Date dateCreated;

	@Column(name = MoicConstant.DATE_APPROVED)
	private Date dateApproved;

	@Column(name = MoicConstant.DATE_MODIFIED)
	private Date dateModified;

	@Column(name = MoicConstant.DATE_SHIP_START)
	private Date dateShipStart;

	@Column(name = MoicConstant.DATE_SHIP_END)
	private Date dateShipEnd;

	@Column(name = MoicConstant.SALES_REPRESENTATIVE)
	private String salesRepresentative;

	@Column(name = MoicConstant.SALES_REPRESENTATIVE_CODE)
	private String salesRepresentativeCode;

	@Column(name = MoicConstant.CUSTOMER_NAME)
	private String customerName;

	@Column(name = MoicConstant.CUSTOMER_CODE)
	private String customerCode;

	@Column(name = MoicConstant.COMPANY_NAME)
	private String companyName;

	@Column(name = MoicConstant.COMPANY_CODE)
	private String companyCode;

	@Column(name = MoicConstant.CUSTOMER_GROUP_NAME)
	private String customerGroupName;

	@Column(name = MoicConstant.CUSTOMER_GROUP_CODE)
	private String customerGroupCode;

	@Column(name = MoicConstant.CUSTOMER_PURCHASE_ORDER)
	private String customerPurchaseOrder;

	@Column(name = MoicConstant.INTEGRATION_PURCHASE_ORDER)
	private String integrationPurchaseOrder;

	@Column(name = MoicConstant.CUSTOMER_BUYER_NAME)
	private String customerBuyerName;

	@Column(name = MoicConstant.SHIPPING_CODE)
	private String shippingCode;

	@Column(name = MoicConstant.SHIPPING_NAME)
	private String shippingName;

	@Column(name = MoicConstant.SHIPPING_PHONE)
	private String shippingPhone;

	@Column(name = MoicConstant.SHIPPING_EMAIL)
	private String shippingEmail;

	@Column(name = MoicConstant.SHIPPING_ADDRESS1)
	private String shippingAddress1;

	@Column(name = MoicConstant.SHIPPING_ADDRESS2)
	private String shippingAddress2;

	@Column(name = MoicConstant.SHIPPING_CITY)
	private String shippingCity;

	@Column(name = MoicConstant.SHIPPING_REGION)
	private String shippingRegion;

	@Column(name = MoicConstant.SHIPPING_POSTAL_CODE)
	private String shippingPostalCode;

	@Column(name = MoicConstant.SHIPPING_COUNTRY)
	private String shippingCountry;

	@Column(name = MoicConstant.SHIPPING_COUNTRY_ISO_THREE)
	private String shippingCountryIsoThree;

	@Column(name = MoicConstant.SHIPPING_COUNTRY_ISO_TWO)
	private String shippingCountryIsoTwo;

	@Column(name = MoicConstant.SHIPPING_METHOD_NAME)
	private String shippingMethodName;

	@Column(name = MoicConstant.SHIPPING_METHOD_CODE)
	private String shippingMethodCode;

	@Column(name = MoicConstant.BILLING_CODE)
	private String billingCode;

	@Column(name = MoicConstant.BILLING_NAME)
	private String billingName;

	@Column(name = MoicConstant.BILLING_PHONE)
	private String billingPhone;

	@Column(name = MoicConstant.BILLING_ADDRESS1)
	private String billingAddress1;

	@Column(name = MoicConstant.BILLING_ADDRESS2)
	private String billingAddress2;

	@Column(name = MoicConstant.BILLING_CITY)
	private String billingCity;

	@Column(name = MoicConstant.BILLING_REGION)
	private String billingRegion;

	@Column(name = MoicConstant.BILLING_POSTAL_CODE)
	private String billingPostalCode;

	@Column(name = MoicConstant.BILLING_COUNTRY)
	private String billingCountry;

	@Column(name = MoicConstant.BILLING_COUNTRY_ISO_THREE)
	private String billingCountryIsoThree;

	@Column(name = MoicConstant.BILLING_COUNTRY_ISO_TWO)
	private String billingCountryIsoTwo;

	@Column(name = MoicConstant.PAYMENT_METHOD_NAME)
	private String paymentMethodName;

	@Column(name = MoicConstant.PAYMENT_METHOD_CODE)
	private String paymentMethodCode;

	@Column(name = MoicConstant.TAX_ID_LABEL)
	private String taxIdLabel;

	@Column(name = MoicConstant.WARE_HOUSE_NAME)
	private String warehouseName;

	@Column(name = MoicConstant.WARE_HOUSE_CODE)
	private String warehouseCode;

	@Column(name = MoicConstant.CREATED_BY_USER_NAME)
	private String createdByUsername;

	@Column(name = MoicConstant.CREATED_BY_EMAIL)
	private String createdByEmail;

	@Column(name = MoicConstant.IS_PERSONAL_ORDER)
	private Boolean isPersonalOrder;
	
	@Column(name = MoicConstant.ORDER_TOTAL_USD, precision = 10, scale = 2)
	private Double orderTotalUSD;

	/* Audit fields */
	@Column(name = MoicConstant.USER_ID)
	private String userId;

	@Column(name = MoicConstant.USER_NAME)
	private String userName;

	@Column(name = MoicConstant.CREATE_DATE)
	private Date createDate;

	@Column(name = MoicConstant.MODIFIED_DATE)
	private Date modifiedDate;

	@Column(name = MoicConstant.CREATE_BY)
	private String createdBy;

	@Column(name = MoicConstant.MODIFIED_BY)
	private String modifiedBy;

	public JoorOrder() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param orderId
	 * @param orderComments
	 * @param orderInternalComments
	 * @param orderCurrency
	 * @param orderCurrencyRetail
	 * @param orderCurrencyName
	 * @param orderDiscount
	 * @param orderDiscountPercent
	 * @param orderShippingTotal
	 * @param orderTotal
	 * @param orderSource
	 * @param orderSeasonCode
	 * @param orderType
	 * @param orderTypeName
	 * @param orderTypeCode
	 * @param orderEvent
	 * @param orderEventCode
	 * @param orderDeliveryName
	 * @param orderDeliveryCode
	 * @param dateCreated
	 * @param dateApproved
	 * @param dateModified
	 * @param dateShipStart
	 * @param dateShipEnd
	 * @param salesRepresentative
	 * @param salesRepresentativeCode
	 * @param customerName
	 * @param customerCode
	 * @param companyName
	 * @param companyCode
	 * @param customerGroupName
	 * @param customerGroupCode
	 * @param customerPurchaseOrder
	 * @param integrationPurchaseOrder
	 * @param customerBuyerName
	 * @param shippingCode
	 * @param shippingName
	 * @param shippingPhone
	 * @param shippingEmail
	 * @param shippingAddress1
	 * @param shippingAddress2
	 * @param shippingCity
	 * @param shippingRegion
	 * @param shippingPostalCode
	 * @param shippingCountry
	 * @param shippingCountryIsoThree
	 * @param shippingCountryIsoTwo
	 * @param shippingMethodName
	 * @param shippingMethodCode
	 * @param billingCode
	 * @param billingName
	 * @param billingPhone
	 * @param billingAddress1
	 * @param billingAddress2
	 * @param billingCity
	 * @param billingRegion
	 * @param billingPostalCode
	 * @param billingCountry
	 * @param billingCountryIsoThree
	 * @param billingCountryIsoTwo
	 * @param paymentMethodName
	 * @param paymentMethodCode
	 * @param taxIdLabel
	 * @param warehouseName
	 * @param warehouseCode
	 * @param createdByUsername
	 * @param createdByEmail
	 * @param isPersonalOrder
	 * @param orderTotalUSD
	 * @param userId
	 * @param userName
	 * @param createDate
	 * @param modifiedDate
	 * @param createdBy
	 * @param modifiedBy
	 */
	public JoorOrder(Long orderId, String orderComments, String orderInternalComments, String orderCurrency,
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
			Boolean isPersonalOrder, Double orderTotalUSD, String userId, String userName, Date createDate,
			Date modifiedDate, String createdBy, String modifiedBy) {
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
		this.userId = userId;
		this.userName = userName;
		this.createDate = createDate;
		this.modifiedDate = modifiedDate;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
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

	@Override
	public String toString() {
		return "JoorOrder [orderId=" + orderId + ", orderComments=" + orderComments + ", orderInternalComments="
				+ orderInternalComments + ", orderCurrency=" + orderCurrency + ", orderCurrencyRetail="
				+ orderCurrencyRetail + ", orderCurrencyName=" + orderCurrencyName + ", orderDiscount=" + orderDiscount
				+ ", orderDiscountPercent=" + orderDiscountPercent + ", orderShippingTotal=" + orderShippingTotal
				+ ", orderTotal=" + orderTotal + ", orderSource=" + orderSource + ", orderSeasonCode=" + orderSeasonCode
				+ ", orderType=" + orderType + ", orderTypeName=" + orderTypeName + ", orderTypeCode=" + orderTypeCode
				+ ", orderEvent=" + orderEvent + ", orderEventCode=" + orderEventCode + ", orderDeliveryName="
				+ orderDeliveryName + ", orderDeliveryCode=" + orderDeliveryCode + ", dateCreated=" + dateCreated
				+ ", dateApproved=" + dateApproved + ", dateModified=" + dateModified + ", dateShipStart="
				+ dateShipStart + ", dateShipEnd=" + dateShipEnd + ", salesRepresentative=" + salesRepresentative
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
				+ ", userId=" + userId + ", userName=" + userName + ", createDate=" + createDate + ", modifiedDate="
				+ modifiedDate + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + "]";
	}	
}