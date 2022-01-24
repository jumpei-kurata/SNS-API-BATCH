package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Review;
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
	
	/**
	 * レビュー全件検索
	 * 
	 * @return
	 */
	public List<Review> findAll(String ｌogicalId) {
		List<Review> list = reviewRepository.findAllReview(ｌogicalId);
		return list;
	}
	
	/**
	 * レビューをIDで検索
	 * 
	 * @return
	 */
	public Review findById(String logicalId,Review review) {
		review = reviewRepository.findById(review,logicalId);
		return review;
	}
	
	/**
	 * レビュー投稿
	 * 
	 * @param review
	 * @return
	 */
	public Review insertReview(Review review) {
		reviewRepository.insertReview(review);
		return review;
	}
}
