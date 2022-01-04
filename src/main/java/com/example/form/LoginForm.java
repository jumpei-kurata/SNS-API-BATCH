package com.example.form;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * ログインのためのフォームです
 * @author ootomokenji
 *
 */
public class LoginForm {

	@Pattern(regexp = "^[a-zA-Z0-9_.+-]+@rakus.co.jp$|^[a-zA-Z0-9_.+-]+@rakus-partners.co.jp$",message = "使用できないメールアドレスです")
	private String email;
	@Size(min = 8,max = 16,message = "パスワードは8文字以上16文字以内で入力してください")
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
		return "LoginForm [email=" + email + ", password=" + password + "]";
	}
	
}
