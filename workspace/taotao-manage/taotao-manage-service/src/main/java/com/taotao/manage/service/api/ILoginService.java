package com.taotao.manage.service.api;

import org.springframework.http.ResponseEntity;

import com.taotao.manage.pojo.User;

public interface ILoginService {
	
	public User loinUser(String name , String password) ; 
	
	public ResponseEntity<User> registerUser(User user) ; 
	
}
