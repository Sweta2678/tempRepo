package com.tapestry.moic.dto;

import java.util.Map;

/**
 * The Class MOQOverviewDto.
 *
 * @version 0.0.1
 */
public class MOQOverviewDto {

	private String sku;
	private Map<Integer, Double> monthOrder;
	private Double allMonthsTotal = 0d;
	
	public MOQOverviewDto() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param sku
	 * @param monthOrder
	 * @param allMonthsTotal
	 */
	public MOQOverviewDto(String sku, Map<Integer, Double> monthOrder, Double allMonthsTotal) {
		super();
		this.sku = sku;
		this.monthOrder = monthOrder;
		this.allMonthsTotal = allMonthsTotal;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public Map<Integer, Double> getMonthOrder() {
		return monthOrder;
	}

	public void setMonthOrder(Map<Integer, Double> monthOrder) {
		this.monthOrder = monthOrder;
	}

	public Double getAllMonthsTotal() {
		return allMonthsTotal;
	}

	public void setAllMonthsTotal(Double allMonthsTotal) {
		this.allMonthsTotal = allMonthsTotal;
	}

	@Override
	public String toString() {
		return "MOQOverviewDto [sku=" + sku + ", monthOrder=" + monthOrder + ", allMonthsTotal=" + allMonthsTotal + "]";
	}
}
