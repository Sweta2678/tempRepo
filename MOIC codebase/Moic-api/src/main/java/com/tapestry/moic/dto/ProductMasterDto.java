package com.tapestry.moic.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * The Class ProductMasterDto.
 *
 * @version 0.0.1
 */
public class ProductMasterDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String plmUniqueID;
	private String seasonCode;
	private String brandID;
	private String divisionID;
	private String styleID;
	private String styleDescription;
	private Boolean active;
	private String designNumber;
	private String developmentTypeCode;
	private Date inStoreDate;
	private Date debutedOnDate;
	private String group;
	private String channel;
	
	
	public ProductMasterDto() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param plmUniqueID
	 * @param seasonCode
	 * @param brandID
	 * @param divisionID
	 * @param styleID
	 * @param styleDescription
	 * @param active
	 * @param designNumber
	 * @param developmentTypeCode
	 * @param inStoreDate
	 * @param debutedOnDate
	 * @param group
	 * @param channel
	 */
	public ProductMasterDto(String plmUniqueID, String seasonCode, String brandID, String divisionID, String styleID,
			String styleDescription, Boolean active, String designNumber, String developmentTypeCode, Date inStoreDate,
			Date debutedOnDate, String group, String channel) {
		super();
		this.plmUniqueID = plmUniqueID;
		this.seasonCode = seasonCode;
		this.brandID = brandID;
		this.divisionID = divisionID;
		this.styleID = styleID;
		this.styleDescription = styleDescription;
		this.active = active;
		this.designNumber = designNumber;
		this.developmentTypeCode = developmentTypeCode;
		this.inStoreDate = inStoreDate;
		this.debutedOnDate = debutedOnDate;
		this.group = group;
		this.channel = channel;
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

	public String getStyleDescription() {
		return styleDescription;
	}

	public void setStyleDescription(String styleDescription) {
		this.styleDescription = styleDescription;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getDesignNumber() {
		return designNumber;
	}

	public void setDesignNumber(String designNumber) {
		this.designNumber = designNumber;
	}

	public String getDevelopmentTypeCode() {
		return developmentTypeCode;
	}

	public void setDevelopmentTypeCode(String developmentTypeCode) {
		this.developmentTypeCode = developmentTypeCode;
	}

	public Date getInStoreDate() {
		return inStoreDate;
	}

	public void setInStoreDate(Date inStoreDate) {
		this.inStoreDate = inStoreDate;
	}

	public Date getDebutedOnDate() {
		return debutedOnDate;
	}

	public void setDebutedOnDate(Date debutedOnDate) {
		this.debutedOnDate = debutedOnDate;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	@Override
	public String toString() {
		return "ProductMaster [plmUniqueID=" + plmUniqueID + ", seasonCode=" + seasonCode + ", brandID=" + brandID
				+ ", divisionID=" + divisionID + ", styleID=" + styleID + ", styleDescription=" + styleDescription
				+ ", active=" + active + ", designNumber=" + designNumber + ", developmentTypeCode="
				+ developmentTypeCode + ", inStoreDate=" + inStoreDate + ", debutedOnDate=" + debutedOnDate + ", group="
				+ group + ", channel=" + channel + "]";
	}
}

