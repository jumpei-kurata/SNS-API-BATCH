package com.example.form;

import java.sql.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CreateUserForm {

	@NotBlank(message = "名前が入力されていません")
	private String name;
	@NotBlank(message = "アカウント名が入力されていません")
	private String accountName;
	@Pattern(regexp = "^[a-zA-Z0-9_.+-]+@rakus.co.jp$|^[a-zA-Z0-9_.+-]+@rakus-partners.co.jp$",message = "使用できないメールアドレスです")
	private String email;
	@Size(min = 8,max = 16,message = "パスワードは8文字以上16文字以内で入力してください")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]+$", message = "アルファベット（大文字小文字混在）と数字とを組み合わせて入力してください")
	private String password;
	@NotNull(message = "入社月が入力されていません")
	private Date hireDate;
	@NotNull(message = "職種が選択されていません")
	private Integer serviceFk;
	@NotBlank(message = "写真が選択されていません")
	private String userPhotoPath;
	@NotNull(message = "誕生日が入力されていません")
	private Date birthDay;
	
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
	
	@Override
	public String toString() {
		return "CreateUserForm [name=" + name + ", accountName=" + accountName + ", email=" + email + ", password="
				+ password + ", hireDate=" + hireDate + ", serviceFk=" + serviceFk + ", userPhotoPath=" + userPhotoPath
				+ ", birthDay=" + birthDay + "]";
	}

}