package com.coach.middleware.batch.dao.VO;

import java.io.Serializable;
import java.sql.Date;

public class LpskuExtractVO implements Cloneable,Serializable {
	private String skuUniqueid;
	private String skumasterid;
	private String sizeScaleID;
	
	private String skuStypeid;
	private String prodUniqueid;
	
	private String CREATESTAMP;
	private String MODIFYSTAMP;
	
	private Date CREATETIMESTAMP;
	private Date MODIFYTIMESTAMP;
	
	
	private String sizescale;
	private String skumaterial;
	
	
	
	
	
	private String slotNumber;
	private String styleNumber;
	private String zSKU;
	private String sKUGroupCode;
	private String sKUGroupDesc;
	private String abbreviatedStyleSKUFullName;
	private String colorName;
	private String departmentCode;
	private String classCode;
	private String subClassCode;
	private String collectioncode;
	private String collectionDescription;
	private String subCollectionCode;
	private String styleIntroDate;
	private String styleFSIntroDate;
	private String sKUIntroDate;
	private String sKUFSIntroDate;
	private String materialTypeCode;
	private String materialTypeValue;
	private String merchSKUstatus;
	private String bOMstatus;
	private String retailPrice;
	private String whoePrice;
	private String originalTargetcost;
	//Need to Check materialTypeCoded2 attriute 
	private String materialTypeCoded2;
	//**********************************
	private String SignatureTypeCode;
	private String pmSignatureTypeCode;
	private String skuSignatureTypeCode;
	private String signatureTypeDesc;
	private String materialTypeDesc;
	private String longLeadTimeMaterial;
	private String onetimeBuy;
	private String genderCode;
	private String team;
	private String designer;
	private String engineer;
	private String prodDevelopment;
	private String productionMgr;
	private String silhouetteCode;
	private String silhouetteDesc;
	private String renegade;
	private String earlyCosting;
	private String dualDevelopment;
	private String handbagSize;
	private String factoryStoreTypeProduct;
	
	private String skuFactoryStoreType;
	private String styleDeleteDate;
	private String styleFSDeleteDate;
	private String sKUDeleteDate;
	private String sKUFSDeleteDate;
	private String productName;
	private String planExclusion;
	private String keyStyleCosting;
	private String firstEdit;
	private String secondEdit;
	private String gcc;
	private String vendorRef;
	private String license;
	
	
	

	
	
	private String shortcolorcode;
	private String shortcolorname;
	private String departmentdesc;
	private String skuname;
	
	private String mode;
	
	
	public Date getCREATETIMESTAMP() {
		return CREATETIMESTAMP;
	}
	public void setCREATETIMESTAMP(Date cREATETIMESTAMP) {
		CREATETIMESTAMP = cREATETIMESTAMP;
	}
	public Date getMODIFYTIMESTAMP() {
		return MODIFYTIMESTAMP;
	}
	public void setMODIFYTIMESTAMP(Date mODIFYTIMESTAMP) {
		MODIFYTIMESTAMP = mODIFYTIMESTAMP;
	}
	public String getSlotNumber() {
		return slotNumber;
	}
	public String getSizeScaleID() {
		return sizeScaleID;
	}
	public void setSizeScaleID(String sizeScaleName) {
		this.sizeScaleID = sizeScaleName;
	}
	public String getSkumaterial() {
		return skumaterial;
	}
	public void setSkumaterial(String skumaterial) {
		this.skumaterial = skumaterial;
	}
	public void setSlotNumber(String slotNumber) {
		this.slotNumber = slotNumber;
	}
	public String getCREATESTAMP() {
		return CREATESTAMP;
	}
	public void setCREATESTAMP(String createTimestamp) {
		this.CREATESTAMP = createTimestamp;
	}
	public String getMODIFYSTAMP() {
		return MODIFYSTAMP;
	}
	public void setMODIFYSTAMP(String modifyTimestamp) {
		this.MODIFYSTAMP = modifyTimestamp;
	}
	public String getStyleNumber() {
		return styleNumber;
	}
	public String getSkuname() {
		return skuname;
	}
	
