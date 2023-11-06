package com.tapestry.moic.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenDto implements Serializable{
	
	private static final long serialVersionUID = 8745851898266990740L;
	
	private String emailAddress;
	private String displayName;
	
	public TokenDto() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param emailAddress
	 * @param displayName
	 */
	public TokenDto(String emailAddress, String displayName) {
		super();
		this.emailAddress = emailAddress;
		this.displayName = displayName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@Override
	public String toString() {
		return "TokenDto [emailAddress=" + emailAddress + ", displayName=" + displayName + "]";
	}
}
