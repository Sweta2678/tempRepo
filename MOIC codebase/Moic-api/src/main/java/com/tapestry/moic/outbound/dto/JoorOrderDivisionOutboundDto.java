package com.tapestry.moic.outbound.dto;


public class JoorOrderDivisionOutboundDto {

	private Long orderID;
	private String divisionName;
	private String divisionCode;
	
	public JoorOrderDivisionOutboundDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JoorOrderDivisionOutboundDto(Long orderID, String divisionName, String divisionCode) {
		super();
		this.orderID = orderID;
		this.divisionName = divisionName;
		this.divisionCode = divisionCode;
	}

	public Long getOrderID() {
		return orderID;
	}

	public void setOrderID(Long orderID) {
		this.orderID = orderID;
	}

	public String getDivisionName() {
		return divisionName;
	}

	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}

	public String getDivisionCode() {
		return divisionCode;
	}

	public void setDivisionCode(String divisionCode) {
		this.divisionCode = divisionCode;
	}

	@Override
	public String toString() {
		return "JoorOrderDivisionOutboundDto [orderID=" + orderID + ", divisionName=" + divisionName + ", divisionCode="
				+ divisionCode + "]";
	}
		
	
}
