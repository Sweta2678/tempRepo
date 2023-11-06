/**
 *
 */
package com.plmviewer.controller;

/**
 * @author uthanasekarapandian
 *
 */
//import javax.security.auth.login.LoginException;
import com.plmviewer.dao.UserActionDAO;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plmviewer.exceptions.BusinessException;
import com.plmviewer.model.User;
import com.plmviewer.model.UserAction;
import com.plmviewer.model.UserLoginVo;
import com.plmviewer.service.LoginService;
import java.sql.Timestamp;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    LoginService loginService;
    @Autowired
    UserActionDAO useractionimpl;

    @RequestMapping(path = "/userlogin/postvalidate/", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE,
            headers = "Accept=application/json")
    public @ResponseBody
    User loginValidate(@RequestBody User user, HttpSession session) {
        logger.info("loginValidate Starts");
        boolean loginBool = false;
        User userData = null;
        try {
            if (user.getUserName() != null
                    && user.getPassword() != null
                    && !(user.getUserName().trim().equalsIgnoreCase("") && user
                    .getPassword().trim().equalsIgnoreCase(""))) {
                userData = loginService.authenticate(user.getUserName(),
                        user.getPassword());
            }
//		if (userData != null && userData.isSuccessMsg()){
            //user.setMessage("success");
            session.setAttribute("user", userData);

            /*}else{
			user.setMessage("failed");
		}
             */
        } catch (BusinessException le) {
            logger.info("Bsuiness Exception :" + le.getMessage());
            userData = new User();
            userData.setSuccessMsg(false);

        }
        logger.info("loginValidate ends");
        return userData;

    }

    @RequestMapping(path = "/userlogin/loginvalidate/", method = RequestMethod.POST)
    public @ResponseBody
    User logValidate(@RequestBody UserLoginVo user) {
        logger.info("loginValidate Starts");
        boolean loginBool = false;
        StringBuilder resposeData = new StringBuilder();
        User userData = null;
        try {
            if (userData.getUserName() != null
                    && userData.getPassword() != null
                    && !(userData.getUserName().trim().equalsIgnoreCase("") && user
                    .getPassword().trim().equalsIgnoreCase(""))) {
                resposeData.append("UserName: " + userData.getUserName() + " ");
                resposeData.append("password: " + userData.getPassword() + " ");
                userData = loginService.authenticate(userData.getUserName(),
                        user.getPassword());
            }
            if (userData != null && userData.isSuccessMsg()) {
                user.setMessage("success");

            } else {
                user.setMessage("failed");
            }
            resposeData.append("user: " + userData);
        } catch (BusinessException le) {
            logger.info("Bsuiness Exception :" + le.getMessage());
            user.setMessage("failed");

        }
        logger.info("loginValidate ends");
        /*	if(user.getMessage().trim().equalsIgnoreCase("success")){
			    return new ResponseEntity<String>("", HttpStatus.NO_CONTENT);
			  } else {
			    return new ResponseEntity<String>("failed", HttpStatus.BAD_REQUEST);
		 }*/
        return userData;

    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String loginInit() {
        logger.info("loginInit load");
        return "UserLogin";
    }

    @RequestMapping(path = "/logout/", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        logger.info("logout load");
        addUserDetails(session.getAttribute("user"), "Logout");
        session.removeAttribute("user");
        session.invalidate();

        return "UserLogin";
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
