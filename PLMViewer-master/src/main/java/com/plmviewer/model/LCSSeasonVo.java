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
@Table(name="LCSSEASON")
public class LCSSeasonVo {
	
	@Column(name="BRANCHIDITERATIONINFO")
	private String branchIterationInfo;
	@Id
	@Column(name="IDA3MASTERREFERENCE")
	private String ida3MasterReference;
	@Column(name="STATECHECKOUTINFO")
	private String stateCheckOutInfo;
	@Column(name="LATESTITERATIONINFO")
	private String latestIterationInfo;
	/**
	 * @return the latestIterationInfo
	 */
	public String getLatestIterationInfo() {
		return latestIterationInfo;
	}
	/**
	 * @param latestIterationInfo the latestIterationInfo to set
	 */
	public void setLatestIterationInfo(String latestIterationInfo) {
		this.latestIterationInfo = latestIterationInfo;
	}

}
