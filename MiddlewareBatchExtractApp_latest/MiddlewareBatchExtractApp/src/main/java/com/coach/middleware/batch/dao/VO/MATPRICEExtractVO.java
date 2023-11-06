package com.coach.middleware.batch.dao.VO;

import java.sql.Timestamp;

public class MATPRICEExtractVO {
	private String CMNUMBER;
	private String VENDORNUMBER;
	private String PRICETYPE;
	private String TODATE;
	private String PRICE;
	private String CURRENCY;
	private String FROMDATE;
	private String UNIQUEID;
	private Timestamp CREATEDATE;
	private Timestamp UPDATEDATE;
	private String COLORCODE;
	private String USERACTION;
	public String getCMNUMBER() {
		return CMNUMBER;
	}
	public void setCMNUMBER(String cMNUMBER) {
		CMNUMBER = cMNUMBER;
	}
	public String getVENDORNUMBER() {
		return VENDORNUMBER;
	}
	public void setVENDORNUMBER(String vENDORNUMBER) {
		VENDORNUMBER = vENDORNUMBER;
	}
	public String getPRICETYPE() {
		return PRICETYPE;
	}
	public void setPRICETYPE(String pRICETYPE) {
		PRICETYPE = pRICETYPE;
	}
	public String getTODATE() {
		return TODATE;
	}
	public void setTODATE(String tODATE) {
		TODATE = tODATE;
	}
	public String getPRICE() {
		return PRICE;
	}
	public void setPRICE(String pRICE) {
		PRICE = pRICE;
	}
	public String getCURRENCY() {
		return CURRENCY;
	}
	public void setCURRENCY(String cURRENCY) {
		CURRENCY = cURRENCY;
	}
	public String getFROMDATE() {
		return FROMDATE;
	}
	public void setFROMDATE(String fROMDATE) {
		FROMDATE = fROMDATE;
	}
	public String getUNIQUEID() {
		return UNIQUEID;
	}
	public void setUNIQUEID(String uNIQUEID) {
		UNIQUEID = uNIQUEID;
	}
	public Timestamp getCREATEDATE() {
		return CREATEDATE;
	}
	public void setCREATEDATE(Timestamp cREATEDATE) {
		CREATEDATE = cREATEDATE;
	}
	public Timestamp getUPDATEDATE() {
		return UPDATEDATE;
	}
	public void setUPDATEDATE(Timestamp uPDATEDATE) {
		UPDATEDATE = uPDATEDATE;
	}
	public String getCOLORCODE() {
		return COLORCODE;
	}
	public void setCOLORCODE(String cOLORCODE) {
		COLORCODE = cOLORCODE;
	}
	public String getUSERACTION() {
		return USERACTION;
	}
	public void setUSERACTION(String uSERACTION) {
		USERACTION = uSERACTION;
	}
	
}
