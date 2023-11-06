package com.coach.middleware.batch.dto;

import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;

public class RpFtpAbcDTO {
	
	
	
	
	private String skuColumn;
	private String abcCode;
	private String mpg;
	private String skipVal;
	private String styleNumber;
	private String skuName;
	
	public String getSkuColumn() {
		return skuColumn;
	}
	public void setSkuColumn(String skuColumn) {
		this.skuColumn = skuColumn;
	}
	public String getAbcCode() {
		return abcCode;
	}
	public void setAbcCode(String abcCode) {
		this.abcCode = abcCode;
	}
	public String getMpg() {
		return mpg;
	}
	public void setMpg(String mpg) {
		this.mpg = mpg;
	}
	public String getSkipVal() {
		return skipVal;
	}
	public void setSkipVal(String skipVal) {
		this.skipVal = skipVal;
	}
	public String getStyleNumber() {
		return styleNumber;
	}
	public void setStyleNumber(String styleNumber) {
		this.styleNumber = styleNumber;
	}
	public String getSkuName() {
		return skuName;
	}
	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}
	

}
