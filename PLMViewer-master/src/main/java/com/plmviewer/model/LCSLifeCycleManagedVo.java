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
@Table(name="lcslifecyclemanaged")
public class LCSLifeCycleManagedVo {
	
	//select t.att1 from lcslifecyclemanaged t where t.ida3a8=6230 and t.ida2a2=prodarev.num47
	@Id
	@Column(name="att1")
	private String att1;
	@Column(name="ida3a8")
	private String ida3a8;
	@Column(name="ida2a2")
	private String ida2a2;
	
	
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
	 * @return the ida3a8
	 */
	public String getIda3a8() {
		return ida3a8;
	}
	/**
	 * @param ida3a8 the ida3a8 to set
	 */
	public void setIda3a8(String ida3a8) {
		this.ida3a8 = ida3a8;
	}
	/**
	 * @return the ida2a2
	 */
	public String getIda2a2() {
		return ida2a2;
	}
	/**
	 * @param ida2a2 the ida2a2 to set
	 */
	public void setIda2a2(String ida2a2) {
		this.ida2a2 = ida2a2;
	}
	

}
