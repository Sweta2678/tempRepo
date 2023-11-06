package com.tapestry.moic.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.tapestry.moic.constant.MoicConstant;
import com.tapestry.moic.entity.key.PreBuyKeys;

/**
 * The Class PreBuy.
 *
 * @version 0.0.1
 */
@Entity
@Table(name = MoicConstant.PRE_BUY)
@IdClass(PreBuyKeys.class)
public class PreBuy implements Serializable {

	private static final long serialVersionUID = 1L; 
	
	@Id
	@Column(name = MoicConstant.SKU)
	private String	SKU;

	@Id
	@Column(name = MoicConstant.SEASON_CODE)
	private String	seasonCode;

	@Column(name = MoicConstant.IS_PRE_BUY_SKU)
	private Boolean isPreBuySKU;
	
	@Column(name = MoicConstant.COO)
	@Size(max = 50)
	private String coo; 
	
	@Column(name = MoicConstant.USER_NAME)
	private String userName;

	@Column(name = MoicConstant.USER_ID)
	private String userId;

	@Column(name = MoicConstant.CREATE_BY)
	private String createdBy;

	@Column(name = MoicConstant.CREATE_DATE)
	private Date createDate;

	@Column(name = MoicConstant.MODIFIED_BY)
	private String modifiedBy;

	@Column(name = MoicConstant.MODIFIED_DATE)
	private Date modifiedDate;

	@Column(name = MoicConstant.SOURCE)
	private String source;

	

	public PreBuy() {
	}



	public PreBuy(String sKU, String seasonCode, Boolean isPreBuySKU, @Size(max = 50) String coo, String userName,
			String userId, String createdBy, Date createDate, String modifiedBy, Date modifiedDate, String source) {
		super();
		SKU = sKU;
		this.seasonCode = seasonCode;
		this.isPreBuySKU = isPreBuySKU;
		this.coo = coo;
		this.userName = userName;
		this.userId = userId;
		this.createdBy = createdBy;
		this.createDate = createDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.source = source;
	}



	public String getSKU() {
		return SKU;
	}



	public void setSKU(String sKU) {
		SKU = sKU;
	}



	public String getSeasonCode() {
		return seasonCode;
	}



	public void setSeasonCode(String seasonCode) {
		this.seasonCode = seasonCode;
	}



	public Boolean getIsPreBuySKU() {
		return isPreBuySKU;
	}



	public void setIsPreBuySKU(Boolean isPreBuySKU) {
		this.isPreBuySKU = isPreBuySKU;
	}



	public String getCoo() {
		return coo;
	}



	public void setCoo(String coo) {
		this.coo = coo;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getUserId() {
		return userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
	}



	public String getCreatedBy() {
		return createdBy;
	}



	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}



	public Date getCreateDate() {
		return createDate;
	}



	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}



	public String getModifiedBy() {
		return modifiedBy;
	}



	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}



	public Date getModifiedDate() {
		return modifiedDate;
	}



	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}



	public String getSource() {
		return source;
	}



	public void setSource(String source) {
		this.source = source;
	}



	@Override
	public String toString() {
		return "PreBuy [SKU=" + SKU + ", seasonCode=" + seasonCode + ", isPreBuySKU=" + isPreBuySKU + ", coo=" + coo
				+ ", userName=" + userName + ", userId=" + userId + ", createdBy=" + createdBy + ", createDate="
				+ createDate + ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate + ", source=" + source
				+ "]";
	}	
}