package com.example.repository;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.LinkToTimeline;
/**
 * タイムライン接続用テーブルのRepository
 * 
 * @author ootomokenji
 *
 */
@Mapper
public interface LinkToTimelineRepository {

	//タイムライン接続テーブルに登録
	void insertLinksToTimeline(LinkToTimeline linkToTimeline);
	//タイムラインIDで検索
	List<LinkToTimeline> findLinkToTimelineByTimelineId(LinkToTimeline linkToTimeline);
}