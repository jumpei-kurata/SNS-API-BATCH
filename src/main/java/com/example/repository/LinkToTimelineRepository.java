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
public interface LinkToTimelineRepository {

	//タイムライン接続テーブルに登録
	void insertLinksToTimeline(@Param("timelineId") Integer timelineId,
			@Param("likeCommentId") Integer likeCommentId);
	
}