package com.taotao.manage.service.api;

import org.springframework.http.ResponseEntity;

import com.taotao.manage.pojo.User;

public interface ILoginService {
	
	public User doLoinUser(String name , String password) ; 
	
	public ResponseEntity<String> doRegisterUser(User user) ;

	public ResponseEntity<String> doConfirmMail(String userId, String check); 
	
}
