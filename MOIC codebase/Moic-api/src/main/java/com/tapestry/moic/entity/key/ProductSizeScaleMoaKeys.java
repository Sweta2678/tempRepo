package com.tapestry.moic.entity.key;

import java.io.Serializable;

public class ProductSizeScaleMoaKeys implements Serializable{

	private static final long serialVersionUID = 3322245727583214312L;
	
	private String sizeName;
	private String SKUUniqueID;
	private String styleUniqueID;
	
	public ProductSizeScaleMoaKeys() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param sizeName
	 * @param sKUUniqueID
	 * @param styleUniqueID
	 */
	public ProductSizeScaleMoaKeys(String sizeName, String sKUUniqueID, String styleUniqueID) {
		super();
		this.sizeName = sizeName;
		SKUUniqueID = sKUUniqueID;
		this.styleUniqueID = styleUniqueID;
	}

	public String getSizeName() {
		return sizeName;
	}

	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}

	public String getSKUUniqueID() {
		return SKUUniqueID;
	}

	public void setSKUUniqueID(String sKUUniqueID) {
		SKUUniqueID = sKUUniqueID;
	}

	public String getStyleUniqueID() {
		return styleUniqueID;
	}

	public void setStyleUniqueID(String styleUniqueID) {
		this.styleUniqueID = styleUniqueID;
	}

	@Override
	public String toString() {
		return "ProductSizeScaleMoaStagingKeys [sizeName=" + sizeName + ", SKUUniqueID=" + SKUUniqueID
				+ ", styleUniqueID=" + styleUniqueID + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((SKUUniqueID == null) ? 0 : SKUUniqueID.hashCode());
		result = prime * result + ((sizeName == null) ? 0 : sizeName.hashCode());
		result = prime * result + ((styleUniqueID == null) ? 0 : styleUniqueID.hashCode());
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
		ProductSizeScaleMoaKeys other = (ProductSizeScaleMoaKeys) obj;
		if (SKUUniqueID == null) {
			if (other.SKUUniqueID != null)
				return false;
		} else if (!SKUUniqueID.equals(other.SKUUniqueID))
			return false;
		if (sizeName == null) {
			if (other.sizeName != null)
				return false;
		} else if (!sizeName.equals(other.sizeName))
			return false;
		if (styleUniqueID == null) {
			if (other.styleUniqueID != null)
				return false;
		} else if (!styleUniqueID.equals(other.styleUniqueID))
			return false;
		return true;
	}
}