/**
 *
 */
package com.plmviewer.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.plmviewer.model.AdvSearchVo;
import com.plmviewer.model.ProductVo;
import com.plmviewer.model.searchVo;
import com.plmviewer.util.SearchConstant;
import java.util.TreeMap;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;

/**
 * @author uthanasekarapandian
 *
 */
@Repository
public class SearchDaoImpl implements SearchDao {

    @Autowired
    SessionFactory sessionFactory;
    private static final Logger logger = LoggerFactory.getLogger(SearchDaoImpl.class);

    @Override
    @Transactional
    public List<ProductVo> getProductList(AdvSearchVo search) {
        logger.info("getProductList Starts");
        logger.info("*****" + search.toString());
        String query = " from ProductVo ";
        String condition = "";

        if (search.getStyleName() != null && search.getStyleName().trim().length() > 0) {
            if (condition.trim().length() <= 0) {
                if (search.getStyleName().trim().contains("*")) {
                    condition = condition + " where styleName  like :styleName";
                } else {
                    condition = condition + " where  styleName =:styleName";
                }
            } else if (search.getStyleName().trim().contains("*")) {
                condition = condition + " and styleName  like :styleName";
            } else {
                condition = condition + " and  styleName =:styleName";
            } //condition = condition + " and styleName =:styleName";

            //	whereLikeClause(search.getStyleName()));
        }
        if (search.getStylenumber() != null && search.getStylenumber().trim().length() > 0) {
            if (condition.trim().length() <= 0) {
                if (search.getStylenumber().trim().contains("*")) {
                    condition = condition + " where styleNumber  like :stylenumber";
                } else {
                    condition = condition + " where styleNumber = :stylenumber ";
                }
            } else if (search.getStylenumber().trim().contains("*")) {
                condition = condition + " and styleNumber  like :styleNumber";
            } else {
                condition = condition + " and styleNumber = :stylenumber ";
            } //condition = condition + " and styleNumber = :stylenumber";
        }

        if (search.getSlot() != null && search.getSlot().trim().length() > 0) {
            if (condition.trim().length() <= 0) {
                if (search.getSlot().trim().contains("*")) {
                    condition = condition + " where slotNumber like :slot";
                } else {
                    condition = condition + " where slotNumber = :slot";
                }
            } else if (search.getSlot().trim().contains("*")) {
                condition = condition + " and slotNumber like :slot";
            } else {
                condition = condition + " and slotNumber =:slot";
            }
        }

        if (search.getAdaptoted() != null && search.getAdaptoted().trim().length() > 0) {

            condition = condition + " and adopted =:adopted";
        }

        if (search.getAttitude() != null && search.getAttitude().trim().length() > 0) {
            if (condition.trim().length() <= 0) {
                condition = condition + " where attitudinalSegments =:attitude";
            } else {
                condition = condition + " and attitudinalSegments =:attitude";
            }

        }

        if (search.getCollaboration() != null && search.getCollaboration().trim().length() > 0) {
            if (condition.trim().length() <= 0) {
                condition = condition + " where collaboration  =:collaboration";
            } else {
                condition = condition + " and collaboration =:collaboration";
            }
            // condition = condition + "collaboration = '" + search.getCollaboration() + "' and ";
        }

        if (search.getChannelexclusive() != null && search.getChannelexclusive().trim().length() > 0) {
            if (condition.trim().length() <= 0) {
                condition = condition + " where channelExclusive =:channelExclusive";
            } else {
                condition = condition + " and channelExclusive  =:channelExclusive";
            }
            // condition = condition + "channelExclusive = '" + search.getChannelexclusive() + "' and ";
        }
        if (search.getCollection() != null && search.getCollection().trim().length() > 0) {
            if (condition.trim().length() <= 0) {
                condition = condition + " where collection =:collection";
            } else {
                condition = condition + " and collection =:collection";
            }
            // condition = condition + "collection = '" + search.getCollection() + "' and ";
        }

        if (search.getDepartment() != null && search.getDepartment().trim().length() > 0) {
            if (condition.trim().length() <= 0) {
                condition = condition + " where department =:department";
            } else {
                condition = condition + " and department =:department";
            }
            // condition = condition + "department = '" + search.getDepartment() + "' and ";
        }
        if (search.getDropped() != null && search.getDropped().trim().length() > 0) {
            if (condition.trim().length() <= 0) {
                condition = condition + " where dropped =:dropped";
            } else {
                condition = condition + " and dropped =:dropped ";
            }
            // condition = condition + "dropped = '" + search.getDepartment() + "' and ";
        }
        if (search.getFactorystoretype() != null && search.getFactorystoretype().trim().length() > 0) {
            if (condition.trim().length() <= 0) {
                condition = condition + " where factorystoreTypeProduct =:factoryStoreType ";
            } else {
                condition = condition + " and factorystoreTypeProduct =:factoryStoreType ";
            }
            // condition = condition + "factorystoreTypeProduct = '" + search.getFactorystoretype() + "' and ";
        }
        if (search.getFsintrodate() != null && search.getFsintrodate().trim().length() > 0) {
            if (condition.trim().length() <= 0) {
                condition = condition + " where fsIntroDate =:fsIntroDate";
            } else {
                condition = condition + " and fsIntroDate =:fsIntroDate";
            }
            //  condition = condition + "fsIntroDate = '" + search.getFsintrodate() + "' and ";
        }
        if (search.getGender() != null && search.getGender().trim().length() > 0) {
            if (condition.trim().length() <= 0) {
                condition = condition + " where gender =:gender";
            } else {
                condition = condition + " and gender =:gender";
            }
            //  condition = condition + "gender = '" + search.getGender() + "' and ";
        }
        if (search.getIntrodate() != null && search.getIntrodate().trim().length() > 0) {
            if (condition.trim().length() <= 0) {
                condition = condition + " where introDate =:introDate";
            } else {
                condition = condition + " and introDate  =:introDate";
            }
            //condition = condition + "introDate = '" + search.getIntrodate() + "' and ";
        }
        if (search.getLifecyclestate() != null && search.getLifecyclestate().trim().length() > 0) {
            if (condition.trim().length() <= 0) {
                condition = condition + " where prodLifeCycleState =:prodLifeCycleState";
            } else {
                condition = condition + " and prodLifeCycleState =:prodLifeCycleState";
            }
        }
        if (search.getLook() != null && search.getLook().trim().length() > 0) {
            if (condition.trim().length() <= 0) {
                condition = condition + " where look =:look";
            } else {
                condition = condition + " and look =:look";
            }
        }
        if (search.getMaterial() != null && search.getMaterial().trim().length() > 0) {
            if (condition.trim().length() <= 0) {
                condition = condition + " where material =:material";
            } else {
                condition = condition + " and material =:material";
            }
            // condition = condition + "material = '" + search.getMaterial() + "' and ";
        }
        if (search.getSilhouette() != null && search.getSilhouette().trim().length() > 0) {
            if (condition.trim().length() <= 0) {
                condition = condition + " where benchStyleForSilhoutte =:benchStyleForSilhoutte";
            } else {
                condition = condition + " and benchStyleForSilhoutte =:benchStyleForSilhoutte";
            }
            // condition = condition + "benchStyleForSilhoutte = '" + search.getSilhouette() + "' and ";
        }
        if (search.getStyleclass() != null && search.getStyleclass().trim().length() > 0) {
            if (condition.trim().length() <= 0) {
                condition = condition + " where productClass =:productClass";
            } else {
                condition = condition + " and productClass =:productClass";
            }
            //condition = condition + "productClass = '" + search.getStyleclass() + "' and ";
        }

        if (search.getSubclass() != null && search.getSubclass().trim().length() > 0) {
            if (condition.trim().length() <= 0) {
                condition = condition + " where subClass =:subClass ";
            } else {
                condition = condition + " and subClass =:subClass ";
            }
            //  condition = condition + "subClass = '" + search.getSubclass() + "' and ";
        }
        if (search.getSubcollection() != null && search.getSubcollection().trim().length() > 0) {
            if (condition.trim().length() <= 0) {
                condition = condition + " where subCollection =:subCollection ";
            } else {
                condition = condition + " and subCollection =:subCollection ";
            }
            //  condition = condition + "subCollection = '" + search.getSubcollection() + "' and ";
        }
        if (search.getTeam() != null && search.getTeam().trim().length() > 0) {
            if (condition.trim().length() <= 0) {
                condition = condition + " where team =:team ";
            } else {
                condition = condition + " and team =:team ";
            }
            //     condition = condition + "team = '" + search.getTeam() + "' and ";
        }

//	        if(search.getCreatedate()!=null && search.getCreatedate().length()>0){
//	            Condition = Condition + "collection = '"+search.getCreatedate()+"' and ";
//	        }
//	           if(search.getStylegroup()!=null && search.getStylegroup().length()>0){
//	            Condition = Condition + "productClass = '"+search.getStylegroup()+"' and ";
//	        } 
//	         if(search.getLastupdated()!=null && search.getLastupdated().length()>0){
//	            Condition = Condition + "introDate = '"+search.getLastupdated()+"' and ";
//	        } 
        //   condition = condition.substring(0, condition.length() - 5);
        query = query + condition;
        logger.info("Final Query :: " + query);

        Query hibernateQuery = sessionFactory.openSession().createQuery(query);
        if (search.getStyleName() != null && search.getStyleName().trim().length() > 0) {
            hibernateQuery.setParameter("styleName", whereLikeClause(search.getStyleName()));
        }
        if (search.getStylenumber() != null && search.getStylenumber().trim().length() > 0) {
            hibernateQuery.setParameter("stylenumber", whereLikeClause(search.getStylenumber()));
        }
        if (search.getSlot() != null && search.getSlot().trim().length() > 0) {
            hibernateQuery.setParameter("slot", whereLikeClause(search.getSlot()));
        }
        if (search.getAdaptoted() != null && search.getAdaptoted().trim().length() > 0) {
            hibernateQuery.setParameter("adopted", whereLikeClause(search.getAdaptoted()));
        }
        if (search.getAttitude() != null && search.getAttitude().trim().length() > 0) {
            hibernateQuery.setParameter("attitude", whereLikeClause(search.getAttitude()));

        }

        if (search.getCollaboration() != null && search.getCollaboration().trim().length() > 0) {
            hibernateQuery.setParameter("collaboration", whereLikeClause(search.getCollaboration()));
        }

        if (search.getChannelexclusive() != null && search.getChannelexclusive().trim().length() > 0) {
            hibernateQuery.setParameter("channelExclusive", whereLikeClause(search.getChannelexclusive()));
        }
        if (search.getCollection() != null && search.getCollection().trim().length() > 0) {
            hibernateQuery.setParameter("collection", whereLikeClause(search.getCollection()));
        }

        if (search.getDepartment() != null && search.getDepartment().trim().length() > 0) {
            hibernateQuery.setParameter("department", whereLikeClause(search.getDepartment()));

        }
        if (search.getDropped() != null && search.getDropped().trim().length() > 0) {
            hibernateQuery.setParameter("dropped", whereLikeClause(search.getDropped()));
        }
        if (search.getFactorystoretype() != null && search.getFactorystoretype().trim().length() > 0) {
            hibernateQuery.setParameter("factorystoreTypeProduct", whereLikeClause(search.getFactorystoretype()));

        }
        if (search.getFsintrodate() != null && search.getFsintrodate().trim().length() > 0) {
            hibernateQuery.setParameter("fsIntroDate", whereLikeClause(search.getFsintrodate()));

        }
        if (search.getGender() != null && search.getGender().trim().length() > 0) {
            hibernateQuery.setParameter("gender", whereLikeClause(search.getGender()));
        }
        if (search.getIntrodate() != null && search.getIntrodate().trim().length() > 0) {
            hibernateQuery.setParameter("introDate", whereLikeClause(search.getIntrodate()));
        }
        if (search.getLifecyclestate() != null && search.getLifecyclestate().trim().length() > 0) {
            hibernateQuery.setParameter("prodLifeCycleState", whereLikeClause(search.getLifecyclestate()));
        }
        if (search.getLook() != null && search.getLook().trim().length() > 0) {
            hibernateQuery.setParameter("look", whereLikeClause(search.getLook()));
        }
        if (search.getMaterial() != null && search.getMaterial().trim().length() > 0) {
            hibernateQuery.setParameter("material", whereLikeClause(search.getMaterial()));
        }
        if (search.getSilhouette() != null && search.getSilhouette().trim().length() > 0) {
            hibernateQuery.setParameter("benchStyleForSilhoutte", whereLikeClause(search.getSilhouette()));
        }
        if (search.getStyleclass() != null && search.getStyleclass().trim().length() > 0) {
            hibernateQuery.setParameter("productClass", whereLikeClause(search.getStyleclass()));
        }

        if (search.getSubclass() != null && search.getSubclass().trim().length() > 0) {
            hibernateQuery.setParameter("subClass", whereLikeClause(search.getSubclass()));
        }
        if (search.getSubcollection() != null && search.getSubcollection().trim().length() > 0) {
            hibernateQuery.setParameter("subCollection", whereLikeClause(search.getSubcollection()));
        }
        if (search.getTeam() != null && search.getTeam().trim().length() > 0) {
            hibernateQuery.setParameter("team", whereLikeClause(search.getTeam()));

        }
        List lst = hibernateQuery.list();
        logger.info("Number of Products :: " + lst.size());
        logger.info("getProductList Ends");
        return lst;

    }

