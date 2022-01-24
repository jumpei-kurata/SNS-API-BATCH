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

@Service
@Transactional
public class LikeCommentService {

	@Autowired
	private TimelineRepository timelineRepository;
	@Autowired
	private LikeCommentRepository likeCommentRepository;
	@Autowired
	private LinkToTimelineRepository linkToTimelineRepository;
	
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
	public void insertLinkToTimeline(Integer timelineId,Integer likeCommentId) {
		linkToTimelineRepository.insertLinksToTimeline(timelineId,likeCommentId);
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
		return likeCommentRepository.findLikeCommentByUserIdAndTimelineId(likeComment);
	}
	
	public void updateLike(LikeComment likeComment) {
		likeCommentRepository.updateLike(likeComment);
	}
	
	public List<LikeComment> findCommentList(Integer timelineId) {
		return likeCommentRepository.findCommentListByTimelineId(timelineId);
	}
}
