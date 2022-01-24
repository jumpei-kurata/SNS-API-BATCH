package com.example.domain;

import java.time.LocalDateTime;

/**
 * レビューを表すドメインです。
 * 
 * @author cyjoh
 *
 */
public class Review {

	private Integer id;
	private Integer userId;
	private String accountName;
	private String userPhotoPath;
	private Integer restaurantId;
	private String restaurantName;
	private String restaurantPhotoPath;
	private Integer star;
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

	public Integer getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getRestaurantPhotoPath() {
		return restaurantPhotoPath;
	}

	public void setRestaurantPhotoPath(String restaurantPhotoPath) {
		this.restaurantPhotoPath = restaurantPhotoPath;
	}

	public Integer getStar() {
		return star;
	}

	public void setStar(Integer star) {
		this.star = star;
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
		return "Review [id=" + id + ", userId=" + userId + ", accountName=" + accountName + ", userPhotoPath="
				+ userPhotoPath + ", restaurantId=" + restaurantId + ", restaurantName=" + restaurantName
				+ ", restaurantPhotoPath=" + restaurantPhotoPath + ", star=" + star + ", sentence=" + sentence
				+ ", likeCount=" + likeCount + ", commentCount=" + commentCount + ", updatedTime=" + updatedTime
				+ ", postedTime=" + postedTime + ", isMyLike=" + isMyLike + ", deleted=" + deleted + "]";
	}

}