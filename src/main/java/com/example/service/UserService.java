package com.example.service;

import java.security.SecureRandom;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Mail;
import com.example.domain.User;
import com.example.form.LoginForm;
import com.example.repository.MailRepository;
import com.example.repository.UserRepository;

/**
 * ユーザーのためのserviceです
 * 
 * @author ootomokenji
 *
 */
@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MailRepository mailRepository;
	@Autowired
	private MailSender sender;

	// このアドレスからメールを送ります
	private final String FROMEMAIL = "lunchkus@gmail.com";

	/**
	 * IDからユーザー情報を取得します
	 * 
	 * @param user IDだけがセットされたUserドメイン
	 * @return 情報が格納されたUserドメイン
	 */
	public User findById(User user) {
		return userRepository.findById(user);
	}

	/**
	 * Emailからユーザー情報を取得します
	 * 
	 * @param form EmailとpasswordがセットされたLoginForm
	 * @return 情報が格納されたUserドメイン
	 */
	public User findByEmail(User user) {

		List<User> users = userRepository.findByEmail(user);
		if (users.size() == 0) {
			return null;
		} else {
			user = users.get(0);
			return user;
		}
	}

	/**
	 * ログイン処理します
	 * 
	 * @param user
	 * @param form
	 * @return
	 */
	public User login(User user, LoginForm form) {

		if (user.getPassword().equals(form.getPassword())) {

			userRepository.loginedUpdate(user);
			user = userRepository.findById(user);
			return user;
		} else {
			return null;
		}
	}

	/**
	 * ユーザー情報を登録します
	 * 
	 * @param user 入力されたユーザー情報
	 * @return ユーザー情報
	 */
	public User insertUser(User user) {

		// メールアドレス重複チェック
		List<User> list = userRepository.findByEmail(user);

		if (list.size() != 0) {
			return null;
		} else {
			String userPhotoPath = createUserPhotoPath();
			user.setUserPhotoPath(userPhotoPath);

			userRepository.insertUser(user);
			return user;
		}
	}

	/**
	 * ランダムにユーザーの写真パスを生成します
	 * 
	 * @return
	 */
	public String createUserPhotoPath() {

		// 写真の枚数
		Integer photoNumber = 6;

		int randomPathNumber = (int) (Math.random() * photoNumber) + 1;
		String userPhotoPath = "user" + randomPathNumber + ".jpeg";

		return userPhotoPath;
	}

	/**
	 * ユーザー情報を更新します
	 * 
	 * @param user 入力されたユーザー情報
	 * @return ユーザー情報
	 */
	public User updateUser(User user) {
		userRepository.updateUser(user);
		user = userRepository.findById(user);
		return user;
	}

	/**
	 * パスワードを変更します
	 * (パスワードを忘れた際の変更用メソッド)
	 * 
	 * 
	 * @param user
	 * @return
	 */
	public User changePassword(User user) {
		
		List<User>userList = userRepository.findByEmail(user);
		
		if(userList.size() != 1) {
			return null;
		}

		String password = user.getPassword();
		user = userList.get(0);
		user.setPassword(password);

		userRepository.changePassword(user);
		user = userRepository.findById(user);

		return user;
	}
	
	/**
	 * パスワードを変更します
	 * (ログイン後にパスワードを変更したい際の変更用メソッド)
	 * 
	 * 
	 * @param user
	 * @return
	 */
	public User changePasswordAfterLogin(User user,String beforePassword,String afterPassword) {
		
		// 上記でDBから持ってきたUser情報と、入力されたパスワードが一致しているかを確認
		// 一致していなければ、return
		if (!( user.getPassword().equals(beforePassword) && beforePassword.equals(user.getPassword()) )) {
			return null ;
		}
		
		// 一致していれば、パスワードの経脳を実行
		user.setPassword(afterPassword);
		userRepository.changePassword(user);
		
		// 最後に最新情報をロードして、コントローラーに返す。
		user = userRepository.findByLogicalId(user);
		
		return user;
	}

	/**
	 * ユーザー仮登録時にメールアドレス認証のメールを送ります
	 * 
	 * @param mail
	 */
	public void accountConfirmMail(Mail mail) {

		List<Mail> list = mailRepository.findMailByEmail(mail);
		String token = createToken();

		mail.setToken(token);
		mail.setStatus(0);

		if (list.size() == 0) {
			mailRepository.insertMail(mail);
		} else {
			mailRepository.changeTokenMail(mail);
		}

		SimpleMailMessage msg = new SimpleMailMessage();
		try {
			msg.setFrom(FROMEMAIL);
			msg.setTo(mail.getEmail());
			msg.setSubject("メールアドレス認証のお願い");
			msg.setText(mail.getName() + " 様\nURLです\n\n" + "http://localhost:3000/auth/signup/" + token);
			sender.send(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mail.setStatus(1);
		mailRepository.changeStatusMail(mail);
	}

	/**
	 * パスワード変更時にメールを送信します
	 * 
	 * @param user
	 */
	public void changePasswordMail(User user) {

		Mail mail = new Mail();
		String token = createToken();

		mail.setEmail(user.getEmail());
		mail.setToken(token);

		mailRepository.changeTokenMail(mail);

		SimpleMailMessage msg = new SimpleMailMessage();
		try {
			msg.setFrom(FROMEMAIL);
			msg.setTo(user.getEmail());
			msg.setSubject("パスワードの再設定について");
			msg.setText(user.getName() + "様\n\n" + "パスワードの再設定がリクエストされました\n" + "以下のリンクから再設定が可能です。\n\n"
					+ "http://localhost:3000/auth/updatepass/" + token + "\n\n" + "このメールに心当たりが無い場合は無視してください。\n"
					+ "上記URLを通して再設定しない限り、パスワードは変更されません。\n");
			sender.send(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 32桁の認証用トークンを発行します
	 * 
	 * @return
	 */
	public String createToken() {

		byte tokenSize[] = new byte[16];
		StringBuffer token = new StringBuffer();

		try {
			SecureRandom random = new SecureRandom();
			random.nextBytes(tokenSize);
			for (int i = 0; i < tokenSize.length; i++) {
				token.append(String.format("%02x", tokenSize[i]));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

		return token.toString();
	}
	
	/**
	 * 16桁の認証用トークンを発行します
	 * 
	 * @return
	 */
	public String createHalfToken() {
		
		byte tokenSize[] = new byte[8];
		StringBuffer token = new StringBuffer();
		
		try {
			SecureRandom random = new SecureRandom();
			random.nextBytes(tokenSize);
			for (int i = 0; i < tokenSize.length; i++) {
				token.append(String.format("%02x", tokenSize[i]));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		
		return token.toString();
	}

	/**
	 * トークンによってメールを検索
	 * 
	 * @param mail
	 * @return
	 */
	public List<Mail> findMailByToken(Mail mail) {
		List<Mail> list = mailRepository.findMailByToken(mail);
		return list;
	}

	/**
	 * メールによってメールを検索
	 * 
	 * @param mail
	 * @return
	 */
	public List<Mail> findMailByEmail(Mail mail) {
		List<Mail> list = mailRepository.findMailByEmail(mail);
		return list;
	}

	/**
	 * メールテーブルのステータスを変更します
	 * 
	 * @param mail
	 * @return
	 */
	public Mail changeStatusMail(Mail mail) {
		mailRepository.changeStatusMail(mail);
		return mail;
	}

	/**
	 * メールテーブルのトークンを更新します
	 * 
	 * @param mail
	 * @return
	 */
	public Mail changeTokenMail(Mail mail) {

		String newAccountToken = createToken();
		mail.setToken(newAccountToken);
		mailRepository.changeTokenMail(mail);

		return mail;
	}

	/**
	 * メールテーブルにinsertします
	 * 
	 * @param mail
	 * @return
	 */
	public Mail insertMail(Mail mail) {
		mailRepository.insertMail(mail);
		return mail;
	}

	
	/**
	 * DBにかぶりがないlogicalIdをuserに付与します
	 * 
	 * @param user
	 * @return logicalIdが付与されたuser
	 */
	public User generateLogicalId(User user) {
		boolean logicalIdIsUnique = false; 
		
		// DBに同じ論理IDがいなくなるまで、トークン生成を行う
		while(!logicalIdIsUnique) {
			user.setLogicalId(createHalfToken());
		
			// 生成した論理IDでDBを検索し、該当するものがなければ、isUniqueをTRUEに
			if(userRepository.findByLogicalId(user) == null) {
				logicalIdIsUnique = true ;
			}
		}
		
		return user;
	}
	
	public User findUserByLogicalId(User user) {
		return user = userRepository.findByLogicalId(user);
	}
	
}
