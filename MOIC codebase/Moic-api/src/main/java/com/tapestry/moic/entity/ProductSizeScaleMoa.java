package com.tapestry.moic.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.tapestry.moic.constant.MoicConstant;
import com.tapestry.moic.entity.key.ProductSizeScaleMoaKeys;

@Entity
@Table(name = MoicConstant.PRODUCT_SIZE_SCALE_MOA)
@IdClass(ProductSizeScaleMoaKeys.class)
public class ProductSizeScaleMoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = MoicConstant.Size_Name, length = 255)
	@NotNull
	private String sizeName;

	@Id
	@Column(name = MoicConstant.SKU_UNIQUE_ID)
	@NotNull
	private String SKUUniqueID;

	@Id
	@Column(name = MoicConstant.STYLE_UNIQUE_ID)
	@NotNull
	private String styleUniqueID;
	
	@Column(name = MoicConstant.UPC, length = 100)
	private String UPC;

	@Column(name = MoicConstant.VENDOR_REF_NUMBER, length = 100)
	private String vendorRefNumber;

	@Column(name = MoicConstant.SIZE_ROW_ID)
	private Long sizeRowID;
	
	@Column(name = MoicConstant.CREATE_BY, length = 50)
	private String createdBy;
	
	@Column(name = MoicConstant.MODIFIED_BY, length = 50)
	private String modifiedBy;
	
	@Column(name = MoicConstant.CREATE_DATE)
	private Date createDate;
	
	@Column(name = MoicConstant.MODIFIED_DATE)
	private Date modifieDate;
	
	@Column(name = MoicConstant.COMBINED_SKU)
	private String combinedSku;

	public ProductSizeScaleMoa() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param sizeName
	 * @param sKUUniqueID
	 * @param styleUniqueID
	 * @param uPC
	 * @param vendorRefNumber
	 * @param sizeRowID
	 * @param createdBy
	 * @param modifiedBy
	 * @param createDate
	 * @param modifieDate
	 * @param combinedSku
	 */
	public ProductSizeScaleMoa(@NotNull String sizeName, @NotNull String sKUUniqueID, @NotNull String styleUniqueID,
			String uPC, String vendorRefNumber, Long sizeRowID, String createdBy, String modifiedBy, Date createDate,
			Date modifieDate, String combinedSku) {
		super();
		this.sizeName = sizeName;
		SKUUniqueID = sKUUniqueID;
		this.styleUniqueID = styleUniqueID;
		UPC = uPC;
		this.vendorRefNumber = vendorRefNumber;
		this.sizeRowID = sizeRowID;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createDate = createDate;
		this.modifieDate = modifieDate;
		this.combinedSku = combinedSku;
	}

	public String getSizeName() {
		return sizeName;
	}

	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}

	public String getSKUUniqueID() {
		return SKUUniqueID;
	}

	public void setSKUUniqueID(String sKUUniqueID) {
		SKUUniqueID = sKUUniqueID;
	}

	public String getStyleUniqueID() {
		return styleUniqueID;
	}

	public void setStyleUniqueID(String styleUniqueID) {
		this.styleUniqueID = styleUniqueID;
	}

	public String getUPC() {
		return UPC;
	}

	public void setUPC(String uPC) {
		UPC = uPC;
	}

	public String getVendorRefNumber() {
		return vendorRefNumber;
	}

	public void setVendorRefNumber(String vendorRefNumber) {
		this.vendorRefNumber = vendorRefNumber;
	}

	public Long getSizeRowID() {
		return sizeRowID;
	}

	public void setSizeRowID(Long sizeRowID) {
		this.sizeRowID = sizeRowID;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifieDate() {
		return modifieDate;
	}

	public void setModifieDate(Date modifieDate) {
		this.modifieDate = modifieDate;
	}

	public String getCombinedSku() {
		return combinedSku;
	}

	public void setCombinedSku(String combinedSku) {
		this.combinedSku = combinedSku;
	}

	@Override
	public String toString() {
		return "ProductSizeScaleMoa [sizeName=" + sizeName + ", SKUUniqueID=" + SKUUniqueID + ", styleUniqueID="
				+ styleUniqueID + ", UPC=" + UPC + ", vendorRefNumber=" + vendorRefNumber + ", sizeRowID=" + sizeRowID
				+ ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", createDate=" + createDate
				+ ", modifieDate=" + modifieDate + ", combinedSku=" + combinedSku + "]";
	}
}