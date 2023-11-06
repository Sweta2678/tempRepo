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
@Table(name="FLEXSPECTOCOMPONENTLINK")
public class FlexSpecToComponentLinkVo {
	@Column(name="COMPONENTTYPE")
	private String componentType;
	@Id
	@Column(name="IDA3A4")
	private String ida3a4;
	@Column(name="IDA3B4")
	private String ida3b4;
	@Column(name="SPECVERSION")
	private String specVersion;
	public String getSpecVersion() {
		return specVersion;
	}
	public void setSpecVersion(String specVersion) {
		this.specVersion = specVersion;
	}
	/**
	 * @return the componentType
	 */
	public String getComponentType() {
		return componentType;
	}
	/**
	 * @param componentType the componentType to set
	 */
	public void setComponentType(String componentType) {
		this.componentType = componentType;
	}
	/**
	 * @return the ida3a4
	 */
	public String getIda3a4() {
		return ida3a4;
	}
	/**
	 * @param ida3a4 the ida3a4 to set
	 */
	public void setIda3a4(String ida3a4) {
		this.ida3a4 = ida3a4;
	}
	/**
	 * @return the ida3b4
	 */
	public String getIda3b4() {
		return ida3b4;
	}
	/**
	 * @param ida3b4 the ida3b4 to set
	 */
	public void setIda3b4(String ida3b4) {
		this.ida3b4 = ida3b4;
	}
	

}
