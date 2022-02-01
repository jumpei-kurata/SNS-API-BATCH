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
		
		// 並び替え
		if (order.equals("最新順")) {
			restList = restaurants.stream()
				.sorted(Comparator.comparing(Restaurant::getId).reversed())
				.collect(Collectors.toList());
		} else if (order.equals("評価順")) {
			restList = restaurants.stream()
				.sorted(Comparator.comparing(Restaurant::getStar).reversed())
				.collect(Collectors.toList());
		}
		
		// 1.ジャンルのみが指定されている場合
		// 2.タイプのみが指定されている場合
		// 3.ジャンルとタイプのどちらも指定されている場合
		if (!(genre.equals("G000")) && type.equals(0)) {
			restList = restaurants.stream()
					.filter(rest -> rest.getGenreFk().equals(genre))
					.collect(Collectors.toList());
		} else if (genre.equals("G000") && !(type.equals(0))) {
			restList = restaurants.stream()
					.filter(rest -> rest.getType().equals(type))
					.collect(Collectors.toList());
		} else if (!(genre.equals("G000") && type.equals(0))) {
			restList = restaurants.stream()
					.filter(rest -> rest.getGenreFk().equals(genre) && rest.getType().equals(type))
					.collect(Collectors.toList());
		}
		
		return restList;
	}
}
