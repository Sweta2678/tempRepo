package com.tapestry.moic.entity.key;

import java.io.Serializable;

public class PreBuyKeys implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private String SKU;
	private String seasonCode;
	
	
	public PreBuyKeys() {
	}


	public PreBuyKeys(String sKU, String seasonCode) {
		super();
		SKU = sKU;
		this.seasonCode = seasonCode;
	}


	public String getSKU() {
		return SKU;
	}


	public void setSKU(String sKU) {
		SKU = sKU;
	}


	public String getSeasonCode() {
		return seasonCode;
	}


	public void setSeasonCode(String seasonCode) {
		this.seasonCode = seasonCode;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((SKU == null) ? 0 : SKU.hashCode());
		result = prime * result + ((seasonCode == null) ? 0 : seasonCode.hashCode());
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
		PreBuyKeys other = (PreBuyKeys) obj;
		if (SKU == null) {
			if (other.SKU != null)
				return false;
		} else if (!SKU.equals(other.SKU))
			return false;
		if (seasonCode == null) {
			if (other.seasonCode != null)
				return false;
		} else if (!seasonCode.equals(other.seasonCode))
			return false;
		return true;
	}

}
