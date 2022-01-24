package com.example.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.LikeComment;
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
	//コメントリストを検索
	List<LikeComment> findCommentListByTimelineId(Integer timelineId);
	//いいねコメントテーブルに登録
	void insertLikeComment(LikeComment likeComment);
	//いいねを更新します
	void updateLike(LikeComment likeComment);
}