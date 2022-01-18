package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Timeline;
import com.example.service.TimelineService;

@RestController
public class TimelineController {
	
	@Autowired
	private TimelineService timelineService;

	@GetMapping(value = "/timeline")
	public Map<String, Object> findAllTimeline() {
		Map<String, Object> map = new HashMap<>();
		
		List<Timeline>list = timelineService.findAll();
		
		map.put("TimelineList", list);
		return map;
	}
}
