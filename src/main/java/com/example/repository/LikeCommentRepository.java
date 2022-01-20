package com.example.repository;

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
	LikeComment findLikeCommentById(LikeComment likeComment);
	//いいねコメントテーブルに登録
	void insertLikeComment(LikeComment likeComment);
	//いいねを更新します
	void updateLike(LikeComment likeComment);
}