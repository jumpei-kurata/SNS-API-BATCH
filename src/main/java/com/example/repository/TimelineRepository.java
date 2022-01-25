package com.example.repository;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.domain.Timeline;
/**
 * timelinesテーブルのRepository
 * 
 * @author ootomokenji
 *
 */
@Mapper
public interface TimelineRepository {

	//タイムライン全件検索
	List<Timeline> findAllTimeline(Timeline timeline);

	List<Timeline> findAllTimelineOld(Timeline timeline);
	//タイムラインを登録
	void insertTimeline(Timeline timeline);
	//いいねカウントを+-1
	void updateLikeCount(@Param("id") Integer id, @Param("status") Integer status);
	
	//コメントカウントを+-1
	void updateCommentCount(@Param("id") Integer id, @Param("status") Integer status);
	
	Timeline findTimelineById (Timeline timeline);
	
	void updateDelete(Timeline timeline);
}