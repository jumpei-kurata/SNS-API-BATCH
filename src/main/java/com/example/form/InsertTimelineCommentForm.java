package com.example.form;

public class InsertTimelineCommentForm {

	private String userLogicalId;
	private String sentence;
	private Integer timelineId;
	
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
	
	public Integer getTimelineId() {
		return timelineId;
	}
	
	public void setTimelineId(Integer timelineId) {
		this.timelineId = timelineId;
	}
	
	@Override
	public String toString() {
		return "InsertTimelineCommentForm [userLogicalId=" + userLogicalId + ", sentence=" + sentence + ", timelineId=" + timelineId
				+ "]";
	}
}
