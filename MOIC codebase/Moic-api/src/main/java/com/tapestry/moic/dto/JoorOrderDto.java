package com.tapestry.moic.dto;

import java.util.Date;

/**
 * The Class JoorOrderDto.
 *
 * @version 0.0.1
 */

public class JoorOrderDto {
	
	private int	orderID;
	private int	orderType;
	private String	orderTypeCode;
	private String	orderComments;
	private String	orderCurrency;
	private String	orderCurrentyRetail;
	private String	orderCurrencyName;
	private int	orderDiscount;
	private int	orderDiscountPercent;
	private int	orderShippingTotal;
	private int	orderTotal;
	private String	orderSeasonCode;
	private String	orderTypeNameClient;
	private String	orderTypeCodeClient;
	private Date	orderCreateDate;
	private Date	orderApproveDate;
	private Date	startShipDate;
	private Date	endShipDate;
	private int	exportStatus;
	private Date	earliestOrderModifiedDate;
	private Date	latestOrderModifiedDate;
	private int	sync;
	
	public JoorOrderDto() {
		// TODO Auto-generated constructor stub
	}

	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public int getOrderType() {
		return orderType;
	}
	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}
	public String getOrderTypeCode() {
		return orderTypeCode;
	}
	public void setOrderTypeCode(String orderTypeCode) {
		this.orderTypeCode = orderTypeCode;
	}
	public String getOrderComments() {
		return orderComments;
	}
	public void setOrderComments(String orderComments) {
		this.orderComments = orderComments;
	}
	public String getOrderCurrency() {
		return orderCurrency;
	}
	public void setOrderCurrency(String orderCurrency) {
		this.orderCurrency = orderCurrency;
	}
	public String getOrderCurrentyRetail() {
		return orderCurrentyRetail;
	}
	public void setOrderCurrentyRetail(String orderCurrentyRetail) {
		this.orderCurrentyRetail = orderCurrentyRetail;
	}
	public String getOrderCurrencyName() {
		return orderCurrencyName;
	}
	public void setOrderCurrencyName(String orderCurrencyName) {
		this.orderCurrencyName = orderCurrencyName;
	}
	public int getOrderDiscount() {
		return orderDiscount;
	}
	public void setOrderDiscount(int orderDiscount) {
		this.orderDiscount = orderDiscount;
	}
	public int getOrderDiscountPercent() {
		return orderDiscountPercent;
	}
	public void setOrderDiscountPercent(int orderDiscountPercent) {
		this.orderDiscountPercent = orderDiscountPercent;
	}
	public int getOrderShippingTotal() {
		return orderShippingTotal;
	}
	public void setOrderShippingTotal(int orderShippingTotal) {
		this.orderShippingTotal = orderShippingTotal;
	}
	public int getOrderTotal() {
		return orderTotal;
	}
	public void setOrderTotal(int orderTotal) {
		this.orderTotal = orderTotal;
	}
	public String getOrderSeasonCode() {
		return orderSeasonCode;
	}
	public void setOrderSeasonCode(String orderSeasonCode) {
		this.orderSeasonCode = orderSeasonCode;
	}
	public String getOrderTypeNameClient() {
		return orderTypeNameClient;
	}
	public void setOrderTypeNameClient(String orderTypeNameClient) {
		this.orderTypeNameClient = orderTypeNameClient;
	}
	public String getOrderTypeCodeClient() {
		return orderTypeCodeClient;
	}
	public void setOrderTypeCodeClient(String orderTypeCodeClient) {
		this.orderTypeCodeClient = orderTypeCodeClient;
	}
	public Date getOrderCreateDate() {
		return orderCreateDate;
	}
	public void setOrderCreateDate(Date orderCreateDate) {
		this.orderCreateDate = orderCreateDate;
	}
	public Date getOrderApproveDate() {
		return orderApproveDate;
	}
	public void setOrderApproveDate(Date orderApproveDate) {
		this.orderApproveDate = orderApproveDate;
	}
	public Date getStartShipDate() {
		return startShipDate;
	}
	public void setStartShipDate(Date startShipDate) {
		this.startShipDate = startShipDate;
	}
	public Date getEndShipDate() {
		return endShipDate;
	}
	public void setEndShipDate(Date endShipDate) {
		this.endShipDate = endShipDate;
	}
	public int getExportStatus() {
		return exportStatus;
	}
	public void setExportStatus(int exportStatus) {
		this.exportStatus = exportStatus;
	}
	public Date getEarliestOrderModifiedDate() {
		return earliestOrderModifiedDate;
	}
	public void setEarliestOrderModifiedDate(Date earliestOrderModifiedDate) {
		this.earliestOrderModifiedDate = earliestOrderModifiedDate;
	}
	public Date getLatestOrderModifiedDate() {
		return latestOrderModifiedDate;
	}
	public void setLatestOrderModifiedDate(Date latestOrderModifiedDate) {
		this.latestOrderModifiedDate = latestOrderModifiedDate;
	}
	public int getSync() {
		return sync;
	}
	public void setSync(int sync) {
		this.sync = sync;
	}
	
	/**
	 * @param orderID
	 * @param orderType
	 * @param orderTypeCode
	 * @param orderComments
	 * @param orderCurrency
	 * @param orderCurrentyRetail
	 * @param orderCurrencyName
	 * @param orderDiscount
	 * @param orderDiscountPercent
	 * @param orderShippingTotal
	 * @param orderTotal
	 * @param orderSeasonCode
	 * @param orderTypeNameClient
	 * @param orderTypeCodeClient
	 * @param orderCreateDate
	 * @param orderApproveDate
	 * @param startShipDate
	 * @param endShipDate
	 * @param exportStatus
	 * @param earliestOrderModifiedDate
	 * @param latestOrderModifiedDate
	 * @param sync
	 */
	public JoorOrderDto(int orderID, int orderType, String orderTypeCode, String orderComments, String orderCurrency,
			String orderCurrentyRetail, String orderCurrencyName, int orderDiscount, int orderDiscountPercent,
			int orderShippingTotal, int orderTotal, String orderSeasonCode, String orderTypeNameClient,
			String orderTypeCodeClient, Date orderCreateDate, Date orderApproveDate, Date startShipDate,
			Date endShipDate, int exportStatus, Date earliestOrderModifiedDate, Date latestOrderModifiedDate,
			int sync) {
		super();
		this.orderID = orderID;
		this.orderType = orderType;
		this.orderTypeCode = orderTypeCode;
		this.orderComments = orderComments;
		this.orderCurrency = orderCurrency;
		this.orderCurrentyRetail = orderCurrentyRetail;
		this.orderCurrencyName = orderCurrencyName;
		this.orderDiscount = orderDiscount;
		this.orderDiscountPercent = orderDiscountPercent;
		this.orderShippingTotal = orderShippingTotal;
		this.orderTotal = orderTotal;
		this.orderSeasonCode = orderSeasonCode;
		this.orderTypeNameClient = orderTypeNameClient;
		this.orderTypeCodeClient = orderTypeCodeClient;
		this.orderCreateDate = orderCreateDate;
		this.orderApproveDate = orderApproveDate;
		this.startShipDate = startShipDate;
		this.endShipDate = endShipDate;
		this.exportStatus = exportStatus;
		this.earliestOrderModifiedDate = earliestOrderModifiedDate;
		this.latestOrderModifiedDate = latestOrderModifiedDate;
		this.sync = sync;
	}

	@Override
	public String toString() {
		return "JoorOrder [orderID=" + orderID + ", orderType=" + orderType + ", orderTypeCode=" + orderTypeCode
				+ ", orderComments=" + orderComments + ", orderCurrency=" + orderCurrency + ", orderCurrentyRetail="
				+ orderCurrentyRetail + ", orderCurrencyName=" + orderCurrencyName + ", orderDiscount=" + orderDiscount
				+ ", orderDiscountPercent=" + orderDiscountPercent + ", orderShippingTotal=" + orderShippingTotal
				+ ", orderTotal=" + orderTotal + ", orderSeasonCode=" + orderSeasonCode + ", orderTypeNameClient="
				+ orderTypeNameClient + ", orderTypeCodeClient=" + orderTypeCodeClient + ", orderCreateDate="
				+ orderCreateDate + ", orderApproveDate=" + orderApproveDate + ", startShipDate=" + startShipDate
				+ ", endShipDate=" + endShipDate + ", exportStatus=" + exportStatus + ", earliestOrderModifiedDate="
				+ earliestOrderModifiedDate + ", latestOrderModifiedDate=" + latestOrderModifiedDate + ", sync=" + sync
				+ "]";
	}


}
