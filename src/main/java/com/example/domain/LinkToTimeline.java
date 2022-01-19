package com.example.domain;

public class LinkToTimeline {

	private Integer id;
	private Integer timelineId;
	private Integer likeCommentId;
	
	public LinkToTimeline(Integer timelineId, Integer likeCommentId) {
		super();
		this.timelineId = timelineId;
		this.likeCommentId = likeCommentId;
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

	@Override
	public String toString() {
		return "LinkToTimeline [id=" + id + ", timelineId=" + timelineId + ", likeCommentId=" + likeCommentId + "]";
	}
	
	
}
