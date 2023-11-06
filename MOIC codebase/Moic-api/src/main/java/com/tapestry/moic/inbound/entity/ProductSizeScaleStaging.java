package com.tapestry.moic.inbound.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.tapestry.moic.constant.MoicConstant;

@Entity
@Table(name = MoicConstant.PRODUCT_SIZESCALE_STAGING)
public class ProductSizeScaleStaging implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = MoicConstant.PLM_UNIQUE_ID, length = 100)
	@Id
	@NotNull
	private String plmUniqueId;

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

	@Column(name = MoicConstant.SKU_CREATE_DATE)
	private Date createDate;

	@Column(name = MoicConstant.SKU_UPDATE_DATE)
	private Date updateDate;

	public ProductSizeScaleStaging() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param plmUniqueId
	 * @param sizeScale
	 * @param sizeScaleMsg
	 * @param sizeList
	 * @param baseSize
	 * @param status
	 * @param lastUpdateDate
	 * @param department
	 * @param createDate
	 * @param updateDate
	 */
	public ProductSizeScaleStaging(@NotNull String plmUniqueId, @NotNull String sizeScale, @NotNull String sizeScaleMsg,
			@NotNull String sizeList, String baseSize, String status, Date lastUpdateDate, String department,
			Date createDate, Date updateDate) {
		super();
		this.plmUniqueId = plmUniqueId;
		this.sizeScale = sizeScale;
		this.sizeScaleMsg = sizeScaleMsg;
		this.sizeList = sizeList;
		this.baseSize = baseSize;
		this.status = status;
		this.lastUpdateDate = lastUpdateDate;
		this.department = department;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	public String getPlmUniqueId() {
		return plmUniqueId;
	}

	public void setPlmUniqueId(String plmUniqueId) {
		this.plmUniqueId = plmUniqueId;
	}

	public String getSizeScale() {
		return sizeScale;
	}

	public void setSizeScale(String sizeScale) {
		this.sizeScale = sizeScale;
	}

	public String getSizeScaleMsg() {
		return sizeScaleMsg;
	}

	public void setSizeScaleMsg(String sizeScaleMsg) {
		this.sizeScaleMsg = sizeScaleMsg;
	}

	public String getSizeList() {
		return sizeList;
	}

	public void setSizeList(String sizeList) {
		this.sizeList = sizeList;
	}

	public String getBaseSize() {
		return baseSize;
	}

	public void setBaseSize(String baseSize) {
		this.baseSize = baseSize;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "ProductSizeScaleStaging [plmUniqueId=" + plmUniqueId + ", sizeScale=" + sizeScale + ", sizeScaleMsg="
				+ sizeScaleMsg + ", sizeList=" + sizeList + ", baseSize=" + baseSize + ", status=" + status
				+ ", lastUpdateDate=" + lastUpdateDate + ", department=" + department + ", createDate=" + createDate
				+ ", updateDate=" + updateDate + "]";
	}
}
