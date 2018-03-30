package com.taotao.manage.bean;

import com.taotao.manage.pojo.User;

public class UserDto {
	
	private String name ; 
	
	private String mail ; 
	
	private String id ; 
	
	private Integer role ;

	private String sessionId ; 
	
	public UserDto(String name, String mail, String id, int role) {
		super();
		this.name = name;
		this.mail = mail;
		this.id = id;
		this.role = role;
	}
	
	public UserDto(User user){
		this.name = user.getName() ; 
		this.id = user.getId() ; 
		this.mail = user.getTel() ; 
		this.role = user.getRole() ; 
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	
	
	
}
