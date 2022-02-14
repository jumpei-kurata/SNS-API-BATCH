package com.example.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * @author cyjoh
 *
 */
@Data
public class InsertRestaurantForm {
	@NotBlank(message = "名前が入力されていません")
	private String name;

	@NotBlank(message = "店舗住所が入力されていません")
	private String address;

	@NotBlank(message = "店のジャンルが選択されていません")
	private String genreFk;

	@Min(value=1,message = "提供タイプが選択されていません")
	private Integer type;

	private String description;
	private String access;
	private String latitude;
	private String longitude;
	private String url;
	private String smoking;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGenreFk() {
		return genreFk;
	}

	public void setGenreFk(String genreFk) {
		this.genreFk = genreFk;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAccess() {
		return access;
	}

	public void setAccess(String access) {
		this.access = access;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSmoking() {
		return smoking;
	}

	public void setSmoking(String smoking) {
		this.smoking = smoking;
	}

	@Override
	public String toString() {
		return "InsertRestaurantForm [name=" + name + ", address=" + address + ", genreFk=" + genreFk + ", type=" + type
				+ ", description=" + description + ", access=" + access + ", latitude=" + latitude + ", longitude="
				+ longitude + ", url=" + url + ", smoking=" + smoking + "]";
	}
}
