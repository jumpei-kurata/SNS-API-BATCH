package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.User;
import com.example.repository.UserRepository;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User findById(User user) {
		return userRepository.findById(user);
	}
	
	public List<User> findByEmail(User user) {
		return userRepository.findByEmail(user);
	}
	
	public User insertUser(User user) {
		user.setId( userRepository.insertUser(user));
		return user;		
	}

}
