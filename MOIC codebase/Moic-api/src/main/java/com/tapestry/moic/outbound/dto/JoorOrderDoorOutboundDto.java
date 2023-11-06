package com.tapestry.moic.outbound.dto;


public class JoorOrderDoorOutboundDto {

	private Long orderID;
	private Integer doorId;
	private String doorName;
	private String doorCode;
	private Boolean doorDeleted;
	
	public JoorOrderDoorOutboundDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JoorOrderDoorOutboundDto(Long orderID, Integer doorId, String doorName, String doorCode,
			Boolean doorDeleted) {
		super();
		this.orderID = orderID;
		this.doorId = doorId;
		this.doorName = doorName;
		this.doorCode = doorCode;
		this.doorDeleted = doorDeleted;
	}

	public Long getOrderID() {
		return orderID;
	}

	public void setOrderID(Long orderID) {
		this.orderID = orderID;
	}

	public Integer getDoorId() {
		return doorId;
	}

	public void setDoorId(Integer doorId) {
		this.doorId = doorId;
	}

	public String getDoorName() {
		return doorName;
	}

	public void setDoorName(String doorName) {
		this.doorName = doorName;
	}

	public String getDoorCode() {
		return doorCode;
	}

	public void setDoorCode(String doorCode) {
		this.doorCode = doorCode;
	}

	public Boolean getDoorDeleted() {
		return doorDeleted;
	}

	public void setDoorDeleted(Boolean doorDeleted) {
		this.doorDeleted = doorDeleted;
	}

	@Override
	public String toString() {
		return "JoorOrderDoorOutboundDto [orderID=" + orderID + ", doorId=" + doorId + ", doorName=" + doorName
				+ ", doorCode=" + doorCode + ", doorDeleted=" + doorDeleted + "]";
	}
	
	
}
