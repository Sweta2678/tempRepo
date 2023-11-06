package com.tapestry.moic.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.tapestry.moic.constant.MoicConstant;
import com.tapestry.moic.entity.key.JoorOrderItemsKeys;

/**
 * The Class JoorOrderItem.
 *
 * @version 0.0.1
 */
@Entity
@Table(name = MoicConstant.JOOR_ORDER_ITEM)
@IdClass(JoorOrderItemsKeys.class)
public class JoorOrderItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = MoicConstant.ORDER_ID)
	private Long orderID;
	
	@Id
	@Column(name = MoicConstant.ITEM_NUMBER)
	private String itemNumber;
	
	@Id
	@Column(name = MoicConstant.ITEM_STYLE_ID)
	private Long itemStyleId;
	
	@Id
	@Column(name = MoicConstant.ITEM_COLOR_CODE)
	private String itemColorCode;
	
	@Id
	@Column(name = MoicConstant.ITEM_SIZE)
	private String itemSize;
	
	@Column(name = MoicConstant.ITEM_NAME)
	private String itemName;
	
	@Column(name = MoicConstant.ITEM_STYLE_IDENTIFIER)
	private String itemStyleIdentifier;
	
	@Column(name = MoicConstant.ITEM_COLOR)
	private String itemColor;
		
	@Column(name = MoicConstant.ITEM_UPC)
	private String itemUpc;
	
	@Column(name = MoicConstant.ITEM_SIZE_CODE)
	private String itemSizeCode;
	
	@Column(name = MoicConstant.ITEM_PRICE)
	private Double itemPrice;
	
	@Column(name = MoicConstant.ITEM_RETAIL_PRICE)
	private Double itemRetailPrice;
	
	@Column(name = MoicConstant.ITEM_UNIT_PRICE)
	private Double itemUnitPrice;
	
	@Column(name = MoicConstant.ITEM_PRICE_ADJUSTMENT)
	private Double itemPriceAdjustment;
	
	@Column(name = MoicConstant.ITEM_QUANTITY)
	private Integer itemQuantity;

	@Column(name = MoicConstant.ITEM_MULTIPLIER)
	private String itemMultiplier;
	
	@Column(name = MoicConstant.ITEM_PRICE_EXTENDED)
	private Double itemPriceExtended;
	
	@Column(name = MoicConstant.ITEM_COMMENT)
	private String itemComment;
	
	@Column(name = MoicConstant.ITEM_DOOR_ID)
	private Integer itemDoorId;
	
	@Column(name = MoicConstant.ITEM_COLOR_COMMENT)
	private String itemColorComment;
	
	@Column(name = MoicConstant.IMPECTED_MOQ_SKUCHANGE)
	private Boolean impactedByMoqSkuChange ;
	
	@Column(name = MoicConstant.COMBINED_SKU)
	private String combinedSku;
	
	@Column(name = MoicConstant.ITEM_TOTAL_USD, precision = 10, scale = 2)
	private Double itemTotalUSD; 
	
	@Column(name = MoicConstant.WIDTH, length = 10)
	private String width;
	
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

	/**
	 * 
	 */
	public JoorOrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param orderID
	 * @param itemNumber
	 * @param itemStyleId
	 * @param itemColorCode
	 * @param itemSize
	 * @param itemName
	 * @param itemStyleIdentifier
	 * @param itemColor
	 * @param itemUpc
	 * @param itemSizeCode
	 * @param itemPrice
	 * @param itemRetailPrice
	 * @param itemUnitPrice
	 * @param itemPriceAdjustment
	 * @param itemQuantity
	 * @param itemMultiplier
	 * @param itemPriceExtended
	 * @param itemComment
	 * @param itemDoorId
	 * @param itemColorComment
	 * @param impactedByMoqSkuChange
	 * @param combinedSku
	 * @param itemTotalUSD
	 * @param width
	 * @param euSize
	 * @param ukSize
	 * @param userId
	 * @param userName
	 * @param createDate
	 * @param modifiedDate
	 * @param createdBy
	 * @param modifiedBy
	 */
	public JoorOrderItem(Long orderID, String itemNumber, Long itemStyleId, String itemColorCode, String itemSize,
			String itemName, String itemStyleIdentifier, String itemColor, String itemUpc, String itemSizeCode,
			Double itemPrice, Double itemRetailPrice, Double itemUnitPrice, Double itemPriceAdjustment,
			Integer itemQuantity, String itemMultiplier, Double itemPriceExtended, String itemComment,
			Integer itemDoorId, String itemColorComment, Boolean impactedByMoqSkuChange, String combinedSku,
			Double itemTotalUSD, String width, String euSize, String ukSize, String userId, String userName,
			Date createDate, Date modifiedDate, String createdBy, String modifiedBy) {
		super();
		this.orderID = orderID;
		this.itemNumber = itemNumber;
		this.itemStyleId = itemStyleId;
		this.itemColorCode = itemColorCode;
		this.itemSize = itemSize;
		this.itemName = itemName;
		this.itemStyleIdentifier = itemStyleIdentifier;
		this.itemColor = itemColor;
		this.itemUpc = itemUpc;
		this.itemSizeCode = itemSizeCode;
		this.itemPrice = itemPrice;
		this.itemRetailPrice = itemRetailPrice;
		this.itemUnitPrice = itemUnitPrice;
		this.itemPriceAdjustment = itemPriceAdjustment;
		this.itemQuantity = itemQuantity;
		this.itemMultiplier = itemMultiplier;
		this.itemPriceExtended = itemPriceExtended;
		this.itemComment = itemComment;
		this.itemDoorId = itemDoorId;
		this.itemColorComment = itemColorComment;
		this.impactedByMoqSkuChange = impactedByMoqSkuChange;
		this.combinedSku = combinedSku;
		this.itemTotalUSD = itemTotalUSD;
		this.width = width;
		this.euSize = euSize;
		this.ukSize = ukSize;
		this.userId = userId;
		this.userName = userName;
		this.createDate = createDate;
		this.modifiedDate = modifiedDate;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
	}

	public Long getOrderID() {
		return orderID;
	}

	public void setOrderID(Long orderID) {
		this.orderID = orderID;
	}

	public String getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}

	public Long getItemStyleId() {
		return itemStyleId;
	}

	public void setItemStyleId(Long itemStyleId) {
		this.itemStyleId = itemStyleId;
	}

	public String getItemColorCode() {
		return itemColorCode;
	}

	public void setItemColorCode(String itemColorCode) {
		this.itemColorCode = itemColorCode;
	}

	public String getItemSize() {
		return itemSize;
	}

	public void setItemSize(String itemSize) {
		this.itemSize = itemSize;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemStyleIdentifier() {
		return itemStyleIdentifier;
	}

	public void setItemStyleIdentifier(String itemStyleIdentifier) {
		this.itemStyleIdentifier = itemStyleIdentifier;
	}

	public String getItemColor() {
		return itemColor;
	}

	public void setItemColor(String itemColor) {
		this.itemColor = itemColor;
	}

	public String getItemUpc() {
		return itemUpc;
	}

	public void setItemUpc(String itemUpc) {
		this.itemUpc = itemUpc;
	}

	public String getItemSizeCode() {
		return itemSizeCode;
	}

	public void setItemSizeCode(String itemSizeCode) {
		this.itemSizeCode = itemSizeCode;
	}

	public Double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public Double getItemRetailPrice() {
		return itemRetailPrice;
	}

	public void setItemRetailPrice(Double itemRetailPrice) {
		this.itemRetailPrice = itemRetailPrice;
	}

	public Double getItemUnitPrice() {
		return itemUnitPrice;
	}

	public void setItemUnitPrice(Double itemUnitPrice) {
		this.itemUnitPrice = itemUnitPrice;
	}

	public Double getItemPriceAdjustment() {
		return itemPriceAdjustment;
	}

	public void setItemPriceAdjustment(Double itemPriceAdjustment) {
		this.itemPriceAdjustment = itemPriceAdjustment;
	}

	public Integer getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(Integer itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public String getItemMultiplier() {
		return itemMultiplier;
	}

	public void setItemMultiplier(String itemMultiplier) {
		this.itemMultiplier = itemMultiplier;
	}

	public Double getItemPriceExtended() {
		return itemPriceExtended;
	}

	public void setItemPriceExtended(Double itemPriceExtended) {
		this.itemPriceExtended = itemPriceExtended;
	}

	public String getItemComment() {
		return itemComment;
	}

	public void setItemComment(String itemComment) {
		this.itemComment = itemComment;
	}

	public Integer getItemDoorId() {
		return itemDoorId;
	}

	public void setItemDoorId(Integer itemDoorId) {
		this.itemDoorId = itemDoorId;
	}

	public String getItemColorComment() {
		return itemColorComment;
	}

	public void setItemColorComment(String itemColorComment) {
		this.itemColorComment = itemColorComment;
	}

	public Boolean getImpactedByMoqSkuChange() {
		return impactedByMoqSkuChange;
	}

	public void setImpactedByMoqSkuChange(Boolean impactedByMoqSkuChange) {
		this.impactedByMoqSkuChange = impactedByMoqSkuChange;
	}

	public String getCombinedSku() {
		return combinedSku;
	}

	public void setCombinedSku(String combinedSku) {
		this.combinedSku = combinedSku;
	}

	public Double getItemTotalUSD() {
		return itemTotalUSD;
	}

	public void setItemTotalUSD(Double itemTotalUSD) {
		this.itemTotalUSD = itemTotalUSD;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
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

	@Override
	public String toString() {
		return "JoorOrderItem [orderID=" + orderID + ", itemNumber=" + itemNumber + ", itemStyleId=" + itemStyleId
				+ ", itemColorCode=" + itemColorCode + ", itemSize=" + itemSize + ", itemName=" + itemName
				+ ", itemStyleIdentifier=" + itemStyleIdentifier + ", itemColor=" + itemColor + ", itemUpc=" + itemUpc
				+ ", itemSizeCode=" + itemSizeCode + ", itemPrice=" + itemPrice + ", itemRetailPrice=" + itemRetailPrice
				+ ", itemUnitPrice=" + itemUnitPrice + ", itemPriceAdjustment=" + itemPriceAdjustment
				+ ", itemQuantity=" + itemQuantity + ", itemMultiplier=" + itemMultiplier + ", itemPriceExtended="
				+ itemPriceExtended + ", itemComment=" + itemComment + ", itemDoorId=" + itemDoorId
				+ ", itemColorComment=" + itemColorComment + ", impactedByMoqSkuChange=" + impactedByMoqSkuChange
				+ ", combinedSku=" + combinedSku + ", itemTotalUSD=" + itemTotalUSD + ", width=" + width + ", euSize="
				+ euSize + ", ukSize=" + ukSize + ", userId=" + userId + ", userName=" + userName + ", createDate="
				+ createDate + ", modifiedDate=" + modifiedDate + ", createdBy=" + createdBy + ", modifiedBy="
				+ modifiedBy + "]";
	}

}