	public void setSkuname(String skuname) {
		this.skuname = skuname;
	}
	public void setStyleNumber(String styleNumber) {
		this.styleNumber = styleNumber;
	}
	public String getzSKU() {
		return zSKU;
	}
	public void setzSKU(String zSKU) {
		this.zSKU = zSKU;
	}
	public String getsKUGroupCode() {
		return sKUGroupCode;
	}
	public void setsKUGroupCode(String sKUGroupCode) {
		this.sKUGroupCode = sKUGroupCode;
	}
	public String getsKUGroupDesc() {
		return sKUGroupDesc;
	}
	public void setsKUGroupDesc(String sKUGroupDesc) {
		this.sKUGroupDesc = sKUGroupDesc;
	}
	public String getAbbreviatedStyleSKUFullName() {
		return abbreviatedStyleSKUFullName;
	}
	public void setAbbreviatedStyleSKUFullName(String abbreviatedStyleSKUFullName) {
		this.abbreviatedStyleSKUFullName = abbreviatedStyleSKUFullName;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	public String getClassCode() {
		return classCode;
	}
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	public String getSubClassCode() {
		return subClassCode;
	}
	public void setSubClassCode(String subClassCode) {
		this.subClassCode = subClassCode;
	}
	public String getCollectioncode() {
		return collectioncode;
	}
	public void setCollectioncode(String collectioncode) {
		this.collectioncode = collectioncode;
	}
	public String getCollectionDescription() {
		return collectionDescription;
	}
	public void setCollectionDescription(String collectionDescription) {
		this.collectionDescription = collectionDescription;
	}
	public String getSubCollectionCode() {
		return subCollectionCode;
	}
	public void setSubCollectionCode(String subCollectionCode) {
		this.subCollectionCode = subCollectionCode;
	}
	public String getStyleIntroDate() {
		return styleIntroDate;
	}
	public void setStyleIntroDate(String styleIntroDate) {
		this.styleIntroDate = styleIntroDate;
	}
	public String getStyleFSIntroDate() {
		return styleFSIntroDate;
	}
	public void setStyleFSIntroDate(String styleFSIntroDate) {
		this.styleFSIntroDate = styleFSIntroDate;
	}
	public String getsKUIntroDate() {
		return sKUIntroDate;
	}
	public void setsKUIntroDate(String sKUIntroDate) {
		this.sKUIntroDate = sKUIntroDate;
	}
	public String getsKUFSIntroDate() {
		return sKUFSIntroDate;
	}
	public void setsKUFSIntroDate(String sKUFSIntroDate) {
		this.sKUFSIntroDate = sKUFSIntroDate;
	}
	public String getMaterialTypeCode() {
		return materialTypeCode;
	}
	public void setMaterialTypeCode(String materialTypeCode) {
		this.materialTypeCode = materialTypeCode;
	}
	public String getMaterialTypeValue() {
		return materialTypeValue;
	}
	public void setMaterialTypeValue(String materialTypeValue) {
		this.materialTypeValue = materialTypeValue;
	}
	public String getMerchSKUstatus() {
		return merchSKUstatus;
	}
	public void setMerchSKUstatus(String merchSKUstatus) {
		this.merchSKUstatus = merchSKUstatus;
	}
	public String getbOMstatus() {
		return bOMstatus;
	}
	public void setbOMstatus(String bOMstatus) {
		this.bOMstatus = bOMstatus;
	}
	public String getRetailPrice() {
		return retailPrice;
	}
	public void setRetailPrice(String retailPrice) {
		this.retailPrice = retailPrice;
	}
	public String getWhoePrice() {
		return whoePrice;
	}
	public void setWhoePrice(String whoePrice) {
		this.whoePrice = whoePrice;
	}
	public String getOriginalTargetcost() {
		return originalTargetcost;
	}
	public void setOriginalTargetcost(String originalTargetcost) {
		this.originalTargetcost = originalTargetcost;
	}
	public String getMaterialTypeCoded2() {
		return materialTypeCoded2;
	}
	public void setMaterialTypeCoded2(String materialTypeCoded2) {
		this.materialTypeCoded2 = materialTypeCoded2;
	}
	
	
	public String getSignatureTypeCode() {
		return SignatureTypeCode;
	}
	public void setSignatureTypeCode(String SignatureTypeCode) {
		this.SignatureTypeCode = SignatureTypeCode;
	}
	
	
	public String getPmSignatureTypeCode() {
		return pmSignatureTypeCode;
	}
	public void setPmSignatureTypeCode(String pmSignatureTypeCode) {
		this.pmSignatureTypeCode = pmSignatureTypeCode;
	}
	public String getSkuSignatureTypeCode() {
		return skuSignatureTypeCode;
	}
	public void setSkuSignatureTypeCode(String skuSignatureTypeCode) {
		this.skuSignatureTypeCode = skuSignatureTypeCode;
	}
	public String getSignatureTypeDesc() {
		return signatureTypeDesc;
	}
	public void setSignatureTypeDesc(String signatureTypeDesc) {
		this.signatureTypeDesc = signatureTypeDesc;
	}
	public String getMaterialTypeDesc() {
		return materialTypeDesc;
	}
	public void setMaterialTypeDesc(String materialTypeDesc) {
		this.materialTypeDesc = materialTypeDesc;
	}
	public String getLongLeadTimeMaterial() {
		return longLeadTimeMaterial;
	}
	public void setLongLeadTimeMaterial(String longLeadTimeMaterial) {
		this.longLeadTimeMaterial = longLeadTimeMaterial;
	}
	public String getOnetimeBuy() {
		return onetimeBuy;
	}
	public void setOnetimeBuy(String onetimeBuy) {
		this.onetimeBuy = onetimeBuy;
	}
	public String getGenderCode() {
		return genderCode;
	}
	public void setGenderCode(String genderCode) {
		this.genderCode = genderCode;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public String getDesigner() {
		return designer;
	}
	public void setDesigner(String designer) {
		this.designer = designer;
	}
	public String getEngineer() {
		return engineer;
	}
	public void setEngineer(String engineer) {
		this.engineer = engineer;
	}
	public String getProdDevelopment() {
		return prodDevelopment;
	}
	public void setProdDevelopment(String prodDevelopment) {
		this.prodDevelopment = prodDevelopment;
	}
	public String getProductionMgr() {
		return productionMgr;
	}
	public void setProductionMgr(String productionMgr) {
		this.productionMgr = productionMgr;
	}
	public String getSilhouetteCode() {
		return silhouetteCode;
	}
	public void setSilhouetteCode(String silhouetteCode) {
		this.silhouetteCode = silhouetteCode;
	}
	public String getSilhouetteDesc() {
		return silhouetteDesc;
	}
	public void setSilhouetteDesc(String silhouetteDesc) {
		this.silhouetteDesc = silhouetteDesc;
	}
	public String getRenegade() {
		return renegade;
	}
	public void setRenegade(String renegade) {
		this.renegade = renegade;
	}
	public String getEarlyCosting() {
		return earlyCosting;
	}
	public void setEarlyCosting(String earlyCosting) {
		this.earlyCosting = earlyCosting;
	}
	public String getDualDevelopment() {
		return dualDevelopment;
	}
	public void setDualDevelopment(String dualDevelopment) {
		this.dualDevelopment = dualDevelopment;
	}
	public String getHandbagSize() {
		return handbagSize;
	}
	public void setHandbagSize(String handbagSize) {
		this.handbagSize = handbagSize;
	}
	public String getFactoryStoreTypeProduct() {
		return factoryStoreTypeProduct;
	}
	public void setFactoryStoreTypeProduct(String factoryStoreTypeProduct) {
		this.factoryStoreTypeProduct = factoryStoreTypeProduct;
	}
	public String getSkuFactoryStoreType() {
		return skuFactoryStoreType;
	}
	public void setSkuFactoryStoreType(String skuFactoryStoreType) {
		this.skuFactoryStoreType = skuFactoryStoreType;
	}
	public String getStyleDeleteDate() {
		return styleDeleteDate;
	}
	public void setStyleDeleteDate(String styleDeleteDate) {
		this.styleDeleteDate = styleDeleteDate;
	}
	public String getStyleFSDeleteDate() {
		return styleFSDeleteDate;
	}
	public void setStyleFSDeleteDate(String styleFSDeleteDate) {
		this.styleFSDeleteDate = styleFSDeleteDate;
	}
	public String getsKUDeleteDate() {
		return sKUDeleteDate;
	}
	public void setsKUDeleteDate(String sKUDeleteDate) {
		this.sKUDeleteDate = sKUDeleteDate;
	}
	public String getsKUFSDeleteDate() {
		return sKUFSDeleteDate;
	}
	public void setsKUFSDeleteDate(String sKUFSDeleteDate) {
		this.sKUFSDeleteDate = sKUFSDeleteDate;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getPlanExclusion() {
		return planExclusion;
	}
	public void setPlanExclusion(String planExclusion) {
		this.planExclusion = planExclusion;
	}
	public String getKeyStyleCosting() {
		return keyStyleCosting;
	}
	public void setKeyStyleCosting(String keyStyleCosting) {
		this.keyStyleCosting = keyStyleCosting;
	}
	public String getFirstEdit() {
		return firstEdit;
	}
	public void setFirstEdit(String firstEdit) {
		this.firstEdit = firstEdit;
	}
	public String getSecondEdit() {
		return secondEdit;
	}
	public void setSecondEdit(String secondEdit) {
		this.secondEdit = secondEdit;
	}
	public String getGcc() {
		return gcc;
	}
	public void setGcc(String gcc) {
		this.gcc = gcc;
	}
	public String getShortcolorcode() {
		return shortcolorcode;
	}
	public void setShortcolorcode(String shortcolorcode) {
		this.shortcolorcode = shortcolorcode;
	}
	public String getShortcolorname() {
		return shortcolorname;
	}
	public void setShortcolorname(String shortcolorname) {
		this.shortcolorname = shortcolorname;
	}
	public String getSkuUniqueid() {
		return skuUniqueid;
	}
	public void setSkuUniqueid(String skuUniqueid) {
		this.skuUniqueid = skuUniqueid;
	}
	public String getSkuStypeid() {
		return skuStypeid;
	}
	public void setSkuStypeid(String skuStypeid) {
		this.skuStypeid = skuStypeid;
	}
	public String getProdUniqueid() {
		return prodUniqueid;
	}
	public void setProdUniqueid(String prodUniqueid) {
		this.prodUniqueid = prodUniqueid;
	}
	public String getDepartmentdesc() {
		return departmentdesc;
	}
	public void setDepartmentdesc(String departmentdesc) {
		this.departmentdesc = departmentdesc;
	}
	
	
	public String getSizescale() {
		return sizescale;
	}
	public void setSizescale(String sizescale) {
		this.sizescale = sizescale;
	}
	public String getColorName() {
		return colorName;
	}
	public void setColorName(String colorName) {
		this.colorName = colorName;
	}
	
	
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	public String getSkumasterid() {
		return skumasterid;
	}
	public void setSkumasterid(String skumasterid) {
		this.skumasterid = skumasterid;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return slotNumber+" :: "+styleNumber+" :: "+skuUniqueid+" :: "+skumasterid;
	}
	
	
	@Override
	public void finalize() {
		//System.out.println(slotNumber+"::"+styleNumber+"  Object Cleared");
	}
	public String getVendorRef() {
		return vendorRef;
	}
	public void setVendorRef(String vendorRef) {
		this.vendorRef = vendorRef;
	}
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}
	

}