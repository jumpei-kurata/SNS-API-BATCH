package com.example.repository;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

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
	//タイムラインを登録
	void insertTimeline(Timeline timeline);
	//いいねカウントを+1
	void updateLikeCount(Timeline timeline);
	
	Timeline findTimelineById (Timeline timeline);
}