/**
 * 
 */
package com.plmviewer.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author uthanasekarapandian
 *
 */
@Entity
@Table(name="PRODUCTATTRIBUTESTORE")
@Access(value= AccessType.FIELD)
public class AttributeStoreVo {
	@Id
	@Column(name="VALUEKEY")
	private String valueKey;
	@Column(name="OBJECTNAME")
	private String objectName;
	@Column(name="ATTRIBUTENAME")
	private String attributeName;
	@Column(name="STATUS")
	private String status;
	@Column(name="DISPLAY")
	private String display;
	/**
	 * @return the display2
	 */
	public String getDisplay() {
		return display;
	}
	/**
	 * @param display2 the display2 to set
	 */
	public void setDisplay2(String display2) {
		this.display = display2;
	}
	/**
	 * @return the valueKey
	 */
	public String getValueKey() {
		return valueKey;
	}
	/**
	 * @param valueKey the valueKey to set
	 */
	public void setValueKey(String valueKey) {
		this.valueKey = valueKey;
	}
	/**
	 * @return the objectName
	 */
	public String getObjectName() {
		return objectName;
	}
	/**
	 * @param objectName the objectName to set
	 */
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	/**
	 * @return the attributeName
	 */
	public String getAttributeName() {
		return attributeName;
	}
	/**
	 * @param attributeName the attributeName to set
	 */
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	

}
