package com.tapestry.moic.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tapestry.moic.constant.MoicConstant;

@Entity
@Table(name = MoicConstant.ROLES)
public class Roles implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = MoicConstant.ROLE_ID)
	private Integer roleId;
	
	@Column(name = MoicConstant.Role, length = 50)
	private String role;
	
	@Column(name = MoicConstant.HAS_OVERVIEW)
	private Boolean hasOverview;
	
	@Column(name = MoicConstant.HAS_MOQ)
	private Boolean hasMoq;
	
	@Column(name = MoicConstant.HAS_SKU_CHANGE)
	private Boolean hasSKUChange;
	
	@Column(name = MoicConstant.HAS_UPC_VALIDATION)
	private Boolean hasUPCValidation;
	
	@Column(name = MoicConstant.HAS_S4_UPLOAD_FILE)
	private Boolean hasS4UploadFile;
	
	@Column(name = MoicConstant.HAS_S4_UPLOAD_FILE_ACTION_UPLOAD_TO_S4)
	private Boolean hasS4UploadFile_action_uploadToS4;
	
	@Column(name = MoicConstant.HAS_CONFIG)
	private Boolean hasConfig;
	
	@Column(name = MoicConstant.HAS_AUDIT_REPORT)
	private Boolean hasAuditReport;
	
	@Column(name = MoicConstant.LANDING_PAGE)
	private String landingPage;
	
	@Column(name = MoicConstant.USER_ID)
	private Integer userId;
	
	@Column(name = MoicConstant.USER_NAME, length = 50)
	private String userName;
	
	@Column(name = MoicConstant.CREATE_BY, length = 50)
	private String createdBy;
	
	@Column(name = MoicConstant.CREATE_DATE)
	private Date createdDate;
	
	@Column(name = MoicConstant.MODIFIED_BY, length = 50)
	private String modifiedBy;
	
	@Column(name = MoicConstant.MODIFIED_DATE)
	private Date modifiedDate;

	/**
	 * 
	 */
	public Roles() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param roleId
	 * @param role
	 * @param hasOverview
	 * @param hasMoq
	 * @param hasSKUChange
	 * @param hasUPCValidation
	 * @param hasS4UploadFile
	 * @param hasS4UploadFile_action_uploadToS4
	 * @param hasConfig
	 * @param hasAuditReport
	 * @param landingPage
	 * @param userId
	 * @param userName
	 * @param createdBy
	 * @param createdDate
	 * @param modifiedBy
	 * @param modifiedDate
	 */
	public Roles(Integer roleId, String role, Boolean hasOverview, Boolean hasMoq, Boolean hasSKUChange,
			Boolean hasUPCValidation, Boolean hasS4UploadFile, Boolean hasS4UploadFile_action_uploadToS4,
			Boolean hasConfig, Boolean hasAuditReport, String landingPage, Integer userId, String userName,
			String createdBy, Date createdDate, String modifiedBy, Date modifiedDate) {
		super();
		this.roleId = roleId;
		this.role = role;
		this.hasOverview = hasOverview;
		this.hasMoq = hasMoq;
		this.hasSKUChange = hasSKUChange;
		this.hasUPCValidation = hasUPCValidation;
		this.hasS4UploadFile = hasS4UploadFile;
		this.hasS4UploadFile_action_uploadToS4 = hasS4UploadFile_action_uploadToS4;
		this.hasConfig = hasConfig;
		this.hasAuditReport = hasAuditReport;
		this.landingPage = landingPage;
		this.userId = userId;
		this.userName = userName;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Override
	public String toString() {
		return "Roles [roleId=" + roleId + ", role=" + role + ", hasOverview=" + hasOverview + ", hasMoq=" + hasMoq
				+ ", hasSKUChange=" + hasSKUChange + ", hasUPCValidation=" + hasUPCValidation + ", hasS4UploadFile="
				+ hasS4UploadFile + ", hasS4UploadFile_action_uploadToS4=" + hasS4UploadFile_action_uploadToS4
				+ ", hasConfig=" + hasConfig + ", hasAuditReport=" + hasAuditReport + ", landingPage=" + landingPage
				+ ", userId=" + userId + ", userName=" + userName + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate + "]";
	}
}