    @Override
    @Transactional
    public Map<String, Map<String, String>> getAttributelist(String attributelevel, String attributename) {

        logger.info("getAttributelist Starts");
        
      String  queryString=        		  
    	  " SELECT attrStore.valueKey,attrStore.attributeName,attrStore.display  FROM AttributeStoreVo  attrStore WHERE "
    	  + " attrStore.attributeName in ('GENDER','LOOK','COLLECTION','SUBCOLLECTION','CLASS','SUBCLASS',"
    	  + "'INTRODATE','FSINTRODATE','ATTITUDE','SILHOUETTE','SIZES','TEAM','MATERIAL','CHANNELEXCLUSIVE',"
    	  + "'FACTORYSTORETYPEPRODUCT','COLLABORATION','LIFECYCLESTATE','STYLEGROUP') order by attrStore.display  ";

        Query query = sessionFactory
                .getCurrentSession()
                .createQuery(queryString);
                        //" SELECT attrStore.valueKey,attrStore.attributeName,attrStore.display  FROM AttributeStoreVo  attrStore WHERE  attrStore.attributeName in ('GENDER','LOOK','COLLECTION','SUBCOLLECTION','CLASS','SUBCLASS','DEPARTMENT','INTRODATE','FSINTRODATE','ATTITUDE','SILHOUETTE','SIZES','TEAM','MATERIAL','CHANNELEXCLUSIVE','FACTORYSTORETYPEPRODUCT','COLLABORATION','LIFECYCLESTATE','STYLEGROUP') order by attrStore.display ");
       
        List attributeList = query.list();
        
        Query departmentQuery = sessionFactory
                .getCurrentSession()
                .createQuery("SELECT attrStore1.valueKey,attrStore1.attributeName,attrStore1.display FROM AttributeStoreVo attrStore1  WHERE   attrStore1.attributeName = 'DEPARTMENT' and attrStore1.display NOT LIKE 'R%'order by attrStore1.display ");
    
         attributeList.addAll(departmentQuery.list());

        logger.info("*************************" + attributeList.size());
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
            if (!vo[1].toString().toUpperCase().contains(" RK ")) {
                fsintrosubmap.put("" + vo[0], "" + vo[1]);
            }
        }
        attributemap.put("FSINTRODATE", fsintrosubmap);
        query = sessionFactory
                .openSession()
                .createSQLQuery("select sg.ida3masterreference as valuekey , sg.att1 as Display from seasongroup sg where sg.latestiterationinfo =1 and sg.ida3a10=14671 order by sg.att1");
        List intro = query.list();

