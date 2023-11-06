/**
 * 
 */
package com.plmviewer.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author uthanasekarapandian
 *
 */
public class SkuAdditionalDetailsVo {
	private String skuStyleName;
	
	private String deleteDate;
	private String  signatureType;
	private String  fsDeleteDate;
	private String  fsIntroDate;
	private String  hardwareColor;
	private String  introDate;
	private String  channelExclusive;
	private String  look;
	private BigDecimal  targetCost;
	private BigDecimal  targetFSPromoGMPct;
	private BigDecimal  targetFSTargetGMPct;
	private String  designRequestComments;
	private String CalcWholeSale1;
	private BigDecimal poCost;
	private BigDecimal  stdFSPromoGMPct;
	private BigDecimal  stdFSTicketGMPct;
	private BigDecimal targetRetailGMPct;
	private String  teamAD;
	private String teamAsstAD;
	private String  teamAsstDD;
	private String  teamAsstDesigner;
	private String teamDD;
	private String teamDesigner1;
	private String teamDesigner2;
	private String teamEngineer;
	private String teamMerchant1;
	private String teamMerchant2;
	private String teamPreProduction;
	private String teamProduction;
	
	private BigDecimal totalCost;
	private String earlyReleaseDate;
	private BigDecimal freightCost;
	
	private String  coachSkuGroup;
	private String  finishedGoodColorCodes;
	private BigDecimal fpNetSalesQty;
	private BigDecimal fpWholesaleNetSales;
	private BigDecimal fsNetSalesCost;
	private BigDecimal  fsNetSalesQty;
	private BigDecimal fsWholesaleNetSales;
	private BigDecimal fpNetSalesCost;
	private String styleFGColorCode;
	private BigDecimal stdRetailGMPct;
	private String  abcCode;
	private String  status;
	private BigDecimal stdCost;
	private BigDecimal  stdCostWsaleMUPct;
	private String  exotic;
	private String  factorystoreTypeSku;
	private BigDecimal finalStandard;
	private BigDecimal  mpg;
	private BigDecimal  unitVolumeFP;
	private BigDecimal  unitVolumeFS;
	private BigDecimal workingCost1;
	private BigDecimal  workingCost2;
	private BigDecimal  workingCostRetailGMPct;
	private String  longLeadTimeMaterial;
	private String  material;
	private String  oneTimeBuy;
	private BigDecimal  overhead;
	private BigDecimal  teamTechnicalDesigner;
	private BigDecimal  wholesaleMUPct;
	private BigDecimal  workingCost1FSPromoGMPct;
	private BigDecimal workingCost1FSTargetGMPct;
	private BigDecimal  workingCost1RetailGMPct;
	private String   team;
	private BigDecimal  sampleFacility;
	private String facecolor;
	private String  upccode ;
	private List<LCSMoaObjectVo> merchandisingFullPrice;

