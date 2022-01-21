package com.example.domain;

import lombok.Data;

@Data
public class Restaurant {
	private Integer id;
	private String name;
	private String address;
	private String genreFk;
	private String genreValue;
	private double star;
	private Integer type;
	private String photoPath;
	private String hotpepperId;
	private String description;
	private String access;
	private String latitude;
	private String longitude;
	private String url;
	private String smoking;
	private String updatedTime;
	private String postedTime;
	private boolean deleted;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public String getGenreValue() {
		return genreValue;
	}

	public void setGenreValue(String genreValue) {
		this.genreValue = genreValue;
	}

	public double getStar() {
		return star;
	}

	public void setStar(double star) {
		this.star = star;
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

	public String getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getPostedTime() {
		return postedTime;
	}

	public void setPostedTime(String postedTime) {
		this.postedTime = postedTime;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "Restaurant [id=" + id + ", name=" + name + ", address=" + address + ", genreFk=" + genreFk
				+ ", genreValue=" + genreValue + ", star=" + star + ", type=" + type + ", photoPath=" + photoPath
				+ ", hotpepperId=" + hotpepperId + ", description=" + description + ", access=" + access + ", latitude="
				+ latitude + ", longitude=" + longitude + ", url=" + url + ", smoking=" + smoking + ", updatedTime="
				+ updatedTime + ", postedTime=" + postedTime + ", deleted=" + deleted + "]";
	}

}
