package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.User;
import com.example.repository.UserRepository;

/**
 * ユーザーのためのserviceです
 * @author ootomokenji
 *
 */
@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * IDからユーザー情報を取得します
	 * @param user IDだけがセットされたUserドメイン
	 * @return 情報が格納されたUserドメイン
	 */
	public User findById(User user) {
		return userRepository.findById(user);
	}
	
	/**
	 * Emailからユーザー情報を取得します
	 * @param user emailだけがセットされたUserドメイン
	 * @return 情報が格納されたUserドメイン
	 */
	public List<User> findByEmail(User user) {
		return userRepository.findByEmail(user);
	}
	
	/**
	 * ユーザー情報を登録します
	 * @param user 入力されたユーザー情報
	 * @return ユーザー情報
	 */
	public User insertUser(User user) {
		userRepository.insertUser(user);
		return user;		
	}

}
