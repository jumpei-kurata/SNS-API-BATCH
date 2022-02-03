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

import com.example.domain.LikeComment;
import com.example.domain.Mail;
import com.example.domain.Review;
import com.example.domain.Timeline;
import com.example.domain.User;
import com.example.form.ConfirmMailForm;
import com.example.form.CreateUserForm;
import com.example.form.LoginForm;
import com.example.form.UserEditForm;
import com.example.form.changePasswordAfterLoginForm;
import com.example.form.changePasswordForm;
import com.example.form.changePasswordMailForm;
import com.example.service.ErrorService;
import com.example.service.LikeCommentService;
import com.example.service.ReviewService;
import com.example.service.TimelineService;
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

	@Autowired
	private TimelineService timelineService;
	
	@Autowired
	private ReviewService reviewService;

	@Autowired
	private LikeCommentService likeCommentService;
		
	
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
	@GetMapping(value = "/user/{id}/{userLogicalId}")
	public Map<String, Object> findById(@PathVariable Integer id,@PathVariable String userLogicalId) {
		Map<String, Object> map = new HashMap<>();
		
		// 表示されようとしているUserの情報をロード
		User requestedUser = new User();
		requestedUser.setId(id);
		requestedUser = userService.findById(requestedUser);

		if (requestedUser == null) {
			map.put("status", "error");
			map.put("message", "このIDのアカウントは存在しません");
			return map;
		}

		// 表示しようとしているUserの情報をロード
		User visitingUser = new User();
		visitingUser.setLogicalId(userLogicalId);
		visitingUser = userService.findUserByLogicalId(visitingUser);
		
		if (visitingUser == null) {
			map.put("status", "error");
			map.put("message", "不正なユーザーによるリクエストです");
			return map;
		}
		
		List<Timeline> postedTimelineList = timelineService.showListByPostUserId(requestedUser,visitingUser);
		List<Review> postedReviewList = reviewService.showListByPostUserId(requestedUser,visitingUser);
		List<Timeline> likedTimelineList = timelineService.showListByLikeUserId(requestedUser, visitingUser);
		List<Review> likedReviewList = reviewService.showListByLikeUserId(requestedUser, visitingUser);
		List<LikeComment> likedCommentList = likeCommentService.showListByLikeUserId(requestedUser, visitingUser);
		
		map.put("status", "success");
		map.put("message", "ロードに成功しました");
		map.put("user", requestedUser);
		map.put("postedTimelineList", postedTimelineList);
		map.put("postedReviewList", postedReviewList);
		map.put("likedTimelineList", likedTimelineList);
		map.put("likedReviewList", likedReviewList);
		map.put("likedCommentList", likedCommentList);
		
		return map;
	}

	/**
	 * 論理IDで検索するメソッド
	 * 
	 * @param userLogicalId
	 * @return
	 */
	@GetMapping(value = "/user/{userLogicalId}")
	public Map<String, Object> findByuserLogicalId(@PathVariable String userLogicalId) {
		Map<String, Object> map = new HashMap<>();
		
		User user = new User();
		user.setLogicalId(userLogicalId);
		
		user = userService.findUserByLogicalId(user);
		
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
	@PatchMapping(value = "/user/edit/{userLogicalId}")
	public Map<String, Object> userEdit(@PathVariable String userLogicalId, @RequestBody @Validated UserEditForm form,BindingResult result) {

		Map<String, Object> map = new HashMap<>();

		
		User user = new User();
		user.setLogicalId(userLogicalId);
		user = userService.findUserByLogicalId(user);
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
		user = userService.changePassword(user);
		
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

	
	/**
	 * ログイン後のパスワード変更のためのAPI
	 * 
	 * @param form
	 * @param result
	 * @return
	 */
	@PatchMapping(value = "/password")
	public Map<String, Object> changePasswordAfterLogin(@RequestBody @Validated changePasswordAfterLoginForm form, BindingResult result) {
		Map<String, Object> map = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errorMessageList = errorService.errorMessage(result);

			map.put("status", "error");
			map.put("message", errorMessageList);
			return map;
		}

		// userを論理IDで照合
		User user = new User();
		user.setLogicalId(form.getUserLogicalId());
		user = userService.findUserByLogicalId(user);

		// userLogicalIdが不正だった場合は、ここで弾かれる
		if (user == null) {
			map.put("status", "error");
			map.put("message", "ユーザーが存在しません。");
			return map;
		}

		// userServiceにて、入力されたパスワードがあっているかの判断
		// 間違っていればnullをreturn、あっていればupdateしてuserをreternしてくれる
		
		user = userService.changePasswordAfterLogin(user,form.getBeforePassword(),form.getAfterPassword());
	
		// 結果に合わせて対応
		if (user == null) {
			map.put("status", "error");
			map.put("message", "入力されたパスワードが違います。");
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
