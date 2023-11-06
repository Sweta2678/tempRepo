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


/**
 * The Class MOQ.
 *
 * @version 0.0.1
 */
@Entity
@Table(name = MoicConstant.MOQ)

public class MOQ implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idAuto", updatable = false, nullable = false)
	private Long idAuto;
	
	@Column(name = MoicConstant.SKU)
	private String sku;
	
	
	@Column(name = MoicConstant.SEASON_CODE)
	private String seasonCode;

	@Column(name = MoicConstant.DELETE)
	private Boolean delete;

	@Column(name = MoicConstant.EFFECTIVE_DATE)
	private Date effectiveDate;

	/* Audit fields */

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

	


	public Long getIdAuto() {
		return idAuto;
	}

	

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getSeasonCode() {
		return seasonCode;
	}

	public void setSeasonCode(String seasonCode) {
		this.seasonCode = seasonCode;
	}

	public Boolean getDelete() {
		return delete;
	}

	public void setDelete(Boolean delete) {
		this.delete = delete;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
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
		return "MOQ [idAuto=" + idAuto + ", sku=" + sku + ", seasonCode=" + seasonCode + ", delete=" + delete
				+ ", effectiveDate=" + effectiveDate + ", userName=" + userName + ", userId=" + userId + ", createdBy="
				+ createdBy + ", createDate=" + createDate + ", modifiedBy=" + modifiedBy + ", modifiedDate="
				+ modifiedDate + ", source=" + source + "]";
	}
	
}