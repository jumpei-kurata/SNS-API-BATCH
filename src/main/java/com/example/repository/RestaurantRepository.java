package com.example.repository;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.example.domain.Restaurant;
/**
 * restaurantsテーブルのRepository<br>
 * 
 * @author cyjoh
 *
 */
@Mapper
public interface RestaurantRepository {
	// 1件検索
	Restaurant findById(Restaurant restaurant);

	// ホットペッパーIDで検索
	Restaurant findByHotpepperId(Restaurant restaurant);
	
	// 名前あいまい検索
	List<Restaurant> findByName(Restaurant restaurant);
	
	// 全件検索
	List<Restaurant> findAll();
	
	// ユーザーテーブルにデータを挿入
	void insertRestaurant(Restaurant restaurant);
	
}

