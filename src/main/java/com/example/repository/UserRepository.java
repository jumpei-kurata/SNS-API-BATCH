package com.example.repository;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.example.domain.User;
@Mapper
public interface UserRepository {
	// 1件検索
	User findById(User user);
	
	// ログイン用1件検索(論理IDも渡す)
	User findByIdForLogin(User user);
	
	// メールアドレスで検索
	List<User> findByEmail(User user);
	
	// 論理IDで検索
	User findByLogicalId(User user);
	
	// 全件検索
	List<User> findAll();
	
	// ユーザーテーブルにデータを挿入
	void insertUser(User user);
	
	// ユーザーテーブルを更新
	int updateUser(User user);
	
	//パスワードを変更
	void changePassword(User user);
	
	//ログイン日時のみを更新
	void loginedUpdate(User user);
}