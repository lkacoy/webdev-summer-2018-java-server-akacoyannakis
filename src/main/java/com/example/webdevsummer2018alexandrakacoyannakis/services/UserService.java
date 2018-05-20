package com.example.webdevsummer2018alexandrakacoyannakis.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevsummer2018alexandrakacoyannakis.models.User;
import com.example.webdevsummer2018alexandrakacoyannakis.repositories.UserRepository;

@RestController
public class UserService {
	
	@Autowired
	UserRepository repository;
	public List<User> findAllUsers() {
		return (List<User>) repository.findAll();
	}
}
