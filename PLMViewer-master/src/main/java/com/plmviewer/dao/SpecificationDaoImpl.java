/**
 * 
 */
package com.plmviewer.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.plmviewer.model.DocumentObjectRelationship;
import com.plmviewer.model.DocumentToFileLinkVo;

/**
 * @author uthanasekarapandian
 *
 */
@Repository
public class SpecificationDaoImpl implements SpecificationDao {
	@Autowired 
	SessionFactory sessionFactory;
	private static final Logger logger = LoggerFactory.getLogger(ProductDaoImpl.class); 

    @SuppressWarnings("unchecked")
	@Override
    @Transactional
    public List<Map<String,String>>  getSpecDetails(String specName){

 	   /*SELECT WTPARTMASTER.NAME,  FLEXSPECTOCOMPONENTLINK.COMPONENTTYPE,  FLEXSPECTOCOMPONENTLINK.IDA3A4
 	FROM FLEXSPECTOCOMPONENTLINK FLEXSPECTOCOMPONENTLINK , FLEXSPECIFICATION FLEXSPECIFICATION ,WTPARTMASTER WTPARTMASTER
 	WHERE FLEXSPECTOCOMPONENTLINK.SPECVERSION = 'A' AND FLEXSPECTOCOMPONENTLINK.IDA3A4 = FLEXSPECIFICATION.IDA3MASTERREFERENCE 
 	AND  WTPARTMASTER.IDA2A2 =FLEXSPECTOCOMPONENTLINK.IDA3B4 AND FLEXSPECIFICATION.ATT1='001 : MLN 090309 003'
 	UNION
 	SELECT WTDOCUMENTMASTER.NAME ,FLEXSPECTOCOMPONENTLINK.COMPONENTTYPE 
 	FROM FLEXSPECTOCOMPONENTLINK FLEXSPECTOCOMPONENTLINK , FLEXSPECIFICATION FLEXSPECIFICATION ,WTDOCUMENTMASTER WTDOCUMENTMASTER
 	WHERE FLEXSPECTOCOMPONENTLINK.SPECVERSION = 'A' AND FLEXSPECTOCOMPONENTLINK.IDA3A4 = FLEXSPECIFICATION.IDA3MASTERREFERENCE 
 	AND WTDOCUMENTMASTER.IDA2A2 =FLEXSPECTOCOMPONENTLINK.IDA3B4  AND FLEXSPECIFICATION.ATT1 ='001 : MLN 090309 003'    */
 	
String sql = "  SELECT NVL(FLEXSPECIFICATION.partPrimaryImageUrl,'')  as partPrimaryImageUrl, WTPARTMASTER.partMasterName as  specName,WTPARTMASTER.ida2a2 as ida2a2, " +
		"  NVL(FLEXSPECTOCOMPONENTLINK.componentType,'') as componentType "
	 	+" FROM FlexSpecToComponentLinkVo FLEXSPECTOCOMPONENTLINK , FlexSpecificationVo FLEXSPECIFICATION ,WTPartMasterVo WTPARTMASTER "
	 	+" WHERE FLEXSPECTOCOMPONENTLINK.specVersion = 'A' AND FLEXSPECTOCOMPONENTLINK.ida3a4 = FLEXSPECIFICATION.ida3MasterReference " 
	 	+" AND  WTPARTMASTER.ida2a2 =FLEXSPECTOCOMPONENTLINK.ida3b4 AND FLEXSPECIFICATION.att1='"+specName +"'" ;
String sql2 = " SELECT NVL(FLEXSPECIFICATION.partPrimaryImageUrl,'') as partPrimaryImageUrl ,WTDOCUMENTMASTER.docMasterName as specName ,WTDOCUMENTMASTER.ida2a2 as ida2a2, " +
	 	" NVL( FLEXSPECTOCOMPONENTLINK.componentType , '' ) as componentType "
	 	+" FROM FlexSpecToComponentLinkVo FLEXSPECTOCOMPONENTLINK , FlexSpecificationVo FLEXSPECIFICATION ,WTDocumentMasterVo WTDOCUMENTMASTER " 
	 	+" WHERE FLEXSPECTOCOMPONENTLINK.specVersion = 'A' AND FLEXSPECTOCOMPONENTLINK.ida3a4 = FLEXSPECIFICATION.ida3MasterReference "
	 	+" AND WTDOCUMENTMASTER.ida2a2 =FLEXSPECTOCOMPONENTLINK.ida3b4  AND FLEXSPECIFICATION.att1 ='"+specName +"'   ";

 	logger.info("getSpecDetails Starts");
 	List<Object[]> specDetailsList = sessionFactory.getCurrentSession().createQuery(sql).list();
	
List<Map<String,String>> specComponentList = null;
 	
 	List<Object[]> specDetailsList2 = sessionFactory.getCurrentSession().createQuery(sql2).list();
 	 specDetailsList.addAll(specDetailsList2);
 			
 		logger.info("getSpecDetails size() " + specDetailsList.size());

 		specComponentList= new ArrayList<Map<String,String>>();	
			
			if (specDetailsList != null && specDetailsList.size() > 0) {
				
	  			java.util.Iterator<Object[]> srcIter = specDetailsList.iterator();
	  			while (srcIter.hasNext()) {
	  				Map<String, String> specDetails = new HashMap<String, String>();
	  				Object[] srcObject = (Object[]) srcIter.next();
	  				specDetails.put("partPrimaryImageUrl", (String) srcObject[0]);
	  				specDetails.put("specName", (String) srcObject[1]);
	  				specDetails.put("ida2a2", (String) srcObject[2]);
	  				specDetails.put("componentType", (String) srcObject[3]);
	  				
	  				specComponentList.add(specDetails);
	  			}
	  		}
	  	
		
 	logger.info("getSpecDetails Ends");
		return specComponentList;
		
 
 }
       @SuppressWarnings("unchecked")
	@Transactional
       public List<DocumentObjectRelationship>  getSpecDocumentDetails(String specName){

    		
   String sql = "   select docObjRelShip.DocumentName \"documentName\",docObjRelShip.DocumentType \"documentType\","+
        " docObjRelShip.DocumentTypeId \"documentTypeId\",docObjRelShip.primaryFileName \"primaryFileName\" from DOCUMENTOBJECTRELATIONSHIP docObjRelShip,FLEXSPECIFICATION flexSpec where "+
      " docObjRelShip.referringobject ='FlexSpecification' and docObjRelShip.refobjectcolumvalue=flexSpec.ida3masterreference "+  
      " and flexSpec.att1 ='"+specName +"'   ";

    	logger.info("getSpecDocumentDetails Starts");
    	   	
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
    	List<DocumentObjectRelationship> specDocList =   query.setResultTransformer( 
				Transformers.aliasToBean(DocumentObjectRelationship.class)).list();

    		logger.info("getSpecDocumentDetails size() " + specDocList.size());

    	logger.info("getSpecDocumentDetails Ends");
   		return specDocList;
   		
    
    }


}
