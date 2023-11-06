package com.tapestry.moic.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tapestry.moic.constant.MoicConstant;

@Entity
@Table(name = MoicConstant.FASHION_SEASON)
public class FashionSeason implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = MoicConstant.FASHION_SEASON_ID)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer seasonCodeId;
	
	@Column(name = MoicConstant.FASHION_SEASON_CODE)
	private String seasonCode;

	/**
	 * 
	 */
	public FashionSeason() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param seasonCodeId
	 * @param seasonCode
	 */
	public FashionSeason(Integer seasonCodeId, String seasonCode) {
		super();
		this.seasonCodeId = seasonCodeId;
		this.seasonCode = seasonCode;
	}

	/**
	 * @return the seasonCodeId
	 */
	public Integer getSeasonCodeId() {
		return seasonCodeId;
	}

	/**
	 * @param seasonCodeId the seasonCodeId to set
	 */
	public void setSeasonCodeId(Integer seasonCodeId) {
		this.seasonCodeId = seasonCodeId;
	}

	/**
	 * @return the seasonCode
	 */
	public String getSeasonCode() {
		return seasonCode;
	}

	/**
	 * @param seasonCode the seasonCode to set
	 */
	public void setSeasonCode(String seasonCode) {
		this.seasonCode = seasonCode;
	}

	@Override
	public String toString() {
		return "FashionSeason [seasonCodeId=" + seasonCodeId + ", seasonCode=" + seasonCode + "]";
	}
}