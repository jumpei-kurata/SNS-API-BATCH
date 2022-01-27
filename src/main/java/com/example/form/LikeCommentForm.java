package com.example.form;

public class LikeCommentForm {

	private String userLogicalId;
	private Integer commentId;
	
	public String getUserLogicalId() {
		return userLogicalId;
	}
	public void setUserLogicalId(String userLogicalId) {
		this.userLogicalId = userLogicalId;
	}
	public Integer getCommentId() {
		return commentId;
	}
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	@Override
	public String toString() {
		return "LikeForm [userLogicalId=" + userLogicalId + ", commentId=" + commentId + "]";
	}
	
	
}
