package com.coach.middleware.batch.dao.VO;

public class VariantLookUpVo {
	private String  key;
	private String  value;
	private String  materialType;
	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
	}
	public String getMateruialType() {
		return materialType;
	}
	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}

