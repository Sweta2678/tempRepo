package com.tapestry.moic.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.tapestry.moic.constant.MoicConstant;

/**
 * The Class MOQReport.
 *
 * @version 0.0.1
 */
@Entity
@Table(name = MoicConstant.MOQ_REPORT)
public class MOQReport implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long idAuto;
	
	@Column(name = MoicConstant.SEASON)
	@Size(max = 50)
	private String season;
	
	@Column(name = MoicConstant.SOLD_TO_NUMBER)
	@Size(max = 50)
	private String soldToNumber;
	
	@Column(name = MoicConstant.CHANNEL)
	@Size(max = 50)
	private String channel;

	@Column(name = MoicConstant.STYLE_COLOR)
	@Size(max = 50)
	private String styleColor;

	@Column(name = MoicConstant.STYLE_NUMBER)
	@Size(max = 50)
	private String styleNumber;

	@Column(name = MoicConstant.STYLE_NAME)
	@Size(max = 80)
	private String styleName;

	@Column(name = MoicConstant.MATERIAL_NAME)
	@Size(max = 100)
	private String materialName;

	@Column(name = MoicConstant.COLOR_NAME)
	@Size(max = 50)
	private String colorName;

	@Column(name = MoicConstant.COLOR_CODE)
	@Size(max = 50)
	private String colorCode;

	@Column(name = MoicConstant.SKU_INTRO_DATE)
	private Date skuIntroDate;

	@Column(name = MoicConstant.DEPARTMENT)
	@Size(max = 100)
	private String department;

	@Column(name = MoicConstant.PARRENTS_CLASS)
	@Size(max = 255)
	private String parentsClass;

	@Column(name = MoicConstant.RETAIL_PRICE)
	private double retailPrice;

	@Column(name = MoicConstant.TARGET_COST)
	private double targetCost;

	@Column(name = MoicConstant.COO)
	@Size(max = 80)
	private String coo;

	@Column(name = MoicConstant.TOTAL_BUY_QUANTITY)
	private int totalBuyQuantity;

	@Column(name = MoicConstant.SCHEDULE_DELIVERY_DATE)
	private Date scheduledDeliveryDate;

	@Column(name = MoicConstant.SITE)
	@Size(max = 255)
	private String site;

	@Column(name = MoicConstant.SALES_DOC_TYPE)
	@Size(max = 50)
	private String salesDocType;

	@Column(name = MoicConstant.SALES_ORG)
	@Size(max = 50)
	private String salesOrg;

	@Column(name = MoicConstant.DISTRIBUTION_CHANNEL)
	@Size(max = 50)
	private String distributionChannel;

	@Column(name = MoicConstant.DIVISON)
	@Size(max = 50)
	private String division;	

	@Column(name = MoicConstant.SHIP_TO_NUMBER)
	@Size(max = 50)
	private String shipToNumber;

	@Column(name = MoicConstant.SO_CANCEL_DATE)
	private Date soCancelDate;

	@Column(name = MoicConstant.PO_NUMBER)
	@Size(max = 255)
	private String poNumber;

	@Column(name = MoicConstant.PO_TYPE)
	@Size(max = 255)
	private String poType;

	@Column(name = MoicConstant.ORDER_REASON)
	@Size(max = 255)
	private String orderReason;

	@Column(name = MoicConstant.REQ_SEGMENT)
	@Size(max = 255)
	private String reqSegment;

	@Column(name = MoicConstant.TARGET)
	private int target;

	@Column(name = MoicConstant.SO_HEADER_REQ_DEL_DATE)
	private Date soHeaderReqDelDate;

	@Column(name = MoicConstant.SKU)
	@Size(max = 80)
	private String SKU;

	@Column(name = MoicConstant.DELETE)
	private Boolean delete;

	@Column(name = MoicConstant.EFFECTIVE_DATE)
	private Date effectiveDate;

	@Column(name = MoicConstant.CANCEL_DELIVERY_DATE)
	private Date cancelDeliveryDate;
	
	@Column(name = MoicConstant.ORDER_TOTAL_USD, precision = 10, scale = 2)
	private Double orderTotalUSD;
	
	@Column(name = MoicConstant.Status)
	@Size(max = 50)
	private String status;
	
	@Column(name = MoicConstant.BUSINESS_UNIT)
	@Size(max = 50)
	private String businessUnit;
	
	@Column(name = MoicConstant.FILE_NAME)
	@Size(max = 50)
	private String fileName; 
	
	@Column(name = MoicConstant.SKU_CLASS)
	@Size(max = 50)
	private String SkuClass;
	
	@Column(name = MoicConstant.ORDER_ID)
	private Long orderId;
	
	@Column(name = MoicConstant.SOLD_TO_DESCRIPTION, length = 255)
	private String soldToDescription;
	
	@Column(name = MoicConstant.WIDTH, length = 10)
	private String width;
	
	@Column(name = MoicConstant.COUNTRY, length = 50)
	private String country;
	
	@Column(name = MoicConstant.CITY, length = 50)
	private String city;
	
	@Column(name = MoicConstant.SIZE, length = 10)
	private String size;
	
	@Column(name = MoicConstant.EU_SIZE, length = 10)
	private String euSize;
	
	@Column(name = MoicConstant.UK_SIZE, length = 10)
	private String ukSize;
	
	/* Audit fields */
	@Column(name = MoicConstant.USER_ID)
	private String userId;

	@Column(name = MoicConstant.USER_NAME)
	private String userName;

	@Column(name = MoicConstant.CREATE_DATE)
	private Date createDate;

	@Column(name = MoicConstant.MODIFIED_DATE)
	private Date modifiedDate;

	@Column(name = MoicConstant.CREATE_BY)
	private String createdBy;

	@Column(name = MoicConstant.MODIFIED_BY)
	private String modifiedBy;

	@Column(name = MoicConstant.SOURCE)
	private String source;

	@Column(name = MoicConstant.LAST_DOWNLOAD_DATE)
	private Date lastDownloadDate;

	@Column(name = MoicConstant.DOWNLOAD_FLAG)
	private Boolean downloadFlag;
	
	public MOQReport() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param idAuto
	 * @param season
	 * @param soldToNumber
	 * @param channel
	 * @param styleColor
	 * @param styleNumber
	 * @param styleName
	 * @param materialName
	 * @param colorName
	 * @param colorCode
	 * @param skuIntroDate
	 * @param department
	 * @param parentsClass
	 * @param retailPrice
	 * @param targetCost
	 * @param coo
	 * @param totalBuyQuantity
	 * @param scheduledDeliveryDate
	 * @param site
	 * @param salesDocType
	 * @param salesOrg
	 * @param distributionChannel
	 * @param division
	 * @param shipToNumber
	 * @param soCancelDate
	 * @param poNumber
	 * @param poType
	 * @param orderReason
	 * @param reqSegment
	 * @param target
	 * @param soHeaderReqDelDate
	 * @param sKU
	 * @param delete
	 * @param effectiveDate
	 * @param cancelDeliveryDate
	 * @param orderTotalUSD
	 * @param status
	 * @param businessUnit
	 * @param fileName
	 * @param skuClass
	 * @param orderId
	 * @param soldToDescription
	 * @param width
	 * @param country
	 * @param city
	 * @param size
	 * @param euSize
	 * @param ukSize
	 * @param userId
	 * @param userName
	 * @param createDate
	 * @param modifiedDate
	 * @param createdBy
	 * @param modifiedBy
	 * @param source
	 * @param lastDownloadDate
	 * @param downloadFlag
	 */
	public MOQReport(Long idAuto, @Size(max = 50) String season, @Size(max = 50) String soldToNumber,
			@Size(max = 50) String channel, @Size(max = 50) String styleColor, @Size(max = 50) String styleNumber,
			@Size(max = 80) String styleName, @Size(max = 100) String materialName, @Size(max = 50) String colorName,
			@Size(max = 50) String colorCode, Date skuIntroDate, @Size(max = 100) String department,
			@Size(max = 255) String parentsClass, double retailPrice, double targetCost, @Size(max = 80) String coo,
			int totalBuyQuantity, Date scheduledDeliveryDate, @Size(max = 255) String site,
			@Size(max = 50) String salesDocType, @Size(max = 50) String salesOrg,
			@Size(max = 50) String distributionChannel, @Size(max = 50) String division,
			@Size(max = 50) String shipToNumber, Date soCancelDate, @Size(max = 255) String poNumber,
			@Size(max = 255) String poType, @Size(max = 255) String orderReason, @Size(max = 255) String reqSegment,
			int target, Date soHeaderReqDelDate, @Size(max = 80) String sKU, Boolean delete, Date effectiveDate,
			Date cancelDeliveryDate, Double orderTotalUSD, @Size(max = 50) String status,
			@Size(max = 50) String businessUnit, @Size(max = 50) String fileName, @Size(max = 50) String skuClass,
			Long orderId, String soldToDescription, String width, String country, String city, String size,
			String euSize, String ukSize, String userId, String userName, Date createDate, Date modifiedDate,
			String createdBy, String modifiedBy, String source, Date lastDownloadDate, Boolean downloadFlag) {
		super();
		this.idAuto = idAuto;
		this.season = season;
		this.soldToNumber = soldToNumber;
		this.channel = channel;
		this.styleColor = styleColor;
		this.styleNumber = styleNumber;
		this.styleName = styleName;
		this.materialName = materialName;
		this.colorName = colorName;
		this.colorCode = colorCode;
		this.skuIntroDate = skuIntroDate;
		this.department = department;
		this.parentsClass = parentsClass;
		this.retailPrice = retailPrice;
		this.targetCost = targetCost;
		this.coo = coo;
		this.totalBuyQuantity = totalBuyQuantity;
		this.scheduledDeliveryDate = scheduledDeliveryDate;
		this.site = site;
		this.salesDocType = salesDocType;
		this.salesOrg = salesOrg;
		this.distributionChannel = distributionChannel;
		this.division = division;
		this.shipToNumber = shipToNumber;
		this.soCancelDate = soCancelDate;
		this.poNumber = poNumber;
		this.poType = poType;
		this.orderReason = orderReason;
		this.reqSegment = reqSegment;
		this.target = target;
		this.soHeaderReqDelDate = soHeaderReqDelDate;
		SKU = sKU;
		this.delete = delete;
		this.effectiveDate = effectiveDate;
		this.cancelDeliveryDate = cancelDeliveryDate;
		this.orderTotalUSD = orderTotalUSD;
		this.status = status;
		this.businessUnit = businessUnit;
		this.fileName = fileName;
		SkuClass = skuClass;
		this.orderId = orderId;
		this.soldToDescription = soldToDescription;
		this.width = width;
		this.country = country;
		this.city = city;
		this.size = size;
		this.euSize = euSize;
		this.ukSize = ukSize;
		this.userId = userId;
		this.userName = userName;
		this.createDate = createDate;
		this.modifiedDate = modifiedDate;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.source = source;
		this.lastDownloadDate = lastDownloadDate;
		this.downloadFlag = downloadFlag;
	}

	public Long getIdAuto() {
		return idAuto;
	}

	public void setIdAuto(Long idAuto) {
		this.idAuto = idAuto;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public String getSoldToNumber() {
		return soldToNumber;
	}

	public void setSoldToNumber(String soldToNumber) {
		this.soldToNumber = soldToNumber;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getStyleColor() {
		return styleColor;
	}

	public void setStyleColor(String styleColor) {
		this.styleColor = styleColor;
	}

	public String getStyleNumber() {
		return styleNumber;
	}

	public void setStyleNumber(String styleNumber) {
		this.styleNumber = styleNumber;
	}

	public String getStyleName() {
		return styleName;
	}

	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public String getColorCode() {
		return colorCode;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}

	public Date getSkuIntroDate() {
		return skuIntroDate;
	}

	public void setSkuIntroDate(Date skuIntroDate) {
		this.skuIntroDate = skuIntroDate;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getParentsClass() {
		return parentsClass;
	}

	public void setParentsClass(String parentsClass) {
		this.parentsClass = parentsClass;
	}

	public double getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(double retailPrice) {
		this.retailPrice = retailPrice;
	}

	public double getTargetCost() {
		return targetCost;
	}

	public void setTargetCost(double targetCost) {
		this.targetCost = targetCost;
	}

	public String getCoo() {
		return coo;
	}

	public void setCoo(String coo) {
		this.coo = coo;
	}

	public int getTotalBuyQuantity() {
		return totalBuyQuantity;
	}

	public void setTotalBuyQuantity(int totalBuyQuantity) {
		this.totalBuyQuantity = totalBuyQuantity;
	}

	public Date getScheduledDeliveryDate() {
		return scheduledDeliveryDate;
	}

	public void setScheduledDeliveryDate(Date scheduledDeliveryDate) {
		this.scheduledDeliveryDate = scheduledDeliveryDate;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getSalesDocType() {
		return salesDocType;
	}

	public void setSalesDocType(String salesDocType) {
		this.salesDocType = salesDocType;
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

	public String getShipToNumber() {
		return shipToNumber;
	}

	public void setShipToNumber(String shipToNumber) {
		this.shipToNumber = shipToNumber;
	}

	public Date getSoCancelDate() {
		return soCancelDate;
	}

	public void setSoCancelDate(Date soCancelDate) {
		this.soCancelDate = soCancelDate;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public String getPoType() {
		return poType;
	}

	public void setPoType(String poType) {
		this.poType = poType;
	}

	public String getOrderReason() {
		return orderReason;
	}

	public void setOrderReason(String orderReason) {
		this.orderReason = orderReason;
	}

	public String getReqSegment() {
		return reqSegment;
	}

	public void setReqSegment(String reqSegment) {
		this.reqSegment = reqSegment;
	}

	public int getTarget() {
		return target;
	}

	public void setTarget(int target) {
		this.target = target;
	}

	public Date getSoHeaderReqDelDate() {
		return soHeaderReqDelDate;
	}

	public void setSoHeaderReqDelDate(Date soHeaderReqDelDate) {
		this.soHeaderReqDelDate = soHeaderReqDelDate;
	}

	public String getSKU() {
		return SKU;
	}

	public void setSKU(String sKU) {
		SKU = sKU;
	}

	public Boolean getDelete() {
		return delete;
	}

	public void setDelete(Boolean delete) {
		this.delete = delete;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Date getCancelDeliveryDate() {
		return cancelDeliveryDate;
	}

	public void setCancelDeliveryDate(Date cancelDeliveryDate) {
		this.cancelDeliveryDate = cancelDeliveryDate;
	}

	public Double getOrderTotalUSD() {
		return orderTotalUSD;
	}

	public void setOrderTotalUSD(Double orderTotalUSD) {
		this.orderTotalUSD = orderTotalUSD;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(String businessUnit) {
		this.businessUnit = businessUnit;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSkuClass() {
		return SkuClass;
	}

	public void setSkuClass(String skuClass) {
		SkuClass = skuClass;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getSoldToDescription() {
		return soldToDescription;
	}

	public void setSoldToDescription(String soldToDescription) {
		this.soldToDescription = soldToDescription;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getEuSize() {
		return euSize;
	}

	public void setEuSize(String euSize) {
		this.euSize = euSize;
	}

	public String getUkSize() {
		return ukSize;
	}

	public void setUkSize(String ukSize) {
		this.ukSize = ukSize;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
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

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Date getLastDownloadDate() {
		return lastDownloadDate;
	}

	public void setLastDownloadDate(Date lastDownloadDate) {
		this.lastDownloadDate = lastDownloadDate;
	}

	public Boolean getDownloadFlag() {
		return downloadFlag;
	}

	public void setDownloadFlag(Boolean downloadFlag) {
		this.downloadFlag = downloadFlag;
	}

	@Override
	public String toString() {
		return "MOQReport [idAuto=" + idAuto + ", season=" + season + ", soldToNumber=" + soldToNumber + ", channel="
				+ channel + ", styleColor=" + styleColor + ", styleNumber=" + styleNumber + ", styleName=" + styleName
				+ ", materialName=" + materialName + ", colorName=" + colorName + ", colorCode=" + colorCode
				+ ", skuIntroDate=" + skuIntroDate + ", department=" + department + ", parentsClass=" + parentsClass
				+ ", retailPrice=" + retailPrice + ", targetCost=" + targetCost + ", coo=" + coo + ", totalBuyQuantity="
				+ totalBuyQuantity + ", scheduledDeliveryDate=" + scheduledDeliveryDate + ", site=" + site
				+ ", salesDocType=" + salesDocType + ", salesOrg=" + salesOrg + ", distributionChannel="
				+ distributionChannel + ", division=" + division + ", shipToNumber=" + shipToNumber + ", soCancelDate="
				+ soCancelDate + ", poNumber=" + poNumber + ", poType=" + poType + ", orderReason=" + orderReason
				+ ", reqSegment=" + reqSegment + ", target=" + target + ", soHeaderReqDelDate=" + soHeaderReqDelDate
				+ ", SKU=" + SKU + ", delete=" + delete + ", effectiveDate=" + effectiveDate + ", cancelDeliveryDate="
				+ cancelDeliveryDate + ", orderTotalUSD=" + orderTotalUSD + ", status=" + status + ", businessUnit="
				+ businessUnit + ", fileName=" + fileName + ", SkuClass=" + SkuClass + ", orderId=" + orderId
				+ ", soldToDescription=" + soldToDescription + ", width=" + width + ", country=" + country + ", city="
				+ city + ", size=" + size + ", euSize=" + euSize + ", ukSize=" + ukSize + ", userId=" + userId
				+ ", userName=" + userName + ", createDate=" + createDate + ", modifiedDate=" + modifiedDate
				+ ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", source=" + source
				+ ", lastDownloadDate=" + lastDownloadDate + ", downloadFlag=" + downloadFlag + "]";
	}

}