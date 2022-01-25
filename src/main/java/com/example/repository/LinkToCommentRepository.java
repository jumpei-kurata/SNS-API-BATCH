package com.example.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * タイムライン接続用テーブルのRepository
 * 
 * @author ootomokenji
 *
 */
@Mapper
public interface LinkToCommentRepository {

	//コメント接続テーブルに登録
	void insertLinksToComment(@Param("commentId") Integer commentId,
			@Param("likeCommentId") Integer likeCommentId);
	
}