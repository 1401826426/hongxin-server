package com.taotao.manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.taotao.common.util.IdGenerator;
import com.taotao.common.util.Md5Util;
import com.taotao.manage.mapper.UserMapper;
import com.taotao.manage.pojo.User;
import com.taotao.manage.pojo.UserExample;
import com.taotao.manage.pojo.UserExample.Criteria;
import com.taotao.manage.service.api.ILoginService;

@Service
public class LoginService implements ILoginService{
	
	@Autowired UserMapper userMapper ; 
	
	@Override
	public User loinUser(String name , String password){
		String md5Password = Md5Util.MD5(password) ;
		UserExample example = new UserExample(); 
		Criteria criteria = example.createCriteria() ; 
		criteria.andNameEqualTo(name).andPasswordEqualTo(md5Password) ; 
		List<User> users = userMapper.selectByExample(example) ;
		if(users == null || users.isEmpty()){
			return null;
		}
		return users.get(0) ; 
	}
	
	@Override
	public ResponseEntity<User> registerUser(User user){
		if(user == null || StringUtils.isEmpty(user.getName()) || StringUtils.isEmpty(user.getMail())){
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST) ;
		}
		UserExample example = new UserExample(); 
		Criteria criteria = example.createCriteria() ; 
		criteria.andNameEqualTo(user.getName())  ; 
		List<User> users = userMapper.selectByExample(example) ;
		if(users != null && !users.isEmpty()){
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST) ; 
		}
		String id = IdGenerator.generateStringId() ; 
		user.setId(id);
		user.setRole(0);
		String md5Password = Md5Util.MD5(user.getPassword()) ;
		user.setPassword(md5Password);
		userMapper.insert(user) ; 
		return new ResponseEntity<User>(user,HttpStatus.OK) ; 
	}
	
}



















