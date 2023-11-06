package com.pmdb.coach.inbound.codeTable;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pmdb.coach.common.constants.IBConstants;
import com.pmdb.coach.db.outbound.respository.IntroDateMasterRepository;
import com.pmdb.coach.db.repository.AttributeStoreTableRepository;
import com.pmdb.coach.db.repository.DepartmentSubClassRepository;
import com.pmdb.coach.db.repository.DeptSubCollectionRepository;
import com.pmdb.coach.db.repository.FiscalTableRepository;
import com.pmdb.coach.db.repository.MaterialAttributeRepository;
import com.pmdb.coach.db.repository.PMSMGMappingRepository;
import com.pmdb.coach.db.repository.PremiumFXRateCodeRepository;
import com.pmdb.coach.db.repository.SeasonGroupRepository;
import com.pmdb.coach.db.repository.SizeScaleMasterRepository;
import com.pmdb.coach.db.repository.VariantMasterRepository;
import com.pmdb.coach.model.inbound.AttributeStoreTable;
import com.pmdb.coach.model.inbound.AttributeStoreTable_PK;
import com.pmdb.coach.model.inbound.DepartmentClassSubClass;
import com.pmdb.coach.model.inbound.DepartmentClassSubClass_PK;
import com.pmdb.coach.model.inbound.DeptCollectionSubCollection;
import com.pmdb.coach.model.inbound.DeptCollectionSubCollection_PK;
import com.pmdb.coach.model.inbound.FiscalTable;
import com.pmdb.coach.model.inbound.MaterialAttributeStore;
import com.pmdb.coach.model.inbound.MaterialAttributeStore_PK;
import com.pmdb.coach.model.inbound.PMSMGMapping;
import com.pmdb.coach.model.inbound.PremiumFXRateCodeTable;
import com.pmdb.coach.model.inbound.PremiumFXRateCodeTable_PK;
import com.pmdb.coach.model.inbound.SeasonGroup;
import com.pmdb.coach.model.inbound.SizeScaleMaster;
import com.pmdb.coach.model.inbound.VariantMaster;
import com.pmdb.coach.model.outbound.IntroDateMaster;
import com.pmdb.coach.util.FormatHelper;
import com.pmdb.coach.util.PropertyReaderUtil;

@Service
public class CodeTableServiceImpl {
	
	private static final Logger logger = LoggerFactory.getLogger(CodeTableService.class);

	@Autowired
	AttributeStoreTableRepository attributeStoreTableRepository;

	@Autowired
	IntroDateMasterRepository introDateMasterRepository;

	@Autowired
	DeptSubCollectionRepository deptSubCollectionRepository;

	@Autowired
	DepartmentSubClassRepository departmentSubClassRepository;

	@Autowired
	VariantMasterRepository variantMasterRepository;

	@Autowired
	FiscalTableRepository fiscalTableRepository;

	@Autowired
	PremiumFXRateCodeRepository premiumFXRateCodeRepository;

	@Autowired
	PMSMGMappingRepository pMSMGMappingRepository;

	@Autowired
	SizeScaleMasterRepository sizeScaleMappingRepository;

	@Autowired
	SeasonGroupRepository seasonGroupRepository;

	@Autowired
	MaterialAttributeRepository materialAttributeRepository;

	@Autowired
	private PropertyReaderUtil propertyReaderUtil;

	@Value("${info.prop.class}")
	private static String Class;

	@Value("${info.prop.subclass}")
	private static String SubClass;

	@Value("${info.prop.sizeLimit}")
	private static Integer SizeLimit;

	private HashMap<String, AttributeStoreTable> materialMap;
	private HashMap<String, AttributeStoreTable> collectionMap;
	private HashMap<String, AttributeStoreTable> subCollectionMap;
	private HashMap<String, AttributeStoreTable> departmentMap;
	private HashMap<String, String> seasonMap;
	private HashMap<String, AttributeStoreTable> styleClassMap;
	private HashMap<String, AttributeStoreTable> subClassMap;

	/**
	 * Method handle the request, validate the all fields, set the value and
	 * create/update/delete the data through JPA, and persist the data, Here based
	 * on all item fields and property file get all mapping first and than check
	 * code table name which is exist or not after than we check is it a attribute
	 * or not based on we update all the below tables.. ATTRIBUTESTORE,
	 * INTRODATEMASTER, DEPTCOLLECTIONSUBCOLLECTION, DEPARTMENTCLASSSUBCLASS,
	 * VARIANTMASTER, PM_FISCALYEAR, PREMIUMFXRATECOSTAGREMENT, PMSMG,
	 * SIZESCALEMASTER, SEASONGROUPMASTER, MATERIALATTRIBUTESTORE tables.
	 */
	
	public CodeTableResponse processCodeTable(CodeTableRequest codeTableRequest){
		logger.info("CodeTableServiceImpl.codeTableRequest Entering with message id "+ codeTableRequest.getMessageID());
		CodeTableResponse response = new CodeTableResponse();
		String messageId = codeTableRequest.getMessageID();
		String appName = codeTableRequest.getApplicationName();
		response.setApplicationName(appName);
		response.setMessageID(messageId);
		String codeTableName = "";
		String message= "";
		try{
			logger.info("CodeTableServiceImpl.processCodeTable Starting to process "+ messageId+ " for codeTable "+ codeTableRequest.getCodeTable().getCodeTableName());
			if(codeTableRequest.getCodeTable() != null&& codeTableRequest.getCodeTable()!= null){
					codeTableName = codeTableRequest.getCodeTable().getCodeTableName();
					message = "Processing For  for: "+ codeTableName;
					response.setResultFlag("SUCCESS");
					if(isRegularAttributeStore(codeTableName)){
						updateAttributeStoreTable( response, codeTableRequest.getCodeTable() ,codeTableRequest);
					}else if(codeTableName.equalsIgnoreCase("PM_CollectionSubCollection")){
						updateDepartmentCollSubCollTable( response, codeTableRequest.getCodeTable(), codeTableRequest);
					}
					else if(codeTableName.equalsIgnoreCase("PM_FISCALYEAR")){
						updateFiscalTable(response, codeTableRequest.getCodeTable());
					}else if(codeTableName.equalsIgnoreCase("PM_Premium_FXRate_CostAgr")){
						updatePreiumFXCostAggTable(response, codeTableRequest.getCodeTable());
					}else if(codeTableName.equalsIgnoreCase("PM_SMG")){
						updatePMSMG(response, codeTableRequest.getCodeTable());
					}else if(codeTableName.equalsIgnoreCase("PM_ClassSubClass")){
						updateDepartmentClassSubClass(response, codeTableRequest.getCodeTable());
					}//
					else if(codeTableName.equalsIgnoreCase("PM_Size_Scale")|| codeTableName.equalsIgnoreCase("Size_Scale")|| codeTableName.equalsIgnoreCase("PM_SizeScale")){
						logger.info("Inside size scale logic  ");
						updatePMSizeScale(response, codeTableRequest.getCodeTable());
					}else if(codeTableName.equalsIgnoreCase("INTRODATE_SEASON_MAPPING")){
						updateIntroDateSeasonmapping(response, codeTableRequest.getCodeTable());
					}else if(isRegularMaterialAttributStore(codeTableName)){
						updateMaterialAttributeStoreTable(response,codeTableRequest.getCodeTable(), codeTableRequest);
					}else if(codeTableName.equalsIgnoreCase("MaterialVariant")){
						updateVariantMasterTable(response,codeTableRequest.getCodeTable());
					}else{
						logger.info(new Date()+" CodeTableServiceImpl.processCodeTable Returning as dont' know what to do with "+codeTableName);
						response.setMessageDetails("Code Table is not implemented yet, sending default failure ");
						response.setResultFlag("FAILED");
					}
				//}
				
			}else{
				//Main code table object in Request is null
				logger.info("CodeTableServiceImpl.processCodeTable No Items retrieved for  "+ messageId);
				response.setMessageDetails("Error no codetables being passed ");
				response.setResultFlag("FAILED");
			}
			
		}catch(Exception ex){
			response.setMessageDetails("Message failed for message ID "+ messageId);
			response.setResultFlag("FAILED");
		}
		logger.info(new Date()+ " The response messaage id "+ messageId);
		response.setMessageID(messageId);
		return response;
		
	}  

