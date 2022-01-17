package com.example.domain;
import java.sql.Date;
import java.time.LocalDateTime;

/**
 * ユーザーのクラス
 * 
 * @author ootomokenji
 *
 */
public class User {
	private Integer id;
	private String name;
	private String accountName;
	private String email;
	private String password;
	private Date hireDate;
	private Integer serviceFk;
	private String serviceName;
	private String userPhotoPath;
	private Date birthDay;
	private String introduction;
	private LocalDateTime loginedTime;
	private LocalDateTime updatedTime;
	private LocalDateTime registeredTime;
	private Boolean deleted;
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
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getHireDate() {
		return hireDate;
	}
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
	public Integer getServiceFk() {
		return serviceFk;
	}
	public void setServiceFk(Integer serviceFk) {
		this.serviceFk = serviceFk;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getUserPhotoPath() {
		return userPhotoPath;
	}
	public void setUserPhotoPath(String userPhotoPath) {
		this.userPhotoPath = userPhotoPath;
	}
	public Date getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public LocalDateTime getLoginedTime() {
		return loginedTime;
	}
	public void setLoginedTime(LocalDateTime loginedTime) {
		this.loginedTime = loginedTime;
	}
	public LocalDateTime getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(LocalDateTime updatedTime) {
		this.updatedTime = updatedTime;
	}
	public LocalDateTime getRegisteredTime() {
		return registeredTime;
	}
	public void setRegisteredTime(LocalDateTime registeredTime) {
		this.registeredTime = registeredTime;
	}
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", accountName=" + accountName + ", email=" + email + ", password="
				+ password + ", hireDate=" + hireDate + ", serviceFk=" + serviceFk + ", serviceName=" + serviceName
				+ ", userPhotoPath=" + userPhotoPath + ", birthDay=" + birthDay + ", introduction=" + introduction
				+ ", loginedTime=" + loginedTime + ", updatedTime=" + updatedTime + ", registeredTime=" + registeredTime
				+ ", deleted=" + deleted + "]";
	}
}