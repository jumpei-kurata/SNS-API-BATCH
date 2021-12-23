package com.example.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.User;
import com.example.form.CreateUserForm;
import com.example.service.UserService;

@RestController
public class CreateUserController {
	
	@Autowired
	private UserService userService;

	@PostMapping(value = "/user")
	public User createUser(@RequestBody CreateUserForm form) {
		
		User user = new User();
		BeanUtils.copyProperties(form, user);
		
		return userService.insertUser(user);
//		
//		Map<String, String>map = new HashMap<>();
//		
//	map.put("message", "success!!");
//	
//	return map;
	}
	@GetMapping(value = "/user/{id}")
	public User createUser(@PathVariable Integer id) {
		
		User user = new User();
		user.setId(id);
		
		user = userService.findById(user);
		
		return user;
	}
}
