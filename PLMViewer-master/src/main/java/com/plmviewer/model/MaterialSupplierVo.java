/**
 * 
 */
package com.plmviewer.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author uthanasekarapandian
 *
 */
public class MaterialSupplierVo {
	
	private String activeMaterialSupplier;
	private String articleApproval;
	private String articleApprovalComments;
	private String articleApprovalDate;
	private String articleMasterLocation;
	private String associatedIntros;
	private String associatedTeams;
	private String channel;
	private String countryOfOrigin;
	private String ffpDate;
	private String  ffpStatus;
	private String  initialIntroDate;
	private String  longLeadTimeMS;
	private BigDecimal materialPrice;
	private String  minimumOrderQuantity;
	private String  MSconflictFlag;
	private BigDecimal MScurrentVersion;
	private String  MSresolveConflictFlag;
	private String  pegRate;
	private BigDecimal  productionLeadTime;
	private BigDecimal  productionMinimum;
	private String  productionTolerance;
	private String  productTeam;
	private String  requestDate;
	private String  requestedBy;
	private String  shippingTerms;
	private BigDecimal  stdCostBasis;
	private String  stdCostCurrencySupplier;
	private String  stdCostEffectivity;
	private String  stdCostEstimateFlag;
	private String  stdCostFy;
	private String  supplierArticleNumber; 
	private String  supplierBasisUOM;
	private String  supStdCostDate;
   private String  unitOfCurrency;
	private String  unitOfMeasureSupp;
	private String chemicalcontent;
	private String manufMethod;
	private String  metalContent;
	private BigDecimal toolingCharges;
	private String  toolingChargesPaid;
	private String toolingPaidDate ;
	
	private String  metalcontent ;
	private String  manufMethof ;
	private String  surfaceFinishmsHardware ;
	private String  toolingPaid ;
	
	private String  articleMasterSent ;
	private String   averageSkinSize ;
	private String  cities ;
	private String classificationDate ;
	private String  classificationRequestDate ;
	private String  etd ;
	private String exoticCommonName ;
	private String exoticMaterial ;
	private String fishAndWildlife ;
	private String harvestMethod ;
	private String hideCOO ;
	private String placeOfHarvest ;
	private String prohibited ;
	private String scientificName ;
	private String  shipDate ;
	private String unrestricted;
	
	private String  materialContent ;
	private String backedByMill ;
	private String backingContent ;
	private BigDecimal backingLeadtime ;
	private String backingMinimum ;
	private String bulkMinimumColor ;
	private String contentWithBacking ;
	private String cuttableWidth ;
	private String cuttableWidthFace ;
	private String dyeMethod ;
	private String estCuttableWidth ;
	private String estCuttableWidthFace ;
	private String estFullWidth ;
	private String estFullWidthFace ;
	private String fabricConstruction ;
	private String fabricContent ;
	private String faceFabricWeight ;
	private String fullWidth ;
	private String fullWidthFace ;
	private String printMethod;
	private BigDecimal sampleLeadTime ;
	private String sampleMinimum ;
	private String surfaceFinishnms;
	private String yarnSizeMS;
	
	private String  coloringMethodMs;
	private String  coreContentms;
	private String  surfaceFinishms;
	private String  thicknessms;
	private String  totalContentms;
	
