package com.coach.middleware.batch.dao.VO;

public class PRODMASTFullPriceMOAVO {

	public String ownerUniqueId;
	public String whPrice;
	public String retailPrice;
	public String targetCost;
	public String totalCost;
	
	
	public String getRetailPrice() {
		return retailPrice;
	}
	public void setRetailPrice(String retailPrice) {
		this.retailPrice = retailPrice;
	}
	public String getTargetCost() {
		return targetCost;
	}
	public void setTargetCost(String targetCost) {
		this.targetCost = targetCost;
	}
	public String getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(String totalCost) {
		this.totalCost = totalCost;
	}
	public String getWhPrice() {
		return whPrice;
	}
	public void setWhPrice(String whPrice) {
		this.whPrice = whPrice;
	}
	public String getOwnerUniqueId() {
		return ownerUniqueId;
	}
	public void setOwnerUniqueId(String ownerUniqueId) {
		this.ownerUniqueId = ownerUniqueId;
	}

}
