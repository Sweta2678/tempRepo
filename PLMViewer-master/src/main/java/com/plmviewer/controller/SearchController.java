/**
 *
 */
package com.plmviewer.controller;

import com.plmviewer.dao.UserActionDAO;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plmviewer.exceptions.BusinessException;
import com.plmviewer.model.AdvSearchVo;
import com.plmviewer.model.ProductVo;
import com.plmviewer.model.User;
import com.plmviewer.model.UserAction;
import com.plmviewer.model.searchVo;
import com.plmviewer.service.ProductService;
import com.plmviewer.service.SearchService;
import com.plmviewer.service.SpecificationService;
import java.sql.Timestamp;
import java.util.Date;
import javax.servlet.http.HttpSession;

/**
 * @author uthanasekarapandian
 *
 */
@Controller
public class SearchController {

    @Autowired
    SearchService searchService;
    @Autowired
    UserActionDAO useractionimpl;
    private static final Logger logger = LoggerFactory.getLogger(SearchController.class);

    @RequestMapping(path = "/userlogin/searchLayout/loadadvancesearch", method = RequestMethod.GET)
    public @ResponseBody
    String advSearchInitLoad() {
        logger.info("loadadvancesearch load");
        String resp = "advSearch";
        return resp;
    }

    @RequestMapping(path = "/userlogin/searchLayout/initLoad", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE,
            headers = "Accept=application/json")
    public @ResponseBody
    Map<String, Map<String, String>> advSearchInitLoad(@RequestParam("attributelevel") String attributelevel,
            @RequestParam("attributename") String attributename,HttpSession session) {
        logger.info("advSearchInitLoad Starts");
        System.out.println("attributelevel :: " + attributelevel);
        System.out.println("attributename :: " + attributename);

        return searchService.getattribute(attributelevel, attributename);
    }

    @RequestMapping(path = "/userlogin/LandingLayout/getSearchProductList", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE,
            headers = "Accept=application/json")
    public @ResponseBody
    List<searchVo> getSearchProductList(@RequestBody AdvSearchVo searchCriteria,HttpSession session) {

        logger.info("SearchController getSearchProductList");
        List<searchVo> productList = null;
        AdvSearchVo AdvSearchCriteria = new AdvSearchVo();
        AdvSearchCriteria = searchCriteria;
        logger.info("Search Criteria Style Name::  " + AdvSearchCriteria.getStyleName());

        productList = searchService.getProductListDetail(AdvSearchCriteria);
          addUserDetails(session.getAttribute("user"), "PRODUCT_SEARCH");
      
        logger.info("SearchController getSearchProductList");
        return productList;
    }

    private void addUserDetails(Object username, String useraction) {
        System.out.println("For action ********* " + useraction);
        UserAction action = new UserAction();
        action.setUsername(((User) username).getUserName());
        action.setAction(useraction);
        action.setActiontime(new Timestamp(new Date().getTime()));
        useractionimpl.addaction(action);
    }
}
