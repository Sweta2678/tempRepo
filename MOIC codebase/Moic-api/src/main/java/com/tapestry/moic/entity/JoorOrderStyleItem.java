package com.tapestry.moic.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.tapestry.moic.constant.MoicConstant;
import com.tapestry.moic.entity.key.JoorOrderStyleItemKeys;

@Entity
@Table(name = MoicConstant.JOOR_ORDER_STYLE_ITEM)
@IdClass(JoorOrderStyleItemKeys.class)
public class JoorOrderStyleItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = MoicConstant.ORDER_ID)
	private Long orderID;
	
	@Id
	@Column(name = MoicConstant.ITEM_NUMBER)
	private String itemNumber;
	
	@Id
	@Column(name = MoicConstant.ITEM_COLOR_CODE)
	private String itemColorCode;
	
	@Id
	@Column(name = MoicConstant.ITEM_SIZE)
	private String itemSize;
	
	@Id
	@Column(name = MoicConstant.ITEM_STYLE_ID)
	private Long itemStyleId;
	
	@Id
	@Column(name = MoicConstant.STYLE_TAG_GROUP, length = 100)
	private String styletaggroup;
	
	@Column(name = MoicConstant.STYLE_TAG_NAME, length = 100)
	private String styletagname;

	@Column(name = MoicConstant.STYLE_TAG_CODE, length = 100)
	private String styletagcode;
	
	@Column(name = MoicConstant.STYLE_TAG_VALUE, length = 100)
	private String styletagvalue;
	
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

	/**
	 * 
	 */
	public JoorOrderStyleItem() {
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
	 * @param userId
	 * @param userName
	 * @param createDate
	 * @param modifiedDate
	 * @param createdBy
	 * @param modifiedBy
	 */
	public JoorOrderStyleItem(Long orderID, String itemNumber, String itemColorCode, String itemSize, Long itemStyleId,
			String styletaggroup, String styletagname, String styletagcode, String styletagvalue, String userId,
			String userName, Date createDate, Date modifiedDate, String createdBy, String modifiedBy) {
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
		this.userId = userId;
		this.userName = userName;
		this.createDate = createDate;
		this.modifiedDate = modifiedDate;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
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

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the modifiedDate
	 */
	public Date getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * @param modifiedDate the modifiedDate to set
	 */
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the modifiedBy
	 */
	public String getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * @param modifiedBy the modifiedBy to set
	 */
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Override
	public String toString() {
		return "JoorOrderStyleItem [orderID=" + orderID + ", itemNumber=" + itemNumber + ", itemColorCode="
				+ itemColorCode + ", itemSize=" + itemSize + ", itemStyleId=" + itemStyleId + ", styletaggroup="
				+ styletaggroup + ", styletagname=" + styletagname + ", styletagcode=" + styletagcode
				+ ", styletagvalue=" + styletagvalue + ", userId=" + userId + ", userName=" + userName + ", createDate="
				+ createDate + ", modifiedDate=" + modifiedDate + ", createdBy=" + createdBy + ", modifiedBy="
				+ modifiedBy + "]";
	}	
}