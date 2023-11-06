/**
 *
 */
package com.plmviewer.service;

import java.util.Hashtable;

import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.LdapContext;
import javax.security.auth.login.AccountException;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.plmviewer.controller.LoginController;
import com.plmviewer.dao.UserActionDAO;
import com.plmviewer.exceptions.BusinessException;
import com.plmviewer.model.User;
import com.plmviewer.model.UserAction;
import java.sql.Timestamp;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author uthanasekarapandian
 *
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserActionDAO useractionimpl;
    
    //UserActionDAO useractionimpl;
    private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
    private static final String CONTEXT_FACTORY_CLASS = "com.sun.jndi.ldap.LdapCtxFactory";

    public User authenticate(String username, String password) throws BusinessException {
        logger.info("authenticate Starts");
        User user = null;
        if (username == null || password == null || username.trim().length() == 0 || password.trim().length() == 0) {
            throw new BusinessException("Username or password is empty");
        }

        try {
            Hashtable<Object, Object> env = new Hashtable<Object, Object>();
            env.put(Context.INITIAL_CONTEXT_FACTORY, CONTEXT_FACTORY_CLASS);
            env.put(Context.PROVIDER_URL, "ldap://glbdcsvip.global.coach.com:389");

            env.put(Context.SECURITY_PRINCIPAL, username);
            env.put(Context.SECURITY_CREDENTIALS, password);

            /*LdapContext conn = new InitialLdapContext(env,null);
	                conn.close();*/
            DirContext ctx = new InitialDirContext(env);
            String userId = username.substring(0, username.indexOf("@"));
            user = getUserBasicAttributes(userId, ctx);
            ctx.close();
            
            System.out.println("started");
            UserAction action = new UserAction();
            action.setUsername(username);
            action.setAction("Login");
            action.setActiontime(new Timestamp(new Date().getTime()));
            System.out.println("going to save");
            
            useractionimpl.addaction(action);
            logger.info("authenticate Ends");
            return user;
        } catch (CommunicationException exp) {
            logger.error("authenticate ::" + exp.getMessage());
            user = new User();
            user.setSuccessMsg(false);
            throw new BusinessException("Not able to communicate to LDAP Server");

            //return false;
        } catch (NamingException e) {
            logger.info("authenticate ::" + e.getMessage());
            user = new User();
            user.setSuccessMsg(false);
            throw new BusinessException(e.getMessage());
        }catch(Exception e){
            e.printStackTrace();
            logger.info("authenticate ::" + e.getMessage());
            user = new User();
            user.setSuccessMsg(false);
            throw new BusinessException(e.getMessage());
        }

    }

    private User getUserBasicAttributes(String username, DirContext ctx) {
        User user = null;
        try {
            SearchControls constraints = new SearchControls();
            constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
            String[] attrIDs = {"distinguishedName", "sn", "givenname", "mail", "telephonenumber"};
            //	constraints.setReturningAttributes(attrIDs); 

            NamingEnumeration answer = ctx.search("DC=global,DC=coach,DC=com", "sAMAccountName=" + username, constraints);
            user = new User();
            if (answer.hasMore()) {
                Attributes attrs = ((SearchResult) answer.next()).getAttributes();
                System.out.println("distinguishedName " + attrs.get("distinguishedName"));
                System.out.println("givenname " + attrs.get("givenname").get());
                System.out.println("sn " + attrs.get("sn").get());
                user.setUserName(username);
                user.setFirstName(attrs.get("givenname").get().toString());
                user.setLastName(attrs.get("sn").get().toString());
                user.setEmail(attrs.get("mail").get().toString());
                user.setSuccessMsg(true);
            } else {
                user.setSuccessMsg(false);
                logger.debug("Invalid User");

            }
        } catch (Exception ex) {
            logger.error("Invalid User  " + ex.getMessage());
        }
        return user;
    }
}
