package com.tapestry.moic.outbound.dto;


public class JoorOrderStyleItemOutboundDto {

	private Long orderID;
	private String itemNumber;
	private String itemColorCode;
	private String itemSize;
	private Long itemStyleId;
	private String styletaggroup;
	private String styletagname;
	private String styletagcode;
	private String styletagvalue;
	
	
	/**
	 * 
	 */
	public JoorOrderStyleItemOutboundDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param orderID
	 * @param itemNumber
	 * @param itemColorCode
	 * @param itemSize
	 * @param itemStyleId
	 * @param styletaggroup
	 * @param styletagname
	 * @param styletagcode
	 * @param styletagvalue
	 */
	public JoorOrderStyleItemOutboundDto(Long orderID, String itemNumber, String itemColorCode, String itemSize,
			Long itemStyleId, String styletaggroup, String styletagname, String styletagcode, String styletagvalue) {
		super();
		this.orderID = orderID;
		this.itemNumber = itemNumber;
		this.itemColorCode = itemColorCode;
		this.itemSize = itemSize;
		this.itemStyleId = itemStyleId;
		this.styletaggroup = styletaggroup;
		this.styletagname = styletagname;
		this.styletagcode = styletagcode;
		this.styletagvalue = styletagvalue;
	}


	/**
	 * @return the orderID
	 */
	public Long getOrderID() {
		return orderID;
	}


	/**
	 * @param orderID the orderID to set
	 */
	public void setOrderID(Long orderID) {
		this.orderID = orderID;
	}


	/**
	 * @return the itemNumber
	 */
	public String getItemNumber() {
		return itemNumber;
	}


	/**
	 * @param itemNumber the itemNumber to set
	 */
	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}


	/**
	 * @return the itemColorCode
	 */
	public String getItemColorCode() {
		return itemColorCode;
	}


	/**
	 * @param itemColorCode the itemColorCode to set
	 */
	public void setItemColorCode(String itemColorCode) {
		this.itemColorCode = itemColorCode;
	}


	/**
	 * @return the itemSize
	 */
	public String getItemSize() {
		return itemSize;
	}


	/**
	 * @param itemSize the itemSize to set
	 */
	public void setItemSize(String itemSize) {
		this.itemSize = itemSize;
	}


	/**
	 * @return the itemStyleId
	 */
	public Long getItemStyleId() {
		return itemStyleId;
	}


	/**
	 * @param itemStyleId the itemStyleId to set
	 */
	public void setItemStyleId(Long itemStyleId) {
		this.itemStyleId = itemStyleId;
	}


	/**
	 * @return the styletaggroup
	 */
	public String getStyletaggroup() {
		return styletaggroup;
	}


	/**
	 * @param styletaggroup the styletaggroup to set
	 */
	public void setStyletaggroup(String styletaggroup) {
		this.styletaggroup = styletaggroup;
	}


	/**
	 * @return the styletagname
	 */
	public String getStyletagname() {
		return styletagname;
	}


	/**
	 * @param styletagname the styletagname to set
	 */
	public void setStyletagname(String styletagname) {
		this.styletagname = styletagname;
	}


	/**
	 * @return the styletagcode
	 */
	public String getStyletagcode() {
		return styletagcode;
	}


	/**
	 * @param styletagcode the styletagcode to set
	 */
	public void setStyletagcode(String styletagcode) {
		this.styletagcode = styletagcode;
	}


	/**
	 * @return the styletagvalue
	 */
	public String getStyletagvalue() {
		return styletagvalue;
	}


	/**
	 * @param styletagvalue the styletagvalue to set
	 */
	public void setStyletagvalue(String styletagvalue) {
		this.styletagvalue = styletagvalue;
	}


	@Override
	public String toString() {
		return "JoorOrderStyleItemOutboundDto [orderID=" + orderID + ", itemNumber=" + itemNumber + ", itemColorCode="
				+ itemColorCode + ", itemSize=" + itemSize + ", itemStyleId=" + itemStyleId + ", styletaggroup="
				+ styletaggroup + ", styletagname=" + styletagname + ", styletagcode=" + styletagcode
				+ ", styletagvalue=" + styletagvalue + "]";
	}
		
}
