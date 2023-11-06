package com.tapestry.moic.entity.key;

import java.io.Serializable;


public class MOQReportKeys implements Serializable{

	private static final long serialVersionUID = -3672626915908500952L;
	
	private String soldToNumber;
	private String season;	
	private String oldSKU;
	private String newSKU;
	public MOQReportKeys() {}
	
	
	public MOQReportKeys(String soldToNumber, String season, String oldSKU, String newSKU) {
		super();
		this.soldToNumber = soldToNumber;
		this.season = season;
		this.oldSKU = oldSKU;
		this.newSKU = newSKU;
	}


	public String getSoldToNumber() {
		return soldToNumber;
	}
	public void setSoldToNumber(String soldToNumber) {
		this.soldToNumber = soldToNumber;
	}
	public String getSeason() {
		return season;
	}
	public void setSeason(String season) {
		this.season = season;
	}
	public String getOldSKU() {
		return oldSKU;
	}
	public void setOldSKU(String oldSKU) {
		this.oldSKU = oldSKU;
	}


	public String getNewSKU() {
		return newSKU;
	}


	public void setNewSKU(String newSKU) {
		this.newSKU = newSKU;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((newSKU == null) ? 0 : newSKU.hashCode());
		result = prime * result + ((oldSKU == null) ? 0 : oldSKU.hashCode());
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
		MOQReportKeys other = (MOQReportKeys) obj;
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
				+ ", newSKU=" + newSKU + "]";
	}	
}