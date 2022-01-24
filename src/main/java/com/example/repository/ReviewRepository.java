package com.example.repository;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.example.domain.Review;


/**
 * reviewsテーブルのRepository
 * 
 * @author cyjoh
 *
 */
@Mapper
public interface ReviewRepository {

	//　レビュー全件検索
	List<Review> findAllReview(String userLogicalId);

	// レビュー1件検索
	Review findById(Review review,String userLogicalId);	
	
	// レビューをレストランIDで検索
	List<Review> findByRestaurantId(Review review,String userLogicalId);	
	
	//　レビューを登録
	void insertReview(Review review);

	// レビューの削除
	void deleteReview(Review review);
}