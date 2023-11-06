package com.tapestry.moic.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tapestry.moic.constant.MoicConstant;

/**
 * The Class JoorOrderDivisions.
 *
 * @version 0.0.1
 */
@Entity
@Table(name = MoicConstant.JOOR_ORDER_DIVISIONS)
public class JoorOrderDivisions implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = MoicConstant.ORDER_ID)
	private Long orderID;
	
	@Column(name = MoicConstant.DIVISION_NAME)
	private String divisionName;
	
	@Column(name = MoicConstant.DIVISION_CODE)
	private String divisionCode;
	
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
	
	public JoorOrderDivisions() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param orderID
	 * @param divisionName
	 * @param divisionCode
	 * @param userId
	 * @param userName
	 * @param createDate
	 * @param modifiedDate
	 * @param createdBy
	 * @param modifiedBy
	 */
	public JoorOrderDivisions(Long orderID, String divisionName, String divisionCode, String userId, String userName,
			Date createDate, Date modifiedDate, String createdBy, String modifiedBy) {
		super();
		this.orderID = orderID;
		this.divisionName = divisionName;
		this.divisionCode = divisionCode;
		this.userId = userId;
		this.userName = userName;
		this.createDate = createDate;
		this.modifiedDate = modifiedDate;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
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
		return "JoorOrderDivisions [orderID=" + orderID + ", divisionName=" + divisionName + ", divisionCode="
				+ divisionCode + ", userId=" + userId + ", userName=" + userName + ", createDate=" + createDate
				+ ", modifiedDate=" + modifiedDate + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + "]";
	}	
}