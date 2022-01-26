package com.example.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.domain.LikeComment;
import com.example.domain.Review;
import com.example.domain.Timeline;
/**
 * いいねコメントテーブルのRepository
 * 
 * @author ootomokenji
 *
 */
@Mapper
public interface LikeCommentRepository {

// 汎用
	// 純粋なロード。
	LikeComment load(LikeComment likeComment);

	//　userId で SELECT
	LikeComment findLikeCommentByUserIdAndTimelineId(LikeComment likeComment);

	//　いいねコメントテーブルにいいねを登録
	void insertLike(LikeComment likeComment);
	
	//　いいねコメントテーブルにコメントを登録
	void insertComment(LikeComment likeComment);
	
	//　いいねを更新します
	void updateLike(LikeComment likeComment);
	
	//コメントを更新します
	void updateComment(LikeComment likeComment);
	
	void updateDelete(LikeComment likeComment);

// タイムライン周り
	//　タイムラインに紐づくコメントリストを検索
	List<LikeComment> findCommentListByTimelineId(Timeline timeline);
	
// レビュー周り	
	//　タイムラインに紐づくコメントリストを検索
	List<LikeComment> findCommentListByReviewId(Review review);
	
// コメントへのいいね周り	
	//　コメントに対するいいねを検索
	LikeComment findLikeCommentByUserIdAndParentCommentId(LikeComment likeComment);
		
	// コメントのいいねカウントを更新する
	void updateLikeCount(@Param("id") Integer id, @Param("status") Integer status);
	
}