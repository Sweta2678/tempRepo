/**
 *
 */
package com.plmviewer.controller;

import com.plmviewer.dao.UserActionDAO;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.plmviewer.exceptions.BusinessException;
import com.plmviewer.model.DocumentObjectRelationship;
import com.plmviewer.model.ProductVo;
import com.plmviewer.model.SKUaRevVo;
import com.plmviewer.model.SkuAdditionalDetailsVo;
import com.plmviewer.model.User;
import com.plmviewer.model.UserAction;
import com.plmviewer.model.UserLoginVo;
import com.plmviewer.service.ProductService;
import com.plmviewer.util.PLMUtil;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author uthanasekarapandian
 *
 */
@Controller
public class LandingController {
    
    @Autowired
    ProductService productService;
    @Autowired
    PLMUtil plmUtil;
    @Autowired
    UserActionDAO useractionimpl;
    
    private static final Logger logger = LoggerFactory.getLogger(LandingController.class);
    
    @RequestMapping(path = "/userlogin/LandingLayout/", method = RequestMethod.GET)
    public ModelAndView landingLoad(HttpSession session) throws Exception {
        String landingUrl = "";
        try {
            logger.info("LandingController load");
            User user = (User) session.getAttribute("user");
            landingUrl = "UserLogin";
            if (user != null) {
                logger.info("user.getFirstName() " + user.getFirstName());
                landingUrl = "LandingLayout";
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return new ModelAndView(landingUrl);
    }
    
    @RequestMapping(path = "/userlogin/LandingLayout/intiLoad", method = RequestMethod.GET)
    public @ResponseBody
    List initLoad() throws Exception {
        logger.info("LandingController load");
        List searchList = new ArrayList(1);
        try {
            searchList.add(0, plmUtil.getProductSearch());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return searchList;
    }
    
    @SuppressWarnings("unchecked")
    @RequestMapping(path = "/userlogin/LandingLayout/subSearchLoad", method = RequestMethod.GET)
    public @ResponseBody
    List subSearchLoad(@RequestParam("productSearch") String productSearch) throws Exception {
        logger.info("LandingController subSearchLoad Starts");
        System.out.println("productSearch  " + productSearch);
        List subSearchList = new ArrayList(1);
        try {
            if (productSearch != null
                    && (productSearch.equalsIgnoreCase("Product") || productSearch
                    .equalsIgnoreCase("Product-StyleNumber"))) {
                subSearchList.add(0, plmUtil.getProductStyleSearch());
            } else if (productSearch != null
                    && (productSearch.equalsIgnoreCase("Material") || productSearch
                    .equalsIgnoreCase("Material-CMNumber"))) {
                subSearchList.add(0, plmUtil.getMaterialSearch());
            } else if (productSearch.equalsIgnoreCase("Document")) {
                subSearchList.add(0, plmUtil.getDocumentSearch());
            }
            logger.info("LandingController subSearchLoad Ends");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return subSearchList;
    }
    
    @RequestMapping(path = "/userlogin/LandingLayout/getProductList", method = RequestMethod.GET)
    public @ResponseBody
    List<ProductVo> getProductList(@RequestParam("productSearch") String productSearch,
            @RequestParam("searchValue") String searchValue, @RequestParam("productsearchby") String searchtype, HttpSession session) throws Exception {
        
        logger.info("LandingController getProductList");
        List<ProductVo> productList = null;
        logger.debug("productSearch::  " + productSearch + "  :: searchValue::  " + searchValue + " Search Type :: " + searchtype);
        
        productList = productService.getProduct(searchValue, searchtype);

        //logger.error("LandingController getProductList  ::: " + be.getMessage());
        System.out.println("**********************************");
        
        logger.info("LandingController getProductList");
        addUserDetails(session.getAttribute("user"), "PRODUCT_DETAIL");
        return productList;
    }
    
    @RequestMapping(path = "/userlogin/LandingLayout/getProductSkuList", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, String> getProductSkuDetails(@RequestParam("styleNumber") String styleNumber,
            @RequestParam("skuStyleName") String skuStyleName, @RequestParam("productsearchby") String searchtype,HttpSession session) throws Exception {
        logger.info("LandingController getProductSkuDetails");
        logger.debug("styleNumber::  " + styleNumber + " ::  skuStyleName::  " + skuStyleName);
        
        Map<String, String> productSkudetails = productService.getProductSkuDetails(styleNumber.trim(), searchtype.trim(), skuStyleName.trim());
        logger.info("LandingController getProductSkuDetails");
        addUserDetails(session.getAttribute("user"), "PRODUCT_SKU_DETAIL");
        return productSkudetails;
    }
    
    @RequestMapping(path = "/userlogin/LandingLayout/getProductSkuAdditionalList", method = RequestMethod.GET)
    public @ResponseBody
    SkuAdditionalDetailsVo getProductSkuAdditionalDetails(@RequestParam("styleNumber") String styleNumber,
            @RequestParam("skuStyleName") String skuStyleName, @RequestParam("dept") String dept, @RequestParam("productsearchby") String searchtype) throws Exception {
        logger.info("LandingController getProductSkuAdditionalDetails");
        logger.debug("styleNumber::  " + styleNumber + " ::  skuStyleName::  " + skuStyleName);
        
        SkuAdditionalDetailsVo skuAdditionalDetailsVo = productService.getProductSkuAdditionalDetails(styleNumber.trim(), searchtype.trim(),
                skuStyleName.trim(), dept.trim());
        logger.info("LandingController getProductSkuAdditionalDetails");
        return skuAdditionalDetailsVo;
    }
    
    @RequestMapping(path = "/userlogin/LandingLayout/getProductDocumentList", method = RequestMethod.GET)
    public @ResponseBody
    List<DocumentObjectRelationship> getProductDocumentDetails(@RequestParam("prodNum") String prodNum,
            @RequestParam("type") String type,HttpSession session) throws Exception {
        logger.info("getProductDocumentDetails Starts");
        List<DocumentObjectRelationship> docObjRelList = productService.getProductDocumentDetails(prodNum, type);
        logger.info("getProductDocumentDetails Ends");
        addUserDetails(session.getAttribute("user"), "PRODUCT_DOCUMENT_DETAIL");
        return docObjRelList;
    }
    
    private void addUserDetails(Object username, String useraction) {
        User user = (User)username;
        System.out.println(username+"For action ********* "+useraction);
        UserAction action = new UserAction();
        action.setUsername(user.getUserName());
        action.setAction(useraction);
        action.setActiontime(new Timestamp(new Date().getTime()));
        useractionimpl.addaction(action);
    }
    
    

    
    @RequestMapping(path = "/userlogin/LandingLayout/initReportLoad", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE,
            headers = "Accept=application/json")
    public @ResponseBody
    Map<String,String>  initReportLoad() {
        return productService.initReportLoad();
    }
    
    
}
