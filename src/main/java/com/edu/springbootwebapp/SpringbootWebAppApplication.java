package com.edu.springbootwebapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.edu.springbootwebapp") // Search in the provide package and resolve all Spring bean and their dependencies, if not exist it scans everywhere by default
public class SpringbootWebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootWebAppApplication.class, args);
	}

}
