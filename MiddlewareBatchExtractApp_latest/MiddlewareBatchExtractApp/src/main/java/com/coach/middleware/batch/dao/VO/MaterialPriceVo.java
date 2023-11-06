package com.coach.middleware.batch.dao.VO;

public class MaterialPriceVo {
	private String fromDate;
	private String  uniqueID;
	private String  matsupplierid;
	private String  matcolorid;
	private String price;
	
	public String getUniqueID() {
		return uniqueID;
	}
	public void setUniqueID(String uniqueID) {
		this.uniqueID = uniqueID;
	}
	public String getMatsupplierid() {
		return matsupplierid;
	}
	public void setMatsupplierid(String matsupplierid) {
		this.matsupplierid = matsupplierid;
	}
	public String getMatcolorid() {
		return matcolorid;
	}
	public void setMatcolorid(String matcolorid) {
		this.matcolorid = matcolorid;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	
}
