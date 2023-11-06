package com.plmviewer.controller;

import com.plmviewer.dao.UserActionDAO;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plmviewer.model.BOMModel;
import com.plmviewer.model.ImagesPageToDocLinkVo;
import com.plmviewer.model.User;
import com.plmviewer.model.UserAction;
import com.plmviewer.service.BOMService;
import com.plmviewer.service.BOMServiceImpl;
import java.sql.Timestamp;
import java.util.Date;
import javax.servlet.http.HttpSession;

@Controller
public class BOMController {

    @Autowired
    BOMService bomService;
    @Autowired
    UserActionDAO useractionimpl;
    private static final Logger logger = LoggerFactory.getLogger(BOMController.class);

    @RequestMapping(path = "/userlogin/LandingLayout/getBomDetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE,
            headers = "Accept=application/json")
    public @ResponseBody
    BOMModel getBomDetails(@RequestParam("ida3MasterRef") String ida3MasterRef,
            @RequestParam("bomPartId") String bomPartId, HttpSession session) {

        logger.info("get Bom details Starts");
        System.out.println("********ida3MasterRef :: **************" + ida3MasterRef);
        System.out.println("********bomPartId :: **************" + bomPartId);
        addUserDetails(session.getAttribute("user"), "BOM_DETAILS");
        try {
            return bomService.getBOMdata(ida3MasterRef, bomPartId);
        } catch (Exception e) {
            logger.error("Error while getting the bom Data");
            e.printStackTrace();
        }
        return null;
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
