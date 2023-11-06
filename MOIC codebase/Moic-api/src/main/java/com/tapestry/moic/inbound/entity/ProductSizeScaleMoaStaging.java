package com.tapestry.moic.inbound.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.tapestry.moic.constant.MoicConstant;
import com.tapestry.moic.inbound.entity.key.ProductSizeScaleMoaStagingKeys;

@Entity
@Table(name = MoicConstant.PRODUCT_SIZE_SCALE_MOA_STAGING)
@IdClass(ProductSizeScaleMoaStagingKeys.class)
public class ProductSizeScaleMoaStaging implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = MoicConstant.Size_Name, length = 255)
	@NotNull
	private String sizeName;

	@Id
	@Column(name = MoicConstant.SKU_UNIQUE_ID)
	@NotNull
	private Long SKUUniqueID;

	@Id
	@Column(name = MoicConstant.STYLE_UNIQUE_ID)
	@NotNull
	private Long styleUniqueID;
	
	@Column(name = MoicConstant.UPC, length = 100)
	private String UPC;

	@Column(name = MoicConstant.VENDOR_REF_NUMBER, length = 100)
	private String vendorRefNumber;

	@Column(name = MoicConstant.SIZE_ROW_ID)
	private Long sizeRowID;

	public ProductSizeScaleMoaStaging() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param sizeName
	 * @param uPC
	 * @param vendorRefNumber
	 * @param sKUUniqueID
	 * @param styleUniqueID
	 * @param sizeRowID
	 */
	public ProductSizeScaleMoaStaging(@NotNull String sizeName, String uPC, String vendorRefNumber,
			@NotNull Long sKUUniqueID, @NotNull Long styleUniqueID, Long sizeRowID) {
		super();
		this.sizeName = sizeName;
		UPC = uPC;
		this.vendorRefNumber = vendorRefNumber;
		SKUUniqueID = sKUUniqueID;
		this.styleUniqueID = styleUniqueID;
		this.sizeRowID = sizeRowID;
	}

	public String getSizeName() {
		return sizeName;
	}

	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
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

	public Long getSKUUniqueID() {
		return SKUUniqueID;
	}

	public void setSKUUniqueID(Long sKUUniqueID) {
		SKUUniqueID = sKUUniqueID;
	}

	public Long getStyleUniqueID() {
		return styleUniqueID;
	}

	public void setStyleUniqueID(Long styleUniqueID) {
		this.styleUniqueID = styleUniqueID;
	}

	public Long getSizeRowID() {
		return sizeRowID;
	}

	public void setSizeRowID(Long sizeRowID) {
		this.sizeRowID = sizeRowID;
	}

	@Override
	public String toString() {
		return "ProductSizeScaleMoaStaging [sizeName=" + sizeName + ", UPC=" + UPC + ", vendorRefNumber="
				+ vendorRefNumber + ", SKUUniqueID=" + SKUUniqueID + ", styleUniqueID=" + styleUniqueID + ", sizeRowID="
				+ sizeRowID + "]";
	}
}
