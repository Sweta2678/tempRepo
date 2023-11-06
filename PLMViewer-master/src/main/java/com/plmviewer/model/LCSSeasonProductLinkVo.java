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
@Table(name="LCSSEASONPRODUCTLINK")
public class LCSSeasonProductLinkVo {

	@Id
	@Column(name="IDA3A5")
	private String ida3a5;
	@Column (name="IDA3B5")
	private String ida3b5;
	@Column(name="EFFECTOUTDATE")
	private String effectOutDate;
	@Column(name="SEASONREMOVED")
	private String seasonRemoved;
	/**
	 * @return the ida3a5
	 */
	public String getIda3a5() {
		return ida3a5;
	}
	/**
	 * @param ida3a5 the ida3a5 to set
	 */
	public void setIda3a5(String ida3a5) {
		this.ida3a5 = ida3a5;
	}
	/**
	 * @return the ida3b5
	 */
	public String getIda3b5() {
		return ida3b5;
	}
	/**
	 * @param ida3b5 the ida3b5 to set
	 */
	public void setIda3b5(String ida3b5) {
		this.ida3b5 = ida3b5;
	}
	/**
	 * @return the effectOutDate
	 */
	public String getEffectOutDate() {
		return effectOutDate;
	}
	/**
	 * @param effectOutDate the effectOutDate to set
	 */
	public void setEffectOutDate(String effectOutDate) {
		this.effectOutDate = effectOutDate;
	}
	/**
	 * @return the seasonRemoved
	 */
	public String getSeasonRemoved() {
		return seasonRemoved;
	}
	/**
	 * @param seasonRemoved the seasonRemoved to set
	 */
	public void setSeasonRemoved(String seasonRemoved) {
		this.seasonRemoved = seasonRemoved;
	}
}
