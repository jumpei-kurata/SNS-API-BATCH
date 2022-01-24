package com.example.domain;

import java.time.LocalDateTime;

/**
 * いいねやコメントを表すドメインです。<br>
 * @author cyjoh
 *
 */
public class LikeComment {

	private Integer id;
	private Integer userId;
	private Integer timelineId;
	private Integer reviewId;
	private Integer parentCommentId;
	private boolean isLike;
	private String comment;
	private Integer commentLikeCount;
	private LocalDateTime actionedTime;
	private boolean hasNoticed;
	private boolean isRead;
	private boolean isMyLike;	
	private boolean commentDeleted;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getTimelineId() {
		return timelineId;
	}

	public void setTimelineId(Integer timelineId) {
		this.timelineId = timelineId;
	}

	public Integer getReviewId() {
		return reviewId;
	}

	public void setReviewId(Integer reviewId) {
		this.reviewId = reviewId;
	}

	public Integer getParentCommentId() {
		return parentCommentId;
	}

	public void setParentCommentId(Integer parentCommentId) {
		this.parentCommentId = parentCommentId;
	}

	public boolean isLike() {
		return isLike;
	}

	public void setLike(boolean isLike) {
		this.isLike = isLike;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getCommentLikeCount() {
		return commentLikeCount;
	}

	public void setCommentLikeCount(Integer commentLikeCount) {
		this.commentLikeCount = commentLikeCount;
	}

	public LocalDateTime getActionedTime() {
		return actionedTime;
	}

	public void setActionedTime(LocalDateTime actionedTime) {
		this.actionedTime = actionedTime;
	}

	public boolean isHasNoticed() {
		return hasNoticed;
	}

	public void setHasNoticed(boolean hasNoticed) {
		this.hasNoticed = hasNoticed;
	}

	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

	public boolean isMyLike() {
		return isMyLike;
	}

	public void setMyLike(boolean isMyLike) {
		this.isMyLike = isMyLike;
	}

	public boolean isCommentDeleted() {
		return commentDeleted;
	}

	public void setCommentDeleted(boolean commentDeleted) {
		this.commentDeleted = commentDeleted;
	}

	@Override
	public String toString() {
		return "LikeComment [id=" + id + ", userId=" + userId + ", timelineId=" + timelineId + ", reviewId=" + reviewId
				+ ", parentCommentId=" + parentCommentId + ", isLike=" + isLike + ", comment=" + comment
				+ ", commentLikeCount=" + commentLikeCount + ", actionedTime=" + actionedTime + ", hasNoticed="
				+ hasNoticed + ", isRead=" + isRead + ", isMyLike=" + isMyLike + ", commentDeleted=" + commentDeleted
				+ "]";
	}

}
