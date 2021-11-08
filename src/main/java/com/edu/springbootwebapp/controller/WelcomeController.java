package com.edu.springbootwebapp.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
// @SessionAttributes("name") its o.k. for user name to keep in session or to ask from Spring security
public class WelcomeController {

	// Dependency tightly coupled
	// LoginService service = new LoginService();

	@RequestMapping(value = "/", method = RequestMethod.GET)
	// @ResponseBody returns String as text to the browser
	public String showWelcomePage(ModelMap model) {
		model.put("name", getLoggedinUserName());
		return "welcome";
	}
	
	private String getLoggedinUserName() {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getName();
		
		if (principal instanceof UserDetails) {
			((UserDetails) principal).getUsername();
		}
		
		return principal.toString();
	}

}
