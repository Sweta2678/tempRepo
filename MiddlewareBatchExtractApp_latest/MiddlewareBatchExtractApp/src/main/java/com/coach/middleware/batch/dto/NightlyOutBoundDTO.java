package com.coach.middleware.batch.dto;

import java.util.*;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.coach.middleware.batch.itemProcessor.ProdMastItemProcessor;

public class NightlyOutBoundDTO implements Cloneable{
	
	private static final Logger logger = Logger
			.getLogger(ProdMastItemProcessor.class);
	
		
		private String styleNum;
		private String introdate;
		private String fsIntrodate;
		private String dept;
		private String styleClass;
		private String subClass;
		private String skuName;
		private String skuNameImg;
		private String skuNamePadded;
		private String sizePadded;
		private String styleuniqueid;
		private String crossBody;
		private String skuUniqueId;
		private String collaboration;
		private String gifting;
		private String productSegmentation;
		private String attitudinalSegments;
		private String styleDesc;
		private String deleteDate;
		private String fsDeleteDate;
		private String targetCost;
		//private String skuTargetCost;
		//private String prodTargetCost;
		private String originalRetailPrice;
		private String orgRetailPrice; 
		private String whPrice; 
		private String totalCost; 
		private String skuTotalCost;
		private String avgWorkingCost1;
		private String skuAvgWorkingCost1;
		private String finalStdCost;
		private String prodStdCost;
		private String collection;
		private String skuIntroYear;
		private String skuIntroDate;
		private String StyleIntroDate;
		private String skuDeleteDate;
		private String skuFsIntroYear;
		private String skuFsIntroDate;
		private String StyleFsIntroDate;
		private String skuFSDeleteDate;
		private String colorFamily;
		private String colorName;
		private String colorUniqueID;
		private String material;
		private String materialName;
		private String silhouette;
		private String silhouetteDesc;
		private String hardwareColor;
		private String hardwareColorDesc;
		private String gender;
		private String genderDesc;
		private String specialProduct;
		private String specialProductDesc;
		private String abcCode;
		private String abcCodeDesc;
		private String planExclusion;
		private String channelExclusive;
		private String StyleGroup;
		private String parentStyle;
		private String parentStyleDesc;
		private String signatureType;
		private String signatureDesc;
		private String mpg;
		private String status;
		private String upcCode;
		private String exotic;
		private String subCollection;
		private String subCollectionDesc;
		private String sizeScale;
		private String pint;
		private String soleType;
		private String toeType;
		private String toeShape;
		private String heelHeight1;
		private String watchCaseSize;
		private String functionality;
		private String faceColor;
		private String watchCaseShape;
		private String lens;
		private String productIdentity;
		private String attitude;
		private String merDesc;
		private String licensed;
		private String staticLine = "Total Product,,Total Product,,,,,,,,,,,Total Product" +
									",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,6,1,";
		private String deptLevelRow;
		private String seasonGroupName;
		private String fsSeasonGroupName;
		
