package com.example.repository;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.example.domain.Restaurant;
@Mapper
public interface RestaurantRepository {
	// 1件検索
	Restaurant findById(Restaurant restaurant);
	// メールアドレスで検索
	List<Restaurant> findByEmail(Restaurant restaurant);
	
	// 全件検索
	List<Restaurant> findAll();
	// ユーザーテーブルにデータを挿入
	void insertRestaurant(Restaurant restaurant);
	
	// ユーザーテーブルを更新
	int updateRestaurant(Restaurant restaurant);
	
	//ログイン日時のみを更新
	void loginedUpdate(Restaurant restaurant);
}