        Map<String, String> introsubmap = new TreeMap<String, String>();
        for (int i = 0; i < intro.size(); i++) {
            Object[] vo = (Object[]) intro.get(i);
            if (!vo[1].toString().toUpperCase().contains(" RK ")) {
                introsubmap.put("" + vo[0], "" + vo[1]);
            }

        }

        attributemap.put("INTRODATE", introsubmap);
        return attributemap;

    }

    @Override
    public List<searchVo> getProductListDetail(AdvSearchVo search) {
        String statement = SearchConstant.selectstatement;
        String whereclause = "";

        if (search.getStyleName() != null && search.getStyleName().trim().length() > 0) {
            if (whereclause.trim().length() <= 0) {
                if (search.getStyleName().trim().contains("*")) {
                    whereclause = whereclause + " where UPPER(att1) like '" + whereLikeClause(search.getStyleName()).toUpperCase() + "'";
                } else {
                    whereclause = whereclause + " where UPPER(att1)  = '" + search.getStyleName().toUpperCase() + "'";
                }

            } else if (search.getStyleName().trim().contains("*")) {
                whereclause = whereclause + " and att1 like '" + whereLikeClause(search.getStyleName()) + "'";
            } else {
                whereclause = whereclause + " and att1 = '" + search.getStyleName() + "'";
            }
        }
        if (search.getStylenumber() != null && search.getStylenumber().trim().length() > 0) {
            if (whereclause.trim().length() <= 0) {
                if (search.getStylenumber().trim().contains("*")) {
                    whereclause = whereclause + " where att44 like '" + whereLikeClause(search.getStylenumber()) + "'";
                } else {
                    whereclause = whereclause + " where att44 = '" + search.getStylenumber() + "'";
                }

            } else if (search.getStylenumber().trim().contains("*")) {
                whereclause = whereclause + " and att44 like '" + whereLikeClause(search.getStylenumber()) + "'";
            } else {
                whereclause = whereclause + " and att44 = '" + search.getStylenumber() + "'";
            }
        }
        if (search.getSlot() != null && search.getSlot().trim().length() > 0) {
            if (whereclause.trim().length() <= 0) {
                if (search.getSlot().trim().contains("*")) {
                    whereclause = whereclause + " where att42 like '" + whereLikeClause(search.getSlot()) + "'";
                } else {
                    whereclause = whereclause + " where att42 = '" + search.getSlot() + "'";
                }

            } else if (search.getSlot().trim().contains("*")) {
                whereclause = whereclause + " and att42 like '" + whereLikeClause(search.getSlot()) + "'";

            } else {
                whereclause = whereclause + " and att42 = '" + search.getSlot() + "'";

            }
        }
        if (search.getAttitude() != null && search.getAttitude().trim().length() > 0) {
            if (whereclause.trim().length() <= 0) {
                whereclause = whereclause + " where att97 = '" + search.getAttitude() + "'";
            } else {
                whereclause = whereclause + " and att97 = '" + search.getAttitude() + "'";
            }
        }
        if (search.getChannelexclusive() != null && search.getChannelexclusive().trim().length() > 0) {
            if (whereclause.trim().length() <= 0) {
                whereclause = whereclause + " where att12 = '" + search.getChannelexclusive() + "'";
            } else {
                whereclause = whereclause + " and att12 = '" + search.getChannelexclusive() + "'";
            }
        }
        if (search.getCollaboration() != null && search.getCollaboration().trim().length() > 0) {
            if (whereclause.trim().length() <= 0) {
                whereclause = whereclause + " where att89 = '" + search.getCollaboration() + "'";
            } else {
                whereclause = whereclause + " and att89 = '" + search.getCollaboration() + "'";
            }
        }
        if (search.getCollection() != null && search.getCollection().trim().length() > 0) {
            if (whereclause.trim().length() <= 0) {
                whereclause = whereclause + " where att13 = '" + search.getCollection() + "'";
            } else {
                whereclause = whereclause + " and att13 = '" + search.getCollection() + "'";
            }
        }

        if (search.getDropped() != null && search.getDropped().trim().length() > 0) {
            if (whereclause.trim().length() <= 0) {
                whereclause = whereclause + " where att69 = '" + search.getDropped() + "'";//merchDropped
            } else {
                whereclause = whereclause + " and att69 = '" + search.getDropped() + "'";
            }
        }

        if (search.getFactorystoretype() != null && search.getFactorystoretype().trim().length() > 0) {
            if (whereclause.trim().length() <= 0) {
                whereclause = whereclause + " where att22 = '" + search.getFactorystoretype() + "'";//merchDropped
            } else {
                whereclause = whereclause + " and att22 = '" + search.getFactorystoretype() + "'";
            }
        }
        if (search.getFsintrodate() != null && search.getFsintrodate().trim().length() > 0) {
            if (whereclause.trim().length() <= 0) {
                whereclause = whereclause + " where att24 = '" + search.getFsintrodate() + "'";//merchDropped
            } else {
                whereclause = whereclause + " and att24 = '" + search.getFsintrodate() + "'";
            }
        }
        if (search.getGender() != null && search.getGender().trim().length() > 0) {
            if (whereclause.trim().length() <= 0) {
                whereclause = whereclause + " where att25 = '" + search.getGender() + "'";//merchDropped
            } else {
                whereclause = whereclause + " and att25 = '" + search.getGender() + "'";
            }
        }
        if (search.getIntrodate() != null && search.getIntrodate().trim().length() > 0) {
            if (whereclause.trim().length() <= 0) {
                whereclause = whereclause + " where att27 = '" + search.getIntrodate() + "'";//merchDropped
            } else {
                whereclause = whereclause + " and att27 = '" + search.getIntrodate() + "'";
            }
        }
        if (search.getLook() != null && search.getLook().trim().length() > 0) {
            if (whereclause.trim().length() <= 0) {
                whereclause = whereclause + " where att98 like '%" + search.getLook() + "%'";//merchDropped
            } else {
                whereclause = whereclause + " and att98 like '%" + search.getLook() + "%'";
            }
        }
        if (search.getMaterial() != null && search.getMaterial().trim().length() > 0) {
            if (whereclause.trim().length() <= 0) {
                whereclause = whereclause + " where att30 = '" + search.getMaterial() + "'";//merchDropped
            } else {
                whereclause = whereclause + " and att30 = '" + search.getMaterial() + "'";
            }
        }
        if (search.getSilhouette() != null && search.getSilhouette().trim().length() > 0) {
            if (whereclause.trim().length() <= 0) {
                whereclause = whereclause + " where att40 = '" + search.getSilhouette() + "'";//merchDropped
            } else {
                whereclause = whereclause + " and att40 = '" + search.getSilhouette() + "'";
            }
        }
        if (search.getStyleclass() != null && search.getStyleclass().trim().length() > 0) {
            if (whereclause.trim().length() <= 0) {
                whereclause = whereclause + " where att50 = '" + search.getStyleclass() + "'";//merchDropped
            } else {
                whereclause = whereclause + " and att50 = '" + search.getStyleclass() + "'";
            }
        }

        if (search.getSubclass() != null && search.getSubclass().trim().length() > 0) {
            if (whereclause.trim().length() <= 0) {
                whereclause = whereclause + " where att51 = '" + search.getSubclass() + "'";//merchDropped
            } else {
                whereclause = whereclause + " and att51 = '" + search.getSubclass() + "'";
            }
        }
        if (search.getSubcollection() != null && search.getSubcollection().trim().length() > 0) {
            if (whereclause.trim().length() <= 0) {
                whereclause = whereclause + " where att45 = '" + search.getSubcollection() + "'";//merchDropped
            } else {
                whereclause = whereclause + " and att45 = '" + search.getSubcollection() + "'";
            }
        } 
        if (search.getTeam() != null && search.getTeam().trim().length() > 0) {
            if (whereclause.trim().length() <= 0) {
        
                whereclause = whereclause + " where att45 = (select t.att1 from lcslifecyclemanaged t where t.ida3a8=6230 and t.ida2a2='"+search.getTeam()+"') ";
            } else {
                whereclause = whereclause + " and att45 = (select t.att1 from lcslifecyclemanaged t where t.ida3a8=6230 and t.ida2a2='"+search.getTeam()+"') ";
            }
        }
        
        
        if (search.getLifecyclestate()!= null && search.getLifecyclestate().trim().length() > 0) {
            if (whereclause.trim().length() <= 0) {
                whereclause = whereclause + " where statestate = '" + search.getLifecyclestate() + "'";//merchDropped
            } else {
                whereclause = whereclause + " and statestate = '" + search.getLifecyclestate() + "'";
            }
        }
        if (search.getAdaptoted()!= null && search.getAdaptoted().trim().length() > 0) {
            if (whereclause.trim().length() <= 0) {
                whereclause = whereclause + " where att8 = '" + search.getAdaptoted() + "'";
            } else {
                whereclause = whereclause + " and att8 = '" + search.getAdaptoted() + "'";
            }
        } 
        if (search.getDropped()!= null && search.getDropped().trim().length() > 0) {
            if (whereclause.trim().length() <= 0) {
                whereclause = whereclause + " where att69 = '" + search.getDropped() + "'";
            } else {
                whereclause = whereclause + " and att69 = '" + search.getDropped() + "'";
            }
        } /**
         * *********************************************************************************************************
         */
        else if (search.getProductType() != null && search.getProductType().trim().length() > 0) {

            if (search.getProductType().equals(SearchConstant.accessoriesDepartType)) {
                statement = statement + SearchConstant.accessoriesSql;
                String departments = "";
                departments = search.getDepartment();
                if (departments == null || departments.length() <= 0) {
                    departments = SearchConstant.AccessoriesDepart;
                }else{
                	departments = "'"+departments+"'"; 
                }

                if (whereclause.trim().length() <= 0) {
                    whereclause = whereclause + " where att49 in (" + departments + ")";
                } else {
                    whereclause = whereclause + " and att49 in (" + departments + ")";
                }

                if (search.getStylegroup() != null && search.getStylegroup().trim().length() > 0) {
                    if (whereclause.trim().length() <= 0) {
                        whereclause = whereclause + " where att63 = '" + search.getStylegroup() + "'";
                    } else {
                        whereclause = whereclause + " and att63 = '" + search.getStylegroup() + "'";
                    }
                }

            } else if (search.getProductType().equals(SearchConstant.footWearDepartType)) {
                statement = statement + SearchConstant.footWearSql;
                String departments = "";
                departments = search.getDepartment();
                if (departments == null || departments.length() <= 0) {
                    departments = SearchConstant.FootWearDepart;
                }else{
                	departments = "'"+departments+"'"; 
                }

                if (whereclause.trim().length() <= 0) {
                    whereclause = whereclause + " where att49 in (" + departments + ")";
                } else {
                    whereclause = whereclause + " and att49 in (" + departments + ")";
                }

                if (search.getStylegroup() != null && search.getStylegroup().trim().length() > 0) {
                    if (whereclause.trim().length() <= 0) {
                        whereclause = whereclause + " where att58 = '" + search.getStylegroup() + "'";
                    } else {
                        whereclause = whereclause + " and att58 = '" + search.getStylegroup() + "'";
                    }
                }

            } else if (search.getProductType().equals(SearchConstant.fragranceDepartType)) {
                statement = statement + SearchConstant.fragranceSql;
                String departments = "";
                departments = search.getDepartment();
                if (departments == null || departments.length() <= 0) {
                    departments = SearchConstant.FragranceDepart;
                }
                else{
                	departments = "'"+departments+"'"; 
                }
                if (whereclause.trim().length() <= 0) {
                    whereclause = whereclause + " where att49 in (" + departments + ")";
                } else {
                    whereclause = whereclause + " and att49 in (" + departments + ")";
                }
                if (search.getStylegroup() != null && search.getStylegroup().trim().length() > 0) {
                    if (whereclause.trim().length() <= 0) {
                        whereclause = whereclause + " where att55 = '" + search.getStylegroup() + "'";
                    } else {
                        whereclause = whereclause + " and att55 = '" + search.getStylegroup() + "'";
                    }
                }

            } else if (search.getProductType().equals(SearchConstant.jewelryDepartType)) {
                statement = statement + SearchConstant.jewelrySql;
                String departments = "";
                departments = search.getDepartment();
                if (departments == null || departments.length() <= 0) {
                    departments = SearchConstant.JewelryDepart;
                }
                else{
                	departments = "'"+departments+"'"; 
                }
                if (whereclause.trim().length() <= 0) {
                    whereclause = whereclause + " where att49 in (" + departments + ")";
                } else {
                    whereclause = whereclause + " and att49 in (" + departments + ")";
                }
                if (search.getStylegroup() != null && search.getStylegroup().trim().length() > 0) {
                    if (whereclause.trim().length() <= 0) {
                        whereclause = whereclause + " where att55 = '" + search.getStylegroup() + "'";
                    } else {
                        whereclause = whereclause + " and att55 = '" + search.getStylegroup() + "'";
                    }
                }

            } else if (search.getProductType().equals(SearchConstant.sunWearDepartType)) {
                statement = statement + SearchConstant.sunWearSql;
                String departments = "";
                departments = search.getDepartment();
                if (departments == null || departments.length() <= 0) {
                    departments = SearchConstant.SunWearDepart;
                }else{
                	departments = "'"+departments+"'"; 
                }
                if (whereclause.trim().length() <= 0) {
                    whereclause = whereclause + " where att49 in (" + departments + ")";
                } else {
                    whereclause = whereclause + " and att49 in (" + departments + ")";
                }
                if (search.getStylegroup() != null && search.getStylegroup().trim().length() > 0) {
                    if (whereclause.trim().length() <= 0) {
                        whereclause = whereclause + " where att55 = '" + search.getStylegroup() + "'";
                    } else {
                        whereclause = whereclause + " and att55 = '" + search.getStylegroup() + "'";
                    }
                }

            } else if (search.getProductType().equals(SearchConstant.watchesDepartType)) {
                statement = statement + SearchConstant.watchesSql;
                String departments = "";
                departments = search.getDepartment();
                if (departments == null || departments.length() <= 0) {
                    departments = SearchConstant.WatchesDepart;
                }
                else{
                	departments = "'"+departments+"'"; 
                }
                if (whereclause.trim().length() <= 0) {
                    whereclause = whereclause + " where att49 in (" + departments + ")";
                } else {
                    whereclause = whereclause + " and att49 in (" + departments + ")";
                }
                if (search.getStylegroup() != null && search.getStylegroup().trim().length() > 0) {
                    if (whereclause.trim().length() <= 0) {
                        whereclause = whereclause + " where att55 = '" + search.getStylegroup() + "'";
                    } else {
                        whereclause = whereclause + " and att55 = '" + search.getStylegroup() + "'";
                    }
                }

            } else if (search.getProductType().equals(SearchConstant.wearablesDepartType)) {
                statement = statement + SearchConstant.wearablesSql;
                String departments = "";
                departments = search.getDepartment();
                if (departments == null || departments.length() <= 0) {
                    departments = SearchConstant.WearablesDepart;
                }else{
                	departments = "'"+departments+"'"; 
                }

                if (whereclause.trim().length() <= 0) {
                    whereclause = whereclause + " where att49 in (" + departments + ")";
                } else {
                    whereclause = whereclause + " and att49 in (" + departments + ")";
                }
                if (search.getStylegroup() != null && search.getStylegroup().trim().length() > 0) {
                    if (whereclause.trim().length() <= 0) {
                        whereclause = whereclause + " where att55 = '" + search.getStylegroup() + "'";
                    } else {
                        whereclause = whereclause + " and att55 = '" + search.getStylegroup() + "'";
                    }
                }

            }

        }

        statement = statement + " from Prodarev prodarev  " + whereclause + "  AND  IDA3A11 NOT in (65819988, 65821413, 65821710,"
                + " 65821711, 65821422, 65821720, 65821721, 12595, 28295143, 28295324, 28295502, 28295680, 28295859, 28296037)";

        /* if (search.getSizescale()!= null && search.getSizescale().trim().length() > 0) {
            statement = statement.replaceAll("'#%sizeScale%#'", "and att1 = '"+search.getSizescale()+"'");
        }else{
             statement = statement.replaceAll("'#%sizeScale%#'", "");
         }*/
        System.out.println("Final statement resut :: " + statement);

        Query query = sessionFactory.openSession().createSQLQuery(statement);

        List<searchVo> searchresult = (List<searchVo>) query.setResultTransformer(Transformers.aliasToBean(searchVo.class)).list();

        logger.info("search result size ::  " + searchresult.size());

        /**
         * *********************************************************************************************************
         */
        return searchresult;
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
        System.out.println("sqlWhere ::;" + tmpSql);
        logger.info("whereLikeClause Ends");
        return tmpSql.trim();
    }

}
