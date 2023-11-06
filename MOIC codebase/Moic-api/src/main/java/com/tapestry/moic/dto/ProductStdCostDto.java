package com.tapestry.moic.dto;

import java.io.Serializable;
import java.util.Date;

public class ProductStdCostDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String	plmUniqueID;
	private String	seasonCode;
	private String	brandID;
	private String	divisionID;
	private String	agentID;
	private String	supplierID;
	private String	plantID;
	private String	styleID;
	private String	colorID;
	private String	costType;
	private Boolean	active;
	private int	contractorCost;
	private int	muContractorCost;
	private int	commissionPercentage;
	private int	commissionCost;
	private int	dutyPercentage;
	private int	dutyCost;
	private int	freightCost;
	private int	overheadPercentage;
	private int	overheadCost;
	private int	llcUSD;
	private Date stdLlcEfectiveDate;

	public ProductStdCostDto() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param plmUniqueID
	 * @param seasonCode
	 * @param brandID
	 * @param divisionID
	 * @param agentID
	 * @param supplierID
	 * @param plantID
	 * @param styleID
	 * @param colorID
	 * @param costType
	 * @param active
	 * @param contractorCost
	 * @param muContractorCost
	 * @param commissionPercentage
	 * @param commissionCost
	 * @param dutyPercentage
	 * @param dutyCost
	 * @param freightCost
	 * @param overheadPercentage
	 * @param overheadCost
	 * @param llcUSD
	 * @param stdLlcEfectiveDate
	 */
	public ProductStdCostDto(String plmUniqueID, String seasonCode, String brandID, String divisionID, String agentID,
			String supplierID, String plantID, String styleID, String colorID, String costType, Boolean active,
			int contractorCost, int muContractorCost, int commissionPercentage, int commissionCost, int dutyPercentage,
			int dutyCost, int freightCost, int overheadPercentage, int overheadCost, int llcUSD,
			Date stdLlcEfectiveDate) {
		super();
		this.plmUniqueID = plmUniqueID;
		this.seasonCode = seasonCode;
		this.brandID = brandID;
		this.divisionID = divisionID;
		this.agentID = agentID;
		this.supplierID = supplierID;
		this.plantID = plantID;
		this.styleID = styleID;
		this.colorID = colorID;
		this.costType = costType;
		this.active = active;
		this.contractorCost = contractorCost;
		this.muContractorCost = muContractorCost;
		this.commissionPercentage = commissionPercentage;
		this.commissionCost = commissionCost;
		this.dutyPercentage = dutyPercentage;
		this.dutyCost = dutyCost;
		this.freightCost = freightCost;
		this.overheadPercentage = overheadPercentage;
		this.overheadCost = overheadCost;
		this.llcUSD = llcUSD;
		this.stdLlcEfectiveDate = stdLlcEfectiveDate;
	}

	public String getPlmUniqueID() {
		return plmUniqueID;
	}

	public void setPlmUniqueID(String plmUniqueID) {
		this.plmUniqueID = plmUniqueID;
	}

	public String getSeasonCode() {
		return seasonCode;
	}

	public void setSeasonCode(String seasonCode) {
		this.seasonCode = seasonCode;
	}

	public String getBrandID() {
		return brandID;
	}

	public void setBrandID(String brandID) {
		this.brandID = brandID;
	}

	public String getDivisionID() {
		return divisionID;
	}

	public void setDivisionID(String divisionID) {
		this.divisionID = divisionID;
	}

	public String getAgentID() {
		return agentID;
	}

	public void setAgentID(String agentID) {
		this.agentID = agentID;
	}

	public String getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(String supplierID) {
		this.supplierID = supplierID;
	}

	public String getPlantID() {
		return plantID;
	}

	public void setPlantID(String plantID) {
		this.plantID = plantID;
	}

	public String getStyleID() {
		return styleID;
	}

	public void setStyleID(String styleID) {
		this.styleID = styleID;
	}

	public String getColorID() {
		return colorID;
	}

	public void setColorID(String colorID) {
		this.colorID = colorID;
	}

	public String getCostType() {
		return costType;
	}

	public void setCostType(String costType) {
		this.costType = costType;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public int getContractorCost() {
		return contractorCost;
	}

	public void setContractorCost(int contractorCost) {
		this.contractorCost = contractorCost;
	}

	public int getMuContractorCost() {
		return muContractorCost;
	}

	public void setMuContractorCost(int muContractorCost) {
		this.muContractorCost = muContractorCost;
	}

	public int getCommissionPercentage() {
		return commissionPercentage;
	}

	public void setCommissionPercentage(int commissionPercentage) {
		this.commissionPercentage = commissionPercentage;
	}

	public int getCommissionCost() {
		return commissionCost;
	}

	public void setCommissionCost(int commissionCost) {
		this.commissionCost = commissionCost;
	}

	public int getDutyPercentage() {
		return dutyPercentage;
	}

	public void setDutyPercentage(int dutyPercentage) {
		this.dutyPercentage = dutyPercentage;
	}

	public int getDutyCost() {
		return dutyCost;
	}

	public void setDutyCost(int dutyCost) {
		this.dutyCost = dutyCost;
	}

	public int getFreightCost() {
		return freightCost;
	}

	public void setFreightCost(int freightCost) {
		this.freightCost = freightCost;
	}

	public int getOverheadPercentage() {
		return overheadPercentage;
	}

	public void setOverheadPercentage(int overheadPercentage) {
		this.overheadPercentage = overheadPercentage;
	}

	public int getOverheadCost() {
		return overheadCost;
	}

	public void setOverheadCost(int overheadCost) {
		this.overheadCost = overheadCost;
	}

	public int getLlcUSD() {
		return llcUSD;
	}

	public void setLlcUSD(int llcUSD) {
		this.llcUSD = llcUSD;
	}

	public Date getStdLlcEfectiveDate() {
		return stdLlcEfectiveDate;
	}

	public void setStdLlcEfectiveDate(Date stdLlcEfectiveDate) {
		this.stdLlcEfectiveDate = stdLlcEfectiveDate;
	}

	@Override
	public String toString() {
		return "ProductStdCostDto [plmUniqueID=" + plmUniqueID + ", seasonCode=" + seasonCode + ", brandID=" + brandID
				+ ", divisionID=" + divisionID + ", agentID=" + agentID + ", supplierID=" + supplierID + ", plantID="
				+ plantID + ", styleID=" + styleID + ", colorID=" + colorID + ", costType=" + costType + ", active="
				+ active + ", contractorCost=" + contractorCost + ", muContractorCost=" + muContractorCost
				+ ", commissionPercentage=" + commissionPercentage + ", commissionCost=" + commissionCost
				+ ", dutyPercentage=" + dutyPercentage + ", dutyCost=" + dutyCost + ", freightCost=" + freightCost
				+ ", overheadPercentage=" + overheadPercentage + ", overheadCost=" + overheadCost + ", llcUSD=" + llcUSD
				+ ", stdLlcEfectiveDate=" + stdLlcEfectiveDate + "]";
	}
}
