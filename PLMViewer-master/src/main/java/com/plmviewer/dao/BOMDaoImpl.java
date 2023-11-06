/**
 * 
 */
package com.plmviewer.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.plmviewer.model.BOMLink;
import com.plmviewer.model.BOMPart;
import com.plmviewer.model.SkuInfoVo;
import com.plmviewer.util.BOMConstant;


/**
 * @author uthanasekarapandian
 *
 */
@Repository
public class BOMDaoImpl implements BOMDao{

	 @Autowired
	    SessionFactory sessionFactory;
	    private static final Logger logger = LoggerFactory.getLogger(BOMDaoImpl.class);

	    @Transactional
	    @SuppressWarnings("unchecked")
	    public List<BOMPart> getBOMPart(String prodIda3MasterRef) {
		 logger.info("getBOMPart Starts");
	        String sql = BOMConstant.flexBomPartDetails + " and  p.ida3masterreference = '"+prodIda3MasterRef+"'  order by f.att1" ;

	        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
	        List<BOMPart> bomPart= query.setResultTransformer(
	                Transformers.aliasToBean(BOMPart.class)).list();
	      
	        logger.info("getBOMPart Ends");
			return bomPart;  		
	 }
	    
	    @Transactional
	    @SuppressWarnings("unchecked")
	public List<BOMLink> getBOMLink(String prodIda3MasterRef, BigDecimal bomPartMasterReference) {
		 logger.info("getBOMLink Starts");
	        String sql = BOMConstant.flexBomLinkDetails + " AND  (FLEXBOMLINK.IDA3E5 = '0' OR FLEXBOMLINK.IDA3E5 IN " +
	        		"(SELECT SKUAREV.IDA3MASTERREFERENCE FROM SKUAREV,  PRODAREV WHERE SKUAREV.ATT6 <> 'cancelled' " +
	        		" AND SKUAREV.IDA3A12 = '"+prodIda3MasterRef+"' )) AND FLEXBOMLINK.IDA3A5 = "+bomPartMasterReference+"  ORDER BY FLEXBOMLINK.SORTINGNUMBER, " +
	        		" FLEXBOMLINK.DROPPED  DESC ";

	        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
	        List<BOMLink> bomLink = query.setResultTransformer(
	                Transformers.aliasToBean(BOMLink.class)).list();

	      
	        logger.info("getBOMLink Ends");
			return bomLink;  
		
	}

	  	@Transactional
	    @SuppressWarnings("unchecked")
	  	public HashMap<String,String> getskuMap(String productMaserID) {

	        logger.info("getskuDetails Starts");
	        System.out.println("getskuDetails");
	        String column = "";
	       
	        String skuquery = " select sku.skuIda3MasterReference,sku.skuStyleName 	from SKUaRevVo sku WHERE "
	                + " sku.ida3a12 = '" + productMaserID + "' AND "
	                + "(sku.skuBomStatus <>'cancelled' or sku.skuBomStatus is null) AND skuStyleName is not null ";

	        Query query = sessionFactory
	                .getCurrentSession()
	                .createQuery(skuquery);


	    	List<Object[]> skuList = query.list();
	  		logger.info("getskuDetails size() " + skuList.size());

				
	  		HashMap<String, String> skuMap = new HashMap<String, String>();
				if (skuList != null && skuList.size() > 0) {
					
		  			java.util.Iterator<Object[]> srcIter = skuList.iterator();
		  			while (srcIter.hasNext()) {
		  				Object[] srcObject = (Object[]) srcIter.next();
		  				skuMap.put((String) srcObject[0],(String) srcObject[1]);
		  				
		  			}
		  		}
		  	
			
	    	logger.info("getskuDetails Ends");
			return skuMap;
	  		
	    }

		@Override
		@Transactional
	    @SuppressWarnings("unchecked")
		public List<SkuInfoVo> getSkuInfo(String prodIda3MasterRef) {
			
			  logger.info("getSkuInfo Starts");
		        System.out.println("getSkuInfo with = " + prodIda3MasterRef);
		        try {
		        	
		        	 String sql = BOMConstant.skuInfoDetails + prodIda3MasterRef +"and s.att1 <> 'null'" ;
		        	 System.out.println(sql);

				        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
				        List<SkuInfoVo>  skuInfoVos= query.setResultTransformer(Transformers.aliasToBean(SkuInfoVo.class)).list();
				        System.out.println("Sku info size is ===="+skuInfoVos.size());

				        return skuInfoVos;
					
				} catch (Exception e) {
					System.out.println("Error occured while fetching  sku information : "+e);
					e.printStackTrace();
				}
		       
		        
			
			return null;
		}
	    

	    


}
