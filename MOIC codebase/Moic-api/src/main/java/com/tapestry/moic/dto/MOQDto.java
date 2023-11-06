package com.tapestry.moic.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Class MOQDto.
 *
 * @version 0.0.1
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MOQDto {

	private Long idAuto;

	private String sku;
	
	private String seasonCode;

	private Boolean delete;

	private Date effectiveDate;

	public MOQDto() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param idAuto
	 * @param sku
	 * @param seasonCode
	 * @param delete
	 * @param effectiveDate
	 */
	public MOQDto(Long idAuto, String sku, String seasonCode, Boolean delete, Date effectiveDate) {
		super();
		this.idAuto = idAuto;
		this.sku = sku;
		this.seasonCode = seasonCode;
		this.delete = delete;
		this.effectiveDate = effectiveDate;
	}

	public Long getIdAuto() {
		return idAuto;
	}

	public void setIdAuto(Long idAuto) {
		this.idAuto = idAuto;
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
	

	@Override
	public String toString() {
		return "MOQDto [idAuto=" + idAuto + ", sku=" + sku + ", seasonCode=" + seasonCode + ", delete=" + delete
				+ ", effectiveDate=" + effectiveDate + "]";
	}

}
