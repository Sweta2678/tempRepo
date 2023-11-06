package com.tapestry.moic.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderReportDto {
	private int	totalBuyQuantity;
	private Date scheduledDeliveryDate;
	private int	targetCost;
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
	public int getTargetCost() {
		return targetCost;
	}
	public void setTargetCost(int targetCost) {
		this.targetCost = targetCost;
	}
	
}
