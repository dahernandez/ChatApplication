package com.patroclos.controllers;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.patroclos.security.AuthenticationFacade;

@Controller
public class DefaultController {

	@Autowired
	private AuthenticationFacade authenticationFacade;


	@RequestMapping(value = "/user", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> user() {		
		Authentication authentication = authenticationFacade.getAuthentication();
		return Collections.singletonMap("name",authentication.getName());	
	}	

}
