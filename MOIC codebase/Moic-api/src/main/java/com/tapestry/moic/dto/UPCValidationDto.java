package com.tapestry.moic.dto;



public class UPCValidationDto  {

	
	private String	sizeName;
	private String  skuName;
	private String SKUUniqueID;
	private String	season;
	private String combinedSku;
	/**
	 * @param sizeName
	 * @param skuName
	 * @param sKUUniqueID
	 * @param season
	 * @param combinedSku
	 */
	public UPCValidationDto(String sizeName, String skuName, String sKUUniqueID, String season, String combinedSku) {
		super();
		this.sizeName = sizeName;
		this.skuName = skuName;
		this.SKUUniqueID = sKUUniqueID;
		this.season = season;
		this.combinedSku = combinedSku;
	}
	/**
	 * @return the sizeName
	 */
	public String getSizeName() {
		return sizeName;
	}
	/**
	 * @param sizeName the sizeName to set
	 */
	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}
	/**
	 * @return the skuName
	 */
	public String getSkuName() {
		return skuName;
	}
	/**
	 * @param skuName the skuName to set
	 */
	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}
	/**
	 * @return the sKUUniqueID
	 */
	public String getSKUUniqueID() {
		return SKUUniqueID;
	}
	/**
	 * @param sKUUniqueID the sKUUniqueID to set
	 */
	public void setSKUUniqueID(String sKUUniqueID) {
		SKUUniqueID = sKUUniqueID;
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
	 * @return the combinedSku
	 */
	public String getCombinedSku() {
		return combinedSku;
	}
	/**
	 * @param combinedSku the combinedSku to set
	 */
	public void setCombinedSku(String combinedSku) {
		this.combinedSku = combinedSku;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((SKUUniqueID == null) ? 0 : SKUUniqueID.hashCode());
		result = prime * result + ((combinedSku == null) ? 0 : combinedSku.hashCode());
		result = prime * result + ((season == null) ? 0 : season.hashCode());
		result = prime * result + ((sizeName == null) ? 0 : sizeName.hashCode());
		result = prime * result + ((skuName == null) ? 0 : skuName.hashCode());
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
		UPCValidationDto other = (UPCValidationDto) obj;
		if (SKUUniqueID == null) {
			if (other.SKUUniqueID != null)
				return false;
		} else if (!SKUUniqueID.equals(other.SKUUniqueID))
			return false;
		if (combinedSku == null) {
			if (other.combinedSku != null)
				return false;
		} else if (!combinedSku.equals(other.combinedSku))
			return false;
		if (season == null) {
			if (other.season != null)
				return false;
		} else if (!season.equals(other.season))
			return false;
		if (sizeName == null) {
			if (other.sizeName != null)
				return false;
		} else if (!sizeName.equals(other.sizeName))
			return false;
		if (skuName == null) {
			if (other.skuName != null)
				return false;
		} else if (!skuName.equals(other.skuName))
			return false;
		return true;
	}
	

}
