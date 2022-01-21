package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.LikeComment;
import com.example.domain.Timeline;
import com.example.domain.User;
import com.example.form.InsertTimelineForm;
import com.example.form.LikeForm;
import com.example.service.ErrorService;
import com.example.service.LikeCommentService;
import com.example.service.TimelineService;
import com.example.service.UserService;

/**
 * タイムラインに関するコントローラーです
 * 
 * @author ootomokenji
 *
 */
@RestController
public class TimelineController {
	
	@Autowired
	private TimelineService timelineService;
	@Autowired
	private LikeCommentService likeCommentService;
	@Autowired
	private UserService userService;
	@Autowired
	private ErrorService errorService;

	/**
	 * タイムラインを最新50件検索します
	 * 
	 * @return
	 */
	@GetMapping(value = "/timeline/{userLogicalId}")
	public Map<String, Object> findAllTimeline(@PathVariable String userLogicalId) {
		Map<String, Object> map = new HashMap<>();
		Timeline timeline = new Timeline();
		
		User user = new User();
		user.setLogicalId(userLogicalId);
		user = userService.findUserByLogicalId(user);
		
		timeline.setUserId(user.getId());
		
//		long start = System.currentTimeMillis();
		List<Timeline>list = timelineService.findAll(timeline);
//		long end = System.currentTimeMillis();
//		System.out.println((end - start) +"ミリ秒");
		
		map.put("status", "success");
		map.put("message", "タイムライン一覧の検索に成功しました");
		map.put("TimelineList", list);
		return map;
	}
	
	/**
	 * タイムラインを投稿します
	 * 
	 * @param form
	 * @param result
	 * @return
	 */
	@PostMapping(value = "/timeline")
	public Map<String, Object> insertTimeline(@RequestBody @Validated InsertTimelineForm form,BindingResult result) {
		Map<String, Object> map = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errorMessageList = errorService.errorMessage(result);
			
			map.put("status", "error");
			map.put("message", errorMessageList);
			return map;
		}
		
		Timeline timeline = new Timeline();
		BeanUtils.copyProperties(form, timeline);
		timelineService.insertTimeline(timeline);
		
		map.put("status", "success");
		map.put("message", "タイムラインの投稿に成功しました");
		return map;
	}
	
	/**
	 * タイムラインにいいねをします
	 * 
	 * @param form
	 * @return
	 */
	@PostMapping(value = "/timeline/like")
	public Map<String, Object> insertLike(@RequestBody LikeForm form) {
		Map<String, Object>map = new HashMap<>();
		
		LikeComment likeComment = new LikeComment();
		BeanUtils.copyProperties(form, likeComment);
		likeComment = likeCommentService.findLikeComment(likeComment);
		
		if (likeComment == null) {
			likeComment = new LikeComment();
			likeComment.setUserId(form.getUserId());
			likeComment = likeCommentService.insertLikeComment(likeComment);
			
			likeCommentService.insertLinkToTimeline(form.getTimelineId(),likeComment.getId());
			
			Timeline timeline = new Timeline();
			timeline.setId(form.getTimelineId());
			likeCommentService.updateLikeCount(timeline);
			
			map.put("status", "success");
			map.put("message", "いいねを登録しました");
			return map;
		}
		
		likeCommentService.updateLike(likeComment);
		
		map.put("status", "success");
		map.put("message", "いいねを更新しました");
		return map;
	}
	
	@PostMapping(value = "/timeline/comment")
	public Map<String, Object> insertComment() {
		Map<String, Object> map = new HashMap<>();
		
		return map;
	}
	
	
	
	
	
	
	/**
	 * テスト用で作りました　タイムラインをnumber回インサートします
	 * 
	 * @param form
	 * @param result
	 * @param number
	 * @return
	 */
	@PostMapping(value = "/timeline/test/{number}")
	public Map<String, Object> insertTimelineTest(@RequestBody @Validated InsertTimelineForm form,BindingResult result,@PathVariable Integer number) {
		Map<String, Object> map = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errorMessageList = errorService.errorMessage(result);
			
			map.put("status", "error");
			map.put("message", errorMessageList);
			return map;
		}
		
		Timeline timeline = new Timeline();
		BeanUtils.copyProperties(form, timeline);
		
		for (int i = 0; i < number; i++) {
			timelineService.insertTimeline(timeline);
		}
		
		map.put("status", "success");
		map.put("message", "タイムラインの投稿に成功しました");
		return map;
	}
	
	/**
	 * 上に同じ　いいねしますテスト
	 * 
	 * @param form
	 * @param number
	 * @return
	 */
	@PostMapping(value = "/timeline/like/test/{number}")
	public Map<String, Object> insertLikeTest(@RequestBody LikeForm form,@PathVariable Integer number) {
		Map<String, Object>map = new HashMap<>();
		LikeComment likeComment = new LikeComment();
		
		for (int i = 0; i < number; i++) {
			likeComment.setUserId(form.getUserId());
			likeComment = likeCommentService.insertLikeComment(likeComment);
			
			likeCommentService.insertLinkToTimeline(form.getTimelineId(),likeComment.getId());
			
			Timeline timeline = new Timeline();
			timeline.setId(form.getTimelineId());
			likeCommentService.updateLikeCount(timeline);
			
		}
		map.put("status", "success");
		map.put("message", "いいねに成功しました");
		return map;
	}
	
}
