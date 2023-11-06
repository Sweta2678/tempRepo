package com.tapestry.moic.dto;

import java.io.Serializable;

public class ProductColorDto implements Serializable {

	private static final long serialVersionUID = 1L; 
	
	private String 	plmUniqueID;
	private String 	seasonCode;
	private String 	brandID;
	private String 	divisionID;
	private String 	styleID;
	private String 	colorID;
	private String 	colorSpecification;
	private Boolean	active;
	private String 	styleLineDescription;
	private String 	styleLineDescriptionID;
	private String 	sKULineDescriptionID;
	
	public ProductColorDto() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param plmUniqueID
	 * @param seasonCode
	 * @param brandID
	 * @param divisionID
	 * @param styleID
	 * @param colorID
	 * @param colorSpecification
	 * @param active
	 * @param styleLineDescription
	 * @param styleLineDescriptionID
	 * @param sKULineDescriptionID
	 */
	public ProductColorDto(String plmUniqueID, String seasonCode, String brandID, String divisionID, String styleID,
			String colorID, String colorSpecification, Boolean active, String styleLineDescription,
			String styleLineDescriptionID, String sKULineDescriptionID) {
		super();
		this.plmUniqueID = plmUniqueID;
		this.seasonCode = seasonCode;
		this.brandID = brandID;
		this.divisionID = divisionID;
		this.styleID = styleID;
		this.colorID = colorID;
		this.colorSpecification = colorSpecification;
		this.active = active;
		this.styleLineDescription = styleLineDescription;
		this.styleLineDescriptionID = styleLineDescriptionID;
		this.sKULineDescriptionID = sKULineDescriptionID;
	}

	public String getPlmUniqueID() {
		return plmUniqueID;
	}
	public void setPlmUniqueID(String plmUniqueID) {
		this.plmUniqueID = plmUniqueID;
	}
	public String getSeasonCode() {
		return seasonCode;
	}
	public void setSeasonCode(String seasonCode) {
		this.seasonCode = seasonCode;
	}
	public String getBrandID() {
		return brandID;
	}
	public void setBrandID(String brandID) {
		this.brandID = brandID;
	}
	public String getDivisionID() {
		return divisionID;
	}
	public void setDivisionID(String divisionID) {
		this.divisionID = divisionID;
	}
	public String getStyleID() {
		return styleID;
	}
	public void setStyleID(String styleID) {
		this.styleID = styleID;
	}
	public String getColorID() {
		return colorID;
	}
	public void setColorID(String colorID) {
		this.colorID = colorID;
	}
	public String getColorSpecification() {
		return colorSpecification;
	}
	public void setColorSpecification(String colorSpecification) {
		this.colorSpecification = colorSpecification;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public String getStyleLineDescription() {
		return styleLineDescription;
	}
	public void setStyleLineDescription(String styleLineDescription) {
		this.styleLineDescription = styleLineDescription;
	}
	public String getStyleLineDescriptionID() {
		return styleLineDescriptionID;
	}
	public void setStyleLineDescriptionID(String styleLineDescriptionID) {
		this.styleLineDescriptionID = styleLineDescriptionID;
	}
	public String getsKULineDescriptionID() {
		return sKULineDescriptionID;
	}
	public void setsKULineDescriptionID(String sKULineDescriptionID) {
		this.sKULineDescriptionID = sKULineDescriptionID;
	}
	@Override
	public String toString() {
		return "ProductColor [plmUniqueID=" + plmUniqueID + ", seasonCode=" + seasonCode + ", brandID=" + brandID
				+ ", divisionID=" + divisionID + ", styleID=" + styleID + ", colorID=" + colorID
				+ ", colorSpecification=" + colorSpecification + ", active=" + active + ", styleLineDescription="
				+ styleLineDescription + ", styleLineDescriptionID=" + styleLineDescriptionID
				+ ", sKULineDescriptionID=" + sKULineDescriptionID + "]";
	}
}