		private String deptCode;
		private String deptDesc;
		private String classCode;
		private String classDesc;
		private String subClassCode; 
		private String subClassDesc;
		private String collectionCode; 
		private String collectionDesc;
		private String skuIntroQuaterStr;
		private String skuFsIntroQuaterStr;
		private String SkuDeleteQuaterStr;
		private String SkuFsDeleteQuaterStr;
		private String styleIntroQuaterStr;
		private String skuIntroYearStr;
		private String skuFsIntroYearStr;
		private String styleIntroYearStr;
		private String styleFsIntroYearStr;
		private String styleFsIntroQuaterStr;
		private String skuDeleteYearStr;
		private String skuFsDeleteYearStr;
		private String styleDeleteYearStr;
		private String styleDeleteQuaterStr;
		private String styleFsDeleteQuaterStr;
		private String styleFsDeleteYearStr;
		private String skuIntroDateStr;
		private String styleIntroDateStr;
		private String styleFsIntroDateStr;
		private String skuFsIntroDateStr;
		private String skuDeleteDateStr;
		private String skuFsDeleteDateStr;
		private String styleDeleteDateStr;
		private String styleFsDeleteDateStr;
		private String skuIntroCalenderDate;
		private String skuFsIntroCalenderDate;
		private String skuDeleteCalenderDate;
		private String skuFsDeleteCalenderDate;
		private String styleIntroCalenderDate;
		private String styleFsIntroCalenderDate;
		private String styleDeleteCalenderDate;
		private String styleFsDeleteCalenderDate;
		private String styleNumPadded;
		private String size;
		private String updated;
		private String factoryType;
		private String shortFactoryType;
		private String sizeIntLevel;
		private String currentCost;
		private String embellishment;
		private String materialGroupName;
		private String materialGroupDesc;
		private String handBagSizeDesc;
		private String handBagSize;
		private String defaultIndicator;
		private String styleNumImg;
		private Set departmentRow = null;
		private Set classRow = null;
		private Set subClassRow = null;
		private Set styleRow = null;
		private Set skuRow = null;
		private Set sizeRow = null;
		private String skuDeleteYear;
		private String styleDeleteYear;
		private String styleDeleteDate;
		private String styleIntroYear;
		private String styleFsIntroYear;
		private String styleFsDeleteYear;
		private String skuFsDeleteYear;
		private String crossBodyDesc;
		private String skuMaterial;
		private String skuSignatureType;
		
		
		
		
		public Object Clone() {
			try {
				return super.clone();
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
				return null;
			} 
		}
		
		
		public String getStyleNumImg() {
			return styleNumImg;
		}
		public void setStyleNumImg(String styleNumImg) {
			this.styleNumImg = styleNumImg;
		}
		public String getStyleNum() {
			return styleNum;
		}
		public void setStyleNum(String styleNum) {
			this.styleNum = styleNum;
		}
		public String getIntrodate() {
			return introdate;
		}
		public void setIntrodate(String introdate) {
			this.introdate = introdate;
		}
		public String getFsIntrodate() {
			return fsIntrodate;
		}
		public void setFsIntrodate(String fsIntrodate) {
			this.fsIntrodate = fsIntrodate;
		}
		public String getDept() {
			return dept;
		}
		public void setDept(String dept) {
			this.dept = dept;
		}
		public String getStyleClass() {
			return styleClass;
		}
		public void setStyleClass(String styleClass) {
			this.styleClass = styleClass;
		}
		public String getSubClass() {
			return subClass;
		}
		public void setSubClass(String subClass) {
			this.subClass = subClass;
		}
		public String getSkuName() {
			return skuName;
		}
		public void setSkuName(String skuName) {
			this.skuName = skuName;
		}
		public String getStyleuniqueid() {
			return styleuniqueid;
		}
		public void setStyleuniqueid(String styleuniqueid) {
			this.styleuniqueid = styleuniqueid;
		}
		public String getCrossBody() {
			return crossBody;
		}
		public void setCrossBody(String crossBody) {
			this.crossBody = crossBody;
		}
		public String getCollaboration() {
			return collaboration;
		}
		public void setCollaboration(String collaboration) {
			this.collaboration = collaboration;
		}
		public String getGifting() {
			return gifting;
		}
		public void setGifting(String gifting) {
			this.gifting = gifting;
		}
		public String getProductSegmentation() {
			return productSegmentation;
		}
		public void setProductSegmentation(String productSegmentation) {
			this.productSegmentation = productSegmentation;
		}
		public String getAttitudinalSegments() {
			return attitudinalSegments;
		}
		public void setAttitudinalSegments(String attitudinalSegments) {
			this.attitudinalSegments = attitudinalSegments;
		}
		public String getStyleDesc() {
			return styleDesc;
		}
		public void setStyleDesc(String styleDesc) {
			this.styleDesc = styleDesc;
		}
		public String getDeleteDate() {
			return deleteDate;
		}
		public void setDeleteDate(String deleteDate) {
			this.deleteDate = deleteDate;
		}
		public String getFsDeleteDate() {
			return fsDeleteDate;
		}
		public void setFsDeleteDate(String fsDeleteDate) {
			this.fsDeleteDate = fsDeleteDate;
		}
		
