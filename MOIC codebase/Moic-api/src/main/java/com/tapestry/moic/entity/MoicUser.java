package com.tapestry.moic.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tapestry.moic.constant.MoicConstant;

@Entity
@Table(name = MoicConstant.MOIC_USER)
public class MoicUser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = MoicConstant.MOIC_USER_ID)
	private Integer moicUserId;

	@Column(name = MoicConstant.EMAIL_ADDRESS, length = 50)
	private String emailAddress;
	
	@Column(name = MoicConstant.ROLE_ID)
	private Integer roleId;
	
	@Column(name =  MoicConstant.DISPLAY_NAME, length = 50)
	private String displayName;
	
	@Column(name = MoicConstant.GIVEN_NAME, length = 50)
	private String givenName;
	
	@Column(name = MoicConstant.SURNAME, length = 50)
	private String surname;
	
	/**
	 * Audit Fields.
	 */
	@Column(name = MoicConstant.USER_ID)
	private Integer userId;
	
	@Column(name = MoicConstant.CREATE_BY, length = 50)
	private String createdBy;
	
	@Column(name = MoicConstant.CREATE_DATE)
	private Date createdDate;
	
	@Column(name = MoicConstant.MODIFIED_BY, length = 50)
	private String modifiedBy;
	
	@Column(name = MoicConstant.MODIFIED_DATE)
	private Date modifiedDate;

	/**
	 * 
	 */
	public MoicUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param moicUserId
	 * @param emailAddress
	 * @param roleId
	 * @param displayName
	 * @param givenName
	 * @param surname
	 * @param userId
	 * @param createdBy
	 * @param createdDate
	 * @param modifiedBy
	 * @param modifiedDate
	 */
	public MoicUser(Integer moicUserId, String emailAddress, Integer roleId, String displayName, String givenName,
			String surname, Integer userId, String createdBy, Date createdDate, String modifiedBy, Date modifiedDate) {
		super();
		this.moicUserId = moicUserId;
		this.emailAddress = emailAddress;
		this.roleId = roleId;
		this.displayName = displayName;
		this.givenName = givenName;
		this.surname = surname;
		this.userId = userId;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
	}

	/**
	 * @return the moicUserId
	 */
	public Integer getMoicUserId() {
		return moicUserId;
	}

	/**
	 * @param moicUserId the moicUserId to set
	 */
	public void setMoicUserId(Integer moicUserId) {
		this.moicUserId = moicUserId;
	}

	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * @return the roleId
	 */
	public Integer getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * @return the givenName
	 */
	public String getGivenName() {
		return givenName;
	}

	/**
	 * @param givenName the givenName to set
	 */
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
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
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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

	@Override
	public String toString() {
		return "MoicUser [moicUserId=" + moicUserId + ", emailAddress=" + emailAddress + ", roleId=" + roleId
				+ ", displayName=" + displayName + ", givenName=" + givenName + ", surname=" + surname + ", userId="
				+ userId + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", modifiedBy=" + modifiedBy
				+ ", modifiedDate=" + modifiedDate + "]";
	}	
}