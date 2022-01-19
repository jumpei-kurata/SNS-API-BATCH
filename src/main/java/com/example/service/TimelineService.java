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
	
	public List<Timeline> findAll() {
		List<Timeline>list = timelineRepository.findAllTimeline();
		return list;
	}
	
	public Timeline insertTimeline(Timeline timeline) {
		
		timelineRepository.insertTimeline(timeline);
		return timeline;
	}
}
