package com.tapestry.moic.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RolesDto implements Serializable{
	
	private static final long serialVersionUID = 5266245040840722147L;
	
	private Boolean hasOverview;
	private Boolean hasMoq;
	private Boolean hasSKUChange;
	private Boolean hasUPCValidation;
	private Boolean hasS4UploadFile;
	private Boolean hasS4UploadFile_action_uploadToS4;
	private Boolean hasConfig;
	private Boolean hasAuditReport;
	private String landingPage;
	
	public RolesDto() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param hasOverview
	 * @param hasMoq
	 * @param hasSKUChange
	 * @param hasUPCValidation
	 * @param hasS4UploadFile
	 * @param hasS4UploadFile_action_uploadToS4
	 * @param hasConfig
	 * @param hasAuditReport
	 * @param landingPage
	 */
	public RolesDto(Boolean hasOverview, Boolean hasMoq, Boolean hasSKUChange, Boolean hasUPCValidation,
			Boolean hasS4UploadFile, Boolean hasS4UploadFile_action_uploadToS4, Boolean hasConfig,
			Boolean hasAuditReport, String landingPage) {
		super();
		this.hasOverview = hasOverview;
		this.hasMoq = hasMoq;
		this.hasSKUChange = hasSKUChange;
		this.hasUPCValidation = hasUPCValidation;
		this.hasS4UploadFile = hasS4UploadFile;
		this.hasS4UploadFile_action_uploadToS4 = hasS4UploadFile_action_uploadToS4;
		this.hasConfig = hasConfig;
		this.hasAuditReport = hasAuditReport;
		this.landingPage = landingPage;
	}

	public Boolean getHasOverview() {
		return hasOverview;
	}

	public void setHasOverview(Boolean hasOverview) {
		this.hasOverview = hasOverview;
	}

	public Boolean getHasMoq() {
		return hasMoq;
	}

	public void setHasMoq(Boolean hasMoq) {
		this.hasMoq = hasMoq;
	}

	public Boolean getHasSKUChange() {
		return hasSKUChange;
	}

	public void setHasSKUChange(Boolean hasSKUChange) {
		this.hasSKUChange = hasSKUChange;
	}

	public Boolean getHasUPCValidation() {
		return hasUPCValidation;
	}

	public void setHasUPCValidation(Boolean hasUPCValidation) {
		this.hasUPCValidation = hasUPCValidation;
	}

	public Boolean getHasS4UploadFile() {
		return hasS4UploadFile;
	}

	public void setHasS4UploadFile(Boolean hasS4UploadFile) {
		this.hasS4UploadFile = hasS4UploadFile;
	}
	
	public Boolean getHasS4UploadFile_action_uploadToS4() {
		return hasS4UploadFile_action_uploadToS4;
	}

	public void setHasS4UploadFile_action_uploadToS4(Boolean hasS4UploadFile_action_uploadToS4) {
		this.hasS4UploadFile_action_uploadToS4 = hasS4UploadFile_action_uploadToS4;
	}

	public Boolean getHasConfig() {
		return hasConfig;
	}

	public void setHasConfig(Boolean hasConfig) {
		this.hasConfig = hasConfig;
	}

	public Boolean getHasAuditReport() {
		return hasAuditReport;
	}

	public void setHasAuditReport(Boolean hasAuditReport) {
		this.hasAuditReport = hasAuditReport;
	}

	public String getLandingPage() {
		return landingPage;
	}

	public void setLandingPage(String landingPage) {
		this.landingPage = landingPage;
	}

	@Override
	public String toString() {
		return "RolesDto [hasOverview=" + hasOverview + ", hasMoq=" + hasMoq + ", hasSKUChange=" + hasSKUChange
				+ ", hasUPCValidation=" + hasUPCValidation + ", hasS4UploadFile=" + hasS4UploadFile
				+ ", hasS4UploadFile_action_uploadToS4=" + hasS4UploadFile_action_uploadToS4 + ", hasConfig="
				+ hasConfig + ", hasAuditReport=" + hasAuditReport + ", landingPage=" + landingPage + "]";
	}
}
