package com.example.repository;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.example.domain.Mail;
import com.example.domain.User;
@Mapper
public interface UserRepository {
	// 1件検索
	User findById(User user);
	// メールアドレスで検索
	List<User> findByEmail(User user);
	
	// 全件検索
	List<User> findAll();
	// ユーザーテーブルにデータを挿入
	void insertUser(User user);
	
	// ユーザーテーブルを更新
	int updateUser(User user);
	
	//ログイン日時のみを更新
	void loginedUpdate(User user);
	
	//トークンでメールテーブルを検索
	List<Mail> findMailByToken(Mail mail);

	//メールアドレスでメールテーブルを検索
	List<Mail> findMailByEmail(Mail mail);
	
	//メールテーブルに挿入
	void insertMail (Mail mail);
	
	//メールテーブルのステータスを更新
	void changeStatusMail(Mail mail);

	//メールテーブルのトークンを更新
	void changeTokenMail(Mail mail);
	
}