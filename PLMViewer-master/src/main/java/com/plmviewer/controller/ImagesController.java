/**
 *
 */
package com.plmviewer.controller;

import com.plmviewer.dao.UserActionDAO;
import java.math.BigDecimal;
import java.util.List;

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

import com.plmviewer.model.DocumentSearchResult;
import com.plmviewer.model.DocumentSearchVo;
import com.plmviewer.model.ImageSearchVo;
import com.plmviewer.model.ImagesPageToDocLinkVo;
import com.plmviewer.model.User;
import com.plmviewer.model.UserAction;
import com.plmviewer.service.DocumentService;
import com.plmviewer.service.ImagesService;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;

/**
 * @author uthanasekarapandian
 *
 */
@Controller
public class ImagesController {

    @Autowired
    ImagesService imagesService;
    @Autowired
    UserActionDAO useractionimpl;

    private static final Logger logger = LoggerFactory.getLogger(ImagesController.class);

    @RequestMapping(path = "/userlogin/LandingLayout/getImagesSearchList", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE,
            headers = "Accept=application/json")
    public @ResponseBody
    List<ImagesPageToDocLinkVo> imagesSearchList(@RequestBody ImageSearchVo searchvo,HttpSession session) {
        logger.info("imagesSearchList Starts");
        System.out.println("Started");
        List<ImagesPageToDocLinkVo> imgPagesToDocLinkVoList = imagesService.getImagesSearchResultList(searchvo);
        logger.info("imagesSearchList Ends");
        addUserDetails(session.getAttribute("user"), "IMAGE_SEARCH");
        return imgPagesToDocLinkVoList;
    }

    @RequestMapping(path = "/userlogin/LandingLayout/getProductSpecImagesList", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            headers = "Accept=application/json")
    public @ResponseBody
    List<ImagesPageToDocLinkVo> getProductSpecImagesList(@RequestParam("docMasterId") BigDecimal docMasterId,HttpSession session) {
        logger.info("getProductSpecImagesList Starts");
        List<ImagesPageToDocLinkVo> imgPagesToDocLinkVoList = imagesService.getProductSpecImagesList(docMasterId);
          addUserDetails(session.getAttribute("user"), "IMAGE_SEARCH");
      
        logger.info("getProductSpecImagesList Ends");
        return imgPagesToDocLinkVoList;
    }

    @RequestMapping(path = "/userlogin/LandingLayout/getProductImagesList", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            headers = "Accept=application/json")
    public @ResponseBody
    List<ImagesPageToDocLinkVo> getProductImagesList(@RequestParam("ida3MasterRef") BigDecimal ida3MasterRef,HttpSession session) {
        logger.info("getProductImagesList Starts");
        List<ImagesPageToDocLinkVo> imgPagesToDocLinkVoList = imagesService.getProductImagesList(ida3MasterRef);
             addUserDetails(session.getAttribute("user"), "IMAGE_SEARCH");
     
        logger.info("getProductImagesList Ends");
        return imgPagesToDocLinkVoList;
    }

    @RequestMapping(path = "/userlogin/searchLayout/initImageLoad", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE,
            headers = "Accept=application/json")
    public @ResponseBody
    Map<String, Map<String, String>> getimagedetails() {
        return imagesService.getProductAttributes();
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
