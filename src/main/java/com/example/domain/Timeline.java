package com.example.domain;

import java.time.LocalDateTime;

public class Timeline {

	private Integer id;
	private Integer userId;
	private String accountName;
	private String userPhotoPath;
	private String sentence;
	private Integer likeCount;
	private Integer commentCount;
	private LocalDateTime updatedTime;
	private LocalDateTime postedTime;
	private boolean isMyLike;
	private boolean deleted;
	
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
	public String getSentence() {
		return sentence;
	}
	public void setSentence(String sentence) {
		this.sentence = sentence;
	}
	public Integer getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}
	public Integer getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}
	public LocalDateTime getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(LocalDateTime updatedTime) {
		this.updatedTime = updatedTime;
	}
	public LocalDateTime getPostedTime() {
		return postedTime;
	}
	public void setPostedTime(LocalDateTime postedTime) {
		this.postedTime = postedTime;
	}
	public boolean isMyLike() {
		return isMyLike;
	}
	public void setMyLike(boolean isMyLike) {
		this.isMyLike = isMyLike;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	@Override
	public String toString() {
		return "Timeline [id=" + id + ", userId=" + userId + ", accountName=" + accountName + ", userPhotoPath="
				+ userPhotoPath + ", sentence=" + sentence + ", likeCount=" + likeCount + ", commentCount="
				+ commentCount + ", updatedTime=" + updatedTime + ", postedTime=" + postedTime + ", isMyLike="
				+ isMyLike + ", deleted=" + deleted + "]";
	}
	
}
