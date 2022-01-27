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
import com.example.form.InsertReviewCommentForm;
import com.example.form.InsertReviewForm;
import com.example.form.LikeCommentForm;
import com.example.form.LikeReviewForm;
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
		map.put("message", "レビュー一覧の検索に成功しました");
		map.put("reviewList", list);
		return map;
	}
	
	/**
	 * レストランIDを指定して、そのレストランに関するレビューを最新50件検索します
	 * 
	 * @return
	 */
	@GetMapping(value = "/review/restaurant/{restaurantId}/{userLogicalId}")
	public Map<String, Object> findReviewListForRestaurant(@PathVariable Integer restaurantId,@PathVariable String userLogicalId) {
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
		review.setRestaurantId(restaurantId);
		
		List<Review> list = reviewService.showReviewListForRestaurant(review);
		
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
	 * 該当のreviewIdより番号が小さい投稿で、レストランIDを指定して、そのレストランに関するレビューを50件検索(レストラン絞りしつつ、古い投稿ロード)
	 * 
	 * @return
	 */
	@GetMapping(value = "/review/restaurant/old/{restaurantId}/{reviewId}/{userLogicalId}")
	public Map<String, Object> findReviewListOlderForRestaurant
	(@PathVariable Integer restaurantId , @PathVariable Integer reviewId , @PathVariable String userLogicalId) {
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
		review.setId(reviewId);
		review.setRestaurantId(restaurantId);
		
		List<Review> list = reviewService.showOlderReviewListForRestaurant(review);
		
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
	 * レビューにいいねをします
	 * 
	 * @param form
	 * @return
	 */
	@PostMapping(value = "/review/like")
	public Map<String, Object> insertLike(@RequestBody LikeReviewForm form) {
		Map<String, Object>map = new HashMap<>();
		
		// 論理ID照合
		User user = new User();
		user.setLogicalId(form.getUserLogicalId());
		user = userService.findUserByLogicalId(user);
		
		if (user == null) {
			map.put("status", "error");
			map.put("message", "ユーザーが存在しません");
			return map;
		}
		
		// 渡されたUserと渡されたReviewに該当するいいねがある/あったか検索
		LikeComment likeComment = 
				likeCommentService.findLikeToReviewByUserIdAndReviewId(user.getId(), form.getReviewId());
		
		// いいねがなければ新しく挿入する（コメントがすでに有っても、いいねがなければ新しく挿入する）
		// その後結果を戻す
		if (likeComment == null) {
			likeComment = new LikeComment();
			likeComment.setUserId(user.getId());
			likeComment.setReviewId(form.getReviewId());

			likeComment = likeCommentService.insertLikeToReview(likeComment);
			
			map.put("status", "success");
			map.put("message", "いいねを登録しました");
			return map;
		}
		
		// いいねがあればそれを更新する
		// その後結果を戻す
		likeCommentService.updateLike(likeComment);
		
		if (likeComment.isLike()) {
			likeCommentService.updateLikeCountToReview(form.getReviewId(),1);
			map.put("message", "いいねを削除しました");
		}else {
			likeCommentService.updateLikeCountToReview(form.getReviewId(),0);
			map.put("message", "いいねを登録しました");
		}

		map.put("status", "success");
		return map;
	}
	
	/**
	 * レビューにコメントを登録します
	 * 
	 * @param form
	 * @return
	 */
	@PostMapping(value = "/review/comment")
	public Map<String, Object> insertComment(@RequestBody InsertReviewCommentForm form) {
		Map<String, Object> map = new HashMap<>();
		
		// 論理IDが正しいか、Userをロードして確認
		User user = new User();
		user.setLogicalId(form.getUserLogicalId());
		user = userService.findUserByLogicalId(user);
		
		if (user == null) {
			map.put("status", "error");
			map.put("message", "ユーザーが存在しません");
			return map;
		}
		
		// 他にLikeCommentが同レビュー、同作成ユーザーがあっても、スルーしてインサートする流れに変更
		// LikeCommentを生成、コメントを生成してインサート
		
		LikeComment likeComment = new LikeComment();
		likeComment.setUserId(user.getId());
		likeComment.setReviewId(form.getReviewId());
		likeComment.setComment(form.getSentence());

		likeComment = likeCommentService.insertCommentToReview(likeComment);
		
		
		map.put("status", "success");
		map.put("message", "コメントを登録しました");
		return map;
	}

	/**
	 * レビューに対するコメントを削除します
	 * 
	 * @param commentId
	 * @param userLogicalId
	 * @return
	 */
	@DeleteMapping(value = "/review/comment/{commentId}/{userLogicalId}")
	public Map<String, Object> DeleteComment(@PathVariable Integer commentId,@PathVariable String userLogicalId) {
		Map<String, Object> map = new HashMap<>();
		
		User user = new User();
		user.setLogicalId(userLogicalId);
		user = userService.findUserByLogicalId(user);
		
		if (user == null) {
			map.put("status", "error");
			map.put("message", "ユーザーが存在しません");
			return map;
		}
		
		LikeComment likeComment = new LikeComment();
		likeComment.setId(commentId);
		
		// 該当のcommentをロード
		likeComment = likeCommentService.load(likeComment);
		
		// ユーザーが違ったらreturn
		if (user.getId() != likeComment.getUserId()) {
			map.put("status", "error");
			map.put("message", "このコメントを削除できるアカウントではありません");
			return map;
		}
		
		// 削除処理を実行、もしすでに削除されていればnullをreturn
		likeComment = likeCommentService.updateDelete(likeComment);
		
		if (likeComment == null) {
			map.put("status", "error");
			map.put("message", "そのコメントは存在していません");
			return map;
		}
		
		map.put("status", "success");
		map.put("message", "コメントの削除に成功しました");
		return map;
	}
	
	
	/**
	 * レビューコメントに良いね
	 * 
	 * @param form
	 * @return
	 */
	@PostMapping(value = "/review/comment/like")
	public Map<String, Object> insertCommentInLike(@RequestBody LikeCommentForm form) {
		Map<String, Object>map = new HashMap<>();
		
		User user = new User();
		user.setLogicalId(form.getUserLogicalId());
		user = userService.findUserByLogicalId(user);
		
		if (user == null) {
			map.put("status", "error");
			map.put("message", "ユーザーが存在しません");
			return map;
		}
		
		// いいねの対象となっているコメントが存在するかどうかを確認
		LikeComment parentComment = new LikeComment(); 
		parentComment.setId(form.getCommentId());
		parentComment = likeCommentService.load(parentComment);
		
		// ロード結果、commentDeletedがtrueならreturn
		
		if (parentComment == null || parentComment.isCommentDeleted()) {
			map.put("status", "error");
			map.put("message", "そのコメントは存在していません");
			return map;
		}
		
		
		LikeComment likeComment = 
				likeCommentService.findLikeByUserIdAndCommentId(user.getId(), form.getCommentId());

		if (likeComment == null) {
			likeComment = new LikeComment();
			likeComment.setUserId(user.getId());
			likeComment.setParentCommentId(form.getCommentId());
			
			likeComment = likeCommentService.insertLikeToLikeComment(likeComment);
			
			map.put("status", "success");
			map.put("message", "いいねを登録しました");
			return map;
		}
		
		likeCommentService.updateLike(likeComment);
		
		if (likeComment.isLike()) {
			likeCommentService.updateLikeCountToComment(form.getCommentId(), 1);
			map.put("message", "いいねを削除しました");
		}else {
			likeCommentService.updateLikeCountToComment(form.getCommentId(),0);
			map.put("message", "いいねを登録しました");
		}
		
		map.put("status", "success");
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
