/**
 * 
 */
package com.plmviewer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author uthanasekarapandian
 *
 */
@Entity
@Table(name="LCSSOURCINGCONFIGMASTER")
public class LCSSourcingConfigMasterVo {
	
	@Id
	@Column(name="ida2a2")
	private String sourceId;
	@Column(name="IDA3A6")
	private String prodIDA3MasterReference;
	
	/**
	 * @return the sourceId
	 */
	public String getSourceId() {
		return sourceId;
	}
	/**
	 * @param sourceId the sourceId to set
	 */
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	/**
	 * @return the prodIDA3MasterReference
	 */
	public String getProdIDA3MasterReference() {
		return prodIDA3MasterReference;
	}
	/**
	 * @param prodIDA3MasterReference the prodIDA3MasterReference to set
	 */
	public void setProdIDA3MasterReference(String prodIDA3MasterReference) {
		this.prodIDA3MasterReference = prodIDA3MasterReference;
	}

}
