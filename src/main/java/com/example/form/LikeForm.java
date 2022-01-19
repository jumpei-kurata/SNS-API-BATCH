package com.example.form;

public class LikeForm {

	private Integer userId;
	private Integer timelineId;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getTimelineId() {
		return timelineId;
	}
	public void setTimelineId(Integer timelineId) {
		this.timelineId = timelineId;
	}
	@Override
	public String toString() {
		return "LikeForm [userId=" + userId + ", timelineId=" + timelineId + "]";
	}
	
	
}
