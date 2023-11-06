package com.tapestry.moic.dto;

import java.io.Serializable;
import java.util.Date;

public class ProductGlobalPricingDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String plmUniqueID;
	private String seasonCode;
	private String brandID;
	private String divisionID;
	private String styleID;
	private int	us1RetailPrice;
	private int	us1WholesalePrice;
	private int	gb1RetailPrice;
	private int	gb1WholesalePrice;
	private int	eu1RetailPrice;
	private int	eu1WholesalePrice;
	private int	ca1RetailPrice;
	private int	ca1WholesalePrice;
	private int	jpy1RetailPrice;
	private int	jpy1WholesalePrice;
	private Date effectiveDate;
	
	public ProductGlobalPricingDto() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param plmUniqueID
	 * @param seasonCode
	 * @param brandID
	 * @param divisionID
	 * @param styleID
	 * @param us1RetailPrice
	 * @param us1WholesalePrice
	 * @param gb1RetailPrice
	 * @param gb1WholesalePrice
	 * @param eu1RetailPrice
	 * @param eu1WholesalePrice
	 * @param ca1RetailPrice
	 * @param ca1WholesalePrice
	 * @param jpy1RetailPrice
	 * @param jpy1WholesalePrice
	 * @param effectiveDate
	 */
	public ProductGlobalPricingDto(String plmUniqueID, String seasonCode, String brandID, String divisionID,
			String styleID, int us1RetailPrice, int us1WholesalePrice, int gb1RetailPrice, int gb1WholesalePrice,
			int eu1RetailPrice, int eu1WholesalePrice, int ca1RetailPrice, int ca1WholesalePrice, int jpy1RetailPrice,
			int jpy1WholesalePrice, Date effectiveDate) {
		super();
		this.plmUniqueID = plmUniqueID;
		this.seasonCode = seasonCode;
		this.brandID = brandID;
		this.divisionID = divisionID;
		this.styleID = styleID;
		this.us1RetailPrice = us1RetailPrice;
		this.us1WholesalePrice = us1WholesalePrice;
		this.gb1RetailPrice = gb1RetailPrice;
		this.gb1WholesalePrice = gb1WholesalePrice;
		this.eu1RetailPrice = eu1RetailPrice;
		this.eu1WholesalePrice = eu1WholesalePrice;
		this.ca1RetailPrice = ca1RetailPrice;
		this.ca1WholesalePrice = ca1WholesalePrice;
		this.jpy1RetailPrice = jpy1RetailPrice;
		this.jpy1WholesalePrice = jpy1WholesalePrice;
		this.effectiveDate = effectiveDate;
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
	public int getUs1RetailPrice() {
		return us1RetailPrice;
	}
	public void setUs1RetailPrice(int us1RetailPrice) {
		this.us1RetailPrice = us1RetailPrice;
	}
	public int getUs1WholesalePrice() {
		return us1WholesalePrice;
	}
	public void setUs1WholesalePrice(int us1WholesalePrice) {
		this.us1WholesalePrice = us1WholesalePrice;
	}
	public int getGb1RetailPrice() {
		return gb1RetailPrice;
	}
	public void setGb1RetailPrice(int gb1RetailPrice) {
		this.gb1RetailPrice = gb1RetailPrice;
	}
	public int getGb1WholesalePrice() {
		return gb1WholesalePrice;
	}
	public void setGb1WholesalePrice(int gb1WholesalePrice) {
		this.gb1WholesalePrice = gb1WholesalePrice;
	}
	public int getEu1RetailPrice() {
		return eu1RetailPrice;
	}
	public void setEu1RetailPrice(int eu1RetailPrice) {
		this.eu1RetailPrice = eu1RetailPrice;
	}
	public int getEu1WholesalePrice() {
		return eu1WholesalePrice;
	}
	public void setEu1WholesalePrice(int eu1WholesalePrice) {
		this.eu1WholesalePrice = eu1WholesalePrice;
	}
	public int getCa1RetailPrice() {
		return ca1RetailPrice;
	}
	public void setCa1RetailPrice(int ca1RetailPrice) {
		this.ca1RetailPrice = ca1RetailPrice;
	}
	public int getCa1WholesalePrice() {
		return ca1WholesalePrice;
	}
	public void setCa1WholesalePrice(int ca1WholesalePrice) {
		this.ca1WholesalePrice = ca1WholesalePrice;
	}
	public int getJpy1RetailPrice() {
		return jpy1RetailPrice;
	}
	public void setJpy1RetailPrice(int jpy1RetailPrice) {
		this.jpy1RetailPrice = jpy1RetailPrice;
	}
	public int getJpy1WholesalePrice() {
		return jpy1WholesalePrice;
	}
	public void setJpy1WholesalePrice(int jpy1WholesalePrice) {
		this.jpy1WholesalePrice = jpy1WholesalePrice;
	}
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	@Override
	public String toString() {
		return "ProductGlobalPricingDto [plmUniqueID=" + plmUniqueID + ", seasonCode=" + seasonCode + ", brandID="
				+ brandID + ", divisionID=" + divisionID + ", styleID=" + styleID + ", us1RetailPrice=" + us1RetailPrice
				+ ", us1WholesalePrice=" + us1WholesalePrice + ", gb1RetailPrice=" + gb1RetailPrice
				+ ", gb1WholesalePrice=" + gb1WholesalePrice + ", eu1RetailPrice=" + eu1RetailPrice
				+ ", eu1WholesalePrice=" + eu1WholesalePrice + ", ca1RetailPrice=" + ca1RetailPrice
				+ ", ca1WholesalePrice=" + ca1WholesalePrice + ", jpy1RetailPrice=" + jpy1RetailPrice
				+ ", jpy1WholesalePrice=" + jpy1WholesalePrice + ", effectiveDate=" + effectiveDate + "]";
	}
}
