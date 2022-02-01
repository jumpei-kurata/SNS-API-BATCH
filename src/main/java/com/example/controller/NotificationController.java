package com.example.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Notification;
import com.example.domain.User;
import com.example.service.NotificationService;
import com.example.service.UserService;

@RestController
public class NotificationController {

	@Autowired
	private UserService userService;

	@Autowired
	private NotificationService notificationService;

	@GetMapping(value= "/notifications/{userLogicalId}")
	public Map<String,Object> getNotificationList(@PathVariable String userLogicalId){
		Map<String, Object> map = new HashMap<>();
		
		// userを論理IDで照合
		User user = new User();
		user.setLogicalId(userLogicalId);
		user = userService.findUserByLogicalId(user);

		// userLogicalIdが不正だった場合は、ここで弾かれる
		if (user == null) {
			map.put("status", "error");
			map.put("message", "ユーザーが存在しません。");
			return map;
		}
		
		List<Notification> list = new ArrayList<>();
		list = notificationService.showNotificationListByUser(user); 
	
		if (list.size() == 0) {
			map.put("status", "success");
			map.put("message", "通知はまだありません");
			return map;
		}
		
		map.put("status", "success");
		map.put("message", "通知一覧の検索に成功しました");
		map.put("notificationList", list);
		return map;
	}
}
