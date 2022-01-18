package com.example.domain;

import java.sql.Date;

public class Timeline {

	private Integer id;
	private Integer userId;
	private String userName;
	private String userPhotoPath;
	private String sentence;
	private Integer likeCount;
	private Integer commentCount;
	private Date updatedTime;
	private Date postedTime;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	public Date getPostedTime() {
		return postedTime;
	}
	public void setPostedTime(Date postedTime) {
		this.postedTime = postedTime;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	@Override
	public String toString() {
		return "Timeline [id=" + id + ", userId=" + userId + ", userName=" + userName + ", userPhotoPath="
				+ userPhotoPath + ", sentence=" + sentence + ", likeCount=" + likeCount + ", commentCount="
				+ commentCount + ", updatedTime=" + updatedTime + ", postedTime=" + postedTime + ", deleted=" + deleted
				+ "]";
	}
	
}
