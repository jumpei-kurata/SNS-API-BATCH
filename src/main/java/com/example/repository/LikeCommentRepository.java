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

	
	//いいねコメントテーブルに登録
	void insertLikeComment(LikeComment likeComment);
	
}