	private String  tapeContent ;
	private String  teethContent ;
	private String materialcontentms;
	
	
	/**
	 * @return the activeMaterialSupplier
	 */
	public String getActiveMaterialSupplier() {
		return activeMaterialSupplier;
	}
	/**
	 * @param activeMaterialSupplier the activeMaterialSupplier to set
	 */
	public void setActiveMaterialSupplier(String activeMaterialSupplier) {
		this.activeMaterialSupplier = activeMaterialSupplier;
	}
	/**
	 * @return the articleApproval
	 */
	public String getArticleApproval() {
		return articleApproval;
	}
	/**
	 * @param articleApproval the articleApproval to set
	 */
	public void setArticleApproval(String articleApproval) {
		this.articleApproval = articleApproval;
	}
	/**
	 * @return the articleApprovalComments
	 */
	public String getArticleApprovalComments() {
		return articleApprovalComments;
	}
	/**
	 * @param articleApprovalComments the articleApprovalComments to set
	 */
	public void setArticleApprovalComments(String articleApprovalComments) {
		this.articleApprovalComments = articleApprovalComments;
	}
	/**
	 * @return the articleApprovalDate
	 */
	public String getArticleApprovalDate() {
		return articleApprovalDate;
	}
	/**
	 * @param articleApprovalDate the articleApprovalDate to set
	 */
	public void setArticleApprovalDate(String articleApprovalDate) {
		this.articleApprovalDate = articleApprovalDate;
	}
	/**
	 * @return the articleMasterLocation
	 */
	public String getArticleMasterLocation() {
		return articleMasterLocation;
	}
	/**
	 * @param articleMasterLocation the articleMasterLocation to set
	 */
	public void setArticleMasterLocation(String articleMasterLocation) {
		this.articleMasterLocation = articleMasterLocation;
	}
	/**
	 * @return the associatedIntros
	 */
	public String getAssociatedIntros() {
		return associatedIntros;
	}
	/**
	 * @param associatedIntros the associatedIntros to set
	 */
	public void setAssociatedIntros(String associatedIntros) {
		this.associatedIntros = associatedIntros;
	}
	/**
	 * @return the associatedTeams
	 */
	public String getAssociatedTeams() {
		return associatedTeams;
	}
	/**
	 * @param associatedTeams the associatedTeams to set
	 */
	public void setAssociatedTeams(String associatedTeams) {
		this.associatedTeams = associatedTeams;
	}
	/**
	 * @return the channel
	 */
	public String getChannel() {
		return channel;
	}
	/**
	 * @param channel the channel to set
	 */
	public void setChannel(String channel) {
		this.channel = channel;
	}
	/**
	 * @return the countryOfOrigin
	 */
	public String getCountryOfOrigin() {
		return countryOfOrigin;
	}
	/**
	 * @param countryOfOrigin the countryOfOrigin to set
	 */
	public void setCountryOfOrigin(String countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}
	/**
	 * @return the ffpDate
	 */
	public String getFfpDate() {
		return ffpDate;
	}
	/**
	 * @param ffpDate the ffpDate to set
	 */
	public void setFfpDate(String ffpDate) {
		this.ffpDate = ffpDate;
	}
	/**
	 * @return the ffpStatus
	 */
	public String getFfpStatus() {
		return ffpStatus;
	}
	/**
	 * @param ffpStatus the ffpStatus to set
	 */
	public void setFfpStatus(String ffpStatus) {
		this.ffpStatus = ffpStatus;
	}
	/**
	 * @return the initialIntroDate
	 */
	public String getInitialIntroDate() {
		return initialIntroDate;
	}
	/**
	 * @param initialIntroDate the initialIntroDate to set
	 */
	public void setInitialIntroDate(String initialIntroDate) {
		this.initialIntroDate = initialIntroDate;
	}
	/**
	 * @return the longLeadTimeMS
	 */
	public String getLongLeadTimeMS() {
		return longLeadTimeMS;
	}
	/**
	 * @param longLeadTimeMS the longLeadTimeMS to set
	 */
	public void setLongLeadTimeMS(String longLeadTimeMS) {
		this.longLeadTimeMS = longLeadTimeMS;
	}
	/**
	 * @return the materialPrice
	 */
	public BigDecimal getMaterialPrice() {
		return materialPrice;
	}
	/**
	 * @param materialPrice the materialPrice to set
	 */
	public void setMaterialPrice(BigDecimal materialPrice) {
		this.materialPrice = materialPrice;
	}
	/**
	 * @return the minimumOrderQuantity
	 */
	public String getMinimumOrderQuantity() {
		return minimumOrderQuantity;
	}
	/**
	 * @param minimumOrderQuantity the minimumOrderQuantity to set
	 */
	public void setMinimumOrderQuantity(String minimumOrderQuantity) {
		this.minimumOrderQuantity = minimumOrderQuantity;
	}
	/**
	 * @return the mSconflictFlag
	 */
	public String getMSconflictFlag() {
		return MSconflictFlag;
	}
	/**
	 * @param mSconflictFlag the mSconflictFlag to set
	 */
	public void setMSconflictFlag(String mSconflictFlag) {
		MSconflictFlag = mSconflictFlag;
	}
	/**
	 * @return the mScurrentVersion
	 */
	public BigDecimal getMScurrentVersion() {
		return MScurrentVersion;
	}
	/**
	 * @param mScurrentVersion the mScurrentVersion to set
	 */
	public void setMScurrentVersion(BigDecimal mScurrentVersion) {
		MScurrentVersion = mScurrentVersion;
	}
	/**
	 * @return the mSresolveConflictFlag
	 */
	public String getMSresolveConflictFlag() {
		return MSresolveConflictFlag;
	}
	/**
	 * @param mSresolveConflictFlag the mSresolveConflictFlag to set
	 */
	public void setMSresolveConflictFlag(String mSresolveConflictFlag) {
		MSresolveConflictFlag = mSresolveConflictFlag;
	}
	/**
	 * @return the pegRate
	 */
	public String getPegRate() {
		return pegRate;
	}
	/**
	 * @param pegRate the pegRate to set
	 */
	public void setPegRate(String pegRate) {
		this.pegRate = pegRate;
	}
	/**
	 * @return the productionLeadTime
	 */
	public BigDecimal getProductionLeadTime() {
		return productionLeadTime;
	}
	/**
	 * @param productionLeadTime the productionLeadTime to set
	 */
	public void setProductionLeadTime(BigDecimal productionLeadTime) {
		this.productionLeadTime = productionLeadTime;
	}
	/**
	 * @return the productionMinimum
	 */
	public BigDecimal getProductionMinimum() {
		return productionMinimum;
	}
	/**
	 * @param productionMinimum the productionMinimum to set
	 */
	public void setProductionMinimum(BigDecimal productionMinimum) {
		this.productionMinimum = productionMinimum;
	}
	/**
	 * @return the productionTolerance
	 */
	public String getProductionTolerance() {
		return productionTolerance;
	}
	/**
	 * @param productionTolerance the productionTolerance to set
	 */
	public void setProductionTolerance(String productionTolerance) {
		this.productionTolerance = productionTolerance;
	}
	/**
	 * @return the productTeam
	 */
	public String getProductTeam() {
		return productTeam;
	}
	/**
	 * @param productTeam the productTeam to set
	 */
	public void setProductTeam(String productTeam) {
		this.productTeam = productTeam;
	}
	/**
	 * @return the requestDate
	 */
	public String getRequestDate() {
		return requestDate;
	}
	/**
	 * @param requestDate the requestDate to set
	 */
	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}
	/**
	 * @return the requestedBy
	 */
	public String getRequestedBy() {
		return requestedBy;
	}
	/**
	 * @param requestedBy the requestedBy to set
	 */
	public void setRequestedBy(String requestedBy) {
		this.requestedBy = requestedBy;
	}
	/**
	 * @return the shippingTerms
	 */
	public String getShippingTerms() {
		return shippingTerms;
	}
	/**
	 * @param shippingTerms the shippingTerms to set
	 */
	public void setShippingTerms(String shippingTerms) {
		this.shippingTerms = shippingTerms;
	}
	/**
	 * @return the stdCostBasis
	 */
	public BigDecimal getStdCostBasis() {
		return stdCostBasis;
	}
	/**
	 * @param stdCostBasis the stdCostBasis to set
	 */
	public void setStdCostBasis(BigDecimal stdCostBasis) {
		this.stdCostBasis = stdCostBasis;
	}
	/**
	 * @return the stdCostCurrencySupplier
	 */
	public String getStdCostCurrencySupplier() {
		return stdCostCurrencySupplier;
	}
	/**
	 * @param stdCostCurrencySupplier the stdCostCurrencySupplier to set
	 */
	public void setStdCostCurrencySupplier(String stdCostCurrencySupplier) {
		this.stdCostCurrencySupplier = stdCostCurrencySupplier;
	}
	/**
	 * @return the stdCostEffectivity
	 */
	public String getStdCostEffectivity() {
		return stdCostEffectivity;
	}
	/**
	 * @param stdCostEffectivity the stdCostEffectivity to set
	 */
	public void setStdCostEffectivity(String stdCostEffectivity) {
		this.stdCostEffectivity = stdCostEffectivity;
	}
	/**
	 * @return the stdCostEstimateFlag
	 */
	public String getStdCostEstimateFlag() {
		return stdCostEstimateFlag;
	}
	/**
	 * @param stdCostEstimateFlag the stdCostEstimateFlag to set
	 */
	public void setStdCostEstimateFlag(String stdCostEstimateFlag) {
		this.stdCostEstimateFlag = stdCostEstimateFlag;
	}
	/**
	 * @return the stdCostFy
	 */
	public String getStdCostFy() {
		return stdCostFy;
	}
	/**
	 * @param stdCostFy the stdCostFy to set
	 */
	public void setStdCostFy(String stdCostFy) {
		this.stdCostFy = stdCostFy;
	}
	/**
	 * @return the supplierArticleNumber
	 */
	public String getSupplierArticleNumber() {
		return supplierArticleNumber;
	}
	/**
	 * @param supplierArticleNumber the supplierArticleNumber to set
	 */
	public void setSupplierArticleNumber(String supplierArticleNumber) {
		this.supplierArticleNumber = supplierArticleNumber;
	}
	/**
	 * @return the supplierBasisUOM
	 */
	public String getSupplierBasisUOM() {
		return supplierBasisUOM;
	}
	/**
	 * @param supplierBasisUOM the supplierBasisUOM to set
	 */
	public void setSupplierBasisUOM(String supplierBasisUOM) {
		this.supplierBasisUOM = supplierBasisUOM;
	}
	/**
	 * @return the supStdCostDate
	 */
	public String getSupStdCostDate() {
		return supStdCostDate;
	}
	/**
	 * @param supStdCostDate the supStdCostDate to set
	 */
	public void setSupStdCostDate(String supStdCostDate) {
		this.supStdCostDate = supStdCostDate;
	}
	/**
	 * @return the unitOfCurrency
	 */
	public String getUnitOfCurrency() {
		return unitOfCurrency;
	}
	/**
	 * @param unitOfCurrency the unitOfCurrency to set
	 */
	public void setUnitOfCurrency(String unitOfCurrency) {
		this.unitOfCurrency = unitOfCurrency;
	}
	/**
	 * @return the unitOfMeasureSupp
	 */
	public String getUnitOfMeasureSupp() {
		return unitOfMeasureSupp;
	}
	/**
	 * @param unitOfMeasureSupp the unitOfMeasureSupp to set
	 */
	public void setUnitOfMeasureSupp(String unitOfMeasureSupp) {
		this.unitOfMeasureSupp = unitOfMeasureSupp;
	}
	/**
	 * @return the chemicalcontent
	 */
	public String getChemicalcontent() {
		return chemicalcontent;
	}
	/**
	 * @param chemicalcontent the chemicalcontent to set
	 */
	public void setChemicalcontent(String chemicalcontent) {
		this.chemicalcontent = chemicalcontent;
	}
	/**
	 * @return the manufMethod
	 */
	public String getManufMethod() {
		return manufMethod;
	}
	/**
	 * @param manufMethod the manufMethod to set
	 */
	public void setManufMethod(String manufMethod) {
		this.manufMethod = manufMethod;
	}
	/**
	 * @return the metalContent
	 */
	public String getMetalContent() {
		return metalContent;
	}
	/**
	 * @param metalContent the metalContent to set
	 */
	public void setMetalContent(String metalContent) {
		this.metalContent = metalContent;
	}
	/**
	 * @return the toolingCharges
	 */
	public BigDecimal getToolingCharges() {
		return toolingCharges;
	}
	/**
	 * @param toolingCharges the toolingCharges to set
	 */
	public void setToolingCharges(BigDecimal toolingCharges) {
		this.toolingCharges = toolingCharges;
	}
	/**
	 * @return the toolingChargesPaid
	 */
	public String getToolingChargesPaid() {
		return toolingChargesPaid;
	}
	/**
	 * @param toolingChargesPaid the toolingChargesPaid to set
	 */
	public void setToolingChargesPaid(String toolingChargesPaid) {
		this.toolingChargesPaid = toolingChargesPaid;
	}
	/**
	 * @return the toolingPaidDate
	 */
	public String getToolingPaidDate() {
		return toolingPaidDate;
	}
	/**
	 * @param toolingPaidDate the toolingPaidDate to set
	 */
	public void setToolingPaidDate(String toolingPaidDate) {
		this.toolingPaidDate = toolingPaidDate;
	}
	/**
	 * @return the metalcontent
	 */
	public String getMetalcontent() {
		return metalcontent;
	}
	/**
	 * @param metalcontent the metalcontent to set
	 */
	public void setMetalcontent(String metalcontent) {
		this.metalcontent = metalcontent;
	}
	/**
	 * @return the manufMethof
	 */
	public String getManufMethof() {
		return manufMethof;
	}
	/**
	 * @param manufMethof the manufMethof to set
	 */
	public void setManufMethof(String manufMethof) {
		this.manufMethof = manufMethof;
	}
	/**
	 * @return the surfaceFinishmsHardware
	 */
	public String getSurfaceFinishmsHardware() {
		return surfaceFinishmsHardware;
	}
	/**
	 * @param surfaceFinishmsHardware the surfaceFinishmsHardware to set
	 */
	public void setSurfaceFinishmsHardware(String surfaceFinishmsHardware) {
		this.surfaceFinishmsHardware = surfaceFinishmsHardware;
	}
	/**
	 * @return the toolingPaid
	 */
	public String getToolingPaid() {
		return toolingPaid;
	}
	/**
	 * @param toolingPaid the toolingPaid to set
	 */
	public void setToolingPaid(String toolingPaid) {
		this.toolingPaid = toolingPaid;
	}
	/**
	 * @return the articleMasterSent
	 */
	public String getArticleMasterSent() {
		return articleMasterSent;
	}
	/**
	 * @param articleMasterSent the articleMasterSent to set
	 */
	public void setArticleMasterSent(String articleMasterSent) {
		this.articleMasterSent = articleMasterSent;
	}
	/**
	 * @return the averageSkinSize
	 */
	public String getAverageSkinSize() {
		return averageSkinSize;
	}
	/**
	 * @param averageSkinSize the averageSkinSize to set
	 */
	public void setAverageSkinSize(String averageSkinSize) {
		this.averageSkinSize = averageSkinSize;
	}
	/**
	 * @return the cities
	 */
	public String getCities() {
		return cities;
	}
	/**
	 * @param cities the cities to set
	 */
	public void setCities(String cities) {
		this.cities = cities;
	}
	/**
	 * @return the classificationDate
	 */
	public String getClassificationDate() {
		return classificationDate;
	}
	/**
	 * @param classificationDate the classificationDate to set
	 */
	public void setClassificationDate(String classificationDate) {
		this.classificationDate = classificationDate;
	}
	/**
	 * @return the classificationRequestDate
	 */
	public String getClassificationRequestDate() {
		return classificationRequestDate;
	}
	/**
	 * @param classificationRequestDate the classificationRequestDate to set
	 */
	public void setClassificationRequestDate(String classificationRequestDate) {
		this.classificationRequestDate = classificationRequestDate;
	}
	/**
	 * @return the etd
	 */
	public String getEtd() {
		return etd;
	}
	/**
	 * @param etd the etd to set
	 */
	public void setEtd(String etd) {
		this.etd = etd;
	}
	/**
	 * @return the exoticCommonName
	 */
	public String getExoticCommonName() {
		return exoticCommonName;
	}
	/**
	 * @param exoticCommonName the exoticCommonName to set
	 */
	public void setExoticCommonName(String exoticCommonName) {
		this.exoticCommonName = exoticCommonName;
	}
	/**
	 * @return the exoticMaterial
	 */
	public String getExoticMaterial() {
		return exoticMaterial;
	}
	/**
	 * @param exoticMaterial the exoticMaterial to set
	 */
	public void setExoticMaterial(String exoticMaterial) {
		this.exoticMaterial = exoticMaterial;
	}
	/**
	 * @return the fishAndWildlife
	 */
	public String getFishAndWildlife() {
		return fishAndWildlife;
	}
	/**
	 * @param fishAndWildlife the fishAndWildlife to set
	 */
	public void setFishAndWildlife(String fishAndWildlife) {
		this.fishAndWildlife = fishAndWildlife;
	}
	/**
	 * @return the harvestMethod
	 */
	public String getHarvestMethod() {
		return harvestMethod;
	}
	/**
	 * @param harvestMethod the harvestMethod to set
	 */
	public void setHarvestMethod(String harvestMethod) {
		this.harvestMethod = harvestMethod;
	}
	/**
	 * @return the hideCOO
	 */
	public String getHideCOO() {
		return hideCOO;
	}
	/**
	 * @param hideCOO the hideCOO to set
	 */
	public void setHideCOO(String hideCOO) {
		this.hideCOO = hideCOO;
	}
	/**
	 * @return the placeOfHarvest
	 */
	public String getPlaceOfHarvest() {
		return placeOfHarvest;
	}
	/**
	 * @param placeOfHarvest the placeOfHarvest to set
	 */
	public void setPlaceOfHarvest(String placeOfHarvest) {
		this.placeOfHarvest = placeOfHarvest;
	}
	/**
	 * @return the prohibited
	 */
	public String getProhibited() {
		return prohibited;
	}
	/**
	 * @param prohibited the prohibited to set
	 */
	public void setProhibited(String prohibited) {
		this.prohibited = prohibited;
	}
	/**
	 * @return the scientificName
	 */
	public String getScientificName() {
		return scientificName;
	}
	/**
	 * @param scientificName the scientificName to set
	 */
	public void setScientificName(String scientificName) {
		this.scientificName = scientificName;
	}
	/**
	 * @return the shipDate
	 */
	public String getShipDate() {
		return shipDate;
	}
	/**
	 * @param shipDate the shipDate to set
	 */
	public void setShipDate(String shipDate) {
		this.shipDate = shipDate;
	}
	/**
	 * @return the unrestricted
	 */
	public String getUnrestricted() {
		return unrestricted;
	}
	/**
	 * @param unrestricted the unrestricted to set
	 */
	public void setUnrestricted(String unrestricted) {
		this.unrestricted = unrestricted;
	}
	/**
	 * @return the materialContent
	 */
	public String getMaterialContent() {
		return materialContent;
	}
	/**
	 * @param materialContent the materialContent to set
	 */
	public void setMaterialContent(String materialContent) {
		this.materialContent = materialContent;
	}
	/**
	 * @return the backedByMill
	 */
	public String getBackedByMill() {
		return backedByMill;
	}
	/**
	 * @param backedByMill the backedByMill to set
	 */
	public void setBackedByMill(String backedByMill) {
		this.backedByMill = backedByMill;
	}
	/**
	 * @return the backingContent
	 */
	public String getBackingContent() {
		return backingContent;
	}
	/**
	 * @param backingContent the backingContent to set
	 */
	public void setBackingContent(String backingContent) {
		this.backingContent = backingContent;
	}
	/**
	 * @return the backingLeadtime
	 */
	public BigDecimal getBackingLeadtime() {
		return backingLeadtime;
	}
	/**
	 * @param backingLeadtime the backingLeadtime to set
	 */
	public void setBackingLeadtime(BigDecimal backingLeadtime) {
		this.backingLeadtime = backingLeadtime;
	}
	/**
	 * @return the backingMinimum
	 */
	public String getBackingMinimum() {
		return backingMinimum;
	}
	/**
	 * @param backingMinimum the backingMinimum to set
	 */
	public void setBackingMinimum(String backingMinimum) {
		this.backingMinimum = backingMinimum;
	}
	/**
	 * @return the bulkMinimumColor
	 */
	public String getBulkMinimumColor() {
		return bulkMinimumColor;
	}
	/**
	 * @param bulkMinimumColor the bulkMinimumColor to set
	 */
	public void setBulkMinimumColor(String bulkMinimumColor) {
		this.bulkMinimumColor = bulkMinimumColor;
	}
	/**
	 * @return the contentWithBacking
	 */
	public String getContentWithBacking() {
		return contentWithBacking;
	}
	/**
	 * @param contentWithBacking the contentWithBacking to set
	 */
	public void setContentWithBacking(String contentWithBacking) {
		this.contentWithBacking = contentWithBacking;
	}
	/**
	 * @return the cuttableWidth
	 */
	public String getCuttableWidth() {
		return cuttableWidth;
	}
	/**
	 * @param cuttableWidth the cuttableWidth to set
	 */
	public void setCuttableWidth(String cuttableWidth) {
		this.cuttableWidth = cuttableWidth;
	}
	/**
	 * @return the cuttableWidthFace
	 */
	public String getCuttableWidthFace() {
		return cuttableWidthFace;
	}
	/**
	 * @param cuttableWidthFace the cuttableWidthFace to set
	 */
	public void setCuttableWidthFace(String cuttableWidthFace) {
		this.cuttableWidthFace = cuttableWidthFace;
	}
	/**
	 * @return the dyeMethod
	 */
	public String getDyeMethod() {
		return dyeMethod;
	}
	/**
	 * @param dyeMethod the dyeMethod to set
	 */
	public void setDyeMethod(String dyeMethod) {
		this.dyeMethod = dyeMethod;
	}
	/**
	 * @return the estCuttableWidth
	 */
	public String getEstCuttableWidth() {
		return estCuttableWidth;
	}
	/**
	 * @param estCuttableWidth the estCuttableWidth to set
	 */
	public void setEstCuttableWidth(String estCuttableWidth) {
		this.estCuttableWidth = estCuttableWidth;
	}
	/**
	 * @return the estCuttableWidthFace
	 */
	public String getEstCuttableWidthFace() {
		return estCuttableWidthFace;
	}
	/**
	 * @param estCuttableWidthFace the estCuttableWidthFace to set
	 */
	public void setEstCuttableWidthFace(String estCuttableWidthFace) {
		this.estCuttableWidthFace = estCuttableWidthFace;
	}
	/**
	 * @return the estFullWidth
	 */
	public String getEstFullWidth() {
		return estFullWidth;
	}
	/**
	 * @param estFullWidth the estFullWidth to set
	 */
	public void setEstFullWidth(String estFullWidth) {
		this.estFullWidth = estFullWidth;
	}
	/**
	 * @return the estFullWidthFace
	 */
	public String getEstFullWidthFace() {
		return estFullWidthFace;
	}
	/**
	 * @param estFullWidthFace the estFullWidthFace to set
	 */
	public void setEstFullWidthFace(String estFullWidthFace) {
		this.estFullWidthFace = estFullWidthFace;
	}
	/**
	 * @return the fabricConstruction
	 */
	public String getFabricConstruction() {
		return fabricConstruction;
	}
	/**
	 * @param fabricConstruction the fabricConstruction to set
	 */
	public void setFabricConstruction(String fabricConstruction) {
		this.fabricConstruction = fabricConstruction;
	}
	/**
	 * @return the fabricContent
	 */
	public String getFabricContent() {
		return fabricContent;
	}
	/**
	 * @param fabricContent the fabricContent to set
	 */
	public void setFabricContent(String fabricContent) {
		this.fabricContent = fabricContent;
	}
	/**
	 * @return the faceFabricWeight
	 */
	public String getFaceFabricWeight() {
		return faceFabricWeight;
	}
	/**
	 * @param faceFabricWeight the faceFabricWeight to set
	 */
	public void setFaceFabricWeight(String faceFabricWeight) {
		this.faceFabricWeight = faceFabricWeight;
	}
	/**
	 * @return the fullWidth
	 */
	public String getFullWidth() {
		return fullWidth;
	}
	/**
	 * @param fullWidth the fullWidth to set
	 */
	public void setFullWidth(String fullWidth) {
		this.fullWidth = fullWidth;
	}
	/**
	 * @return the fullWidthFace
	 */
	public String getFullWidthFace() {
		return fullWidthFace;
	}
	/**
	 * @param fullWidthFace the fullWidthFace to set
	 */
	public void setFullWidthFace(String fullWidthFace) {
		this.fullWidthFace = fullWidthFace;
	}
	/**
	 * @return the printMethod
	 */
	public String getPrintMethod() {
		return printMethod;
	}
	/**
	 * @param printMethod the printMethod to set
	 */
	public void setPrintMethod(String printMethod) {
		this.printMethod = printMethod;
	}
	/**
	 * @return the sampleLeadTime
	 */
	public BigDecimal getSampleLeadTime() {
		return sampleLeadTime;
	}
	/**
	 * @param sampleLeadTime the sampleLeadTime to set
	 */
	public void setSampleLeadTime(BigDecimal sampleLeadTime) {
		this.sampleLeadTime = sampleLeadTime;
	}
	/**
	 * @return the sampleMinimum
	 */
	public String getSampleMinimum() {
		return sampleMinimum;
	}
	/**
	 * @param sampleMinimum the sampleMinimum to set
	 */
	public void setSampleMinimum(String sampleMinimum) {
		this.sampleMinimum = sampleMinimum;
	}
	/**
	 * @return the surfaceFinishnms
	 */
	public String getSurfaceFinishnms() {
		return surfaceFinishnms;
	}
	/**
	 * @param surfaceFinishnms the surfaceFinishnms to set
	 */
	public void setSurfaceFinishnms(String surfaceFinishnms) {
		this.surfaceFinishnms = surfaceFinishnms;
	}
	/**
	 * @return the yarnSizeMS
	 */
	public String getYarnSizeMS() {
		return yarnSizeMS;
	}
	/**
	 * @param yarnSizeMS the yarnSizeMS to set
	 */
	public void setYarnSizeMS(String yarnSizeMS) {
		this.yarnSizeMS = yarnSizeMS;
	}
	/**
	 * @return the coloringMethodMs
	 */
	public String getColoringMethodMs() {
		return coloringMethodMs;
	}
	/**
	 * @param coloringMethodMs the coloringMethodMs to set
	 */
	public void setColoringMethodMs(String coloringMethodMs) {
		this.coloringMethodMs = coloringMethodMs;
	}
	/**
	 * @return the coreContentms
	 */
	public String getCoreContentms() {
		return coreContentms;
	}
	/**
	 * @param coreContentms the coreContentms to set
	 */
	public void setCoreContentms(String coreContentms) {
		this.coreContentms = coreContentms;
	}
	/**
	 * @return the surfaceFinishms
	 */
	public String getSurfaceFinishms() {
		return surfaceFinishms;
	}
	/**
	 * @param surfaceFinishms the surfaceFinishms to set
	 */
	public void setSurfaceFinishms(String surfaceFinishms) {
		this.surfaceFinishms = surfaceFinishms;
	}
	/**
	 * @return the thicknessms
	 */
	public String getThicknessms() {
		return thicknessms;
	}
	/**
	 * @param thicknessms the thicknessms to set
	 */
	public void setThicknessms(String thicknessms) {
		this.thicknessms = thicknessms;
	}
	/**
	 * @return the totalContentms
	 */
	public String getTotalContentms() {
		return totalContentms;
	}
	/**
	 * @param totalContentms the totalContentms to set
	 */
	public void setTotalContentms(String totalContentms) {
		this.totalContentms = totalContentms;
	}
	/**
	 * @return the tapeContent
	 */
	public String getTapeContent() {
		return tapeContent;
	}
	/**
	 * @param tapeContent the tapeContent to set
	 */
	public void setTapeContent(String tapeContent) {
		this.tapeContent = tapeContent;
	}
	/**
	 * @return the teethContent
	 */
	public String getTeethContent() {
		return teethContent;
	}
	/**
	 * @param teethContent the teethContent to set
	 */
	public void setTeethContent(String teethContent) {
		this.teethContent = teethContent;
	}
	/**
	 * @return the materialcontentms
	 */
	public String getMaterialcontentms() {
		return materialcontentms;
	}
	/**
	 * @param materialcontentms the materialcontentms to set
	 */
	public void setMaterialcontentms(String materialcontentms) {
		this.materialcontentms = materialcontentms;
	}

	 
	
	

}
