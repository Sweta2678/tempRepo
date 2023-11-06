/**
 * 
 */
package com.plmviewer.model;

import java.math.BigDecimal;

/**
 * @author uthanasekarapandian
 *
 */
public class DocumentSearchResult {

    private String documentName;
    private BigDecimal ida3a11; 
    private String documentType;
    private String createDate;
    private String updateDate;

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

	/**
	 * @return the ida3a11
	 */
	public BigDecimal getIda3a11() {
		return ida3a11;
	}

	/**
	 * @param ida3a11 the ida3a11 to set
	 */
	public void setIda3a11(BigDecimal ida3a11) {
		this.ida3a11 = ida3a11;
	}


}
