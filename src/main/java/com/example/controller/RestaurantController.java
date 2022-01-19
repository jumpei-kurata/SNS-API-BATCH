package com.example.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Restaurant;
import com.example.service.RestaurantService;

/**
 *　レストランのためのRestControllerです<br>
 * @author cyjoh
 *
 */
@RestController
public class RestaurantController {
	
	@Autowired
	private RestaurantService restaurantService;

	@GetMapping(value = "/restaurant")
	public Map<String,Object> getRestaurantList(){
		Map<String, Object> map = new HashMap<>();
		
		List<Restaurant> restaurantList = new ArrayList<>(); 
		restaurantList = restaurantService.getRestaurantList();
		
		if(restaurantList == null) {
			map.put("status", "error");
			map.put("message", "エラーが発生しました");
			return map;
		}
		if(restaurantList.size() == 0) {
			map.put("status", "success");
			map.put("message", "レストランが1件も登録されていません");
			return map;
		}
		
		map.put("status", "success");
		map.put("message", "レストラン一覧を表示します");
		map.put("restaurant", restaurantList);
		
		return map;

	}
}
