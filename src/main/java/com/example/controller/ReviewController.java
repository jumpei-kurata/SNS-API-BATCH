package com.example.controller;

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

import com.example.domain.Review;
import com.example.form.InsertReviewForm;
import com.example.service.ErrorService;
import com.example.service.ReviewService;

/**
 * レビューに関するコントローラーです。<br>
 * 
 * @author cyjoh
 *
 */
@RestController
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	@Autowired
	private ErrorService errorService;

	/**
	 * レビューを最新50件検索します
	 * 
	 * @return
	 */
	@GetMapping(value = "/review/{userLogicalId}")
	public Map<String, Object> findAllReview(@PathVariable String userLogicalId) {
		Map<String, Object> map = new HashMap<>();

		List<Review> list = reviewService.findAll(userLogicalId);
		
		if(list == null) {
			map.put("status", "error");
			map.put("message", "エラーが発生しました");
			return map;
		}
		if(list.size() == 0) {
			map.put("status", "success");
			map.put("message", "レビューがが1件も登録されていません");
			return map;
		}
		
		map.put("status", "success");
		map.put("message", "レビュー一覧の検索に成功しました");
		map.put("ｒeviewList", list);
		return map;
	}

	/**
	 * レビューを投稿します
	 * 
	 * @param form
	 * @param result
	 * @return　
	 */
	@PostMapping(value = "/review")
	public Map<String, Object> insertReview(@RequestBody @Validated InsertReviewForm form, BindingResult result) {
		Map<String, Object> map = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errorMessageList = errorService.errorMessage(result);

			map.put("status", "error");
			map.put("message", errorMessageList);
			return map;
		}

		Review review = new Review();
		BeanUtils.copyProperties(form, review);
		reviewService.insertReview(review);

		map.put("status", "success");
		map.put("message", "レビューの投稿に成功しました");
		return map;
	}

}
