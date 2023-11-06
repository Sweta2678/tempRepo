package com.coach.middleware.batch.dao.VO;

import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;

public class SapMcompLpVO {
	
	private String seqId;
	private String style;
	private String colorDesc;
	private String skuSize;
	private String requestType;
	private String color;
	private String recdFromSapflag;
	private String sapCompeletDate; 
	private String status;
	private String statusCode;
	
	/*@Resource(name = "sapMcompLpProps")
	private Properties sapMcompLpProps;
	
	@Value("#{sapMcompLpProps['com.coach.middleware.sapmcomplp.targetDirectory']}")
	private String targetDirectory;
	
	@Value("#{sapMcompLpProps['com.coach.middleware.sapmcomplp.targetFile']}")
	private String targetFile;
	
	@Value("#{sapMcompLpProps['com.coach.middleware.sapmcomplp.targetDestDirectory']}")
	private String targetDestDirectory;
	
	
	
	
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
	}*/
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
	public String getColorDesc() {
		return colorDesc;
	}
	public void setColorDesc(String colorDesc) {
		this.colorDesc = colorDesc;
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
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	/*public Properties getSapMcompLpProps() {
		return sapMcompLpProps;
	}
	public void setSapMcompLpProps(Properties sapMcompLpProps) {
		this.sapMcompLpProps = sapMcompLpProps;
	}*/
	public String getRecdFromSapflag() {
		return recdFromSapflag;
	}
	public void setRecdFromSapflag(String recdFromSapflag) {
		this.recdFromSapflag = recdFromSapflag;
	}
	public String getSapCompeletDate() {
		return sapCompeletDate;
	}
	public void setSapCompeletDate(String sapCompeletDate) {
		this.sapCompeletDate = sapCompeletDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	
	

}
