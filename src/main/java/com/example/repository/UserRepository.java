package com.example.repository;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
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
	
	void loginedUpdate(User user);
}