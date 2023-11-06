package com.tapestry.moic.controller.interfaces;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tapestry.moic.constant.MoicConstant;

/**
 * The Interface IAuthController.
 * 
 * @version 0.0.1
 */
@Controller
@RequestMapping(value = MoicConstant.AUTH)
public interface IAuthController {

	@PostMapping(value = MoicConstant.LOGIN)
	@ResponseBody
	void login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			@RequestBody(required = false) String xml);
	
	@GetMapping(value = "test")
	@ResponseBody
	void test(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);

}