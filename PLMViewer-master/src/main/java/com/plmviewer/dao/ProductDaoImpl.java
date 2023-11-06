/**
 *
 */
package com.plmviewer.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.plmviewer.dao.ProductDao;
import com.plmviewer.model.DocumentObjectRelationship;
import com.plmviewer.model.LCSMoaObjectVo;
import com.plmviewer.model.ProductadditionDetailsVo;
import com.plmviewer.model.LCSRevisableEntityVo;
import com.plmviewer.model.ProductVo;
import com.plmviewer.model.SKUaRevVo;
import com.plmviewer.model.SkuAdditionalDetailsVo;
import com.plmviewer.util.ProductDeptConstant;

import java.sql.Timestamp;

/**
 * @author uthanasekarapandian
 *
 */
@Repository
public class ProductDaoImpl implements ProductDao {

    @Autowired
    SessionFactory sessionFactory;
    private static final Logger logger = LoggerFactory.getLogger(ProductDaoImpl.class);

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<ProductVo> getProductbyStyleNumber(String styleNumber) throws Exception {
        List<ProductVo> prodVoList = null;
        try {
            logger.info("getProductbyStyleNumber Starts");
            logger.info("getProductbyStyleNumber :: styleNumber  :: " + styleNumber);
            prodVoList = sessionFactory.getCurrentSession().createCriteria(ProductVo.class)
                    .add(Restrictions.eq("styleNumber", styleNumber)).list();
            logger.info("getProductbyStyleNumber :: prodVoList  :: " + prodVoList.size());
            logger.info("getProductbyStyleNumber Ends");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return prodVoList;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<ProductVo> getProductbySlot(String slot) throws Exception {
        List<ProductVo> prodVoList = null;
        try {
            logger.info("getProductbyStyleNumber Starts");
            logger.info("getProductbyStyleNumber :: styleNumber  :: " + slot);
            prodVoList = sessionFactory.getCurrentSession().createCriteria(ProductVo.class)
                    .add(Restrictions.eq("slotNumber", slot)).list();

            logger.info("getProductbyStyleNumber :: prodVoList  :: " + prodVoList.size());
            logger.info("getProductbyStyleNumber Ends");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return prodVoList;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<ProductVo> getProductList(String styleName)throws Exception{
        List<ProductVo> prodVoList = null;
        try {
            logger.info("getProductList Starts");
            logger.info("getProductList :: styleName  :: " + styleName);
            prodVoList = sessionFactory.getCurrentSession().createCriteria(ProductVo.class)
                    //.add(Restrictions.like("styleName","%"+styleName+"%")).list();
                    .add(Restrictions.like("styleNumber", "15199")).list();
            logger.info("getProductList :: prodVoList  :: " + prodVoList.size());
            logger.info("getProductList Ends");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return prodVoList;
    }

    @SuppressWarnings({"unchecked", "deprecation"})
    @Override
    @Transactional
    public ProductadditionDetailsVo getProductDataList(String dept, String searchkey, String searchtype, String deptType) throws Exception{
        ProductadditionDetailsVo productAdditionalDetailsVo = null;
        try {
            logger.info("getProductDataList Starts");
            String wherelause = "";
            if (searchtype.equals("stylenumber")) {
                wherelause = " from Prodarev where att49=  '" + dept + "' and att44 = '" + searchkey + "'";
            } else {
                wherelause = " from Prodarev where att49=  '" + dept + "' and att42 = '" + searchkey + "'";
            }
            System.out.println("*******************************************************************");
            String sqltobeexecuted = ProductDeptConstant.prodDptSql;
            if (deptType.equalsIgnoreCase(ProductDeptConstant.accessoriesDepartType)) {
                sqltobeexecuted = sqltobeexecuted + ProductDeptConstant.accessoriesSql + wherelause;
            } else if (deptType.equalsIgnoreCase(ProductDeptConstant.footWearDepartType)) {
                sqltobeexecuted = sqltobeexecuted + ProductDeptConstant.footWearSql + wherelause;
            } else if (deptType.equalsIgnoreCase(ProductDeptConstant.fragranceDepartType)) {
                sqltobeexecuted = sqltobeexecuted + ProductDeptConstant.fragranceSql + wherelause;
            } else if (deptType.equalsIgnoreCase(ProductDeptConstant.sunWearDepartType)) {
                sqltobeexecuted = sqltobeexecuted + ProductDeptConstant.sunWearSql + wherelause;

            } else if (deptType.equalsIgnoreCase(ProductDeptConstant.jewelryDepartType)) {
                sqltobeexecuted = sqltobeexecuted + ProductDeptConstant.jewelrySql + wherelause;

            } else if (deptType.equalsIgnoreCase(ProductDeptConstant.watchesDepartType)) {
                sqltobeexecuted = sqltobeexecuted + ProductDeptConstant.watchesSql + wherelause;
            } else if (deptType.equalsIgnoreCase(ProductDeptConstant.wearablesDepartType)) {
                sqltobeexecuted = sqltobeexecuted + ProductDeptConstant.wearablesSql + wherelause;
            }

            Query query = sessionFactory.getCurrentSession().createSQLQuery(sqltobeexecuted);

            List resultwithAliastoBean = query.setResultTransformer(Transformers.aliasToBean(ProductadditionDetailsVo.class)).list();
            System.out.println(" **** lst ****" + resultwithAliastoBean);
            if (resultwithAliastoBean != null & resultwithAliastoBean.size() > 0) {
                productAdditionalDetailsVo = (ProductadditionDetailsVo) resultwithAliastoBean.get(0);
            }
            logger.info("getProductDataList :: prodVoList  :: " + productAdditionalDetailsVo);
            logger.info("getProductDataList Ends");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return productAdditionalDetailsVo;
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public List<String> getProductSeason(String searchkey, String Searchtype) throws Exception {
        List<String> seasonList = null;
        try {
            logger.info("getProductSeason Starts");
            String column = "";
            if (Searchtype.equalsIgnoreCase("stylenumber")) {
                column = "Prodarev.styleNumber";

            } else {
                column = "Prodarev.slotNumber";
            }

            Query query = sessionFactory.getCurrentSession()
                    .createQuery("SELECT WTPARTMASTER.partMasterName "
                            + " FROM WTPartMasterVo WTPARTMASTER, LCSSeasonVo LCSSEASON, LCSSeasonProductLinkVo LCSSEASONPRODUCTLINK ,ProductVo Prodarev "
                            + " WHERE LCSSEASON.ida3MasterReference = WTPARTMASTER.ida2a2 "
                            + " AND LCSSEASONPRODUCTLINK.ida3b5 = WTPARTMASTER.ida2a2 AND LCSSEASONPRODUCTLINK.ida3a5 = Prodarev.ida3a12 "
                            + " AND " + column + " = '" + searchkey + "'"
                            + " AND ( LCSSEASONPRODUCTLINK.seasonRemoved IS NULL  OR LCSSEASONPRODUCTLINK.seasonRemoved = '0' ) AND "
                            + " LCSSEASONPRODUCTLINK.effectOutDate IS NULL AND  LCSSEASON.stateCheckOutInfo <> 'wrk' AND LCSSEASON.latestIterationInfo = '1' "
                            + " ORDER BY WTPARTMASTER.partMasterName ");

            seasonList = query.list();

            logger.info("getProductSeason sourceList.size() " + seasonList.size());

            logger.info("getProductSeason Ends");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return seasonList;
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public Map<String, String> getProductSource(String searchkey, String Searchtype) throws Exception {
        Map<String, String> prodSrcMap = null;
        try {
            logger.info("getProductSource Starts");
            /*
		 * select LCSSOURCINGCONFIGMASTER.ida2a2,LCSSOURCINGCONFIG.att1 FROM
		 * LCSSOURCINGCONFIGMASTER,LCSSOURCINGCONFIG,Prodarev prodarev WHERE
		 * LCSSOURCINGCONFIGMASTER.IDA3A6= (select ida3masterreference from
		 * prodarev WHERE ATT44 = '15199') and
		 * LCSSOURCINGCONFIG.ida3masterreference=LCSSOURCINGCONFIGMASTER.ida2a2
		 * and LCSSOURCINGCONFIG.LATESTITERATIONINFO=1;
             */
            String column = "";
            if (Searchtype.equalsIgnoreCase("stylenumber")) {
                column = "Prodarev.styleNumber";

            } else {
                column = "Prodarev.slotNumber";
            }
            Query query = sessionFactory
                    .getCurrentSession()
                    .createQuery(
                            "select LCSSOURCINGCONFIGMASTER.sourceId,"
                            + "LCSSOURCINGCONFIG.sourceName from LCSSourcingConfigMasterVo LCSSOURCINGCONFIGMASTER , "
                            + "LCSSourcingConfigVo LCSSOURCINGCONFIG ,ProductVo Prodarev  WHERE "
                            + " LCSSOURCINGCONFIGMASTER.prodIDA3MasterReference= Prodarev.ida3masterreference AND " + column + " = '"
                            + searchkey
                            + "' AND LCSSOURCINGCONFIG.prodIDA3MAsterReference=LCSSOURCINGCONFIGMASTER.sourceId "
                            + " AND LCSSOURCINGCONFIG.latestIterationInfo=1 ");
            List<Object[]> sourceList = query.list();

            logger.info("getProductSource sourceList.size() " + sourceList.size());
            prodSrcMap = new HashMap<String, String>();
            if (sourceList != null && sourceList.size() > 0) {
                java.util.Iterator<Object[]> srcIter = sourceList.iterator();
                while (srcIter.hasNext()) {
                    Object[] srcObject = (Object[]) srcIter.next();
                    String sourceId = (String) srcObject[0];
                    String sourceName = (String) srcObject[1];
                    prodSrcMap.put(sourceId, sourceName);
                }
            }
            logger.info("getProductSource Ends");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return prodSrcMap;
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public Map<String, String> getSKUforStyle(String searchkey, String Searchtype) throws Exception {
        Map<String, String> prodSrcMap = null;
        try {
            logger.info("getSKUforStyle Starts");
            /*
 select sku.ida3masterreference,p.ida3masterreference,
sku.branchiditerationinfo from skuarev sku , prodarev p where sku.ida3a12 = p.ida3masterreference ;
             */
            String column = "";
            if (Searchtype.equalsIgnoreCase("stylenumber")) {
                column = "prodarev.styleNumber";

            } else {
                column = "prodarev.slotNumber";
            }
            Query query = sessionFactory
                    .getCurrentSession()
                    .createQuery(
                            "select sku.branchIterationInfo, sku.skuStyleName from SKUaRevVo sku , "
                            + "ProductVo prodarev  WHERE "
                            + " sku.ida3a12= prodarev.ida3masterreference AND "
                            + " " + column + " = '" + searchkey + "'  AND sku.skuStyleName is not null  ");
            List<Object[]> sourceList = query.list();

            logger.info("getSKUforStyle sourceList.size() " + sourceList.size());
            prodSrcMap = new HashMap<String, String>();
            if (sourceList != null && sourceList.size() > 0) {
                java.util.Iterator<Object[]> srcIter = sourceList.iterator();
                while (srcIter.hasNext()) {
                    Object[] srcObject = (Object[]) srcIter.next();
                    String sourceId = (String) srcObject[0];
                    String sourceName = (String) srcObject[1];
                    prodSrcMap.put(sourceId, sourceName);
                }
            }

            logger.info("getSKUforStyle Ends");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return prodSrcMap;
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public List<String> getSpecification(String searchkey, String Searchtype) throws Exception {
        List<String> specList = null;
        try {
            logger.info("getSpecification Starts");
            String column = "";
            if (Searchtype.equalsIgnoreCase("stylenumber")) {
                column = "p.styleNumber";

            } else {
                column = "p.slotNumber";
            }

            Query query = sessionFactory
                    .getCurrentSession()
                    .createQuery(
                            " select spec.att1  from FlexSpecificationVo spec ,"
                            + " ProductVo p where spec.ida3a12 = p.ida3masterreference and "
                            + " " + column + " = '" + searchkey + "'  ");
            specList = query.list();
            logger.info("specification list size() " + specList.size());

            /*if (specList != null && specList.size() > 0) {
			java.util.Iterator<Object[]> srcIter = specList.iterator();
			while (srcIter.hasNext()) {
				Object[] srcObject = (Object[]) srcIter.next();
				String specName = (String) srcObject[0];
				prodSrcList.add(specName);
			}
		}*/
            logger.info("getSpecification Ends");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return specList;

    }

    @SuppressWarnings("unchecked")
    @Transactional
    public Map<String, String> getskuDetails(String searchkey, String searchtype, String skuStyleName) throws Exception {
        Map<String, String> prodSkuMap = null;
        try {
            logger.info("getskuDetails Starts");
            String column = "";
            String datecolumn = "";
            if (searchtype.equalsIgnoreCase("stylenumber")) {
                column = "prodarev.styleNumber";
                datecolumn = "prdarev.att44 = '" + searchkey + "'";
            } else {
                column = "prodarev.slotNumber";
                datecolumn = "prdarev.att42 = '" + searchkey + "'";
            }
            String skuquery = " select sku.introDate,case when sku.earlyReleaseDate is null then to_char(prodarev.earlyReleaseDate, 'mm/dd/yyyy') else to_char(sku.earlyReleaseDate, 'mm/dd/yyyy') END as earlyReleaseDate,sku.finishedGoodColorCode,sku.deleteDate,"
                    + "sku.skuFactoryStoreType,sku.fsIntroDate,sku.fsDeleteDate from SKUaRevVo sku , ProductVo prodarev  WHERE "
                    + " sku.ida3a12= prodarev.ida3masterreference AND  sku.skuStyleName = '" + skuStyleName.trim() + "' AND "
                    + " " + column + " = '" + searchkey.trim() + "'   AND sku.skuStyleName is not null  ";

            Query query = sessionFactory
                    .getCurrentSession()
                    .createQuery(skuquery);

            List<Object[]> skuList = query.list();
            logger.info("getskuDetails size() " + skuList.size());

            prodSkuMap = new HashMap<String, String>();

            if (skuList != null && skuList.size() > 0) {

                java.util.Iterator<Object[]> srcIter = skuList.iterator();
                while (srcIter.hasNext()) {
                    Object[] srcObject = (Object[]) srcIter.next();
                    /*	String sourceId = (String) srcObject[0];
	  				String sourceName = (String) srcObject[1];*/
                    // prodSkuMap.put("introDate", (String) srcObject[0]);
                    if (srcObject[1] != null) {
                        prodSkuMap.put("earlyReleaseDate",srcObject[1].toString());
                    }
                    // prodSkuMap.put("finishedGoodColorCode", (String) srcObject[2]);
                    //prodSkuMap.put("deleteDate", (String) srcObject[3]);
                    prodSkuMap.put("skuFactoryStoreType", (String) srcObject[4]);
                    // prodSkuMap.put("fsIntroDate", (String) srcObject[5]);
                    //prodSkuMap.put("fsDeleteDate", (String) srcObject[6]);
                }
            }

            System.out.println("before prodSkuMap :: " + prodSkuMap);
            String query1 = ProductDeptConstant.sqldates + " sku.att1 = '" + skuStyleName + "' and " + datecolumn;
            System.out.println("query1 :: " + query1);
            Query sqlquery1 = sessionFactory.getCurrentSession().createSQLQuery(query1);
            List<Object[]> skudateList = sqlquery1.list();
            System.out.println("skudateList " + skudateList);

            if (skudateList != null && skudateList.size() > 0) {
                System.out.println("size " + skudateList.size());
                java.util.Iterator<Object[]> srcIter1 = skudateList.iterator();
                while (srcIter1.hasNext()) {
                    Object[] srcObject = (Object[]) srcIter1.next();

                    if (srcObject[0] != null) {
                        String deleteDate = getAttributeValue("PRODUCT",
                                "DELETEDATE", (String) srcObject[0]);
                        prodSkuMap.put("deleteDate", deleteDate);
                    }
                    if (srcObject[1] != null) {
                        String fsdeleteDate = getAttributeValue("PRODUCT",
                                "FSDELETEDATE", (String) srcObject[1]);
                        prodSkuMap.put("fsDeleteDate", fsdeleteDate);
                    }
                    if (srcObject[2] != null) {
                        String fsintrodate = getAttributeValue("PRODUCT",
                                "FSINTRODATE", (String) srcObject[2]);
                        prodSkuMap.put("fsintroDate", fsintrodate);
                    }
                    if (srcObject[3] != null) {
                        String introdate = getAttributeValue("PRODUCT",
                                "INTRODATE", (String) srcObject[3]);
                        prodSkuMap.put("introDate", introdate);
                    }

                    prodSkuMap.put("finishedGoodColorCode", (String) srcObject[4]);

//                                        prodSkuMap.put("deleteDate", (String) srcObject[0]);
//                                        prodSkuMap.put("fsDeleteDate", (String) srcObject[1]);
//                                        prodSkuMap.put("fsIntroDate", (String) srcObject[2]);
//	  				prodSkuMap.put("introDate", (String) srcObject[3]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        System.out.println(" after prodSkuMap :: " + prodSkuMap);
        logger.info("getskuDetails Ends");
        return prodSkuMap;

    }

    @SuppressWarnings("unchecked")
    @Transactional
    public String getAttributeValue(String objectName, String attributeName, String valueKey) throws Exception {

        try {
            logger.info("getAttributeValue Starts");
            if (valueKey != null && (attributeName.equalsIgnoreCase("FSINTRODATE") || attributeName.equalsIgnoreCase("FSDELETEDATE"))) {
                Query queryintro = sessionFactory.openSession().createSQLQuery("select sg.att1 as Display from seasongroup sg where sg.latestiterationinfo = 1 and sg.ida3a10 = 14636 and sg.ida3masterreference = '" + valueKey + "'");
                List intro = queryintro.list();
                if (intro != null && intro.size() > 0) {
                    return intro.get(0).toString();
                }

            } else if (valueKey != null && (attributeName.equalsIgnoreCase("INTRODATE") || attributeName.equalsIgnoreCase("DELETEDATE"))) {
                Query querydelete = sessionFactory.openSession().createSQLQuery("select sg.att1 as Display from seasongroup sg where sg.latestiterationinfo =1 and sg.ida3a10=14671 and sg.ida3masterreference = '" + valueKey + "'");
                List deletedate = querydelete.list();
                if (deletedate != null && deletedate.size() > 0) {
                    return deletedate.get(0).toString();
                }
            } else {

                logger.info("Object Name ::: " + objectName + " :: Attribut Name " + attributeName + " :: " + valueKey);
                if (objectName != null && attributeName != null && valueKey != null) {
                    Query query = sessionFactory
                            .getCurrentSession()
                            .createQuery(
                                    " SELECT attrStore.display  FROM AttributeStoreVo  attrStore WHERE   "
                                    + "  attrStore.objectName='" + objectName + "'  AND attrStore.attributeName = '" + attributeName.trim() + "' "
                                    + " AND attrStore.valueKey ='" + valueKey.trim() + "'  ");

                    List<String> attributeList = query.list();
                    logger.info("Attribute Size :::" + attributeList.size());
                    if (attributeList != null && attributeList.size() > 0) {

                        java.util.Iterator<String> attrIter = attributeList.iterator();
                        while (attrIter.hasNext()) {
                            valueKey = attrIter.next();
                        }
                    }
                }
            }
            logger.info("getAttributeValue Ends");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return valueKey;

    }

    @Override
    @Transactional
    public List<LCSMoaObjectVo> getMerchandisingPricingList(String searchkey, String searchtype, String deptType) throws Exception {
        List<LCSMoaObjectVo> lcsMoaObjVo = null;
        try {
            logger.info("getMerchandisingPricingList Starts");
            String wherelause = "";
            if (searchtype.equalsIgnoreCase("stylenumber")) {
                wherelause = " and  prodarev.att44 = '" + searchkey + "' and dropped =0 order by rownum ";
            } else {
                wherelause = " and  prodarev.att42 = '" + searchkey + "' and dropped =0 order by rownum ";
            }
            String sqltobeexecuted = "";
            if (deptType.equalsIgnoreCase(ProductDeptConstant.merchFullPrice)) {
                sqltobeexecuted = sqltobeexecuted + ProductDeptConstant.merchandisingFullPrice + wherelause;
            } else if (deptType.equalsIgnoreCase(ProductDeptConstant.merchCJIPrice)) {
                sqltobeexecuted = sqltobeexecuted + ProductDeptConstant.merchandisingCJIPrice + wherelause;
            } else if (deptType.equalsIgnoreCase(ProductDeptConstant.merchFactoryPrice)) {
                sqltobeexecuted = sqltobeexecuted + ProductDeptConstant.merchandisingFactoryPrice + wherelause;
            }

            System.out.println("********** Merch Pricing :: " + deptType + " :: " + sqltobeexecuted);
            Query query = sessionFactory.getCurrentSession().createSQLQuery(sqltobeexecuted);

            // resultwithAliastoBean
            lcsMoaObjVo = query.setResultTransformer(Transformers.aliasToBean(LCSMoaObjectVo.class)).list();
            /*if(resultwithAliastoBean != null & resultwithAliastoBean.size() > 0){
    		lcsMoaObjVo = (LCSMoaObjectVo)resultwithAliastoBean.get(0);
    	}*/

            logger.info("getMerchandisingPricingList Ends");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return lcsMoaObjVo;

    }

    @Override
    @Transactional
    public SkuAdditionalDetailsVo getSkuAccessoriesDeptsql(String searchkey, String searchtype, String dept, String skuStyleName, String deptType) throws Exception {
        SkuAdditionalDetailsVo skuAdditionalDetailsVo = null;
        try {
            logger.info("getSkuAccessoriesDeptsql Starts");
            String wherelause = "";
            if (searchtype.equalsIgnoreCase("stylenumber")) {
                wherelause = " from skuarev  sku,Prodarev prdarev where sku.ida3a12 = prdarev.ida3masterreference "
                        + " AND  sku.att1 ='" + skuStyleName + "' and  prdarev.att44 = '" + searchkey + "' and prdarev.att49 = '" + dept + "' ";
            } else {
                wherelause = " from skuarev  sku,Prodarev prdarev where sku.ida3a12 = prdarev.ida3masterreference "
                        + " AND  sku.att1 ='" + skuStyleName + "' and  prdarev.att42 = '" + searchkey + "' and prdarev.att49 = '" + dept + "' ";
            }
            String sqltobeexecuted = "";
            if (deptType.equalsIgnoreCase(ProductDeptConstant.accessoriesDepartType)) {
                sqltobeexecuted = sqltobeexecuted + ProductDeptConstant.skuDeptSql + wherelause;
            } else if (deptType.equalsIgnoreCase(ProductDeptConstant.skuFragranceDepartType)) {
                sqltobeexecuted = sqltobeexecuted + ProductDeptConstant.skuDeptSql + ProductDeptConstant.skuFragranceDeptSql + wherelause;
            } else if (deptType.equalsIgnoreCase(ProductDeptConstant.skuFootWearDepartType)) {
                sqltobeexecuted = sqltobeexecuted + ProductDeptConstant.skuDeptSql + ProductDeptConstant.skuFootWearDeptSql + wherelause;
            }else if(deptType.equalsIgnoreCase(ProductDeptConstant.skuSunWearDepartType)){
                sqltobeexecuted = sqltobeexecuted + ProductDeptConstant.skuDeptSql + ProductDeptConstant.skuSunwearDeptSql+ wherelause;
            }else {
                sqltobeexecuted = sqltobeexecuted + ProductDeptConstant.skuDeptSql + wherelause;
            }

            Query query = sessionFactory.getCurrentSession().createSQLQuery(sqltobeexecuted);

            List resultwithAliastoBean = query.setResultTransformer(Transformers.aliasToBean(SkuAdditionalDetailsVo.class)).list();
            if (resultwithAliastoBean != null & resultwithAliastoBean.size() > 0) {
                skuAdditionalDetailsVo = (SkuAdditionalDetailsVo) resultwithAliastoBean.get(0);
            }

            logger.info("getSkuAccessoriesDeptsql Ends");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return skuAdditionalDetailsVo;

    }

    @Override
    @Transactional
    public Map<String, String> getAttributelist(String attributelevel, String attributename) throws Exception {
        Map<String, String> attributemap = null;
        try {
            logger.info("getAttributelist Starts");

            /*  Query query1 = sessionFactory
                .getCurrentSession()
                .createQuery(
                        " SELECT distinct attrStore.attributeName  FROM AttributeStoreVo  attrStore WHERE  attrStore.status ='ACTIVE' "
                        + " AND attrStore.objectName='" + attributelevel + "' ");
    List attributeList1 = query1.list();
        System.out.println("*************************"+attributeList1);*/
            Query query = sessionFactory
                    .getCurrentSession()
                    .createQuery(
                            " SELECT attrStore.valueKey,attrStore.display  FROM AttributeStoreVo  attrStore WHERE  attrStore.status ='ACTIVE' "
                            + " AND attrStore.objectName='" + attributelevel + "'  AND attrStore.attributeName = '" + attributename + "' ");

            List attributeList = query.list();
            System.out.println(attributename + "*************************" + attributeList.size());
            attributemap = new HashMap<String, String>();

            for (int i = 0; i < attributeList.size(); i++) {

                Object[] vo = (Object[]) attributeList.get(i);
                //String attri  = String.valueOf(attributeList.get(i).getValueKey());
                // System.out.println("attri :: "+vo[0]);
                attributemap.put("" + vo[0], "" + vo[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return attributemap;
    }

    @Override
    @Transactional
    public List<LCSMoaObjectVo> getSkuSizeScaleMoa(String searchkey, String searchtype, String skuStyleName) throws Exception {
        List<LCSMoaObjectVo> sizeScaleMoa = null;
        try {
            logger.info("getSkuSizeScaleMoa Starts");

//and sku.ida3a12= 90559991   AND sku.att1 ='VMI'  and moa.dropped=0  order by rownum
            String whereclause = "";
            if (searchtype.equalsIgnoreCase("stylenumber")) {
                //String masterid = getProductMasterid("where att44 = '" + searchkey + "'");
                whereclause = "  and sku.ida3a12 = (select ida3masterreference from prodarev  where att44 = '" + searchkey + "')   AND  sku.att1 ='" + skuStyleName + "' and moa.dropped=0  order by rownum";

            } else {
                //String masterid = getProductMasterid("where att42 = '" + searchkey + "'");
                //whereclause = "  sku.ida3a12  = '" + masterid + "' AND  sku.att1 ='" + skuStyleName + "' and moa.dropped=0  order by rownum";
                whereclause = "  and sku.ida3a12 = (select ida3masterreference from prodarev  where att42 = '" + searchkey + "')   AND  sku.att1 ='" + skuStyleName + "' and moa.dropped=0  order by rownum";
            }
            System.out.println("Size Scale Query :: " + ProductDeptConstant.skuSizeScaleMoaSql + whereclause);
            Query query = sessionFactory.getCurrentSession().createSQLQuery(ProductDeptConstant.skuSizeScaleMoaSql + whereclause);
            sizeScaleMoa = query.setResultTransformer(Transformers.aliasToBean(LCSMoaObjectVo.class)).list();

            System.out.println("Size Scale Query :: " + sizeScaleMoa);
            logger.info("getSkuSizeScaleMoa Ends");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return sizeScaleMoa;

    }

    @Override
    @Transactional
    public List<LCSMoaObjectVo> getSkuMerchandisingPrice(String searchkey, String searchtype, String skuStyleName) throws Exception {
        List<LCSMoaObjectVo> skuMerchandisingPrice = null;
        try {
            logger.info("getSkuMerchandisingPrice Starts");
            String whereclause = "";

            if (searchtype.equalsIgnoreCase("stylenumber")) {
                whereclause = "  prodarev.att44 = '" + searchkey + "' and sku.att1 = '" + skuStyleName + "' and moa.dropped =0 order by rownum ";
            } else {
                whereclause = "  prodarev.att42 = '" + searchkey + "' and sku.att1 = '" + skuStyleName + "' and moa.dropped =0 order by rownum ";
            }

            String sql = ProductDeptConstant.skuMerchansingFullPrice + whereclause;

            Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);

            //List resultwithAliastoBean 
            skuMerchandisingPrice = query.setResultTransformer(Transformers.aliasToBean(LCSMoaObjectVo.class)).list();
            /*if(resultwithAliastoBean != null & resultwithAliastoBean.size() > 0){
    		skuMerchandisingPrice = (LCSMoaObjectVo)resultwithAliastoBean.get(0);
    	}*/
            logger.info("getSkuMerchandisingPrice Ends");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return skuMerchandisingPrice;

    }

    @Override
    @Transactional
    public List<String> getSkuGroup(String searchkey, String searchtype) throws Exception {
        List<String> skuGroupList = null;
        try {
            logger.info("getSkuGroup Starts");
            String whereclause = "";

            if (searchtype.equalsIgnoreCase("stylenumber")) {
                whereclause = " and prodarev.att44 = '" + searchkey + "' ";
            } else {
                whereclause = " and prodarev.att42 = '" + searchkey + "' ";
            }
            String sql = ProductDeptConstant.skuGroupTable + whereclause;
            System.out.println("sql :: " + sql);

            Query query = sessionFactory.getCurrentSession().createSQLQuery(ProductDeptConstant.skuGroupTable + whereclause);

            skuGroupList = query.list();
            logger.info("getSkuGroup Starts");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return skuGroupList;
    }

    ////select * from DOCUMENTOBJECTRELATIONSHIP where referringobject ='LCSProduct' and refobjectcolumvalue= <product.ida3masterreference>
    @SuppressWarnings("unchecked")
    @Transactional
    public List<DocumentObjectRelationship> getProductDocumentDetails(String prodNum, String type) throws Exception {
        List<DocumentObjectRelationship> prodDocList = null;
        try {
            String andclause = "";
            if (type.equals("stylenumber")) {
                andclause = " and prod.att44 = '" + prodNum + "'";
            } else {
                andclause = " and  prod.att42 = '" + prodNum + "'";
            }
            String sql = "   select docObjRelShip.DocumentName \"documentName\",docObjRelShip.DocumentType \"documentType\","
                    + " docObjRelShip.DocumentTypeId \"documentTypeId\",docObjRelShip.primaryFileName \"primaryFileName\" from  "
                    + " DOCUMENTOBJECTRELATIONSHIP docObjRelShip,    prodarev prod where  docObjRelShip.referringobject ='LCSProduct' "
                    + " and docObjRelShip.refobjectcolumvalue= prod.ida3masterreference  " + andclause;

            logger.info("getProductDocumentDetails Starts");

            Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
            prodDocList = query.setResultTransformer(
                    Transformers.aliasToBean(DocumentObjectRelationship.class)).list();

            logger.info("getProductDocumentDetails size() " + prodDocList.size());

            logger.info("getProductDocumentDetails Ends");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return prodDocList;

    }

    private String getProductMasterid(String value) throws Exception {
        System.out.println("q1 :: " + "select ida3masterreference from prodarev " + value);
        try {
            Query q1 = sessionFactory.getCurrentSession().createSQLQuery("select ida3masterreference from prodarev " + value);

            List lst = q1.list();
            if (lst != null && lst.size() > 0) {
                return (String) lst.get(0);
            } else {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }finally{
            return "";
        }
        //return "";
    }

	@Override
	@Transactional
	public Map<String, String> initReportLoad() {


        logger.info("initReportLoad Starts");
        System.out.println("Get attribute starts here.................................");
        
      String  queryString=        		  
    	  " SELECT attrStore.valueKey,attrStore.attributeName,attrStore.display  FROM AttributeStoreVo  attrStore WHERE "
    	  + " attrStore.attributeName in ('DEPARTMENT')  and attrStore.display NOT LIKE 'R%' "    			  
    	  + " and attrStore.objectName = 'PRODUCT' order by attrStore.valueKey ";

        Query query = sessionFactory
                .getCurrentSession()
                .createQuery(queryString);
                        //" SELECT attrStore.valueKey,attrStore.attributeName,attrStore.display  FROM AttributeStoreVo  attrStore WHERE  attrStore.attributeName in ('GENDER','LOOK','COLLECTION','SUBCOLLECTION','CLASS','SUBCLASS','DEPARTMENT','INTRODATE','FSINTRODATE','ATTITUDE','SILHOUETTE','SIZES','TEAM','MATERIAL','CHANNELEXCLUSIVE','FACTORYSTORETYPEPRODUCT','COLLABORATION','LIFECYCLESTATE','STYLEGROUP') order by attrStore.display ");
       
        List attributeList = query.list();      

        logger.info("*************************" + attributeList.size());
       Map<String, String> attributemap = new TreeMap<String, String>();
        
        for (int i = 0; i < attributeList.size(); i++) {
            Object[] vo = (Object[]) attributeList.get(i);
            attributemap.put("" + vo[0], "" + vo[2]);
        }     
        
        System.out.println("I am ending the getAttributelist "+attributemap);
        return attributemap;
	
	}
}
