/**
 * 
 */
package com.plmviewer.model;

import java.math.BigDecimal;

/**
 * @author uthanasekarapandian
 *
 */
public class MaterialResultVo {
	private String materialName;
	private String materialPartNumber;
	private String materialShortName;
	private BigDecimal materialTypeId;
   private String subType;
    private String materialType;
    private String brand;
    private String commonName;
    private String activeMaterial;
    private String cmParentGroup;
    private String materialSlot;
    private String materialCapacityGroup;
    private String lifeCycleState;
	/**
	 * @return the materialTypeId
	 */
	public BigDecimal getMaterialTypeId() {
		return materialTypeId;
	}
	/**
	 * @param materialTypeId the materialTypeId to set
	 */
	public void setMaterialTypeId(BigDecimal materialTypeId) {
		this.materialTypeId = materialTypeId;
	}
	/**
	 * @return the materialName
	 */
	public String getMaterialName() {
		return materialName;
	}
	/**
	 * @param materialName the materialName to set
	 */
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	/**
	 * @return the materialPartNumber
	 */
	public String getMaterialPartNumber() {
		return materialPartNumber;
	}
	/**
	 * @param materialPartNumber the materialPartNumber to set
	 */
	public void setMaterialPartNumber(String materialPartNumber) {
		this.materialPartNumber = materialPartNumber;
	}
	/**
	 * @return the materialShortName
	 */
	public String getMaterialShortName() {
		return materialShortName;
	}
	/**
	 * @param materialShortName the materialShortName to set
	 */
	public void setMaterialShortName(String materialShortName) {
		this.materialShortName = materialShortName;
	}

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getActiveMaterial() {
        return activeMaterial;
    }

    public void setActiveMaterial(String activeMaterial) {
        this.activeMaterial = activeMaterial;
    }

    public String getCmParentGroup() {
        return cmParentGroup;
    }

    public void setCmParentGroup(String cmParentGroup) {
        this.cmParentGroup = cmParentGroup;
    }
    public String getMaterialSlot() {
        return materialSlot;
    }

    public void setMaterialSlot(String materialSlot) {
        this.materialSlot = materialSlot;
    }
	public String getMaterialCapacityGroup() {
		return materialCapacityGroup;
	}
	public void setMaterialCapacityGroup(String materialCapacityGroup) {
		this.materialCapacityGroup = materialCapacityGroup;
	}
	public String getLifeCycleState() {
		return lifeCycleState;
	}
	public void setLifeCycleState(String lifeCycleState) {
		this.lifeCycleState = lifeCycleState;
	}
    
}
