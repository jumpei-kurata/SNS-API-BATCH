package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Restaurant;
import com.example.domain.Review;
import com.example.repository.RestaurantRepository;
import com.example.repository.ReviewRepository;


/**
 * レビューに関するサービスです<br>
 * 
 * @author cyjoh
 *
 */
@Service
@Transactional
public class ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	/**
	 * レビューの50件検索
	 * 
	 * @return
	 */
	public List<Review> findAll(Review review) {
		List<Review> list = reviewRepository.findAllReview(review);
		return list;
	}
	
	
	/**
	 * レビューの50件検索
	 * 
	 * @return
	 */
	public List<Review> showReviewListForRestaurant(Review review) {
		List<Review> list = reviewRepository.findByRestaurantId(review);
		return list;
	}
	
	/**
	 * 渡されたレビューのレビューIDより古いレビューを50件検索
	 * 
	 * @return
	 */
	public List<Review> findOld(Review review) {
		List<Review> list = reviewRepository.findAllReviewOld(review);
		return list;
	}
	
	/**
	 * レビューをIDで検索
	 * 
	 * @return
	 */
	public Review findById(Review review) {
		review = reviewRepository.findById(review);
		return review;
	}
	
	/**
	 * レビューを投稿する
	 * 
	 * @param review
	 * @return
	 */
	public Review postReview(Review review) {
		// レビューデータをインサート
		reviewRepository.insertReview(review);
		
		//　その後、そのレストランIDを使って、そのレストランの星（評価）を更新（引数がレストランなのでレストランに詰めてる）
		Restaurant restaurant = new Restaurant();
		restaurant.setId(review.getRestaurantId());
		restaurantRepository.updateStar(restaurant);

		return review;
	}
	
	/**
	 * レビューを削除する
	 * 
	 * @param review
	 */
	public void deleteReview(Review review) {
		// レビューデータのdeletedカラムをupdate
		reviewRepository.deleteReview(review);
		
		//　その後、そのレストランIDを使って、そのレストランの星（評価）を更新（引数がレストランなのでレストランに詰めてる）
		Restaurant restaurant = new Restaurant();
		restaurant.setId(review.getRestaurantId());
		restaurantRepository.updateStar(restaurant);
		
	}
}
