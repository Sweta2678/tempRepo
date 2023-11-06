/**
 * 
 */
package com.plmviewer.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plmviewer.dao.BOMDao;
import com.plmviewer.dao.BOMDaoImpl;
import com.plmviewer.exceptions.BusinessException;
import com.plmviewer.model.BOMLink;
import com.plmviewer.model.BOMModel;
import com.plmviewer.model.BOMPart;
import com.plmviewer.model.SkuInfoVo;
import com.plmviewer.util.FlexObject;
import com.plmviewer.util.PLMUtil;
import com.plmviewer.util.SortHelper;

import ch.qos.logback.core.net.SyslogOutputStream;

/**
 * @author uthanasekarapandian
 *
 */
@Service("bomService")
public class BOMServiceImpl implements BOMService{
	@Autowired
	BOMDao bomDao;
	
    private static final Logger logger = LoggerFactory.getLogger(BOMServiceImpl.class);
    public BOMModel getBOMdata(String prodIda3MasterRef,String bomPartMasterID) throws BusinessException {
    	System.out.println("getBOMPart Starts"+bomPartMasterID + " prodIda3MasterRef=="+prodIda3MasterRef );
    	BOMModel bomModel = new BOMModel();
    	BOMPart currentBomPart=null;
    	BigDecimal currentBomPartMasterID = null;
		List<BOMPart> bomPartList = bomDao.getBOMPart(prodIda3MasterRef);
		if(bomPartList == null ||bomPartList.size() <=0){
			return bomModel;
		}
		else{
			HashMap<String,String> bomNameMap = new HashMap<String,String>();
			bomModel.setSectionMap(new PLMUtil().getBomSectionMap());
			
			 System.out.println("Before calling setSku");
		     bomModel.setSkuInfoVos(getSkuInformation(prodIda3MasterRef));
		     System.out.println("After calling set");

			for(BOMPart bomPart : bomPartList){
				BigDecimal bomIda3masterRef = bomPart.getBomPartMasterReference();
				String bomMasterID = bomIda3masterRef.toString();
				String bomName = bomPart.getBomPartName();
				bomNameMap.put(bomIda3masterRef.toString(),bomName);
				if(bomPartMasterID == null || bomPartMasterID == "" ){
					if(bomName.startsWith("001")){
						currentBomPart= bomPart;
						bomModel.setCurrentBOM(bomIda3masterRef.toString());
						currentBomPartMasterID= bomIda3masterRef;
					}
				}else if(bomPartMasterID != null &&  !"".equals(bomPartMasterID.trim()) &&  !"0".equals(bomPartMasterID.trim()) && bomMasterID.equals(bomPartMasterID) ){
					currentBomPart= bomPart;
					bomModel.setCurrentBOM(bomIda3masterRef.toString());
					currentBomPartMasterID= bomIda3masterRef;
				}
			}
			bomModel.setBomNameMap(bomNameMap);
			System.out.println("BoNameMap "+bomModel.getBomNameMap() );
		}
		bomModel.setSectionOrder(getSectionOrder());
		ArrayList<String> columnOrder = new ArrayList<String>();
		ArrayList<String>sectionOrder = getSectionOrder();
			 columnOrder.add("DC Material Ref"); columnOrder.add("Placement");columnOrder.add("CM#");columnOrder.add("Material"); columnOrder.add("Supplier"); 

		 
		 bomModel.setColumnOrder(columnOrder);
		 
		if(currentBomPartMasterID != null && !currentBomPartMasterID.equals("0")) {

				BigDecimal bomIda3masterRef = currentBomPartMasterID;
				String bomPartName = currentBomPart.getBomPartName();
				List<BOMLink> bomLinkList = bomDao.getBOMLink(prodIda3MasterRef, bomIda3masterRef);
			  try {
					List<FlexObject> bomMapList =  convertObjectToFlexObject(bomLinkList);
					Vector bomFlexOBjFilter = filterWIPOnly(bomMapList);
					HashMap<String,Collection<FlexObject>> groupedBOMMap = SortHelper.groupIntoCollections(bomFlexOBjFilter,"DIMENSIONNAME");
					Collection <FlexObject>productLevelBom = groupedBOMMap.get("");

					if(productLevelBom == null ||productLevelBom.size() < 1 )
						return null;
					Collection <FlexObject>skuLevelBOM = groupedBOMMap.get(":SKU");
					HashMap<String,Collection<FlexObject>> skuLevelBOMMapByBranchID = new HashMap<String,Collection<FlexObject>>() ;
					if(skuLevelBOM != null && skuLevelBOM.size()>0 ){
						skuLevelBOMMapByBranchID = SortHelper.groupIntoCollections(skuLevelBOM,"BRANCHID");
						//System.out.println("GOT HERE BOMMODEL "+bomModel.getCurrentBOM()+ " skuLevelBOMMapByBranchID "+skuLevelBOMMapByBranchID.size());
					}
					HashMap<String,String> nonCancelledSKU = bomDao.getskuMap(prodIda3MasterRef);
					if(nonCancelledSKU != null && nonCancelledSKU.size()>0){
						for(String key: nonCancelledSKU.keySet()){
							String skuNameValue= nonCancelledSKU.get(key);
							columnOrder.add(skuNameValue);
						}
					}
					Collection<String>skuNameOrdered;
					PLMUtil util = new PLMUtil();
					Vector <FlexObject>processedBom = new Vector();
				     for (FlexObject flexObj : productLevelBom){
				    	 ArrayList<String> skuWithLinksFound = new ArrayList<String>();
				    	 String branchId = flexObj.getString("BRANCHID");
				    	 System.out.println("branchID for product "+ branchId);
				    	 String materialId = flexObj.getString("ida3b5");
				    	 String colorId = flexObj.getString("ida3d5");
				    	 String matColorID = flexObj.getString("ida3g5");
				    	 String materialDescription = flexObj.getString("att3");
				    	 String colorDescription =flexObj.getString("att1");
				    	 String placement = SortHelper.formatString(flexObj.getString("att5"));
				    	 String supplier = flexObj.getString("supplierName");
				    	 String section = flexObj.getString("att6");
				    	 String sortingNumber= flexObj.getString("sortingNumber");
				    	 if(supplier ==null || "PlaceHolder".equalsIgnoreCase(supplier))
				    		 supplier ="";
				    	 String partNumber="";
				    	  FlexObject processedObject = new FlexObject();
				    	  processedObject.setData("branchID",branchId);
				    	  processedObject.setData("sortingNumber",sortingNumber);
				    	  processedObject.setData("section",section);
				    	  processedObject.setData("Supplier",supplier);
				    	  processedObject.setData("Placement", placement);
				    	  processedObject.setData("SORTINGNUMBER", sortingNumber);
				    	  processedObject.setData("DC Material Ref", SortHelper.formatString(flexObj.getString("att4")));
				    	 if(!materialId.equals("5222")){
				    		 materialDescription= flexObj.getString("materialName");
				    		 partNumber = flexObj.getString("partNumber");
				    	 }
				    	 if(colorId != null && !"0".equals(colorId) && !"".equals(colorId)){
				    		 colorDescription= flexObj.getString("lcsColorAtt1");
				    	 }
				    	 processedObject.setData("Material",materialDescription);
				    	 processedObject.setData("CM#",partNumber);
				    	 processedObject.setData("colorDescription", colorDescription);
				    	 Collection <FlexObject>skuLeveBomLinksForBranchId  = skuLevelBOMMapByBranchID.get(branchId);
				    	 if(skuLeveBomLinksForBranchId != null && skuLeveBomLinksForBranchId.size()>0){
					    	 for(FlexObject skuLink : skuLeveBomLinksForBranchId){
					    		 String skuId = skuLink.getString("ida3e5");
					    		 String skuName = nonCancelledSKU.get(skuId);
					    		 String skuColor =skuLink.getString("att1");
					    		 if(skuLink.getString("ida3d5") != null && !"0".equals(skuLink.getString("ida3d5")) && !"".equals(skuLink.getString("ida3d5").trim())){
					    			 skuColor= skuLink.getString("LCSCOLORATT1");
						    	 }
					    		 if(skuColor == null || skuColor.trim().equals(""))
					    			 skuColor= colorDescription;
					    		 processedObject.setData(skuName, skuColor);
					    		 skuWithLinksFound.add(skuId);
					    	 }
				    	 }
				    	 for(String  key:nonCancelledSKU.keySet()){
				    		 if(skuWithLinksFound.indexOf(key) < 0){
				    			 String skuName = nonCancelledSKU.get(key);
				    			 processedObject.setData(skuName, colorDescription);
				    		 }
				    	 }
				    	 processedBom.add(processedObject);
				     }
				     HashMap<String,Collection<FlexObject>> processedBOMGroupIntoCollections = SortHelper.groupIntoCollections(processedBom,"section");
				     LinkedHashMap<String,Collection<LinkedHashMap<String,String>>>bomLinkdataMap =  new LinkedHashMap<String,Collection<LinkedHashMap<String,String>>>();
				   
				     for(String section : sectionOrder){
				    	 Collection<FlexObject>collForSection=processedBOMGroupIntoCollections.get(section);
				    	 if(collForSection != null){
					    	 collForSection = SortHelper.sortFlexObjects(collForSection, "SORTINGNUMBER");
					    	 Vector<LinkedHashMap<String,String>> sortedCollForSection = new Vector<LinkedHashMap<String,String>>();
					    	 sortedCollForSection= SortHelper.convertFlexObjectsIntoMap(collForSection,columnOrder);
					    	 bomLinkdataMap.put(section, sortedCollForSection);
				    	 }
				    	 
				     }
				     bomModel.setDataMap(bomLinkdataMap);
				     
				     
				} catch (IllegalAccessException e) {
					e.printStackTrace();
					throw new BusinessException(e.getMessage());
					
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
					throw new BusinessException(e.getMessage());
				} catch (InvocationTargetException e) {
					e.printStackTrace();
					throw new BusinessException(e.getMessage());
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException(e.getMessage());
				}
		
		 }
		
		System.out.println("returning bommode"+ bomModel.getSkuInfoVos().size());
		return bomModel;
		
	}

