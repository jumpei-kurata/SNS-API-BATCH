package com.example.form;

/**
 * レストラン並び替え用のステータスを受け取るクラス
 * @author manami
 *
 */
public class FindRestaurantsForm {
	/** 並び替え */
	private String order;
	/** ジャンル */
	private String genre;
	/** タイプ */
	private Integer type;
	
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
}
