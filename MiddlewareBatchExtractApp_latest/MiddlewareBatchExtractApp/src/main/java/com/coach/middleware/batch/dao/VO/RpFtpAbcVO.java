package com.coach.middleware.batch.dao.VO;

import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;

public class RpFtpAbcVO {
	
	private String skuColumn;
	private String abcCode;
	private String mpg;
	private String styleNumber;
	private String skuName;
	
	/*@Resource(name = "rpFtpAbcProps")
	private Properties rpFtpAbcProps;
	
	@Value("#{rpFtpAbcProps['com.coach.middleware.rpftpabc.targetDirectory']}")
	private String targetDirectory;
	
	@Value("#{rpFtpAbcProps['com.coach.middleware.rpftpabc.targetFile']}")
	private String targetFile;
	
	@Value("#{rpFtpAbcProps['com.coach.middleware.rpftpabc.targetDestDirectory']}")
	private String targetDestDirectory;
	
	@Value("#{rpFtpAbcProps['com.coach.middleware.rpftpabc.targetDestFile']}")
	private String targetDestFile;*/
	
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
	/*public Properties getRpFtpAbcProps() {
		return rpFtpAbcProps;
	}
	public void setRpFtpAbcProps(Properties rpFtpAbcProps) {
		this.rpFtpAbcProps = rpFtpAbcProps;
	}
	public String getTargetDirectory() {
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
	}
	public String getTargetDestFile() {
		return targetDestFile;
	}
	public void setTargetDestFile(String targetDestFile) {
		this.targetDestFile = targetDestFile;
	}*/

}
