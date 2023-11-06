package com.tapestry.moic.dto;

public class PreBuyDto{

	private String	sku;
	private String	seasonCode;
	private Boolean isPreBuySKU;
	private String coo;
	
	public PreBuyDto() {
		
	}

	public PreBuyDto(String sku, String seasonCode, Boolean isPreBuySKU, String coo) {
		super();
		this.sku = sku;
		this.seasonCode = seasonCode;
		this.isPreBuySKU = isPreBuySKU;
		this.coo = coo;
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

	@Override
	public String toString() {
		return "PreBuyDto [sku=" + sku + ", seasonCode=" + seasonCode + ", isPreBuySKU=" + isPreBuySKU + ", coo=" + coo
				+ "]";
	}
	
	

	
}
