package com.example.domain;

import java.time.LocalDateTime;

public class Mail {
	
	private Integer id;
	private String email;
	private String name;
	private String token;
	private Integer status;
	private LocalDateTime registeredTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public LocalDateTime getRegisteredTime() {
		return registeredTime;
	}
	public void setRegisteredTime(LocalDateTime registeredTime) {
		this.registeredTime = registeredTime;
	}
	@Override
	public String toString() {
		return "Mail [id=" + id + ", email=" + email + ", name=" + name + ", token=" + token + ", status=" + status
				+ ", registeredTime=" + registeredTime + "]";
	}

	
}
