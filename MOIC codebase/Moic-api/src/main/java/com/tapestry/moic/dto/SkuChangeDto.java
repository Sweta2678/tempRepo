package com.tapestry.moic.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Class SKUChangeDto.
 *
 * @version 0.0.1
 */
/**
 * @author khem.bhardwaj
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SkuChangeDto {

	private Long idAuto;
	
	private String season;
	
	private String	oldSKU;	

	private String	newSKU;	

	private Boolean	drop_1;
	
	private Boolean delete;

	private Date effectiveDate;

	private String	level;
	
	private String	levelId;

	private String	userName;

	private String userId;

	private Date	createDate;

	private Date	modifiedDate;

	private String	createdBy;
		
	private String	modifiedBy;
		
	private String channel;
	private String source;

	public SkuChangeDto() {
		// TODO Auto-generated constructor stub
	}
	









	public SkuChangeDto(Long idAuto, String season, String oldSKU, String newSKU, Boolean drop_1, Boolean delete,
			Date effectiveDate, String level, String levelId, String userName, String userId, Date createDate,
			Date modifiedDate, String createdBy, String modifiedBy, String channel, String source) {
		super();
		this.idAuto = idAuto;
		this.season = season;
		this.oldSKU = oldSKU;
		this.newSKU = newSKU;
		this.drop_1 = drop_1;
		this.delete = delete;
		this.effectiveDate = effectiveDate;
		this.level = level;
		this.levelId = levelId;
		this.userName = userName;
		this.userId = userId;
		this.createDate = createDate;
		this.modifiedDate = modifiedDate;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.channel = channel;
		this.source = source;
	}










	public String getChannel() {
		return channel;
	}










	public void setChannel(String channel) {
		this.channel = channel;
	}










	public void setDrop_1(Boolean drop_1) {
		this.drop_1 = drop_1;
	}










	public Long getIdAuto() {
		return idAuto;
	}

	public void setIdAuto(Long idAuto) {
		this.idAuto = idAuto;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
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
	
	public String getOldSKU() {
		return oldSKU;
	}

	public void setOldSKU(String oldSKU) {
		this.oldSKU = oldSKU;
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

	public void setDrop(Boolean drop_1) {
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((newSKU == null) ? 0 : newSKU.hashCode());
		result = prime * result + ((oldSKU == null) ? 0 : oldSKU.hashCode());
		result = prime * result + ((season == null) ? 0 : season.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SkuChangeDto other = (SkuChangeDto) obj;
		if (newSKU == null) {
			if (other.newSKU != null)
				return false;
		} else if (!newSKU.equals(other.newSKU))
			return false;
		if (oldSKU == null) {
			if (other.oldSKU != null)
				return false;
		} else if (!oldSKU.equals(other.oldSKU))
			return false;
		if (season == null) {
			if (other.season != null)
				return false;
		} else if (!season.equals(other.season))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "SkuChangeDto [idAuto=" + idAuto + ", season=" + season + ", oldSKU=" + oldSKU + ", newSKU=" + newSKU
				+ ", drop_1=" + drop_1 + ", delete=" + delete + ", effectiveDate=" + effectiveDate + ", level=" + level
				+ ", levelId=" + levelId + ", userName=" + userName + ", userId=" + userId + ", createDate="
				+ createDate + ", modifiedDate=" + modifiedDate + ", createdBy=" + createdBy + ", modifiedBy="
				+ modifiedBy + ", channel=" + channel + ", source=" + source + "]";
	}

	
	

}
