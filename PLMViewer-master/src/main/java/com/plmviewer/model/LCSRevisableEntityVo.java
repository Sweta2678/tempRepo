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
@Table(name="lcsrevisableentity")
public class LCSRevisableEntityVo {
	@Id
	@Column(name="att1")
	private String att1;
	@Column(name="ida3a10")
	private String ida3a10;
	@Column(name="branchiditerationinfo")
	private String branchiditerationinfo;
	@Column(name="latestiterationinfo")
	private String latestiterationinfo;
	/**
	 * @return the att1
	 */
	public String getAtt1() {
		return att1;
	}
	/**
	 * @param att1 the att1 to set
	 */
	public void setAtt1(String att1) {
		this.att1 = att1;
	}
	/**
	 * @return the ida3a10
	 */
	public String getIda3a10() {
		return ida3a10;
	}
	/**
	 * @param ida3a10 the ida3a10 to set
	 */
	public void setIda3a10(String ida3a10) {
		this.ida3a10 = ida3a10;
	}
	/**
	 * @return the branchiditerationinfo
	 */
	public String getBranchiditerationinfo() {
		return branchiditerationinfo;
	}
	/**
	 * @param branchiditerationinfo the branchiditerationinfo to set
	 */
	public void setBranchiditerationinfo(String branchiditerationinfo) {
		this.branchiditerationinfo = branchiditerationinfo;
	}
	/**
	 * @return the latestiterationinfo
	 */
	public String getLatestiterationinfo() {
		return latestiterationinfo;
	}
	/**
	 * @param latestiterationinfo the latestiterationinfo to set
	 */
	public void setLatestiterationinfo(String latestiterationinfo) {
		this.latestiterationinfo = latestiterationinfo;
	}


}
