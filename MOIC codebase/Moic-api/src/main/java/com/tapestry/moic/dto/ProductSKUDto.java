package com.tapestry.moic.dto;

import java.io.Serializable;
import java.util.Date;

public class ProductSKUDto implements Serializable {

	private static final long serialVersionUID = 1L; 
	
	private String	skuOID;
	private String	department;
	private String	parentsClass;// mapping with "class" field
	private String	subClass;
	private String	plmUniqueID;
	private String	ctyleID;
	private String	colorID;
	private String	skuID;
	private String	skuStatus;
	private String	skuName;
	private String	skuTeam;
	private Date	introDate;
	private String	season;
	private Date	deleteDate;
	private String	channelExclusive;
	private String	material;
	private String	materialType;
	
	public ProductSKUDto() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param skuOID
	 * @param department
	 * @param parentsClass
	 * @param subClass
	 * @param plmUniqueID
	 * @param ctyleID
	 * @param colorID
	 * @param skuID
	 * @param skuStatus
	 * @param skuName
	 * @param skuTeam
	 * @param introDate
	 * @param season
	 * @param deleteDate
	 * @param channelExclusive
	 * @param material
	 * @param materialType
	 */
	public ProductSKUDto(String skuOID, String department, String parentsClass, String subClass, String plmUniqueID,
			String ctyleID, String colorID, String skuID, String skuStatus, String skuName, String skuTeam,
			Date introDate, String season, Date deleteDate, String channelExclusive, String material,
			String materialType) {
		super();
		this.skuOID = skuOID;
		this.department = department;
		this.parentsClass = parentsClass;
		this.subClass = subClass;
		this.plmUniqueID = plmUniqueID;
		this.ctyleID = ctyleID;
		this.colorID = colorID;
		this.skuID = skuID;
		this.skuStatus = skuStatus;
		this.skuName = skuName;
		this.skuTeam = skuTeam;
		this.introDate = introDate;
		this.season = season;
		this.deleteDate = deleteDate;
		this.channelExclusive = channelExclusive;
		this.material = material;
		this.materialType = materialType;
	}

	public String getSkuOID() {
		return skuOID;
	}
	public void setSkuOID(String skuOID) {
		this.skuOID = skuOID;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getParentsClass() {
		return parentsClass;
	}
	public void setParentsClass(String parentsClass) {
		this.parentsClass = parentsClass;
	}
	public String getSubClass() {
		return subClass;
	}
	public void setSubClass(String subClass) {
		this.subClass = subClass;
	}
	public String getPlmUniqueID() {
		return plmUniqueID;
	}
	public void setPlmUniqueID(String plmUniqueID) {
		this.plmUniqueID = plmUniqueID;
	}
	public String getCtyleID() {
		return ctyleID;
	}
	public void setCtyleID(String ctyleID) {
		this.ctyleID = ctyleID;
	}
	public String getColorID() {
		return colorID;
	}
	public void setColorID(String colorID) {
		this.colorID = colorID;
	}
	public String getSkuID() {
		return skuID;
	}
	public void setSkuID(String skuID) {
		this.skuID = skuID;
	}
	public String getSkuStatus() {
		return skuStatus;
	}
	public void setSkuStatus(String skuStatus) {
		this.skuStatus = skuStatus;
	}
	public String getSkuName() {
		return skuName;
	}
	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}
	public String getSkuTeam() {
		return skuTeam;
	}
	public void setSkuTeam(String skuTeam) {
		this.skuTeam = skuTeam;
	}
	public Date getIntroDate() {
		return introDate;
	}
	public void setIntroDate(Date introDate) {
		this.introDate = introDate;
	}
	public String getSeason() {
		return season;
	}
	public void setSeason(String season) {
		this.season = season;
	}
	public Date getDeleteDate() {
		return deleteDate;
	}
	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}
	public String getChannelExclusive() {
		return channelExclusive;
	}
	public void setChannelExclusive(String channelExclusive) {
		this.channelExclusive = channelExclusive;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getMaterialType() {
		return materialType;
	}
	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}
	@Override
	public String toString() {
		return "ProductSKU [skuOID=" + skuOID + ", department=" + department + ", subClass=" + subClass
				+ ", plmUniqueID=" + plmUniqueID + ", ctyleID=" + ctyleID + ", colorID=" + colorID + ", skuID=" + skuID
				+ ", skuStatus=" + skuStatus + ", skuName=" + skuName + ", skuTeam=" + skuTeam + ", introDate="
				+ introDate + ", season=" + season + ", deleteDate=" + deleteDate + ", channelExclusive="
				+ channelExclusive + ", material=" + material + ", materialType=" + materialType + "]";
	}
}
