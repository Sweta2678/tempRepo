/**
 * 
 */
package com.plmviewer.model;

import java.util.List;

/**
 * @author uthanasekarapandian
 *
 */
public class ProductGenDetailsVo {
	private List<String> season;
	private List<String> source;
	private List<String> specification;
	private List<String> skuName;
	private String type;
	private String  productLifeCycleState;
	
	/**
	 * @return the season
	 */
	public List<String> getSeason() {
		return season;
	}
	/**
	 * @param season the season to set
	 */
	public void setSeason(List<String> season) {
		this.season = season;
	}
	/**
	 * @return the source
	 */
	public List<String> getSource() {
		return source;
	}
	/**
	 * @param source the source to set
	 */
	public void setSource(List<String> source) {
		this.source = source;
	}
	/**
	 * @return the skuName
	 */
	public List<String> getSkuName() {
		return skuName;
	}
	/**
	 * @param skuName the skuName to set
	 */
	public void setSkuName(List<String> skuName) {
		this.skuName = skuName;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the productLifeCycleState
	 */
	public String getProductLifeCycleState() {
		return productLifeCycleState;
	}
	/**
	 * @param productLifeCycleState the productLifeCycleState to set
	 */
	public void setProductLifeCycleState(String productLifeCycleState) {
		this.productLifeCycleState = productLifeCycleState;
	}

	/**
	 * @return the specification
	 */
	public List<String> getSpecification() {
		return specification;
	}
	/**
	 * @param specification the specification to set
	 */
	public void setSpecification(List<String> specification) {
		this.specification = specification;
	}
}
