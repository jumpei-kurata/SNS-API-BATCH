package com.example.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class InsertRestaurantByHotpepperForm {
	@NotBlank(message = "名前が入力されていません")
	String name;

	@NotBlank(message = "店舗住所が入力されていません")
	String address;

	@NotBlank(message = "店のジャンルが選択されていません")
	String genreFk;

	@Size(message = "提供タイプが選択されていません")
	Integer type;

	@NotBlank(message = "画像が登録されていません")
	String photoPath;

	@NotBlank(message = "ホットペッパーIDが登録されていません")
	String hotpepperId;

	@NotBlank(message = "説明文が登録されていません")
	String description;

	@NotBlank(message = "アクセス情報が登録されていません")
	String access;

	@NotBlank(message = "緯度情報が登録されていません")
	String latitude;

	@NotBlank(message = "経度情報が登録されていません")
	String longitude;

	@NotBlank(message = "URLが登録されていません")
	String url;

	@NotBlank(message = "喫煙情報が登録されていません")
	String smoking;

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

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public String getHotpepperId() {
		return hotpepperId;
	}

	public void setHotpepperId(String hotpepperId) {
		this.hotpepperId = hotpepperId;
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
		return "InsertRestaurantByHotpepperForm [name=" + name + ", address=" + address + ", genreFk=" + genreFk
				+ ", type=" + type + ", photoPath=" + photoPath + ", hotpepperId=" + hotpepperId + ", description="
				+ description + ", access=" + access + ", latitude=" + latitude + ", longitude=" + longitude + ", url="
				+ url + ", smoking=" + smoking + "]";
	}

}
