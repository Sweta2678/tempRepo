/**
 *
 */
package com.plmviewer.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plmviewer.dao.DocumentDao;
import com.plmviewer.model.DocumentSearchResult;
import com.plmviewer.model.DocumentToFileLinkVo;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.plmviewer.model.DocumentVo;
import java.util.StringTokenizer;

/**
 * @author uthanasekarapandian
 *
 */
@Service("documentService")
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    DocumentDao documentDao;

    private static final Logger logger = LoggerFactory.getLogger(DocumentServiceImpl.class);

    @Override
    public List<DocumentSearchResult> getDocumentSearchResultList(
            String docName, BigDecimal ida3a11) {
        logger.info("getDocumentSearchResultList Starts");
        List<DocumentSearchResult> docSearchResultList = documentDao.getDocumentSearchResultList(docName, ida3a11);
        logger.info("getDocumentSearchResultList Ends");
        return docSearchResultList;
    }

    @Override
    public Map<String, String> getDocumentList() throws Exception {
        try {
            return documentDao.getDocumentList();
        } catch (Exception e) {
            throw e;
        }

    }

    public DocumentVo getDocumentDetails(String docName, BigDecimal ida3a11) {
        logger.info("getDocumentDetails Starts");
        DocumentVo documentVo = documentDao.getDocumentDetails(docName, ida3a11);
        documentVo.setIntendedUsers(splitDocumentData(documentVo.getIntendedUsers()));
        documentVo.setDepartment(splitDocumentData(documentVo.getDepartment()));

        List<DocumentToFileLinkVo> docToPrimaryFileLinkVo = documentDao.getDocumenttoFileLink(documentVo.getIda3masterreference(), "Primary");
        List<String> primaryFileList = new ArrayList<String>();
        Iterator primaryFileIt = docToPrimaryFileLinkVo.iterator();
        while (primaryFileIt.hasNext()) {
            DocumentToFileLinkVo docToFile = (DocumentToFileLinkVo) primaryFileIt.next();
            if (docToFile.getPrimaryFile() != null) {
                if (docToFile.getPrimaryFile().contains(",")) {
                    String[] primaryFile = docToFile.getPrimaryFile().split("\\,");
                    for (int i = 0; i < primaryFile.length; i++) {
                        String tmpPrimaryFile = primaryFile[i];
                        primaryFileList.add(tmpPrimaryFile);
                    }

                } else {
                    primaryFileList.add(docToFile.getPrimaryFile());
                }
            }

        }
        documentVo.setPrimaryFileList(primaryFileList);
        List<DocumentToFileLinkVo> docToSecFileLinkVo = documentDao.getDocumenttoFileLink(
                documentVo.getIda3masterreference(), "Secondary");
        List<String> secFileList = new ArrayList<String>();
        Iterator it = docToSecFileLinkVo.iterator();
        while (it.hasNext()) {
            DocumentToFileLinkVo docToFile = (DocumentToFileLinkVo) it.next();
            if (docToFile.getSecondaryFile() != null) {
                if (docToFile.getSecondaryFile().contains(",")) {
                    String[] secFile = docToFile.getSecondaryFile().split("\\,");
                    for (int i = 0; i < secFile.length; i++) {
                        String tmpSecFile = secFile[i];
                        secFileList.add(tmpSecFile);
                    }

                } else {
                    secFileList.add(docToFile.getSecondaryFile());
                }
            }

        }
        documentVo.setSecondaryFileList(secFileList);
        logger.info("getDocumentDetails Ends");
        getValueforKeys(documentVo);
        return documentVo;
    }

    public String splitDocumentData(String matData) {
        logger.info("splitDocumentData Starts");
        String splitedData = "";
        if (matData != null && !matData.trim().equals("")) {
            String pattern = "(\\|\\~\\*\\~\\|)";
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(matData);
            splitedData = m.replaceAll(",");

            logger.info("splitDocumentData Ends");
        }
        return splitedData;
    }

    private void getValueforKeys(DocumentVo documentVo) {
        if (documentVo.getDepartment() != null && documentVo.getDepartment().length() > 0) {
            StringTokenizer token = new StringTokenizer(documentVo.getDepartment(), ",");
            String tempdept = "";
            while (token.hasMoreTokens()) {
                tempdept = tempdept + documentDao.getAttributeValue("PRODUCT", "DEPARTMENT", token.nextToken());
            }
            if (tempdept.endsWith(",") && tempdept.length() > 1) {
                tempdept = tempdept.substring(0, tempdept.length() - 1);
            }
            documentVo.setDepartment(tempdept);
        }
        if (documentVo.getDocClass() != null && documentVo.getDocClass().length() > 0) {
            documentVo.setDocClass(documentDao.getAttributeValue("PRODUCT", "CLASS", documentVo.getDocClass()));
        }
        if (documentVo.getSubClass() != null && documentVo.getSubClass().length() > 0) {
            documentVo.setSubClass(documentDao.getAttributeValue("PRODUCT", "SUBCLASS", documentVo.getSubClass()));
        }
        if (documentVo.getCollection() != null && documentVo.getCollection().length() > 0) {
            documentVo.setCollection(documentDao.getAttributeValue("PRODUCT", "COLLECTION", documentVo.getCollection()));
        }
        if (documentVo.getSubCollection() != null && documentVo.getSubCollection().length() > 0) {
            documentVo.setSubCollection(documentDao.getAttributeValue("PRODUCT", "SUBCOLLECTION", documentVo.getSubCollection()));
        }
        if (documentVo.getIntroDate() != null && documentVo.getIntroDate().length() > 0) {
            documentVo.setIntroDate(documentDao.getAttributeValue("PRODUCT", "INTRODATE", documentVo.getIntroDate()));
        }

        if (documentVo.getIntendedUsers() != null && documentVo.getIntendedUsers().length() > 0) {
           // documentVo.setIntendedUsers(documentDao.getGeneralAttributeValue("INTENDEDUSERS", documentVo.getIntendedUsers()));
            StringTokenizer token = new StringTokenizer(documentVo.getIntendedUsers(), ",");
            String tempdept = "";
            while (token.hasMoreTokens()) {
                tempdept = tempdept +","+ documentDao.getGeneralAttributeValue("INTENDEDUSERS", token.nextToken());
            }
            if (tempdept.endsWith(",") && tempdept.length() > 1) {
                tempdept = tempdept.substring(0, tempdept.length() - 1) ;
            }
            documentVo.setIntendedUsers(tempdept);

        }
        if (documentVo.getInternalOffice() != null && documentVo.getInternalOffice().length() > 0) {
            documentVo.setInternalOffice(documentDao.getGeneralAttributeValue("INTERNALOFFICE", documentVo.getInternalOffice()));
        }
        if (documentVo.getPrnType() != null && documentVo.getPrnType().length() > 0) {
            documentVo.setPrnType(documentDao.getGeneralAttributeValue("PRNTYPE", documentVo.getPrnType()));
        }

    }

}
