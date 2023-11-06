/**
 * 
 */
package com.plmviewer.model;

import java.math.BigDecimal;

/**
 * @author uthanasekarapandian
 *
 */
  public class BOMPart {
	
	private String bomPartName;
	private BigDecimal bomPartMasterReference;
	/**
	 * @return the bomPartName
	 */
	public String getBomPartName() {
		return bomPartName;
	}
	/**
	 * @param bomPartName the bomPartName to set
	 */
	public void setBomPartName(String bomPartName) {
		this.bomPartName = bomPartName;
	}
	/**
	 * @return the bomPartMasterReference
	 */
	public BigDecimal getBomPartMasterReference() {
		return bomPartMasterReference;
	}
	/**
	 * @param bomPartMasterReference the bomPartMasterReference to set
	 */
	public void setBomPartMasterReference(BigDecimal bomPartMasterReference) {
		this.bomPartMasterReference = bomPartMasterReference;
	}
	

}
