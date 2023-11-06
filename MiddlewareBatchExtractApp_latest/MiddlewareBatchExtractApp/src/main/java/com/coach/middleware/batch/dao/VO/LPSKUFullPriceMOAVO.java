package com.coach.middleware.batch.dao.VO;

public class LPSKUFullPriceMOAVO {
	public String WHOLESALEPRICE;
	public String RETAILPRICE;
	public String TARGETCOST;
	public String TOTALCOST;
	public String OWNERUNIQUEID;
	public String OWNER;

	public String getWHOLESALEPRICE() {
		return WHOLESALEPRICE;
	}

	public void setWHOLESALEPRICE(String wHOLESALEPRICE) {
		WHOLESALEPRICE = wHOLESALEPRICE;
	}

	public String getRETAILPRICE() {
		return RETAILPRICE;
	}

	public void setRETAILPRICE(String rETAILPRICE) {
		RETAILPRICE = rETAILPRICE;
	}

	public String getTARGETCOST() {
		return TARGETCOST;
	}

	public void setTARGETCOST(String tARGETCOST) {
		TARGETCOST = tARGETCOST;
	}

	public String getTOTALCOST() {
		return TOTALCOST;
	}

	public void setTOTALCOST(String tOTALCOST) {
		TOTALCOST = tOTALCOST;
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



	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	@Override
	public boolean equals(Object arg0) {
		
		// TODO Auto-generated method stub
		return this.OWNERUNIQUEID.equals(((FullPriceMOAVO)arg0).getOWNERUNIQUEID());
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return OWNERUNIQUEID.hashCode();
	}
	
	
}
