package com.tapestry.moic.dto;

import java.io.Serializable;

/**
 * The Class AccountMaster.
 *
 * @version 0.0.1
 */
public class JoorOrderItemDto implements Serializable {

	private static final long serialVersionUID = 1L; 
	
	private int	orderID;
	private String	itemName_Style;
	private String	itemNumber_Style;
	private String	itemStyleID_Style;
	private String	itemStyleIdentifier_Style;
	private String	itemColor_Style;
	private String	itemColorCode_Style;
	private String	itemUPC_Style;
	private String	itemSize_Style;
	private String	itemSizeCode_Style;
	private int	itemPrice_Style;
	private int	itemUnitPrice_Style;
	private int	itemPriceAdjustment_Style;
	private int	itemRetailPrice_Style;
	private int	itemQuantity_Style;
	private int	itemMultiplier_Style;
	private int	itemPriceExtended_Style;
	private String	itemComment_Style;
	private String	itemColorComment_Style;
	private String	itemDoorID_Style;
	
	public JoorOrderItemDto() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param orderID
	 * @param itemName_Style
	 * @param itemNumber_Style
	 * @param itemStyleID_Style
	 * @param itemStyleIdentifier_Style
	 * @param itemColor_Style
	 * @param itemColorCode_Style
	 * @param itemUPC_Style
	 * @param itemSize_Style
	 * @param itemSizeCode_Style
	 * @param itemPrice_Style
	 * @param itemUnitPrice_Style
	 * @param itemPriceAdjustment_Style
	 * @param itemRetailPrice_Style
	 * @param itemQuantity_Style
	 * @param itemMultiplier_Style
	 * @param itemPriceExtended_Style
	 * @param itemComment_Style
	 * @param itemColorComment_Style
	 * @param itemDoorID_Style
	 */
	public JoorOrderItemDto(int orderID, String itemName_Style, String itemNumber_Style, String itemStyleID_Style,
			String itemStyleIdentifier_Style, String itemColor_Style, String itemColorCode_Style, String itemUPC_Style,
			String itemSize_Style, String itemSizeCode_Style, int itemPrice_Style, int itemUnitPrice_Style,
			int itemPriceAdjustment_Style, int itemRetailPrice_Style, int itemQuantity_Style, int itemMultiplier_Style,
			int itemPriceExtended_Style, String itemComment_Style, String itemColorComment_Style,
			String itemDoorID_Style) {
		super();
		this.orderID = orderID;
		this.itemName_Style = itemName_Style;
		this.itemNumber_Style = itemNumber_Style;
		this.itemStyleID_Style = itemStyleID_Style;
		this.itemStyleIdentifier_Style = itemStyleIdentifier_Style;
		this.itemColor_Style = itemColor_Style;
		this.itemColorCode_Style = itemColorCode_Style;
		this.itemUPC_Style = itemUPC_Style;
		this.itemSize_Style = itemSize_Style;
		this.itemSizeCode_Style = itemSizeCode_Style;
		this.itemPrice_Style = itemPrice_Style;
		this.itemUnitPrice_Style = itemUnitPrice_Style;
		this.itemPriceAdjustment_Style = itemPriceAdjustment_Style;
		this.itemRetailPrice_Style = itemRetailPrice_Style;
		this.itemQuantity_Style = itemQuantity_Style;
		this.itemMultiplier_Style = itemMultiplier_Style;
		this.itemPriceExtended_Style = itemPriceExtended_Style;
		this.itemComment_Style = itemComment_Style;
		this.itemColorComment_Style = itemColorComment_Style;
		this.itemDoorID_Style = itemDoorID_Style;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public String getItemName_Style() {
		return itemName_Style;
	}

	public void setItemName_Style(String itemName_Style) {
		this.itemName_Style = itemName_Style;
	}

	public String getItemNumber_Style() {
		return itemNumber_Style;
	}

	public void setItemNumber_Style(String itemNumber_Style) {
		this.itemNumber_Style = itemNumber_Style;
	}

	public String getItemStyleID_Style() {
		return itemStyleID_Style;
	}

	public void setItemStyleID_Style(String itemStyleID_Style) {
		this.itemStyleID_Style = itemStyleID_Style;
	}

	public String getItemStyleIdentifier_Style() {
		return itemStyleIdentifier_Style;
	}

	public void setItemStyleIdentifier_Style(String itemStyleIdentifier_Style) {
		this.itemStyleIdentifier_Style = itemStyleIdentifier_Style;
	}

	public String getItemColor_Style() {
		return itemColor_Style;
	}

	public void setItemColor_Style(String itemColor_Style) {
		this.itemColor_Style = itemColor_Style;
	}

	public String getItemColorCode_Style() {
		return itemColorCode_Style;
	}

	public void setItemColorCode_Style(String itemColorCode_Style) {
		this.itemColorCode_Style = itemColorCode_Style;
	}

	public String getItemUPC_Style() {
		return itemUPC_Style;
	}

	public void setItemUPC_Style(String itemUPC_Style) {
		this.itemUPC_Style = itemUPC_Style;
	}

	public String getItemSize_Style() {
		return itemSize_Style;
	}

	public void setItemSize_Style(String itemSize_Style) {
		this.itemSize_Style = itemSize_Style;
	}

	public String getItemSizeCode_Style() {
		return itemSizeCode_Style;
	}

	public void setItemSizeCode_Style(String itemSizeCode_Style) {
		this.itemSizeCode_Style = itemSizeCode_Style;
	}

	public int getItemPrice_Style() {
		return itemPrice_Style;
	}

	public void setItemPrice_Style(int itemPrice_Style) {
		this.itemPrice_Style = itemPrice_Style;
	}

	public int getItemUnitPrice_Style() {
		return itemUnitPrice_Style;
	}

	public void setItemUnitPrice_Style(int itemUnitPrice_Style) {
		this.itemUnitPrice_Style = itemUnitPrice_Style;
	}

	public int getItemPriceAdjustment_Style() {
		return itemPriceAdjustment_Style;
	}

	public void setItemPriceAdjustment_Style(int itemPriceAdjustment_Style) {
		this.itemPriceAdjustment_Style = itemPriceAdjustment_Style;
	}

	public int getItemRetailPrice_Style() {
		return itemRetailPrice_Style;
	}

	public void setItemRetailPrice_Style(int itemRetailPrice_Style) {
		this.itemRetailPrice_Style = itemRetailPrice_Style;
	}

	public int getItemQuantity_Style() {
		return itemQuantity_Style;
	}

	public void setItemQuantity_Style(int itemQuantity_Style) {
		this.itemQuantity_Style = itemQuantity_Style;
	}

	public int getItemMultiplier_Style() {
		return itemMultiplier_Style;
	}

	public void setItemMultiplier_Style(int itemMultiplier_Style) {
		this.itemMultiplier_Style = itemMultiplier_Style;
	}

	public int getItemPriceExtended_Style() {
		return itemPriceExtended_Style;
	}

	public void setItemPriceExtended_Style(int itemPriceExtended_Style) {
		this.itemPriceExtended_Style = itemPriceExtended_Style;
	}

	public String getItemComment_Style() {
		return itemComment_Style;
	}

	public void setItemComment_Style(String itemComment_Style) {
		this.itemComment_Style = itemComment_Style;
	}

	public String getItemColorComment_Style() {
		return itemColorComment_Style;
	}

	public void setItemColorComment_Style(String itemColorComment_Style) {
		this.itemColorComment_Style = itemColorComment_Style;
	}

	public String getItemDoorID_Style() {
		return itemDoorID_Style;
	}

	public void setItemDoorID_Style(String itemDoorID_Style) {
		this.itemDoorID_Style = itemDoorID_Style;
	}

	@Override
	public String toString() {
		return "JoorOrderItemDto [OrderID=" + orderID + ", itemName_Style=" + itemName_Style + ", itemNumber_Style="
				+ itemNumber_Style + ", itemStyleID_Style=" + itemStyleID_Style + ", itemStyleIdentifier_Style="
				+ itemStyleIdentifier_Style + ", itemColor_Style=" + itemColor_Style + ", itemColorCode_Style="
				+ itemColorCode_Style + ", itemUPC_Style=" + itemUPC_Style + ", itemSize_Style=" + itemSize_Style
				+ ", itemSizeCode_Style=" + itemSizeCode_Style + ", itemPrice_Style=" + itemPrice_Style
				+ ", itemUnitPrice_Style=" + itemUnitPrice_Style + ", itemPriceAdjustment_Style="
				+ itemPriceAdjustment_Style + ", itemRetailPrice_Style=" + itemRetailPrice_Style
				+ ", itemQuantity_Style=" + itemQuantity_Style + ", itemMultiplier_Style=" + itemMultiplier_Style
				+ ", itemPriceExtended_Style=" + itemPriceExtended_Style + ", itemComment_Style=" + itemComment_Style
				+ ", itemColorComment_Style=" + itemColorComment_Style + ", itemDoorID_Style=" + itemDoorID_Style + "]";
	}
}