	/**
	 * @return the merchandisingFullPrice
	 */
	public List<LCSMoaObjectVo> getMerchandisingFullPrice() {
		return merchandisingFullPrice;
	}
	/**
	 * @param merchandisingFullPrice the merchandisingFullPrice to set
	 */
	public void setMerchandisingFullPrice(List<LCSMoaObjectVo> merchandisingFullPrice) {
		this.merchandisingFullPrice = merchandisingFullPrice;
	}
	/**
	 * @return the sizeScaleMoa
	 */
	public List<LCSMoaObjectVo> getSizeScaleMoa() {
		return sizeScaleMoa;
	}
	/**
	 * @param sizeScaleMoa the sizeScaleMoa to set
	 */
	public void setSizeScaleMoa(List<LCSMoaObjectVo> sizeScaleMoa) {
		this.sizeScaleMoa = sizeScaleMoa;
	}
	private List<LCSMoaObjectVo> sizeScaleMoa;
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
	 * @return the totalCost
	 */
	public BigDecimal getTotalCost() {
		return totalCost;
	}
	/**
	 * @param totalCost the totalCost to set
	 */
	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
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
	 * @return the coachSkuGroup
	 */
	public String getCoachSkuGroup() {
		return coachSkuGroup;
	}
	/**
	 * @param coachSkuGroup the coachSkuGroup to set
	 */
	public void setCoachSkuGroup(String coachSkuGroup) {
		this.coachSkuGroup = coachSkuGroup;
	}
	/**
	 * @return the finishedGoodColorCodes
	 */
	public String getFinishedGoodColorCodes() {
		return finishedGoodColorCodes;
	}
	/**
	 * @param finishedGoodColorCodes the finishedGoodColorCodes to set
	 */
	public void setFinishedGoodColorCodes(String finishedGoodColorCodes) {
		this.finishedGoodColorCodes = finishedGoodColorCodes;
	}
	/**
	 * @return the fpNetSalesQty
	 */
	public BigDecimal getFpNetSalesQty() {
		return fpNetSalesQty;
	}
	/**
	 * @param fpNetSalesQty the fpNetSalesQty to set
	 */
	public void setFpNetSalesQty(BigDecimal fpNetSalesQty) {
		this.fpNetSalesQty = fpNetSalesQty;
	}
	/**
	 * @return the fpWholesaleNetSales
	 */
	public BigDecimal getFpWholesaleNetSales() {
		return fpWholesaleNetSales;
	}
	/**
	 * @param fpWholesaleNetSales the fpWholesaleNetSales to set
	 */
	public void setFpWholesaleNetSales(BigDecimal fpWholesaleNetSales) {
		this.fpWholesaleNetSales = fpWholesaleNetSales;
	}
	/**
	 * @return the fsNetSalesCost
	 */
	public BigDecimal getFsNetSalesCost() {
		return fsNetSalesCost;
	}
	/**
	 * @param fsNetSalesCost the fsNetSalesCost to set
	 */
	public void setFsNetSalesCost(BigDecimal fsNetSalesCost) {
		this.fsNetSalesCost = fsNetSalesCost;
	}
	/**
	 * @return the fsNetSalesQty
	 */
	public BigDecimal getFsNetSalesQty() {
		return fsNetSalesQty;
	}
	/**
	 * @param fsNetSalesQty the fsNetSalesQty to set
	 */
	public void setFsNetSalesQty(BigDecimal fsNetSalesQty) {
		this.fsNetSalesQty = fsNetSalesQty;
	}
	/**
	 * @return the fsWholesaleNetSales
	 */
	public BigDecimal getFsWholesaleNetSales() {
		return fsWholesaleNetSales;
	}
	/**
	 * @param fsWholesaleNetSales the fsWholesaleNetSales to set
	 */
	public void setFsWholesaleNetSales(BigDecimal fsWholesaleNetSales) {
		this.fsWholesaleNetSales = fsWholesaleNetSales;
	}
	/**
	 * @return the fpNetSalesCost
	 */
	public BigDecimal getFpNetSalesCost() {
		return fpNetSalesCost;
	}
	/**
	 * @param fpNetSalesCost the fpNetSalesCost to set
	 */
	public void setFpNetSalesCost(BigDecimal fpNetSalesCost) {
		this.fpNetSalesCost = fpNetSalesCost;
	}
	/**
	 * @return the styleFGColorCode
	 */
	public String getStyleFGColorCode() {
		return styleFGColorCode;
	}
	/**
	 * @param styleFGColorCode the styleFGColorCode to set
	 */
	public void setStyleFGColorCode(String styleFGColorCode) {
		this.styleFGColorCode = styleFGColorCode;
	}
	/**
	 * @return the stdRetailGMPct
	 */
	public BigDecimal getStdRetailGMPct() {
		return stdRetailGMPct;
	}
	/**
	 * @param stdRetailGMPct the stdRetailGMPct to set
	 */
	public void setStdRetailGMPct(BigDecimal stdRetailGMPct) {
		this.stdRetailGMPct = stdRetailGMPct;
	}
	/**
	 * @return the abcCode
	 */
	public String getAbcCode() {
		return abcCode;
	}
	/**
	 * @param abcCode the abcCode to set
	 */
	public void setAbcCode(String abcCode) {
		this.abcCode = abcCode;
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
	/**
	 * @return the stdCost
	 */
	public BigDecimal getStdCost() {
		return stdCost;
	}
	/**
	 * @param stdCost the stdCost to set
	 */
	public void setStdCost(BigDecimal stdCost) {
		this.stdCost = stdCost;
	}
	/**
	 * @return the stdCostWsaleMUPct
	 */
	public BigDecimal getStdCostWsaleMUPct() {
		return stdCostWsaleMUPct;
	}
	/**
	 * @param stdCostWsaleMUPct the stdCostWsaleMUPct to set
	 */
	public void setStdCostWsaleMUPct(BigDecimal stdCostWsaleMUPct) {
		this.stdCostWsaleMUPct = stdCostWsaleMUPct;
	}
	/**
	 * @return the exotic
	 */
	public String getExotic() {
		return exotic;
	}
	/**
	 * @param exotic the exotic to set
	 */
	public void setExotic(String exotic) {
		this.exotic = exotic;
	}
	/**
	 * @return the factorystoreTypeSku
	 */
	public String getFactorystoreTypeSku() {
		return factorystoreTypeSku;
	}
	/**
	 * @param factorystoreTypeSku the factorystoreTypeSku to set
	 */
	public void setFactorystoreTypeSku(String factorystoreTypeSku) {
		this.factorystoreTypeSku = factorystoreTypeSku;
	}
	/**
	 * @return the finalStandard
	 */
	public BigDecimal getFinalStandard() {
		return finalStandard;
	}
	/**
	 * @param finalStandard the finalStandard to set
	 */
	public void setFinalStandard(BigDecimal finalStandard) {
		this.finalStandard = finalStandard;
	}
	/**
	 * @return the mpg
	 */
	public BigDecimal getMpg() {
		return mpg;
	}
	/**
	 * @param mpg the mpg to set
	 */
	public void setMpg(BigDecimal mpg) {
		this.mpg = mpg;
	}
	/**
	 * @return the unitVolumeFP
	 */
	public BigDecimal getUnitVolumeFP() {
		return unitVolumeFP;
	}
	/**
	 * @param unitVolumeFP the unitVolumeFP to set
	 */
	public void setUnitVolumeFP(BigDecimal unitVolumeFP) {
		this.unitVolumeFP = unitVolumeFP;
	}
	/**
	 * @return the unitVolumeFS
	 */
	public BigDecimal getUnitVolumeFS() {
		return unitVolumeFS;
	}
	/**
	 * @param unitVolumeFS the unitVolumeFS to set
	 */
	public void setUnitVolumeFS(BigDecimal unitVolumeFS) {
		this.unitVolumeFS = unitVolumeFS;
	}
	/**
	 * @return the workingCost1
	 */
	public BigDecimal getWorkingCost1() {
		return workingCost1;
	}
	/**
	 * @param workingCost1 the workingCost1 to set
	 */
	public void setWorkingCost1(BigDecimal workingCost1) {
		this.workingCost1 = workingCost1;
	}
	/**
	 * @return the workingCost2
	 */
	public BigDecimal getWorkingCost2() {
		return workingCost2;
	}
	/**
	 * @param workingCost2 the workingCost2 to set
	 */
	public void setWorkingCost2(BigDecimal workingCost2) {
		this.workingCost2 = workingCost2;
	}
	/**
	 * @return the workingCostRetailGMPct
	 */
	public BigDecimal getWorkingCostRetailGMPct() {
		return workingCostRetailGMPct;
	}
	/**
	 * @param workingCostRetailGMPct the workingCostRetailGMPct to set
	 */
	public void setWorkingCostRetailGMPct(BigDecimal workingCostRetailGMPct) {
		this.workingCostRetailGMPct = workingCostRetailGMPct;
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
	 * @return the teamTechnicalDesigner
	 */
	public BigDecimal getTeamTechnicalDesigner() {
		return teamTechnicalDesigner;
	}
	/**
	 * @param teamTechnicalDesigner the teamTechnicalDesigner to set
	 */
	public void setTeamTechnicalDesigner(BigDecimal teamTechnicalDesigner) {
		this.teamTechnicalDesigner = teamTechnicalDesigner;
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
	 * @return the upccode
	 */
	public String getUpccode() {
		return upccode;
	}
	/**
	 * @param upccode the upccode to set
	 */
	public void setUpccode(String upccode) {
		this.upccode = upccode;
	}
	/**
	 * @return the skuStyleName
	 */
	public String getSkuStyleName() {
		return skuStyleName;
	}
	/**
	 * @param skuStyleName the skuStyleName to set
	 */
	public void setSkuStyleName(String skuStyleName) {
		this.skuStyleName = skuStyleName;
	}

}
