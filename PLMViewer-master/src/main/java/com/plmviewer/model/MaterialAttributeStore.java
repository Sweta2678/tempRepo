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
@Table(name = "MATERIALATTRIBUTESTORE")
@Access(value = AccessType.FIELD)
public class MaterialAttributeStore {

    @Id
    @Column(name = "VALUEKEY")
    private String valueKey;
    @Column(name = "MATERIALTYPE")
    private String objectName;
    @Column(name = "ATTRIBUTENAME")
    private String attributeName;
    @Column(name = "DISPLAY")
    private String display;

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
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

}
