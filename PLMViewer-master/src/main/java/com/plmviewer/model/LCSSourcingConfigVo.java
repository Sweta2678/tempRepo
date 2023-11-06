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
@Table(name="LCSSOURCINGCONFIG")
public class LCSSourcingConfigVo {
	@Id
	@Column(name="ida3masterreference")
	private String prodIDA3MAsterReference;
	@Column(name="att1")
	private String sourceName;
	@Column(name="latestiterationinfo")
	private String latestIterationInfo;
	
	public String getProdIDA3MAsterReference() {
		return prodIDA3MAsterReference;
	}
	public void setProdIDA3MAsterReference(String prodIDA3MAsterReference) {
		this.prodIDA3MAsterReference = prodIDA3MAsterReference;
	}
	public String getSourceName() {
		return sourceName;
	}
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	public String getLatestIterationInfo() {
		return latestIterationInfo;
	}
	public void setLatestIterationInfo(String latestIterationInfo) {
		this.latestIterationInfo = latestIterationInfo;
	}

}
