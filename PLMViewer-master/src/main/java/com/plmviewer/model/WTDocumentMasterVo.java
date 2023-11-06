/**
 * 
 */
package com.plmviewer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author uthanasekarapandian
 *
 */
@Entity
@Table(name="WTDOCUMENTMASTER")
public class WTDocumentMasterVo {
	@Id
	@Column(name="IDA2A2")
	private String ida2a2;
	@Column (name="name")
	private String docMasterName;
	public String getIda2a2() {
		return ida2a2;
	}
	public void setIda2a2(String ida2a2) {
		this.ida2a2 = ida2a2;
	}
	public String getDocMasterName() {
		return docMasterName;
	}
	public void setDocMasterName(String docMasterName) {
		this.docMasterName = docMasterName;
	}

}
