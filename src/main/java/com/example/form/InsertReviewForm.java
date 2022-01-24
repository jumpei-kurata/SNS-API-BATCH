package com.example.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * レビュー投稿用のフォームです。<br>
 *
 * @author cyjoh
 *
 */
public class InsertReviewForm {

	private String userLogicalId;

	@Min(value = 1, message = "レストランを選択してください")
	private Integer restaurantId;

	@Min(value = 1, message = "評価は1以上で選択してください。")
	private Integer star;

	@Size(min = 1, message = "1文字以上で入力してください")
	@Size(max = 140, message = "レビューは140字以内にしてください")
	private String sentence;

	public String getUserLogicalId() {
		return userLogicalId;
	}

	public void setUserLogicalId(String userLogicalId) {
		this.userLogicalId = userLogicalId;
	}

	public Integer getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}

	public Integer getStar() {
		return star;
	}

	public void setStar(Integer star) {
		this.star = star;
	}

	public String getSentence() {
		return sentence;
	}

	public void setSentence(String sentence) {
		this.sentence = sentence;
	}

	@Override
	public String toString() {
		return "InsertReviewForm [userLogicalId=" + userLogicalId + ", restaurantId=" + restaurantId + ", star=" + star
				+ ", sentence=" + sentence + "]";
	}

}
