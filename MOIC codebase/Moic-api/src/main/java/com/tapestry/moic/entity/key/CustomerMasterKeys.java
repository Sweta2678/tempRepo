package com.tapestry.moic.entity.key;

import java.io.Serializable;

public class CustomerMasterKeys implements Serializable {

	private static final long serialVersionUID = 1L;
	private String soldToNumber;
	private String shipToNumber;
	private String salesOrg;
	private String distributionChannel;
	private String division;
	private String site;

	public CustomerMasterKeys() {
	}

	public CustomerMasterKeys(String soldToNumber, String shipToNumber, String salesOrg, String distributionChannel,
			String division, String site) {
		super();
		this.soldToNumber = soldToNumber;
		this.shipToNumber = shipToNumber;
		this.salesOrg = salesOrg;
		this.distributionChannel = distributionChannel;
		this.division = division;
		this.site = site;
	}

	public String getSoldToNumber() {
		return soldToNumber;
	}

	public void setSoldToNumber(String soldToNumber) {
		this.soldToNumber = soldToNumber;
	}

	public String getShipToNumber() {
		return shipToNumber;
	}

	public void setShipToNumber(String shipToNumber) {
		this.shipToNumber = shipToNumber;
	}

	public String getSalesOrg() {
		return salesOrg;
	}

	public void setSalesOrg(String salesOrg) {
		this.salesOrg = salesOrg;
	}

	public String getDistributionChannel() {
		return distributionChannel;
	}

	public void setDistributionChannel(String distributionChannel) {
		this.distributionChannel = distributionChannel;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((distributionChannel == null) ? 0 : distributionChannel.hashCode());
		result = prime * result + ((division == null) ? 0 : division.hashCode());
		result = prime * result + ((salesOrg == null) ? 0 : salesOrg.hashCode());
		result = prime * result + ((shipToNumber == null) ? 0 : shipToNumber.hashCode());
		result = prime * result + ((site == null) ? 0 : site.hashCode());
		result = prime * result + ((soldToNumber == null) ? 0 : soldToNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerMasterKeys other = (CustomerMasterKeys) obj;
		if (distributionChannel == null) {
			if (other.distributionChannel != null)
				return false;
		} else if (!distributionChannel.equals(other.distributionChannel))
			return false;
		if (division == null) {
			if (other.division != null)
				return false;
		} else if (!division.equals(other.division))
			return false;
		if (salesOrg == null) {
			if (other.salesOrg != null)
				return false;
		} else if (!salesOrg.equals(other.salesOrg))
			return false;
		if (shipToNumber == null) {
			if (other.shipToNumber != null)
				return false;
		} else if (!shipToNumber.equals(other.shipToNumber))
			return false;
		if (site == null) {
			if (other.site != null)
				return false;
		} else if (!site.equals(other.site))
			return false;
		if (soldToNumber == null) {
			if (other.soldToNumber != null)
				return false;
		} else if (!soldToNumber.equals(other.soldToNumber))
			return false;
		return true;
	}

}