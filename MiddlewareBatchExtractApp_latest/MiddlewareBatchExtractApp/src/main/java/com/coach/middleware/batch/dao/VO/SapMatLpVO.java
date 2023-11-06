package com.coach.middleware.batch.dao.VO;

import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;

public class SapMatLpVO {
	
	private String matNum;
	private String matDesc;
	private String upc;
	private String stdCost;
	private String retailPrice;
	private String whPrice;
	private String fsTicketPrice;
	private String quality;
	private String style;
	private String color;
	
	/*@Resource(name = "sapMatLpProps")
	private Properties sapMatLpProps;
	
	@Value("#{sapMatLpProps['com.coach.middleware.sapmatlp.targetDirectory']}")
	private String targetDirectory;
	
	@Value("#{sapMatLpProps['com.coach.middleware.sapmatlp.targetFile']}")
	private String targetFile;
	
	@Value("#{sapMatLpProps['com.coach.middleware.sapmatlp.targetDestDirectory']}")
	private String targetDestDirectory;*/
	
	
	
	
	/*public String getTargetDirectory() {
		return targetDirectory;
	}
	public void setTargetDirectory(String targetDirectory) {
		this.targetDirectory = targetDirectory;
	}
	public String getTargetFile() {
		return targetFile;
	}
	public void setTargetFile(String targetFile) {
		this.targetFile = targetFile;
	}
	public String getTargetDestDirectory() {
		return targetDestDirectory;
	}
	public void setTargetDestDirectory(String targetDestDirectory) {
		this.targetDestDirectory = targetDestDirectory;
	}*/
	public String getMatNum() {
		return matNum;
	}
	public void setMatNum(String matNum) {
		this.matNum = matNum;
	}
	public String getMatDesc() {
		return matDesc;
	}
	public void setMatDesc(String matDesc) {
		this.matDesc = matDesc;
	}
	public String getUpc() {
		return upc;
	}
	public void setUpc(String upc) {
		this.upc = upc;
	}
	public String getStdCost() {
		return stdCost;
	}
	public void setStdCost(String stdCost) {
		this.stdCost = stdCost;
	}
	public String getRetailPrice() {
		return retailPrice;
	}
	public void setRetailPrice(String retailPrice) {
		this.retailPrice = retailPrice;
	}
	public String getWhPrice() {
		return whPrice;
	}
	public void setWhPrice(String whPrice) {
		this.whPrice = whPrice;
	}
	public String getFsTicketPrice() {
		return fsTicketPrice;
	}
	public void setFsTicketPrice(String fsTicketPrice) {
		this.fsTicketPrice = fsTicketPrice;
	}
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
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
	/*public Properties getSapMatLpProps() {
		return sapMatLpProps;
	}
	public void setSapMatLpProps(Properties sapMatLpProps) {
		this.sapMatLpProps = sapMatLpProps;
	}*/
	

}
