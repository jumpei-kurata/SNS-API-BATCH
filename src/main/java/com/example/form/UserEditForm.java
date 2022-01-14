package com.example.form;

import java.sql.Date;

import javax.validation.constraints.Size;

public class UserEditForm {

	private Integer id;
	private String name;
	private String accountName;
	@Size(min = 8,max = 16,message = "パスワードは8文字以上16文字以内で入力してください")
	private String password;
	private Date hireDate;
	private Integer serviceFk;
	private Date birthDay;
	
	private String introduction;
	
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
		return "UserEditForm [id=" + id + ", name=" + name + ", accountName=" + accountName 
				+ ", password=" + password + ", hireDate=" + hireDate + ", serviceFk=" + serviceFk + ", birthDay="
				+ birthDay + ", introduction=" + introduction + "]";
	}

	
	
}