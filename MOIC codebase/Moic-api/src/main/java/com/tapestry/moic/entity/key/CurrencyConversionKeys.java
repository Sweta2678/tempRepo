package com.tapestry.moic.entity.key;

import java.io.Serializable;

public class CurrencyConversionKeys implements Serializable {

	private static final long serialVersionUID = 1L;
	private String currencyCode;
	private String joorPriceLabel;


	public CurrencyConversionKeys() {
	}

	/**
	 * @param currencyCode
	 * @param joorPriceLabel
	 */
	public CurrencyConversionKeys(String currencyCode, String joorPriceLabel) {
		super();
		this.currencyCode = currencyCode;
		this.joorPriceLabel = joorPriceLabel;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getJoorPriceLabel() {
		return joorPriceLabel;
	}

	public void setJoorPriceLabel(String joorPriceLabel) {
		this.joorPriceLabel = joorPriceLabel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currencyCode == null) ? 0 : currencyCode.hashCode());
		result = prime * result + ((joorPriceLabel == null) ? 0 : joorPriceLabel.hashCode());
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
		CurrencyConversionKeys other = (CurrencyConversionKeys) obj;
		if (currencyCode == null) {
			if (other.currencyCode != null)
				return false;
		} else if (!currencyCode.equals(other.currencyCode))
			return false;
		if (joorPriceLabel == null) {
			if (other.joorPriceLabel != null)
				return false;
		} else if (!joorPriceLabel.equals(other.joorPriceLabel))
			return false;
		return true;
	}
}