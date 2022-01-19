package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.LikeComment;
import com.example.domain.LinkToTimeline;
import com.example.domain.Timeline;
import com.example.repository.LikeCommentRepository;
import com.example.repository.LinkToTimelineRepository;
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
	@Autowired
	private LikeCommentRepository likeCommentRepository;
	@Autowired
	private LinkToTimelineRepository linkToTimelineRepository;
	
	public List<Timeline> findAll() {
		List<Timeline>list = timelineRepository.findAllTimeline();
		
		for (Timeline timeline : list) {
			if (timeline.getLikeCount() == null) {
				timeline.setLikeCount(0);
			}
		}
		
		return list;
	}
	
	public Timeline insertTimeline(Timeline timeline) {
		
		timelineRepository.insertTimeline(timeline);
		return timeline;
	}
	
	public LikeComment insertLikeComment(LikeComment likeComment) {
		likeCommentRepository.insertLikeComment(likeComment);
		return likeComment;
	}
	
	public void insertLinkToTimeline(LinkToTimeline linkToTimeline) {
		linkToTimelineRepository.insertLinksToTimeline(linkToTimeline);
	}
}
