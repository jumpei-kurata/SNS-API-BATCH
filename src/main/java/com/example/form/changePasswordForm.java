package com.example.form;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class changePasswordForm {

	private String email;
	@Size(min = 8,max = 16,message = "パスワードは8文字以上16文字以内で入力してください")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]+$", message = "アルファベット（大文字小文字混在）と数字とを組み合わせて入力してください")
	private String password;

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

	@Override
	public String toString() {
		return "changePasswordForm [email=" + email + ", password=" + password + "]";
	}
	
}
