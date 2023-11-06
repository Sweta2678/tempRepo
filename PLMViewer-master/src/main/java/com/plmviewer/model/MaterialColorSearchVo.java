/**
 * 
 */
package com.plmviewer.model;

import java.math.BigDecimal;

/**
 * @author uthanasekarapandian
 *
 */
public class MaterialColorSearchVo {
	
	private String colorName;
	private String supplierName;
	private BigDecimal colorId;
	private String materialColorStatus;
	/**
	 * @return the colorName
	 */
	public String getColorName() {
		return colorName;
	}
	/**
	 * @param colorName the colorName to set
	 */
	public void setColorName(String colorName) {
		this.colorName = colorName;
	}
	/**
	 * @return the supplierName
	 */
	public String getSupplierName() {
		return supplierName;
	}
	/**
	 * @param supplierName the supplierName to set
	 */
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	/**
	 * @return the colorId
	 */
	public BigDecimal getColorId() {
		return colorId;
	}
	/**
	 * @param colorId the colorId to set
	 */
	public void setColorId(BigDecimal colorId) {
		this.colorId = colorId;
	}
	/**
	 * @return the materialColorStatus
	 */
	public String getMaterialColorStatus() {
		return materialColorStatus;
	}
	/**
	 * @param materialColorStatus the materialColorStatus to set
	 */
	public void setMaterialColorStatus(String materialColorStatus) {
		this.materialColorStatus = materialColorStatus;
	}

}
