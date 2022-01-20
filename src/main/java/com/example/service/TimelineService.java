package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.LikeComment;
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
	
	/**
	 * いいねコメントテーブルに登録
	 * 
	 * @param likeComment
	 * @return
	 */
	public LikeComment insertLikeComment(LikeComment likeComment) {
		likeCommentRepository.insertLikeComment(likeComment);
		return likeComment;
	}
	
	/**
	 * タイムライン接続テーブルに登録
	 * 
	 * @param linkToTimeline
	 */
	public void insertLinkToTimeline(Integer timelineId,Integer likeCountId) {
		linkToTimelineRepository.insertLinksToTimeline(timelineId,likeCountId);
	}
	
	/**
	 * いいねカウント+1
	 * 
	 * @param timeline
	 */
	public void updateLikeCount(Timeline timeline) {
		timelineRepository.updateLikeCount(timeline);
	}
	
	public LikeComment findLikeComment(LikeComment likeComment) {
		return likeCommentRepository.findLikeCommentById(likeComment);
	}
	
	public void updateLike(LikeComment likeComment) {
		likeCommentRepository.updateLike(likeComment);
	}
}
