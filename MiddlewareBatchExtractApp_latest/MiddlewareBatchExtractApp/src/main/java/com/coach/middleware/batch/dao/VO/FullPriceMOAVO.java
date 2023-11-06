package com.coach.middleware.batch.dao.VO;

import java.util.Date;

public class FullPriceMOAVO {
	public Double WHOLESALEPRICE;
	public Double RETAILPRICE;
	public String STYLENUMBER;
	public String SKU;

	public Double TARGETCOST;
	public Double STDCOST;
	public String OWNERUNIQUEID;
	public String OWNER;
	private Date STARTDATE;
	private Date ENDDATE;
	public Date getSTARTDATE() {
		return STARTDATE;
	}

	public void setSTARTDATE(Date sTARTDATE) {
		STARTDATE = sTARTDATE;
	}

	public Date getENDDATE() {
		return ENDDATE;
	}

	public void setENDDATE(Date eNDDATE) {
		ENDDATE = eNDDATE;
	}

	public Double getWHOLESALEPRICE() {
		return WHOLESALEPRICE;
	}

	public void setWHOLESALEPRICE(Double wHOLESALEPRICE) {
		WHOLESALEPRICE = wHOLESALEPRICE;
	}

	public Double getRETAILPRICE() {
		return RETAILPRICE;
	}

	public void setRETAILPRICE(Double rETAILPRICE) {
		RETAILPRICE = rETAILPRICE;
	}

	public Double getTARGETCOST() {
		return TARGETCOST;
	}

	public void setTARGETCOST(Double tARGETCOST) {
		TARGETCOST = tARGETCOST;
	}

	public Double getSTDCOST() {
		return STDCOST;
	}

	public void setSTDCOST(Double STDCOST) {
		STDCOST = STDCOST;
	}

	public String getOWNERUNIQUEID() {
		return OWNERUNIQUEID;
	}

	public void setOWNERUNIQUEID(String oWNERUNIQUEID) {
		OWNERUNIQUEID = oWNERUNIQUEID;
	}

	public String getOWNER() {
		return OWNER;
	}

	public void setOWNER(String oWNER) {
		OWNER = oWNER;
	}

	public String getSTYLENUMBER() {
		return STYLENUMBER;
	}

	public void setSTYLENUMBER(String sTYLENUMBER) {
		STYLENUMBER = sTYLENUMBER;
	}

	public String getSKU() {
		return SKU;
	}

	public void setSKU(String sKU) {
		SKU = sKU;
	}



	
}
