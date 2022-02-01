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
public interface NotificationRepository {

	// 通知表示
	List<Review> findAllReview(Review review);

	//コメントカウントを+-1
	void updateCommentCount(@Param("id") Integer id, @Param("status") Integer status);
}