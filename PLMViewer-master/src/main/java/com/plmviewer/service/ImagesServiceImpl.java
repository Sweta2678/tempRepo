/**
 *
 */
package com.plmviewer.service;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plmviewer.controller.ImagesController;
import com.plmviewer.dao.ImagesDao;
import com.plmviewer.model.ImageSearchVo;
import com.plmviewer.model.ImagesPageToDocLinkVo;
import java.util.Map;

/**
 * @author uthanasekarapandian
 *
 */
@Service("ImagesService")
public class ImagesServiceImpl implements ImagesService {

    @Autowired
    ImagesDao imagesDao;

    private static final Logger logger = LoggerFactory.getLogger(ImagesServiceImpl.class);

    public List<ImagesPageToDocLinkVo> getImagesSearchResultList(ImageSearchVo prodNum) {
        logger.info("getImagesSearchResultList Starts");
        List<ImagesPageToDocLinkVo> imagesPageToDocLinkVoList = imagesDao.getImagesSearchResultList(prodNum);
        logger.info("getImagesSearchResultList Ends");
        return imagesPageToDocLinkVoList;
    }

    public List<ImagesPageToDocLinkVo> getProductSpecImagesList(BigDecimal docMasterId) {
        logger.info("getProductSpecImagesList Starts");
        List<ImagesPageToDocLinkVo> imagesPageToDocLinkVoList = imagesDao.getProductSpecImagesList(docMasterId);
        logger.info("getProductSpecImagesList Ends");
        return imagesPageToDocLinkVoList;
    }

    public List<ImagesPageToDocLinkVo> getProductImagesList(BigDecimal ida3MasterRef) {
        logger.info("getProductImagesList Starts");
        List<ImagesPageToDocLinkVo> imagesPageToDocLinkVoList = imagesDao.getProductImagesList(ida3MasterRef);
        logger.info("getProductImagesList Ends");
        return imagesPageToDocLinkVoList;
    }

    @Override
    public Map<String, Map<String, String>> getProductAttributes() {
        return imagesDao.getProductAttributeList();
    }
    

}
