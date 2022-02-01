package com.example.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.domain.Review;
import com.example.domain.Timeline;

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

	//引数のレビューIDより古く、レストランIDに該当するレビューを新しい順に50件検索
	List<Review> findOlderByRestaurantId(Review review);
	
	// 投稿ユーザーの最新50件のレビューを新しい順に検索	
	List<Review> findByReviewUserId(@Param("requestedUserId") Integer requestedUserId ,@Param("visitingUserId") Integer visitingUserId );
	
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