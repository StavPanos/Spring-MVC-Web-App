package com.edu.springbootwebapp.service;

import org.springframework.stereotype.Service;

// @ComponentScan default - scan all packages for Spring Beans (@Component etc) 
// and injects the dependencies (manages their life cycle) where @AutoWired exists
// Spring Bean {Component (General Bean), Service (Business Logic), Repository (Database Persistent), Controller (MVC)}
@Service
public class LoginService {
	
	public boolean validateUser(String userid, String password) {
		//Panos, dummy
		return userid.equalsIgnoreCase("Panos")
				&& password.equalsIgnoreCase("dummy");
	}

}
