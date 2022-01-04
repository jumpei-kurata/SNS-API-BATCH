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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.User;
import com.example.form.CreateUserForm;
import com.example.form.LoginForm;
import com.example.form.UserEditForm;
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
	 * @return メッセージ（成功/失敗）ステータス（OK/error） ユーザー情報
	 */
	@PostMapping(value = "/signup")
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
		
		map.put("status", "OK");
		map.put("message", "success!!");
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
	
	/**
	 * ログインします
	 * @param form
	 * @param result
	 * @return メッセージ（成功/失敗）ステータス（OK/error） ユーザー情報
	 */
	@PostMapping(value = "/login")
	public Map<String, Object> login(@RequestBody @Validated LoginForm form,BindingResult result) {
		
		Map<String, Object> map = new HashMap<>();
		
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
		
		user.setEmail(form.getEmail());
		List<User> users =  userService.findByEmail(user);
		
		if (users.isEmpty()) {
			map.put("status", "error");
			map.put("message", "このメールアドレスのアカウントは存在しません");
			return map;
		}
		
		if( users.get(0).getPassword() .equals ( form.getPassword() ) ) {
			map.put("status", "OK");
			map.put("message", "success!!");
			map.put("user", users.get(0));
			return map;
		}else {
			map.put("status", "error");
			map.put("message", "パスワードが間違っています");
			return map;
		}
	}
	
	/**
	 * ユーザ情報を更新します
	 * @param form
	 * @param result
	 * @return
	 */
	@PatchMapping(value = "/user/edit")
	public Map<String, Object> userEdit(@RequestBody @Validated UserEditForm form, BindingResult result) {
		Map<String, Object> map = new HashMap<>();
		
		if ( (result.hasFieldErrors("email") && form.getEmail() != null) || (result.hasFieldErrors("password") && form.getPassword() != null) ) {
			map.put("status", "error");
			
			if (form.getEmail() != null) {
				map.put("emailMessage", result.getFieldError("email").getDefaultMessage());
			}
			if (form.getPassword() != null) {
				map.put("passwordMessage", result.getFieldError("password").getDefaultMessage());
			}
			
			return map;
		}
		
		User user = new User();
		user.setId(form.getId());
		user = userService.findById(user);
		
		if (!(form.getName() == null)) {
			user.setName(form.getName());
		}
		
		if (!(form.getAccountName() == null)) {
			user.setAccountName(form.getAccountName());
		}
		
		if (!(form.getEmail() == null)) {
			user.setEmail(form.getEmail());
		}
		
		if (!(form.getPassword() == null)) {
			user.setPassword(form.getPassword());
		}
		
		if (!(form.getHireDate() == null)) {
			user.setHireDate(form.getHireDate());
		}
		
		if (!(form.getServiceFk() == null)) {
			user.setServiceFk(form.getServiceFk());
		}
		
		if (!(form.getBirthDay() == null)) {
			user.setBirthDay(form.getBirthDay());
		}
		
		if (!(form.getIntroduction() == null)) {
			user.setIntroduction(form.getIntroduction());
		}
		
		userService.updateUser(user);
		
		map.put("status", "OK");
		map.put("message", "success!!");
		map.put("user", user);
		
		return map;
	}
	
}
