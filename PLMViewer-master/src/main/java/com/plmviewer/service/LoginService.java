/**
 * 
 */
package com.plmviewer.service;

import javax.security.auth.login.LoginException;

import com.plmviewer.exceptions.BusinessException;
import com.plmviewer.model.User;

/**
 * @author uthanasekarapandian
 *
 */
public interface LoginService {
	 public User authenticate(String username, String password) throws  BusinessException;
}
