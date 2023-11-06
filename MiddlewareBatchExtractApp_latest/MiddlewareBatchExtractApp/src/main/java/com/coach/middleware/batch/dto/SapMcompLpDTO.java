package com.coach.middleware.batch.dto;

import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;

public class SapMcompLpDTO {
	
	
	
	
	private String seqId;
	private String style;
	private String color;
	private String skuSize;
	private String requestType;
	private String skipVal;
	private String sapCompeletDate; 
	private String status;
	private String statusCode;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSeqId() {
		return seqId;
	}
	public void setSeqId(String seqId) {
		this.seqId = seqId;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getSkuSize() {
		return skuSize;
	}
	public void setSkuSize(String skuSize) {
		this.skuSize = skuSize;
	}
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public String getSkipVal() {
		return skipVal;
	}
	public void setSkipVal(String skipVal) {
		this.skipVal = skipVal;
	}
	public String getSapCompeletDate() {
		return sapCompeletDate;
	}
	public void setSapCompeletDate(String sapCompeletDate) {
		this.sapCompeletDate = sapCompeletDate;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
		

}
