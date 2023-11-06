/**
 * 
 */
package com.plmviewer.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author uthanasekarapandian
 *
 */
@Entity
@Table(name="skuarev")
@Access(value= AccessType.FIELD)
public class SKUaRevVo {
	
	
	@Column(name="ida3a12")
	private String ida3a12;
	@Column(name="ida3masterreference")
	private String skuIda3MasterReference;
	@Id
	@Column(name="branchiditerationinfo")
	private String branchIterationInfo;
	@Column(name="att1")
	private String  skuStyleName;
	@Column(name="att27")
	private String introDate;
	@Column(name="date1")
	private String earlyReleaseDate;
	@Column(name="num13")
	private String finishedGoodColorCode;
	@Column(name="att15")
	private String deleteDate;
	@Column(name="att5")
	private String  skuFactoryStoreType;
	@Column(name="att24")
	private String fsIntroDate;
	@Column(name="att23")
	private String fsDeleteDate;
	@Column(name="att6")
	private String skuBomStatus;

	
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
	 * @return the finishedGoodColorCode
	 */
	public String getFinishedGoodColorCode() {
		return finishedGoodColorCode;
	}
	/**
	 * @param finishedGoodColorCode the finishedGoodColorCode to set
	 */
	public void setFinishedGoodColorCode(String finishedGoodColorCode) {
		this.finishedGoodColorCode = finishedGoodColorCode;
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
	 * @return the skuFactoryStoreType
	 */
	public String getSkuFactoryStoreType() {
		return skuFactoryStoreType;
	}
	/**
	 * @param skuFactoryStoreType the skuFactoryStoreType to set
	 */
	public void setSkuFactoryStoreType(String skuFactoryStoreType) {
		this.skuFactoryStoreType = skuFactoryStoreType;
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
	public String getSkuStyleName() {
		return skuStyleName;
	}
	public void setSkuStyleName(String skuStyleName) {
		this.skuStyleName = skuStyleName;
	}
	/**
	 * @return the ida3a12
	 */
	public String getIda3a12() {
		return ida3a12;
	}
	/**
	 * @param ida3a12 the ida3a12 to set
	 */
	public void setIda3a12(String ida3a12) {
		this.ida3a12 = ida3a12;
	}
	
	/**
	 * @return the skuIda3MasterReference
	 */
	public String getSkuIda3MasterReference() {
		return skuIda3MasterReference;
	}
	/**
	 * @param skuIda3MasterReference the skuIda3MasterReference to set
	 */
	public void setSkuIda3MasterReference(String skuIda3MasterReference) {
		this.skuIda3MasterReference = skuIda3MasterReference;
	}
	/**
	 * @return the branchIterationInfo
	 */
	public String getBranchIterationInfo() {
		return branchIterationInfo;
	}
	/**
	 * @param branchIterationInfo the branchIterationInfo to set
	 */
	public void setBranchIterationInfo(String branchIterationInfo) {
		this.branchIterationInfo = branchIterationInfo;
	}
	public String getSkuBomStatus() {
		return skuBomStatus;
	}
	public void setSkuBomStatus(String skuBomStatus) {
		this.skuBomStatus = skuBomStatus;
	}

}
