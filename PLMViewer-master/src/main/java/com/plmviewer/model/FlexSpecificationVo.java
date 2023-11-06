/**
 * 
 */
package com.plmviewer.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author uthanasekarapandian
 *
 */
@Entity
@Table(name="FLEXSPECIFICATION")
public class FlexSpecificationVo {
	
	@Id
	@Column(name="ida3a12")
	private BigDecimal ida3a12;
	@Column(name="att1")
	private String att1;
	@Column(name="IDA3MASTERREFERENCE")
	private String ida3MasterReference;
	
	@Column(name="PARTPRIMARYIMAGEURL")
	private String partPrimaryImageUrl;
	public BigDecimal getIda3a12() {
		return ida3a12;
	}
	public void setIda3a12(BigDecimal ida3a12) {
		this.ida3a12 = ida3a12;
	}
	public String getAtt1() {
		return att1;
	}
	public void setAtt1(String att1) {
		this.att1 = att1;
	}
	public String getIda3MasterReference() {
		return ida3MasterReference;
	}
	public void setIda3MasterReference(String ida3MasterReference) {
		this.ida3MasterReference = ida3MasterReference;
	}
	/**
	 * @return the partPrimaryImageUrl
	 */
	public String getPartPrimaryImageUrl() {
		return partPrimaryImageUrl;
	}
	/**
	 * @param partPrimaryImageUrl the partPrimaryImageUrl to set
	 */
	public void setPartPrimaryImageUrl(String partPrimaryImageUrl) {
		this.partPrimaryImageUrl = partPrimaryImageUrl;
	}
	
	

}
