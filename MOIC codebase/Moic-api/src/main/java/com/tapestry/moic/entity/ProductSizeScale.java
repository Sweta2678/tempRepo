package com.tapestry.moic.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.tapestry.moic.constant.MoicConstant;

import lombok.Data;

@Entity
@Table(name = MoicConstant.PRODUCT_SIZE_SCALE)
@Data
public class ProductSizeScale implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = MoicConstant.SIZE_SCALE_ID, length = 100)
	@Id
	@NotNull
	private String sizeScaleId;

	@Column(name = MoicConstant.SIZE_SCALE, length = 2000)
	@NotNull
	private String sizeScale;

	@Column(name = MoicConstant.SIZE_SCALE_MSG, length = 2000)
	@NotNull
	private String sizeScaleMsg;

	@Column(name = MoicConstant.SIZE_LIST, length = 2000)
	@NotNull
	private String sizeList;

	@Column(name = MoicConstant.BASE_SIZE, length = 255)
	private String baseSize;

	@Column(name = MoicConstant.Status)
	private String status;

	@Column(name = MoicConstant.LAST_UPDATE_DATE)
	private Date lastUpdateDate;

	@Column(name = MoicConstant.DEPARTMENT, length = 100)
	private String department;

	@Column(name = MoicConstant.CREATE_BY, length = 50)
	private String createdBy;
	
	@Column(name = MoicConstant.MODIFIED_BY, length = 50)
	private String modifiedBy;
	
	@Column(name = MoicConstant.CREATE_DATE)
	private Date createDate;
	
	@Column(name = MoicConstant.MODIFIED_DATE)
	private Date modifieDate;

	public ProductSizeScale() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param sizeScaleId
	 * @param sizeScale
	 * @param sizeScaleMsg
	 * @param sizeList
	 * @param baseSize
	 * @param status
	 * @param lastUpdateDate
	 * @param department
	 * @param createdBy
	 * @param modifiedBy
	 * @param createDate
	 * @param modifieDate
	 */
	public ProductSizeScale(@NotNull String sizeScaleId, @NotNull String sizeScale, @NotNull String sizeScaleMsg,
			@NotNull String sizeList, String baseSize, String status, Date lastUpdateDate, String department,
			String createdBy, String modifiedBy, Date createDate, Date modifieDate) {
		super();
		this.sizeScaleId = sizeScaleId;
		this.sizeScale = sizeScale;
		this.sizeScaleMsg = sizeScaleMsg;
		this.sizeList = sizeList;
		this.baseSize = baseSize;
		this.status = status;
		this.lastUpdateDate = lastUpdateDate;
		this.department = department;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createDate = createDate;
		this.modifieDate = modifieDate;
	}

	/**
	 * @return the sizeScaleId
	 */
	public String getSizeScaleId() {
		return sizeScaleId;
	}

	/**
	 * @param sizeScaleId the sizeScaleId to set
	 */
	public void setSizeScaleId(String sizeScaleId) {
		this.sizeScaleId = sizeScaleId;
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
	 * @return the sizeScaleMsg
	 */
	public String getSizeScaleMsg() {
		return sizeScaleMsg;
	}

	/**
	 * @param sizeScaleMsg the sizeScaleMsg to set
	 */
	public void setSizeScaleMsg(String sizeScaleMsg) {
		this.sizeScaleMsg = sizeScaleMsg;
	}

	/**
	 * @return the sizeList
	 */
	public String getSizeList() {
		return sizeList;
	}

	/**
	 * @param sizeList the sizeList to set
	 */
	public void setSizeList(String sizeList) {
		this.sizeList = sizeList;
	}

	/**
	 * @return the baseSize
	 */
	public String getBaseSize() {
		return baseSize;
	}

	/**
	 * @param baseSize the baseSize to set
	 */
	public void setBaseSize(String baseSize) {
		this.baseSize = baseSize;
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
	 * @return the lastUpdateDate
	 */
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	/**
	 * @param lastUpdateDate the lastUpdateDate to set
	 */
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
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
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the modifiedBy
	 */
	public String getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * @param modifiedBy the modifiedBy to set
	 */
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the modifieDate
	 */
	public Date getModifieDate() {
		return modifieDate;
	}

	/**
	 * @param modifieDate the modifieDate to set
	 */
	public void setModifieDate(Date modifieDate) {
		this.modifieDate = modifieDate;
	}

	@Override
	public String toString() {
		return "ProductSizeScale [sizeScaleId=" + sizeScaleId + ", sizeScale=" + sizeScale + ", sizeScaleMsg="
				+ sizeScaleMsg + ", sizeList=" + sizeList + ", baseSize=" + baseSize + ", status=" + status
				+ ", lastUpdateDate=" + lastUpdateDate + ", department=" + department + ", createdBy=" + createdBy
				+ ", modifiedBy=" + modifiedBy + ", createDate=" + createDate + ", modifieDate=" + modifieDate + "]";
	}
	
	
}
