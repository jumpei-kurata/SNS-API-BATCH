package com.example.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * レビュー接続用テーブルのRepository
 * 
 * @author cyjohe
 *
 */
@Mapper
public interface LinkToReviewRepository {

	//タイムライン接続テーブルに登録
	void insertLinksToReview(@Param("reviewId") Integer reviewId,
			@Param("likeCommentId") Integer likeCommentId);
	
}