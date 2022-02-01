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
	
	// 全件検索したものから降順で50件取得
	List<Restaurant> findByDefault();
	
	// 表示されているレストランより古いレストラン情報を取得
	List<Restaurant> findByDefaultMore(Restaurant restaurant);
	
	// レストランテーブルにデータを挿入
	void insertRestaurant(Restaurant restaurant);
	
	// レストランテーブルのstar欄を更新
	void updateStar(Restaurant restaurant);
}

