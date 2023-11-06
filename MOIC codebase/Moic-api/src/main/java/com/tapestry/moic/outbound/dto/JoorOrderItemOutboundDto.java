package com.tapestry.moic.outbound.dto;

public class JoorOrderItemOutboundDto {

	private Long orderID;
	private String itemNumber;
	private String itemName;
	private Long itemStyleId;
	private String itemStyleIdentifier;
	private String itemColor;
	private String itemColorCode;
	private String itemUpc;
	private String itemSize;
	private String itemSizeCode;
	private Double itemPrice;
	private Double itemRetailPrice;
	private Double itemUnitPrice;
	private Double itemPriceAdjustment;
	private Integer itemQuantity;
	private String itemMultiplier;
	private Double itemPriceExtended;
	private String itemComment;
	private Integer itemDoorId;
	private String itemColorComment;
	private Double itemTotalUSD;
	private String width;
	private String euSize;
	private String ukSize;
	
	public JoorOrderItemOutboundDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param orderID
	 * @param itemNumber
	 * @param itemName
	 * @param itemStyleId
	 * @param itemStyleIdentifier
	 * @param itemColor
	 * @param itemColorCode
	 * @param itemUpc
	 * @param itemSize
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
	 * @param itemTotalUSD
	 * @param width
	 * @param euSize
	 * @param ukSize
	 */
	public JoorOrderItemOutboundDto(Long orderID, String itemNumber, String itemName, Long itemStyleId,
			String itemStyleIdentifier, String itemColor, String itemColorCode, String itemUpc, String itemSize,
			String itemSizeCode, Double itemPrice, Double itemRetailPrice, Double itemUnitPrice,
			Double itemPriceAdjustment, Integer itemQuantity, String itemMultiplier, Double itemPriceExtended,
			String itemComment, Integer itemDoorId, String itemColorComment, Double itemTotalUSD, String width,
			String euSize, String ukSize) {
		super();
		this.orderID = orderID;
		this.itemNumber = itemNumber;
		this.itemName = itemName;
		this.itemStyleId = itemStyleId;
		this.itemStyleIdentifier = itemStyleIdentifier;
		this.itemColor = itemColor;
		this.itemColorCode = itemColorCode;
		this.itemUpc = itemUpc;
		this.itemSize = itemSize;
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
		this.itemTotalUSD = itemTotalUSD;
		this.width = width;
		this.euSize = euSize;
		this.ukSize = ukSize;
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

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Long getItemStyleId() {
		return itemStyleId;
	}

	public void setItemStyleId(Long itemStyleId) {
		this.itemStyleId = itemStyleId;
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

	public String getItemColorCode() {
		return itemColorCode;
	}

	public void setItemColorCode(String itemColorCode) {
		this.itemColorCode = itemColorCode;
	}

	public String getItemUpc() {
		return itemUpc;
	}

	public void setItemUpc(String itemUpc) {
		this.itemUpc = itemUpc;
	}

	public String getItemSize() {
		return itemSize;
	}

	public void setItemSize(String itemSize) {
		this.itemSize = itemSize;
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

	@Override
	public String toString() {
		return "JoorOrderItemOutboundDto [orderID=" + orderID + ", itemNumber=" + itemNumber + ", itemName=" + itemName
				+ ", itemStyleId=" + itemStyleId + ", itemStyleIdentifier=" + itemStyleIdentifier + ", itemColor="
				+ itemColor + ", itemColorCode=" + itemColorCode + ", itemUpc=" + itemUpc + ", itemSize=" + itemSize
				+ ", itemSizeCode=" + itemSizeCode + ", itemPrice=" + itemPrice + ", itemRetailPrice=" + itemRetailPrice
				+ ", itemUnitPrice=" + itemUnitPrice + ", itemPriceAdjustment=" + itemPriceAdjustment
				+ ", itemQuantity=" + itemQuantity + ", itemMultiplier=" + itemMultiplier + ", itemPriceExtended="
				+ itemPriceExtended + ", itemComment=" + itemComment + ", itemDoorId=" + itemDoorId
				+ ", itemColorComment=" + itemColorComment + ", itemTotalUSD=" + itemTotalUSD + ", width=" + width
				+ ", euSize=" + euSize + ", ukSize=" + ukSize + "]";
	}

	
}
