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
import com.plmviewer.model.DocumentToFileLinkVo;
import com.plmviewer.model.DocumentVo;
import com.plmviewer.util.DocumentConstant;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author uthanasekarapandian
 *
 */
@Repository
public class DocumentDaoImpl implements DocumentDao {

    @Autowired
    SessionFactory sessionFactory;
    private static final Logger logger = LoggerFactory.getLogger(DocumentDaoImpl.class);

    @Transactional
    @SuppressWarnings("unchecked")
    public List<DocumentSearchResult> getDocumentSearchResultList(
            String docName, BigDecimal ida3a11) {
        List<DocumentSearchResult> documentSearchResult = null;
        try {

            logger.info("getDocumentSearchResultList Starts");
            String searchSql = DocumentConstant.SearchDocument;
            if (docName != null && !docName.trim().equalsIgnoreCase("")) {
                if (docName.trim().contains("*")) {
                    searchSql = searchSql + " and UPPER(att1) like '" + whereLikeClause(docName).toUpperCase() +"' and lcsdocument.ida3a11<>6401";
                } else {
                    searchSql = searchSql + " and UPPER(att1) = '" + docName.toUpperCase() +"' and lcsdocument.ida3a11<>6401" ;
                }
            }
            if (ida3a11 != null && !(ida3a11.equals(new BigDecimal(0)))) {
                if (searchSql.trim().length() > 0) {
                    searchSql = searchSql + " and ida3a11 =  " + ida3a11;
                } else {
                    searchSql = searchSql + "  ida3a11 =  " + ida3a11;
                }
            }
            searchSql = searchSql + " and STATECHECKOUTINFO <> 'wrk' ";

            Query query = sessionFactory.openSession().createSQLQuery(searchSql);

            documentSearchResult = query.setResultTransformer(
                    Transformers.aliasToBean(DocumentSearchResult.class)).list();

            logger.info("getDocumentSearchResultList size() " + documentSearchResult.size());

            logger.info("getDocumentSearchResultList Ends");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return documentSearchResult;
    }

    @Transactional
    public DocumentVo getDocumentDetails(String docName, BigDecimal ida3a11) {
        logger.info("getDocumentDetails Starts");
        DocumentVo documentVo = null;
        try {

            Query query = sessionFactory.openSession().createSQLQuery(getDocumentSql(ida3a11) + " and att1 = '" + docName + "'");
            List<DocumentVo> documentVoResult = query.setResultTransformer(
                    Transformers.aliasToBean(DocumentVo.class)).list();
            if (documentVoResult != null) {
                documentVo = documentVoResult.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        logger.info("getDocumentDetails Ends");
        return documentVo;
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public List<DocumentToFileLinkVo> getDocumenttoFileLink(BigDecimal ida3MasterRef, String fileType) {
        List<DocumentToFileLinkVo> documentToFileLinkResult = null;
        try {
            logger.info("getDocumenttoFileLink Starts");
            String sql = "";
            if (fileType.equalsIgnoreCase("Primary")) {
                sql = DocumentConstant.documentToPrimaryFileLink;
            } else {
                sql = DocumentConstant.documentToSecondaryFileLink;
            }
            Query query = sessionFactory.openSession().createSQLQuery(sql + "  lnk.documentmasterid = " + ida3MasterRef + " ");
            documentToFileLinkResult = query.setResultTransformer(
                    Transformers.aliasToBean(DocumentToFileLinkVo.class)).list();

            logger.info("getDocumenttoFileLink Ends");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return documentToFileLinkResult;
    }

    public String getDocumentSql(BigDecimal ida3a11) {
        String sql = "";
        try {

            if (ida3a11.equals(new BigDecimal(28290875))) {
                sql = DocumentConstant.designDocuments;
            } else if (ida3a11.equals(new BigDecimal(28290897))) {
                sql = DocumentConstant.constructionDetails;
            } else if (ida3a11.equals(new BigDecimal(62450372))) {
                sql = DocumentConstant.fabricPlacementDocuments;
            } else if (ida3a11.equals(new BigDecimal(28290960))) {
                sql = DocumentConstant.fillers;
            } else if (ida3a11.equals(new BigDecimal(51112237))) {
                sql = DocumentConstant.jobAidsandShortcuts;
            } else if (ida3a11.equals(new BigDecimal(28291069))) {
                sql = DocumentConstant.packagingAndPackingDetails;
            } else if (ida3a11.equals(new BigDecimal(28291103)) || ida3a11.equals(new BigDecimal(28291104))) {
                sql = DocumentConstant.patternsDev_LinePresentation;
            } else if (ida3a11.equals(new BigDecimal(28290878))) {
                sql = DocumentConstant.productRevisionNotice;
            } else if (ida3a11.equals(new BigDecimal(122403831))) {
                sql = DocumentConstant.productSafetyCompliance;
            } else if (ida3a11.equals(new BigDecimal(172578037))) {
                sql = DocumentConstant.tradeCompliance;
            } else if (ida3a11.equals(new BigDecimal(28291107))) {
                sql = DocumentConstant.wearablesConstructionDetails;
            } else if (ida3a11.equals(new BigDecimal(46588034)) || ida3a11.equals(new BigDecimal(60100568)) || ida3a11.equals(new BigDecimal(172592286)) || ida3a11.equals(new BigDecimal(28290874))
                    || ida3a11.equals(new BigDecimal(28290959)) || ida3a11.equals(new BigDecimal(28291068)) || ida3a11.equals(new BigDecimal(28291105)) || ida3a11.equals(new BigDecimal(28291106))
                    || ida3a11.equals(new BigDecimal(5078)) || ida3a11.equals(new BigDecimal(6401)) || ida3a11.equals(new BigDecimal(88647753)) || ida3a11.equals(new BigDecimal(73998205)) || ida3a11.equals(new BigDecimal(134525515))) {
                sql = DocumentConstant.comments + " and ida3a11= " + ida3a11;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sql;
    }

    @Override
    public Map<String, String> getDocumentList() throws Exception {
        try {
            Query query = sessionFactory
                    .openSession()
                    .createSQLQuery(
                            " SELECT attrStore.valueKey,attrStore.display  FROM generalAttributeStore  attrStore "
                            + "   where attrStore.objectname = 'DOCUMENT' and attrStore.attributename = 'DOCUMENTFLEXTYPE'  order by attrStore.display  ");

            List attributeList = query.list();

            logger.info("*************************" + attributeList.size());

            Map<String, String> map = new TreeMap<String, String>();
            for (int i = 0; i < attributeList.size(); i++) {
                Object[] vo = (Object[]) attributeList.get(i);
                map.put("" + vo[0], "" + vo[1]);
            }
            return map;
        } catch (Exception e) {
            throw e;
        }
    }

    private String whereLikeClause(String sqlWhere) {
        String tmpSql = "";
        try {
            logger.info("whereLikeClause Starts :: " + sqlWhere);

            if (sqlWhere.contains("*")) {
                tmpSql = "" + sqlWhere.replaceAll("\\*", "%");
            } else {
                tmpSql = sqlWhere;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tmpSql.trim();
    }

    @Override
    public String getAttributeValue(String attributetype, String attributename, String valuekey) {
        String value = "";
        logger.info("getAttributeValue Starts");
        if (valuekey != null && (attributename.equalsIgnoreCase("FSINTRODATE") || attributename.equalsIgnoreCase("FSDELETEDATE"))) {
            Query queryintro = sessionFactory.openSession().createSQLQuery("select sg.att1 as Display from seasongroup sg where sg.latestiterationinfo = 1 and sg.ida3a10 = 14636 and sg.ida3masterreference = '" + valuekey + "'");
            List intro = queryintro.list();
            if (intro != null && intro.size() > 0) {
                value = intro.get(0).toString();
            }

        } else if (valuekey != null && (attributename.equalsIgnoreCase("INTRODATE") || attributename.equalsIgnoreCase("DELETEDATE"))) {
            Query querydelete = sessionFactory.openSession().createSQLQuery("select sg.att1 as Display from seasongroup sg where sg.latestiterationinfo =1 and sg.ida3a10=14671 and sg.ida3masterreference = '" + valuekey + "'");
            List deletedate = querydelete.list();
            if (deletedate != null && deletedate.size() > 0) {
                value = deletedate.get(0).toString();
            }
        } else {

            Query query = sessionFactory
                    .openSession()
                    .createSQLQuery(
                            " SELECT display FROM productattributestore  WHERE  objectName='" + attributetype + "'  AND attributeName = '" + attributename + "' AND valuekey = '" + valuekey + "'");

            List attributeList = query.list();
            System.out.println(attributename + "*************************" + attributeList.size());
            if (attributeList != null && attributeList.size() > 0) {
                if (attributeList.get(0) != null) {
                    value = (String) attributeList.get(0);
                }
            }
        }
        return value;
    }

    @Override
    public String getGeneralAttributeValue(String attributename, String valuekey) {
        String value = "";
        Query query = sessionFactory
                .openSession()
                .createSQLQuery(
                        " SELECT display FROM generalattributestore  WHERE  attributeName = '" + attributename + "' AND valuekey = '" + valuekey + "'");

        List attributeList = query.list();
        System.out.println(attributename + "*************************" + attributeList.size());
        if (attributeList != null && attributeList.size() > 0) {
            if (attributeList.get(0) != null) {
                value = (String) attributeList.get(0);
            }
        }
        return value;
    }

}
