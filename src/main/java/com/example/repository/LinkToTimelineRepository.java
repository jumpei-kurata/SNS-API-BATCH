package com.example.repository;

import org.apache.ibatis.annotations.Mapper;

/**
 * タイムライン接続用テーブルのRepository
 * 
 * @author ootomokenji
 *
 */
@Mapper
public interface LinkToTimelineRepository {

	//タイムライン接続テーブルに登録
	void insertLinksToTimeline(Integer timelineId,Integer likeCountId);
	
}