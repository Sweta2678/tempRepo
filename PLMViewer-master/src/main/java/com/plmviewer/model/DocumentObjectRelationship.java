/**
 * 
 */
package com.plmviewer.model;

import java.math.BigDecimal;

/**
 * @author uthanasekarapandian
 *
 */
public class DocumentObjectRelationship {
	
	private String documentName;
	private String documentType;
	private String primaryFileName;
	private BigDecimal documentTypeId;
	/**
	 * @return the documentTypeId
	 */
	public BigDecimal getDocumentTypeId() {
		return documentTypeId;
	}
	/**
	 * @param documentTypeId the documentTypeId to set
	 */
	public void setDocumentTypeId(BigDecimal documentTypeId) {
		this.documentTypeId = documentTypeId;
	}
	/**
	 * @return the documentName
	 */
	public String getDocumentName() {
		return documentName;
	}
	/**
	 * @param documentName the documentName to set
	 */
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	/**
	 * @return the documentType
	 */
	public String getDocumentType() {
		return documentType;
	}
	/**
	 * @param documentType the documentType to set
	 */
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	/**
	 * @return the primaryFileName
	 */
	public String getPrimaryFileName() {
		return primaryFileName;
	}
	/**
	 * @param primaryFileName the primaryFileName to set
	 */
	public void setPrimaryFileName(String primaryFileName) {
		this.primaryFileName = primaryFileName;
	}
	

}
