package com.plmviewer.model;

import java.io.Serializable;
import java.util.*;

public class BOMModel {
	
	private LinkedHashMap<String,Collection<LinkedHashMap<String,String>>> dataMap;
	private ArrayList<String>  columns;
	private HashMap<String,String>sectionMap;
	private Collection<String>sectionOrder;
	private Collection<String>columnOrder;
	private HashMap<String,String>bomNameMap;
	private String currentBOM;
	private List<SkuInfoVo> skuInfoVos;
	private HashMap<String,String> bomStatusMap;
	

	public BOMModel(){		
		setBomStatusMap();		
	}
	public HashMap<String, String> getBomStatusMap() {
		return bomStatusMap;
	}


	private void setBomStatusMap() {
		this.bomStatusMap = new HashMap<String,String>();
		bomStatusMap.put("bip", "BIP");
		bomStatusMap.put("cancelled", "Cancelled");
		bomStatusMap.put("finalized", "Final");
		bomStatusMap.put("initial", "Initial");
		bomStatusMap.put("validated", "Validated");		
	}


	public List<SkuInfoVo> getSkuInfoVos() {
		return skuInfoVos;
	}


	public void setSkuInfoVos(List<SkuInfoVo> skuInfoVos) {
		this.skuInfoVos = skuInfoVos;
	}


	public LinkedHashMap<String,Collection<LinkedHashMap<String,String>>>  getDataMap() {
		return dataMap;
	}

	public void setDataMap(LinkedHashMap<String,Collection<LinkedHashMap<String,String>>>  dataMap) {
		this.dataMap = dataMap;
	}

	public ArrayList<String> getColumns() {
		return columns;
	}

	public void setColumns(ArrayList<String> columns) {
		this.columns = columns;
	}

	public HashMap<String,String> getSectionMap() {
		return sectionMap;
	}

	public void setSectionMap(HashMap<String,String> sectionMap) {
		this.sectionMap = sectionMap;
	}

	public Collection<String> getSectionOrder() {
		return sectionOrder;
	}

	public void setSectionOrder(Collection<String> sectionOrder) {
		this.sectionOrder = sectionOrder;
	}

	public Collection<String> getColumnOrder() {
		return columnOrder;
	}

	public void setColumnOrder(Collection<String> columnOrder) {
		this.columnOrder = columnOrder;
	}

	public HashMap<String,String> getBomNameMap() {
		return bomNameMap;
	}

	public void setBomNameMap(HashMap<String,String> bomNameMap) {
		this.bomNameMap = bomNameMap;
	}

	public String getCurrentBOM() {
		return currentBOM;
	}

	public void setCurrentBOM(String currentBOM) {
		this.currentBOM = currentBOM;
	}

	
}
