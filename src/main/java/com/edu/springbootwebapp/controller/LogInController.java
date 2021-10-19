package com.edu.springbootwebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.edu.springbootwebapp.service.LoginService;

@Controller
@SessionAttributes("name")
public class LogInController {

	// Dependency tightly coupled
	// LoginService service = new LoginService();

	// Dependency Injection - Injected automatically
	@Autowired
	LoginService service;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	// @ResponseBody returns String as text to the browser
	public String showLoginPage() {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String showWelcomePage(ModelMap model, @RequestParam String name, @RequestParam String password) {
		boolean isValidUser = service.validateUser(name, password);
		
		if (isValidUser) {
			model.put("name", name);
			model.put("password", password);
			return "welcome";
		} else
            model.put("errorMessage", "Invalid Credentials!!");
			return "login";
	}

}
