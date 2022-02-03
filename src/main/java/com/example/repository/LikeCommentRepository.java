package com.example.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.domain.LikeComment;
import com.example.domain.Notification;
import com.example.domain.Review;
import com.example.domain.Timeline;
import com.example.domain.User;
/**
 * いいねコメントテーブルのRepository
 * 
 * @author ootomokenji
 *
 */
@Mapper
public interface LikeCommentRepository {

// 汎用
	// 純粋なロード。
	LikeComment load(LikeComment likeComment);

	//　いいねコメントテーブルにいいねを登録
	void insertLike(LikeComment likeComment);
	
	//　いいねコメントテーブルにコメントを登録
	void insertComment(LikeComment likeComment);
	
	//　いいねを更新します
	void updateLike(LikeComment likeComment);
	
	//コメントを更新します
	void updateComment(LikeComment likeComment);
	
	void updateDelete(LikeComment likeComment);

// タイムライン周り
	//　タイムラインに紐づくコメントリストを検索
	List<LikeComment> findCommentListByTimelineId(Timeline timeline);

	//　userId で SELECT
	LikeComment findLikeCommentByUserIdAndTimelineId(LikeComment likeComment);
	
	//　userIdとtimelineIdで、いいね有無を検索
	LikeComment findLikeToTLByUserIdAndTimeLineId(LikeComment likeComment);
	
// レビュー周り	
	//　タイムラインに紐づくコメントリストを検索
	List<LikeComment> findCommentListByReviewId(Review review);
	
	//　userIdとreviewIdで、いいね有無を検索
	LikeComment findLikeToReviewByUserIdAndReviewId(LikeComment likeComment);
	
// コメントへのいいね周り	
	//　コメントに対するいいねを検索
	LikeComment findLikeCommentByUserIdAndParentCommentId(LikeComment likeComment);
		
	// コメントのいいねカウントを更新する
	void updateLikeCount(@Param("id") Integer id, @Param("status") Integer status);

// 通知周り
	// 通知を表示する
	List<Notification> findNotificationByUserId (User user);
	
	// 通知を表示する
	void updateHasNoticed (User user);
	
// ユーザー周り
	// 特定のユーザーがいいねしたコメントリストの取得
	List<LikeComment> findByLikeUserId(@Param("requestedUserId") Integer requestedUserId ,@Param("visitingUserId") Integer visitingUserId );
	
}