package com.taotao.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.manage.bean.UserDto;
import com.taotao.manage.manage.SessionManager;
import com.taotao.manage.pojo.User;
import com.taotao.manage.service.api.ILoginService;

@Controller
public class LoginController {
	
	@Autowired
	private ILoginService loginService ; 
	
	@RequestMapping("/login")
	public ResponseEntity<UserDto> login(@RequestParam("name") String name , 
			@RequestParam("password") String password){
		User user = loginService.doLoinUser(name, password) ; 
		if(user == null || user.getState() == 0){ 
			return new ResponseEntity<UserDto>(HttpStatus.BAD_REQUEST) ; 
		}
		UserDto userDto = new UserDto(user) ; 
		String sessionId = SessionManager.getInstance().addUser(userDto) ;
		userDto.setSessionId(sessionId);
		return new ResponseEntity<UserDto>(userDto , HttpStatus.OK) ; 
	}
	
	@RequestMapping("/register") 
	public ResponseEntity<String> register(User user){
		return loginService.doRegisterUser(user) ; 
	}
	
	@RequestMapping("/confirmMail/{userId}/{check}")
	public ResponseEntity<String> confirmMail(@PathVariable("userId") String userId,@PathVariable("check") String check){
		return loginService.doConfirmMail(userId,check) ; 
		
	}
	
	
}























