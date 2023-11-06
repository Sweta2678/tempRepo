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
@Table(name="seasongroupmaster")
public class SeasonGroupMasterVo {
	@Id
	@Column(name="name")
	private String seasonGroupName;
	/**
	 * @return the seasonGroupName
	 */
	public String getSeasonGroupName() {
		return seasonGroupName;
	}
	/**
	 * @param seasonGroupName the seasonGroupName to set
	 */
	public void setSeasonGroupName(String seasonGroupName) {
		this.seasonGroupName = seasonGroupName;
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
	@Column(name="ida2a2")
	private String ida2a2;

}
