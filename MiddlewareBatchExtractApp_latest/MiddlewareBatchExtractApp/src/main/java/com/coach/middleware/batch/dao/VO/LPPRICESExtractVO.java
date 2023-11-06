package com.coach.middleware.batch.dao.VO;

import java.util.Date;

public class LPPRICESExtractVO implements Cloneable{
	
	
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	private Date startDate;
	
	private Date endDate;
	private String sku;
	private String ownerUniqueID;
	private int century;//2015 for FY15
	private String fiscalYear; //Ex: FY07, FY08
	private String fiscalPeriod; //Ex: 1,2 ...12
	private String style; 
	private String esssku;
	
	private String yearPeriodStr;// Ex: FY07P1
	private Float retailPrice;
	
	private Float wholeSalePrice;
	
	private Float stdCost;
	
	
	private Float cjiPrice;

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date fromDate) {
		this.startDate = fromDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public String getFiscalYear() {
		return fiscalYear;
	}

	public void setFiscalYear(String fiscalYear) {
		this.fiscalYear = fiscalYear;
	}

	public String getYearPeriodStr() {
		return yearPeriodStr;
	}

	public void setYearPeriodStr(String yearPeriodStr) {
		this.yearPeriodStr = yearPeriodStr;
	}

	public Float getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(Float retailPrice) {
		this.retailPrice = retailPrice;
	}

	public Float getWholeSalePrice() {
		return wholeSalePrice;
	}

	public void setWholeSalePrice(Float wholeSalePrice) {
		this.wholeSalePrice = wholeSalePrice;
	}

	public Float getStdCost() {
		return stdCost;
	}

	public void setStdCost(Float stdCost) {
		this.stdCost = stdCost;
	}


	public Float getCjiPrice() {
		return cjiPrice;
	}

	public void setCjiPrice(Float cjiPrice) {
		this.cjiPrice = cjiPrice;
	}
	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getOwnerUniqueID() {
		return ownerUniqueID;
	}

	public void setOwnerUniqueID(String ownerUniqueID) {
		this.ownerUniqueID = ownerUniqueID;
	}
	public int getCentury() {
		return century;
	}

	public void setCentury(int century) {
		this.century = century;
	}


	public String getFiscalPeriod() {
		return fiscalPeriod;
	}

	public void setFiscalPeriod(String fiscalPeriod) {
		this.fiscalPeriod = fiscalPeriod;
	}
	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}
	public String getEsssku() {
		return esssku;
	}

	public void setEsssku(String esssku) {
		this.esssku = esssku;
	}



}
