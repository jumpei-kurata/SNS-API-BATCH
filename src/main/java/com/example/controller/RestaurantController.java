package com.example.controller;

import java.util.ArrayList;
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

import com.example.domain.Restaurant;
import com.example.form.FindRestaurantsForm;
import com.example.form.InsertRestaurantByHotpepperForm;
import com.example.form.InsertRestaurantForm;
import com.example.service.ErrorService;
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

	@Autowired
	private ErrorService errorService;

	/**
	 * レストラン一覧について、IDが新しい順に50件ロードします。
	 * 
	 * @return
	 */
//	@GetMapping(value = "/restaurant")
//	public Map<String,Object> getRestaurantList(){
//		Map<String, Object> map = new HashMap<>();
//		
//		List<Restaurant> restaurantList = new ArrayList<>(); 
//		restaurantList = restaurantService.getRestaurantList();
//		
//		if(restaurantList == null) {
//			map.put("status", "error");
//			map.put("message", "エラーが発生しました");
//			return map;
//		}
//		if(restaurantList.size() == 0) {
//			map.put("status", "success");
//			map.put("message", "レストランが1件も登録されていません");
//			return map;
//		}
//		
//		map.put("status", "success");
//		map.put("message", "レストラン一覧を表示します");
//		map.put("restaurant", restaurantList);
//		
//		return map;
//
//	}

	/**
	 * レストラン一覧について、渡されたレストランより古い50件をロードします。
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/restaurant/more/{id}")
	public Map<String,Object> getRestaurantListMore(@PathVariable Integer id){
		Map<String, Object> map = new HashMap<>();
		
		// レストランサービスの引数に合わせるために、レストランドメインに情報を詰める
		Restaurant param = new Restaurant();
		param.setId(id) ;
		
		// 返す用のリストを作って、サービスに値を渡してロードする
		List<Restaurant> restaurantList = new ArrayList<>(); 
		restaurantList = restaurantService.getRestaurantListMore(param);
		
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
	
	
	@PostMapping(value = "/restaurant")
	public Map<String, Object> insertRestaurant(@RequestBody @Validated InsertRestaurantForm form, BindingResult result){
		
		Map<String, Object> map = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errorMessageList = errorService.errorMessage(result);
			
			map.put("status", "error");
			map.put("message", errorMessageList);
			return map;
		}
		
		Restaurant restaurant = new Restaurant();
		BeanUtils.copyProperties(form, restaurant);
		
		restaurant = restaurantService.insert(restaurant);
		
		map.put("status", "success");
		map.put("message", "レストラン登録に成功しました。");
		restaurant = restaurantService.findById(restaurant);
		map.put("restaurant", restaurant);
		return map;
		
		
	}

	@PostMapping(value = "/restaurant/hp")
	public Map<String, Object> insertRestaurantByHotpepper(@RequestBody @Validated InsertRestaurantByHotpepperForm form, BindingResult result){
		
		Map<String, Object> map = new HashMap<>();
		
		if (result.hasErrors()) {
			List<String> errorMessageList = errorService.errorMessage(result);
			
			map.put("status", "error");
			map.put("message", errorMessageList);
			return map;
		}
		
		Restaurant restaurant = new Restaurant();
		BeanUtils.copyProperties(form, restaurant);
		
		restaurant = restaurantService.insert(restaurant);
		
		map.put("status", "success");
		map.put("message", "レストラン登録に成功しました。");
		restaurant = restaurantService.findById(restaurant);
		map.put("restaurant", restaurant);
		return map;
		
		
	}
	
	@GetMapping(value = "/restaurant/name/{name}")
	public Map<String,Object> findByName(@PathVariable String name){
		Map<String, Object> map = new HashMap<>();
		
		List<Restaurant> restaurantList = new ArrayList<>(); 
		Restaurant restaurant = new Restaurant();
		
		restaurant.setName(name);
		restaurantList = restaurantService.findByName(restaurant);
		
		if(restaurantList == null) {
			map.put("status", "error");
			map.put("message", "エラーが発生しました");
			return map;
		}
		if(restaurantList.size() == 0) {
			map.put("status", "success");
			map.put("message", "該当するレストランはありません。");
			return map;
		}
		
		map.put("status", "success");
		map.put("message", "該当するレストラン一覧を表示します");
		map.put("restaurant", restaurantList);
		
		return map;

	}
	
	/**
	 * 並び替え用のステータスを受け取り、レストランを検索します。
	 * @param form
	 * @return レストランの情報
	 */
	@GetMapping(value = "/restaurant")
	public Map<String, Object> findRestaurants(String order, String genre, int type) {
		Map<String, Object> map = new HashMap<>();
		
		List<Restaurant> restaurantList = new ArrayList<>(); 
		restaurantList = restaurantService.findRestaurants(order, genre, type);
		
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
