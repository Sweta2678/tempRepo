/**
 * 
 */
package com.plmviewer.model;

import java.math.BigDecimal;

/**
 * @author uthanasekarapandian
 *
 */
public class LCSMoaObjectVo {
	private String  wholeSalePrice;
	private String  endDate;
	private BigDecimal freight;
	private String  moaBoolean;
	private BigDecimal  oldPoCost; 
	private BigDecimal  overhead;
	private BigDecimal  POcost;
	
	private BigDecimal  retailPrice; 
	private String  startDate;
	private BigDecimal  targetCost;
	private BigDecimal  totalCost;
	private BigDecimal  tradeMU;
	private BigDecimal  WHMU;
	private BigDecimal  WHPrice;
	private BigDecimal  cjiJPY;
	private BigDecimal  fxRate;
	private BigDecimal  jpyTaxIncluded;
	private String  merchCJIPrices;
	private String  moaBooleanCJI;
	private BigDecimal  premiumRate;
	private BigDecimal offRetail;
	private BigDecimal  pctOffTicket;
	private BigDecimal  additive;
	private BigDecimal  fsPromoPrice;
	private BigDecimal  fsTicket; 
	private String  merchFactoryPrices;
	private String  moaBooleanFact;
	private String  rounding;
	
	//size scale //
	private String moaSize;
	private String upc;
	private String vendorRefNumber;
	/**
	 * @return the size
	 */
	public String getMoaSize() {
		return moaSize;
	}
	/**
	 * @param size the size to set
	 */
	public void setMoaSize(String moaSize) {
		this.moaSize = moaSize;
	}
	/**
	 * @return the upc
	 */
	public String getUpc() {
		return upc;
	}
	/**
	 * @param upc the upc to set
	 */
	public void setUpc(String upc) {
		this.upc = upc;
	}
	/**
	 * @return the vendorRefNumber
	 */
	public String getVendorRefNumber() {
		return vendorRefNumber;
	}
	/**
	 * @param vendorRefNumber the vendorRefNumber to set
	 */
	public void setVendorRefNumber(String vendorRefNumber) {
		this.vendorRefNumber = vendorRefNumber;
	}
	/**
	 * @return the wholeSalePrice
	 */
	public String getWholeSalePrice() {
		return wholeSalePrice;
	}
	/**
	 * @param wholeSalePrice the wholeSalePrice to set
	 */
	public void setWholeSalePrice(String wholeSalePrice) {
		this.wholeSalePrice = wholeSalePrice;
	}
	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the freight
	 */
	public BigDecimal getFreight() {
		return freight;
	}
	/**
	 * @param freight the freight to set
	 */
	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}
	/**
	 * @return the moaBoolean
	 */
	public String getMoaBoolean() {
		return moaBoolean;
	}
	/**
	 * @param moaBoolean the moaBoolean to set
	 */
	public void setMoaBoolean(String moaBoolean) {
		this.moaBoolean = moaBoolean;
	}
	/**
	 * @return the oldPoCos
	 */
	public BigDecimal getOldPoCost() {
		return oldPoCost;
	}
	/**
	 * @param oldPoCos the oldPoCos to set
	 */
	public void setOldPoCost(BigDecimal oldPoCost) {
		this.oldPoCost = oldPoCost;
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
	 * @return the pOcost
	 */
	public BigDecimal getPOcost() {
		return POcost;
	}
	/**
	 * @param pOcost the pOcost to set
	 */
	public void setPOcost(BigDecimal pOcost) {
		POcost = pOcost;
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
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
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
	 * @return the tradeMU
	 */
	public BigDecimal getTradeMU() {
		return tradeMU;
	}
	/**
	 * @param tradeMU the tradeMU to set
	 */
	public void setTradeMU(BigDecimal tradeMU) {
		this.tradeMU = tradeMU;
	}
	/**
	 * @return the wHMU
	 */
	public BigDecimal getWHMU() {
		return WHMU;
	}
	/**
	 * @param wHMU the wHMU to set
	 */
	public void setWHMU(BigDecimal wHMU) {
		WHMU = wHMU;
	}
	/**
	 * @return the wHPrice
	 */
	public BigDecimal getWHPrice() {
		return WHPrice;
	}
	/**
	 * @param wHPrice the wHPrice to set
	 */
	public void setWHPrice(BigDecimal wHPrice) {
		WHPrice = wHPrice;
	}
	/**
	 * @return the cjiJPY
	 */
	public BigDecimal getCjiJPY() {
		return cjiJPY;
	}
	/**
	 * @param cjiJPY the cjiJPY to set
	 */
	public void setCjiJPY(BigDecimal cjiJPY) {
		this.cjiJPY = cjiJPY;
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
	 * @return the jpyTaxIncluded
	 */
	public BigDecimal getJpyTaxIncluded() {
		return jpyTaxIncluded;
	}
	/**
	 * @param jpyTaxIncluded the jpyTaxIncluded to set
	 */
	public void setJpyTaxIncluded(BigDecimal jpyTaxIncluded) {
		this.jpyTaxIncluded = jpyTaxIncluded;
	}
	/**
	 * @return the merchCJIPrices
	 */
	public String getMerchCJIPrices() {
		return merchCJIPrices;
	}
	/**
	 * @param merchCJIPrices the merchCJIPrices to set
	 */
	public void setMerchCJIPrices(String merchCJIPrices) {
		this.merchCJIPrices = merchCJIPrices;
	}
	/**
	 * @return the moaBooleanCJI
	 */
	public String getMoaBooleanCJI() {
		return moaBooleanCJI;
	}
	/**
	 * @param moaBooleanCJI the moaBooleanCJI to set
	 */
	public void setMoaBooleanCJI(String moaBooleanCJI) {
		this.moaBooleanCJI = moaBooleanCJI;
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
	 * @return the offRetail
	 */
	public BigDecimal getOffRetail() {
		return offRetail;
	}
	/**
	 * @param offRetail the offRetail to set
	 */
	public void setOffRetail(BigDecimal offRetail) {
		this.offRetail = offRetail;
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
	 * @return the fsTicket
	 */
	public BigDecimal getFsTicket() {
		return fsTicket;
	}
	/**
	 * @param fsTicket the fsTicket to set
	 */
	public void setFsTicket(BigDecimal fsTicket) {
		this.fsTicket = fsTicket;
	}
	/**
	 * @return the merchFactoryPrices
	 */
	public String getMerchFactoryPrices() {
		return merchFactoryPrices;
	}
	/**
	 * @param merchFactoryPrices the merchFactoryPrices to set
	 */
	public void setMerchFactoryPrices(String merchFactoryPrices) {
		this.merchFactoryPrices = merchFactoryPrices;
	}
	/**
	 * @return the moaBooleanFact
	 */
	public String getMoaBooleanFact() {
		return moaBooleanFact;
	}
	/**
	 * @param moaBooleanFact the moaBooleanFact to set
	 */
	public void setMoaBooleanFact(String moaBooleanFact) {
		this.moaBooleanFact = moaBooleanFact;
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
	


}
