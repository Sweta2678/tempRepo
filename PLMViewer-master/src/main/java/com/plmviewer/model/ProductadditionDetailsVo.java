/**
 * 
 */
package com.plmviewer.model;

import java.math.BigDecimal;


/**
 * @author uthanasekarapandian
 *
 */
public class ProductadditionDetailsVo {
private String styleName;
	
	
	
	private String abbrStyleName ;

	private String styleNumber;
	
	private String department;
	private String collection;
	
    private String productClass;
	
	private String adopted;
	private String gender;
	

    private String deleteDate;////(select s.name from seasongroupmaster s where s.ida2a2 = prodarev.att15) deleteDate,
	

    private String subCollection;
    private String subClass;
	
	
    private String earlyReleaseDate;

    private String  introDate;//(select s.name from seasongroupmaster s where s.ida2a2 = prodarev.att27) introDate,
	 private String  watchCaseSize;
	 private String  watchCaseShape;
	private  String function;
	
	private String facecolor;
	private String additionalFeatures;
	
	private BigDecimal additive;
	private String adoptedDesignOption;
	private String attitude;
	private String attitudinalSegments;
	private BigDecimal averageCost;
	private BigDecimal  averageCurrentCost;
	private BigDecimal  averageFinalStandardCost;
	private BigDecimal  averagestdCostWsaleMUPct;
	private BigDecimal  averageWorkingCost1;
	private BigDecimal  averageWorkingCost2;
	private String  benchStyleForSilhoutte;
	private String  benchStyleforTargetMargin;
	private String  CalcWholeSale1;
	private String   careCardCm;
	private String   channelExclusive;
	private String   collaboration;
	private String   costAgreement;
	private String dampCloth;
	
	private String   designRequestComments;
    private String  emblishment;
	private String fabricCleaner;
	private String factorystoreTypeProduct;
	private BigDecimal freightCost;

	private String fsDeleteDate;//(select s.name from seasongroupmaster s where s.ida2a2 = prodarev.att23) 
	private String  fsIntroDate;	//(select s.name from seasongroupmaster s where s.ida2a2 = prodarev.att24)
	private BigDecimal fsPromoPrice;
	private BigDecimal fsTicketPrice;
	private BigDecimal fxRate;
	private String gcc;
	
	private String gifting;
	private String hardwareColor;
	private String jewelryDimensions;
	private BigDecimal jpYenRetail;
	private BigDecimal jpYenRetailWithTax;
	private String leatherCleaner;
	private String leatherMoisturizer;
	private String longLeadTimeMaterial;
	private String look;
	private String material;
	private String MerchCJIPrices;
	private String merchDropped;
	private String MerchFactoryPrices;
	private String oneTimeBuy;
	private BigDecimal overhead;
	private String parentStyle;
	private BigDecimal pctOffRetail;
	private BigDecimal pctOffTicket;
	private String pint;
	private String planExclusion;
	private BigDecimal poCost;
	private BigDecimal premiumRate;

	private String productidentity;
	private String productSegmentation;
	private String proposedStyleNumber;
	private BigDecimal retailPrice;
	private String rounding;
	private String signatureType;
	private String silhouette;
	private String slot;
	private String specialProduct;
	private BigDecimal stdFSPromoGMPct;
	private BigDecimal stdFSTicketGMPct;

	private BigDecimal targetCost;
	private BigDecimal targetFSPromoGMPct;
	private BigDecimal targetFSTargetGMPct;
	private BigDecimal targetRetailGMPct;
	private String team;
	

	private String  closures;
	private String  coin;
	private String  creditCardPocket;
	private String  crossBody;
	private String  handbagSize;
	private String  idwindow;
	private String  keyringDimensions;
	private String  organizationalPanel;
	private BigDecimal  sampleFacility;
	private String  styleGroup ;

	private String  toeshape;
	private String  soletype;
	private String  toetype;
	private String  heelHeight1;
	 

	private String teamAD;
	private String teamAsstAD;
	private String teamAsstDD;
	private String teamAsstDesigner;
	private String teamDD;
	private String teamDesigner1;
	private String teamDesigner2;
	private String teamEngineer;
	private String teamMerchant1;
	private String teamMerchant2;
	private String teamPreProduction;
	private String teamProduction;
	private String teamTechnicalDesigner;
	private String totalCost;
	private String totoBillfoldPockets;
   private String totoCheckbook;
	private String totoCollection;
	private String totoColors;
	private BigDecimal totoDropLengthIn;
	private String totoExteriorPocket;
	private BigDecimal totoHeightCm;
	private String totoInteriorPocket;
	private BigDecimal totoLengthCm;
	private String totoLiningMaterial;
	private String totoShellMaterial;
	private String totoSizes;
	private BigDecimal totoStrapLengthIn;
	private String totoTrimMaterial;
	private String totoTypeOfSole;
	private BigDecimal totoWidthCm;
	private BigDecimal tradeMUPct;
	private String updated;
	private BigDecimal wholesaleMUPct;

	private BigDecimal wholesalePrice;
	private BigDecimal workingCost1FSPromoGMPct;
	private BigDecimal workingCost1FSTargetGMPct;
	private BigDecimal workingCost1RetailGMPct;
	private String upperMaterial;
	private String sizeScale;
	private String pdtType;
	private String lens;

