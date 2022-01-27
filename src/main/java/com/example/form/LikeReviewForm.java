package com.example.form;

public class LikeReviewForm {

	private String userLogicalId;
	private Integer reviewId;
	
	public String getUserLogicalId() {
		return userLogicalId;
	}
	public void setUserLogicalId(String userLogicalId) {
		this.userLogicalId = userLogicalId;
	}
	public Integer getReviewId() {
		return reviewId;
	}
	public void setReviewId(Integer reviewId) {
		this.reviewId = reviewId;
	}
	@Override
	public String toString() {
		return "LikeForm [userLogicalId=" + userLogicalId + ", reviewId=" + reviewId + "]";
	}
	
	
}
