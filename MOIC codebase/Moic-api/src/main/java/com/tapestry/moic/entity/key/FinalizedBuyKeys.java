package com.tapestry.moic.entity.key;

import java.io.Serializable;


public class FinalizedBuyKeys implements Serializable{

	private static final long serialVersionUID = -3672626915908500952L;
	
	private String soldToNumber;
	private String season;	
	private String oldSKU;// Joor OR PMDB mapping with "SKU" field in class FinalizedBuy
	private String newSKU;
	private Long orderID;
	
	public FinalizedBuyKeys() {}

	/**
	 * @param soldToNumber
	 * @param season
	 * @param oldSKU
	 * @param newSKU
	 * @param orderID
	 */
	public FinalizedBuyKeys(String soldToNumber, String season, String oldSKU, String newSKU, Long orderID) {
		super();
		this.soldToNumber = soldToNumber;
		this.season = season;
		this.oldSKU = oldSKU;
		this.newSKU = newSKU;
		this.orderID = orderID;
	}

	/**
	 * @return the soldToNumber
	 */
	public String getSoldToNumber() {
		return soldToNumber;
	}

	/**
	 * @param soldToNumber the soldToNumber to set
	 */
	public void setSoldToNumber(String soldToNumber) {
		this.soldToNumber = soldToNumber;
	}

	/**
	 * @return the season
	 */
	public String getSeason() {
		return season;
	}

	/**
	 * @param season the season to set
	 */
	public void setSeason(String season) {
		this.season = season;
	}

	/**
	 * @return the oldSKU
	 */
	public String getOldSKU() {
		return oldSKU;
	}

	/**
	 * @param oldSKU the oldSKU to set
	 */
	public void setOldSKU(String oldSKU) {
		this.oldSKU = oldSKU;
	}

	/**
	 * @return the newSKU
	 */
	public String getNewSKU() {
		return newSKU;
	}

	/**
	 * @param newSKU the newSKU to set
	 */
	public void setNewSKU(String newSKU) {
		this.newSKU = newSKU;
	}

	/**
	 * @return the orderID
	 */
	public Long getOrderID() {
		return orderID;
	}

	/**
	 * @param orderID the orderID to set
	 */
	public void setOrderID(Long orderID) {
		this.orderID = orderID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((newSKU == null) ? 0 : newSKU.hashCode());
		result = prime * result + ((oldSKU == null) ? 0 : oldSKU.hashCode());
		result = prime * result + ((orderID == null) ? 0 : orderID.hashCode());
		result = prime * result + ((season == null) ? 0 : season.hashCode());
		result = prime * result + ((soldToNumber == null) ? 0 : soldToNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FinalizedBuyKeys other = (FinalizedBuyKeys) obj;
		if (newSKU == null) {
			if (other.newSKU != null)
				return false;
		} else if (!newSKU.equals(other.newSKU))
			return false;
		if (oldSKU == null) {
			if (other.oldSKU != null)
				return false;
		} else if (!oldSKU.equals(other.oldSKU))
			return false;
		if (orderID == null) {
			if (other.orderID != null)
				return false;
		} else if (!orderID.equals(other.orderID))
			return false;
		if (season == null) {
			if (other.season != null)
				return false;
		} else if (!season.equals(other.season))
			return false;
		if (soldToNumber == null) {
			if (other.soldToNumber != null)
				return false;
		} else if (!soldToNumber.equals(other.soldToNumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FinalizedBuyKeys [soldToNumber=" + soldToNumber + ", season=" + season + ", oldSKU=" + oldSKU
				+ ", newSKU=" + newSKU + ", orderID=" + orderID + "]";
	}
	
		
}