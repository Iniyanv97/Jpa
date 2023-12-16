package com.example.demo;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Jpa2Application {

	public static void main(String[] args) {
		
		
		ConfigurableApplicationContext context = SpringApplication.run(Jpa2Application.class, args);
		Jpa2Controller jpa2controller=context.getBean(Jpa2Controller.class);
		User user = new User();user.setId((long)1);user.setName("Ini");
		jpa2controller.saveUser(user);
		
		List<User> x = jpa2controller.getAllUser();
		System.out.println(x.toString());
		
		
		
	}

}
