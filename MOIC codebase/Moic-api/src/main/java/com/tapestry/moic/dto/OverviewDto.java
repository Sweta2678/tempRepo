package com.tapestry.moic.dto;

import java.util.Map;

/**
 * The Class OverviewDto.
 *
 * @version 0.0.1
 */
public class OverviewDto {

	private String channel;
	private Map<Integer, Double> monthOrder;
	private Double allMonthsTotal = 0d;
	private Double target = 0d;
	
	public OverviewDto() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param channel
	 * @param monthOrder
	 * @param allMonthsTotal
	 * @param target
	 */
	public OverviewDto(String channel, Map<Integer, Double> monthOrder, Double allMonthsTotal, Double target) {
		super();
		this.channel = channel;
		this.monthOrder = monthOrder;
		this.allMonthsTotal = allMonthsTotal;
		this.target = target;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
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

	public Double getTarget() {
		return target;
	}

	public void setTarget(Double target) {
		this.target = target;
	}

	@Override
	public String toString() {
		return "OverviewDto [channel=" + channel + ", monthOrder=" + monthOrder + ", allMonthsTotal=" + allMonthsTotal
				+ ", target=" + target + "]";
	}
}
