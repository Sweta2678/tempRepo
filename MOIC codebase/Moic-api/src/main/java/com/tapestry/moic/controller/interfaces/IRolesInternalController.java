package com.tapestry.moic.controller.interfaces;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tapestry.moic.constant.MoicConstant;
import com.tapestry.moic.response.interfaces.MoicResponse;

/**
 * The Interface IRolesInternalController.
 * 
 * @version 0.0.1
 */
@RestController
@RequestMapping(value = MoicConstant.API_BASE_URL)
public interface IRolesInternalController {

	@PostMapping(value = MoicConstant.GET_ROLE_PERMISSIONS)
	@ResponseBody
	MoicResponse getRolePermissions(@RequestParam("token") String token);
}