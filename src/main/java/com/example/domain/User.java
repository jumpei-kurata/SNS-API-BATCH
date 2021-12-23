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

	private Integer userId;
	private String userName;
	private String accountName;
	private String userEmail;
	private String userPassword;
	private Date hireDate;
	private Integer serviceFk;
	private String serviceName;
	private Date birthDay;
	private String introduction;
	private LocalDateTime loginedTime;
	private LocalDateTime updatedTime;
	private LocalDateTime registeredTime;
	private Boolean deleted;
	
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
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
		return "User [userId=" + userId + ", userName=" + userName + ", accountName=" + accountName + ", userEmail="
				+ userEmail + ", userPassword=" + userPassword + ", hireDate=" + hireDate + ", serviceFk=" + serviceFk
				+ ", serviceName=" + serviceName + ", birthDay=" + birthDay + ", introduction=" + introduction
				+ ", loginedTime=" + loginedTime + ", updatedTime=" + updatedTime + ", registeredTime=" + registeredTime
				+ ", deleted=" + deleted + "]";
	}
}
