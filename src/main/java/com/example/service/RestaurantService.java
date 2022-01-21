package com.example.service;

import java.util.List;

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
	 * レストランの情報を全件取得します。
	 * 
	 * @return レストランの情報
	 */
	public List<Restaurant> getRestaurantList() {
		return restaurantRepository.findAll();
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
}
