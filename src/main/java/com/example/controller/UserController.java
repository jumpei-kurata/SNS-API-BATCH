package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Mail;
import com.example.domain.User;
import com.example.form.ConfirmMailForm;
import com.example.form.CreateUserForm;
import com.example.form.LoginForm;
import com.example.form.UserEditForm;
import com.example.form.changePasswordForm;
import com.example.form.changePasswordMailForm;
import com.example.service.ErrorService;
import com.example.service.UserService;

/**
 * ユーザーのためのRestControllerです
 * 
 * @author ootomokenji
 *
 */
@RestController
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private ErrorService errorService;

	/**
	 * ユーザーを登録します
	 * 
	 * @param form   入力されたユーザー情報
	 * @param result
	 * @return メッセージ（成功/失敗）ステータス（OK/error） ユーザー情報
	 */
	@PostMapping(value = "/signup")
	public Map<String, Object> insertUser(@RequestBody @Validated CreateUserForm form, BindingResult result) {

		Map<String, Object> map = new HashMap<>();

		// バリデーションチェック
		if (result.hasErrors()) {
			List<String> errorMessageList = errorService.errorMessage(result);

			map.put("status", "error");
			map.put("message", errorMessageList);
			return map;
		}

		User user = new User();
		BeanUtils.copyProperties(form, user);

		// 論理ID発行
		user = userService.generateLogicalId(user);

		System.out.println(user);
		
		// インサート処理
		user = userService.insertUser(user);
		
		
		// メールアドレス重複チェック
		if (user == null) {
			map.put("status", "error");
			map.put("message", "既にこのメールアドレスは使用されています");
			return map;
		} else {
			map.put("status", "success");
			map.put("message", "ユーザ登録に成功しました");
			user = userService.findById(user);
			map.put("user", user);
			return map;
		}
	}

	/**
	 * ユーザーをロードします
	 * 
	 * @param id 受け取ったユーザーID
	 * @return IDから取得したユーザー情報
	 */
	@GetMapping(value = "/user/{id}")
	public Map<String, Object> findById(@PathVariable Integer id) {
		Map<String, Object> map = new HashMap<>();

		User user = new User();
		user.setId(id);

		user = userService.findById(user);

		if (user == null) {
			map.put("status", "error");
			map.put("message", "このIDのアカウントは存在しません");
			return map;
		}

		map.put("status", "success");
		map.put("message", "ロードに成功しました");
		map.put("user", user);
		return map;
	}

	/**
	 * ログインします
	 * 
	 * @param form
	 * @param result
	 * @return メッセージ（成功/失敗）ステータス（OK/error） ユーザー情報
	 */
	@PostMapping(value = "/login")
	public Map<String, Object> login(@RequestBody @Validated LoginForm form, BindingResult result) {

		Map<String, Object> map = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errorMessageList = errorService.errorMessage(result);

			map.put("status", "error");
			map.put("message", errorMessageList);
			return map;
		}

		User user = new User();
		BeanUtils.copyProperties(form, user);

		user = userService.findByEmail(user);

		if (user == null) {
			map.put("status", "error");
			map.put("message", "このメールアドレスのアカウントは存在しません");
			return map;
		}

		user = userService.login(user, form);

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
	 * 
	 * @param form
	 * @param result
	 * @return
	 */
	@PatchMapping(value = "/user/edit/{id}")
	public Map<String, Object> userEdit(@PathVariable("id") Integer id, @RequestBody @Validated UserEditForm form,
			BindingResult result) {

		Map<String, Object> map = new HashMap<>();

		form.setId(id);
		User user = new User();
		BeanUtils.copyProperties(form, user);
		user = userService.updateUser(user);

		map.put("status", "success");
		map.put("message", "ユーザ情報の編集に成功しました");
		map.put("user", user);

		return map;
	}

	/**
	 * ユーザー仮登録のメール送信API
	 * 
	 * @param form
	 * @return
	 */
	@PostMapping(value = "/presignup")
	public Map<String, Object> sendCheckMail(@RequestBody ConfirmMailForm form) {
		Map<String, Object> map = new HashMap<>();

		Mail mail = new Mail();
		mail.setEmail(form.getEmail());
		List<Mail> list = userService.findMailByEmail(mail);

		if (list.size() == 0) {
			mail = new Mail();
			mail.setName(form.getName());
			mail.setEmail(form.getEmail());
		} else {
			mail = list.get(0);
		}

		try {
			userService.accountConfirmMail(mail);
		} catch (Exception e) {
			e.printStackTrace();
		}

		map.put("status", "success");
		map.put("message", "ご登録いただいたメールアドレス宛にメールを送信しました");
		return map;
	}

	/**
	 * パスワード変更メールAPI
	 * 
	 * @param form
	 * @return
	 */
	@PostMapping(value = "password/sendMail")
	public Map<String, Object> changePassword(@RequestBody changePasswordMailForm form) {
		Map<String, Object> map = new HashMap<>();

		User user = new User();
		user.setEmail(form.getEmail());
		user = userService.findByEmail(user);

		if (user == null) {
			map.put("status", "error");
			map.put("message", "このメールアドレスは使用されていません");
			return map;
		} else {
			userService.changePasswordMail(user);
			map.put("status", "success");
			map.put("message", "ご入力いただいたメールアドレス宛にメールを送信しました");
			return map;
		}
	}

	/**
	 * アカウント仮登録時に使います トークンからメールテーブルを検索
	 * 
	 * @param token
	 * @return
	 */
	@GetMapping(value = "/mail/{token}")
	public Map<String, Object> findMailByToken(@PathVariable String token) {
		Map<String, Object> map = new HashMap<>();
		Mail mail = new Mail();
		mail.setToken(token);
		List<Mail> list = userService.findMailByToken(mail);

		if (list.size() != 1) {
			map.put("status", "error");
			map.put("message", "トークンが有効ではありません");
			return map;
		}

		map.put("status", "success");
		map.put("message", "ユーザ情報の検索に成功しました");
		map.put("mail", list.get(0));
		return map;
	}

	/**
	 * パスワード変更のためのAPI
	 * 
	 * @param token
	 * @param form
	 * @return
	 */
	@PatchMapping(value = "/password/{token}")
	public Map<String, Object> changePassword(@PathVariable String token,
			@RequestBody @Validated changePasswordForm form, BindingResult result) {
		Map<String, Object> map = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errorMessageList = errorService.errorMessage(result);

			map.put("status", "error");
			map.put("message", errorMessageList);
			return map;
		}

		Mail mail = new Mail();
		mail.setToken(token);
		List<Mail> list = userService.findMailByToken(mail);

		if (list.size() != 1) {
			map.put("status", "error");
			map.put("message", "トークンが有効ではありません");
			return map;
		} else if (!(list.get(0).getEmail().equals(form.getEmail()))) {
			map.put("status", "error");
			map.put("message", "メールアドレスが正しくありません");
			return map;
		}

		User user = new User();
		BeanUtils.copyProperties(form, user);
		user = userService.updatePassword(user);

		if (user == null) {
			map.put("status", "error");
			map.put("message", "このメールアドレスは登録されていません");
			return map;
		}

		map.put("status", "success");
		map.put("message", "パスワードの変更が完了しました");
		map.put("user", user);
		return map;
	}

	@GetMapping(value = "test/generateLogical")
	public String generateLogicalId() {
		return userService.createHalfToken();
	}

}
