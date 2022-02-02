package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Timeline;
import com.example.domain.User;
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
		List<Timeline> list = timelineRepository.findAllTimeline(timeline);
		return list;
	}

	public List<Timeline> findOld(Timeline timeline) {
		List<Timeline> list = timelineRepository.findAllTimelineOld(timeline);
		return list;
	}
	
	/**
	 * 渡されたUserのIDで投稿されたタイムライン最新50件を検索する。
	 * 
	 * @param user
	 * @return　
	 */
	public List<Timeline> showListByPostUserId(User requestedUser,User visitingUser) {
		
		// もらってきたUserをもとに,レポジトリへuserIdを渡す
		List<Timeline> list = timelineRepository.findByPostUserId(requestedUser.getId(),visitingUser.getId());
		return list;
	}
	
	/**
	 * 渡されたUserのIDで閲覧される側がいいねしたタイムライン最新50件を検索する。
	 * 
	 * @param user
	 * @return　
	 */
	public List<Timeline> showListByLikeUserId(User requestedUser,User visitingUser) {
		
		// もらってきたUserをもとに,レポジトリへuserIdを渡す
		List<Timeline> list = timelineRepository.findByLikeUserId(requestedUser.getId(),visitingUser.getId());
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
		
		timeline = timelineRepository.findTimelineById(timeline);
		
		return timeline;
	}
	
	public void updateDelete(Timeline timeline) {
		timelineRepository.updateDelete(timeline);
	}
}
