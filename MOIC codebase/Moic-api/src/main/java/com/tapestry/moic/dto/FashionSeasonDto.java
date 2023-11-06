package com.tapestry.moic.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FashionSeasonDto {
	
	private String seasonCode;

	/**
	 * 
	 */
	public FashionSeasonDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param seasonCode
	 */
	public FashionSeasonDto(String seasonCode) {
		super();
		this.seasonCode = seasonCode;
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
		return "FashionSeasonDto [seasonCode=" + seasonCode + "]";
	}

	
}
