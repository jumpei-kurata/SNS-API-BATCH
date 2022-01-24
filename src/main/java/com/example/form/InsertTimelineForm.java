package com.example.form;

import javax.validation.constraints.Size;

public class InsertTimelineForm {

	private String userLogicalId;
	
	@Size(min = 1,message = "1文字以上で入力してください")
	@Size(max = 140,message = "つぶやきは140字以内にしてください")
	private String sentence;
	
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
	
	@Override
	public String toString() {
		return "InsertTimelineForm [userLogicalId=" + userLogicalId + ", sentence=" + sentence + "]";
	}
	
}
