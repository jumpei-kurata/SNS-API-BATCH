package com.example.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class ConfirmMailForm {
	
	@Size(min=2,max=31)
	private String name;
	
	@Email
	private String email;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "ConfirmMailForm [name=" + name + ", email=" + email + "]";
	}
	
}
