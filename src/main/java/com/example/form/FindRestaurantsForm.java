package com.example.form;

/**
 * レストラン並び替え用のステータスを受け取るクラス
 * @author manami
 *
 */
public class FindRestaurantsForm {
	/** 並び替え */
	private String changeOrder;
	/** ジャンル */
	private String genre;
	/** タイプ */
	private Integer type;
	
	public String getChangeOrder() {
		return changeOrder;
	}
	public void setChangeOrder(String changeOrder) {
		this.changeOrder = changeOrder;
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
