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

	// レビュー全件検索
	List<Review> findAllReview(Review review);


	// 引数のレビューIDより古いレビューを新しい順に50件検索 
	List<Review> findAllReviewOld(Review review);

	// レビューをレストランIDで検索
	List<Review> findByRestaurantId(Review review);

	// レビュー1件検索
	Review findById(Review review);

	// レビューを登録
	void insertReview(Review review);

	// レビューの削除
	void deleteReview(Review review);
	
	//いいねカウントを+-1
	void updateLikeCount(@Param("id") Integer id, @Param("status") Integer status);
		
	//コメントカウントを+-1
	void updateCommentCount(@Param("id") Integer id, @Param("status") Integer status);
}