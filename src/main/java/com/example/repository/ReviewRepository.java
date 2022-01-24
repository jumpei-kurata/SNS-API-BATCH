package com.example.repository;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
	List<Review> findAllReview(String userlogicalId);

	// レビュー1件検索
	Review findById(@Param("review") Review review,@Param("userlogicalId")String userlogicalId);	
	
	// レビューをレストランIDで検索
	List<Review> findByRestaurantId(@Param("review") Review review,@Param("userlogicalId")String userlogicalId);	
	
	//　レビューを登録
	void insertReview(Review review);

	// レビューの削除
	void deleteReview(Review review);
}