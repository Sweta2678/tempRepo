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
@Table(name="WTPARTMASTER")
public class WTPartMasterVo {
	
	@Id
	@Column(name="IDA2A2")
	private String ida2a2;
	@Column (name="name")
	private String partMasterName;
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
	/**
	 * @return the name
	 */
	public String getPartMasterName() {
		return partMasterName;
	}
	/**
	 * @param name the name to set
	 */
	public void setPartMasterName(String partMasterName) {
		this.partMasterName = partMasterName;
	}

}
