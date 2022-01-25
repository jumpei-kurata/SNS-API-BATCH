package com.example.form;

import java.sql.Date;

public class UserEditForm {

	private String userLogicalId;
	private String name;
	private String accountName;
	private Date hireDate;
	private Integer serviceFk;
	private Date birthDay;
	
	private String introduction;
	
	public String getUserLogicalId() {
		return userLogicalId;
	}
	public void setUserLogicalId(String userLogicalId) {
		this.userLogicalId = userLogicalId;
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
	@Override
	public String toString() {
		return "UserEditForm [userLogicalId=" + userLogicalId + ", name=" + name + ", accountName=" + accountName 
				+ ", hireDate=" + hireDate + ", serviceFk=" + serviceFk + ", birthDay="
				+ birthDay + ", introduction=" + introduction + "]";
	}

	
	
}