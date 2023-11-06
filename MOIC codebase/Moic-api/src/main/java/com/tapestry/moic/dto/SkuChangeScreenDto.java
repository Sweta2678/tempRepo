package com.tapestry.moic.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Class SkuChangeScreenDto.
 *
 * @version 0.0.1
 */
/**
 * @author khem.bhardwaj
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SkuChangeScreenDto {

	private Long idAuto;
	
	private String season;
	
	private String	oldSKU;	

	private String	newSKU;	

	private Boolean	drop;

	private String	level;
	
	private String	levelId;




	public SkuChangeScreenDto() {
		// TODO Auto-generated constructor stub
	}




	public SkuChangeScreenDto(Long idAuto, String season, String oldSKU, String newSKU, Boolean drop, String level,
			String levelId) {
		super();
		this.idAuto = idAuto;
		this.season = season;
		this.oldSKU = oldSKU;
		this.newSKU = newSKU;
		this.drop = drop;
		this.level = level;
		this.levelId = levelId;
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




	public Boolean getDrop() {
		return drop;
	}




	public void setDrop(Boolean drop) {
		this.drop = drop;
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




	@Override
	public String toString() {
		return "SkuChangeScreenDto [idAuto=" + idAuto + ", season=" + season + ", oldSKU=" + oldSKU + ", newSKU="
				+ newSKU + ", drop=" + drop + ", level=" + level + ", levelId=" + levelId + "]";
	}
	
	

}