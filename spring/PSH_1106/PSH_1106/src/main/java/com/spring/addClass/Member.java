package com.spring.addClass;

public class Member {
	//멤버변수지롱
	private String name;
	private String id;
	private String pw;
	private String email;
	
	//getter and setter - 값을 받아서 설정해줘
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
