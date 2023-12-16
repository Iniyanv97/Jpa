package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Validated

@Controller
public class Jpa2IndexPageController {

	private final Jpa2Controller jpa2Controller;
	
	public Jpa2IndexPageController(Jpa2Controller jpa2Controller)
	{
		this.jpa2Controller=jpa2Controller;
	}
	
	@GetMapping("/")
	public String Home (Model model)
	{
		List<User> user = jpa2Controller.getAllUser();
		model.addAttribute("records",user);
		return "index";
	}
	@PostMapping("/submitForm")
	public String CreateForm(@RequestParam @Valid String name, Model model)
	{
		
		@Valid User user = new User();
	
		user.setName(name);
		jpa2Controller.saveUser(user);
		List<User> user2 = jpa2Controller.getAllUser();
		model.addAttribute("records",user2);
		return "index";
			
	}
	@RequestMapping("/Create")
	public String Create()
	{
		return "create";
	}
	@PostMapping("/readForm")
	public String CreateForm(@RequestParam long id, Model model)
	{
		
		User user4=new User();
		
		Optional<User> user3=jpa2Controller.getUserById(id);
		if(user3.isPresent())
		{
			user4=user3.get();
			model.addAttribute("employee",user4);
		}
		else
		{
			model.addAttribute("employee",user4);	
			model.addAttribute("message","Id not found");
		}
		
		
		
		return "read";
			
	}
	
	@PostMapping("/update")
	public String UpdateForm(@RequestParam long id, String name, Model model)
	{
		User existingUser=new User();
		Optional<User> user3=jpa2Controller.getUserById(id);
		if(user3.isPresent())
		{
			existingUser=user3.get();
			existingUser.setId(id);
			existingUser.setName(name);
			
		        jpa2Controller.saveUser(existingUser);
		        model.addAttribute("message","Updated successfully On the Index");
		        return "update";
		}
		else
		{
			
			model.addAttribute("message","Id not found");
			return "update";
		}
	}
	@PostMapping("/delete")
	public String deleteUser(@RequestParam long id, Model model)
	{
		Optional<User> user3=jpa2Controller.getUserById(id);
		if(user3.isPresent())
		{
		
			
		        jpa2Controller.deleteUser(id);
		        model.addAttribute("message","Deleted successfully On the Index");
		        return "delete";
		}
		else
		{
			
			model.addAttribute("message","Id not found");
			return "delete";
		}
	}
	
}
