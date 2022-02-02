package com.example.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.LikeComment;
import com.example.domain.Review;
import com.example.domain.Timeline;
import com.example.domain.User;
import com.example.service.LikeCommentService;
import com.example.service.ReviewService;
import com.example.service.TimelineService;
import com.example.service.UserService;

@RestController
public class CommentController {

	@Autowired
	private UserService userService;

	@Autowired
	private TimelineService timelineService;

	@Autowired
	private ReviewService reviewService; 
	
	@Autowired
	private LikeCommentService likeCommentService; 

	/**
	 * コメントIDにあわせ、そのコメントが紐づくタイムライン、またはレビューの詳細情報を返します。
	 * 
	 * @param id
	 * @param userLogicalId
	 * @return
	 */
	@GetMapping(value= "/comment/{id}/{userLogicalId}")
	public Map<String,Object> showCommentDetail(@PathVariable Integer id, @PathVariable String userLogicalId){
		Map<String, Object> map = new HashMap<>();
		
		// userを論理IDで照合
		User user = new User();
		user.setLogicalId(userLogicalId);
		user = userService.findUserByLogicalId(user);

		// userLogicalIdが不正だった場合は、ここで弾かれる
		if (user == null) {
			map.put("status", "error");
			map.put("message", "ユーザーが存在しません");
			return map;
		}
		
		// 渡されたコメントIDが、どの投稿に紐づくのかを検索
		LikeComment likeComment = new LikeComment();
		likeComment.setId(id);
		likeComment = likeCommentService.load(likeComment);
		
		// コメントが削除されていた場合はエラーを返す
		if(likeComment.isCommentDeleted()) {
			map.put("status", "error");
			map.put("message", "そのコメントは存在しません");
			return map;
		}
		
		// いずれにせよ使うのでcommentListをインスタンス化
		List<LikeComment> commentList = new ArrayList<>();
		
		// つぶやきに紐づくのであれば、timeline詳細を返す 
		// (if文内は、タイムラインの詳細表示のメソッドと処理は同じ)
		if(likeComment.getTimelineId() != null ) {
			
			// タイムラインをインスタンス化、timelineIdとUserIdをセット
			Timeline timeline = new Timeline();
			timeline.setId(likeComment.getTimelineId());
			timeline.setUserId(user.getId());
			
			// timelineをロードしてくる
			timeline = timelineService.findTimelineById(timeline);
			
			if (timeline == null) {
				map.put("status", "error");
				map.put("message", "該当するつぶやきが存在しません");
				return map;
			}
			
			// それに紐づくコメント一覧もロード
			commentList = likeCommentService.findCommentListToTimeline(timeline);
			
			// マップを返す
			map.put("status", "success");
			map.put("message", "このコメントがあるタイムライン詳細の検索に成功しました");
			map.put("timeline", timeline);
			map.put("commentList", commentList);
			return map ;
			
		}

		// レビューに紐づくのであれば、review詳細を返す
		// (if文内は、レビューの詳細表示のメソッドと処理は同じ)
		if(likeComment.getReviewId() != null ) {
			
			// タイムラインをインスタンス化、reviewIdとUserIdをセット
			Review review = new Review();
			review.setId(likeComment.getReviewId());
			review.setUserId(user.getId());
			
			// reviewをロードしてくる
			review = reviewService.findById(review);
			
			if (review == null) {
				map.put("status", "error");
				map.put("message", "そのレビューは存在しません");
				return map;
			}
			
			// それに紐づくコメント一覧もロード
			commentList = likeCommentService.findCommentListToReview(review);
			
			// マップを返す
			map.put("status", "success");
			map.put("message", "このコメントがあるレビュー詳細の検索に成功しました");
			map.put("review", review);
			map.put("commentList", commentList);
			return map ;
			
		}
		
		// タイムラインにもレビューにも紐付いていないので、エラーとして返す
		map.put("status", "error");
		map.put("message", "該当する投稿が見つかりませんでした");
		return map;
	}
}
