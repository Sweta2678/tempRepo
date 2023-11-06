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

import lombok.Data;

@Entity
@Table(name = MoicConstant.SKU_CHANGE)
@Data
public class SkuChange implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; 
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long idAuto;

	@Column(name = MoicConstant.OLD_SKU)
	private String	oldSKU;
	
	@Column(name = MoicConstant.SEASON)
	private String	season;
	
	@Column(name = MoicConstant.NEW_SKU)
	private String	newSKU;
	
	@Column(name = MoicConstant.DROP)
	private Boolean	drop_1;
	
	@Column(name = MoicConstant.LEVEL)
	private String	level;
	
	@Column(name = MoicConstant.LEVEL_ID)
	private String	levelId;
	
	@Column(name = MoicConstant.DELETE)
	private Boolean	delete;
		
	
	@Column(name = MoicConstant.EFFECTIVE_DATE)
	private Date	effectiveDate;
	
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
	
	public SkuChange() {}
	public SkuChange(String oldSKU, String season, String newSKU, Boolean drop_1, String level, String levelId,
			Boolean delete, Date effectiveDate, String userName, String userId, String createdBy, Date createDate,
			String modifiedBy, Date modifiedDate, String source) {		
		this.oldSKU = oldSKU;
		this.season = season;
		this.newSKU = newSKU;
		this.drop_1 = drop_1;
		this.level = level;
		this.levelId = levelId;
		this.delete = delete;
		this.effectiveDate = effectiveDate;
		this.userName = userName;
		this.userId = userId;
		this.createdBy = createdBy;
		this.createDate = createDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.source = source;
	}

	public Long getIdAuto() {
		return idAuto;
	}

	public String getOldSKU() {
		return oldSKU;
	}

	public void setOldSKU(String oldSKU) {
		this.oldSKU = oldSKU;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public String getNewSKU() {
		return newSKU;
	}

	public void setNewSKU(String newSKU) {
		this.newSKU = newSKU;
	}

	public Boolean getDrop_1() {
		return drop_1;
	}

	public void setDrop_1(Boolean drop_1) {
		this.drop_1 = drop_1;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getLevelId() {
		return levelId;
	}

	public void setLevelId(String levelId) {
		this.levelId = levelId;
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
	

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Override
	public String toString() {
		return "SkuChange [idAuto=" + idAuto + ", oldSKU=" + oldSKU + ", season=" + season + ", newSKU=" + newSKU
				+ ", drop_1=" + drop_1 + ", level=" + level + ", levelId=" + levelId + ", delete=" + delete
				+ ", effectiveDate=" + effectiveDate + ", userName=" + userName + ", userId=" + userId + ", createdBy="
				+ createdBy + ", createDate=" + createDate + ", modifiedBy=" + modifiedBy + ", modifiedDate="
				+ modifiedDate + ", source=" + source + "]";
	}	
}