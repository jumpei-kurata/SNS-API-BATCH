package com.example.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Restaurant;
import com.example.repository.RestaurantRepository;

/**
 * レストランクラスに関係する業務処理を行うサービスクラスです。<br>
 * 
 * @author cyjoh
 *
 */
@Service
@Transactional
public class RestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepository;

	/**
	 * レストランの情報を50件取得します。
	 * 
	 * @return レストランの情報
	 */
	public List<Restaurant> getRestaurantList() {
		return restaurantRepository.findByDefault();
	}
	
	/**
	 * レストランの情報を指定されたIDよりDIDが小さいもののリストを50件取得します。
	 * 
	 * @return レストランの情報
	 */
	public List<Restaurant> getRestaurantListMore(Restaurant restaurant) {
		return restaurantRepository.findByDefaultMore(restaurant);
	}
	
	/**
	 * レストランの情報をIDに合わせて1件取得します。
	 * 
	 * @return レストランの情報
	 */
	public Restaurant findById(Restaurant restaurant) {
		return restaurantRepository.findById(restaurant);
	}
	
	/**
	 * レストランの情報をホットペッパーIDに合わせて1件取得します。
	 * 
	 * @return レストランの情報
	 */
	public Restaurant findByHotpepperId(Restaurant restaurant) {
		return restaurantRepository.findByHotpepperId(restaurant);
	}
	
	/**
	 * レストランの情報を名前のあいまい検索で取得します。
	 * 
	 * @return レストランの情報
	 */
	public List<Restaurant> findByName(Restaurant restaurant) {
		return restaurantRepository.findByName(restaurant);
	}

	/**
	 * レストランの情報をデータベースに登録します。
	 * 
	 * @return レストランの情報
	 */
	public Restaurant insert(Restaurant restaurant) {
		if(restaurant.getPhotoPath() == null) {
			restaurant.setPhotoPath("r_"+restaurant.getGenreFk()+".jpeg");
		}
		restaurantRepository.insertRestaurant(restaurant);
		return restaurant;
	}
	
	/**
	 * 並び替え用のステータスを受け取り、レストラン検索をします。
	 * 
	 * @return レストランの情報
	 */
	public List<Restaurant> findRestaurants(String order, String genre, Integer type) {
		
		List<Restaurant> restaurants = restaurantRepository.findAll();
		List<Restaurant> restList = new ArrayList<>();
		
		// 1.「最新順」かつ「ジャンル」のみが指定されている場合
		// 2.「最新順」かつ「タイプ」のみが指定されている場合
		// 3.「最新順」かつ「ジャンル」とタイプのどちらも指定されている場合
		// 4.「最新順」かつ「ジャンル」と「タイプ」のどちらも指定されていない場合
		if (order.equals("最新順") && !(genre.equals("G000")) && type.equals(0)) {
			restList = restaurants.stream()
					.filter(rest -> rest.getGenreFk().equals(genre))
					.sorted(Comparator.comparing(Restaurant::getId).reversed())
					.collect(Collectors.toList());
		} else if (order.equals("最新順") && genre.equals("G000") && !(type.equals(0))) {
			restList = restaurants.stream()
					.filter(rest -> rest.getType().equals(type))
					.sorted(Comparator.comparing(Restaurant::getId).reversed())
					.collect(Collectors.toList());
		} else if (order.equals("最新順") && !(genre.equals("G000") && type.equals(0))) {
			restList = restaurants.stream()
					.filter(rest -> rest.getGenreFk().equals(genre) && rest.getType().equals(type))
					.sorted(Comparator.comparing(Restaurant::getId).reversed())
					.collect(Collectors.toList());
		} else if (order.equals("最新順") && genre.equals("G000") && type.equals(0)) {
			restList = restaurants.stream()
					.sorted(Comparator.comparing(Restaurant::getId).reversed())
					.collect(Collectors.toList());
		}
		
		// 1.「評価順」かつ「ジャンル」のみが指定されている場合
		// 2.「評価順」かつ「タイプ」のみが指定されている場合
		// 3.「評価順」かつ「ジャンル」とタイプのどちらも指定されている場合
		// 4.「評価順」かつ「ジャンル」と「タイプ」のどちらも指定されていない場合
		if (order.equals("評価順") && !(genre.equals("G000")) && type.equals(0)) {
			restList = restaurants.stream()
					.filter(rest -> rest.getGenreFk().equals(genre))
					.sorted(Comparator.comparing(Restaurant::getId).reversed())
					.sorted(Comparator.comparing(Restaurant::getStar).reversed())
					.collect(Collectors.toList());
		} else if (order.equals("評価順") && genre.equals("G000") && !(type.equals(0))) {
			restList = restaurants.stream()
					.filter(rest -> rest.getType().equals(type))
					.sorted(Comparator.comparing(Restaurant::getId).reversed())
					.sorted(Comparator.comparing(Restaurant::getStar).reversed())
					.collect(Collectors.toList());
		} else if (order.equals("評価順") && !(genre.equals("G000") && type.equals(0))) {
			restList = restaurants.stream()
					.filter(rest -> rest.getGenreFk().equals(genre) && rest.getType().equals(type))
					.sorted(Comparator.comparing(Restaurant::getId).reversed())
					.sorted(Comparator.comparing(Restaurant::getStar).reversed())
					.collect(Collectors.toList());
		} else if (order.equals("評価順") && genre.equals("G000") && type.equals(0)) {
			restList = restaurants.stream()
					.sorted(Comparator.comparing(Restaurant::getId).reversed())
					.sorted(Comparator.comparing(Restaurant::getStar).reversed())
					.collect(Collectors.toList());
		}
		
		System.out.println(restList);
		
		return restList;
	}
}
