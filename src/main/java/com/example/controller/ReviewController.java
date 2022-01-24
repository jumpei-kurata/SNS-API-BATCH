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

import com.example.domain.LikeComment;
import com.example.domain.Review;
import com.example.domain.User;
import com.example.form.InsertReviewForm;
import com.example.service.ErrorService;
import com.example.service.ReviewService;
import com.example.service.UserService;

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
	
	@Autowired
	private UserService userService;

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
			map.put("message", "レビューが1件も登録されていません");
			return map;
		}
		
		map.put("status", "success");
		map.put("message", "レビュー一覧の検索に成功しました");
		map.put("reviewList", list);
		return map;
	}

	
	/**
	 * レビューの詳細を表示します
	 * 
	 * @return　参照結果とIDに対応したレビューデータとコメントデータ
	 */
	@GetMapping(value = "/review/detail/{id}/{userLogicalId}")
	public Map<String, Object> findReviewById(@PathVariable Integer id,@PathVariable String userlogicalId) {
		Map<String, Object> map = new HashMap<>();

		// レビューIDに対応したレビュー詳細
		Review review = new Review();
		review.setId(id);
		review = reviewService.findById(userlogicalId,review);
		
		if(review == null) {
			map.put("status", "error");
			map.put("message", "そのレビューは存在しません");
			return map;
		}

		// レビューIDに対応したコメント一覧のロード
		List<LikeComment> commentList = new ArrayList<>();  
		
		// 今はまだコメントリストはロードしてません。
		
		map.put("status", "success");
		map.put("message", "レビューの検索に成功しました");
		map.put("review", review);
		
		map.put("commentList", commentList);
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
	public Map<String, Object> postReview(@RequestBody @Validated InsertReviewForm form,BindingResult result) {
		Map<String, Object> map = new HashMap<>();
		
		// バリデーション
		if (result.hasErrors()) {
			List<String> errorMessageList = errorService.errorMessage(result);
			
			map.put("status", "error");
			map.put("message", errorMessageList);
			return map;
		}
		
		// userLogicalIdをもとに、userデータを取得する
		User user = new User();
		user.setLogicalId(form.getUserLogicalId());
		user = userService.findUserByLogicalId(user);
		
		// userLogicalIdが不正だった場合は、ここで弾かれる
		if (user == null) {
			map.put("status", "error");
			map.put("message","ユーザーが存在しません。");
			return map;
		}
		
		// レビューをインスタンス化、formのデータとUserIdを詰める
		Review review = new Review();
		review.setUserId(user.getId());
		BeanUtils.copyProperties(form, review);
		
		// レビューのインサート処理と、レストランの星を更新（サービスで一括）
		reviewService.postReview(review);
		
		map.put("status", "success");
		map.put("message", "レビューの投稿に成功しました");
		return map;
	}
}
