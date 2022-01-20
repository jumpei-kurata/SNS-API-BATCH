package com.example.form;

import javax.validation.constraints.Size;

public class InsertTimelineForm {

	private Integer userId;
	@Size(min = 1,message = "1文字以上で入力してください")
	@Size(max = 140,message = "つぶやきは140字以内にしてください")
	private String sentence;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getSentence() {
		return sentence;
	}
	public void setSentence(String sentence) {
		this.sentence = sentence;
	}
	
	@Override
	public String toString() {
		return "InsertTimelineForm [userId=" + userId + ", sentence=" + sentence + "]";
	}
	
}
