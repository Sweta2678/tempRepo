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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plmviewer.model.DocumentObjectRelationship;
import com.plmviewer.model.User;
import com.plmviewer.model.UserAction;
import com.plmviewer.service.ProductService;
import com.plmviewer.service.SpecificationService;
import java.sql.Timestamp;
import java.util.Date;
import javax.servlet.http.HttpSession;

/**
 * @author uthanasekarapandian
 *
 */
@Controller
public class SpecificationController {

    @Autowired
    SpecificationService specificationService;

    @Autowired
    UserActionDAO useractionimpl;

    private static final Logger logger = LoggerFactory.getLogger(SpecificationController.class);

    @RequestMapping(path = "/userlogin/LandingLayout/getSpecComponentList", method = RequestMethod.GET)
    public @ResponseBody
    List<Map<String, String>> getSpecComponentList(@RequestParam("specComponent") String specComponent, HttpSession session) {
        logger.info("SpecificationController getSpecComponentList Starts");
        logger.debug("specComponent::  " + specComponent);

        List<Map<String, String>> productSkudetails = specificationService.getSpecDetails(specComponent);
        addUserDetails(session.getAttribute("user"),"PRODUCT_SPECIFICATION_DETAIL");
        logger.info("SpecificationController getSpecComponentList Ends");
        return productSkudetails;
    }

    @RequestMapping(path = "/userlogin/LandingLayout/getSpecDocList", method = RequestMethod.GET)
    public @ResponseBody
    List<DocumentObjectRelationship> getSpecDocList(@RequestParam("specComponent") String specComponent,HttpSession session) {
        logger.info("SpecificationController getSpecDocList Starts");
        logger.debug("specComponent::  " + specComponent);

        List<DocumentObjectRelationship> specDocList = specificationService.getSpecDocList(specComponent);
        addUserDetails(session.getAttribute("user"),"PRODUCT_SPECIFICATION_DOCUMENT_DETAIL");
        logger.info("SpecificationController getSpecDocList Ends");
        return specDocList;
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
