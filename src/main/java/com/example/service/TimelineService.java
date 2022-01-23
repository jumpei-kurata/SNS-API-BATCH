package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Timeline;
import com.example.repository.TimelineRepository;

/**
 * タイムラインのサービスです
 * @author ootomokenji
 *
 */
@Service
@Transactional
public class TimelineService {

	@Autowired
	private TimelineRepository timelineRepository;
	
	/**
	 * タイムライン全件検索
	 * 
	 * @return
	 */
	public List<Timeline> findAll(Timeline timeline) {
		List<Timeline>list = timelineRepository.findAllTimeline(timeline);
		return list;
	}
	
	/**
	 * タイムライン投稿
	 * 
	 * @param timeline
	 * @return
	 */
	public Timeline insertTimeline(Timeline timeline) {
		timelineRepository.insertTimeline(timeline);
		return timeline;
	}
	
	public Timeline findTimelineById(Timeline timeline) {
		return timelineRepository.findTimelineById(timeline);
	}
}
