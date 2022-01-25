package com.example.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.domain.LikeComment;
import com.example.domain.Timeline;
/**
 * いいねコメントテーブルのRepository
 * 
 * @author ootomokenji
 *
 */
@Mapper
public interface LikeCommentRepository {
	//userId で SELECT
	LikeComment findLikeCommentByUserIdAndTimelineId(LikeComment likeComment);
//	コメントに対するいいねを検索
	LikeComment findCommentByUserIdAndCommentId(LikeComment likeComment);

	//コメントリストを検索
	List<LikeComment> findCommentListByTimelineId(Timeline timeline);
	//いいねコメントテーブルに登録
	void insertLikeComment(LikeComment likeComment);
	//いいねを更新します
	void updateLike(LikeComment likeComment);
	//コメントを更新します
	void updateComment(LikeComment likeComment);
	
	void updateDelete(LikeComment likeComment);
	
	void updateLikeCount(@Param("id") Integer id, @Param("status") Integer status);
	
	LikeComment findLikeCommentByCommentId(LikeComment likeComment);
}