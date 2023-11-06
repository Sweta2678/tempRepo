/**
 * 
 */
package com.plmviewer.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author uthanasekarapandian
 *
 */
public class MaterialVo {
	
	private String materialName ="";
	private BigDecimal materialTypeId;
	private String partPrimaryImageUrl;
	
	private BigDecimal ida3masterreference;
	private String activeMaterial ="";
	private String assignee ="";
	private String autoCreateMultiColor ="";
	private String brand ="";
	private String bulkTestRegime ="";
	private String careCards ="";
	private String careInstructions ="";
	private String cmParentGroup ="";
	private String conceptReferencePart ="";
	private String conflictFlag ="";
	private BigDecimal currentVersion;
	private String developmentTeams;
	private String devIntros;
	private String ecVEarlyDevDesingerName;
	private String ecVMMSlotNumber;
	private String htsCode;
	private String longLeadTime;
	private String materialComments;
	private String materialUsedByWearables;
	private String mcg;
	private String partNumber;
	private BigDecimal preConceptMaterial;
	private String resolveConflictFlag;
	private String shortName;
	private BigDecimal stdCostAllSupp;
	private String stdCostCurrency;
	private String stdCostEffectivityAllSupp;
	private String stdCostEstimateFlagAllSupp;
	private String stdCostFyAllSupp;
	private String subType;
	private String variant;
	private String washingInstructions="";
	private String buckleType="" ;
	private String cadATA ;
	private String cadETA ;
	private String dap ;
	private String  inkColor ;
	private String  logoType ;
	private String  matingParts;
	private String modelATA ;
	private String modelETA ;
	private BigDecimal numberPC; 
	private String  prongMfgMethod;
	
	private String  adhesive="";
	private String  texture="";
	private String chainLength="";
	private String chainType="";
	private String channelWidth="";
	private String jawOpening="";
	private BigDecimal numLinks;
	private String  orbSize="";
	private String  orbSolderPoints=""; 
	private String  orbWireLength="";
	private String  ringType="";
	private String  rivetType="";
	private String  surfaceFinishAllSupp="";
	private String   limitations="";
	private String   materialTestRegime="";
	private String standardCuttableWidth="";
    private String fiberTypeAllSupp ="";
	private String  averageSkinSizeAllSupp ="";
	private String   commonName ="";
	private String  fancy ="";
	private String   grainTypeAllSupp ="";
	private String  hideSegmentation ="";
	private String   leatherEndUse =""; 
	private String   patentFinish ="";
	private String   patternRepeat ="";
	private String   wearablesLeatherType ="";
	 private String   materialContentAllSupp ="";
	 private String   materialType="" ; 
	 
	 private String   boxType ="";
	 private String   channelUse="" ;
	 private String  contentAllSupp="" ;
	 private String   dimensions ;
	 private String   insertType ;
	 private String   insertType2 ;
	 private String   labelType ;
	 private String   packagingCategory ;
	 
	 
	 private String   backingContentAllSupp ;
	 private String  baseFabricName;
	 private String   coloringMethodAllSupp ;
	 private String   gcc  ;
	 private String   typeOfWeaveAllSupp  ;
	 private String   woolType  ;
	 private String   yarnSizeAllSupp  ;
	 private String   contentWithBackingAllSupp ;
	 private String   cottonType  ;
	 private String   fabricConstructionAllSupp  ;
	 private String   fabricContentAllSupp  ;
	 private String   faceFabricWeightAllSupp  ;
	 private String   knitPileConstruction ;
	 private String   knitType  ;
	 private String   liningWeightAllSupp  ;
	 
	 
	 private String   thicknessAllSupp;
	 private String   totalContentAllSupp;
	 private String   careAndContent;
	 private String   coreContentAllSupp; 
	 private String   finishedGoodsCOO;
	 private String foldPosition;
	 private String fsBullet;
	 private String   gender ;
	 private String   labelConstruction;
	 private String   labelSize;
	 private String   openingSide;
	 private String   sided;
	 private String   sizeDescription;
	 private String   sizeDesignation;
	 private String   surfaceContent;
	 private String   content;
	 private String   lubricated;
	 private String   stitchingPurpose	 ;
	 
	 private String  separator ;
	 private String  tapeContentAllSupp ;
	 private String  teethContentAllSupp;
	 
	 private Map<BigDecimal,String> materialSupplier;
	 
	 private MaterialSupplierVo matSupplierVo;
	 
