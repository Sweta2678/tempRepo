package com.tapestry.moic.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.tapestry.moic.constant.MoicConstant;
import com.tapestry.moic.controller.interfaces.IRolesInternalController;
import com.tapestry.moic.dto.RolesDto;
import com.tapestry.moic.response.SuccessResponse;
import com.tapestry.moic.response.interfaces.MoicResponse;
import com.tapestry.moic.service.interfaces.IRolesInternalService;

/**
 * The Class RolesInternalController.
 *
 * @version 0.0.1
 */
@Component
public class RolesInternalController implements IRolesInternalController {

	/**
	 * Field LOGGER
	 */
	private Logger LOGGER = LoggerFactory.getLogger(RolesInternalController.class);

	@Autowired
	private IRolesInternalService rolesInternalService;

	@Override
	public MoicResponse getRolePermissions(String token) {
		LOGGER.debug("Inside getRolePermissions controller token :: " + token);
		if(null != token && !token.isEmpty()) {
			RolesDto rolesDto = rolesInternalService.getRolePermissions(token);
			if(rolesDto != null)
				return new SuccessResponse(true, HttpStatus.OK, MoicConstant.SUCCESS_MESSAGE, rolesDto);
			else
				return new SuccessResponse(true, HttpStatus.OK, MoicConstant.USER_OR_ROLES_DOES_NOT_EXIST, null);
		}
		else {
			return new SuccessResponse(true, HttpStatus.OK, MoicConstant.INVALID_TOKEN, null);
		}
	}

}
