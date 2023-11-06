/**
 * 
 */
package com.plmviewer.model;

/**
 * @author uthanasekarapandian
 *
 */
public class MaterialSearchVo {
	
	private String materialName;
	private String materialType;
	private String partNumber ;
	
	private String materialslot;
    private String materialshotname;
    private String lifecyclestate;
    private String brand;
    private String activematerial;
    private String cmparentgroup;
    private String capacitytype;
    
    private String materialsubtype;
    private String exoticmaterial;
    private String exoticcomonname;
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
	 * @return the materialType
	 */
	public String getMaterialType() {
		return materialType;
	}
	/**
	 * @param materialType the materialType to set
	 */
	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}
	/**
	 * @return the partNumber
	 */
	public String getPartNumber() {
		return partNumber;
	}
	/**
	 * @param partNumber the partNumber to set
	 */
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

   public String getMaterialslot() {
        return materialslot;
    }

    public void setMaterialslot(String materialslot) {
        this.materialslot = materialslot;
    }

    public String getMaterialshotname() {
        return materialshotname;
    }

    public void setMaterialshotname(String materialshotname) {
        this.materialshotname = materialshotname;
    }

    public String getLifecyclestate() {
        return lifecyclestate;
    }

    public void setLifecyclestate(String lifecyclestate) {
        this.lifecyclestate = lifecyclestate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getActivematerial() {
        return activematerial;
    }

    public void setActivematerial(String activematerial) {
        this.activematerial = activematerial;
    }

    public String getCmparentgroup() {
        return cmparentgroup;
    }

    public void setCmparentgroup(String cmparentgroup) {
        this.cmparentgroup = cmparentgroup;
    }

    public String getCapacitytype() {
        return capacitytype;
    }

    public void setCapacitytype(String capacitytype) {
        this.capacitytype = capacitytype;
    }

    

    public String getMaterialsubtype() {
        return materialsubtype;
    }

    public void setMaterialsubtype(String materialsubtype) {
        this.materialsubtype = materialsubtype;
    }

    public String getExoticmaterial() {
        return exoticmaterial;
    }

    public void setExoticmaterial(String exoticmaterial) {
        this.exoticmaterial = exoticmaterial;
    }

    public String getExoticcomonname() {
        return exoticcomonname;
    }

    public void setExoticcomonname(String exoticcomonname) {
        this.exoticcomonname = exoticcomonname;
    }

    @Override
    public String toString() {
        return "MaterialSearchVo{" + "materialName=" + materialName + ", materialType=" + materialType + ", partNumber=" + partNumber + ", materialslot=" + materialslot + ", materialshotname=" + materialshotname + ", lifecyclestate=" + lifecyclestate + ", brand=" + brand + ", activematerial=" + activematerial + ", cmparentgroup=" + cmparentgroup + ", capacitytype=" + capacitytype +  ", materialsubtype=" + materialsubtype + ", exoticmaterial=" + exoticmaterial + ", exoticcomonname=" + exoticcomonname + '}';
    }
}
