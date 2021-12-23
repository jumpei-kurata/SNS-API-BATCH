package com.example.form;

import java.sql.Date;

public class CreateUserForm {

	private String name;
	private String accountName;
	private String email;
	private String password;
	private Date hireDate;
	private Integer serviceFk;
	private Date birthDay;
	private String introduction;
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
		return "CreateUserForm [name=" + name + ", accountName=" + accountName + ", email=" + email + ", password="
				+ password + ", hireDate=" + hireDate + ", serviceFk=" + serviceFk + ", birthDay=" + birthDay
				+ ", introduction=" + introduction + "]";
	}

	
	
}