		public String getOrgRetailPrice() {
			return orgRetailPrice;
		}
		public void setOrgRetailPrice(String orgRetailPrice) {
			this.orgRetailPrice = orgRetailPrice;
		}
		public String getWhPrice() {
			return whPrice;
		}
		public void setWhPrice(String whPrice) {
			this.whPrice = whPrice;
		}
		public String getTotalCost() {
			return totalCost;
		}
		public void setTotalCost(String totalCost) {
			this.totalCost = totalCost;
		}
		public String getAvgWorkingCost1() {
			return avgWorkingCost1;
		}
		public void setAvgWorkingCost1(String avgWorkingCost1) {
			this.avgWorkingCost1 = avgWorkingCost1;
		}
		public String getFinalStdCost() {
			return finalStdCost;
		}
		public void setFinalStdCost(String finalStdCost) {
			this.finalStdCost = finalStdCost;
		}
		public String getCollection() {
			return collection;
		}
		public void setCollection(String collection) {
			this.collection = collection;
		}
		public String getSkuIntroYear() {
			return skuIntroYear;
		}
		public void setSkuIntroYear(String skuIntroYear) {
			this.skuIntroYear = skuIntroYear;
		}
		public String getSkuIntroDate() {
			return skuIntroDate;
		}
		public void setSkuIntroDate(String skuIntroDate) {
			this.skuIntroDate = skuIntroDate;
		}
		public String getStyleIntroDate() {
			return StyleIntroDate;
		}
		public void setStyleIntroDate(String styleIntroDate) {
			StyleIntroDate = styleIntroDate;
		}
		public String getSkuDeleteDate() {
			return skuDeleteDate;
		}
		public void setSkuDeleteDate(String skuDeleteDate) {
			this.skuDeleteDate = skuDeleteDate;
		}
		public String getSkuFsIntroYear() {
			return skuFsIntroYear;
		}
		public void setSkuFsIntroYear(String skuFsIntroYear) {
			this.skuFsIntroYear = skuFsIntroYear;
		}
		public String getSkuFsIntroDate() {
			return skuFsIntroDate;
		}
		public void setSkuFsIntroDate(String skuFsIntroDate) {
			this.skuFsIntroDate = skuFsIntroDate;
		}
		public String getStyleFsIntroDate() {
			return StyleFsIntroDate;
		}
		public void setStyleFsIntroDate(String styleFsIntroDate) {
			StyleFsIntroDate = styleFsIntroDate;
		}
		public String getSkuFSDeleteDate() {
			return skuFSDeleteDate;
		}
		public void setSkuFSDeleteDate(String skuFSDeleteDate) {
			this.skuFSDeleteDate = skuFSDeleteDate;
		}
		public String getColorFamily() {
			return colorFamily;
		}
		public void setColorFamily(String colorFamily) {
			this.colorFamily = colorFamily;
		}
		public String getColorName() {
			return colorName;
		}
		public void setColorName(String colorName) {
			this.colorName = colorName;
		}
		public String getColorUniqueID() {
			return colorUniqueID;
		}
		public void setColorUniqueID(String colorUniqueID) {
			this.colorUniqueID = colorUniqueID;
		}
		public String getMaterial() {
			return material;
		}
		public void setMaterial(String material) {
			this.material = material;
		}
		public String getSilhouette() {
			return silhouette;
		}
		public void setSilhouette(String silhouette) {
			this.silhouette = silhouette;
		}
		public String getHardwareColor() {
			return hardwareColor;
		}
		public void setHardwareColor(String hardwareColor) {
			this.hardwareColor = hardwareColor;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public String getSpecialProduct() {
			return specialProduct;
		}
		public void setSpecialProduct(String specialProduct) {
			this.specialProduct = specialProduct;
		}
		public String getAbcCode() {
			return abcCode;
		}
		public void setAbcCode(String abcCode) {
			this.abcCode = abcCode;
		}
		public String getPlanExclusion() {
			return planExclusion;
		}
		public void setPlanExclusion(String planExclusion) {
			this.planExclusion = planExclusion;
		}
		public String getChannelExclusive() {
			return channelExclusive;
		}
		public void setChannelExclusive(String channelExclusive) {
			this.channelExclusive = channelExclusive;
		}
		public String getStyleGroup() {
			return StyleGroup;
		}
		public void setStyleGroup(String styleGroup) {
			StyleGroup = styleGroup;
		}
		public String getParentStyle() {
			return parentStyle;
		}
		public void setParentStyle(String parentStyle) {
			this.parentStyle = parentStyle;
		}
		public String getSignatureType() {
			return signatureType;
		}
		public void setSignatureType(String signatureType) {
			this.signatureType = signatureType;
		}
		public String getMpg() {
			return mpg;
		}
		public void setMpg(String mpg) {
			this.mpg = mpg;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getUpcCode() {
			return upcCode;
		}
		public void setUpcCode(String upcCode) {
			this.upcCode = upcCode;
		}
		public String getExotic() {
			return exotic;
		}
		public void setExotic(String exotic) {
			this.exotic = exotic;
		}
		public String getSubCollection() {
			return subCollection;
		}
		public void setSubCollection(String subCollection) {
			this.subCollection = subCollection;
		}
		public String getSizeScale() {
			return sizeScale;
		}
		public void setSizeScale(String sizeScale) {
			this.sizeScale = sizeScale;
		}
		public String getPint() {
			return pint;
		}
		public void setPint(String pint) {
			this.pint = pint;
		}
		public String getSoleType() {
			return soleType;
		}
		public void setSoleType(String soleType) {
			this.soleType = soleType;
		}
		public String getToeType() {
			return toeType;
		}
		public void setToeType(String toeType) {
			this.toeType = toeType;
		}
		public String getToeShape() {
			return toeShape;
		}
		public void setToeShape(String toeShape) {
			this.toeShape = toeShape;
		}
		public String getHeelHeight1() {
			return heelHeight1;
		}
		public void setHeelHeight1(String heelHeight1) {
			this.heelHeight1 = heelHeight1;
		}
		public String getWatchCaseSize() {
			return watchCaseSize;
		}
		public void setWatchCaseSize(String watchCaseSize) {
			this.watchCaseSize = watchCaseSize;
		}
		public String getFunctionality() {
			return functionality;
		}
		public void setFunctionality(String functionality) {
			this.functionality = functionality;
		}
		public String getFaceColor() {
			return faceColor;
		}
		public void setFaceColor(String faceColor) {
			this.faceColor = faceColor;
		}
		public String getWatchCaseShape() {
			return watchCaseShape;
		}
		public void setWatchCaseShape(String watchCaseShape) {
			this.watchCaseShape = watchCaseShape;
		}
		public String getLens() {
			return lens;
		}
		public void setLens(String lens) {
			this.lens = lens;
		}
		public String getProductIdentity() {
			return productIdentity;
		}
		public void setProductIdentity(String productIdentity) {
			this.productIdentity = productIdentity;
		}
		public String getAttitude() {
			return attitude;
		}
		public void setAttitude(String attitude) {
			this.attitude = attitude;
		}
		public String getMerDesc() {
			return merDesc;
		}
		public void setMerDesc(String merDesc) {
			this.merDesc = merDesc;
		}
		public String getLicensed() {
			return licensed;
		}
		public void setLicensed(String licensed) {
			this.licensed = licensed;
		}
		public String getStaticLine() {
			return staticLine;
		}
		public void setStaticLine(String staticLine) {
			this.staticLine = staticLine;
		}
		public String getDeptLevelRow() {
			return deptLevelRow;
		}
		public void setDeptLevelRow(String deptLevelRow) {
			this.deptLevelRow = deptLevelRow;
		}
		
		public String getSeasonGroupName() {
			return seasonGroupName;
		}
		public void setSeasonGroupName(String seasonGroupName) {
			this.seasonGroupName = seasonGroupName;
		}
		public String getFsSeasonGroupName() {
			return fsSeasonGroupName;
		}
		public void setFsSeasonGroupName(String fsSeasonGroupName) {
			this.fsSeasonGroupName = fsSeasonGroupName;
		}
		public String getDeptCode() {
			return deptCode;
		}
		public void setDeptCode(String deptCode) {
			this.deptCode = deptCode;
		}
		public String getDeptDesc() {
			return deptDesc;
		}
		public void setDeptDesc(String deptDesc) {
			this.deptDesc = deptDesc;
		}
		public String getClassCode() {
			return classCode;
		}
		public void setClassCode(String classCode) {
			this.classCode = classCode;
		}
		public String getClassDesc() {
			return classDesc;
		}
		public void setClassDesc(String classDesc) {
			this.classDesc = classDesc;
		}
		public String getSubClassCode() {
			return subClassCode;
		}
		public void setSubClassCode(String subClassCode) {
			this.subClassCode = subClassCode;
		}
		public String getSubClassDesc() {
			return subClassDesc;
		}
		public void setSubClassDesc(String subClassDesc) {
			this.subClassDesc = subClassDesc;
		}
		public String getCollectionDesc() {
			return collectionDesc;
		}
		public void setCollectionDesc(String collectionDesc) {
			this.collectionDesc = collectionDesc;
		}
		public String getCollectionCode() {
			return collectionCode;
		}
		public void setCollectionCode(String collectionCode) {
			this.collectionCode = collectionCode;
		}
		public String getSkuIntroQuaterStr() {
			return skuIntroQuaterStr;
		}
		public void setSkuIntroQuaterStr(String skuIntroQuaterStr) {
			this.skuIntroQuaterStr = skuIntroQuaterStr;
		}
		public String getSkuIntroYearStr() {
			return skuIntroYearStr;
		}
		public void setSkuIntroYearStr(String skuIntroYearStr) {
			this.skuIntroYearStr = skuIntroYearStr;
		}
		
		public String getSkuDeleteQuaterStr() {
			return SkuDeleteQuaterStr;
		}
		public void setSkuDeleteQuaterStr(String skuDeleteQuaterStr) {
			SkuDeleteQuaterStr = skuDeleteQuaterStr;
		}
		public String getSkuFsDeleteQuaterStr() {
			return SkuFsDeleteQuaterStr;
		}
		public void setSkuFsDeleteQuaterStr(String skuFsDeleteQuaterStr) {
			SkuFsDeleteQuaterStr = skuFsDeleteQuaterStr;
		}
		public String getSkuDeleteYearStr() {
			return skuDeleteYearStr;
		}
		public void setSkuDeleteYearStr(String skuDeleteYearStr) {
			this.skuDeleteYearStr = skuDeleteYearStr;
		}
		public String getSkuFsDeleteYearStr() {
			return skuFsDeleteYearStr;
		}
		public void setSkuFsDeleteYearStr(String skuFsDeleteYearStr) {
			this.skuFsDeleteYearStr = skuFsDeleteYearStr;
		}
		public String getSkuIntroDateStr() {
			return skuIntroDateStr;
		}
		public void setSkuIntroDateStr(String skuIntroDateStr) {
			this.skuIntroDateStr = skuIntroDateStr;
		}
		public String getSkuFsIntroDateStr() {
			return skuFsIntroDateStr;
		}
		public void setSkuFsIntroDateStr(String skuFsIntroDateStr) {
			this.skuFsIntroDateStr = skuFsIntroDateStr;
		}
		public String getSkuDeleteDateStr() {
			return skuDeleteDateStr;
		}
		public void setSkuDeleteDateStr(String skuDeleteDateStr) {
			this.skuDeleteDateStr = skuDeleteDateStr;
		}
		public String getSkuFsDeleteDateStr() {
			return skuFsDeleteDateStr;
		}
		public void setSkuFsDeleteDateStr(String skuFsDeleteDateStr) {
			this.skuFsDeleteDateStr = skuFsDeleteDateStr;
		}
		public String getSkuIntroCalenderDate() {
			return skuIntroCalenderDate;
		}
		public void setSkuIntroCalenderDate(String skuIntroCalenderDate) {
			this.skuIntroCalenderDate = skuIntroCalenderDate;
		}
		public String getSkuFsIntroCalenderDate() {
			return skuFsIntroCalenderDate;
		}
		public void setSkuFsIntroCalenderDate(String skuFsIntroCalenderDate) {
			this.skuFsIntroCalenderDate = skuFsIntroCalenderDate;
		}
		public String getSkuDeleteCalenderDate() {
			return skuDeleteCalenderDate;
		}
		public void setSkuDeleteCalenderDate(String skuDeleteCalenderDate) {
			this.skuDeleteCalenderDate = skuDeleteCalenderDate;
		}
		public String getSkuFsDeleteCalenderDate() {
			return skuFsDeleteCalenderDate;
		}
		public void setSkuFsDeleteCalenderDate(String skuFsDeleteCalenderDate) {
			this.skuFsDeleteCalenderDate = skuFsDeleteCalenderDate;
		}
		public String getStyleIntroCalenderDate() {
			return styleIntroCalenderDate;
		}
		public void setStyleIntroCalenderDate(String styleIntroCalenderDate) {
			this.styleIntroCalenderDate = styleIntroCalenderDate;
		}
		public String getStyleFsIntroCalenderDate() {
			return styleFsIntroCalenderDate;
		}
		public void setStyleFsIntroCalenderDate(String styleFsIntroCalenderDate) {
			this.styleFsIntroCalenderDate = styleFsIntroCalenderDate;
		}
		public String getStyleDeleteCalenderDate() {
			return styleDeleteCalenderDate;
		}
		public void setStyleDeleteCalenderDate(String styleDeleteCalenderDate) {
			this.styleDeleteCalenderDate = styleDeleteCalenderDate;
		}
		public String getStyleFsDeleteCalenderDate() {
			return styleFsDeleteCalenderDate;
		}
		public void setStyleFsDeleteCalenderDate(String styleFsDeleteCalenderDate) {
			this.styleFsDeleteCalenderDate = styleFsDeleteCalenderDate;
		}
		public String getStyleIntroYearStr() {
			return styleIntroYearStr;
		}
		public void setStyleIntroYearStr(String styleIntroYearStr) {
			this.styleIntroYearStr = styleIntroYearStr;
		}
		public String getStyleFsIntroYearStr() {
			return styleFsIntroYearStr;
		}
		public void setStyleFsIntroYearStr(String styleFsIntroYearStr) {
			this.styleFsIntroYearStr = styleFsIntroYearStr;
		}
		public String getStyleIntroQuaterStr() {
			return styleIntroQuaterStr;
		}
		public void setStyleIntroQuaterStr(String styleIntroQuaterStr) {
			this.styleIntroQuaterStr = styleIntroQuaterStr;
		}
		public String getStyleIntroDateStr() {
			return styleIntroDateStr;
		}
		public void setStyleIntroDateStr(String styleIntroDateStr) {
			this.styleIntroDateStr = styleIntroDateStr;
		}
		public String getStyleNumPadded() {
			return styleNumPadded;
		}
		public void setStyleNumPadded(String styleNumPadded) {
			this.styleNumPadded = styleNumPadded;
		}
		public String getSize() {
			return size;
		}
		public void setSize(String size) {
			this.size = size;
		}
		public String getStyleDeleteYearStr() {
			return styleDeleteYearStr;
		}
		public void setStyleDeleteYearStr(String styleDeleteYearStr) {
			this.styleDeleteYearStr = styleDeleteYearStr;
		}
		public String getStyleFsDeleteYearStr() {
			return styleFsDeleteYearStr;
		}
		public void setStyleFsDeleteYearStr(String styleFsDeleteYearStr) {
			this.styleFsDeleteYearStr = styleFsDeleteYearStr;
		}
		public String getStyleDeleteQuaterStr() {
			return styleDeleteQuaterStr;
		}
		public void setStyleDeleteQuaterStr(String styleDeleteQuaterStr) {
			this.styleDeleteQuaterStr = styleDeleteQuaterStr;
		}
		public String getStyleDeleteDateStr() {
			return styleDeleteDateStr;
		}
		public void setStyleDeleteDateStr(String styleDeleteDateStr) {
			this.styleDeleteDateStr = styleDeleteDateStr;
		}
		public String getStyleFsDeleteDateStr() {
			return styleFsDeleteDateStr;
		}
		public void setStyleFsDeleteDateStr(String styleFsDeleteDateStr) {
			this.styleFsDeleteDateStr = styleFsDeleteDateStr;
		}
		public String getSkuFsIntroYearStr() {
			return skuFsIntroYearStr;
		}
		public void setSkuFsIntroYearStr(String skuFsIntroYearStr) {
			this.skuFsIntroYearStr = skuFsIntroYearStr;
		}
		public String getSkuFsIntroQuaterStr() {
			return skuFsIntroQuaterStr;
		}
		public void setSkuFsIntroQuaterStr(String skuFsIntroQuaterStr) {
			this.skuFsIntroQuaterStr = skuFsIntroQuaterStr;
		}
		public String getStyleFsIntroQuaterStr() {
			return styleFsIntroQuaterStr;
		}
		public void setStyleFsIntroQuaterStr(String styleFsIntroQuaterStr) {
			this.styleFsIntroQuaterStr = styleFsIntroQuaterStr;
		}
		public String getStyleFsIntroDateStr() {
			return styleFsIntroDateStr;
		}
		public void setStyleFsIntroDateStr(String styleFsIntroDateStr) {
			this.styleFsIntroDateStr = styleFsIntroDateStr;
		}
		public String getStyleFsDeleteQuaterStr() {
			return styleFsDeleteQuaterStr;
		}
		public void setStyleFsDeleteQuaterStr(String styleFsDeleteQuaterStr) {
			this.styleFsDeleteQuaterStr = styleFsDeleteQuaterStr;
		}
		public String getMaterialName() {
			return materialName;
		}
		public void setMaterialName(String materialName) {
			this.materialName = materialName;
		}
		public String getSilhouetteDesc() {
			return silhouetteDesc;
		}
		public void setSilhouetteDesc(String silhouetteDesc) {
			this.silhouetteDesc = silhouetteDesc;
		}
		public String getGenderDesc() {
			return genderDesc;
		}
		public void setGenderDesc(String genderDesc) {
			this.genderDesc = genderDesc;
		}
		public String getHardwareColorDesc() {
			return hardwareColorDesc;
		}
		public void setHardwareColorDesc(String hardwareColorDesc) {
			this.hardwareColorDesc = hardwareColorDesc;
		}
		public String getSpecialProductDesc() {
			return specialProductDesc;
		}
		public void setSpecialProductDesc(String specialProductDesc) {
			this.specialProductDesc = specialProductDesc;
		}
		public String getAbcCodeDesc() {
			return abcCodeDesc;
		}
		public void setAbcCodeDesc(String abcCodeDesc) {
			this.abcCodeDesc = abcCodeDesc;
		}
		public String getParentStyleDesc() {
			return parentStyleDesc;
		}
		public void setParentStyleDesc(String parentStyleDesc) {
			this.parentStyleDesc = parentStyleDesc;
		}
		public String getUpdated() {
			return updated;
		}
		public void setUpdated(String updated) {
			this.updated = updated;
		}
		public String getFactoryType() {
			return factoryType;
		}
		public void setFactoryType(String factoryType) {
			this.factoryType = factoryType;
		}
		public String getShortFactoryType() {
			return shortFactoryType;
		}
		public void setShortFactoryType(String shortFactoryType) {
			this.shortFactoryType = shortFactoryType;
		}
		public String getSubCollectionDesc() {
			return subCollectionDesc;
		}
		public void setSubCollectionDesc(String subCollectionDesc) {
			this.subCollectionDesc = subCollectionDesc;
		}
		public String getSizeIntLevel() {
			return sizeIntLevel;
		}
		public void setSizeIntLevel(String sizeIntLevel) {
			this.sizeIntLevel = sizeIntLevel;
		}
		public String getCurrentCost() {
			return currentCost;
		}
		public void setCurrentCost(String currentCost) {
			this.currentCost = currentCost;
		}
		public String getSkuNamePadded() {
			return skuNamePadded;
		}
		public void setSkuNamePadded(String skuNamePadded) {
			this.skuNamePadded = skuNamePadded;
		}
		public String getSizePadded() {
			return sizePadded;
		}
		public void setSizePadded(String sizePadded) {
			this.sizePadded = sizePadded;
		}
		
		public String getEmbellishment() {
			return embellishment;
		}
		public void setEmbellishment(String embellishment) {
			this.embellishment = embellishment;
		}
		public String getMaterialGroupName() {
			return materialGroupName;
		}
		public void setMaterialGroupName(String materialGroupName) {
			this.materialGroupName = materialGroupName;
		}
		public String getMaterialGroupDesc() {
			return materialGroupDesc;
		}
		public void setMaterialGroupDesc(String materialGroupDesc) {
			this.materialGroupDesc = materialGroupDesc;
		}
		public String getHandBagSizeDesc() {
			return handBagSizeDesc;
		}
		public void setHandBagSizeDesc(String handBagSizeDesc) {
			this.handBagSizeDesc = handBagSizeDesc;
		}
		public String getHandBagSize() {
			return handBagSize;
		}
		public void setHandBagSize(String handBagSize) {
			this.handBagSize = handBagSize;
		}
		public String getDefaultIndicator() {
			return defaultIndicator;
		}
		public void setDefaultIndicator(String defaultIndicator) {
			this.defaultIndicator = defaultIndicator;
		}


		public String getTargetCost() {
			return targetCost;
		}


		public void setTargetCost(String targetCost) {
			this.targetCost = targetCost;
		}


		public String getSkuTotalCost() {
			return skuTotalCost;
		}


		public void setSkuTotalCost(String skuTotalCost) {
			this.skuTotalCost = skuTotalCost;
		}


		public String getSkuAvgWorkingCost1() {
			return skuAvgWorkingCost1;
		}


		public void setSkuAvgWorkingCost1(String skuAvgWorkingCost1) {
			this.skuAvgWorkingCost1 = skuAvgWorkingCost1;
		}


		public String getSignatureDesc() {
			return signatureDesc;
		}


		public void setSignatureDesc(String signatureDesc) {
			this.signatureDesc = signatureDesc;
		}


		public String getProdStdCost() {
			return prodStdCost;
		}


		public void setProdStdCost(String prodStdCost) {
			this.prodStdCost = prodStdCost;
		}


		public String getSkuDeleteYear() {
			return skuDeleteYear;
		}


		public void setSkuDeleteYear(String skuDeleteYear) {
			this.skuDeleteYear = skuDeleteYear;
		}


		public String getStyleDeleteYear() {
			return styleDeleteYear;
		}


		public void setStyleDeleteYear(String styleDeleteYear) {
			this.styleDeleteYear = styleDeleteYear;
		}


		public String getStyleIntroYear() {
			return styleIntroYear;
		}


		public void setStyleIntroYear(String styleIntroYear) {
			this.styleIntroYear = styleIntroYear;
		}


		public String getStyleFsIntroYear() {
			return styleFsIntroYear;
		}


		public void setStyleFsIntroYear(String styleFsIntroYear) {
			this.styleFsIntroYear = styleFsIntroYear;
		}


		public String getSkuFsDeleteYear() {
			return skuFsDeleteYear;
		}


		public void setSkuFsDeleteYear(String skuFsDeleteYear) {
			this.skuFsDeleteYear = skuFsDeleteYear;
		}


		public String getStyleFsDeleteYear() {
			return styleFsDeleteYear;
		}


		public void setStyleFsDeleteYear(String styleFsDeleteYear) {
			this.styleFsDeleteYear = styleFsDeleteYear;
		}


		public String getStyleDeleteDate() {
			return styleDeleteDate;
		}


		public void setStyleDeleteDate(String styleDeleteDate) {
			this.styleDeleteDate = styleDeleteDate;
		}


		public String getSkuNameImg() {
			return skuNameImg;
		}


		public void setSkuNameImg(String skuNameImg) {
			this.skuNameImg = skuNameImg;
		}


		public String getCrossBodyDesc() {
			return crossBodyDesc;
		}


		public void setCrossBodyDesc(String crossBodyDesc) {
			this.crossBodyDesc = crossBodyDesc;
		}


		public String getSkuMaterial() {
			return skuMaterial;
		}


		public void setSkuMaterial(String skuMaterial) {
			this.skuMaterial = skuMaterial;
		}


		public String getSkuSignatureType() {
			return skuSignatureType;
		}


		public void setSkuSignatureType(String skuSignatureType) {
			this.skuSignatureType = skuSignatureType;
		}


		public String getOriginalRetailPrice() {
			return originalRetailPrice;
		}


		public void setOriginalRetailPrice(String originalRetailPrice) {
			this.originalRetailPrice = originalRetailPrice;
		}


		public String getSkuUniqueId() {
			return skuUniqueId;
		}


		public void setSkuUniqueId(String skuUniqueId) {
			this.skuUniqueId = skuUniqueId;
		}


	
		
		
		
	
}
