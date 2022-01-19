package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Timeline;
import com.example.form.InsertTimelineForm;
import com.example.service.TimelineService;

@RestController
public class TimelineController {
	
	@Autowired
	private TimelineService timelineService;

	@GetMapping(value = "/timeline")
	public Map<String, Object> findAllTimeline() {
		Map<String, Object> map = new HashMap<>();
		
		List<Timeline>list = timelineService.findAll();
		
		map.put("status", "success");
		map.put("message", "タイムライン一覧の検索に成功しました");
		map.put("TimelineList", list);
		return map;
	}
	
	@PostMapping(value = "/timeline")
	public Map<String, Object> insertTimeline(@RequestBody InsertTimelineForm form) {
		Map<String, Object> map = new HashMap<>();

		
		Timeline timeline = new Timeline();
		BeanUtils.copyProperties(form, timeline);
		timelineService.insertTimeline(timeline);
		
		map.put("status", "success");
		map.put("message", "タイムラインの投稿に成功しました");
		return map;
	}
}
