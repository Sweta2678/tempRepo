/**
 * 
 */
package com.plmviewer.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plmviewer.dao.SearchDao;
import com.plmviewer.model.AdvSearchVo;
import com.plmviewer.model.ProductVo;
import com.plmviewer.model.searchVo;

/**
 * @author uthanasekarapandian
 *
 */
@Service("SearchService")
public class SearchServiceImpl implements SearchService {

    private static final Logger logger = LoggerFactory.getLogger(SearchServiceImpl.class);

    @Autowired
    SearchDao searchDao;

    @Override
    public Map<String, Map<String, String>> getattribute(String attrtype, String attrname) {
        logger.info("getattribute Starts");
        Map<String, Map<String, String>> attributelist = searchDao.getAttributelist(attrtype, attrname);
        logger.info("getattribute Ends");
        return attributelist;
    }

    @Override
    public List<ProductVo> getProductList(AdvSearchVo search) {
        logger.info("getProductList Starts");
        List<ProductVo> productlist = searchDao.getProductList(search);
        logger.info("getProductList Ends");
        return productlist;
    }

    @Override
    public List<searchVo> getProductListDetail(AdvSearchVo search) {
        logger.info("getProductList Starts");
        List<searchVo> productlist = searchDao.getProductListDetail(search);
        logger.info("getProductList Ends");
        return productlist;

    }
}
