/**
 *
 */
package com.plmviewer.controller;

import com.plmviewer.dao.UserActionDAO;
import com.plmviewer.exceptions.BusinessException;
import java.math.BigDecimal;
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

import com.plmviewer.model.DocumentSearchResult;
import com.plmviewer.model.DocumentSearchVo;
import com.plmviewer.model.DocumentVo;
import com.plmviewer.model.MaterialPricingSearchResult;
import com.plmviewer.model.PLMExceptiondata;
import com.plmviewer.model.User;
import com.plmviewer.model.UserAction;
import com.plmviewer.service.DocumentService;
import com.plmviewer.service.DocumentServiceImpl;
import com.plmviewer.service.MaterialService;
import java.sql.Timestamp;
import java.util.Date;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author uthanasekarapandian
 *
 */
@Controller
public class DocumentController {

    @Autowired
    DocumentService documentService;
    @Autowired
    UserActionDAO useractionimpl;

    private static final Logger logger = LoggerFactory.getLogger(DocumentController.class);

    @RequestMapping(path = "/userlogin/searchLayout/initDocumentLoad", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE,
            headers = "Accept=application/json")
    public @ResponseBody
    Map<String, String> documentSearchInitLoad(@RequestParam("attributelevel") String attributelevel,
            @RequestParam("attributename") String attributename, HttpSession session) throws Exception {
        logger.info("Document Search Starts");
        System.out.println("Controller Started");
        try {

            Map<String, String> mp = documentService.getDocumentList();
            return mp;
        } catch (Exception e) {
            e.printStackTrace();;
            throw e;
        }

    }

    @RequestMapping(path = "/userlogin/LandingLayout/getDocumentSearchList", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE,
            headers = "Accept=application/json")

    public @ResponseBody
    List<DocumentSearchResult> documentSearchlist(@RequestBody DocumentSearchVo searchCriteria, HttpSession session) {
        logger.info("Document Search Starts");
        BigDecimal docType = new BigDecimal(0);
        if (searchCriteria.getDocumentType() != null && !searchCriteria.getDocumentType().trim().equals("")) {
            docType = new BigDecimal(searchCriteria.getDocumentType());
        } else {
            docType = new BigDecimal(0);
        }
        addUserDetails(session.getAttribute("user"), "DOCUMENT_SEARCH");
        return documentService.getDocumentSearchResultList(searchCriteria.getDocumentName(), docType);
    }

    @RequestMapping(path = "/userlogin/LandingLayout/getDocumentSearchResult", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            headers = "Accept=application/json")
    public @ResponseBody
    List<DocumentSearchResult> getDocumentSearchResult(@RequestParam("docName") String docName,
            @RequestParam("ida3a11") BigDecimal ida3a11, HttpSession session) {
        logger.info("getDocumentSearchResult Starts");
        BigDecimal docType = new BigDecimal(0);
        if (ida3a11 != null && !ida3a11.equals(new BigDecimal(0))) {
            docType = ida3a11;
        } else {
            docType = new BigDecimal(0);
        }
        List<DocumentSearchResult> documentSearchResult = documentService.getDocumentSearchResultList(docName, docType);
        addUserDetails(session.getAttribute("user"), "DOCUMENT_SEARCH");
        logger.info("getDocumentSearchResult Ends");
        return documentSearchResult;
    }

    @RequestMapping(path = "/userlogin/LandingLayout/getDocumentDetails", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            headers = "Accept=application/json")
    public @ResponseBody
    DocumentVo getDocumentDetails(@RequestParam("docName") String docName,
            @RequestParam("ida3a11") BigDecimal ida3a11, HttpSession session) {
        logger.info("getDocumentSearchResult Starts");
        DocumentVo documentVo = documentService.getDocumentDetails(docName, ida3a11);
        logger.info("getDocumentSearchResult Ends");
        addUserDetails(session.getAttribute("user"), "DOCUMENT_DETAIL");
        return documentVo;
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
