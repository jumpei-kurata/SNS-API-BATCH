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
import com.example.domain.LinkToTimeline;
import com.example.domain.Timeline;
import com.example.form.InsertTimelineForm;
import com.example.form.LikeForm;
import com.example.service.ErrorService;
import com.example.service.TimelineService;

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
	private ErrorService errorService;

	/**
	 * タイムラインを最新50件検索します
	 * 
	 * @return
	 */
	@GetMapping(value = "/timeline")
	public Map<String, Object> findAllTimeline() {
		Map<String, Object> map = new HashMap<>();
		
//		long start = System.currentTimeMillis();
		List<Timeline>list = timelineService.findAll();
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
		
		LinkToTimeline linkToTimeline = new LinkToTimeline(form.getTimelineId(), null,form.getUserId());
		
		if (timelineService.likeflg(linkToTimeline)) {
			map.put("status", "error");
			map.put("message", "既にいいねされています");
			return map;
		}
		
		LikeComment likeComment = new LikeComment();
		likeComment.setUserId(form.getUserId());
		likeComment = timelineService.insertLikeComment(likeComment);
		
		linkToTimeline.setLikeCommentId(likeComment.getId());
		timelineService.insertLinkToTimeline(linkToTimeline);
		
		Timeline timeline = new Timeline();
		timeline.setId(form.getTimelineId());
		timelineService.updateLikeCount(timeline);
		
		map.put("status", "success");
		map.put("message", "いいねに成功しました");
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
	 * 上に同じ　いいねします
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
			likeComment = timelineService.insertLikeComment(likeComment);
			
			LinkToTimeline linkToTimeline = new LinkToTimeline(form.getTimelineId(), likeComment.getId(),null);
			timelineService.insertLinkToTimeline(linkToTimeline);
			
		}
		map.put("status", "success");
		map.put("message", "いいねに成功しました");
		return map;
	}
	
}
