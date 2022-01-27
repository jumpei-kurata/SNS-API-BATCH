package com.example.form;

public class InsertReviewCommentForm {

	private String userLogicalId;
	private String sentence;
	private Integer reviewId;
	
	public String getUserLogicalId() {
		return userLogicalId;
	}
	
	public void setUserLogicalId(String userLogicalId) {
		this.userLogicalId = userLogicalId;
	}
	
	public String getSentence() {
		return sentence;
	}
	
	public void setSentence(String sentence) {
		this.sentence = sentence;
	}
	
	public Integer getReviewId() {
		return reviewId;
	}
	
	public void setReviewId(Integer reviewId) {
		this.reviewId = reviewId;
	}
	
	@Override
	public String toString() {
		return "InsertReviewCommentForm [userLogicalId=" + userLogicalId + ", sentence=" + sentence + ", reviewId=" + reviewId
				+ "]";
	}
}
