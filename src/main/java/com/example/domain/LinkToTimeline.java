package com.example.domain;

public class LinkToTimeline {

	private Integer id;
	private Integer timelineId;
	private Integer likeCommentId;
	private Integer userId;
	
	public LinkToTimeline(Integer timelineId, Integer likeCommentId, Integer userId) {
		super();
		this.timelineId = timelineId;
		this.likeCommentId = likeCommentId;
		this.userId = userId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTimelineId() {
		return timelineId;
	}

	public void setTimelineId(Integer timelineId) {
		this.timelineId = timelineId;
	}

	public Integer getLikeCommentId() {
		return likeCommentId;
	}

	public void setLikeCommentId(Integer likeCommentId) {
		this.likeCommentId = likeCommentId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "LinkToTimeline [id=" + id + ", timelineId=" + timelineId + ", likeCommentId=" + likeCommentId
				+ ", userId=" + userId + "]";
	}
	
	
}
