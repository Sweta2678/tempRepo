/**
 * 
 */
package com.plmviewer.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.plmviewer.model.DocumentSearchResult;
import com.plmviewer.model.ImageSearchVo;
import com.plmviewer.model.ImagesPageToDocLinkVo;
import com.plmviewer.util.ImagesConstant;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author uthanasekarapandian
 *
 */
@Repository
public class ImagesDaoImpl implements ImagesDao {
	@Autowired 
	SessionFactory sessionFactory;
	private static final Logger logger = LoggerFactory.getLogger(ImagesDaoImpl.class);
	
	 @Transactional
	    @SuppressWarnings("unchecked")
	    public List<ImagesPageToDocLinkVo> getImagesSearchResultList(ImageSearchVo search) {
	        logger.info("getImagesSearchResultList Starts");
	        String whereclause = "  imglink.ownermasterid = p.ida3masterreference and "
	                + " imglink.documentmasterid = doc.ida3masterreference ";

	        if (search.getStylename() != null && search.getStylename().trim().length() > 0) {

	            if (search.getStylename().trim().contains("*")) {
	                whereclause = whereclause + " and p.att1 like '" + whereLikeClause(search.getStylename()) + "'";
	            } else {
	                whereclause = whereclause + " and p.att1 = '" + search.getStylename() + "'";
	            }

	        }
	        if (search.getStylenumber() != null && search.getStylenumber().trim().length() > 0) {

	            if (search.getStylenumber().trim().contains("*")) {
	                whereclause = whereclause + " and p.att44 like '" + whereLikeClause(search.getStylenumber()) + "'";
	            } else {
	                whereclause = whereclause + " and p.att44 = '" + search.getStylenumber() + "'";
	            }

	        }
	        if (search.getCollection() != null && search.getCollection().trim().length() > 0) {
	            whereclause = whereclause + " and p.att13 = '" + search.getCollection() + "'";
	        }
	        if (search.getSubcollection() != null && search.getSubcollection().trim().length() > 0) {
	            whereclause = whereclause + " and p.att45 = '" + search.getSubcollection() + "'";
	        }
	        if (search.getStyleclass() != null && search.getStyleclass().trim().length() > 0) {
	            whereclause = whereclause + " and p.att50 = '" + search.getStyleclass() + "'";
	        }
	        if (search.getSubclass() != null && search.getSubclass().trim().length() > 0) {
	            whereclause = whereclause + " and p.att51 = '" + search.getSubclass() + "'";
	        }

	        if (search.getDepartment() != null && search.getDepartment().trim().length() > 0) {
	            whereclause = whereclause + " and p.att49 = '" + search.getDepartment() + "'";
	        }

	       if (search.getFsintrodate() != null && search.getFsintrodate().trim().length() > 0) {
	            whereclause = whereclause + " and p.att24 = '" + search.getFsintrodate() + "'";
	        }

	        if (search.getIntrodate() != null && search.getIntrodate().trim().length() > 0) {
	            whereclause = whereclause + " and p.att27 = '" + search.getIntrodate() + "'";
	        }

	        if (search.getPagename() != null && search.getPagename().trim().length() > 0) {
	        	  if (search.getPagename().trim().contains("*")) {
		                whereclause = whereclause + " and  imglink.documentname like '" + whereLikeClause(search.getPagename()) + "'";
		            } else {
		                whereclause = whereclause + " and  imglink.documentname = '" + search.getPagename() + "'";
		            }
	        }
	        
	        if (search.getPagetype() != null && search.getPagetype().trim().length() > 0) {
	            whereclause = whereclause + " and  imglink.pagelayout = '" + search.getPagetype() + "'";
	        }
	        
	        if (search.getCreatedfrom() != null && search.getCreatedfrom().trim().length() > 0 && 
	        		search.getCreatedto() != null && search.getCreatedto().trim().length() > 0  ) {
	        	
	            whereclause = whereclause + " and doc.createstampa2 between  to_date('" + search.getCreatedfrom() + "','DD-MM-YY') " +
	            		" and to_date('" +search.getCreatedto()+"','DD-MM-YY') ";
	        }
	        
	        if (search.getCreatedfrom() != null && search.getCreatedfrom().trim().length() > 0   && ( (
	        		search.getCreatedto() != null && search.getCreatedto().trim().length() == 0) || 
	        		search.getCreatedto() == null )   ) {
	              whereclause = whereclause + " and doc.createstampa2 >= to_date('" + search.getCreatedfrom() +"','DD-MM-YY') " ;
	        }	        
	        if (search.getCreatedto() != null && search.getCreatedto().trim().length() > 0   && ( (
	        		search.getCreatedfrom() != null && search.getCreatedfrom().trim().length() == 0) || 
	        		search.getCreatedfrom() == null )   ) {
	            whereclause = whereclause + " and doc.createstampa2 <= to_date('" + search.getCreatedto() +"','DD-MM-YY') ";
	        }
	        if (search.getUpdatedfrom() != null && search.getUpdatedfrom().trim().length() > 0 && 
	        		search.getUpdatedto() != null && search.getUpdatedto().trim().length() > 0  ) {
	        	
	            whereclause = whereclause + " and doc.updatestampa2 between to_date('" + search.getUpdatedfrom() + "','DD-MM-YY') " +
	            		" and to_date('" + search.getUpdatedto() +"','DD-MM-YY') ";
	        }
            
	        if (search.getUpdatedfrom() != null && search.getUpdatedfrom().trim().length() > 0   && ( (
	        		search.getUpdatedto() != null && search.getUpdatedto().trim().length() == 0) || 
	        		search.getUpdatedto() == null )   ) {
	        	
	            whereclause = whereclause + " and doc.updatestampa2 >= to_date('" + search.getUpdatedfrom() +"','DD-MM-YY') ";
	        }
	        
	        if (search.getUpdatedto() != null && search.getUpdatedto().trim().length() > 0   && ( (
	        		search.getUpdatedfrom() != null && search.getUpdatedfrom().trim().length() == 0) || 
	        		search.getUpdatedfrom() == null )   ) {
	        	
	            whereclause = whereclause + " and doc.updatestampa2 <= to_date('" + search.getUpdatedto() +"','DD-MM-YY') ";
	        }
	        
	        Query query = sessionFactory.getCurrentSession().createSQLQuery(ImagesConstant.imgSearch + whereclause);

	        List<ImagesPageToDocLinkVo> imagesPageToDocLinkVoList = query.setResultTransformer(
	                Transformers.aliasToBean(ImagesPageToDocLinkVo.class)).list();

	        logger.info("getDocumentSearchResultList size() " + imagesPageToDocLinkVoList.size());

	        logger.info("getImagesSearchResultList Ends");
	        return imagesPageToDocLinkVoList;
	    }

	
	@Transactional
	@SuppressWarnings("unchecked")
	public List<ImagesPageToDocLinkVo> getProductImagesList(BigDecimal ida3masterRef){
		logger.info("getProductImagesList Starts");
		String andClause = "  imglink.ownermasterid = p.ida3masterreference and " +
				" imglink.documentmasterid = doc.ida3masterreference and  p.ida3masterreference= '"+ida3masterRef+"' ";
         Query query = sessionFactory.getCurrentSession().createSQLQuery(ImagesConstant.imgSearch+andClause);
		
		List<ImagesPageToDocLinkVo> imagesPageToDocLinkVoList =  query.setResultTransformer( 
				Transformers.aliasToBean(ImagesPageToDocLinkVo.class)).list();
		
		logger.info("getProductImagesList size() " + imagesPageToDocLinkVoList.size());
		
		logger.info("getProductImagesList Ends");
		return imagesPageToDocLinkVoList;
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public List<ImagesPageToDocLinkVo> getProductSpecImagesList(BigDecimal docMasterId){
		logger.info("getProductSpecImagesList Starts");
		String andClause = "  where documentmasterid ="+docMasterId+" ";
         Query query = sessionFactory.getCurrentSession().createSQLQuery(ImagesConstant.prodSpecImgList+andClause);
		
		List<ImagesPageToDocLinkVo> imagesPageToDocLinkVoList =  query.setResultTransformer( 
				Transformers.aliasToBean(ImagesPageToDocLinkVo.class)).list();
		
		logger.info("getProductSpecImagesList size() " + imagesPageToDocLinkVoList.size());
		
		logger.info("getProductSpecImagesList Ends");
		return imagesPageToDocLinkVoList;
	}

    @Override
    @Transactional
    public Map<String, Map<String, String>> getProductAttributeList() {
        logger.info("getAttributelist Starts");

	        Query query = sessionFactory
	                .getCurrentSession()
	                .createQuery(
                        " SELECT attrStore.valueKey,attrStore.attributeName,attrStore.display  FROM AttributeStoreVo  attrStore WHERE  attrStore.attributeName in ('COLLECTION','SUBCOLLECTION','CLASS','SUBCLASS','DEPARTMENT') order by attrStore.display ");

	  
	        List attributeList = query.list();
	        logger.info( "*************************" + attributeList.size());
	        Map<String, Map<String, String>> attributemap = new HashMap<String, Map<String, String>>();
	        Map<String, String> submap = null;
	        for (int i = 0; i < attributeList.size(); i++) {
	            Object[] vo = (Object[]) attributeList.get(i);
	            String key = "" + vo[1];
	            submap = attributemap.get(key);
	            if (submap == null) {
	                submap = new TreeMap<String, String>();
	                attributemap.put(key, submap);
	            }
	            submap.put("" + vo[0], "" + vo[2]);
	        }
        query = sessionFactory.openSession().createSQLQuery("select sg.ida3masterreference as valuekey , sg.att1 as Display from seasongroup sg where sg.latestiterationinfo = 1 and sg.ida3a10 = 14636 order by sg.att1");
        List fsintro = query.list();
        logger.info("*************************" + fsintro.size());

        Map<String, String> fsintrosubmap = new TreeMap<String, String>();
        for (int i = 0; i < fsintro.size(); i++) {
            Object[] vo = (Object[]) fsintro.get(i);
          fsintrosubmap.put("" + vo[0], "" + vo[1]);
        }
        
        attributemap.put("FSINTRODATE", fsintrosubmap);
        query = sessionFactory
                .openSession()
                .createSQLQuery("select sg.ida3masterreference as valuekey , sg.att1 as Display from seasongroup sg where sg.latestiterationinfo =1 and sg.ida3a10=14671 order by sg.att1");
        List intro = query.list();

        Map<String, String> introsubmap = new TreeMap<String, String>();
        for (int i = 0; i < intro.size(); i++) {
            Object[] vo = (Object[]) intro.get(i);
            introsubmap.put("" + vo[0], "" + vo[1]);
        }

        attributemap.put("INTRODATE", introsubmap);
        attributemap.put("PAGETYPE",getImageAttributeList());
	        return attributemap;
    }
    
    private String whereLikeClause(String sqlWhere) {
        logger.info("whereLikeClause Starts :: " + sqlWhere);
        System.out.print("where " + sqlWhere);
        String tmpSql = "";
        if (sqlWhere.contains("*")) {
            tmpSql = "" + sqlWhere.replaceAll("\\*", "%");
        } else {
            tmpSql = sqlWhere;
        }
        logger.info("whereLikeClause Ends");
        return tmpSql.trim();
    }
    
    
    @Transactional
    private Map<String, String> getImageAttributeList() {
      
    	logger.info("getImageAttributeList Starts");
            Query query = sessionFactory
                    .openSession()
                    .createSQLQuery(
                            " SELECT attrStore.valueKey,attrStore.display  FROM generalAttributeStore  attrStore " +
                            "   where attrStore.objectname = 'IMAGESPAGE' and attrStore.attributename = 'PAGETYPE'  order by attrStore.display  ");

            List attributeList = query.list();

            logger.info("*************************" + attributeList.size());  

            Map<String, String> imgAttrMap = new TreeMap<String, String>();
            for (int i = 0; i < attributeList.size(); i++) {
                Object[] vo = (Object[]) attributeList.get(i);
                imgAttrMap.put("" + vo[0], "" + vo[1]);
            }
            logger.info("getImageAttributeList Ends");
            
            return imgAttrMap;
    } 
}
