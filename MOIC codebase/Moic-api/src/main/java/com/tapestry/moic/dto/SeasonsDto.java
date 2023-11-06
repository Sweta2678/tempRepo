package com.tapestry.moic.dto;

import java.io.Serializable;

public class SeasonsDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private Boolean checked;
	
	public SeasonsDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 * @param checked
	 */
	public SeasonsDto(String name, Boolean checked) {
		super();
		this.name = name;
		this.checked = checked;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	@Override
	public String toString() {
		return "SeasonsDto [name=" + name + ", checked=" + checked + "]";
	}

}
