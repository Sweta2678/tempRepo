package com.tapestry.moic.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tapestry.moic.constant.MoicConstant;

/**
 * The Class JoorOrderDoors.
 *
 * @version 0.0.1
 */
@Entity
@Table(name = MoicConstant.JOOR_ORDER_DOORS)
public class JoorOrderDoors implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = MoicConstant.ORDER_ID)
	private Long orderID;
	
	@Column(name = MoicConstant.DOOR_ID)
	private Integer doorId;
	
	@Column(name = MoicConstant.DOOR_NAME)
	private String doorName;
	
	@Column(name = MoicConstant.DOOR_CODE)
	private String doorCode;
	
	@Column(name = MoicConstant.DOOR_DELETED)
	private Boolean doorDeleted;
	
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
	
	public JoorOrderDoors() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param orderID
	 * @param doorId
	 * @param doorName
	 * @param doorCode
	 * @param doorDeleted
	 * @param userId
	 * @param userName
	 * @param createDate
	 * @param modifiedDate
	 * @param createdBy
	 * @param modifiedBy
	 */
	public JoorOrderDoors(Long orderID, Integer doorId, String doorName, String doorCode, Boolean doorDeleted,
			String userId, String userName, Date createDate, Date modifiedDate, String createdBy, String modifiedBy) {
		super();
		this.orderID = orderID;
		this.doorId = doorId;
		this.doorName = doorName;
		this.doorCode = doorCode;
		this.doorDeleted = doorDeleted;
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
		return "JoorOrderDoors [orderID=" + orderID + ", doorId=" + doorId + ", doorName=" + doorName + ", doorCode="
				+ doorCode + ", doorDeleted=" + doorDeleted + ", userId=" + userId + ", userName=" + userName
				+ ", createDate=" + createDate + ", modifiedDate=" + modifiedDate + ", createdBy=" + createdBy
				+ ", modifiedBy=" + modifiedBy + "]";
	}
}