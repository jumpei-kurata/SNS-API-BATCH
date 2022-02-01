package com.example.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Notification {

	private Integer id;
	private Integer userId;
	private String accountName;
	private String userPhotoPath;
	private boolean isLike;
	private String comment;

	private Integer timelineId;
	private String timelineSentence;

	private Integer reviewId;
	private String reviewSentence;

	private Integer parentCommentId;
	private String parentCommentSentence;

	private LocalDateTime actionedTime;
	private boolean hasNoticed;
	private boolean isRead;

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

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getUserPhotoPath() {
		return userPhotoPath;
	}

	public void setUserPhotoPath(String userPhotoPath) {
		this.userPhotoPath = userPhotoPath;
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

	public Integer getTimelineId() {
		return timelineId;
	}

	public void setTimelineId(Integer timelineId) {
		this.timelineId = timelineId;
	}

	public String getTimelineSentence() {
		return timelineSentence;
	}

	public void setTimelineSentence(String timelineSentence) {
		this.timelineSentence = timelineSentence;
	}

	public Integer getReviewId() {
		return reviewId;
	}

	public void setReviewId(Integer reviewId) {
		this.reviewId = reviewId;
	}

	public String getReviewSentence() {
		return reviewSentence;
	}

	public void setReviewSentence(String reviewSentence) {
		this.reviewSentence = reviewSentence;
	}

	public Integer getParentCommentId() {
		return parentCommentId;
	}

	public void setParentCommentId(Integer parentCommentId) {
		this.parentCommentId = parentCommentId;
	}

	public String getParentCommentSentence() {
		return parentCommentSentence;
	}

	public void setParentCommentSentence(String parentCommentSentence) {
		this.parentCommentSentence = parentCommentSentence;
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

	@Override
	public String toString() {
		return "Notification [id=" + id + ", userId=" + userId + ", accountName=" + accountName + ", userPhotoPath="
				+ userPhotoPath + ", isLike=" + isLike + ", comment=" + comment + ", timelineId=" + timelineId
				+ ", timelineSentence=" + timelineSentence + ", reviewId=" + reviewId + ", reviewSentence="
				+ reviewSentence + ", parentCommentId=" + parentCommentId + ", parentCommentSentence="
				+ parentCommentSentence + ", actionedTime=" + actionedTime + ", hasNoticed=" + hasNoticed + ", isRead="
				+ isRead + "]";
	}

}