	/**
	 * @return the styleName
	 */
	public String getStyleName() {
		return styleName;
	}
	/**
	 * @param styleName the styleName to set
	 */
	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}
	/**
	 * @return the abbrStyleName
	 */
	public String getAbbrStyleName() {
		return abbrStyleName;
	}
	/**
	 * @param abbrStyleName the abbrStyleName to set
	 */
	public void setAbbrStyleName(String abbrStyleName) {
		this.abbrStyleName = abbrStyleName;
	}
	/**
	 * @return the styleNumber
	 */
	public String getStyleNumber() {
		return styleNumber;
	}
	/**
	 * @param styleNumber the styleNumber to set
	 */
	public void setStyleNumber(String styleNumber) {
		this.styleNumber = styleNumber;
	}
	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}
	/**
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}
	/**
	 * @return the collection
	 */
	public String getCollection() {
		return collection;
	}
	/**
	 * @param collection the collection to set
	 */
	public void setCollection(String collection) {
		this.collection = collection;
	}
	/**
	 * @return the productClass
	 */
	public String getProductClass() {
		return productClass;
	}
	/**
	 * @param productClass the productClass to set
	 */
	public void setProductClass(String productClass) {
		this.productClass = productClass;
	}
	/**
	 * @return the adopted
	 */
	public String getAdopted() {
		return adopted;
	}
	/**
	 * @param adopted the adopted to set
	 */
	public void setAdopted(String adopted) {
		this.adopted = adopted;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return the deleteDate
	 */
	public String getDeleteDate() {
		return deleteDate;
	}
	/**
	 * @param deleteDate the deleteDate to set
	 */
	public void setDeleteDate(String deleteDate) {
		this.deleteDate = deleteDate;
	}
	/**
	 * @return the subCollection
	 */
	public String getSubCollection() {
		return subCollection;
	}
	/**
	 * @param subCollection the subCollection to set
	 */
	public void setSubCollection(String subCollection) {
		this.subCollection = subCollection;
	}
	/**
	 * @return the subClass
	 */
	public String getSubClass() {
		return subClass;
	}
	/**
	 * @param subClass the subClass to set
	 */
	public void setSubClass(String subClass) {
		this.subClass = subClass;
	}
	/**
	 * @return the earlyReleaseDate
	 */
	public String getEarlyReleaseDate() {
		return earlyReleaseDate;
	}
	/**
	 * @param earlyReleaseDate the earlyReleaseDate to set
	 */
	public void setEarlyReleaseDate(String earlyReleaseDate) {
		this.earlyReleaseDate = earlyReleaseDate;
	}
	/**
	 * @return the introDate
	 */
	public String getIntroDate() {
		return introDate;
	}
	/**
	 * @param introDate the introDate to set
	 */
	public void setIntroDate(String introDate) {
		this.introDate = introDate;
	}
	/**
	 * @return the watchCaseSize
	 */
	public String getWatchCaseSize() {
		return watchCaseSize;
	}
	/**
	 * @param watchCaseSize the watchCaseSize to set
	 */
	public void setWatchCaseSize(String watchCaseSize) {
		this.watchCaseSize = watchCaseSize;
	}
	/**
	 * @return the watchCaseShape
	 */
	public String getWatchCaseShape() {
		return watchCaseShape;
	}
	/**
	 * @param watchCaseShape the watchCaseShape to set
	 */
	public void setWatchCaseShape(String watchCaseShape) {
		this.watchCaseShape = watchCaseShape;
	}
	/**
	 * @return the function
	 */
	public String getFunction() {
		return function;
	}
	/**
	 * @param function the function to set
	 */
	public void setFunction(String function) {
		this.function = function;
	}
	/**
	 * @return the facecolor
	 */
	public String getFacecolor() {
		return facecolor;
	}
	/**
	 * @param facecolor the facecolor to set
	 */
	public void setFacecolor(String facecolor) {
		this.facecolor = facecolor;
	}
	/**
	 * @return the additionalFeatures
	 */
	public String getAdditionalFeatures() {
		return additionalFeatures;
	}
	/**
	 * @param additionalFeatures the additionalFeatures to set
	 */
	public void setAdditionalFeatures(String additionalFeatures) {
		this.additionalFeatures = additionalFeatures;
	}
	/**
	 * @return the additive
	 */
	public BigDecimal getAdditive() {
		return additive;
	}
	/**
	 * @param additive the additive to set
	 */
	public void setAdditive(BigDecimal additive) {
		this.additive = additive;
	}
	/**
	 * @return the adoptedDesignOption
	 */
	public String getAdoptedDesignOption() {
		return adoptedDesignOption;
	}
	/**
	 * @param adoptedDesignOption the adoptedDesignOption to set
	 */
	public void setAdoptedDesignOption(String adoptedDesignOption) {
		this.adoptedDesignOption = adoptedDesignOption;
	}
	/**
	 * @return the attitude
	 */
	public String getAttitude() {
		return attitude;
	}
	/**
	 * @param attitude the attitude to set
	 */
	public void setAttitude(String attitude) {
		this.attitude = attitude;
	}
	/**
	 * @return the attitudinalSegments
	 */
	public String getAttitudinalSegments() {
		return attitudinalSegments;
	}
	/**
	 * @param attitudinalSegments the attitudinalSegments to set
	 */
	public void setAttitudinalSegments(String attitudinalSegments) {
		this.attitudinalSegments = attitudinalSegments;
	}
	/**
	 * @return the averageCost
	 */
	public BigDecimal getAverageCost() {
		return averageCost;
	}
	/**
	 * @param averageCost the averageCost to set
	 */
	public void setAverageCost(BigDecimal averageCost) {
		this.averageCost = averageCost;
	}
	/**
	 * @return the averageCurrentCost
	 */
	public BigDecimal getAverageCurrentCost() {
		return averageCurrentCost;
	}
	/**
	 * @param averageCurrentCost the averageCurrentCost to set
	 */
	public void setAverageCurrentCost(BigDecimal averageCurrentCost) {
		this.averageCurrentCost = averageCurrentCost;
	}
	/**
	 * @return the averageFinalStandardCost
	 */
	public BigDecimal getAverageFinalStandardCost() {
		return averageFinalStandardCost;
	}
	/**
	 * @param averageFinalStandardCost the averageFinalStandardCost to set
	 */
	public void setAverageFinalStandardCost(BigDecimal averageFinalStandardCost) {
		this.averageFinalStandardCost = averageFinalStandardCost;
	}
	/**
	 * @return the averagestdCostWsaleMUPct
	 */
	public BigDecimal getAveragestdCostWsaleMUPct() {
		return averagestdCostWsaleMUPct;
	}
	/**
	 * @param averagestdCostWsaleMUPct the averagestdCostWsaleMUPct to set
	 */
	public void setAveragestdCostWsaleMUPct(BigDecimal averagestdCostWsaleMUPct) {
		this.averagestdCostWsaleMUPct = averagestdCostWsaleMUPct;
	}
	/**
	 * @return the averageWorkingCost1
	 */
	public BigDecimal getAverageWorkingCost1() {
		return averageWorkingCost1;
	}
	/**
	 * @param averageWorkingCost1 the averageWorkingCost1 to set
	 */
	public void setAverageWorkingCost1(BigDecimal averageWorkingCost1) {
		this.averageWorkingCost1 = averageWorkingCost1;
	}
	/**
	 * @return the averageWorkingCost2
	 */
	public BigDecimal getAverageWorkingCost2() {
		return averageWorkingCost2;
	}
	/**
	 * @param averageWorkingCost2 the averageWorkingCost2 to set
	 */
	public void setAverageWorkingCost2(BigDecimal averageWorkingCost2) {
		this.averageWorkingCost2 = averageWorkingCost2;
	}
	/**
	 * @return the benchStyleForSilhoutte
	 */
	public String getBenchStyleForSilhoutte() {
		return benchStyleForSilhoutte;
	}
	/**
	 * @param benchStyleForSilhoutte the benchStyleForSilhoutte to set
	 */
	public void setBenchStyleForSilhoutte(String benchStyleForSilhoutte) {
		this.benchStyleForSilhoutte = benchStyleForSilhoutte;
	}
	/**
	 * @return the benchStyleforTargetMargin
	 */
	public String getBenchStyleforTargetMargin() {
		return benchStyleforTargetMargin;
	}
	/**
	 * @param benchStyleforTargetMargin the benchStyleforTargetMargin to set
	 */
	public void setBenchStyleforTargetMargin(String benchStyleforTargetMargin) {
		this.benchStyleforTargetMargin = benchStyleforTargetMargin;
	}
	/**
	 * @return the calcWholeSale1
	 */
	public String getCalcWholeSale1() {
		return CalcWholeSale1;
	}
	/**
	 * @param calcWholeSale1 the calcWholeSale1 to set
	 */
	public void setCalcWholeSale1(String calcWholeSale1) {
		CalcWholeSale1 = calcWholeSale1;
	}
	/**
	 * @return the careCardCm
	 */
	public String getCareCardCm() {
		return careCardCm;
	}
	/**
	 * @param careCardCm the careCardCm to set
	 */
	public void setCareCardCm(String careCardCm) {
		this.careCardCm = careCardCm;
	}
	/**
	 * @return the channelExclusive
	 */
	public String getChannelExclusive() {
		return channelExclusive;
	}
	/**
	 * @param channelExclusive the channelExclusive to set
	 */
	public void setChannelExclusive(String channelExclusive) {
		this.channelExclusive = channelExclusive;
	}
	/**
	 * @return the collaboration
	 */
	public String getCollaboration() {
		return collaboration;
	}
	/**
	 * @param collaboration the collaboration to set
	 */
	public void setCollaboration(String collaboration) {
		this.collaboration = collaboration;
	}
	/**
	 * @return the costAgreement
	 */
	public String getCostAgreement() {
		return costAgreement;
	}
	/**
	 * @param costAgreement the costAgreement to set
	 */
	public void setCostAgreement(String costAgreement) {
		this.costAgreement = costAgreement;
	}
	/**
	 * @return the dampCloth
	 */
	public String getDampCloth() {
		return dampCloth;
	}
	/**
	 * @param dampCloth the dampCloth to set
	 */
	public void setDampCloth(String dampCloth) {
		this.dampCloth = dampCloth;
	}
	/**
	 * @return the designRequestComments
	 */
	public String getDesignRequestComments() {
		return designRequestComments;
	}
	/**
	 * @param designRequestComments the designRequestComments to set
	 */
	public void setDesignRequestComments(String designRequestComments) {
		this.designRequestComments = designRequestComments;
	}
	/**
	 * @return the emblishment
	 */
	public String getEmblishment() {
		return emblishment;
	}
	/**
	 * @param emblishment the emblishment to set
	 */
	public void setEmblishment(String emblishment) {
		this.emblishment = emblishment;
	}
	/**
	 * @return the fabricCleaner
	 */
	public String getFabricCleaner() {
		return fabricCleaner;
	}
	/**
	 * @param fabricCleaner the fabricCleaner to set
	 */
	public void setFabricCleaner(String fabricCleaner) {
		this.fabricCleaner = fabricCleaner;
	}
	/**
	 * @return the factorystoreTypeProduct
	 */
	public String getFactorystoreTypeProduct() {
		return factorystoreTypeProduct;
	}
	/**
	 * @param factorystoreTypeProduct the factorystoreTypeProduct to set
	 */
	public void setFactorystoreTypeProduct(String factorystoreTypeProduct) {
		this.factorystoreTypeProduct = factorystoreTypeProduct;
	}
	/**
	 * @return the freightCost
	 */
	public BigDecimal getFreightCost() {
		return freightCost;
	}
	/**
	 * @param freightCost the freightCost to set
	 */
	public void setFreightCost(BigDecimal freightCost) {
		this.freightCost = freightCost;
	}
	/**
	 * @return the fsDeleteDate
	 */
	public String getFsDeleteDate() {
		return fsDeleteDate;
	}
	/**
	 * @param fsDeleteDate the fsDeleteDate to set
	 */
	public void setFsDeleteDate(String fsDeleteDate) {
		this.fsDeleteDate = fsDeleteDate;
	}
	/**
	 * @return the fsIntroDate
	 */
	public String getFsIntroDate() {
		return fsIntroDate;
	}
	/**
	 * @param fsIntroDate the fsIntroDate to set
	 */
	public void setFsIntroDate(String fsIntroDate) {
		this.fsIntroDate = fsIntroDate;
	}
	/**
	 * @return the fsPromoPrice
	 */
	public BigDecimal getFsPromoPrice() {
		return fsPromoPrice;
	}
	/**
	 * @param fsPromoPrice the fsPromoPrice to set
	 */
	public void setFsPromoPrice(BigDecimal fsPromoPrice) {
		this.fsPromoPrice = fsPromoPrice;
	}
	/**
	 * @return the fsTicketPrice
	 */
	public BigDecimal getFsTicketPrice() {
		return fsTicketPrice;
	}
	/**
	 * @param fsTicketPrice the fsTicketPrice to set
	 */
	public void setFsTicketPrice(BigDecimal fsTicketPrice) {
		this.fsTicketPrice = fsTicketPrice;
	}
	/**
	 * @return the fxRate
	 */
	public BigDecimal getFxRate() {
		return fxRate;
	}
	/**
	 * @param fxRate the fxRate to set
	 */
	public void setFxRate(BigDecimal fxRate) {
		this.fxRate = fxRate;
	}
	/**
	 * @return the gcc
	 */
	public String getGcc() {
		return gcc;
	}
	/**
	 * @param gcc the gcc to set
	 */
	public void setGcc(String gcc) {
		this.gcc = gcc;
	}
	/**
	 * @return the gifting
	 */
	public String getGifting() {
		return gifting;
	}
	/**
	 * @param gifting the gifting to set
	 */
	public void setGifting(String gifting) {
		this.gifting = gifting;
	}
	/**
	 * @return the hardwareColor
	 */
	public String getHardwareColor() {
		return hardwareColor;
	}
	/**
	 * @param hardwareColor the hardwareColor to set
	 */
	public void setHardwareColor(String hardwareColor) {
		this.hardwareColor = hardwareColor;
	}
	/**
	 * @return the jewelryDimensions
	 */
	public String getJewelryDimensions() {
		return jewelryDimensions;
	}
	/**
	 * @param jewelryDimensions the jewelryDimensions to set
	 */
	public void setJewelryDimensions(String jewelryDimensions) {
		this.jewelryDimensions = jewelryDimensions;
	}
	/**
	 * @return the jpYenRetail
	 */
	public BigDecimal getJpYenRetail() {
		return jpYenRetail;
	}
	/**
	 * @param jpYenRetail the jpYenRetail to set
	 */
	public void setJpYenRetail(BigDecimal jpYenRetail) {
		this.jpYenRetail = jpYenRetail;
	}
	/**
	 * @return the jpYenRetailWithTax
	 */
	public BigDecimal getJpYenRetailWithTax() {
		return jpYenRetailWithTax;
	}
	/**
	 * @param jpYenRetailWithTax the jpYenRetailWithTax to set
	 */
	public void setJpYenRetailWithTax(BigDecimal jpYenRetailWithTax) {
		this.jpYenRetailWithTax = jpYenRetailWithTax;
	}
	/**
	 * @return the leatherCleaner
	 */
	public String getLeatherCleaner() {
		return leatherCleaner;
	}
	/**
	 * @param leatherCleaner the leatherCleaner to set
	 */
	public void setLeatherCleaner(String leatherCleaner) {
		this.leatherCleaner = leatherCleaner;
	}
	/**
	 * @return the leatherMoisturizer
	 */
	public String getLeatherMoisturizer() {
		return leatherMoisturizer;
	}
	/**
	 * @param leatherMoisturizer the leatherMoisturizer to set
	 */
	public void setLeatherMoisturizer(String leatherMoisturizer) {
		this.leatherMoisturizer = leatherMoisturizer;
	}
	/**
	 * @return the longLeadTimeMaterial
	 */
	public String getLongLeadTimeMaterial() {
		return longLeadTimeMaterial;
	}
	/**
	 * @param longLeadTimeMaterial the longLeadTimeMaterial to set
	 */
	public void setLongLeadTimeMaterial(String longLeadTimeMaterial) {
		this.longLeadTimeMaterial = longLeadTimeMaterial;
	}
	/**
	 * @return the look
	 */
	public String getLook() {
		return look;
	}
	/**
	 * @param look the look to set
	 */
	public void setLook(String look) {
		this.look = look;
	}
	/**
	 * @return the material
	 */
	public String getMaterial() {
		return material;
	}
	/**
	 * @param material the material to set
	 */
	public void setMaterial(String material) {
		this.material = material;
	}
	/**
	 * @return the merchCJIPrices
	 */
	public String getMerchCJIPrices() {
		return MerchCJIPrices;
	}
	/**
	 * @param merchCJIPrices the merchCJIPrices to set
	 */
	public void setMerchCJIPrices(String merchCJIPrices) {
		MerchCJIPrices = merchCJIPrices;
	}
	/**
	 * @return the merchDropped
	 */
	public String getMerchDropped() {
		return merchDropped;
	}
	/**
	 * @param merchDropped the merchDropped to set
	 */
	public void setMerchDropped(String merchDropped) {
		this.merchDropped = merchDropped;
	}
	/**
	 * @return the merchFactoryPrices
	 */
	public String getMerchFactoryPrices() {
		return MerchFactoryPrices;
	}
	/**
	 * @param merchFactoryPrices the merchFactoryPrices to set
	 */
	public void setMerchFactoryPrices(String merchFactoryPrices) {
		MerchFactoryPrices = merchFactoryPrices;
	}
	/**
	 * @return the oneTimeBuy
	 */
	public String getOneTimeBuy() {
		return oneTimeBuy;
	}
	/**
	 * @param oneTimeBuy the oneTimeBuy to set
	 */
	public void setOneTimeBuy(String oneTimeBuy) {
		this.oneTimeBuy = oneTimeBuy;
	}
	/**
	 * @return the overhead
	 */
	public BigDecimal getOverhead() {
		return overhead;
	}
	/**
	 * @param overhead the overhead to set
	 */
	public void setOverhead(BigDecimal overhead) {
		this.overhead = overhead;
	}
	/**
	 * @return the parentStyle
	 */
	public String getParentStyle() {
		return parentStyle;
	}
	/**
	 * @param parentStyle the parentStyle to set
	 */
	public void setParentStyle(String parentStyle) {
		this.parentStyle = parentStyle;
	}
	/**
	 * @return the pctOffRetail
	 */
	public BigDecimal getPctOffRetail() {
		return pctOffRetail;
	}
	/**
	 * @param pctOffRetail the pctOffRetail to set
	 */
	public void setPctOffRetail(BigDecimal pctOffRetail) {
		this.pctOffRetail = pctOffRetail;
	}
	/**
	 * @return the pctOffTicket
	 */
	public BigDecimal getPctOffTicket() {
		return pctOffTicket;
	}
	/**
	 * @param pctOffTicket the pctOffTicket to set
	 */
	public void setPctOffTicket(BigDecimal pctOffTicket) {
		this.pctOffTicket = pctOffTicket;
	}
	/**
	 * @return the pint
	 */
	public String getPint() {
		return pint;
	}
	/**
	 * @param pint the pint to set
	 */
	public void setPint(String pint) {
		this.pint = pint;
	}
	/**
	 * @return the planExclusion
	 */
	public String getPlanExclusion() {
		return planExclusion;
	}
	/**
	 * @param planExclusion the planExclusion to set
	 */
	public void setPlanExclusion(String planExclusion) {
		this.planExclusion = planExclusion;
	}
	/**
	 * @return the poCost
	 */
	public BigDecimal getPoCost() {
		return poCost;
	}
	/**
	 * @param poCost the poCost to set
	 */
	public void setPoCost(BigDecimal poCost) {
		this.poCost = poCost;
	}
	/**
	 * @return the premiumRate
	 */
	public BigDecimal getPremiumRate() {
		return premiumRate;
	}
	/**
	 * @param premiumRate the premiumRate to set
	 */
	public void setPremiumRate(BigDecimal premiumRate) {
		this.premiumRate = premiumRate;
	}
	/**
	 * @return the productidentity
	 */
	public String getProductidentity() {
		return productidentity;
	}
	/**
	 * @param productidentity the productidentity to set
	 */
	public void setProductidentity(String productidentity) {
		this.productidentity = productidentity;
	}
	/**
	 * @return the productSegmentation
	 */
	public String getProductSegmentation() {
		return productSegmentation;
	}
	/**
	 * @param productSegmentation the productSegmentation to set
	 */
	public void setProductSegmentation(String productSegmentation) {
		this.productSegmentation = productSegmentation;
	}
	/**
	 * @return the proposedStyleNumber
	 */
	public String getProposedStyleNumber() {
		return proposedStyleNumber;
	}
	/**
	 * @param proposedStyleNumber the proposedStyleNumber to set
	 */
	public void setProposedStyleNumber(String proposedStyleNumber) {
		this.proposedStyleNumber = proposedStyleNumber;
	}
	/**
	 * @return the retailPrice
	 */
	public BigDecimal getRetailPrice() {
		return retailPrice;
	}
	/**
	 * @param retailPrice the retailPrice to set
	 */
	public void setRetailPrice(BigDecimal retailPrice) {
		this.retailPrice = retailPrice;
	}
	/**
	 * @return the rounding
	 */
	public String getRounding() {
		return rounding;
	}
	/**
	 * @param rounding the rounding to set
	 */
	public void setRounding(String rounding) {
		this.rounding = rounding;
	}
	/**
	 * @return the signatureType
	 */
	public String getSignatureType() {
		return signatureType;
	}
	/**
	 * @param signatureType the signatureType to set
	 */
	public void setSignatureType(String signatureType) {
		this.signatureType = signatureType;
	}
	/**
	 * @return the silhouette
	 */
	public String getSilhouette() {
		return silhouette;
	}
	/**
	 * @param silhouette the silhouette to set
	 */
	public void setSilhouette(String silhouette) {
		this.silhouette = silhouette;
	}
	/**
	 * @return the slot
	 */
	public String getSlot() {
		return slot;
	}
	/**
	 * @param slot the slot to set
	 */
	public void setSlot(String slot) {
		this.slot = slot;
	}
	/**
	 * @return the specialProduct
	 */
	public String getSpecialProduct() {
		return specialProduct;
	}
	/**
	 * @param specialProduct the specialProduct to set
	 */
	public void setSpecialProduct(String specialProduct) {
		this.specialProduct = specialProduct;
	}
	/**
	 * @return the stdFSPromoGMPct
	 */
	public BigDecimal getStdFSPromoGMPct() {
		return stdFSPromoGMPct;
	}
	/**
	 * @param stdFSPromoGMPct the stdFSPromoGMPct to set
	 */
	public void setStdFSPromoGMPct(BigDecimal stdFSPromoGMPct) {
		this.stdFSPromoGMPct = stdFSPromoGMPct;
	}
	/**
	 * @return the stdFSTicketGMPct
	 */
	public BigDecimal getStdFSTicketGMPct() {
		return stdFSTicketGMPct;
	}
	/**
	 * @param stdFSTicketGMPct the stdFSTicketGMPct to set
	 */
	public void setStdFSTicketGMPct(BigDecimal stdFSTicketGMPct) {
		this.stdFSTicketGMPct = stdFSTicketGMPct;
	}
	/**
	 * @return the targetCost
	 */
	public BigDecimal getTargetCost() {
		return targetCost;
	}
	/**
	 * @param targetCost the targetCost to set
	 */
	public void setTargetCost(BigDecimal targetCost) {
		this.targetCost = targetCost;
	}
	/**
	 * @return the targetFSPromoGMPct
	 */
	public BigDecimal getTargetFSPromoGMPct() {
		return targetFSPromoGMPct;
	}
	/**
	 * @param targetFSPromoGMPct the targetFSPromoGMPct to set
	 */
	public void setTargetFSPromoGMPct(BigDecimal targetFSPromoGMPct) {
		this.targetFSPromoGMPct = targetFSPromoGMPct;
	}
	/**
	 * @return the targetFSTargetGMPct
	 */
	public BigDecimal getTargetFSTargetGMPct() {
		return targetFSTargetGMPct;
	}
	/**
	 * @param targetFSTargetGMPct the targetFSTargetGMPct to set
	 */
	public void setTargetFSTargetGMPct(BigDecimal targetFSTargetGMPct) {
		this.targetFSTargetGMPct = targetFSTargetGMPct;
	}
	/**
	 * @return the targetRetailGMPct
	 */
	public BigDecimal getTargetRetailGMPct() {
		return targetRetailGMPct;
	}
	/**
	 * @param targetRetailGMPct the targetRetailGMPct to set
	 */
	public void setTargetRetailGMPct(BigDecimal targetRetailGMPct) {
		this.targetRetailGMPct = targetRetailGMPct;
	}
	/**
	 * @return the team
	 */
	public String getTeam() {
		return team;
	}
	/**
	 * @param team the team to set
	 */
	public void setTeam(String team) {
		this.team = team;
	}
	/**
	 * @return the closures
	 */
	public String getClosures() {
		return closures;
	}
	/**
	 * @param closures the closures to set
	 */
	public void setClosures(String closures) {
		this.closures = closures;
	}
	/**
	 * @return the coin
	 */
	public String getCoin() {
		return coin;
	}
	/**
	 * @param coin the coin to set
	 */
	public void setCoin(String coin) {
		this.coin = coin;
	}
	/**
	 * @return the creditCardPocket
	 */
	public String getCreditCardPocket() {
		return creditCardPocket;
	}
	/**
	 * @param creditCardPocket the creditCardPocket to set
	 */
	public void setCreditCardPocket(String creditCardPocket) {
		this.creditCardPocket = creditCardPocket;
	}
	/**
	 * @return the crossBody
	 */
	public String getCrossBody() {
		return crossBody;
	}
	/**
	 * @param crossBody the crossBody to set
	 */
	public void setCrossBody(String crossBody) {
		this.crossBody = crossBody;
	}
	/**
	 * @return the handbagSize
	 */
	public String getHandbagSize() {
		return handbagSize;
	}
	/**
	 * @param handbagSize the handbagSize to set
	 */
	public void setHandbagSize(String handbagSize) {
		this.handbagSize = handbagSize;
	}
	/**
	 * @return the iDWindow
	 */
	public String getIDWindow() {
		return idwindow;
	}
	/**
	 * @param iDWindow the iDWindow to set
	 */
	public void setIDWindow(String idwindow) {
		idwindow = idwindow;
	}
	/**
	 * @return the keyringDimensions
	 */
	public String getKeyringDimensions() {
		return keyringDimensions;
	}
	/**
	 * @param keyringDimensions the keyringDimensions to set
	 */
	public void setKeyringDimensions(String keyringDimensions) {
		this.keyringDimensions = keyringDimensions;
	}
	/**
	 * @return the organizationalPanel
	 */
	public String getOrganizationalPanel() {
		return organizationalPanel;
	}
	/**
	 * @param organizationalPanel the organizationalPanel to set
	 */
	public void setOrganizationalPanel(String organizationalPanel) {
		this.organizationalPanel = organizationalPanel;
	}
	/**
	 * @return the sampleFacility
	 */
	public BigDecimal getSampleFacility() {
		return sampleFacility;
	}
	/**
	 * @param sampleFacility the sampleFacility to set
	 */
	public void setSampleFacility(BigDecimal sampleFacility) {
		this.sampleFacility = sampleFacility;
	}
	/**
	 * @return the styleGroup
	 */
	public String getStyleGroup() {
		return styleGroup;
	}
	/**
	 * @param styleGroup the styleGroup to set
	 */
	public void setStyleGroup(String styleGroup) {
		this.styleGroup = styleGroup;
	}
	/**
	 * @return the toeshape
	 */
	public String getToeshape() {
		return toeshape;
	}
	/**
	 * @param toeshape the toeshape to set
	 */
	public void setToeshape(String toeshape) {
		this.toeshape = toeshape;
	}
	/**
	 * @return the soletype
	 */
	public String getSoletype() {
		return soletype;
	}
	/**
	 * @param soletype the soletype to set
	 */
	public void setSoletype(String soletype) {
		this.soletype = soletype;
	}
	/**
	 * @return the toetype
	 */
	public String getToetype() {
		return toetype;
	}
	/**
	 * @param toetype the toetype to set
	 */
	public void setToetype(String toetype) {
		this.toetype = toetype;
	}
	/**
	 * @return the heelHeight1
	 */
	public String getHeelHeight1() {
		return heelHeight1;
	}
	/**
	 * @param heelHeight1 the heelHeight1 to set
	 */
	public void setHeelHeight1(String heelHeight1) {
		this.heelHeight1 = heelHeight1;
	}
	/**
	 * @return the teamAD
	 */
	public String getTeamAD() {
		return teamAD;
	}
	/**
	 * @param teamAD the teamAD to set
	 */
	public void setTeamAD(String teamAD) {
		this.teamAD = teamAD;
	}
	/**
	 * @return the teamAsstAD
	 */
	public String getTeamAsstAD() {
		return teamAsstAD;
	}
	/**
	 * @param teamAsstAD the teamAsstAD to set
	 */
	public void setTeamAsstAD(String teamAsstAD) {
		this.teamAsstAD = teamAsstAD;
	}
	/**
	 * @return the teamAsstDD
	 */
	public String getTeamAsstDD() {
		return teamAsstDD;
	}
	/**
	 * @param teamAsstDD the teamAsstDD to set
	 */
	public void setTeamAsstDD(String teamAsstDD) {
		this.teamAsstDD = teamAsstDD;
	}
	/**
	 * @return the teamAsstDesigner
	 */
	public String getTeamAsstDesigner() {
		return teamAsstDesigner;
	}
	/**
	 * @param teamAsstDesigner the teamAsstDesigner to set
	 */
	public void setTeamAsstDesigner(String teamAsstDesigner) {
		this.teamAsstDesigner = teamAsstDesigner;
	}
	/**
	 * @return the teamDD
	 */
	public String getTeamDD() {
		return teamDD;
	}
	/**
	 * @param teamDD the teamDD to set
	 */
	public void setTeamDD(String teamDD) {
		this.teamDD = teamDD;
	}
	/**
	 * @return the teamDesigner1
	 */
	public String getTeamDesigner1() {
		return teamDesigner1;
	}
	/**
	 * @param teamDesigner1 the teamDesigner1 to set
	 */
	public void setTeamDesigner1(String teamDesigner1) {
		this.teamDesigner1 = teamDesigner1;
	}
	/**
	 * @return the teamDesigner2
	 */
	public String getTeamDesigner2() {
		return teamDesigner2;
	}
	/**
	 * @param teamDesigner2 the teamDesigner2 to set
	 */
	public void setTeamDesigner2(String teamDesigner2) {
		this.teamDesigner2 = teamDesigner2;
	}
	/**
	 * @return the teamEngineer
	 */
	public String getTeamEngineer() {
		return teamEngineer;
	}
	/**
	 * @param teamEngineer the teamEngineer to set
	 */
	public void setTeamEngineer(String teamEngineer) {
		this.teamEngineer = teamEngineer;
	}
	/**
	 * @return the teamMerchant1
	 */
	public String getTeamMerchant1() {
		return teamMerchant1;
	}
	/**
	 * @param teamMerchant1 the teamMerchant1 to set
	 */
	public void setTeamMerchant1(String teamMerchant1) {
		this.teamMerchant1 = teamMerchant1;
	}
	/**
	 * @return the teamMerchant2
	 */
	public String getTeamMerchant2() {
		return teamMerchant2;
	}
	/**
	 * @param teamMerchant2 the teamMerchant2 to set
	 */
	public void setTeamMerchant2(String teamMerchant2) {
		this.teamMerchant2 = teamMerchant2;
	}
	/**
	 * @return the teamPreProduction
	 */
	public String getTeamPreProduction() {
		return teamPreProduction;
	}
	/**
	 * @param teamPreProduction the teamPreProduction to set
	 */
	public void setTeamPreProduction(String teamPreProduction) {
		this.teamPreProduction = teamPreProduction;
	}
	/**
	 * @return the teamProduction
	 */
	public String getTeamProduction() {
		return teamProduction;
	}
	/**
	 * @param teamProduction the teamProduction to set
	 */
	public void setTeamProduction(String teamProduction) {
		this.teamProduction = teamProduction;
	}
	/**
	 * @return the teamTechnicalDesigner
	 */
	public String getTeamTechnicalDesigner() {
		return teamTechnicalDesigner;
	}
	/**
	 * @param teamTechnicalDesigner the teamTechnicalDesigner to set
	 */
	public void setTeamTechnicalDesigner(String teamTechnicalDesigner) {
		this.teamTechnicalDesigner = teamTechnicalDesigner;
	}
	/**
	 * @return the totalCost
	 */
	public String getTotalCost() {
		return totalCost;
	}
	/**
	 * @param totalCost the totalCost to set
	 */
	public void setTotalCost(String totalCost) {
		this.totalCost = totalCost;
	}
	/**
	 * @return the totoBillfoldPockets
	 */
	public String getTotoBillfoldPockets() {
		return totoBillfoldPockets;
	}
	/**
	 * @param totoBillfoldPockets the totoBillfoldPockets to set
	 */
	public void setTotoBillfoldPockets(String totoBillfoldPockets) {
		this.totoBillfoldPockets = totoBillfoldPockets;
	}
	/**
	 * @return the totoCheckbook
	 */
	public String getTotoCheckbook() {
		return totoCheckbook;
	}
	/**
	 * @param totoCheckbook the totoCheckbook to set
	 */
	public void setTotoCheckbook(String totoCheckbook) {
		this.totoCheckbook = totoCheckbook;
	}
	/**
	 * @return the totoCollection
	 */
	public String getTotoCollection() {
		return totoCollection;
	}
	/**
	 * @param totoCollection the totoCollection to set
	 */
	public void setTotoCollection(String totoCollection) {
		this.totoCollection = totoCollection;
	}
	/**
	 * @return the totoColors
	 */
	public String getTotoColors() {
		return totoColors;
	}
	/**
	 * @param totoColors the totoColors to set
	 */
	public void setTotoColors(String totoColors) {
		this.totoColors = totoColors;
	}
	/**
	 * @return the totoDropLengthIn
	 */
	public BigDecimal getTotoDropLengthIn() {
		return totoDropLengthIn;
	}
	/**
	 * @param totoDropLengthIn the totoDropLengthIn to set
	 */
	public void setTotoDropLengthIn(BigDecimal totoDropLengthIn) {
		this.totoDropLengthIn = totoDropLengthIn;
	}
	/**
	 * @return the totoExteriorPocket
	 */
	public String getTotoExteriorPocket() {
		return totoExteriorPocket;
	}
	/**
	 * @param totoExteriorPocket the totoExteriorPocket to set
	 */
	public void setTotoExteriorPocket(String totoExteriorPocket) {
		this.totoExteriorPocket = totoExteriorPocket;
	}
	/**
	 * @return the totoHeightCm
	 */
	public BigDecimal getTotoHeightCm() {
		return totoHeightCm;
	}
	/**
	 * @param totoHeightCm the totoHeightCm to set
	 */
	public void setTotoHeightCm(BigDecimal totoHeightCm) {
		this.totoHeightCm = totoHeightCm;
	}
	/**
	 * @return the totoInteriorPocket
	 */
	public String getTotoInteriorPocket() {
		return totoInteriorPocket;
	}
	/**
	 * @param totoInteriorPocket the totoInteriorPocket to set
	 */
	public void setTotoInteriorPocket(String totoInteriorPocket) {
		this.totoInteriorPocket = totoInteriorPocket;
	}
	/**
	 * @return the totoLengthCm
	 */
	public BigDecimal getTotoLengthCm() {
		return totoLengthCm;
	}
	/**
	 * @param totoLengthCm the totoLengthCm to set
	 */
	public void setTotoLengthCm(BigDecimal totoLengthCm) {
		this.totoLengthCm = totoLengthCm;
	}
	/**
	 * @return the totoLiningMaterial
	 */
	public String getTotoLiningMaterial() {
		return totoLiningMaterial;
	}
	/**
	 * @param totoLiningMaterial the totoLiningMaterial to set
	 */
	public void setTotoLiningMaterial(String totoLiningMaterial) {
		this.totoLiningMaterial = totoLiningMaterial;
	}
	/**
	 * @return the totoShellMaterial
	 */
	public String getTotoShellMaterial() {
		return totoShellMaterial;
	}
	/**
	 * @param totoShellMaterial the totoShellMaterial to set
	 */
	public void setTotoShellMaterial(String totoShellMaterial) {
		this.totoShellMaterial = totoShellMaterial;
	}
	/**
	 * @return the totoSizes
	 */
	public String getTotoSizes() {
		return totoSizes;
	}
	/**
	 * @param totoSizes the totoSizes to set
	 */
	public void setTotoSizes(String totoSizes) {
		this.totoSizes = totoSizes;
	}
	/**
	 * @return the totoStrapLengthIn
	 */
	public BigDecimal getTotoStrapLengthIn() {
		return totoStrapLengthIn;
	}
	/**
	 * @param totoStrapLengthIn the totoStrapLengthIn to set
	 */
	public void setTotoStrapLengthIn(BigDecimal totoStrapLengthIn) {
		this.totoStrapLengthIn = totoStrapLengthIn;
	}
	/**
	 * @return the totoTrimMaterial
	 */
	public String getTotoTrimMaterial() {
		return totoTrimMaterial;
	}
	/**
	 * @param totoTrimMaterial the totoTrimMaterial to set
	 */
	public void setTotoTrimMaterial(String totoTrimMaterial) {
		this.totoTrimMaterial = totoTrimMaterial;
	}
	/**
	 * @return the totoTypeOfSole
	 */
	public String getTotoTypeOfSole() {
		return totoTypeOfSole;
	}
	/**
	 * @param totoTypeOfSole the totoTypeOfSole to set
	 */
	public void setTotoTypeOfSole(String totoTypeOfSole) {
		this.totoTypeOfSole = totoTypeOfSole;
	}
	/**
	 * @return the totoWidthCm
	 */
	public BigDecimal getTotoWidthCm() {
		return totoWidthCm;
	}
	/**
	 * @param totoWidthCm the totoWidthCm to set
	 */
	public void setTotoWidthCm(BigDecimal totoWidthCm) {
		this.totoWidthCm = totoWidthCm;
	}
	/**
	 * @return the tradeMUPct
	 */
	public BigDecimal getTradeMUPct() {
		return tradeMUPct;
	}
	/**
	 * @param tradeMUPct the tradeMUPct to set
	 */
	public void setTradeMUPct(BigDecimal tradeMUPct) {
		this.tradeMUPct = tradeMUPct;
	}
	/**
	 * @return the updated
	 */
	public String getUpdated() {
		return updated;
	}
	/**
	 * @param updated the updated to set
	 */
	public void setUpdated(String updated) {
		this.updated = updated;
	}
	/**
	 * @return the wholesaleMUPct
	 */
	public BigDecimal getWholesaleMUPct() {
		return wholesaleMUPct;
	}
	/**
	 * @param wholesaleMUPct the wholesaleMUPct to set
	 */
	public void setWholesaleMUPct(BigDecimal wholesaleMUPct) {
		this.wholesaleMUPct = wholesaleMUPct;
	}
	/**
	 * @return the wholesalePrice
	 */
	public BigDecimal getWholesalePrice() {
		return wholesalePrice;
	}
	/**
	 * @param wholesalePrice the wholesalePrice to set
	 */
	public void setWholesalePrice(BigDecimal wholesalePrice) {
		this.wholesalePrice = wholesalePrice;
	}
	/**
	 * @return the workingCost1FSPromoGMPct
	 */
	public BigDecimal getWorkingCost1FSPromoGMPct() {
		return workingCost1FSPromoGMPct;
	}
	/**
	 * @param workingCost1FSPromoGMPct the workingCost1FSPromoGMPct to set
	 */
	public void setWorkingCost1FSPromoGMPct(BigDecimal workingCost1FSPromoGMPct) {
		this.workingCost1FSPromoGMPct = workingCost1FSPromoGMPct;
	}
	/**
	 * @return the workingCost1FSTargetGMPct
	 */
	public BigDecimal getWorkingCost1FSTargetGMPct() {
		return workingCost1FSTargetGMPct;
	}
	/**
	 * @param workingCost1FSTargetGMPct the workingCost1FSTargetGMPct to set
	 */
	public void setWorkingCost1FSTargetGMPct(BigDecimal workingCost1FSTargetGMPct) {
		this.workingCost1FSTargetGMPct = workingCost1FSTargetGMPct;
	}
	/**
	 * @return the workingCost1RetailGMPct
	 */
	public BigDecimal getWorkingCost1RetailGMPct() {
		return workingCost1RetailGMPct;
	}
	/**
	 * @param workingCost1RetailGMPct the workingCost1RetailGMPct to set
	 */
	public void setWorkingCost1RetailGMPct(BigDecimal workingCost1RetailGMPct) {
		this.workingCost1RetailGMPct = workingCost1RetailGMPct;
	}
	/**
	 * @return the upperMaterial
	 */
	public String getUpperMaterial() {
		return upperMaterial;
	}
	/**
	 * @param upperMaterial the upperMaterial to set
	 */
	public void setUpperMaterial(String upperMaterial) {
		this.upperMaterial = upperMaterial;
	}
	/**
	 * @return the sizeScale
	 */
	public String getSizeScale() {
		return sizeScale;
	}
	/**
	 * @param sizeScale the sizeScale to set
	 */
	public void setSizeScale(String sizeScale) {
		this.sizeScale = sizeScale;
	}
	/**
	 * @return the pdtType
	 */
	public String getPdtType() {
		return pdtType;
	}
	/**
	 * @param pdtType the pdtType to set
	 */
	public void setPdtType(String pdtType) {
		this.pdtType = pdtType;
	}
	/**
	 * @return the lens
	 */
	public String getLens() {
		return lens;
	}
	/**
	 * @param lens the lens to set
	 */
	public void setLens(String lens) {
		this.lens = lens;
	}
}
