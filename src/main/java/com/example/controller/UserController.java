package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.example.form.ConfirmMailForm;
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
public class UserController {
	
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
		
		//バリデーションチェック
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
		
		//インサート処理
		User user = userService.insertUser(form) ;
		
		//メールアドレス重複チェック
		if (user == null) {
			map.put("status", "error");
			map.put("message", "既にこのメールアドレスは使用されています");
			return map;
		}else {
			map.put("status", "success");
			map.put("message", "ユーザ登録に成功しました");
			user = userService.findById(user);
			map.put("user", user);
			return map;
		}
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
		
		User user = userService.findByEmail(form);
		
		if (user == null) {
			map.put("status", "error");
			map.put("message", "このメールアドレスのアカウントは存在しません");
			return map;
		}
		
		user = userService.login(user,form);

		if (user == null) {
			map.put("status", "error");
			map.put("message", "パスワードが間違っています");
			return map;
		}

		map.put("status", "success");
		map.put("message", "ログインに成功しました");
		map.put("user", user);
		
		return map;
	}
	
	/**
	 * ユーザ情報を更新します
	 * @param form
	 * @param result
	 * @return
	 */
	@PatchMapping(value = "/user/edit/{id}")
	public Map<String, Object> userEdit(@PathVariable("id") Integer id,@RequestBody @Validated UserEditForm form, BindingResult result) {
		
		Map<String, Object> map = new HashMap<>();
		
		if ((result.hasErrors() && form.getPassword() != null)) {

			map.put("status", "error");
			map.put("Message", result.getFieldError().getDefaultMessage());
			
			return map;
		}
		
		form.setId(id);
	 	User user = userService.updateUser(form);
		user = userService.findById(user);
		
		map.put("status", "success");
		map.put("message", "ユーザ情報の編集に成功しました");
		map.put("user", user);
		
		return map;
	}
	
	@PostMapping(value = "user/mail")
	public Map<String, Object> sendCheckMail(@RequestBody ConfirmMailForm form) {
		Map<String, Object> map = new HashMap<>();
		try {
			userService.accountConfirmMail(form);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
	}
	
}