	/**
	 * @return the matSupplierVo
	 */
	public MaterialSupplierVo getMatSupplierVo() {
		return matSupplierVo;
	}
	/**
	 * @param matSupplierVo the matSupplierVo to set
	 */
	public void setMatSupplierVo(MaterialSupplierVo matSupplierVo) {
		this.matSupplierVo = matSupplierVo;
	}
	/**
	 * @return the activeMaterial
	 */
	public String getActiveMaterial() {
		return activeMaterial;
	}
	/**
	 * @param activeMaterial the activeMaterial to set
	 */
	public void setActiveMaterial(String activeMaterial) {
		this.activeMaterial = activeMaterial;
	}
	/**
	 * @return the assignee
	 */
	public String getAssignee() {
		return assignee;
	}
	/**
	 * @param assignee the assignee to set
	 */
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	/**
	 * @return the autoCreateMultiColor
	 */
	public String getAutoCreateMultiColor() {
		return autoCreateMultiColor;
	}
	/**
	 * @param autoCreateMultiColor the autoCreateMultiColor to set
	 */
	public void setAutoCreateMultiColor(String autoCreateMultiColor) {
		this.autoCreateMultiColor = autoCreateMultiColor;
	}
	/**
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}
	/**
	 * @param brand the brand to set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}
	/**
	 * @return the bulkTestRegime
	 */
	public String getBulkTestRegime() {
		return bulkTestRegime;
	}
	/**
	 * @param bulkTestRegime the bulkTestRegime to set
	 */
	public void setBulkTestRegime(String bulkTestRegime) {
		this.bulkTestRegime = bulkTestRegime;
	}
	/**
	 * @return the careCards
	 */
	public String getCareCards() {
		return careCards;
	}
	/**
	 * @param careCards the careCards to set
	 */
	public void setCareCards(String careCards) {
		this.careCards = careCards;
	}
	/**
	 * @return the careInstructions
	 */
	public String getCareInstructions() {
		return careInstructions;
	}
	/**
	 * @param careInstructions the careInstructions to set
	 */
	public void setCareInstructions(String careInstructions) {
		this.careInstructions = careInstructions;
	}
	/**
	 * @return the cmParentGroup
	 */
	public String getCmParentGroup() {
		return cmParentGroup;
	}
	/**
	 * @param cmParentGroup the cmParentGroup to set
	 */
	public void setCmParentGroup(String cmParentGroup) {
		this.cmParentGroup = cmParentGroup;
	}
	/**
	 * @return the conceptReferencePart
	 */
	public String getConceptReferencePart() {
		return conceptReferencePart;
	}
	/**
	 * @param conceptReferencePart the conceptReferencePart to set
	 */
	public void setConceptReferencePart(String conceptReferencePart) {
		this.conceptReferencePart = conceptReferencePart;
	}
	/**
	 * @return the conflictFlag
	 */
	public String getConflictFlag() {
		return conflictFlag;
	}
	/**
	 * @param conflictFlag the conflictFlag to set
	 */
	public void setConflictFlag(String conflictFlag) {
		this.conflictFlag = conflictFlag;
	}
	/**
	 * @return the currentVersion
	 */
	public BigDecimal getCurrentVersion() {
		return currentVersion;
	}
	/**
	 * @param currentVersion the currentVersion to set
	 */
	public void setCurrentVersion(BigDecimal currentVersion) {
		this.currentVersion = currentVersion;
	}
	/**
	 * @return the developmentTeams
	 */
	public String getDevelopmentTeams() {
		return developmentTeams;
	}
	/**
	 * @param developmentTeams the developmentTeams to set
	 */
	public void setDevelopmentTeams(String developmentTeams) {
		this.developmentTeams = developmentTeams;
	}
	/**
	 * @return the devIntros
	 */
	public String getDevIntros() {
		return devIntros;
	}
	/**
	 * @param devIntros the devIntros to set
	 */
	public void setDevIntros(String devIntros) {
		this.devIntros = devIntros;
	}
	/**
	 * @return the ecVEarlyDevDesingerName
	 */
	public String getEcVEarlyDevDesingerName() {
		return ecVEarlyDevDesingerName;
	}
	/**
	 * @param ecVEarlyDevDesingerName the ecVEarlyDevDesingerName to set
	 */
	public void setEcVEarlyDevDesingerName(String ecVEarlyDevDesingerName) {
		this.ecVEarlyDevDesingerName = ecVEarlyDevDesingerName;
	}
	/**
	 * @return the ecVMMSlotNumber
	 */
	public String getEcVMMSlotNumber() {
		return ecVMMSlotNumber;
	}
	/**
	 * @param ecVMMSlotNumber the ecVMMSlotNumber to set
	 */
	public void setEcVMMSlotNumber(String ecVMMSlotNumber) {
		this.ecVMMSlotNumber = ecVMMSlotNumber;
	}
	/**
	 * @return the htsCode
	 */
	public String getHtsCode() {
		return htsCode;
	}
	/**
	 * @param htsCode the htsCode to set
	 */
	public void setHtsCode(String htsCode) {
		this.htsCode = htsCode;
	}
	/**
	 * @return the longLeadTime
	 */
	public String getLongLeadTime() {
		return longLeadTime;
	}
	/**
	 * @param longLeadTime the longLeadTime to set
	 */
	public void setLongLeadTime(String longLeadTime) {
		this.longLeadTime = longLeadTime;
	}
	/**
	 * @return the materialComments
	 */
	public String getMaterialComments() {
		return materialComments;
	}
	/**
	 * @param materialComments the materialComments to set
	 */
	public void setMaterialComments(String materialComments) {
		this.materialComments = materialComments;
	}
	/**
	 * @return the materialUsedByWearables
	 */
	public String getMaterialUsedByWearables() {
		return materialUsedByWearables;
	}
	/**
	 * @param materialUsedByWearables the materialUsedByWearables to set
	 */
	public void setMaterialUsedByWearables(String materialUsedByWearables) {
		this.materialUsedByWearables = materialUsedByWearables;
	}
	/**
	 * @return the mcg
	 */
	public String getMcg() {
		return mcg;
	}
	/**
	 * @param mcg the mcg to set
	 */
	public void setMcg(String mcg) {
		this.mcg = mcg;
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
	/**
	 * @return the preConceptMaterial
	 */
	public BigDecimal getPreConceptMaterial() {
		return preConceptMaterial;
	}
	/**
	 * @param preConceptMaterial the preConceptMaterial to set
	 */
	public void setPreConceptMaterial(BigDecimal preConceptMaterial) {
		this.preConceptMaterial = preConceptMaterial;
	}
	/**
	 * @return the resolveConflictFlag
	 */
	public String  getResolveConflictFlag() {
		return resolveConflictFlag;
	}
	/**
	 * @param resolveConflictFlag the resolveConflictFlag to set
	 */
	public void setResolveConflictFlag(String resolveConflictFlag) {
		this.resolveConflictFlag = resolveConflictFlag;
	}
	/**
	 * @return the shortName
	 */
	public String getShortName() {
		return shortName;
	}
	/**
	 * @param shortName the shortName to set
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	/**
	 * @return the stdCostAllSupp
	 */
	public BigDecimal getStdCostAllSupp() {
		return stdCostAllSupp;
	}
	/**
	 * @param stdCostAllSupp the stdCostAllSupp to set
	 */
	public void setStdCostAllSupp(BigDecimal stdCostAllSupp) {
		this.stdCostAllSupp = stdCostAllSupp;
	}
	/**
	 * @return the stdCostCurrency
	 */
	public String getStdCostCurrency() {
		return stdCostCurrency;
	}
	/**
	 * @param stdCostCurrency the stdCostCurrency to set
	 */
	public void setStdCostCurrency(String stdCostCurrency) {
		this.stdCostCurrency = stdCostCurrency;
	}
	/**
	 * @return the stdCostEffectivityAllSupp
	 */
	public String getStdCostEffectivityAllSupp() {
		return stdCostEffectivityAllSupp;
	}
	/**
	 * @param stdCostEffectivityAllSupp the stdCostEffectivityAllSupp to set
	 */
	public void setStdCostEffectivityAllSupp(String stdCostEffectivityAllSupp) {
		this.stdCostEffectivityAllSupp = stdCostEffectivityAllSupp;
	}
	/**
	 * @return the stdCostEstimateFlagAllSupp
	 */
	public String getStdCostEstimateFlagAllSupp() {
		return stdCostEstimateFlagAllSupp;
	}
	/**
	 * @param stdCostEstimateFlagAllSupp the stdCostEstimateFlagAllSupp to set
	 */
	public void setStdCostEstimateFlagAllSupp(String stdCostEstimateFlagAllSupp) {
		this.stdCostEstimateFlagAllSupp = stdCostEstimateFlagAllSupp;
	}
	/**
	 * @return the stdCostFyAllSupp
	 */
	public String getStdCostFyAllSupp() {
		return stdCostFyAllSupp;
	}
	/**
	 * @param stdCostFyAllSupp the stdCostFyAllSupp to set
	 */
	public void setStdCostFyAllSupp(String stdCostFyAllSupp) {
		this.stdCostFyAllSupp = stdCostFyAllSupp;
	}
	/**
	 * @return the subType
	 */
	public String getSubType() {
		return subType;
	}
	/**
	 * @param subType the subType to set
	 */
	public void setSubType(String subType) {
		this.subType = subType;
	}
	/**
	 * @return the variant
	 */
	public String getVariant() {
		return variant;
	}
	/**
	 * @param variant the variant to set
	 */
	public void setVariant(String variant) {
		this.variant = variant;
	}
	/**
	 * @return the washingInstructions
	 */
	public String getWashingInstructions() {
		return washingInstructions;
	}
	/**
	 * @param washingInstructions the washingInstructions to set
	 */
	public void setWashingInstructions(String washingInstructions) {
		this.washingInstructions = washingInstructions;
	}
	/**
	 * @return the buckleType
	 */
	public String getBuckleType() {
		return buckleType;
	}
	/**
	 * @param buckleType the buckleType to set
	 */
	public void setBuckleType(String buckleType) {
		this.buckleType = buckleType;
	}
	/**
	 * @return the cadATA
	 */
	public String getCadATA() {
		return cadATA;
	}
	/**
	 * @param cadATA the cadATA to set
	 */
	public void setCadATA(String cadATA) {
		this.cadATA = cadATA;
	}
	/**
	 * @return the cadETA
	 */
	public String getCadETA() {
		return cadETA;
	}
	/**
	 * @param cadETA the cadETA to set
	 */
	public void setCadETA(String cadETA) {
		this.cadETA = cadETA;
	}
	/**
	 * @return the dap
	 */
	public String getDap() {
		return dap;
	}
	/**
	 * @param dap the dap to set
	 */
	public void setDap(String dap) {
		this.dap = dap;
	}
	/**
	 * @return the inkColor
	 */
	public String getInkColor() {
		return inkColor;
	}
	/**
	 * @param inkColor the inkColor to set
	 */
	public void setInkColor(String inkColor) {
		this.inkColor = inkColor;
	}
	/**
	 * @return the logoType
	 */
	public String getLogoType() {
		return logoType;
	}
	/**
	 * @param logoType the logoType to set
	 */
	public void setLogoType(String logoType) {
		this.logoType = logoType;
	}
	/**
	 * @return the matingParts
	 */
	public String getMatingParts() {
		return matingParts;
	}
	/**
	 * @param matingParts the matingParts to set
	 */
	public void setMatingParts(String matingParts) {
		this.matingParts = matingParts;
	}
	/**
	 * @return the modelATA
	 */
	public String getModelATA() {
		return modelATA;
	}
	/**
	 * @param modelATA the modelATA to set
	 */
	public void setModelATA(String modelATA) {
		this.modelATA = modelATA;
	}
	/**
	 * @return the modelETA
	 */
	public String getModelETA() {
		return modelETA;
	}
	/**
	 * @param modelETA the modelETA to set
	 */
	public void setModelETA(String modelETA) {
		this.modelETA = modelETA;
	}
	/**
	 * @return the numberPC
	 */
	public BigDecimal getNumberPC() {
		return numberPC;
	}
	/**
	 * @param numberPC the numberPC to set
	 */
	public void setNumberPC(BigDecimal numberPC) {
		this.numberPC = numberPC;
	}
	/**
	 * @return the prongMfgMethod
	 */
	public String getProngMfgMethod() {
		return prongMfgMethod;
	}
	/**
	 * @param prongMfgMethod the prongMfgMethod to set
	 */
	public void setProngMfgMethod(String prongMfgMethod) {
		this.prongMfgMethod = prongMfgMethod;
	}
	/**
	 * @return the adhesive
	 */
	public String getAdhesive() {
		return adhesive;
	}
	/**
	 * @param adhesive the adhesive to set
	 */
	public void setAdhesive(String adhesive) {
		this.adhesive = adhesive;
	}
	/**
	 * @return the texture
	 */
	public String getTexture() {
		return texture;
	}
	/**
	 * @param texture the texture to set
	 */
	public void setTexture(String texture) {
		this.texture = texture;
	}
	/**
	 * @return the chainLength
	 */
	public String getChainLength() {
		return chainLength;
	}
	/**
	 * @param chainLength the chainLength to set
	 */
	public void setChainLength(String chainLength) {
		this.chainLength = chainLength;
	}
	/**
	 * @return the chainType
	 */
	public String getChainType() {
		return chainType;
	}
	/**
	 * @param chainType the chainType to set
	 */
	public void setChainType(String chainType) {
		this.chainType = chainType;
	}
	/**
	 * @return the channelWidth
	 */
	public String getChannelWidth() {
		return channelWidth;
	}
	/**
	 * @param channelWidth the channelWidth to set
	 */
	public void setChannelWidth(String channelWidth) {
		this.channelWidth = channelWidth;
	}
	/**
	 * @return the jawOpening
	 */
	public String getJawOpening() {
		return jawOpening;
	}
	/**
	 * @param jawOpening the jawOpening to set
	 */
	public void setJawOpening(String jawOpening) {
		this.jawOpening = jawOpening;
	}
	/**
	 * @return the numLinks
	 */
	public BigDecimal getNumLinks() {
		return numLinks;
	}
	/**
	 * @param numLinks the numLinks to set
	 */
	public void setNumLinks(BigDecimal numLinks) {
		this.numLinks = numLinks;
	}
	/**
	 * @return the orbSize
	 */
	public String getOrbSize() {
		return orbSize;
	}
	/**
	 * @param orbSize the orbSize to set
	 */
	public void setOrbSize(String orbSize) {
		this.orbSize = orbSize;
	}
	/**
	 * @return the orbSolderPoints
	 */
	public String getOrbSolderPoints() {
		return orbSolderPoints;
	}
	/**
	 * @param orbSolderPoints the orbSolderPoints to set
	 */
	public void setOrbSolderPoints(String orbSolderPoints) {
		this.orbSolderPoints = orbSolderPoints;
	}
	/**
	 * @return the orbWireLength
	 */
	public String getOrbWireLength() {
		return orbWireLength;
	}
	/**
	 * @param orbWireLength the orbWireLength to set
	 */
	public void setOrbWireLength(String orbWireLength) {
		this.orbWireLength = orbWireLength;
	}
	/**
	 * @return the ringType
	 */
	public String getRingType() {
		return ringType;
	}
	/**
	 * @param ringType the ringType to set
	 */
	public void setRingType(String ringType) {
		this.ringType = ringType;
	}
	/**
	 * @return the rivetType
	 */
	public String getRivetType() {
		return rivetType;
	}
	/**
	 * @param rivetType the rivetType to set
	 */
	public void setRivetType(String rivetType) {
		this.rivetType = rivetType;
	}
	/**
	 * @return the surfaceFinishAllSupp
	 */
	public String getSurfaceFinishAllSupp() {
		return surfaceFinishAllSupp;
	}
	/**
	 * @param surfaceFinishAllSupp the surfaceFinishAllSupp to set
	 */
	public void setSurfaceFinishAllSupp(String surfaceFinishAllSupp) {
		this.surfaceFinishAllSupp = surfaceFinishAllSupp;
	}
	/**
	 * @return the limitations
	 */
	public String getLimitations() {
		return limitations;
	}
	/**
	 * @param limitations the limitations to set
	 */
	public void setLimitations(String limitations) {
		this.limitations = limitations;
	}
	/**
	 * @return the materialTestRegime
	 */
	public String getMaterialTestRegime() {
		return materialTestRegime;
	}
	/**
	 * @param materialTestRegime the materialTestRegime to set
	 */
	public void setMaterialTestRegime(String materialTestRegime) {
		this.materialTestRegime = materialTestRegime;
	}
	/**
	 * @return the averageSkinSizeAllSupp
	 */
	public String getAverageSkinSizeAllSupp() {
		return averageSkinSizeAllSupp;
	}
	/**
	 * @param averageSkinSizeAllSupp the averageSkinSizeAllSupp to set
	 */
	public void setAverageSkinSizeAllSupp(String averageSkinSizeAllSupp) {
		this.averageSkinSizeAllSupp = averageSkinSizeAllSupp;
	}
	/**
	 * @return the commonName
	 */
	public String getCommonName() {
		return commonName;
	}
	/**
	 * @param commonName the commonName to set
	 */
	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}
	/**
	 * @return the fancy
	 */
	public String getFancy() {
		return fancy;
	}
	/**
	 * @param fancy the fancy to set
	 */
	public void setFancy(String fancy) {
		this.fancy = fancy;
	}
	/**
	 * @return the grainTypeAllSupp
	 */
	public String getGrainTypeAllSupp() {
		return grainTypeAllSupp;
	}
	/**
	 * @param grainTypeAllSupp the grainTypeAllSupp to set
	 */
	public void setGrainTypeAllSupp(String grainTypeAllSupp) {
		this.grainTypeAllSupp = grainTypeAllSupp;
	}
	/**
	 * @return the hideSegmentation
	 */
	public String getHideSegmentation() {
		return hideSegmentation;
	}
	/**
	 * @param hideSegmentation the hideSegmentation to set
	 */
	public void setHideSegmentation(String hideSegmentation) {
		this.hideSegmentation = hideSegmentation;
	}
	/**
	 * @return the leatherEndUse
	 */
	public String getLeatherEndUse() {
		return leatherEndUse;
	}
	/**
	 * @param leatherEndUse the leatherEndUse to set
	 */
	public void setLeatherEndUse(String leatherEndUse) {
		this.leatherEndUse = leatherEndUse;
	}
	/**
	 * @return the patentFinish
	 */
	public String getPatentFinish() {
		return patentFinish;
	}
	/**
	 * @param patentFinish the patentFinish to set
	 */
	public void setPatentFinish(String patentFinish) {
		this.patentFinish = patentFinish;
	}
	/**
	 * @return the patternRepeat
	 */
	public String getPatternRepeat() {
		return patternRepeat;
	}
	/**
	 * @param patternRepeat the patternRepeat to set
	 */
	public void setPatternRepeat(String patternRepeat) {
		this.patternRepeat = patternRepeat;
	}
	/**
	 * @return the wearablesLeatherType
	 */
	public String getWearablesLeatherType() {
		return wearablesLeatherType;
	}
	/**
	 * @param wearablesLeatherType the wearablesLeatherType to set
	 */
	public void setWearablesLeatherType(String wearablesLeatherType) {
		this.wearablesLeatherType = wearablesLeatherType;
	}
	/**
	 * @return the materialContentAllSupp
	 */
	public String getMaterialContentAllSupp() {
		return materialContentAllSupp;
	}
	/**
	 * @param materialContentAllSupp the materialContentAllSupp to set
	 */
	public void setMaterialContentAllSupp(String materialContentAllSupp) {
		this.materialContentAllSupp = materialContentAllSupp;
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
	 * @return the boxType
	 */
	public String getBoxType() {
		return boxType;
	}
	/**
	 * @param boxType the boxType to set
	 */
	public void setBoxType(String boxType) {
		this.boxType = boxType;
	}
	/**
	 * @return the channelUse
	 */
	public String getChannelUse() {
		return channelUse;
	}
	/**
	 * @param channelUse the channelUse to set
	 */
	public void setChannelUse(String channelUse) {
		this.channelUse = channelUse;
	}
	/**
	 * @return the contentAllSupp
	 */
	public String getContentAllSupp() {
		return contentAllSupp;
	}
	/**
	 * @param contentAllSupp the contentAllSupp to set
	 */
	public void setContentAllSupp(String contentAllSupp) {
		this.contentAllSupp = contentAllSupp;
	}
	/**
	 * @return the dimensions
	 */
	public String getDimensions() {
		return dimensions;
	}
	/**
	 * @param dimensions the dimensions to set
	 */
	public void setDimensions(String dimensions) {
		this.dimensions = dimensions;
	}
	/**
	 * @return the insertType
	 */
	public String getInsertType() {
		return insertType;
	}
	/**
	 * @param insertType the insertType to set
	 */
	public void setInsertType(String insertType) {
		this.insertType = insertType;
	}
	/**
	 * @return the insertType2
	 */
	public String getInsertType2() {
		return insertType2;
	}
	/**
	 * @param insertType2 the insertType2 to set
	 */
	public void setInsertType2(String insertType2) {
		this.insertType2 = insertType2;
	}
	/**
	 * @return the labelType
	 */
	public String getLabelType() {
		return labelType;
	}
	/**
	 * @param labelType the labelType to set
	 */
	public void setLabelType(String labelType) {
		this.labelType = labelType;
	}
	/**
	 * @return the packagingCategory
	 */
	public String getPackagingCategory() {
		return packagingCategory;
	}
	/**
	 * @param packagingCategory the packagingCategory to set
	 */
	public void setPackagingCategory(String packagingCategory) {
		this.packagingCategory = packagingCategory;
	}
	/**
	 * @return the backingContentAllSupp
	 */
	public String getBackingContentAllSupp() {
		return backingContentAllSupp;
	}
	/**
	 * @param backingContentAllSupp the backingContentAllSupp to set
	 */
	public void setBackingContentAllSupp(String backingContentAllSupp) {
		this.backingContentAllSupp = backingContentAllSupp;
	}
	/**
	 * @return the baseFabricName
	 */
	public String getBaseFabricName() {
		return baseFabricName;
	}
	/**
	 * @param baseFabricName the baseFabricName to set
	 */
	public void setBaseFabricName(String baseFabricName) {
		this.baseFabricName = baseFabricName;
	}
	/**
	 * @return the coloringMethodAllSupp
	 */
	public String getColoringMethodAllSupp() {
		return coloringMethodAllSupp;
	}
	/**
	 * @param coloringMethodAllSupp the coloringMethodAllSupp to set
	 */
	public void setColoringMethodAllSupp(String coloringMethodAllSupp) {
		this.coloringMethodAllSupp = coloringMethodAllSupp;
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
	 * @return the typeOfWeaveAllSupp
	 */
	public String getTypeOfWeaveAllSupp() {
		return typeOfWeaveAllSupp;
	}
	/**
	 * @param typeOfWeaveAllSupp the typeOfWeaveAllSupp to set
	 */
	public void setTypeOfWeaveAllSupp(String typeOfWeaveAllSupp) {
		this.typeOfWeaveAllSupp = typeOfWeaveAllSupp;
	}
	/**
	 * @return the woolType
	 */
	public String getWoolType() {
		return woolType;
	}
	/**
	 * @param woolType the woolType to set
	 */
	public void setWoolType(String woolType) {
		this.woolType = woolType;
	}
	/**
	 * @return the yarnSizeAllSupp
	 */
	public String getYarnSizeAllSupp() {
		return yarnSizeAllSupp;
	}
	/**
	 * @param yarnSizeAllSupp the yarnSizeAllSupp to set
	 */
	public void setYarnSizeAllSupp(String yarnSizeAllSupp) {
		this.yarnSizeAllSupp = yarnSizeAllSupp;
	}
	/**
	 * @return the contentWithBackingAllSupp
	 */
	public String getContentWithBackingAllSupp() {
		return contentWithBackingAllSupp;
	}
	/**
	 * @param contentWithBackingAllSupp the contentWithBackingAllSupp to set
	 */
	public void setContentWithBackingAllSupp(String contentWithBackingAllSupp) {
		this.contentWithBackingAllSupp = contentWithBackingAllSupp;
	}
	/**
	 * @return the cottonType
	 */
	public String getCottonType() {
		return cottonType;
	}
	/**
	 * @param cottonType the cottonType to set
	 */
	public void setCottonType(String cottonType) {
		this.cottonType = cottonType;
	}
	/**
	 * @return the fabricConstructionAllSupp
	 */
	public String getFabricConstructionAllSupp() {
		return fabricConstructionAllSupp;
	}
	/**
	 * @param fabricConstructionAllSupp the fabricConstructionAllSupp to set
	 */
	public void setFabricConstructionAllSupp(String fabricConstructionAllSupp) {
		this.fabricConstructionAllSupp = fabricConstructionAllSupp;
	}
	/**
	 * @return the fabricContentAllSupp
	 */
	public String getFabricContentAllSupp() {
		return fabricContentAllSupp;
	}
	/**
	 * @param fabricContentAllSupp the fabricContentAllSupp to set
	 */
	public void setFabricContentAllSupp(String fabricContentAllSupp) {
		this.fabricContentAllSupp = fabricContentAllSupp;
	}
	/**
	 * @return the faceFabricWeightAllSupp
	 */
	public String getFaceFabricWeightAllSupp() {
		return faceFabricWeightAllSupp;
	}
	/**
	 * @param faceFabricWeightAllSupp the faceFabricWeightAllSupp to set
	 */
	public void setFaceFabricWeightAllSupp(String faceFabricWeightAllSupp) {
		this.faceFabricWeightAllSupp = faceFabricWeightAllSupp;
	}
	/**
	 * @return the knitPileConstruction
	 */
	public String getKnitPileConstruction() {
		return knitPileConstruction;
	}
	/**
	 * @param knitPileConstruction the knitPileConstruction to set
	 */
	public void setKnitPileConstruction(String knitPileConstruction) {
		this.knitPileConstruction = knitPileConstruction;
	}
	/**
	 * @return the knitType
	 */
	public String getKnitType() {
		return knitType;
	}
	/**
	 * @param knitType the knitType to set
	 */
	public void setKnitType(String knitType) {
		this.knitType = knitType;
	}
	/**
	 * @return the liningWeightAllSupp
	 */
	public String getLiningWeightAllSupp() {
		return liningWeightAllSupp;
	}
	/**
	 * @param liningWeightAllSupp the liningWeightAllSupp to set
	 */
	public void setLiningWeightAllSupp(String liningWeightAllSupp) {
		this.liningWeightAllSupp = liningWeightAllSupp;
	}
	/**
	 * @return the thicknessAllSupp
	 */
	public String getThicknessAllSupp() {
		return thicknessAllSupp;
	}
	/**
	 * @param thicknessAllSupp the thicknessAllSupp to set
	 */
	public void setThicknessAllSupp(String thicknessAllSupp) {
		this.thicknessAllSupp = thicknessAllSupp;
	}
	/**
	 * @return the totalContentAllSupp
	 */
	public String getTotalContentAllSupp() {
		return totalContentAllSupp;
	}
	/**
	 * @param totalContentAllSupp the totalContentAllSupp to set
	 */
	public void setTotalContentAllSupp(String totalContentAllSupp) {
		this.totalContentAllSupp = totalContentAllSupp;
	}
	/**
	 * @return the careAndContent
	 */
	public String getCareAndContent() {
		return careAndContent;
	}
	/**
	 * @param careAndContent the careAndContent to set
	 */
	public void setCareAndContent(String careAndContent) {
		this.careAndContent = careAndContent;
	}
	/**
	 * @return the coreContentAllSupp
	 */
	public String getCoreContentAllSupp() {
		return coreContentAllSupp;
	}
	/**
	 * @param coreContentAllSupp the coreContentAllSupp to set
	 */
	public void setCoreContentAllSupp(String coreContentAllSupp) {
		this.coreContentAllSupp = coreContentAllSupp;
	}
	/**
	 * @return the finishedGoodsCOO
	 */
	public String getFinishedGoodsCOO() {
		return finishedGoodsCOO;
	}
	/**
	 * @param finishedGoodsCOO the finishedGoodsCOO to set
	 */
	public void setFinishedGoodsCOO(String finishedGoodsCOO) {
		this.finishedGoodsCOO = finishedGoodsCOO;
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
	 * @return the labelConstruction
	 */
	public String getLabelConstruction() {
		return labelConstruction;
	}
	/**
	 * @param labelConstruction the labelConstruction to set
	 */
	public void setLabelConstruction(String labelConstruction) {
		this.labelConstruction = labelConstruction;
	}
	/**
	 * @return the labelSize
	 */
	public String getLabelSize() {
		return labelSize;
	}
	/**
	 * @param labelSize the labelSize to set
	 */
	public void setLabelSize(String labelSize) {
		this.labelSize = labelSize;
	}
	/**
	 * @return the openingSide
	 */
	public String getOpeningSide() {
		return openingSide;
	}
	/**
	 * @param openingSide the openingSide to set
	 */
	public void setOpeningSide(String openingSide) {
		this.openingSide = openingSide;
	}
	/**
	 * @return the sided
	 */
	public String getSided() {
		return sided;
	}
	/**
	 * @param sided the sided to set
	 */
	public void setSided(String sided) {
		this.sided = sided;
	}
	/**
	 * @return the sizeDescription
	 */
	public String getSizeDescription() {
		return sizeDescription;
	}
	/**
	 * @param sizeDescription the sizeDescription to set
	 */
	public void setSizeDescription(String sizeDescription) {
		this.sizeDescription = sizeDescription;
	}
	/**
	 * @return the sizeDesignation
	 */
	public String getSizeDesignation() {
		return sizeDesignation;
	}
	/**
	 * @param sizeDesignation the sizeDesignation to set
	 */
	public void setSizeDesignation(String sizeDesignation) {
		this.sizeDesignation = sizeDesignation;
	}
	/**
	 * @return the surfaceContent
	 */
	public String getSurfaceContent() {
		return surfaceContent;
	}
	/**
	 * @param surfaceContent the surfaceContent to set
	 */
	public void setSurfaceContent(String surfaceContent) {
		this.surfaceContent = surfaceContent;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the lubricated
	 */
	public String getLubricated() {
		return lubricated;
	}
	/**
	 * @param lubricated the lubricated to set
	 */
	public void setLubricated(String lubricated) {
		this.lubricated = lubricated;
	}
	/**
	 * @return the stitchingPurpose
	 */
	public String getStitchingPurpose() {
		return stitchingPurpose;
	}
	/**
	 * @param stitchingPurpose the stitchingPurpose to set
	 */
	public void setStitchingPurpose(String stitchingPurpose) {
		this.stitchingPurpose = stitchingPurpose;
	}
	/**
	 * @return the separator
	 */
	public String getSeparator() {
		return separator;
	}
	/**
	 * @param separator the separator to set
	 */
	public void setSeparator(String separator) {
		this.separator = separator;
	}
	/**
	 * @return the tapeContentAllSupp
	 */
	public String getTapeContentAllSupp() {
		return tapeContentAllSupp;
	}
	/**
	 * @param tapeContentAllSupp the tapeContentAllSupp to set
	 */
	public void setTapeContentAllSupp(String tapeContentAllSupp) {
		this.tapeContentAllSupp = tapeContentAllSupp;
	}
	/**
	 * @return the teethContentAllSupp
	 */
	public String getTeethContentAllSupp() {
		return teethContentAllSupp;
	}
	/**
	 * @param teethContentAllSupp the teethContentAllSupp to set
	 */
	public void setTeethContentAllSupp(String teethContentAllSupp) {
		this.teethContentAllSupp = teethContentAllSupp;
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
	 * @return the ida3masterreference
	 */
	public BigDecimal getIda3masterreference() {
		return ida3masterreference;
	}
	/**
	 * @param ida3masterreference the ida3masterreference to set
	 */
	public void setIda3masterreference(BigDecimal ida3masterreference) {
		this.ida3masterreference = ida3masterreference;
	}
	/**
	 * @return the foldPosition
	 */
	public String getFoldPosition() {
		return foldPosition;
	}
	/**
	 * @param foldPosition the foldPosition to set
	 */
	public void setFoldPosition(String foldPosition) {
		this.foldPosition = foldPosition;
	}
	/**
	 * @return the fsBullet
	 */
	public String getFsBullet() {
		return fsBullet;
	}
	/**
	 * @param fsBullet the fsBullet to set
	 */
	public void setFsBullet(String fsBullet) {
		this.fsBullet = fsBullet;
	}

	/**
	 * @return the standardCuttableWidth
	 */
	public String getStandardCuttableWidth() {
		return standardCuttableWidth;
	}
	/**
	 * @param standardCuttableWidth the standardCuttableWidth to set
	 */
	public void setStandardCuttableWidth(String standardCuttableWidth) {
		this.standardCuttableWidth = standardCuttableWidth;
	}
	/**
	 * @return the fiberTypeAllSupp
	 */
	public String getFiberTypeAllSupp() {
		return fiberTypeAllSupp;
	}
	/**
	 * @param fiberTypeAllSupp the fiberTypeAllSupp to set
	 */
	public void setFiberTypeAllSupp(String fiberTypeAllSupp) {
		this.fiberTypeAllSupp = fiberTypeAllSupp;
	}
	/**
	 * @return the materialSupplier
	 */
	public Map<BigDecimal,String> getMaterialSupplier() {
		return materialSupplier;
	}
	/**
	 * @param materialSupplier the materialSupplier to set
	 */
	public void setMaterialSupplier(Map<BigDecimal,String> materialSupplier) {
		this.materialSupplier = materialSupplier;
	}
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
	 * @return the partPrimaryImageUrl
	 */
	public String getPartPrimaryImageUrl() {
		return partPrimaryImageUrl;
	}
	/**
	 * @param partPrimaryImageUrl the partPrimaryImageUrl to set
	 */
	public void setPartPrimaryImageUrl(String partPrimaryImageUrl) {
		this.partPrimaryImageUrl = partPrimaryImageUrl;
	}

}
