package com.example.form;

public class LikeTimelineForm {

	private String userLogicalId;
	private Integer timelineId;
	
	public String getUserLogicalId() {
		return userLogicalId;
	}
	public void setUserLogicalId(String userLogicalId) {
		this.userLogicalId = userLogicalId;
	}
	public Integer getTimelineId() {
		return timelineId;
	}
	public void setTimelineId(Integer timelineId) {
		this.timelineId = timelineId;
	}
	@Override
	public String toString() {
		return "LikeForm [userLogicalId=" + userLogicalId + ", timelineId=" + timelineId + "]";
	}
	
	
}
