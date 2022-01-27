package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.LikeComment;
import com.example.domain.Review;
import com.example.domain.Timeline;
import com.example.repository.LikeCommentRepository;
import com.example.repository.LinkToCommentRepository;
import com.example.repository.LinkToReviewRepository;
import com.example.repository.LinkToTimelineRepository;
import com.example.repository.ReviewRepository;
import com.example.repository.TimelineRepository;

@Service
@Transactional
public class LikeCommentService {

	@Autowired
	private TimelineRepository timelineRepository;
	@Autowired
	private LinkToTimelineRepository linkToTimelineRepository;

	@Autowired
	private ReviewRepository reviewRepository;
	@Autowired
	private LinkToReviewRepository linkToReviewRepository;

	@Autowired
	private LikeCommentRepository likeCommentRepository;
	@Autowired
	private LinkToCommentRepository linkToCommentRepository;
	
	
	
// 汎用	
	
	
	/**
	 * サーバー側の処理で使うためのロードメソッド
	 * 
	 * @param likeComment
	 * @return
	 */
	public LikeComment load(LikeComment likeComment) {
		return likeCommentRepository.load(likeComment);
	}

	/**
	 * コメントの削除処理
	 * 
	 * @param likeComment
	 */
	public LikeComment updateDelete(LikeComment likeComment) {
		
		//　もしもうすでにコメント削除されていたら、nullをreturn
		if(likeComment.isCommentDeleted()) {
			return null;
		}
		
		// そうでないならば、削除を実施
		likeComment.setCommentDeleted(true);
		likeCommentRepository.updateDelete(likeComment);
		
		
		if (likeComment.getTimelineId() != null) {
			timelineRepository.updateCommentCount(likeComment.getTimelineId(),1);
		}

		if (likeComment.getReviewId() != null) {
			reviewRepository.updateCommentCount(likeComment.getReviewId(),1);
		}
		
		return likeComment;
	}
	
	/**
	 * いいねの更新処理。渡したis_likeの逆に切り替わる。
	 * 
	 * @param likeComment
	 */
	public void updateLike(LikeComment likeComment) {
		likeCommentRepository.updateLike(likeComment);
	}
	
	/**
	 * コメント内容の更新処理。渡したcommentの内容に切り替わる。
	 * @param likeComment
	 */
	public void updateComment(LikeComment likeComment) {
		likeCommentRepository.updateComment(likeComment);
	}
	
	
// タイムライン周り

	
	/**
	 * いいねコメントテーブルにタイムラインへのいいねを登録
	 * 
	 * @param likeComment
	 * @return
	 */
	public LikeComment insertLikeToTimeline(LikeComment likeComment) {
		
		likeComment.setLike(true);
		likeCommentRepository.insertLike(likeComment);
		
		linkToTimelineRepository.insertLinksToTimeline(likeComment.getTimelineId(),likeComment.getId());
		
		timelineRepository.updateLikeCount(likeComment.getTimelineId(),0);
		
		return likeComment;
	}
	
	/**
	 * いいねコメントテーブルにタイムラインへのコメントを登録
	 * 
	 * @param likeComment
	 * @return
	 */
	public LikeComment insertCommentToTimeline(LikeComment likeComment) {
		
		likeCommentRepository.insertComment(likeComment);
		
		linkToTimelineRepository.insertLinksToTimeline(likeComment.getTimelineId(),likeComment.getId());
		
		timelineRepository.updateCommentCount(likeComment.getTimelineId(),0);
		
		return likeComment;
	}
	
	/**
	 * タイムラインのいいねカウント+-1
	 * 
	 * @param timeline
	 */
	public void updateLikeCountToTimeline(Integer timelineId,Integer status) {
		timelineRepository.updateLikeCount(timelineId,status);
	}
	
	/**
	 * タイムラインのコメントカウント+-1
	 * insert時に合わせて実行しているので、現状このメソッドが呼ばれることはない
	 * 
	 * @param timeline
	 */
	public void updateCommentCountTimeline(Integer timelineId,Integer status) {
		timelineRepository.updateCommentCount(timelineId,status);
	}
	
	public LikeComment findLikeComment(Integer userId,Integer timelineId) {
		LikeComment likeComment = new LikeComment();
		
		likeComment.setUserId(userId);
		likeComment.setTimelineId(timelineId);
		return likeCommentRepository.findLikeCommentByUserIdAndTimelineId(likeComment);
	}

	
	/**
	 * 渡されたUserと渡されたTimelineに該当するいいねがある/あったか検索
	 * 
	 * @param userId
	 * @param timelineId
	 * @return
	 */
	public LikeComment findLikeToTLByUserIdAndTimeLineId(Integer userId,Integer timelineId) {
		LikeComment likeComment = new LikeComment();
		likeComment.setUserId(userId);
		likeComment.setTimelineId(timelineId);
		return likeCommentRepository.findLikeToTLByUserIdAndTimeLineId(likeComment);
	}
	
