package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.User;
import com.example.form.CreateUserForm;
import com.example.service.UserService;

/**
 * ユーザーのためのRestControllerです
 * @author ootomokenji
 *
 */
@RestController
public class CreateUserController {
	
	@Autowired
	private UserService userService;

	/**
	 * ユーザーを登録します
	 * @param form 入力されたユーザー情報
	 * @param result
	 * @return メッセージ（成功/失敗）
	 */
	@PostMapping(value = "/user")
	public Map<String, Object> insertUser(@RequestBody @Validated CreateUserForm form,BindingResult result) {
		
		Map<String, Object>map = new HashMap<>();

		if (result.hasErrors()) {
			List<ObjectError>list =  result.getAllErrors();
			String errorMessage = "";
			for (ObjectError objectError : list) {
				errorMessage = errorMessage +" "+ objectError.getDefaultMessage();
			}
			map.put("status", "error");
			map.put("message", errorMessage);
			return map;
		}
		
		User user = new User();
		BeanUtils.copyProperties(form, user);

		if (userService.findByEmail(user).size() != 0) {
			map.put("status", "error");
			map.put("message", "メールアドレスが重複しています");
			return map;
		}
		
		try {
			user = userService.insertUser(user) ;
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", "error");
			return map;
		}
		
		map.put("status", "success!!");
		map.put("message", "OK");
		map.put("user", user);
		return map;
	}
	
	
	/**
	 * ユーザーをロードします
	 * @param id 受け取ったユーザーID
	 * @return IDから取得したユーザー情報
	 */
	@GetMapping(value = "/user/{id}")
	public User findById(@PathVariable Integer id) {
		
		User user = new User();
		user.setId(id);
		
		user = userService.findById(user);
		
		return user;
	}
}
