package com.example.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.User;
import com.example.form.ConfirmMailForm;
import com.example.form.CreateUserForm;
import com.example.form.LoginForm;
import com.example.form.UserEditForm;
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
	@Autowired
	private MailSender sender;
	
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
	 * @param form EmailとpasswordがセットされたLoginForm
	 * @return 情報が格納されたUserドメイン
	 */
	public User findByEmail(LoginForm form) {
		
		User user = new User();
		user.setEmail(form.getEmail());

		List<User> users = userRepository.findByEmail(user);
		if (users.size() == 0) {
			return null;
		}else {
			user = users.get(0);
			return user;
		}
	}
	
	/**
	 * ログイン処理します
	 * @param user
	 * @param form
	 * @return
	 */
	public User login(User user,LoginForm form) {
		
		if( user.getPassword() .equals ( form.getPassword() ) ) {
			
			userRepository.loginedUpdate(user);
			user = userRepository.findById(user);
			return user;
		}else {
			return null;
		}
	}
	
	/**
	 * ユーザー情報を登録します
	 * @param user 入力されたユーザー情報
	 * @return ユーザー情報
	 */
	public User insertUser(CreateUserForm form) {
		//情報移し替える
		User user = new User();
		BeanUtils.copyProperties(form, user);
		
		//メールアドレス重複チェック
		if (userRepository.findByEmail(user).size() != 0) {
			return null;
		}else {
			userRepository.insertUser(user);
			return user;		
		}
	}

	/**
	 * ユーザー情報を更新します
	 * @param user 入力されたユーザー情報
	 * @return ユーザー情報
	 */
	public User updateUser(UserEditForm form) {
		
		User user = new User();
		user.setId(form.getId());
		user = userRepository.findById(user);
		
		if (form.getName() != null) {
			user.setName(form.getName());
		}
		
		if (form.getAccountName() != null) {
			user.setAccountName(form.getAccountName());
		}
		
		if (form.getPassword() != null) {
			user.setPassword(form.getPassword());
		}
		
		if (form.getHireDate() != null) {
			user.setHireDate(form.getHireDate());
		}
		
		if (form.getServiceFk() != null) {
			user.setServiceFk(form.getServiceFk());
		}
		
		if (form.getBirthDay() != null) {
			user.setBirthDay(form.getBirthDay());
		}
		
		if (form.getIntroduction() != null) {
			user.setIntroduction(form.getIntroduction());
		}
		
		userRepository.updateUser(user);
		return user;
	}
	
	public void accountConfirmMail(ConfirmMailForm form) {
		
		SimpleMailMessage msg = new SimpleMailMessage();
		try {
			msg.setFrom("kenji.otomo@rakus-partners.co.jp");
			msg.setTo(form.getEmail());
			msg.setSubject("メールアドレス認証のお願い");
			msg.setText(form.getName()+" 様\nURLです\n\n"+"http://localhost:8080/");
			sender.send(msg);
		} catch (Exception e) {
				e.printStackTrace();
		}
		
	}
}
