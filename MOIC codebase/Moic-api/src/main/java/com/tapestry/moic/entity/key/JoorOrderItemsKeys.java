package com.tapestry.moic.entity.key;

import java.io.Serializable;

import javax.persistence.Column;

import com.tapestry.moic.constant.MoicConstant;

public class JoorOrderItemsKeys implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = MoicConstant.ORDER_ID)
	private Long orderID;
	
	@Column(name = MoicConstant.ITEM_NUMBER)
	private String itemNumber;
	
	@Column(name = MoicConstant.ITEM_COLOR_CODE)
	private String itemColorCode;
	
	@Column(name = MoicConstant.ITEM_SIZE)
	private String itemSize;
	
	@Column(name = MoicConstant.ITEM_STYLE_ID)
	private Long itemStyleId;

	/**
	 * 
	 */
	public JoorOrderItemsKeys() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param orderID
	 * @param itemNumber
	 * @param itemColorCode
	 * @param itemSize
	 * @param itemStyleId
	 */
	public JoorOrderItemsKeys(Long orderID, String itemNumber, String itemColorCode, String itemSize,
			Long itemStyleId) {
		super();
		this.orderID = orderID;
		this.itemNumber = itemNumber;
		this.itemColorCode = itemColorCode;
		this.itemSize = itemSize;
		this.itemStyleId = itemStyleId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemColorCode == null) ? 0 : itemColorCode.hashCode());
		result = prime * result + ((itemNumber == null) ? 0 : itemNumber.hashCode());
		result = prime * result + ((itemSize == null) ? 0 : itemSize.hashCode());
		result = prime * result + ((itemStyleId == null) ? 0 : itemStyleId.hashCode());
		result = prime * result + ((orderID == null) ? 0 : orderID.hashCode());
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
		JoorOrderItemsKeys other = (JoorOrderItemsKeys) obj;
		if (itemColorCode == null) {
			if (other.itemColorCode != null)
				return false;
		} else if (!itemColorCode.equals(other.itemColorCode))
			return false;
		if (itemNumber == null) {
			if (other.itemNumber != null)
				return false;
		} else if (!itemNumber.equals(other.itemNumber))
			return false;
		if (itemSize == null) {
			if (other.itemSize != null)
				return false;
		} else if (!itemSize.equals(other.itemSize))
			return false;
		if (itemStyleId == null) {
			if (other.itemStyleId != null)
				return false;
		} else if (!itemStyleId.equals(other.itemStyleId))
			return false;
		if (orderID == null) {
			if (other.orderID != null)
				return false;
		} else if (!orderID.equals(other.orderID))
			return false;
		return true;
	}	
}