 public static List<FlexObject> convertObjectToFlexObject(List<BOMLink> bomLinkList) throws 
 IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {

		List<FlexObject> bomMapList = new ArrayList<FlexObject>();
		for (Object bomLink : bomLinkList) {

			Method[] methods = bomLink.getClass().getMethods();
			FlexObject flexObj = new FlexObject();
			Map<String, Object> map = new HashMap<String, Object>();
			for (Method m : methods) {
				if (m.getName().startsWith("get")
						&& !m.getName().startsWith("getClass")) {
					Object value = (Object) m.invoke(bomLink);
					flexObj.put(m.getName().substring(3), (Object) value);
					//map.put(m.getName().substring(3), (Object) value);
				}
			}
			//bomMapList.add(map);
			bomMapList.add(flexObj);
		}
		return bomMapList;

	}
	 
	 private static Vector filterWIPOnly(List<FlexObject> bomMapList)
	    {
	        Vector vector = new Vector();
	        Iterator iterator = bomMapList.iterator();
	        Object obj = null;
	        Hashtable hashtable = new Hashtable();
	        ArrayList arraylist = new ArrayList();
	        do
	        {
	            if(!iterator.hasNext())
	                break;
	            FlexObject flexobject = (FlexObject)iterator.next();
	            if(!flexobject.getBoolean("wip") && !flexobject.getBoolean("dropped"))
	                hashtable.put(flexobject.getString("dimensionId"), flexobject);
	             //if(flexobject.getBoolean("FLEXBOMLINK.DROPPED"))
	            //     hashtable.remove(flexobject.getString("FLEXBOMLINK.DIMENSIONID"));
	            if(flexobject.getBoolean("dropped") && flexobject.getBoolean("wip"))
	                arraylist.add(flexobject.getString("branchId"));
	        } while(true);
	        iterator = bomMapList.iterator();
	        do
	        {
	            if(!iterator.hasNext())
	                break;
	            FlexObject flexobject1 = (FlexObject)iterator.next();
	            if(arraylist.contains(flexobject1.getString("branchId")))
	                hashtable.remove(flexobject1.getString("dimensionId"));
	            else
	            if(flexobject1.getBoolean("wip"))
	                hashtable.put(flexobject1.getString("dimensionId"), flexobject1);
	        } while(true);
	        return new Vector(hashtable.values());
	    }
	 
	 
	 public BOMModel getDummyData(String productId, String bomPartId)throws BusinessException{
		 BOMModel model = new BOMModel();
		 try{
		
		 HashMap<String,String> bomNameMap = new HashMap<String,String>();
	     bomDao.getBOMPart("59420815");
		 //System.out.println(nonCancelledSKU);
		 bomNameMap.put("123456", "001:BOM1");
		 bomNameMap.put("12345690", "002:BOM2");
		 model.setBomNameMap(bomNameMap);
		 if(bomPartId != null && !"0".equals(bomPartId) && !"".equals(bomPartId.trim())){
			 model.setCurrentBOM(bomPartId);
		 }else
		 {
			 model.setCurrentBOM("123456");
		 }
		 LinkedHashMap <String,Collection<LinkedHashMap<String,String>>> dataForSections = new LinkedHashMap <String,Collection<LinkedHashMap<String,String>>>();
		 Vector<LinkedHashMap<String,String>> collectionForSection=new Vector<LinkedHashMap<String,String>>();
		 LinkedHashMap<String,String> valueMap = new LinkedHashMap<String,String>();
		 ArrayList<String> columnOrder = new ArrayList<String>();
		 columnOrder.add("DC Material Ref"); columnOrder.add("Placement");columnOrder.add("CM#");columnOrder.add("Material"); columnOrder.add("Supplier"); 
		 columnOrder.add("SBWBK");columnOrder.add("SKHMA");
		 model.setColumnOrder(columnOrder);
		 valueMap.put("Material", "24cm Sig C Reps Lightweight CVC - Light KnitRex Rabbit 1.0 mm (per Set)");
		 valueMap.put("DC Material Ref", "A");
		 valueMap.put("Placement", "Test1");
		 valueMap.put("Supplier", "Multi Source");
		 valueMap.put("SBWBK", "MAHOGANY - 202");
		 valueMap.put("SKHMA", "BLACK - 001");
		 collectionForSection.add(valueMap);
		 valueMap = new LinkedHashMap<String,String>();
		 valueMap.put("Material", "Giant-Up Buck - 1.6-1.8mm");
		 valueMap.put("DC Material Ref", "B");
		 valueMap.put("Placement", "Test2");
		 valueMap.put("Supplier", "Giant Up");
		 valueMap.put("SBWBK", "N/A");
		 valueMap.put("SKHMA", "BORDEAUX - BOR");
		 collectionForSection.add(valueMap);
		 dataForSections.put("shell", collectionForSection);
		 
		 collectionForSection=new Vector<LinkedHashMap<String,String>>();
		 valueMap = new LinkedHashMap<String,String>();
		 valueMap.put("Material", "24cm Sig C Reps Lightweight CVC - Light KnitRex Rabbit 1.0 mm (per Set)");
		 valueMap.put("DC Material Ref", "C");
		 valueMap.put("Placement", "Test3");
		 valueMap.put("Supplier", "Multi Source");
		 valueMap.put("SBWBK", "MAHOGANY - 202");
		 valueMap.put("SKHMA", "BLACK - 001");
		 collectionForSection.add(valueMap);
		 dataForSections.put("trim", collectionForSection);
		 
		 if("12345690".equals(bomPartId)){
			 collectionForSection=new Vector<LinkedHashMap<String,String>>();
			 valueMap = new LinkedHashMap<String,String>();
			 valueMap.put("Material", "24cm Sig C Reps Lightweight CVC - Light KnitRex Rabbit 1.0 mm (per Set)");
			 valueMap.put("DC Material Ref", "D");
			 valueMap.put("Placement", "Test4");
			 valueMap.put("Supplier", "Multi Source");
			 valueMap.put("SBWBK", "MAHOGANY - 202");
			 valueMap.put("SKHMA", "BLACK - 001");
			 collectionForSection.add(valueMap);
			 dataForSections.put("hardware", collectionForSection);
		 }
		 
		 model.setDataMap(dataForSections);
	     model.setSectionMap(new PLMUtil().getBomSectionMap());
	     ArrayList<String>bomSectionOrder = getSectionOrder();
	     model.setSectionOrder(bomSectionOrder);
		 }catch(Exception ex){
			 ex.printStackTrace();
			 throw new BusinessException(ex.getMessage());
		 }
		 
		 return model;
	 }

    private ArrayList<String>getSectionOrder(){
    	 ArrayList<String>list = new ArrayList<String>();
    	 list.add("shell");
    	 list.add("trim");
    	 list.add("facings");
    	 list.add("interior");
    	 list.add("lining");
    	 list.add("hardware");
    	 list.add("thread");
    	 list.add("mtm");
    	 list.add("misc");
    	 list.add("collateral");
    	 list.add("packaging");
    	 return list;
    }

	public List<SkuInfoVo> getSkuInformation(String prodIda3MasterRef) throws BusinessException {
		System.out.println("I ma in getSkuInfo");
		List<SkuInfoVo> skuInfoVos= bomDao.getSkuInfo(prodIda3MasterRef);
		System.out.println("Sku size is" + skuInfoVos.size());
		return skuInfoVos;
		
	}


}