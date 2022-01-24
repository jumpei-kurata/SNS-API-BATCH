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
	public List<Review> findAll(String userLogicalId) {
		List<Review> list = reviewRepository.findAllReview(userLogicalId);
		return list;
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
