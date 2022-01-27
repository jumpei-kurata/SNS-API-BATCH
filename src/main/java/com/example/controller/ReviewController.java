package com.example.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.LikeComment;
import com.example.domain.Review;
import com.example.domain.User;
import com.example.form.InsertReviewForm;
import com.example.form.UserLogicalIdForm;
import com.example.service.ErrorService;
import com.example.service.LikeCommentService;
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
	private LikeCommentService likeCommentService;

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

		// userを論理IDで照合
		User user = new User();
		user.setLogicalId(userLogicalId);
		user = userService.findUserByLogicalId(user);

		// userLogicalIdが不正だった場合は、ここで弾かれる
		if (user == null) {
			map.put("status", "error");
			map.put("message", "ユーザーが存在しません。");
			return map;
		}

		Review review = new Review();
		review.setUserId(user.getId());

		List<Review> list = reviewService.findAll(review);

		if (list == null) {
			map.put("status", "error");
			map.put("message", "エラーが発生しました");
			return map;
		}
		if (list.size() == 0) {
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
	 * 該当のreviewIdより番号が小さい投稿を50件検索(古い投稿ロード)
	 * 
	 * @param reviewId
	 * @param userLogicalId
	 * @return
	 */
	@GetMapping(value = "/review/old/{reviewId}/{userLogicalId}")
	public Map<String, Object> findAllReviewOld(@PathVariable Integer reviewId ,@PathVariable String userLogicalId) {
		Map<String, Object> map = new HashMap<>();
		Review review = new Review();
		
		User user = new User();
		user.setLogicalId(userLogicalId);
		user = userService.findUserByLogicalId(user);
		
		if (user == null) {
			map.put("status", "error");
			map.put("message", "ユーザーが存在しません");
			return map;
		}
		
		review.setId(reviewId);
		review.setUserId(user.getId());
		
		List<Review>list = reviewService.findOld(review);
		
		map.put("status", "success");
		map.put("message", "タイムライン一覧の検索に成功しました");
		map.put("reviewList", list);
		return map;
	}
	
	/**
	 * レビューの詳細を表示します
	 * 
	 * @return 参照結果とIDに対応したレビューデータとコメントデータ
	 */
	@GetMapping(value = "/review/detail/{id}/{userLogicalId}")
	public Map<String, Object> findReviewById(@PathVariable Integer id, @PathVariable String userLogicalId) {
		Map<String, Object> map = new HashMap<>();

		// userを論理IDで照合
		User user = new User();
		user.setLogicalId(userLogicalId);
		user = userService.findUserByLogicalId(user);

		// userLogicalIdが不正だった場合は、ここで弾かれる
		if (user == null) {
			map.put("status", "error");
			map.put("message", "ユーザーが存在しません。");
			return map;
		}

		// レビューIDに対応したレビュー詳細
		Review review = new Review();
		review.setId(id);
		review.setUserId(user.getId());
		review = reviewService.findById(review);

		if (review == null) {
			map.put("status", "error");
			map.put("message", "そのレビューは存在しません");
			return map;
		}

		// レビューIDに対応したコメント一覧のロード
		List<LikeComment> commentList = new ArrayList<>();
		commentList = likeCommentService.findCommentListToReview(review);

		// それぞれの情報をマップに詰めて送る
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
	public Map<String, Object> postReview(@RequestBody @Validated InsertReviewForm form, BindingResult result) {
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
			map.put("message", "ユーザーが存在しません。");
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

	/**
	 * レビューを削除
	 * 
	 * @return
	 */
	@DeleteMapping(value = "/review/{reviewId}")
	public Map<String, Object> deleteReview(@PathVariable Integer reviewId, @RequestBody UserLogicalIdForm form) {
		Map<String, Object> map = new HashMap<>();

		// userを論理IDで照合
		User user = new User();
		user.setLogicalId(form.getUserLogicalId());
		user = userService.findUserByLogicalId(user);

		// userLogicalIdが不正だった場合は、ここで弾かれる
		if (user == null) {
			map.put("status", "error");
			map.put("message", "ユーザーが存在しません。");
			return map;
		}

		Review review = new Review();
		review.setId(reviewId);
		review = reviewService.findById(review);

		if (review == null) {
			map.put("status", "error");
			map.put("message", "そのレビューは存在しません");
			return map;
		}

		if (user.getId() != review.getUserId()) {

			map.put("status", "error");
			map.put("message", "このレビューを削除できるアカウントではありません");
			return map;
		}

		reviewService.deleteReview(review);
		map.put("status", "success");
		map.put("message", "レビューの削除に成功しました");
		return map;
	}

	/**
	 * テスト用のメソッド。 50件レビューを登録する。
	 * 
	 * @param form
	 * @param result
	 * @return
	 */
	@PostMapping(value = "/test/review/{times}")
	public Map<String, Object> testPostReview(@RequestBody @Validated InsertReviewForm form, BindingResult result,
			@PathVariable String times) {
		Map<String, Object> map = new HashMap<>();

		int intTimes = Integer.parseInt(times);
		
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
			map.put("message", "ユーザーが存在しません。");
			return map;
		}

		// レビューをインスタンス化、formのデータとUserIdを詰める
		for (int i = 0; i < intTimes; i++) {
			Review review = new Review();
			review.setUserId(user.getId());
			BeanUtils.copyProperties(form, review);

			review.setSentence(review.getSentence()+i);
			
			// レビューのインサート処理と、レストランの星を更新（サービスで一括）
			reviewService.postReview(review);
		}
		
		map.put("status", "success");
		map.put("message", "レビューの投稿に成功しました");
		return map;
	}

}
