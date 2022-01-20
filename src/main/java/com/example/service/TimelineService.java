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
	
	
	
	/**
	 * タイムライン全件検索
	 * 
	 * @return
	 */
	public List<Timeline> findAll() {
		List<Timeline>list = timelineRepository.findAllTimeline();
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
	public void insertLinkToTimeline(LinkToTimeline linkToTimeline) {
		linkToTimelineRepository.insertLinksToTimeline(linkToTimeline);
	}
	
	/**
	 * いいねカウント+1
	 * 
	 * @param timeline
	 */
	public void updateLikeCount(Timeline timeline) {
		timelineRepository.updateLikeCount(timeline);
	}
	
	/**
	 * いいねしたかチェック
	 * 
	 * @param linkToTimeline
	 * @return
	 */
	public boolean likeflg(LinkToTimeline linkToTimeline) {
		List<LinkToTimeline>list = linkToTimelineRepository.findLinkToTimelineByTimelineId(linkToTimeline);
		//誰からもいいねされていない
		if (list.isEmpty()) {
			return false;
		}
		//このユーザーはいいねしている
		for (LinkToTimeline ltt : list) {
			System.out.println(ltt);
			if (ltt.getUserId() == linkToTimeline.getUserId()) {
				return true;
			}
		}
		//このユーザーはいいねしていない
		return false;
	}
}
