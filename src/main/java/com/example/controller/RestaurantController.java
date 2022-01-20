package com.example.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	/**
	 * レストラン情報をロードします
	 * @param id 受け取ったレストランID
	 * @return IDから取得したレストラン情報
	 */
	@GetMapping(value = "/restaurant/{id}")
	public Map<String,Object> findById(@PathVariable Integer id) {
		
		Map<String, Object> map = new HashMap<>();
		
		Restaurant restaurant = new Restaurant();
		restaurant.setId(id);
		
		restaurant = restaurantService.findById(restaurant);
		
		if(restaurant == null) {
			map.put("status", "error");
			map.put("message", "不正なリクエストです");
			return map;
		}
		
		map.put("status", "success");
		map.put("message", "レストラン情報を表示します");
		map.put("restaurant", restaurant);	
		return map;
	}
	
	/**
	 * レストラン情報をホットペッパーIDでロードします
	 * @param id 受け取ったレストランのホットペッパーID
	 * @return IDから取得したレストラン情報
	 */
	@GetMapping(value = "/restaurant/hp/{hotpepperId}")
	public Map<String,Object> findByHotpepperId(@PathVariable String hotpepperId) {
		
		Map<String, Object> map = new HashMap<>();
		
		Restaurant restaurant = new Restaurant();
		restaurant.setHotpepperId(hotpepperId);
		restaurant = restaurantService.findByHotpepperId(restaurant);
		
		if(restaurant == null) {
			map.put("status", "error");
			map.put("message", "不正なリクエストです");
			return map;
		}
		
		map.put("status", "success");
		map.put("message", "レストラン情報を表示します");
		map.put("restaurant", restaurant);	
		return map;
		
	}
}
