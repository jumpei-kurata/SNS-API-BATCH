package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.LikeComment;
import com.example.domain.Timeline;
import com.example.repository.LikeCommentRepository;
import com.example.repository.LinkToCommentRepository;
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
	@Autowired
	private LinkToCommentRepository linkToCommentRepository;
	
	/**
	 * いいねコメントテーブルに登録
	 * 
	 * @param likeComment
	 * @return
	 */
	public LikeComment insertLikeCommentToTimeline(LikeComment likeComment) {
		
		if (likeComment.getComment() == null) {
			likeComment.setLike(true);
		}
		likeCommentRepository.insertLikeComment(likeComment);
		
		linkToTimelineRepository.insertLinksToTimeline(likeComment.getTimelineId(),likeComment.getId());
		
		timelineRepository.updateLikeCount(likeComment.getTimelineId(),0);
		
		return likeComment;
	}
	
	public LikeComment insertLikeCommentToLikeComment(LikeComment likeComment) {
		
		if (likeComment.getComment() == null) {
			likeComment.setLike(true);
		}
		likeCommentRepository.insertLikeComment(likeComment);
		
		linkToCommentRepository.insertLinksToComment(likeComment.getParentCommentId(), likeComment.getId());
		
		timelineRepository.updateLikeCount(likeComment.getTimelineId(),0);
		
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
	 * いいねカウント+-1
	 * 
	 * @param timeline
	 */
	public void updateLikeCountToTimeline(Integer timelineId,Integer status) {
		timelineRepository.updateLikeCount(timelineId,status);
	}
	public void updateLikeCountToComment(Integer commentId,Integer status) {
		likeCommentRepository.updateLikeCount(commentId,status);
	}
	/**
	 * コメントカウント+-1
	 * 
	 * @param timeline
	 */
	public void updateCommentCountTimeline(Integer timelineId,Integer status) {
		timelineRepository.updateCommentCount(timelineId,status);
	}
	
	public LikeComment findLikeComment(Integer userId,Integer timelineId) {
		LikeComment likeComment = new LikeComment();
		
		likeComment.setUserId(userId);
		likeComment.setTimelineId(timelineId);
		return likeCommentRepository.findLikeCommentByUserIdAndTimelineId(likeComment);
	}
	
	public void updateLike(LikeComment likeComment) {
		likeCommentRepository.updateLike(likeComment);
	}
	
	public void updateComment(LikeComment likeComment) {
		likeCommentRepository.updateComment(likeComment);
	}
	
	public List<LikeComment> findCommentListToTimeline(Timeline timeline) {
		return likeCommentRepository.findCommentListByTimelineId(timeline);
	}
	
	public LikeComment findCommentByUserIdAndCommentId(Integer userId, Integer commentId){

		LikeComment likeComment = new LikeComment();
		likeComment.setUserId(userId);
		likeComment.setParentCommentId(commentId);
		
		return likeCommentRepository.findCommentByUserIdAndCommentId(likeComment);
	}
	
	public void updateDelete(LikeComment likeComment) {
		likeCommentRepository.updateDelete(likeComment);
	}
	
	public LikeComment findLikeCommentByCommentId(LikeComment likeComment) {
		return likeCommentRepository.findLikeCommentByCommentId(likeComment);
	}
	
}