	public CodeTableResponse updateAttributeStoreTable(CodeTableResponse response, CodeTable codeTable, CodeTableRequest codeTableRequest){
		logger.info("CodeTableServiceImpl.updateAttributeStoreTable : Entering for codeTable "+ codeTable.getCodeTableName());
		String failedKeys = "";
		String successKeys="";
		
		String codeTableColumns = PropertyReaderUtil.getValueIgnoreCase(codeTable.getCodeTableName());
		logger.info("CodeTableServiceImpl.updateAttributeStoreTable : codeTableColumns "+ codeTableColumns);
		String codeTableName = codeTable.getCodeTableName();
		boolean tableUpdated = false;
		if(FormatHelper.hasContent(codeTableColumns)){
			String []attProperties = codeTableColumns.split(";");
			if(attProperties.length >3){
				logger.info("CodeTableServiceImpl.updateAttributeStoreTable : More that 3 table columnames found");
				String tableName = attProperties[0];
				String attName = attProperties[1];
				String objectName = attProperties[2];
				String objectLevel = attProperties[3];	
				Items items = codeTable.getItems();
				//Items items = codeTableRequest.getCodeTable().getItems();
				if(items != null && items.getItem() != null && items.getItem().length >0){
					logger.info("CodeTableServiceImpl.updateAttributeStoreTable : items list retrieved for "+codeTable.getCodeTableName());
					Item[] itemsArray = items.getItem();
					for(int i =0; i< itemsArray.length; i++){
						Item item = itemsArray[i];
						logger.info("CodeTableServiceImpl.updateAttributeStoreTable : item  retrieved for "+codeTable.getCodeTableName());
						Field[] fieldArray = item.getFields().getField();
						String currentKey ="";
						try{
							HashMap fieldMap = new HashMap();
							for(int j = 0 ;j < fieldArray.length; j++){
								Field field = fieldArray[j];
								fieldMap.put(field.getId().toUpperCase(),field.getValue());
							}
							logger.info("CodeTableServiceImpl.updateAttributeStoreTable : fields retrieved list retrieved for "+codeTable.getCodeTableName()+ fieldMap);
							currentKey= (String)fieldMap.get("CODE");
							String action = (String)fieldMap.get("ACTIONSTATUS");
							String newAction = "";
							String display= (String)fieldMap.get("NAME");
							String dept = null;
							if(fieldMap.containsKey("DEPARTMENT"))
								dept = (String)fieldMap.get("DEPARTMENT");
							AttributeStoreTable attStore = new AttributeStoreTable();
							AttributeStoreTable_PK key = new AttributeStoreTable_PK();
							key.setAttributeName(attName);
							key.setValueKey((String)fieldMap.get("CODE"));
							key.setObjectName(objectName);
							key.setObjectLevel(objectLevel);
							attStore.setDisplay(display);
							
							attStore.setCompKey(key);
							attStore.setDepartment(dept);
							attStore.setCodeTableName(codeTableName);
				
							if (codeTableRequest.getCodeTable().getCodeTableName()
									.equalsIgnoreCase(IBConstants.PM_SEGMENTATIONSILHOUETTE)) {
								attStore.setDepartment((String) fieldMap.get(IBConstants.DEPT));
							}
							if (codeTableRequest.getCodeTable().getCodeTableName().equalsIgnoreCase(IBConstants.PM_Platform)) {
								attStore.setDepartment((String) fieldMap.get(IBConstants.DEPT));
							}
							if(codeTable.getCodeTableName().equalsIgnoreCase("PM_DEPARTMENT")){
								
								 String productType = (String)fieldMap.get("PRODUCTTYPE");
								 String licenced = (String)fieldMap.get("LICENSED");
								 
								 attStore.setProductType(productType);
								 attStore.setLicensed(licenced);
							}
							if(codeTable.getCodeTableName().equalsIgnoreCase("COLLECTION")){
								 String code = (String)fieldMap.get("CODE");
								 String collectionDesc = (String)fieldMap.get("NAME");
								 String department = (String)fieldMap.get("DEPARTMENT");
									System.out.println("Collection codetable........"+ code +"**"+collectionDesc+"***"+department);
								 key.setValueKey(code);
								 attStore.setDisplay(collectionDesc);
								attStore.setDepartment(department);
								 attStore.setCompKey(key);
							}
							if(codeTable.getCodeTableName().equalsIgnoreCase("PM_Class")){
								 String code = (String)fieldMap.get("CODE");
								 String classDesc = (String)fieldMap.get("NAME");
								 String department = (String)fieldMap.get("DEPARTMENT");
									System.out.println("Class codetable   :"+ code +"**"+classDesc+"***"+department);
								 key.setValueKey(code);
								 attStore.setDisplay(classDesc);
								 attStore.setDepartment(department);
								 attStore.setCompKey(key);
								
							}
							if(codeTable.getCodeTableName().equalsIgnoreCase("PM_MATERIAL")){
								String display2 = (String)fieldMap.get("KEY2");
								String display3 = (String)fieldMap.get("MATERIALTYPE");
								String display4 = (String)fieldMap.get("MATERIALTYPEVALUE");
								
								attStore.setDisplay2(display2);
								attStore.setDisplay3(display3);
								attStore.setDisplay4(display4);
							}
							
							AttributeStoreTable existing = null;
							String codeKey =(String)fieldMap.get("CODE");
							if(codeKey.indexOf("'") > -1)
								codeKey=codeKey.replaceAll("'", "''"); 
							//String query = "from com.pmdbcoach.hibernate.skuintegration.AttributeStoreTable where upper(attributeName) = '"+attName.toUpperCase()+"' and  valuekey ='"+codeKey+"' and upper(objectName) = '"+objectName.toUpperCase()+"' and upper(objectlevel) = '"+ objectLevel.toUpperCase()+"'";
							try{
								List<AttributeStoreTable> tableList = new ArrayList<AttributeStoreTable>();
								tableList = attributeStoreTableRepository.findList(attName.toUpperCase(), codeKey, objectName.toUpperCase(), objectLevel.toUpperCase());
								//tableList = PersistanctHelper.Query(query, tableList);
								if(tableList == null || tableList.size() == 0){
									newAction = "Create";
								}
								else {
									newAction = "Update";
									existing = (AttributeStoreTable)tableList.get(0);
								}
							}catch (Exception ex){
								
								if(FormatHelper.hasContent(failedKeys))
									failedKeys = failedKeys+","+failedKeys;
								else
									failedKeys =currentKey;
								continue;
							}
							if(action.equalsIgnoreCase("DELETE") && existing != null){
								try{
									attributeStoreTableRepository.delete(existing);
									//PersistanctHelper.deleteRow(existing);
									if(FormatHelper.hasContent(successKeys))
										successKeys = successKeys+ ","+currentKey;
									else
										successKeys = currentKey;
									tableUpdated= true;
								}catch(Exception ex){
									logger.info("Data not deleted properly in Attribute Store Table" + ex.getMessage());
									logger.error(ex.getMessage(), ex);
									if(FormatHelper.hasContent(failedKeys))
										failedKeys = failedKeys+","+failedKeys;
									else
										failedKeys =currentKey;
								}
								continue;
							}
							logger.info("CodeTableServiceImpl.updateAttributeStoreTable : "+ codeTable.getCodeTableName()+ " attname "+ attStore.getCompKey().getAttributeName()+ " key "+ attStore.getCompKey().getValueKey()+ " display "+ attStore.getDisplay());
							int indexOfHyphen = display.indexOf("-");
							//logger.info("index of hyphen "+indexOfHyphen);
							if(indexOfHyphen > -1 && !codeTable.getCodeTableName().equalsIgnoreCase("PM_Material")){
								String display2 = display.substring((indexOfHyphen+1), display.length());
								attStore.setDisplay2(display2.trim());
								logger.info("CodeTableServiceImpl.updateAttributeStoreTable : "+ codeTable.getCodeTableName()+ " attname "+ attStore.getCompKey().getAttributeName()+ " key "+ attStore.getCompKey().getValueKey()+ " display "+ attStore.getDisplay()+ " display2 "+ display2);
							}
							
							attStore.setStatus((String)fieldMap.get("STATUS"));
							if(newAction.equalsIgnoreCase("CREATE")){
								logger.info("CodeTableServiceImpl.updateClassTable : creating new row ");
								try{
									attributeStoreTableRepository.saveAndFlush(attStore);
									//PersistanctHelper.createRow(attStore);
									if(FormatHelper.hasContent(successKeys))
										successKeys = successKeys+ ","+currentKey;
									else
										successKeys = currentKey;
									logger.info("Insert Success for "+ currentKey); 
									if("PM_INTRODATE".equalsIgnoreCase(codeTableRequest.getCodeTable().getCodeTableName())){
									//if("PM_INTRODATE".equalsIgnoreCase(codeTableName)){
										 updateIntroDateMaster(attStore.getDisplay());
									 }
									tableUpdated=true;
								}catch(Exception ex){
									ex.printStackTrace();
									if(FormatHelper.hasContent(failedKeys))
										failedKeys = failedKeys+","+failedKeys;
									else
										failedKeys =currentKey;
								}
								
							}else if(newAction.equalsIgnoreCase("UPDATE")){
								//  public  String prepareUpdateSQLForAttributeStore(Object bean, String updateString,String attributeName, String key, String objectName) {
								logger.info("CodeTableServiceImpl.updateAttributeStoreTable : updating existing row "+ codeTableName);
								try{
									String deptOld =FormatHelper.format(existing.getDepartment());
									String deptNew = FormatHelper.format(attStore.getDepartment());
									String valueKey = attStore.getCompKey().getValueKey();
									attStore.setDepartment(deptNew);
									//PersistanctHelper.updateRow(attStore);
									attributeStoreTableRepository.save(attStore);

									
									if(FormatHelper.hasContent(successKeys))
										successKeys = successKeys+ ","+currentKey;
									else
										successKeys = currentKey;
									logger.info("Insert Success for "+ currentKey); 
									if("PM_INTRODATE".equalsIgnoreCase(codeTableName)){
										 updateIntroDateMaster(attStore.getDisplay());
									 }
									if("PM_Class".equalsIgnoreCase(codeTableName)||"Collection".equalsIgnoreCase(codeTableName)){
										logger.info("CodeTableServiceImpl.updateAttributeStoreTable : updating existing row deptOld "+ deptOld+" new department "+ deptNew);
										if(!deptNew.equals(deptOld)){
											try{
												logger.info("CodeTableServiceImpl.updateAttributeStoreTable : updating existing row new and old department are not same ");
												updateMappingTable(deptOld,deptNew,codeTableName,currentKey);
											}catch(Exception ex){
												attributeStoreTableRepository.saveAndFlush(existing);
												//PersistanctHelper.updateRow(existing);
												failedKeys =currentKey+" error in removing the heirarchy in mapping tables";
												throw ex;
												
											}
										}else
										{
											logger.info("CodeTableServiceImpl.updateAttributeStoreTable : departments are same ");
										}
									}
									
									tableUpdated = true;
								}catch(Exception ex){
									ex.printStackTrace();
									if(FormatHelper.hasContent(failedKeys))
										failedKeys = failedKeys+","+failedKeys;
									else
										failedKeys =currentKey;
								}
							}
						
						}catch(Exception ex){
							ex.printStackTrace();
							logger.info("Not update Attribute store table successfully due to insufficent or wrong data" + ex);
							logger.error(ex.getMessage(), ex);
							logger.info("Got error in saving class attribute ");
							failedKeys = failedKeys+" "+currentKey; 
						}
					}
					
				}else{
					response.setResultFlag("FAILED");
					response.setMessageDetails("Codetable :"+ codeTable.getCodeTableName()+ " not processed successfully: Details missing in Property File ");
					return response;
				}
				
			}else{
				response.setResultFlag("FAILED");
				response.setMessageDetails("Codetable :"+ codeTable.getCodeTableName()+ " not processed successfully: Details missing in Property File ");
				return response;
			}
		}else{
			response.setResultFlag("FAILED");
			response.setMessageDetails("Codetable :"+ codeTable.getCodeTableName()+ " not processed successfully: Entry for details for codeTable is  missing in Property File ");
			return response;
		}
		if(FormatHelper.hasContent(failedKeys)){
			response.setResultFlag("FAILED");
			response.setMessageDetails("Codetable :"+ codeTable.getCodeTableName()+ " was not fully processed successfully, FAILEDKEYS "+ failedKeys);
		}else
			response.setMessageDetails("Codetable :"+ codeTable.getCodeTableName()+ " was processed successfully");
		logger.info("Update AttriubteStore table the messageiD "+ response.getMessageID());
		if(tableUpdated){
			try{
		        if("PM_DEPARTMENT".equalsIgnoreCase(codeTableName)){
		        	refreshDepartmentMap();
		        }
		        else if("COLLECTION".equalsIgnoreCase(codeTableName)){
		        	refreshCollectionMap();
		        }
		        else if("PM_SUBCOLLECTION".equalsIgnoreCase(codeTableName)){
		        	refreshSubCollectionMap();
		        }else if("PM_MATERIAL".equalsIgnoreCase(codeTableName)){
		        	refreshMaterialMap();
		        }else if("MMSEASON".equalsIgnoreCase(codeTableName)){
		        	refreshSeasonMap();
		        }else if("PM_CLASS".equalsIgnoreCase(codeTableName)){
		        	refreshStyleClassMap();
		        }
		        else if("PM_SUBCLASS".equalsIgnoreCase(codeTableName)){
		        	refreshSubClassMap();
		        }
			 
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		return response;	
	}
	
	/**
	 * Refreshed Material Map to find the key and value from ATTRIBUTESTORE and put
	 * into map.
	 */
	public HashMap<String, AttributeStoreTable> refreshMaterialMap() {
		materialMap = new HashMap<String, AttributeStoreTable>();
		List<AttributeStoreTable> tableList = new ArrayList<AttributeStoreTable>();
		try {
			tableList = attributeStoreTableRepository.findAllMaterial(tableList);
		} catch (Exception ex) {
			logger.info("Not refreshed Material Map" + ex.getMessage());
			logger.error(ex.getMessage(), ex);
		}
		if (tableList == null || tableList.size() == 0) {
			return materialMap;
		} else {
			Iterator<AttributeStoreTable> iter = tableList.iterator();
			AttributeStoreTable tbl;
			String key;
			String value;
			while (iter.hasNext()) {
				tbl = iter.next();
				key = tbl.getCompKey().getValueKey();
				value = tbl.getDisplay();
				if (FormatHelper.hasContent(key) && FormatHelper.hasContent(value))
					materialMap.put(key.trim(), tbl);
			}
		}
		return materialMap;
	}
	
	public HashMap<String, AttributeStoreTable> refreshDepartmentMap() {
		departmentMap = new HashMap<String, AttributeStoreTable>();
		List<AttributeStoreTable> tableList = new ArrayList<AttributeStoreTable>();
		try {
			tableList = attributeStoreTableRepository.findAllDepartment(tableList);
		} catch (Exception ex) {
			logger.info("Not refreshed Department Map" + ex.getMessage());
			logger.error(ex.getMessage(), ex);
		}
		if (tableList == null || tableList.size() == 0) {
			return departmentMap;
		} else {
			Iterator<AttributeStoreTable> iter = tableList.iterator();
			AttributeStoreTable tbl;
			String key;
			String value;
			while (iter.hasNext()) {
				tbl = iter.next();
				key = tbl.getCompKey().getValueKey();
				value = tbl.getDisplay();
				if (FormatHelper.hasContent(key) && FormatHelper.hasContent(value))
					departmentMap.put(key.trim(), tbl);
			}
		}
		return departmentMap;
	}
	/**
	 * Refreshed Collection Map to find the key and value from ATTRIBUTESTORE and
	 * put into map.
	 */
	public HashMap<String, AttributeStoreTable> refreshCollectionMap() {
		collectionMap = new HashMap<String, AttributeStoreTable>();
		List<AttributeStoreTable> tableList = new ArrayList<AttributeStoreTable>();
		try {
			tableList = attributeStoreTableRepository.findAllCollection(tableList);
		} catch (Exception ex) {
			logger.info("Not refreshed Collection Map" + ex.getMessage());
			logger.error(ex.getMessage(), ex);
		}
		if (tableList == null || tableList.size() == 0) {
			return collectionMap;
		} else {
			Iterator<AttributeStoreTable> iter = tableList.iterator();
			AttributeStoreTable tbl;
			String key;
			String value;
			while (iter.hasNext()) {
				tbl = iter.next();
				key = tbl.getCompKey().getValueKey();
				value = tbl.getDisplay();
				if (FormatHelper.hasContent(key) && FormatHelper.hasContent(value))
					collectionMap.put(key.trim(), tbl);
			}
		}
		return collectionMap;
	}

	/**
	 * Get refreshed Collection Map.
	 */
	public HashMap<String, AttributeStoreTable> getCollectionMap() {
		if (collectionMap == null || collectionMap.size() < 1) {
			refreshCollectionMap();
		}
		return collectionMap;
	}

	/**
	 * Refreshed Class Map to find the key and value from ATTRIBUTESTORE and put
	 * into map.
	 */
	public HashMap<String, AttributeStoreTable> refreshStyleClassMap() {
		styleClassMap = new HashMap<String, AttributeStoreTable>();
		List<AttributeStoreTable> tableList = new ArrayList<AttributeStoreTable>();
		try {
			tableList = attributeStoreTableRepository.findAllStyleClass(tableList);
		} catch (Exception ex) {
			logger.info("Not refreshed Style Class Map" + ex.getMessage());
			logger.error(ex.getMessage(), ex);
		}
		if (tableList == null || tableList.size() == 0) {
			return styleClassMap;
		} else {
			Iterator<AttributeStoreTable> iter = tableList.iterator();
			AttributeStoreTable tbl;
			String key;
			String value;
			while (iter.hasNext()) {
				tbl = iter.next();
				key = tbl.getCompKey().getValueKey();
				value = tbl.getDisplay();
				if (FormatHelper.hasContent(key) && FormatHelper.hasContent(value))
					styleClassMap.put(key.trim(), tbl);
			}
		}
		return styleClassMap;
	}

	/**
	 * Get refreshed Class Map.
	 */
	public HashMap<String, AttributeStoreTable> getstyleClassMap() {
		if (styleClassMap == null || styleClassMap.size() < 1) {
			refreshStyleClassMap();
		}
		return styleClassMap;
	}

	/**
	 * Refreshed Subclass Map to find the key and value from ATTRIBUTESTORE and put
	 * into map.
	 */
	public HashMap<String, AttributeStoreTable> refreshSubClassMap() {
		subClassMap = new HashMap<String, AttributeStoreTable>();
		List<AttributeStoreTable> tableList = new ArrayList<AttributeStoreTable>();
		try {
			tableList = attributeStoreTableRepository.findAllSubClass(tableList);
		} catch (Exception ex) {
			logger.info("Not refreshed Sub Class Map" + ex.getMessage());
			logger.error(ex.getMessage(), ex);
		}
		if (tableList == null || tableList.size() == 0) {
			return subClassMap;
		} else {
			Iterator<AttributeStoreTable> iter = tableList.iterator();
			AttributeStoreTable tbl;
			String key;
			String value;
			while (iter.hasNext()) {
				tbl = iter.next();
				key = tbl.getCompKey().getValueKey();
				value = tbl.getDisplay();
				if (FormatHelper.hasContent(key) && FormatHelper.hasContent(value))
					subClassMap.put(key.trim(), tbl);
			}
		}
		return subClassMap;
	}

	/**
	 * Get refreshed Subclass Map.
	 */
	public HashMap<String, AttributeStoreTable> getSubClassMap() {
		if (subClassMap == null || subClassMap.size() < 1) {
			refreshSubClassMap();
		}
		return subClassMap;
	}

	/**
	 * Set refreshed Collection Map.
	 */
	public void setCollectionMap(HashMap<String, AttributeStoreTable> collectionMap) {
		this.collectionMap = collectionMap;
	}

	/**
	 * Refreshed Sub class collection Map to find the key and value from
	 * ATTRIBUTESTORE and put into map.
	 */
	public HashMap<String, AttributeStoreTable> refreshSubCollectionMap() {
		subCollectionMap = new HashMap<String, AttributeStoreTable>();
		List<AttributeStoreTable> tableList = new ArrayList<AttributeStoreTable>();
		try {
			tableList = attributeStoreTableRepository.findAllRefreshSubCollection(tableList);
		} catch (Exception ex) {
			logger.info("Not refreshed Sub Collection Map" + ex.getMessage());
			logger.error(ex.getMessage(), ex);
		}
		if (tableList == null || tableList.size() == 0) {
			return subCollectionMap;
		} else {
			Iterator<AttributeStoreTable> iter = tableList.iterator();
			AttributeStoreTable tbl;
			String key;
			String value;
			while (iter.hasNext()) {
				tbl = iter.next();
				key = tbl.getCompKey().getValueKey();
				value = tbl.getDisplay();
				if (FormatHelper.hasContent(key) && FormatHelper.hasContent(value))
					subCollectionMap.put(key.trim(), tbl);
			}
		}
		return subCollectionMap;
	}

	/**
	 * Get refreshed Sub class collection Map.
	 */
	public HashMap<String, AttributeStoreTable> getSubCollectionMap() {
		if (subCollectionMap == null || subCollectionMap.size() < 1) {
			refreshSubCollectionMap();
		}
		return subCollectionMap;
	}

	/**
	 * Set refreshed Sub class collection Map.
	 */
	public void setSubCollectionMap(HashMap<String, AttributeStoreTable> subCollectionMap) {
		this.subCollectionMap = subCollectionMap;
	}

	/**
	 * Get refreshed Season Map.
	 */
	public HashMap<String, String> getSeasonMap() {
		if (seasonMap == null || seasonMap.size() < 1) {
			refreshSeasonMap();
		}
		return seasonMap;
	}

	/**
	 * Set refreshed Season Map.
	 */
	public void setSeasonMap(HashMap<String, String> seasonMap) {
		this.seasonMap = seasonMap;
	}

	/**
	 * Refreshed Season Map to find the key and value from ATTRIBUTESTORE and put
	 * into map.
	 */
	public HashMap<String, String> refreshSeasonMap() {
		seasonMap = new HashMap<String, String>();
		List<AttributeStoreTable> tableList = new ArrayList<AttributeStoreTable>();
		try {
			tableList = attributeStoreTableRepository.findAllSeason(tableList);
		} catch (Exception ex) {
			logger.info("Not refreshed Season Map" + ex.getMessage());
			logger.error(ex.getMessage(), ex);
		}
		if (tableList == null || tableList.size() == 0) {
			return seasonMap;
		} else {
			Iterator<AttributeStoreTable> iter = tableList.iterator();
			AttributeStoreTable tbl;
			String key;
			String value;
			while (iter.hasNext()) {
				tbl = iter.next();
				key = tbl.getCompKey().getValueKey();
				value = tbl.getDisplay();
				if (FormatHelper.hasContent(key) && FormatHelper.hasContent(value))
					seasonMap.put(key.trim(), value);
			}
		}
		return seasonMap;
	}

	public CodeTableResponse updateMaterialAttributeStoreTable(CodeTableResponse response, CodeTable codeTable , CodeTableRequest codeTableRequest){
		//Class=ATTRIBUTESTORE;STYLECLASS;PRODUCT;PRODUCT
		logger.info("CodeTableServiceImpl.updateMaterialAttributeStoreTable : Entering for codeTable "+ codeTable.getCodeTableName());
		String failedKeys = "";
		String successKeys="";
		
		String codeTableColumns = PropertyReaderUtil.getValueIgnoreCase(codeTable.getCodeTableName());
		logger.info("CodeTableServiceImpl.updateMaterialAttributeStoreTable : codeTableColumns "+ codeTableColumns);
		String codeTableName = codeTable.getCodeTableName();
		if(FormatHelper.hasContent(codeTableColumns)){
			String []attProperties = codeTableColumns.split(";");
			if(attProperties.length >1){
				logger.info("CodeTableServiceImpl.updateMaterialAttributeStoreTable : More that 3 table columnames found");
				String tableName = attProperties[0];
				String attName = attProperties[1];
				Items items = codeTable.getItems();
				HashMap fieldMap = new HashMap();
				if(items != null && items.getItem().length >0){
					logger.info("CodeTableServiceImpl.updateMaterialAttributeStoreTable : items list retrieved for "+codeTable.getCodeTableName());
					Item[] itemsArray = items.getItem();
					for(int i =0; i< itemsArray.length; i++){
						Item item = itemsArray[i];
						logger.info("CodeTableServiceImpl.updateMaterialAttributeStoreTable : item  retrieved for "+codeTable.getCodeTableName());
						Field[] fieldArray = item.getFields().getField();
						String currentKey ="";
						try{
							fieldMap.clear();
							for(int j = 0 ;j < fieldArray.length; j++){
								Field field = fieldArray[j];
								fieldMap.put(field.getId().toUpperCase(),field.getValue());
							}
							logger.info("CodeTableServiceImpl.updateMaterialAttributeStoreTable : fields retrieved list retrieved for "+codeTable.getCodeTableName()+ fieldMap);
							currentKey= (String)fieldMap.get("CODE");
							String action = (String)fieldMap.get("ACTIONSTATUS");
							String newAction = "";
							String display= (String)fieldMap.get("NAME");
							String dept = null;
							
							MaterialAttributeStore attStore = new MaterialAttributeStore();
							MaterialAttributeStore_PK key = new MaterialAttributeStore_PK();
							key.setAttributeName(attName);
							String valueKey="";
							if("MATERIALSUBTYPE".equalsIgnoreCase(codeTableName)||"SurfaceFinish".equalsIgnoreCase(codeTableName)){
								key.setValueKey((String)fieldMap.get("KEY"));
								currentKey = (String)fieldMap.get("KEY");
							}
							else
								key.setValueKey((String)fieldMap.get("CODE"));
							valueKey= key.getValueKey();
							if(!FormatHelper.hasContent(valueKey)||!FormatHelper.hasContent(display)){
								if(FormatHelper.hasContent(valueKey))
									failedKeys= failedKeys+valueKey;
								else
									failedKeys= failedKeys+" MissingKey for field";
								continue;
							}
							attStore.setId(key);
							attStore.setDisplay(display);
							String materialType ="MATERIAL";
							if(fieldMap.containsKey("MaterialType") && FormatHelper.hasContent((String)fieldMap.get("MaterialType"))){
								key.setMaterialType(FormatHelper.format((String)fieldMap.get("MaterialType")));
								materialType = (String)fieldMap.get("MaterialType");
							}else if(fieldMap.containsKey("MATERIALTYPE") && FormatHelper.hasContent((String)fieldMap.get("MATERIALTYPE"))){
								key.setMaterialType(FormatHelper.format((String)fieldMap.get("MATERIALTYPE")));
								materialType=FormatHelper.format((String)fieldMap.get("MATERIALTYPE"));
							}
							else
								key.setMaterialType("MATERIAL");
							if(fieldMap.containsKey("STANDARDUNIT"))
								attStore.setStandardUnit((String)fieldMap.get("STANDARDUNIT"));
							if(fieldMap.containsKey("EXOTICCOMMONNAME"))
								attStore.setExotiCommonname((String)fieldMap.get("EXOTICCOMMONNAME"));
							if(fieldMap.containsKey("COH_APPROVE"))
								attStore.setCoh_approve((String)fieldMap.get("COH_APPROVE"));
							if(fieldMap.containsKey("DESCRIPTION"))
								attStore.setDescription((String)fieldMap.get("DESCRIPTION"));
							if(fieldMap.containsKey("SCIENTIFICNAME"))
								attStore.setScientificName((String)fieldMap.get("SCIENTIFICNAME"));
							if(fieldMap.containsKey("Rate"))
								attStore.setRate((String)fieldMap.get("Rate"));
							if(fieldMap.containsKey("CONTRACTTYPE"))
								attStore.setContractType((String)fieldMap.get("CONTRACTTYPE"));
							if(fieldMap.containsKey("MASTERTYPEID"))
								attStore.setMasterTypeID((String)fieldMap.get("MASTERTYPEID"));
							if(fieldMap.containsKey("SUPPLIERCAPACITYTYPE"))
								attStore.setSupplierCapacityType((String)fieldMap.get("SUPPLIERCAPACITYTYPE"));
							if(fieldMap.containsKey("UOM"))
								attStore.setSupplierCapacityType((String)fieldMap.get("SUPPLIERCAPACITYTYPE"));
							if(fieldMap.containsKey("UOM"))
								attStore.setUom((String)fieldMap.get("UOM"));
							if(fieldMap.containsKey("UOMDESC"))
								attStore.setUomDesc((String)fieldMap.get("UOMDESC"));
							
							
							attStore.setCodeTableName(codeTableName);
							MaterialAttributeStore existing = null;
							//String query = "from com.pmdbcoach.hibernate.skuintegration.MaterialAttributeStore where upper(attributeName) = '"+attName.toUpperCase()+"' and  valuekey ='"+currentKey+"' and upper(MATERIALTYPE) = '"+materialType.toUpperCase()+"'" ;//and upper(objectlevel) = '"+ objectLevel.toUpperCase()+"'";
							try{
								List<MaterialAttributeStore> tableList = new ArrayList<MaterialAttributeStore>();
								tableList = materialAttributeRepository.findAll(attName.toUpperCase(), currentKey, materialType.toUpperCase());
								//tableList = PersistanctHelper.Query(query, tableList);
								if(tableList == null || tableList.size() == 0){
									newAction = "Create";
								}
								else {
									newAction = "Update";
									existing = (MaterialAttributeStore)tableList.get(0);
								}
							}catch (Exception ex){
								
								if(FormatHelper.hasContent(failedKeys))
									failedKeys = failedKeys+","+failedKeys;
								else
									failedKeys =currentKey;
								continue;
							}
							if(action.equalsIgnoreCase("DELETE") && existing != null){
								try{
									materialAttributeRepository.delete(existing);
									if(FormatHelper.hasContent(successKeys))
										successKeys = successKeys+ ","+currentKey;
									else
										successKeys = currentKey;
								}catch(Exception ex){
									logger.info("Data not deleted properly in Material Attribute Store Table" + ex.getMessage());
									logger.error(ex.getMessage(), ex);
									if(FormatHelper.hasContent(failedKeys))
										failedKeys = failedKeys+","+failedKeys;
									else
										failedKeys =currentKey;
								}
								continue;
							}
							logger.info("CodeTableServiceImpl.updateMaterialAttributeStoreTable : "+ codeTable.getCodeTableName()+ " attname "+ attStore.getId().getAttributeName()+ " key "+ attStore.getId().getValueKey()+ " display "+ attStore.getDisplay());

							logger.info("CodeTableServiceImpl.updateMaterialAttributeStoreTable : "+ codeTable.getCodeTableName()+ " attname "+ attStore.getId().getAttributeName()+ " key "+ attStore.getId().getValueKey()+ " display "+ attStore.getDisplay());
							
							attStore.setStatus((String)fieldMap.get("STATUS"));
							if(newAction.equalsIgnoreCase("CREATE")){
								logger.info("CodeTableServiceImpl.updateMaterialAttributeStoreTable : creating new row ");
								try{
									materialAttributeRepository.saveAndFlush(attStore);
									//PersistanctHelper.createRow(attStore);
									if(FormatHelper.hasContent(successKeys))
										successKeys = successKeys+ ","+currentKey;
									else
										successKeys = currentKey;
									logger.info("Insert Success for "+ currentKey); ;
									
								}catch(Exception ex){
									logger.info("Row not created successfully in Material Attribute Store Table" + ex.getMessage());
									ex.printStackTrace();
									if(FormatHelper.hasContent(failedKeys))
										failedKeys = failedKeys+","+failedKeys;
									else
										failedKeys =currentKey;
								}
								
							}else if(newAction.equalsIgnoreCase("UPDATE")){
							logger.info("CodeTableServiceImpl.updateMaterialAttributeStoreTable : updating existing row ");
								try{
									materialAttributeRepository.save(attStore);
									//PersistanctHelper.updateRow(attStore);
									if(FormatHelper.hasContent(successKeys))
										successKeys = successKeys+ ","+currentKey;
									else
										successKeys = currentKey;
									logger.info("Update Success for "+ currentKey); ;
									
								}catch(Exception ex){
									logger.info("Data not update successfully in Material Attribute Store Table" + ex.getMessage());
									ex.printStackTrace();
									if(FormatHelper.hasContent(failedKeys))
										failedKeys = failedKeys+","+failedKeys;
									else
										failedKeys =currentKey;
								}
							}
						
						}catch(Exception ex){
							ex.printStackTrace();
							logger.info("Got error in saving class attribute ");
							failedKeys = failedKeys+" "+currentKey; 
						}
					}
					
				}else{
					response.setResultFlag("FAILED");
					response.setMessageDetails("Codetable :"+ codeTable.getCodeTableName()+ " Message is missing items array, nothing to Process ");
					return response;
				}
				
			}else{
				response.setResultFlag("FAILED");
				response.setMessageDetails("Codetable :"+ codeTable.getCodeTableName()+ " Invalid entry in Property file for this code table, unableToProcess");
				return response;
			}
		}else{
			response.setResultFlag("FAILED");
			response.setMessageDetails("Codetable :"+ codeTable.getCodeTableName()+ " Missing or Invalid entry in Property file for this code table, unableToProcess");
			return response;
		}
		if(FormatHelper.hasContent(failedKeys)){
			response.setResultFlag("FAILED");
			response.setMessageDetails("Codetable :"+ codeTable.getCodeTableName()+ "failed to process fully, FAILEDKEYS "+ failedKeys);
		}else{
			response.setResultFlag("SUCCESS");
			response.setMessageDetails("Codetable :"+ codeTable.getCodeTableName()+ ": Processed Successfully");
		}
			
		logger.info("Update Class table the messageiD "+ response.getMessageID());
		return response;
		
	}
	/**
	 * Here based on code table name and attribute table values store the data with
	 * create/update/delete query to DEPTCOLLECTIONSUBCOLLECTION table.
	 */
	
	public CodeTableResponse updateDepartmentCollSubCollTable(CodeTableResponse response, CodeTable codeTable , CodeTableRequest codeTableRequest){
		logger.info("CodeTableServiceImpl.artmentCollSubCollTable : Entering for codeTable "+ codeTable.getCodeTableName());
		String failedKeys = "";
		String successKeys="";
		boolean processedAtleasOne = false;
		
		try{
			String codeTableName = codeTable.getCodeTableName();
			Items items = codeTable.getItems();
			if(items != null && items.getItem() != null && items.getItem().length>0){
				logger.info("CodeTableServiceImpl.artmentCollSubCollTable : Item array is not null and has array length greater than 0 ");
				Item[] mappingItems = items.getItem();
				for(int index = 0; index < mappingItems.length; index++){
					logger.info("CodeTableServiceImpl.artmentCollSubCollTable : Getting the item at index "+index);
					Item mappingItem = mappingItems[index];
					logger.info("CodeTableServiceImpl.artmentCollSubCollTable : Getting the item at mappingItem "+mappingItem);
					Fields fields = mappingItem.getFields();
					logger.info("CodeTableServiceImpl.artmentCollSubCollTable : Getting the item at mappingItem "+mappingItem);
					if(fields != null && fields.getField().length >0){
						logger.info("CodeTableServiceImpl.artmentCollSubCollTable : Field in Itemm array is not null ");
						Field[] allfields = fields.getField();
						HashMap <String,String>fieldMap = convertFieldsArrayIntoMap(allfields);
						if(fieldMap != null && fieldMap.size()>0)
						{
							try
							{
								 String attrDepartments="";
								 List attrdepartmentLists = new ArrayList();
								 String uniqueID = (String)fieldMap.get("CODE");
								 String oid = (String)fieldMap.get("OID");
								 String department = (String)fieldMap.get("DEPARTMENT");
								 String departmentDesc;
								 String collection = (String)fieldMap.get("COLLECTION");
								 String collectionDesc = (String)fieldMap.get("COLLECTIONNAME");
								 String subcollection = (String)fieldMap.get("SUBCOLLECTION");
								 String subcollectionDesc = (String)fieldMap.get("NAME");
								 String status = (String)fieldMap.get("STATUS");
								 String actionStatus = (String)fieldMap.get("ACTIONSTATUS");
								 AttributeStoreTable attrCollectionExisting = new AttributeStoreTable() ;
								 AttributeStoreTable attrSubCollectionExisting = new AttributeStoreTable() ;
								 attrCollectionExisting= getCollectionMap().get(collection);
								 attrSubCollectionExisting= getSubCollectionMap().get(subcollection);
								 if(!FormatHelper.hasContent(collectionDesc) ||  !FormatHelper.hasContent(subcollectionDesc)||
										 !FormatHelper.hasContent(collection)|| !FormatHelper.hasContent(subcollection))
								 {
									 logger.info("CodeTableServiceImpl.artmentCollSubCollTable One of the fiels doesn't have a value");
									 failedKeys = uniqueID+":Missing RequiredFields Please check COLLECTION,COLLECTIONNAME,SUBCOLLECTION,NAME fields ,";
									 
								 }else if(attrCollectionExisting==null || !collectionDesc.equalsIgnoreCase(attrCollectionExisting.getDisplay())){
									 logger.info("CodeTableServiceImpl.artmentCollSubCollTable Collection " + collectionDesc+ " unavailable in AttributeStore for collection "+ collection);
									 failedKeys = uniqueID+": Collection " + collectionDesc+ " unavailable in AttributeStore "+" for collection "+ collection;
								 }
								 else if(attrSubCollectionExisting==null || !subcollectionDesc.equalsIgnoreCase(attrSubCollectionExisting.getDisplay())){
									 logger.info("CodeTableServiceImpl.artmentCollSubCollTable Sub collection unavailable in AttributeStore");
									 failedKeys = uniqueID+": SubCollection " + subcollectionDesc+ " unavailable in AttributeStore "+" for collection "+ subcollectionDesc;
								 }else if(attrCollectionExisting.getDepartment() ==null || !FormatHelper.hasContent(attrCollectionExisting.getDepartment())){
									 logger.info("CodeTableServiceImpl.artmentCollSubCollTable Sub collection unavailable in AttributeStore");
									 failedKeys = uniqueID+ ": No mapping availabe between Collection and Department ";
								 }
								 else
								 {
									 logger.info("Department : "+attrCollectionExisting.getDepartment());
									 attrDepartments= attrCollectionExisting.getDepartment();
									 if(attrDepartments!=null){
										 StringTokenizer tokenizer = new StringTokenizer(attrDepartments,";");
										 while(tokenizer.hasMoreTokens()){
											String element = (String) tokenizer.nextElement();
											if(element!=null){
												if(!attrdepartmentLists.contains(element)){
													attrdepartmentLists.add(element);
												}
											}
										}
										logger.info("Department from AttributeStore : "+attrdepartmentLists);
									 }
									 HashMap departmentMap = getDepartmentMap();
									 logger.info("CodeTableServiceImpl.artmentCollSubCollTable got the departmentmap "+ departmentMap);
									 for(int d=0;d<attrdepartmentLists.size();d++){
										 String newDepartment=(String) attrdepartmentLists.get(d);
										 logger.info("Department is to be mapped : "+newDepartment);
										 AttributeStoreTable tbl  = (AttributeStoreTable)departmentMap.get(newDepartment);
										 if(tbl == null){
											 logger.info("CodeTableServiceImpl.artmentCollSubCollTable got the departmentmap "+ newDepartment+"  doesn't exist in PMDB");
											 failedKeys = uniqueID+":Department key "+newDepartment+" doesn exist in PMDB,";
											 
										 }else
										 {
											 logger.info("CodeTableServiceImpl.artmentCollSubCollTable got the departmentmap "+ newDepartment+" in PMDB display : "+tbl.getDisplay2());
											 departmentDesc  = tbl.getDisplay2();
											 if(collectionDesc.indexOf("-")> 1)
												 collectionDesc = collectionDesc.substring(collectionDesc.indexOf("-" )+1, collectionDesc.length());
											 if(subcollectionDesc.indexOf("-")> 1)
												 subcollectionDesc = subcollectionDesc.substring(subcollectionDesc.indexOf("-" )+1, subcollectionDesc.length());
											 DeptCollectionSubCollection deptCollSubCollVO  = new DeptCollectionSubCollection();
											 DeptCollectionSubCollection_PK deptCollSubCollVO_PK = new DeptCollectionSubCollection_PK();
											 deptCollSubCollVO_PK.setCode(uniqueID);
											 deptCollSubCollVO_PK.setDepartment(newDepartment);
											 deptCollSubCollVO.setCollection(collection);
											 deptCollSubCollVO.setActionStatus(actionStatus);
											 deptCollSubCollVO.setCompKey(deptCollSubCollVO_PK);
											 deptCollSubCollVO.setSubcollection(subcollection);
											 deptCollSubCollVO.setCollectionDesc(collectionDesc);
											 deptCollSubCollVO.setSubcollectionDesc(subcollectionDesc);
											 deptCollSubCollVO.setDepartmentDesc(departmentDesc);
											 deptCollSubCollVO.setOid(oid);
											 deptCollSubCollVO.setCreateDate(new Date());
											 deptCollSubCollVO.setLastUpdateDate(new Date());
											 deptCollSubCollVO.setStatus(status);
											 logger.info("CodeTableServiceImpl.artmentCollSubCollTable new object "+ deptCollSubCollVO.toString());
												List<DeptCollectionSubCollection> lst = null;

											 DeptCollectionSubCollection existing = new DeptCollectionSubCollection() ;
											 lst = deptSubCollectionRepository.findAllupdate(uniqueID, newDepartment);
												if (lst != null && !lst.isEmpty()) {
													existing = lst.get(0);
												} else {
													logger.info("Record does not exist in Audit table");
												}
											 //existing = (DeptCollectionSubCollection) new PMDBObjectQuery().searchDepartmentCollSubCollMapping(uniqueID,newDepartment);
											 if("Create".equalsIgnoreCase(actionStatus)||"Update".equalsIgnoreCase(actionStatus)){
												 if(existing == null){
													 deptSubCollectionRepository.saveAndFlush(deptCollSubCollVO);
													 //PersistanctHelper.createRow(deptCollSubCollVO);
													 logger.info("CodeTableServiceImpl.artmentCollSubCollTable Create the  new object "+ deptCollSubCollVO.toString());
												 }
												 else{
													 logger.info("CodeTableServiceImpl.artmentCollSubCollTable existing department : "+existing.getCompKey().getDepartment() + " Collection :"+existing.getCollection());
													 
													 //PersistanctHelper.updateRow(deptCollSubCollVO);
													 logger.info("CodeTableServiceImpl.artmentCollSubCollTable updating the existing object "+ deptCollSubCollVO.toString());
												 }
												 
											 }else if("Delete".equalsIgnoreCase(actionStatus)){
												 if(existing != null){
													 deptSubCollectionRepository.delete(existing);
													 //PersistanctHelper.deleteRow(existing);
													 logger.info("CodeTableServiceImpl.artmentCollSubCollTable Deleting the existing object "+ deptCollSubCollVO.toString());
												 }else{
													 logger.info("CodeTableServiceImpl.artmentCollSubCollTable no existing object found so nothing to delete "+ deptCollSubCollVO.toString());
												 }
											 }
											 successKeys= successKeys+uniqueID+",";
											 processedAtleasOne= true;
										 }
									 }
								 }
							}catch(Exception ex){
								ex.printStackTrace();
								logger.info("DeptCollectionSubcollectionmapping: Error while saving one of the items ");
								failedKeys = fieldMap.get("CODE")+":"+ex.getLocalizedMessage()+",";
							}
						}else{
							failedKeys = "No values found for atleast One Item";
						}
					}else{
						failedKeys = "No values found for atleast One Item";
					}
					
				}	
			}else{
				logger.info("DeptCollectionSubcollectionmapping: no values read" );
				response.setResultFlag("FAILED");
				response.setMessageDetails(" Code Table "+ codeTableName+" was not updated for messagID "+ response.getMessageID()+ " as no values were retrieved in request");
				logger.info("DeptCollectionSubcollectionmapping: Returning response for message id "+ response.getMessageID()+ " "+ response.getMessageDetails());
				return response;
			}
		}catch(Exception ex){
			ex.printStackTrace();
			String error = "";
			if(FormatHelper.hasContent(ex.getLocalizedMessage())&& !"Null".equalsIgnoreCase(ex.getLocalizedMessage()))
				error = ex.getLocalizedMessage();
			logger.info("DeptCollectionSubcollectionmapping: Returning response for message id "+ response.getMessageID()+ error);
			response.setResultFlag("FAILED");
			response.setMessageDetails(" Code Table was not updated for messagID "+ response.getMessageID()+ " "+error);
			return response;
		}
		if(FormatHelper.hasContent(failedKeys)){
			logger.info("DeptCollectionSubcollectionmapping: Composing partial Failed response for message id "+ response.getMessageID());
			response.setResultFlag("FAILED");
			if(processedAtleasOne)
				response.setMessageDetails("Failed for=+ " +failedKeys);//+  " Successfully processed: "+ successKeys);
			else
				response.setMessageDetails("Failed for=+ " +failedKeys);
				
		}else{
			logger.info("DeptCollectionSubcollectionmapping: Composing Success response for message id "+ response.getMessageID());
			response.setResultFlag("SUCCESS");
			response.setMessageDetails("DeptCollectionSubcollectionmapping: All Keys successfully processed");
		}
		logger.info("DeptCollectionSubcollectionmapping: Returning response for message id "+ response.getMessageID());
		if(FormatHelper.hasContent(failedKeys)){
			response.setResultFlag("FAILED");
			if(FormatHelper.hasContent(successKeys))
				response.setMessageDetails("DeptCollectionSubcollectionmapping: Partial success processed :Failed "+ failedKeys);
			else
				response.setMessageDetails("DeptCollectionSubcollectionmapping: extract failed for  "+ failedKeys);
		}
		return response;
	}
	
	public HashMap<String, AttributeStoreTable> getDepartmentMap() {
		if (departmentMap == null || departmentMap.size() < 1) {
			refreshDepartmentMap();
		}
		return departmentMap;
	}

	/**
	 * Set refreshed Departmental Map.
	 */
	public void setDepartmentMap(HashMap<String, AttributeStoreTable> departmentMap) {
		this.departmentMap = departmentMap;
	}
	
	public CodeTableResponse updateVariantMasterTable(CodeTableResponse response, CodeTable codeTable){
		//Class=ATTRIBUTESTORE;STYLECLASS;PRODUCT;PRODUCT
		logger.info("CodeTableServiceImpl.updateVariantMasterTable : Entering for codeTable "+ codeTable.getCodeTableName());
		String failedKeys = "";
		String successKeys="";
		String codeTableName = codeTable.getCodeTableName();
		String tableName = "VariantMaster";
		Items items = codeTable.getItems();
		if(items != null && items.getItem().length >0){
			logger.info("CodeTableServiceImpl.updateVariantMasterTable : items list retrieved for "+codeTable.getCodeTableName());
			Item[] itemsArray = items.getItem();
			for(int i =0; i< itemsArray.length; i++){
				Item item = itemsArray[i];
				logger.info("CodeTableServiceImpl.updateVariantMasterTable : item  retrieved for "+codeTable.getCodeTableName());
				Field[] fieldArray = item.getFields().getField();
			
				String currentKey ="";
				try{
					HashMap fieldMap = new HashMap();
					for(int j = 0 ;j < fieldArray.length; j++){
						Field field = fieldArray[j];
						fieldMap.put(field.getId().toUpperCase(),field.getValue());
					}
					logger.info("CodeTableServiceImpl.updateVariantMasterTable : fields retrieved list retrieved for "+codeTable.getCodeTableName()+ fieldMap);
					String action = (String)fieldMap.get("ACTIONSTATUS");
					String newAction="";
					VariantMaster variantMaster = new VariantMaster();
					variantMaster.setOid(Long.parseLong((String)fieldMap.get("OID")));
					variantMaster.setName((String)fieldMap.get("NAME"));
					variantMaster.setVariantKey((String)fieldMap.get("VARIANTKEY"));
					variantMaster.setCode((String)fieldMap.get("CODE"));
					variantMaster.setMaterialType((String)fieldMap.get("MATERIALTYPE"));
					variantMaster.setSubType((String)fieldMap.get("SUBTYPE"));
					variantMaster.setVariant((String)fieldMap.get("VARIANT"));
					variantMaster.setVariantID((String)fieldMap.get("VARIANTID"));
					variantMaster.setVariantDesc((String)fieldMap.get("VARIANTDESC"));
					currentKey= (String)fieldMap.get("VARIANTKEY");
					//CREATEDATE
					//UPDATEDATE
					VariantMaster existing = null;
					String query = "from com.pmdbcoach.hibernate.skuintegration.VariantMaster where OID ='"+ variantMaster.getOid()+"'";
					try{
						long longId = variantMaster.getOid();
						List<VariantMaster> tableList = new ArrayList<VariantMaster>();
						tableList = variantMasterRepository.findList(longId);
						//tableList = PersistanctHelper.Query(query, tableList);
						if(tableList == null || tableList.size() == 0){
							newAction = "Create";
						}
						else {
							newAction = "Update";
							existing = (VariantMaster)tableList.get(0);
						}
					}catch (Exception ex){
						
						if(FormatHelper.hasContent(failedKeys))
							failedKeys = failedKeys+","+failedKeys;
						else
							failedKeys =currentKey;
						continue;
					}
					if(action.equalsIgnoreCase("DELETE") && existing != null){
						try{
							variantMasterRepository.delete(existing);
							//PersistanctHelper.deleteRow(existing);
							if(FormatHelper.hasContent(successKeys))
								successKeys = successKeys+ ","+currentKey;
							else
								successKeys = currentKey;
						}catch(Exception ex){
						
							if(FormatHelper.hasContent(failedKeys))
								failedKeys = failedKeys+","+failedKeys;
							else
								failedKeys =currentKey;
						}
						continue;
					}
					logger.info("CodeTableServiceImpl.updateVariantMasterTable : "+ codeTable.getCodeTableName()+ " key "+ variantMaster.getOid()+ " display "+ variantMaster.getVariantDesc());
				
					if(newAction.equalsIgnoreCase("CREATE")){
						logger.info("CodeTableServiceImpl.updateVariantMasterTable : creating new row ");
						try{
							variantMaster.setCreateDate(new Date());
							variantMaster.setUpdateDate(new Date());
							variantMasterRepository.saveAndFlush(variantMaster);
							//PersistanctHelper.createRow(variantMaster);
							if(FormatHelper.hasContent(successKeys))
								successKeys = successKeys+ ","+currentKey;
							else
								successKeys = currentKey;
							logger.info("Insert Success for "+ currentKey); ;
							
						}catch(Exception ex){
							ex.printStackTrace();
							if(FormatHelper.hasContent(failedKeys))
								failedKeys = failedKeys+","+failedKeys;
							else
								failedKeys =currentKey;
						}
						
					}else if(newAction.equalsIgnoreCase("UPDATE")){
					logger.info("CodeTableServiceImpl.updateVariantMasterTable : updating existing row ");
						try{
							variantMaster.setCreateDate(existing.getCreateDate());
							variantMaster.setUpdateDate(new Date());
							variantMasterRepository.save(variantMaster);
							//PersistanctHelper.updateRow(variantMaster);
							if(FormatHelper.hasContent(successKeys))
								successKeys = successKeys+ ","+currentKey;
							else
								successKeys = currentKey;
							logger.info("Update Success for "+ currentKey); ;
							
						}catch(Exception ex){
							ex.printStackTrace();
							if(FormatHelper.hasContent(failedKeys))
								failedKeys = failedKeys+","+failedKeys;
							else
								failedKeys =currentKey;
						}
					}
				
				}catch(Exception ex){
					ex.printStackTrace();
					logger.info("Got error in saving class attribute ");
					failedKeys = failedKeys+" "+currentKey; 
				}
			}
			
		}
		
		if(FormatHelper.hasContent(failedKeys)){
			response.setResultFlag("FAILED");
			response.setMessageDetails("Codetable :"+ codeTable.getCodeTableName()+  "was not fully processed, FAILEDKEYS: "+ failedKeys);
		}else
		{
			response.setResultFlag("SUCCESS");
			response.setMessageDetails("Codetable :"+ codeTable.getCodeTableName()+" was successfully processed ");
		}
		logger.info("Update Class table the messageiD "+ response.getMessageID());
		return response;
		
	}
	public HashMap<String, String> convertFieldsArrayIntoMap (Field[] fieldArray){
		HashMap<String, String> fieldMap = new HashMap<String, String>();
		logger.info("CodeTableServiceImpl.convertFieldsArrayIntoMap : fieldArray "+ fieldArray.length);
		try{
			
			for(int j = 0 ;j < fieldArray.length; j++){
				Field field = fieldArray[j];
				logger.info("CodeTableServiceImpl.convertFieldsArrayIntoMap " +field.getId().toUpperCase()+"  " +field.getValue());
				fieldMap.put(field.getId().toUpperCase(),field.getValue());
			    
			}
		}catch(Exception ex){
			
				ex.printStackTrace();
				return null;
			
		}
		logger.info("CodeTableServiceImpl.convertFieldsArrayIntoMap : returning "+ fieldMap);
		return fieldMap;
	}
	public CodeTableResponse updateFiscalTable(CodeTableResponse response, CodeTable codeTable){
		logger.info("CodeTableServiceImpl.updateFISCAL : Entering for codeTable "+ codeTable.getCodeTableName());
		String failedKeys = "";
		String successKeys="";
		boolean processedAtleasOne = false;
		
		try{
			String codeTableName = codeTable.getCodeTableName();
			Items items = codeTable.getItems();
			if(items != null && items.getItem() != null && items.getItem().length>0){
				logger.info("CodeTableServiceImpl.update FISCAL Code Table mapping : Item array is not null and has array length greater htan 0 ");
				Item[] mappingItems = items.getItem();
				logger.info("****mappingItems.length :: "+mappingItems.length);
				for(int index = 0; index < mappingItems.length; index++){
					Item mappingItem = mappingItems[index];
					Fields fields = mappingItem.getFields();
					logger.info("****fields :: "+fields);
					logger.info("****fields.length :: "+fields.getField().length);
					
					if(fields != null && fields.getField().length >0){
						logger.info("CodeTableServiceImpl.FISCAL Code Table mapping : Field in Itemm array is not null ");
						Field[] allfields = fields.getField();
						HashMap <String,String>fieldMap = convertFieldsArrayIntoMap(allfields);
						logger.info("####fieldMap size "+fieldMap.size());
						if(fieldMap != null && fieldMap.size()>0){
							try
							{
								 String uniqueID = (String)fieldMap.get("CODE");
								 String oid = (String)fieldMap.get("OID");
								 String NAME = (String)fieldMap.get("NAME");
								
								 String altFiscalYearName = (String)fieldMap.get("ALTFISCALYEARNAME");
								 String fisCalYear = (String)fieldMap.get("FISCALYEAR");
								 String startDate = (String)fieldMap.get("STARTDATE");
								 String endDate = (String)fieldMap.get("ENDDATE");
								 String createBy = (String)fieldMap.get("CREATEBY");
								 //String createDate = (String)fieldMap.get("CREATEDATE");
								 
								 
								 String lastUpdateBy = (String)fieldMap.get("LASTUPDATEBY");
								 //String lastUpdateDate = (String)fieldMap.get("LASTUPDATEDATE");
								 String status = (String)fieldMap.get("STATUS");
								 String actionStatus = (String)fieldMap.get("ACTIONSTATUS");
								 
								 
								 //createdate updatedate
								 if(!FormatHelper.hasContent(NAME) || !FormatHelper.hasContent(altFiscalYearName)|| !FormatHelper.hasContent(fisCalYear)||
										 !FormatHelper.hasContent(endDate) || !FormatHelper.hasContent(createBy) || 
										  !FormatHelper.hasContent(lastUpdateBy) || 
										 !FormatHelper.hasContent(status) )
								 {
									 logger.info("CodeTableServiceImpl.FISCAL Code Table mapping.. One of the fiels doesn't have a value");
									 failedKeys = uniqueID+":Missing RequiredFields,";
								 }
								 
								 
//								 HashMap fiscalmap = AttributeStoreHelper.getFISCALMAP();
//								 logger.info("CodeTableServiceImpl.updateFISCALTable got the UniqueID "+ uniqueID);
//								 fiscaltable tbl  = (fiscaltable)fiscalmap.get(uniqueID);
//								 if(tbl == null){
//									 logger.info("CodeTableServiceImpl.updateFISCALTable got the uniqueID "+ uniqueID+"  doesn't exist in PMDB");
//									 failedKeys = uniqueID+":uniqueID key "+uniqueID+" doesn exist in PMDB,";
//									 
//								 }else
//								 {
									 logger.info("***********CodeTableServiceImpl.FISCAL Code Table mapping got the uniqueID "+ uniqueID+" in PMDB");
									 FiscalTable fiscal  = new FiscalTable();//caps
									 fiscal.setAltFiscalYearName(altFiscalYearName);
									 fiscal.setCreateBy(createBy);
									 //fiscal.setCreateDate(createDate);
									 fiscal.setEndDate(endDate);
									 fiscal.setFisCalYear(fisCalYear);
									 fiscal.setLastUpdateBy(lastUpdateBy);
									 //fiscal.setLastUpdateDate(lastUpdateDate);
									 fiscal.setNAME(NAME);
									 fiscal.setOid(oid);
									 fiscal.setStartDate(startDate);
									 fiscal.setStatus(actionStatus);
									 fiscal.setUniqueID(uniqueID);
									 logger.info("***********actionStatus"+actionStatus);
										
									 List<FiscalTable> lst = null;
										FiscalTable fiscalexist = null;
										lst = fiscalTableRepository.FindID(fiscal.getUniqueID().toString());
										if (lst != null && !lst.isEmpty()) {
											fiscalexist = lst.get(0);
										} else {
											logger.info("Record does not exist in FiscalTable");
										}
//									 FiscalTable fiscalexist = (FiscalTable) new PMDBObjectQuery().searchFISCALQuery(fiscal.getUniqueID().toString());
//									 logger.info("***********fiscalexist"+fiscalexist);
										
									 if("Create".equalsIgnoreCase(actionStatus)||"Update".equalsIgnoreCase(actionStatus)){
										 if(fiscalexist == null){
											 fiscal.setCreateDate(new Date().toString());
											 fiscal.setLastUpdateDate(new Date().toString());
											 fiscalTableRepository.saveAndFlush(fiscal);
											 //PersistanctHelper.createRow(fiscal);
											 logger.info("CodeTableServiceImpl.FISCAL Code Table mapping Create the  new object "+ fiscal.toString());
										 }
										 else{
											 fiscal.setCreateDate(fiscalexist.getCreateDate());
											 fiscal.setLastUpdateDate(new Date().toString());
											 fiscalTableRepository.save(fiscal);
											 //PersistanctHelper.updateRow(fiscal);
											 logger.info("CodeTableServiceImpl.FISCAL Code Table mapping updating the existing object "+ fiscal.toString());
										 }
									 }else if("Delete".equalsIgnoreCase(actionStatus)){
										 if(fiscalexist != null){
											 fiscalTableRepository.delete(fiscal);
											 //PersistanctHelper.deleteRow(fiscal);
											 logger.info("CodeTableServiceImpl.FISCAL Code Table mapping Deleting the existing object "+ fiscal.toString());
										 }else{
											 logger.info("CodeTableServiceImpl.FISCAL Code Table mapping no existing object found so nothing to delete "+ fiscal.toString());
										 }
									 }
									 successKeys= successKeys+uniqueID+",";
									 processedAtleasOne= true;
//								}	 
							}catch(Exception ex){
								ex.printStackTrace();
								logger.info("fiscal table: Error while saving one of the items "+ex.getMessage());
								failedKeys = fieldMap.get("CODE")+":"+ex.getLocalizedMessage()+",";
							}
						}
					}
					
				}	
			}else{
				logger.info("FISCAL Code Table mapping: no values read" );
				response.setResultFlag("FAILED");
				response.setMessageDetails(" Code Table "+ codeTableName+" was not updated for messagID "+ response.getMessageID()+ " as no values were retrieved in request");
				logger.info("FISCAL Code Table mapping: Returning response for message id "+ response.getMessageID()+ " "+ response.getMessageDetails());
				return response;
			}
		}catch(Exception ex){
			ex.printStackTrace();
			logger.info(ex.getMessage());
			logger.info("FISCAL Code Table mapping: Returning response for message id "+ response.getMessageID());
			response.setResultFlag("FAILED");
			response.setMessageDetails(" Code Table was not updated for messagID "+ response.getMessageID()+ " "+ex.getLocalizedMessage());
			return response;
		}
		if(FormatHelper.hasContent(failedKeys)){
			logger.info("FISCAL Code Table mapping: Composing partial Failed response for message id "+ response.getMessageID());
			response.setResultFlag("FAILED");
			if(processedAtleasOne)
				response.setMessageDetails("Was not processed sucessfully, Failed for=+ " +failedKeys);
			else
				response.setMessageDetails("Failed for=+ " +failedKeys);
				
		}else{
			logger.info("FISCAL Code Table mapping: Composing Success response for message id "+ response.getMessageID());
			response.setResultFlag("SUCCESS");
			response.setMessageDetails("FISCAL Code Table mapping: Successfully processed: "+ successKeys);
		}
		logger.info("FISCAL Code Table mapping: Returning response for message id "+ response.getMessageID());
		if(FormatHelper.hasContent(failedKeys))
			response.setResultFlag("FAILED");
		return response;
	}
	private CodeTableResponse updatePreiumFXCostAggTable(CodeTableResponse response,	CodeTable codeTable) {

		logger.info("CodeTableServiceImpl.updateFISCAL : Entering for codeTable "+ codeTable.getCodeTableName());
		String failedKeys = "";
		String successKeys="";
		boolean processedAtleasOne = false;
		
		try{
			String codeTableName = codeTable.getCodeTableName();
			Items items = codeTable.getItems();
			if(items != null && items.getItem() != null && items.getItem().length>0){
				logger.info("CodeTableServiceImpl.update PreiumFXCostAgg Code Table mapping : Item array is not null and has array length greater htan 0 ");
				Item[] mappingItems = items.getItem();
				logger.info("****mappingItems.length :: "+mappingItems.length);
				for(int index = 0; index < mappingItems.length; index++){
					Item mappingItem = mappingItems[index];
					Fields fields = mappingItem.getFields();
					logger.info("****fields :: "+fields);
					logger.info("****fields.length :: "+fields.getField().length);
					
					if(fields != null && fields.getField().length >0){
						logger.info("CodeTableServiceImpl.PreiumFXCostAgg Code Table mapping : Field in Itemm array is not null ");
						Field[] allfields = fields.getField();
						HashMap <String,String>fieldMap = convertFieldsArrayIntoMap(allfields);
						logger.info("####fieldMap size "+fieldMap.size());
						if(fieldMap != null && fieldMap.size()>0){
							try
							{
								
							
								 String uniqueID = (String)fieldMap.get("CODE");
								 String oid = (String)fieldMap.get("OID");
								 String NAME = (String)fieldMap.get("NAME");
								 String department = (String)fieldMap.get("DEPARTMENT");
								 String fisCalYear = (String)fieldMap.get("FISCALYEAR");
								 String premiumRate = (String)fieldMap.get("PREMIUMRATE");
								 String jpFXRate= (String)fieldMap.get("JPYFXRATE");
								 String jpTax= (String)fieldMap.get("JPYTAX");
								 String freight = (String)fieldMap.get("FREIGHT");
								 String overHead = (String)fieldMap.get("OVERHEAD");
								 String targetCost = (String)fieldMap.get("TARGETCOST");
								 String createBy = (String)fieldMap.get("CREATEBY");
								 //String createDate = (String)fieldMap.get("CREATEDATE");
								 String lastUpdateBy = (String)fieldMap.get("LASTUPDATEBY");
								 //String lastUpdateDate = (String)fieldMap.get("LASTUPDATEDATE");
								 String status = (String)fieldMap.get("STATUS");
								 String actionStatus = (String)fieldMap.get("ACTIONSTATUS");
								 
								 
								 
								
									 logger.info("***********CodeTableServiceImpl.PreiumFXCostAgg Code Table mapping got the uniqueID "+ uniqueID+" in PMDB");
									 PremiumFXRateCodeTable fxrate  = new PremiumFXRateCodeTable();//caps
									 PremiumFXRateCodeTable_PK pk = new PremiumFXRateCodeTable_PK();
									 pk.setOid(oid);
									 pk.setCode(uniqueID);
									 
									 
									 fxrate.setCode_OID(pk);
									 
									 fxrate.setNAME(NAME);
									 fxrate.setDepartMent(department);
									 fxrate.setFisCalYear(fisCalYear);
									 fxrate.setJpyFXrate(jpFXRate);
									 fxrate.setPremiumRate(premiumRate);
									 fxrate.setFreight(freight);
									 fxrate.setJpyTax(jpTax);
									 fxrate.setOverHead(overHead);
									 fxrate.setTargetCost(targetCost);
									// fxrate.setCreateDate(createDate);
									// fxrate.setUpdateDate(lastUpdateDate);
									 fxrate.setStatus(actionStatus);
									 logger.info("***********actionStatus"+actionStatus);
									
										List<PremiumFXRateCodeTable> lst = null;

									 PremiumFXRateCodeTable fxrateexist = null;
										lst = premiumFXRateCodeRepository.FindID(pk.getCode(), pk.getOid());
										if (lst != null && !lst.isEmpty()) {
											fxrateexist = lst.get(0);
										} else {
											logger.info("Record does not exist in PremiumFXRateCodeTable");
										}
									
									 //PremiumFXRateCodeTable fxrateexist = (PremiumFXRateCodeTable) new PMDBObjectQuery().searchFXRAteQuery(pk);
									 logger.info("***********fiscalexist"+fxrateexist);
										
									 if("Create".equalsIgnoreCase(actionStatus)||"Update".equalsIgnoreCase(actionStatus)){
										 logger.info("***********Action Status :: "+actionStatus);
										 if(fxrateexist == null){
											 logger.info("***********Start Creating");
											 fxrate.setCreateDate(new Date());
											 fxrate.setUpdateDate(new Date());
											 premiumFXRateCodeRepository.saveAndFlush(fxrate);
											 //PersistanctHelper.createRow(fxrate);
											 logger.info("CodeTableServiceImpl.PreiumFXCostAgg Code Table mapping Create the  new object "+ fxrate.toString());
										 }
										 else{
											 logger.info("***********Start Updating");
											 fxrate.setCreateDate(fxrateexist.getCreateDate());
											 fxrate.setUpdateDate(new Date());
											 premiumFXRateCodeRepository.save(fxrate);
											 //PersistanctHelper.updateRow(fxrate);
											 logger.info("CodeTableServiceImpl.PreiumFXCostAgg Code Table mapping updating the existing object "+ fxrate.toString());
										 }
									 }else if("Delete".equalsIgnoreCase(actionStatus)){
										 if(fxrateexist != null){
											 premiumFXRateCodeRepository.delete(fxrate);
											 //PersistanctHelper.deleteRow(fxrate);
											 logger.info("CodeTableServiceImpl.PreiumFXCostAgg Code Table mapping Deleting the existing object "+ fxrate.toString());
										 }else{
											 logger.info("CodeTableServiceImpl.PreiumFXCostAgg Code Table mapping no existing object found so nothing to delete "+ fxrate.toString());
										 }
									 }
									 successKeys= successKeys+uniqueID+",";
									 processedAtleasOne= true;
//								}	 
							}catch(Exception ex){
								ex.printStackTrace();
								logger.info("PreiumFXCostAgg table: Error while saving one of the items "+ex.getMessage());
								failedKeys = fieldMap.get("CODE")+":"+ex.getLocalizedMessage()+",";
							}
						}
					}
					
				}	
			}else{
				logger.info("PreiumFXCostAgg Code Table mapping: no values read" );
				response.setResultFlag("FAILED");
				response.setMessageDetails(" Code Table "+ codeTableName+" was not updated for messagID "+ response.getMessageID()+ " as no values were retrieved in request");
				logger.info("PreiumFXCostAgg Code Table mapping: Returning response for message id "+ response.getMessageID()+ " "+ response.getMessageDetails());
				return response;
			}
		}catch(Exception ex){
			ex.printStackTrace();
			logger.info(ex.getMessage());
			logger.info("PreiumFXCostAgg Code Table mapping: Returning response for message id "+ response.getMessageID());
			response.setResultFlag("FAILED");
			response.setMessageDetails(" Code Table was not updated for messagID "+ response.getMessageID()+ " "+ex.getLocalizedMessage());
			return response;
		}
		if(FormatHelper.hasContent(failedKeys)){
			logger.info("PreiumFXCostAgg Code Table mapping: Composing partial Failed response for message id "+ response.getMessageID());
			response.setResultFlag("FAILED");
			if(processedAtleasOne)
				response.setMessageDetails("PreiumFXCostAgg Code Table was not processed successfully, Failed for=+ " +failedKeys);
			else
				response.setMessageDetails("PreiumFXCostAgg Code Table Failed for=+ " +failedKeys);
				
		}else{
			logger.info("PreiumFXCostAgg Code Table mapping: Composing Success response for message id "+ response.getMessageID());
			response.setResultFlag("SUCCESS");
			response.setMessageDetails("PreiumFXCostAgg Code Table mapping: Successfully processed ");
		}
		logger.info("PreiumFXCostAgg Code Table mapping: Returning response for message id "+ response.getMessageID());
		if(FormatHelper.hasContent(failedKeys))
			response.setResultFlag("FAILED");
		return response;
	
		
	}

	
	private CodeTableResponse updatePMSMG(CodeTableResponse response,	CodeTable codeTable) {

		logger.info("CodeTableServiceImpl.updateFISCAL : Entering for codeTable "+ codeTable.getCodeTableName());
		String failedKeys = "";
		String successKeys="";
		boolean processedAtleasOne = false;
		
		try{
			String codeTableName = codeTable.getCodeTableName();
			Items items = codeTable.getItems();
			if(items != null && items.getItem() != null && items.getItem().length>0){
				logger.info("CodeTableServiceImpl.update PMSMG Code Table mapping : Item array is not null and has array length greater htan 0 ");
				Item[] mappingItems = items.getItem();
				logger.info("****mappingItems.length :: "+mappingItems.length);
				for(int index = 0; index < mappingItems.length; index++){
					Item mappingItem = mappingItems[index];
					Fields fields = mappingItem.getFields();
					logger.info("****fields :: "+fields);
					logger.info("****fields.length :: "+fields.getField().length);
					
					if(fields != null && fields.getField().length >0){
						logger.info("CodeTableServiceImpl.PMSMG Code Table mapping : Field in Itemm array is not null ");
						Field[] allfields = fields.getField();
						HashMap <String,String>fieldMap = convertFieldsArrayIntoMap(allfields);
						logger.info("####fieldMap size "+fieldMap.size());
						if(fieldMap != null && fieldMap.size()>0){
							try
							{
								
							
								 String uniqueID = (String)fieldMap.get("CODE");
								 String oid = (String)fieldMap.get("OID");
								 String NAME = (String)fieldMap.get("NAME");
								 String smg = (String)fieldMap.get("SMG");
								 
								 String department = (String)fieldMap.get("DEPT");
								 String Class = (String)fieldMap.get("CLASS");
								 String subClass = (String)fieldMap.get("SUBCLASS");
								 
								 String SILHOUETTE= (String)fieldMap.get("SILHOUETTE");
								 String CCTUSE= (String)fieldMap.get("CCTUSE");
								 //String createDate = (String)fieldMap.get("CREATEDATE");
								 //String lastUpdateDate = (String)fieldMap.get("LASTUPDATEDATE");
								 String status = (String)fieldMap.get("STATUS");
								 String actionStatus = (String)fieldMap.get("ACTIONSTATUS");
								 
								 logger.info("*********"+fieldMap);
								 
								 
								 //createdate updatedate
								 if(!FormatHelper.hasContent(NAME) || !FormatHelper.hasContent(department)||
										 !FormatHelper.hasContent(uniqueID) || !FormatHelper.hasContent(smg))
								 {
									 logger.info("CodeTableServiceImpl.PMSMG Code Table mapping.. One of the fiels doesn't have a value");
									 failedKeys = uniqueID+":Missing RequiredFields,";
									 continue;
								 }
								 
								
									 logger.info("***********CodeTableServiceImpl.PMSMG Code Table mapping got the uniqueID "+ uniqueID+" in PMDB");
									 PMSMGMapping pmsmg  = new PMSMGMapping();//caps
									 pmsmg.setOid(Integer.parseInt(oid));
									 pmsmg.setCode(Integer.parseInt(uniqueID));
									 pmsmg.setName(NAME);
									 pmsmg.setDept(department);
									 pmsmg.setSMG(smg);
									 pmsmg.setSilhouette(SILHOUETTE);
									 pmsmg.setClassDetails(Class);
									 pmsmg.setSubClass(subClass);
									 pmsmg.setCctuse(CCTUSE);
									 //pmsmg.setCreatedDate(createDate);
									 //pmsmg.setLastUpdateDate(lastUpdateDate);
									 pmsmg.setStatus(status);
									 logger.info("***********actionStatus"+actionStatus);
									 
									 List<PMSMGMapping> lst = null;
										PMSMGMapping fxrateexist = null;
										lst = pMSMGMappingRepository.FindID(uniqueID);
										if (lst != null && !lst.isEmpty()) {
											fxrateexist = lst.get(0);
										} else {
											logger.info("Record does not exist in PMSMGMapping");
										}
										
									 //PMSMGMapping fxrateexist = (PMSMGMapping) new PMDBObjectQuery().searchPMSMGQuery(uniqueID);
									 logger.info("***********PMSMGexist"+fxrateexist);
										
									 if("Create".equalsIgnoreCase(actionStatus)||"Update".equalsIgnoreCase(actionStatus)){
										 if(fxrateexist == null){
											 pmsmg.setCreatedDate(new Date());
											 pmsmg.setLastUpdateDate(new Date());
											 pMSMGMappingRepository.saveAndFlush(pmsmg);
											 //PersistanctHelper.createRow(pmsmg);
											 logger.info("CodeTableServiceImpl.PMSMG Code Table mapping Create the  new object "+ pmsmg.toString());
										 }
										 else{
											 
											 pmsmg.setCreatedDate(fxrateexist.getCreatedDate());
											 pmsmg.setLastUpdateDate(new Date());
												pMSMGMappingRepository.save(pmsmg);

											 //PersistanctHelper.updateRow(pmsmg);
											 logger.info("CodeTableServiceImpl.PMSMG Code Table mapping updating the existing object "+ pmsmg.toString());
										 }
									 }else if("Delete".equalsIgnoreCase(actionStatus)){
										 if(fxrateexist != null){
											 pMSMGMappingRepository.delete(pmsmg);
											 //PersistanctHelper.deleteRow(pmsmg);
											 logger.info("CodeTableServiceImpl.PMSMG Code Table mapping Deleting the existing object "+ pmsmg.toString());
										 }else{
											 logger.info("CodeTableServiceImpl.PMSMG Code Table mapping no existing object found so nothing to delete "+ pmsmg.toString());
										 }
									 }
									 successKeys= successKeys+uniqueID+",";
									 processedAtleasOne= true;
//								}	 
							}catch(Exception ex){
								ex.printStackTrace();
								logger.info("PMSMG table: Error while saving one of the items "+ex.getMessage());
								failedKeys = fieldMap.get("CODE")+":"+ex.getLocalizedMessage()+",";
							}
						}
					}
					
				}	
			}else{
				logger.info("PMSMG Code Table mapping: no values read" );
				response.setResultFlag("FAILED");
				response.setMessageDetails(" Code Table "+ codeTableName+" was not updated for messagID "+ response.getMessageID()+ " as no values were retrieved in request");
				logger.info("PMSMG Code Table mapping: Returning response for message id "+ response.getMessageID()+ " "+ response.getMessageDetails());
				return response;
			}
		}catch(Exception ex){
			ex.printStackTrace();
			logger.info(ex.getMessage());
			logger.info("PMSMG Code Table mapping: Returning response for message id "+ response.getMessageID());
			response.setResultFlag("FAILED");
			response.setMessageDetails(" Code Table was not updated for messagID "+ response.getMessageID()+ " "+ex.getLocalizedMessage());
			return response;
		}
		if(FormatHelper.hasContent(failedKeys)){
			logger.info("PMSMG Code Table mapping: Composing partial Failed response for message id "+ response.getMessageID());
			response.setResultFlag("FAILED");
			if(processedAtleasOne)
				response.setMessageDetails("PMSMG Code Table was not processed successfully Failed for=+ " +failedKeys);
			else
				response.setMessageDetails("Failed for=+ " +failedKeys);
				
		}else{
			logger.info("PMSMG Code Table mapping: Composing Success response for message id "+ response.getMessageID());
			response.setResultFlag("SUCCESS");
			response.setMessageDetails("PMSMG Code Table mapping: Successfully processed ");
		}
		logger.info("PMSMG Code Table mapping: Returning response for message id "+ response.getMessageID());
		if(FormatHelper.hasContent(failedKeys))
			response.setResultFlag("FAILED");
		return response;
	
		
	}
	private CodeTableResponse updateDepartmentClassSubClass(CodeTableResponse response,CodeTable codeTable) {
		logger.info("CodeTableServiceImpl.PMDepartMEntClassSubClass : Entering for codeTable "+ codeTable.getCodeTableName());
		String failedKeys = "";
		String successKeys="";
		boolean processedAtleasOne = false;
		try{
			String codeTableName = codeTable.getCodeTableName();
			Items items = codeTable.getItems();
			HashMap departmentMap = getDepartmentMap();
			if(items != null && items.getItem() != null && items.getItem().length>0){
				logger.info("CodeTableServiceImpl.PMDepartMEntClassSubClass : Item array is not null and has array length greater htan 0 ");
				Item[] mappingItems = items.getItem();
				for(int index = 0; index < mappingItems.length; index++){
					Item mappingItem = mappingItems[index];
					Fields fields = mappingItem.getFields();
					if(fields != null && fields.getField().length >0){
						logger.info("CodeTableServiceImpl.PMDepartMEntClassSubClass : Field in Itemm array is not null ");
						Field[] allfields = fields.getField();
						HashMap <String,String>fieldMap = convertFieldsArrayIntoMap(allfields);
						if(fieldMap != null && fieldMap.size()>0){
							try
							{
								 String attrDepartments="";
								 List attrDepartmentLists = new ArrayList();
								 String uniqueID = (String)fieldMap.get("CODE");
								 String oid = (String)fieldMap.get("OID");
								 String classKey = (String)fieldMap.get("CLASS");
								 String subclass = (String)fieldMap.get("SUBCLASS");
								 String classname = (String)fieldMap.get("CLASSNAME");
								 String subclassname = (String)fieldMap.get("NAME");
								 String status = (String)fieldMap.get("STATUS");
								 String actionStatus = (String)fieldMap.get("ACTIONSTATUS");
								 AttributeStoreTable attrClassExisting = new AttributeStoreTable() ;
								 AttributeStoreTable attrSubClassExisting = new AttributeStoreTable() ;
								 attrClassExisting= getstyleClassMap().get(classKey);
								 attrSubClassExisting= getSubClassMap().get(subclass);
								 if( !FormatHelper.hasContent(classKey)|| !FormatHelper.hasContent(subclass)||!FormatHelper.hasContent(classname)|| !FormatHelper.hasContent(subclassname))
								 {
									 logger.info("CodeTableServiceImpl.PMDepartMEntClassSubClass One of the fiels doesn't have a value");
									 failedKeys = uniqueID+":Missing RequiredFields,";
								 }
								 else if(attrClassExisting==null ||!classname.equalsIgnoreCase(attrClassExisting.getDisplay()) ){
									 logger.info("CodeTableServiceImpl.PMDepartMEntClassSubClass class "+classname+" unavailable in AttributeStore");
									 failedKeys = uniqueID+": Class unavailable in AttributeStore ";
								 }
								 else if(attrSubClassExisting==null ||!subclassname.equalsIgnoreCase(attrSubClassExisting.getDisplay())){
									 logger.info("CodeTableServiceImpl.PMDepartMEntClassSubClass Sub class"+subclass+" unavailable in AttributeStore");
									 failedKeys = uniqueID+":Sub Class unavailable in AttributeStore ";
								 }else if(attrClassExisting.getDepartment() == null || attrClassExisting.getDepartment().length()< 0){
									 logger.info("CodeTableServiceImpl.PMDepartMEntClassSubClass Class "+classKey+" not mapped to any departments in AttributeStore");
									 failedKeys = uniqueID+": Class not mapped to any department in AttributeStore ";
								 }
								 else
								 {
									 logger.info("Department : "+attrClassExisting.getDepartment());
									 attrDepartments= attrClassExisting.getDepartment();
									 if(attrDepartments!=null && attrDepartments.length() >0)
									 {
										 StringTokenizer tokenizer = new StringTokenizer(attrDepartments,";");
										 while(tokenizer.hasMoreTokens())
										 {
											String element = (String) tokenizer.nextElement();
											if(element!=null)
											{
												if(!attrDepartmentLists.contains(element))
												{
													attrDepartmentLists.add(element.trim());
												}
											}
										}
										logger.info("Department from AttributeStore for Class mapp: "+attrDepartmentLists);
									 }									 
									 for(int d=0;d<attrDepartmentLists.size();d++)
									 {
										 String newDepartment=(String) attrDepartmentLists.get(d);
										 logger.info("Department is to be mapped for class : "+newDepartment.trim());
										 AttributeStoreTable tbl  = (AttributeStoreTable)departmentMap.get(newDepartment);
										 if(tbl == null)
										 {
											 logger.info("CodeTableServiceImpl.PMDepartMEntClassSubClass got the departmentmap "+ newDepartment+"  doesn't exist in PMDB");
											 failedKeys = uniqueID+":Department key "+newDepartment+" doesn exist in PMDB,";
										 
										 }else
										 {
											 logger.info("CodeTableServiceImpl.PMDepartMEntClassSubClass got the departmentmap "+ newDepartment+" in PMDB");
											 String departmentDesc  = tbl.getDisplay2();
											 DepartmentClassSubClass deptclasssubclass  = new DepartmentClassSubClass();
											 DepartmentClassSubClass_PK pk = new DepartmentClassSubClass_PK();
											 deptclasssubclass.setClassCode(classKey);
											 deptclasssubclass.setSubClass(subclass);
											 pk.setDepartment(newDepartment);
											 pk.setCode(uniqueID);
											 deptclasssubclass.setCompKey(pk);
											 deptclasssubclass.setOid(Integer.parseInt(oid));
											 deptclasssubclass.setClassName(classname);
											 deptclasssubclass.setDeptName(departmentDesc);
											 deptclasssubclass.setSubClassName(subclassname);
											 deptclasssubclass.setStatus(status);
									 		 int indexOfHyphen = classname.indexOf("-");
									  		 logger.info("index of hyphen "+indexOfHyphen);
											 if(indexOfHyphen > -1 )
											 {
												 String display2 = classname.substring((indexOfHyphen+1), classname.length());
												 display2 = display2.trim();
												 deptclasssubclass.setClassDesc(display2);
												 logger.info("CodeTableServiceImpl.PMDepartMEntClassSubClass : "+ codeTable.getCodeTableName()+ " departmentDesc "+ departmentDesc+ "classkey "+ classKey+" classname "+ classname+ " subclassname "+ subclassname);
											 }
											 indexOfHyphen = subclassname.indexOf("-");
											 logger.info("index of hyphen "+indexOfHyphen);
											 if(indexOfHyphen > -1 )
											 {
												String display2 = subclassname.substring((indexOfHyphen+1), subclassname.length());
												display2 = display2.trim();
												deptclasssubclass.setSubClassDesc(display2);
												logger.info("CodeTableServiceImpl.PMDepartMEntClassSubClass : "+ codeTable.getCodeTableName()+ " departmentDesc "+ departmentDesc+ " classname "+ classname+ " subclassname "+ subclassname);
											 }
											 logger.info("CodeTableServiceImpl.PMDepartMEntClassSubClass new object "+ deptclasssubclass.toString());
											 //DepartmentClassSubClass existing = new DepartmentClassSubClass() ;
											 List<DepartmentClassSubClass> lst = null;
												DepartmentClassSubClass existing = null;
												lst = departmentSubClassRepository.FindID(uniqueID, newDepartment);
												if (lst != null && !lst.isEmpty()) {
													existing = lst.get(0);
												} else {
													logger.info("Record does not exist in DepartmentClassSubClass");
												}
											 //existing = (DepartmentClassSubClass) new PMDBObjectQuery().searchDepartmentClassSubclassMapping(uniqueID,newDepartment);
											 if("Create".equalsIgnoreCase(actionStatus)||"Update".equalsIgnoreCase(actionStatus))
											 {
												 if(existing == null)
												 {
													deptclasssubclass.setCreatedDate(new Date());
													deptclasssubclass.setLastUpdateDate(new Date());	
													departmentSubClassRepository.saveAndFlush(deptclasssubclass);
													//PersistanctHelper.createRow(deptclasssubclass);
													logger.info("CodeTableServiceImpl.PMDepartMEntClassSubClass Create the  new object "+ deptclasssubclass.toString());
												 }
												 else
												 {
													deptclasssubclass.setCreatedDate(existing.getCreatedDate());
													deptclasssubclass.setLastUpdateDate(new Date());
													departmentSubClassRepository.save(existing);
													//PersistanctHelper.updateRow(deptclasssubclass);
													logger.info("CodeTableServiceImpl.PMDepartMEntClassSubClass updating the existing object "+ deptclasssubclass.toString());
												 }
											 }
											 else if("Delete".equalsIgnoreCase(actionStatus)){
												 if(existing != null)
												 {
													 departmentSubClassRepository.delete(existing);
													 //PersistanctHelper.deleteRow(existing);
													 logger.info("CodeTableServiceImpl.PMDepartMEntClassSubClass Deleting the existing object "+ deptclasssubclass.toString());
												 }else
												 {
													 logger.info("CodeTableServiceImpl.PMDepartMEntClassSubClass no existing object found so nothing to delete "+ deptclasssubclass.toString());
												 }
											 }
											 successKeys= successKeys+uniqueID+",";
											 processedAtleasOne= true;
									 	}	 
									 }//end for loop of departments list for class
								}	 
							 }catch(Exception ex)
							 {
								ex.printStackTrace();
								logger.info("PMDepartMEntClassSubClass: Error while saving one of the items ");
								failedKeys = fieldMap.get("CODE")+":"+ex.getLocalizedMessage()+",";
							}
						}
					}
				}	
			}else
			{
				logger.info("PMDepartMEntClassSubClass: no values read" );
				response.setResultFlag("FAILED");
				response.setMessageDetails(" Code Table "+ codeTableName+" was not updated for messagID "+ response.getMessageID()+ " as no values were retrieved in request");
				logger.info("PMDepartMEntClassSubClass: Returning response for message id "+ response.getMessageID()+ " "+ response.getMessageDetails());
				return response;
			}
		}catch(Exception ex){
			ex.printStackTrace();
			logger.info("PMDepartMEntClassSubClass: Returning response for message id "+ response.getMessageID());
			response.setResultFlag("FAILED");
			response.setMessageDetails(" Code Table was not updated for messagID "+ response.getMessageID()+ " "+ex.getLocalizedMessage());
			return response;
		}
		if(FormatHelper.hasContent(failedKeys)){
			logger.info("PMDepartMEntClassSubClass: Composing partial Failed response for message id "+ response.getMessageID());
			response.setResultFlag("FAILED");
			if(processedAtleasOne)
				response.setMessageDetails("PM_DepartmentClassSubCalss is not processed successfully, Failed for=+ " +failedKeys);
			else
				response.setMessageDetails("Failed for=+ " +failedKeys);
				
		}else{
			logger.info("PMDepartMEntClassSubClass: Composing Success response for message id "+ response.getMessageID());
			response.setResultFlag("SUCCESS");
			response.setMessageDetails("PMDepartMEntClassSubClass: Successfully processed ");
		}
		logger.info("PMDepartMEntClassSubClass: Returning response for message id "+ response.getMessageID());
		if(FormatHelper.hasContent(failedKeys))
			response.setResultFlag("FAILED");
		return response;
	
		
		
	}
	private CodeTableResponse updatePMSizeScale(CodeTableResponse response,
			CodeTable codeTable) {

		logger.info("CodeTableServiceImpl.SizeScale : Entering for codeTable "+ codeTable.getCodeTableName());
		String failedKeys = "";
		String successKeys="";
		boolean processedAtleasOne = false;
		try{
			String codeTableName = codeTable.getCodeTableName();
			Items items = codeTable.getItems();
			//TODO
			int sizeLimit = Integer.parseInt(PropertyReaderUtil.getValue("codetable.sizescale.sizelimit","5"));
			if(items != null && items.getItem() != null && items.getItem().length>0){
				logger.info("CodeTableServiceImpl.update SizeScale Code Table mapping : Item array is not null and has array length greater htan 0 ");
				Item[] mappingItems = items.getItem();
				logger.info("****mappingItems.length :: "+mappingItems.length);
				//PMDBObjectQuery query = new PMDBObjectQuery();
				for(int index = 0; index < mappingItems.length; index++){
					Item mappingItem = mappingItems[index];
					Fields fields = mappingItem.getFields();
					logger.info("****fields :: "+fields);
					logger.info("****fields.length :: "+fields.getField().length);
					
					if(fields != null && fields.getField().length >0){
						logger.info("CodeTableServiceImpl.SizeScale Code Table mapping : Field in Itemm array is not null ");
						Field[] allfields = fields.getField();
						HashMap <String,String>fieldMap = convertFieldsArrayIntoMap(allfields);
						logger.info("####fieldMap size "+fieldMap.size());
						if(fieldMap != null && fieldMap.size()>0){
							try
							{
								
								 String uniqueID = (String)fieldMap.get("CODE");
								 String oid = (String)fieldMap.get("OID");
								 String NAME = (String)fieldMap.get("NAME");
								 String description = (String)fieldMap.get("DESCRIPTION");
								 
								 String department = (String)fieldMap.get("DEPTARTMENT");
								 String size = (String)fieldMap.get("SIZES");
								 String basesize = (String)fieldMap.get("BASESIZE");
								 String createby= (String)fieldMap.get("CREATEBY");
								 //String createDate = (String)fieldMap.get("CREATEDATE");
								 String SILHOUETTE= (String)fieldMap.get("LASTUPDATEBY");
								 //String lastUpdateDate = (String)fieldMap.get("LASTUPDATEDATE");
								 String status = (String)fieldMap.get("STATUS");
								 String actionStatus = (String)fieldMap.get("ACTIONSTATUS");
								 logger.info("*********"+fieldMap);
								 //createdate updatedate
								 if(!FormatHelper.hasContent(NAME) || !FormatHelper.hasContent(uniqueID) || !FormatHelper.hasContent(size)  )
								 {
									 logger.info("CodeTableServiceImpl.SizeScale Code Table mapping.. One of the fields doesn't have a value");
									failedKeys = uniqueID+":Missing RequiredFields,";
									continue;
								 }
								 
								 String sizeval[] = size.split(",");
								 String longSize ="";
								 boolean sizeLimitExceeded = false;
								 for(int i =0; i< sizeval.length; i++){
									 String sizeDisplay = sizeval[i];
									 if(sizeDisplay.trim().length() > 5){
										 longSize = sizeval[i];
										 sizeLimitExceeded = true;
										 break;
									 }else{
										
										 if(i ==0)
											 size = sizeDisplay.trim();
										 else
											 size = size+"|~*~|"+sizeDisplay.trim();
									 }
								 }
								 if(sizeLimitExceeded){
									 failedKeys = uniqueID+": "+longSize+" exceeds Size limit,";
									 continue;
								 }
								 logger.info("***********CodeTableServiceImpl.SizeScale Code Table mapping got the uniqueID "+ uniqueID+" in PMDB");
								 SizeScaleMaster sizescale = new SizeScaleMaster();
								 //SizeScaleMapping sizescale  = new SizeScaleMapping();//caps
								 //sizescale.setOid(Integer.parseInt(oid));
								 //sizescale.setUniqueID(uniqueID);
								 sizescale.setUniqueid(uniqueID);

								 sizescale.setSizeScaleMsg(NAME);
								 sizescale.setDepartment(department);
								 sizescale.setSizeScale(description);
								// size = size.replaceAll(",", "|~*~|");
								 sizescale.setSizeList(size);
								 sizescale.setBaseSize(basesize);
								
								 
								 sizescale.setLastUpdateDate(new Date());
								 sizescale.setStatus(status);
								 logger.info("***********actionStatus"+actionStatus);
									
								 List<SizeScaleMaster> lst = null;
									SizeScaleMaster existingSizeScale = null;
									lst = sizeScaleMappingRepository.findId(uniqueID);
									if (lst != null && !lst.isEmpty()) {
										existingSizeScale = lst.get(0);
									}
								 //SizeScaleMapping existingSizeScale = (SizeScaleMapping) query.searchSizeScaleQuery(uniqueID);
								 //logger.info("***********Size _Scale "+existingSizeScale);
									
								 if("Create".equalsIgnoreCase(actionStatus)||"Update".equalsIgnoreCase(actionStatus)){
									 if(existingSizeScale == null){
										 sizescale.setCreateDate(new Date());
										 sizeScaleMappingRepository.saveAndFlush(sizescale);
										 //PersistanctHelper.createRow(sizescale);
										 logger.info("CodeTableServiceImpl.SizeScale Code Table mapping Create the  new object "+ sizescale.toString());
									 }
									 else{
										 sizescale.setCreateDate(existingSizeScale.getCreateDate());
										 sizeScaleMappingRepository.save(sizescale);
										 //PersistanctHelper.updateRow(sizescale);
										 logger.info("CodeTableServiceImpl.SizeScale Code Table mapping updating the existing object "+ sizescale.toString());
									 }
								 }else if("Delete".equalsIgnoreCase(actionStatus)){
									 if(existingSizeScale != null){
										sizeScaleMappingRepository.delete(sizescale);
										//PersistanctHelper.deleteRow(sizescale);
										 logger.info("CodeTableServiceImpl.SizeScale Code Table mapping Deleting the existing object "+ sizescale.toString());
									 }else{
										 logger.info("CodeTableServiceImpl.SizeScale Code Table mapping no existing object found so nothing to delete "+ sizescale.toString());
									 }
								 }
								 
									/* AttributeStoreHelper attrhelper = new AttributeStoreHelper();
									 HashMap<String, String> sizemap = attrhelper.getSizeMap();
									 if(sizemap!=null){
										
										 for(String sizes : sizeval){
											 if(!sizemap.containsKey(sizes)){
												 AttributeStoreTable attr = new AttributeStoreTable();
												 attr.setDepartment(department);
												 
												 AttributeStoreTable_PK pk = new AttributeStoreTable_PK();
												 
												 pk.setValueKey(sizes);
												
												 pk.setObjectLevel("SKU");
												 pk.setObjectName("SKU");
												 pk.setAttributeName("SIZES");
												 attr.setCompKey(pk);
												 attr.setDisplay(sizes);
												 PersistanctHelper.createRow(attr);
												 logger.info("****************Inserting AttributeStoretable");
											 }
										 }
									 }*/
									 successKeys= successKeys+uniqueID+",";
									 processedAtleasOne= true;
//								}	 
							}catch(Exception ex){
								ex.printStackTrace();
								logger.info("SizeScale table: Error while saving one of the items "+ex.getMessage());
								failedKeys = fieldMap.get("CODE")+":"+ex.getLocalizedMessage()+",";
							}
						}
					}
					
				}	
			}else{
				logger.info("SizeScale Code Table mapping: no values read" );
				response.setResultFlag("FAILED");
				response.setMessageDetails(" Code Table "+ codeTableName+" was not updated for messagID "+ response.getMessageID()+ " as no values were retrieved in request");
				logger.info("SizeScale Code Table mapping: Returning response for message id "+ response.getMessageID()+ " "+ response.getMessageDetails());
				return response;
			}
		}catch(Exception ex){
			ex.printStackTrace();
			logger.info(ex.getMessage());
			logger.info("SizeScale Code Table mapping: Returning response for message id "+ response.getMessageID());
			response.setResultFlag("FAILED");
			response.setMessageDetails(" Code Table was not updated for messagID "+ response.getMessageID()+ " "+ex.getLocalizedMessage());
			return response;
		}
		if(FormatHelper.hasContent(failedKeys)){
			logger.info("SizeScale Code Table mapping: Composing partial Failed response for message id "+ response.getMessageID());
			response.setResultFlag("FAILED");
			if(processedAtleasOne)
				response.setMessageDetails("SizeScale code table was not successfully processed Failed for=+ " +failedKeys);
			else
				response.setMessageDetails("SizeScale code table Failed for=+ " +failedKeys);
				
		}else{
			logger.info("SizeScale Code Table mapping: Composing Success response for message id "+ response.getMessageID());
			response.setResultFlag("SUCCESS");
			response.setMessageDetails("SizeScale Code Table mapping: Successfully processed");
		}
		logger.info("SizeScale Code Table mapping: Returning response for message id "+ response.getMessageID());
		if(FormatHelper.hasContent(failedKeys))
			response.setResultFlag("FAILED");
		return response;
	
		
	}

	//TODO
	private CodeTableResponse updateIntroDateSeasonmapping(CodeTableResponse response,
			CodeTable codeTable) {
		logger.info("CodeTableServiceImpl.IntroDateSeasonmapping : Entering for codeTable "+ codeTable.getCodeTableName());
		String failedKeys = "";
		String successKeys="";
		boolean processedAtleasOne = false;
		try{
			String codeTableName = codeTable.getCodeTableName();
			Items items = codeTable.getItems();
			if(items != null && items.getItem() != null && items.getItem().length>0){
				logger.info("CodeTableServiceImpl.update IntroDateSeasonmapping Code Table mapping : Item array is not null and has array length greater than 0 ");
				Item[] mappingItems = items.getItem();
				logger.info("****mappingItems.length :: "+mappingItems.length);
				for(int index = 0; index < mappingItems.length; index++){
					Item mappingItem = mappingItems[index];
					Fields fields = mappingItem.getFields();
					logger.info("****fields :: "+fields);
					logger.info("****fields.length :: "+fields.getField().length);
					if(fields != null && fields.getField().length >0){
						logger.info("CodeTableServiceImpl.IntroDateSeasonmapping Code Table mapping : Field in Itemm array is not null ");
						Field[] allfields = fields.getField();
						HashMap <String,String>fieldMap = convertFieldsArrayIntoMap(allfields);
						logger.info("####fieldMap size "+fieldMap.size());
						if(fieldMap != null && fieldMap.size()>0){
							try
							{
								 String code = (String)fieldMap.get("CODE");
								 String oid = (String)fieldMap.get("OID");
								 String name = (String)fieldMap.get("NAME");
								 String Startshipdate = (String)fieldMap.get("STARTSHIPDATE");
								 String StartDate = (String)fieldMap.get("STARTDATE");
								 String EndDate = (String)fieldMap.get("ENDDATE");
								 String status = (String)fieldMap.get("STATUS");
								 String actionStatus = (String)fieldMap.get("ACTIONSTATUS");
								 logger.info("*********"+fieldMap);
								 //createdate updatedate
								 if(!FormatHelper.hasContent(name) || !FormatHelper.hasContent(code) || 
										!FormatHelper.hasContent(status) )
								 {
									 logger.info("CodeTableServiceImpl.IntroDateSeasonmapping Code Table mapping.One of the required fields(Name, Code)doesn't have a value");
									 failedKeys = code+":Missing RequiredFields,";
									 continue;
								 }
								 
								
								 logger.info("***********CodeTableServiceImpl.IntroDateSeasonmapping Code Table mapping Getting the uniqueID for "+ code+" in PMDB");
								 //IntroDateSeasonGroupMapping seasonmapping  = new IntroDateSeasonGroupMapping();//caps
								 SeasonGroup seasonmapping  = new SeasonGroup();//caps
								 String uniqueid= "";
								 String fsuniqueid="";
								 String seasonuniqueid="";
								 try{
									 uniqueid = attributeStoreTableRepository.findUniqueId(code);

									 //uniqueid = (new PMDBObjectQuery().searchIntrodateQuery(code)).getCompKey().getValueKey();
								 
								 }catch(Exception ex){
									 logger.info("Getting null uniqueid in Intro Date Season mapping Table" + ex.getMessage());
										logger.error(ex.getMessage(), ex);
									 ex.printStackTrace();
								 }
								 try{
									 fsuniqueid = attributeStoreTableRepository.findFsUniqueId(code);
									 //fsuniqueid = new PMDBObjectQuery().searchFSIntrodateQuery(code).getCompKey().getValueKey();
								 }catch(Exception ex){
									 ex.printStackTrace();
								 }
								 List<AttributeStoreTable> list = null;
								 AttributeStoreTable seasonAtt = new AttributeStoreTable();
								 String seasonName="";
								 try{
									 list = attributeStoreTableRepository.findName(name);
										if (list != null && !list.isEmpty()) {
										seasonAtt = list.get(0);
										seasonuniqueid = seasonAtt.getCompKey().getValueKey();
										seasonName = seasonAtt.getDisplay();
										}else {
											logger.info("Record does not exist in AttributeStoreTable");
										}
//									 seasonAtt= new PMDBObjectQuery().searchSeasonQuery(name);
//									 seasonuniqueid = seasonAtt.getCompKey().getValueKey();
//									 seasonName= seasonAtt.getDisplay();
								 }catch(Exception ex){
									 logger.info("Getting null name in Intro Date Season mapping Table" + ex.getMessage());
									 logger.error(ex.getMessage(), ex);
									 ex.printStackTrace();
								 }
								 if(!FormatHelper.hasContent(uniqueid)){
									 logger.info("CodeTableServiceImpl.IntroDateSeasonmapping Code Table mapping, Intro with display not present in PMDB "+ code);
									 failedKeys = code+":Intro with display not present in PMDB,";
									 continue;
								 }
								 if(!FormatHelper.hasContent(fsuniqueid)){
									 logger.info("CodeTableServiceImpl.IntroDateSeasonmapping Code Table FS Intro with display not present in PMDB "+code);
									 failedKeys = code+":FS Intro with display not present in PMDB,";
									 continue;
								 }
								 if(!FormatHelper.hasContent(seasonuniqueid)){
									 logger.info("CodeTableServiceImpl.IntroDateSeasonmapping Code Table mapping. seasonuniqueID not present in pmdbattributestore "+ name);
									 failedKeys = code+":Season UniqueID not present in PMDB,";
									 continue;
								 }
								 seasonmapping.setSeasongroupname(code);
								 seasonmapping.setUniqueid(uniqueid);
								 seasonmapping.setSeasonName(seasonName);
								 seasonmapping.setSeasonUniqueId(seasonuniqueid);
								 seasonmapping.setStartshipdate(FormatHelper.format2DateFromECV(Startshipdate));
								 seasonmapping.setFsstartshipdate(FormatHelper.format2DateFromECV(Startshipdate));
								 seasonmapping.setSgpTypeName("Intro");
								 seasonmapping.setStartdate(FormatHelper.format2DateFromECV(StartDate));
								 seasonmapping.setEnddate(FormatHelper.format2DateFromECV(EndDate));
								 logger.info("***********actionStatus"+actionStatus);
								 
								 List<SeasonGroup> lsts = null;
									SeasonGroup seasonmappingexist = null;
									lsts = seasonGroupRepository.FindID(uniqueid);
									if (lsts != null && !lsts.isEmpty()) {
										seasonmappingexist = lsts.get(0);
									} else {
										logger.info("Record does not exist in Audit table");
									}
								 //SeasonGroup seasonmappingexist = (SeasonGroup) new PMDBObjectQuery().searchintroDateMapping(uniqueid);
								 logger.info("***********IntroDateSeasonmapping Exist  "+seasonmappingexist + "  unqiueid "+ seasonmapping.getUniqueid()+" "+  uniqueid);
									
								 if("Create".equalsIgnoreCase(actionStatus)||"Update".equalsIgnoreCase(actionStatus)){
									 if(seasonmappingexist == null){
										 seasonmapping.setCreateDate(new Date());
										 seasonmapping.setUpdateDate(new Date());
										 seasonGroupRepository.saveAndFlush(seasonmapping);
										 //PersistanctHelper.createRow(seasonmapping);
										 logger.info("CodeTableServiceImpl.IntroDateSeasonmapping Code Table mapping Create the  new object "+ seasonmapping.toString());
									 }
									 else{
										 //seasonmapping.setCreateDate(seasonmappingexist.getCreateDate());
										 seasonmapping.setUpdateDate(new Date());
										 seasonGroupRepository.save(seasonmapping);
										 //PersistanctHelper.updateRow(seasonmapping);
										 logger.info("CodeTableServiceImpl.IntroDateSeasonmapping Code Table mapping updating the existing object "+ seasonmapping.toString());
									 }
								 }else if("Delete".equalsIgnoreCase(actionStatus)){
									 if(seasonmappingexist != null){
										 seasonGroupRepository.delete(seasonmapping);
										 //PersistanctHelper.deleteRow(seasonmapping);
										 logger.info("CodeTableServiceImpl.IntroDateSeasonmapping Code Table mapping Deleting the existing object "+ seasonmapping.toString());
									 }else{
										 logger.info("CodeTableServiceImpl.IntroDateSeasonmapping Code Table mapping no existing object found so nothing to delete "+ seasonmapping.toString());
									 }
								 }
								 
								 seasonmapping.setUniqueid((fsuniqueid));	
								 seasonmapping.setStartshipdate(FormatHelper.format2DateFromECV(Startshipdate));
								 seasonmapping.setFsstartshipdate(FormatHelper.format2DateFromECV(Startshipdate));
								 seasonmapping.setSgpTypeName("FS Intro");
								 
								 //SeasonGroup seasonmappingexistfs = (SeasonGroup) new PMDBObjectQuery().searchintroDateMapping(fsuniqueid);
								 List<SeasonGroup> lts = null;
									SeasonGroup seasonmappingexistfs = null;
									lts = seasonGroupRepository.FindID(fsuniqueid);
									if (lts != null && !lts.isEmpty()) {
										seasonmappingexistfs = lts.get(0);
									}else {
										logger.info("Record does not exist in SeasonGroup");
									}
								 if("Create".equalsIgnoreCase(actionStatus)||"Update".equalsIgnoreCase(actionStatus)){
									 if(seasonmappingexistfs == null){
										 seasonmapping.setCreateDate(new Date());
										 seasonmapping.setUpdateDate(new Date());
										 seasonGroupRepository.saveAndFlush(seasonmapping);
										 //PersistanctHelper.createRow(seasonmapping);
										 logger.info("CodeTableServiceImpl.IntroDateSeasonmapping Code Table mapping Create the  new object "+ seasonmapping.toString());
									 }
									 else{
										 seasonmapping.setUpdateDate(new Date());
										 seasonGroupRepository.save(seasonmapping);
										 //PersistanctHelper.updateRow(seasonmapping);
										 logger.info("CodeTableServiceImpl.IntroDateSeasonmapping Code Table mapping updating the existing object "+ seasonmapping.toString());
									 }
								 }else if("Delete".equalsIgnoreCase(actionStatus)){
									 if(seasonmappingexist != null){
										 seasonGroupRepository.delete(seasonmapping);
										 //PersistanctHelper.deleteRow(seasonmapping);
										 logger.info("CodeTableServiceImpl.IntroDateSeasonmapping Code Table mapping Deleting the existing object "+ seasonmapping.toString());
									 }else{
										 logger.info("CodeTableServiceImpl.IntroDateSeasonmapping Code Table mapping no existing object found so nothing to delete "+ seasonmapping.toString());
									 }
								 }
								 
								 
								 successKeys= successKeys+uniqueid+",";
								 processedAtleasOne= true;
								 
						}catch(Exception ex){
							ex.printStackTrace();
							logger.info("Introdate season mapping table: Error while saving one of the items "+ex.getMessage());
							failedKeys = fieldMap.get("CODE")+":"+ex.getLocalizedMessage()+",";
						}
					}
				}
				}	
			}else{
				logger.info("IntroDateSeasonmapping Code Table mapping: no values read" );
				response.setResultFlag("FAILED");
				response.setMessageDetails(" Code Table "+ codeTableName+" was not updated for messagID "+ response.getMessageID()+ " as no values were retrieved in request");
				logger.info("IntroDateSeasonmapping Code Table mapping: Returning response for message id "+ response.getMessageID()+ " "+ response.getMessageDetails());
				return response;
			}
		}catch(Exception ex){
			ex.printStackTrace();
			logger.info(ex.getMessage());
			logger.info("IntroDateSeasonmapping Code Table mapping: Returning response for message id "+ response.getMessageID());
			response.setResultFlag("FAILED");
			response.setMessageDetails(" Code Table was not updated for messagID "+ response.getMessageID()+ " "+ex.getLocalizedMessage());
			return response;
		}
		if(FormatHelper.hasContent(failedKeys)){
			logger.info("IntroDateSeasonmapping Code Table mapping: Composing partial Failed response for message id "+ response.getMessageID());
			response.setResultFlag("FAILED");
			if(processedAtleasOne)
				response.setMessageDetails("IntroDateSeasonmapping Code Table was not processed successfully Failed for: " +failedKeys);
			else
				response.setMessageDetails("IntroDateSeasonmapping Code Table was not processed successfully Failed for: " +failedKeys);
				
		}else{
			logger.info("IntroDateSeasonmapping Code Table mapping: Composing Success response for message id "+ response.getMessageID());
			response.setResultFlag("SUCCESS");
			response.setMessageDetails("IntroDateSeasonmapping : Successfully processed ");
		}
		logger.info("IntroDateSeasonmapping Code Table mapping: Returning response for message id "+ response.getMessageID());
		if(FormatHelper.hasContent(failedKeys))
			response.setResultFlag("FAILED");
		return response;
	
	}

	
	public boolean isRegularAttributeStore(String codeTableName){
		boolean isRegularAttributeStore=false;
		if("Collection".equalsIgnoreCase(codeTableName)|| "PM_Department".equalsIgnoreCase(codeTableName) || "PM_BillfoldPockets".equalsIgnoreCase(codeTableName)||"PM_StyleGroup".equalsIgnoreCase(codeTableName)||"PM_TEAM".equals(codeTableName) || "PM_MATERIAL".equalsIgnoreCase(codeTableName) ||
				"PM_CalcWHPriceorWHMU".equalsIgnoreCase(codeTableName) || "PM_CaseShape".equalsIgnoreCase(codeTableName) || "PM_CaseSize".equalsIgnoreCase(codeTableName) 
				|| "PM_ChannelExclusive".equalsIgnoreCase(codeTableName) || "PM_CJI_Prices".equalsIgnoreCase(codeTableName) || "PM_Class".equalsIgnoreCase(codeTableName) || "PM_Closures".equalsIgnoreCase(codeTableName) || "PM_Complexity".equalsIgnoreCase(codeTableName) || "PM_CostAgreement".equalsIgnoreCase(codeTableName) 
				|| "PM_FPORFS_SKU_TYPE".equalsIgnoreCase(codeTableName)  || "PM_Functionality".equalsIgnoreCase(codeTableName) || "PM_FaceColor".equalsIgnoreCase(codeTableName) || "PM_Factory_Prices".equalsIgnoreCase(codeTableName) ||
				"PM_FactoryStoreType".equalsIgnoreCase(codeTableName)|| "PM_IntroDate".equalsIgnoreCase(codeTableName)  || "PM_FSIntroDate".equalsIgnoreCase(codeTableName) || "PM_Functionality".equalsIgnoreCase(codeTableName) || "PM_Gender".equalsIgnoreCase(codeTableName) || "PM_HardwareColor".equalsIgnoreCase(codeTableName) || "PM_HandbagSize".equalsIgnoreCase(codeTableName) || "PM_keyRingDimensions".equalsIgnoreCase(codeTableName) ||
				"PM_LensType".equalsIgnoreCase(codeTableName) || "PM_LifecycleState".equalsIgnoreCase(codeTableName) || "PM_loggerloggergerpe".equalsIgnoreCase(codeTableName) || "PM_LONG_LEAD_TIME".equalsIgnoreCase(codeTableName) || "PM_Look".equalsIgnoreCase(codeTableName) || "PM_MaterStyle".equalsIgnoreCase(codeTableName) || "PM_Organizational_Panel".equalsIgnoreCase(codeTableName) || "PM_PlanExclusion".equalsIgnoreCase(codeTableName) || "PM_Print".equalsIgnoreCase(codeTableName) || "PM_PRODUCT_IDENTITY".equalsIgnoreCase(codeTableName) || "PM_Segmentation".equalsIgnoreCase(codeTableName) 
				|| "PM_Rounding".equalsIgnoreCase(codeTableName) || "PM_SAP_FLAG".equalsIgnoreCase(codeTableName) || "PM_SKUStatus".equalsIgnoreCase(codeTableName) || "PM_SpecialProduct".equalsIgnoreCase(codeTableName) || "PM_SubClass".equalsIgnoreCase(codeTableName) || "PM_SubCollection".equalsIgnoreCase(codeTableName) || "PM_ToeShape".equalsIgnoreCase(codeTableName) || "PM_ToeType".equalsIgnoreCase(codeTableName) || "PM_TypeOfSole".equalsIgnoreCase(codeTableName) 
				||  "PRODUCTTYPE".equalsIgnoreCase(codeTableName) ||"PM_Attitude".equalsIgnoreCase(codeTableName) || "PM_AttitudinalSegments".equalsIgnoreCase(codeTableName) || 
				"PM_Collaboration".equalsIgnoreCase(codeTableName) || "PM_Embellishment".equalsIgnoreCase(codeTableName) || "PM_Silhouette".equalsIgnoreCase(codeTableName)|| "MMSEASON".equalsIgnoreCase(codeTableName)
				|| "PM_HeelHeight".equalsIgnoreCase(codeTableName) || "PM_HeelShape".equalsIgnoreCase(codeTableName)
				|| "PM_FamilyGroup".equalsIgnoreCase(codeTableName) || "PM_Factory".equalsIgnoreCase(codeTableName)
				|| "PM_PriceDescription".equalsIgnoreCase(codeTableName) || "PM_BrandSubbrand".equalsIgnoreCase(codeTableName)
				|| "PM_Construction".equalsIgnoreCase(codeTableName) || "PM_ProductSegmentation".equalsIgnoreCase(codeTableName)
				|| "PM_Brand".equalsIgnoreCase(codeTableName) || "PM_Subbrand".equalsIgnoreCase(codeTableName)
				|| "PM_FashionSeason".equalsIgnoreCase(codeTableName) || (IBConstants.PM_Platform).equalsIgnoreCase(codeTableName)
				|| (IBConstants.PM_SEGMENTATIONSILHOUETTE.equalsIgnoreCase(codeTableName)))
			return true;
		
		String attStoreTables = Class;

		//String attStoreTables = PropertyReaderUtil.getValue("codetables.to.attributeStoreTable");
		if(attStoreTables != null && FormatHelper.hasContent(attStoreTables)){
			String[] tables = attStoreTables.split(",");
			for(int i=0; i<tables.length; i++){
				if(codeTableName.equalsIgnoreCase(tables[i]))
					return true;
			}
		}
		return isRegularAttributeStore;
	}
	
	public boolean isRegularMaterialAttributStore(String codeTableName){
		if ("FINISH".equalsIgnoreCase(codeTableName) || "MATERIALSUBTYPE".equalsIgnoreCase(codeTableName)
				|| "MATERIALTYPE".equalsIgnoreCase(codeTableName) || "CAPACITYTYPE".equalsIgnoreCase(codeTableName)
				|| "PRINTMETHOD".equalsIgnoreCase(codeTableName) || "MSFFPPASSFAIL".equalsIgnoreCase(codeTableName)
				|| "MSCStatus".equalsIgnoreCase(codeTableName) || "MMINTRODATE".equalsIgnoreCase(codeTableName)
				|| "MMHIDESEGMENTATION".equalsIgnoreCase(codeTableName)
				|| "MMHARVESTMETHOD".equalsIgnoreCase(codeTableName) || "MMGRAINTYPE".equalsIgnoreCase(codeTableName)
				|| "MaterialMasterStatus".equalsIgnoreCase(codeTableName)
				|| "LIMITATIONS".equalsIgnoreCase(codeTableName)
				|| "FitForPurposeStatus".equalsIgnoreCase(codeTableName)
				|| "FABRICCONTENT".equalsIgnoreCase(codeTableName) || "DYEMETHOD".equalsIgnoreCase(codeTableName)
				|| "DevelopmentTeams".equalsIgnoreCase(codeTableName) || "COSTTYPE".equalsIgnoreCase(codeTableName)
				|| "CommonNameSpecies".equalsIgnoreCase(codeTableName) || "UOM".equalsIgnoreCase(codeTableName)
				|| "SurfaceFinish".equalsIgnoreCase(codeTableName) || "MMTESTREGIME".equalsIgnoreCase(codeTableName)
				|| "MMBULKTESTREGIME".equalsIgnoreCase(codeTableName)
				|| "SCIENTIFICNAME".equalsIgnoreCase(codeTableName) || "QASTATUS".equalsIgnoreCase(codeTableName)
				|| "MM_CARE_CARD".equalsIgnoreCase(codeTableName) || "INCOTERMS".equalsIgnoreCase(codeTableName)
				|| "ExoticCommonName".equalsIgnoreCase(codeTableName)
				|| "MATCAPACITYGROUP".equalsIgnoreCase(codeTableName) || "Currency".equalsIgnoreCase(codeTableName)
				|| "PRINTMETHOD".equalsIgnoreCase(codeTableName) || "COUNTRYOFORIGIN".equalsIgnoreCase(codeTableName))
			return true;
		
		String attStoreTables = SubClass;
		//String attStoreTables = PropertyReaderUtil.getValue("codetables.to.materailAttributeStoreTable");
		if(attStoreTables != null && FormatHelper.hasContent(attStoreTables)){
			String[] tables = attStoreTables.split(",");
			for(int i=0; i<tables.length; i++){
				if(codeTableName.equalsIgnoreCase(tables[i]))
					return true;
			}
		}
	
			return false;
	}
	
	public void   updateIntroDateMaster(String display){
		if (!FormatHelper.hasContent(display))return;
		try{
			logger.info("updateIntroDateMaster old display "+ display);
			String []trucanteProductTypeArr = display.split(" ");
			display = trucanteProductTypeArr[0];
			String []displayArr = display.split("/");
			
			String newDisplay ="";
			for(int i=0; i< 2 ; i++){
			    int value = Integer.parseInt(displayArr[i]);
			    if(value <10)
			    	newDisplay = newDisplay+"0"+displayArr[i];
			    else
			    	newDisplay= newDisplay+displayArr[i];
			    newDisplay=newDisplay+"/";  	
			}
			newDisplay = newDisplay+displayArr[2];
			logger.info("updateIntroDateMaster new display "+ newDisplay);
			IntroDateMaster introDateMaster = new IntroDateMaster();
			introDateMaster.setIntroDate(newDisplay.trim());
			IntroDateMaster existing = introDateMasterRepository.getOne(newDisplay);
			//IntroDateMaster existing =  new PMDBObjectQuery().searchIntroDateMaster(newDisplay);
			if(existing == null){
				logger.info("updateIntroDateMaster new display "+ newDisplay);
				//PersistanctHelper.createRow(introDateMaster);
				introDateMasterRepository.saveAndFlush(introDateMaster);

			}
				
		}catch(Exception ex){
			ex.printStackTrace();
			logger.info("Error updateIntroDateMaster new display "+ display + " "+ex.getLocalizedMessage());
		}
		
	}
	public void printCodeTableRequest(CodeTableRequest request){
		logger.info("Entering to print the code table request for "+ request.getMessageID());
		
	}
	
	public void updateMappingTable(String deptOld, String deptNew, String codeTableName, String valueKey )throws Exception {
		logger.info("CodeTableServiceImpl.updateMappingTable : updating existing row deptOld "+ deptOld+ " new departent "+deptNew);
		List<String> deptList = new ArrayList<String>();
		List<String> deptToAdd = new ArrayList<String>();
		//List deptList = new ArrayList();
		//List deptToAdd = new ArrayList();
		String existingDept ="";
		if(deptOld!=null && deptNew!=null)
		{
			logger.info("CodeTableServiceImpl.updateMappingTable :either tables are not null");
			StringTokenizer tokenizer = new StringTokenizer(deptOld,";");
			while(tokenizer.hasMoreTokens())
			{
				String element = (String) tokenizer.nextElement();
				if(element!=null){
					if(!deptList.contains(element)){
						deptList.add(element);
						
					}
				}
			}
			logger.info("CodeTableServiceImpl.updateMappingTable :either tables are not old deptList " + deptList);
			StringTokenizer newTokenizer = new StringTokenizer(deptNew,";");
			while(newTokenizer.hasMoreTokens())
			{
				String newElement = (String) newTokenizer.nextElement();
				if(newElement!=null){
					if(deptList.contains(newElement)){
						deptList.remove(newElement);
					}
					deptToAdd.add(newElement);
				}
			}
			logger.info("CodeTableServiceImpl.updateMappingTable :either tables are not old deptList " + deptList);
			logger.info("CodeTableServiceImpl.updateMappingTable :either tables are not old deptToAdd " + deptToAdd);
			StringTokenizer newTokenizer2 = new StringTokenizer(deptOld,";");
			while(newTokenizer2.hasMoreTokens())
			{
				String existingElement = (String) newTokenizer2.nextElement();
				logger.info("CodeTableServiceImpl.updateMappingTable :either tables are not old existingElement " + existingElement);
				if(!FormatHelper.hasContent(existingDept) && !deptList.contains(existingElement))
					existingDept=existingElement;
				if(existingElement!=null){
					if(deptToAdd.contains(existingElement)){
						deptToAdd.remove(existingElement);
					}
				}
				logger.info("CodeTableServiceImpl.updateMappingTable :upeither tables are upadate deptToAdd " + deptToAdd);
			}
		}
		logger.info("CodeTableServiceImpl.updateMappingTable :upeither tables are upadate existingDept " + existingDept);
		for(int d=0;d<deptList.size();d++){
			String depart = (String) deptList.get(d);
			List<DeptCollectionSubCollection> existingDeptColl;
			List<DepartmentClassSubClass> esistingDeptClass;
			if(codeTableName.equalsIgnoreCase("COLLECTION")){
			logger.info("CodeTableServiceImpl.updateMappingTable : Deleteting department :"+depart + " and collection :"+valueKey +" from DeptCollectionSubCollectin");
			
			 existingDeptColl = deptSubCollectionRepository.findAll(valueKey, depart);
			//existingDeptColl = (List<DeptCollectionSubCollection>) new PMDBObjectQuery().searchDepartmentCollSubCollMappingColl(valueKey,depart);
			 if(existingDeptColl!=null){
				 ArrayList<DeptCollectionSubCollection> listToDelete = new ArrayList<DeptCollectionSubCollection> ();
				 listToDelete.addAll(existingDeptColl);
				 //PersistanctHelper.deleteRows(listToDelete);
				 for (DeptCollectionSubCollection deptDelete : listToDelete) {
						deptSubCollectionRepository.delete(deptDelete);
					}
				 logger.info("Delete success from DeptCollectionSubCollection....");
				 }else
					 logger.info("No mapping found in DeptCollectionSubCollection for "+depart + " and collection :"+valueKey);
			}else if(codeTableName.equalsIgnoreCase("PM_Class")){
				logger.info("CodeTableServiceImpl.updateMappingTable : Deleteting department :"+depart + " and Class :"+valueKey +" from DepartmentClassSubClass");
				esistingDeptClass = departmentSubClassRepository.findAll(valueKey, depart);
				//esistingDeptClass = (List<DepartmentClassSubClass>) new PMDBObjectQuery().searchDepartmentClassSubclassMappingColl(valueKey,depart);
				 logger.info("CodeTableServiceImpl.updateMappingTable : Deleteting department :"+depart + " and Class :"+valueKey +" from DepartmentClassSubClass exsiting data "+ esistingDeptClass);
				 if(esistingDeptClass!=null){
					 logger.info("CodeTableServiceImpl.updateMappingTable : Deleteting department :"+depart + " and Class :"+valueKey +" from DepartmentClassSubClass exsiting data "+ esistingDeptClass.size());
					 ArrayList<DepartmentClassSubClass> listToDelete = new ArrayList<DepartmentClassSubClass> ();
					 listToDelete.addAll(esistingDeptClass);
					 //PersistanctHelper.deleteRows(listToDelete);
					 for (DepartmentClassSubClass deptSubDelete : listToDelete) {
							departmentSubClassRepository.delete(deptSubDelete);
					}
					 logger.info("Delete success from DepartmentClass SubClass....");
				}else
					logger.info("No mapping found in DepartmentClass SubClass for "+depart + " and class :"+valueKey);
			}
		}
		if(codeTableName.equalsIgnoreCase("COLLECTION") && deptToAdd != null && deptToAdd.size()>0 && FormatHelper.hasContent(existingDept)){
			logger.info("CodeTableServiceImpl.updateMappingTable :updateing for new deparmtnets "+ deptToAdd + " using mapping from "+existingDept);
			List<DeptCollectionSubCollection> existingDeptColl;
			existingDeptColl = deptSubCollectionRepository.findAll(valueKey, existingDept);
			//existingDeptColl = (List<DeptCollectionSubCollection>)new PMDBObjectQuery().searchDepartmentCollSubCollMappingColl(valueKey,existingDept);
			if(existingDeptColl != null && existingDeptColl.size()> 0){
				logger.info("CodeTableServiceImpl.updateMappingTable :found rows  mapping for one of  exisiting departments "+ existingDept);
				//Iterator iter = existingDeptColl.iterator();
				Iterator<DeptCollectionSubCollection> iter = existingDeptColl.iterator();
				while(iter.hasNext()){
					DeptCollectionSubCollection collSubColl = (DeptCollectionSubCollection)iter.next();
					for(int i = 0; i < deptToAdd.size(); i++){
						String department = (String)deptToAdd.get(i);
						DeptCollectionSubCollection newMapping = new DeptCollectionSubCollection();
						DeptCollectionSubCollection_PK id = new DeptCollectionSubCollection_PK();
						id.setCode(collSubColl.getCompKey().getCode());
						id.setDepartment(department);
						newMapping.setCompKey(id);
						newMapping.setActionStatus("Create");
						newMapping.setCollection(collSubColl.getCollection());
						newMapping.setCollectionDesc(collSubColl.getCollectionDesc());
						newMapping.setSubcollection(collSubColl.getSubcollection());
						newMapping.setSubcollectionDesc(collSubColl.getSubcollectionDesc());
						String departmentDesc = getDepartmentMap().get(department).getDisplay2();
						newMapping.setDepartmentDesc(departmentDesc);
						newMapping.setOid(collSubColl.getOid());
						newMapping.setCreateDate(new Date());
						newMapping.setLastUpdateDate(new Date());
						newMapping.setStatus("Active");
						deptSubCollectionRepository.saveAndFlush(newMapping);
						//PersistanctHelper.createRow(newMapping);
					}
				}
				
			}
		}
		if(codeTableName.equalsIgnoreCase("PM_CLASS") && deptToAdd != null && deptToAdd.size()>0 && FormatHelper.hasContent(existingDept)){
			logger.info("CodeTableServiceImpl.updateMappingTable :Adding mapping for newly added departments "+ existingDept);
			List<DepartmentClassSubClass> existingDeptClassSubClass;
			//existingDeptClassSubClass = (List<DepartmentClassSubClass>)new PMDBObjectQuery().searchDepartmentClassSubclassMappingColl(valueKey,existingDept);
			existingDeptClassSubClass = departmentSubClassRepository.findAll(valueKey, existingDept);

			if(existingDeptClassSubClass != null && existingDeptClassSubClass.size()> 0){
				logger.info("CodeTableServiceImpl.updateMappingTable :found rows  mapping for one of  exisiting departments "+ existingDept);
				//Iterator iter = existingDeptClassSubClass.iterator();
				Iterator<DepartmentClassSubClass> iter = existingDeptClassSubClass.iterator();
				while(iter.hasNext()){
					DepartmentClassSubClass deptClassSubClass = (DepartmentClassSubClass)iter.next();
					logger.info("CodeTableServiceImpl.updateMappingTable :adding for  departments "+ deptClassSubClass.getClassCode()+ " " + deptClassSubClass.getSubClass());
					for(int i = 0; i < deptToAdd.size(); i++){
						String department = (String)deptToAdd.get(i);
						logger.info("CodeTableServiceImpl.updateMappingTable :adding for  departments "+ department);
						DepartmentClassSubClass newMapping = new DepartmentClassSubClass();
						DepartmentClassSubClass_PK id = new DepartmentClassSubClass_PK();
						id.setCode(deptClassSubClass.getCompKey().getCode());
						id.setDepartment(department);
						newMapping.setCompKey(id);
						newMapping.setStatus("Create");
						String departmentDesc = getDepartmentMap().get(department).getDisplay2();
						newMapping.setClassCode(deptClassSubClass.getClassCode());
						newMapping.setClassDesc(deptClassSubClass.getClassDesc());
						newMapping.setClassName(deptClassSubClass.getClassName());
						newMapping.setDeptName(departmentDesc);
						newMapping.setSubClass(deptClassSubClass.getSubClass());
						newMapping.setSubClassDesc(deptClassSubClass.getSubClassDesc());
						newMapping.setSubClassName(deptClassSubClass.getSubClassName());
						newMapping.setLastUpdateDate(new Date());
						newMapping.setStatus("Active");
						departmentSubClassRepository.saveAndFlush(newMapping);
						//PersistanctHelper.createRow(newMapping);
					}
				}
				
			}
		}
		
	}
	

}
