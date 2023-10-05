package com.jose.backend.usersapp.backendusersapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.jose.backend.usersapp.backendusersapp")
public class BackendUsersappApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendUsersappApplication.class, args);
	}

}