	/**
	 * 一つのタイムラインに連なる、コメントリストの取得(詳細表示にて使用)
	 * @param timeline
	 * @return
	 */
	public List<LikeComment> findCommentListToTimeline(Timeline timeline) {
		return likeCommentRepository.findCommentListByTimelineId(timeline);
	}
	
	/**
	 * タイムライン接続テーブルに登録(テスト用のメソッドで使用)
	 * 
	 * @param linkToTimeline
	 */
	public void insertLinkToTimeline(Integer timelineId,Integer likeCommentId) {
		linkToTimelineRepository.insertLinksToTimeline(timelineId,likeCommentId);
	}
	
	
// レビュー周り
	
	
	/**
	 * いいねコメントテーブルにタイムラインへのいいねを登録
	 * 
	 * @param likeComment
	 * @return
	 */
	public LikeComment insertLikeToReview(LikeComment likeComment) {
		
		likeComment.setLike(true);
		likeCommentRepository.insertLike(likeComment);
		
		linkToReviewRepository.insertLinksToReview(likeComment.getReviewId(),likeComment.getId());
		
		reviewRepository.updateLikeCount(likeComment.getReviewId(),0);
		
		return likeComment;
	}
	
	/**
	 * いいねコメントテーブルにレビューへのコメントを登録
	 * 
	 * @param likeComment
	 * @return
	 */
	public LikeComment insertCommentToReview(LikeComment likeComment) {
		
		likeCommentRepository.insertComment(likeComment);
		
		linkToReviewRepository.insertLinksToReview(likeComment.getReviewId(),likeComment.getId());
		
		reviewRepository.updateCommentCount(likeComment.getReviewId(),0);
		
		return likeComment;
	}
	
	/**
	 * レビューのいいねカウント+-1
	 * 
	 * @param review
	 */
	public void updateLikeCountToReview(Integer reviewId,Integer status) {
		reviewRepository.updateLikeCount(reviewId,status);
	}
	
	/**
	 * レビューのコメントカウント+-1
	 * insert時に合わせて実行しているので、現状このメソッドが呼ばれることはない
	 * 
	 * @param review
	 */
	public void updateCommentCountReview(Integer reviewId,Integer status) {
		reviewRepository.updateCommentCount(reviewId,status);
	}
	
	
	/**
	 * 渡されたUserと渡されたReviewに該当するいいねがある/あったか検索
	 * 
	 * @param userId
	 * @param reviewId
	 * @return
	 */
	public LikeComment findLikeToReviewByUserIdAndReviewId(Integer userId,Integer reviewId) {
		LikeComment likeComment = new LikeComment();
		likeComment.setUserId(userId);
		likeComment.setReviewId(reviewId);
		return likeCommentRepository.findLikeToReviewByUserIdAndReviewId(likeComment);
	}
	
	
	/**
	 * 一つのレビューに連なる、コメントリストの取得(詳細表示にて使用)
	 * @param review
	 * @return
	 */
	public List<LikeComment> findCommentListToReview(Review	review) {
		return likeCommentRepository.findCommentListByReviewId(review);
	}
	
	
// コメントへのいいね周り
	
	
	/**
	 * 渡されたUserIdによる、渡されたCommentIdの投稿へのいいねがあるかどうかを検索するメソッド
	 * @param userId
	 * @param commentId
	 * @return
	 */
	public LikeComment findLikeByUserIdAndCommentId(Integer userId, Integer commentId){

		LikeComment likeComment = new LikeComment();
		likeComment.setUserId(userId);
		likeComment.setParentCommentId(commentId);
		
		return likeCommentRepository.findLikeCommentByUserIdAndParentCommentId(likeComment);
	}
	
	/**
	 * いいねコメントテーブルにコメントへのいいねを登録
	 * 
	 * @param likeComment
	 * @return
	 */
	public LikeComment insertLikeToLikeComment(LikeComment likeComment) {
		
		likeComment.setLike(true);
		likeCommentRepository.insertLike(likeComment);
		
		linkToCommentRepository.insertLinksToComment(likeComment.getParentCommentId(), likeComment.getId());
		
		likeCommentRepository.updateLikeCount(likeComment.getParentCommentId(), 0);
		
		return likeComment;
	}

	
	/**
	 * コメントのいいねカウント+-1
	 * 
	 * @param timeline
	 */
	public void updateLikeCountToComment(Integer commentId,Integer status) {
		likeCommentRepository.updateLikeCount(commentId,status);
	}
	
	
}
