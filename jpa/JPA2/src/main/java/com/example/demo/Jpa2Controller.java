package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Service
public class Jpa2Controller {
	
	@Autowired
	private Jpa2Repository jpa2repository;
	
	public List<User> getAllUser(){
		return (List<User>)	jpa2repository.findAll();
	}
	public Optional<User> getUserById(Long id) {
		return jpa2repository.findById(id);
	}
	public User saveUser(User User) {
        return jpa2repository.save(User);
    }

    public void deleteUser(Long id) {
       jpa2repository.deleteById(id);
    }
 

}
