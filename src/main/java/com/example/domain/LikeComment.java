package com.example.domain;

import java.time.LocalDate;

public class LikeComment {

	private Integer id;
	private Integer userId;
	private boolean isLike;
	private String comment;
	private Integer commentLikeCount;
	private LocalDate actionedTime;
	private boolean hasNoticed;
	private boolean isRead;
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
	public LocalDate getActionedTime() {
		return actionedTime;
	}
	public void setActionedTime(LocalDate actionedTime) {
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
	public boolean isCommentDeleted() {
		return commentDeleted;
	}
	public void setCommentDeleted(boolean commentDeleted) {
		this.commentDeleted = commentDeleted;
	}
	@Override
	public String toString() {
		return "LikeComment [id=" + id + ", userId=" + userId + ", isLike=" + isLike + ", comment=" + comment
				+ ", commentLikeCount=" + commentLikeCount + ", actionedTime=" + actionedTime + ", hasNoticed="
				+ hasNoticed + ", isRead=" + isRead + ", commentDeleted=" + commentDeleted + "]";
	}
	
	
}
