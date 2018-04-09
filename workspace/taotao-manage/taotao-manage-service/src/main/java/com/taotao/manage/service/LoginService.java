package com.taotao.manage.service;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.taotao.common.bean.Mail;
import com.taotao.common.util.IdGenerator;
import com.taotao.common.util.Md5Util;
import com.taotao.manage.mapper.UserMapper;
import com.taotao.manage.pojo.User;
import com.taotao.manage.pojo.UserExample;
import com.taotao.manage.pojo.UserExample.Criteria;
import com.taotao.manage.service.api.ILoginService;
import com.taotao.manage.service.util.SendMailUtil;

@Service
public class LoginService implements ILoginService{
	
	@Autowired UserMapper userMapper ; 
	
	@Autowired
	private SendMailUtil  sendMailUtil ; 
	
	@Autowired
	private PropertieService propertiesService ; 
	
	public static final String REGEX_EMAIL = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
	
	
	@Override
	public User doLoinUser(String name , String password){
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
	public ResponseEntity<String> doRegisterUser(User user){
		if(user == null || StringUtils.isEmpty(user.getName()) || StringUtils.isEmpty(user.getMail())){
			return new ResponseEntity<String>("用户名或邮箱为空" , HttpStatus.BAD_REQUEST) ;
		}
		if(!Pattern.matches(REGEX_EMAIL, user.getMail())){
			return new ResponseEntity<String>("邮箱格式出错" , HttpStatus.BAD_REQUEST) ;
		}
		UserExample example = new UserExample(); 
		Criteria criteria = example.createCriteria() ; 
		criteria.andNameEqualTo(user.getName())  ; 
		List<User> users = userMapper.selectByExample(example) ;
		if(users != null && !users.isEmpty()){
			return new ResponseEntity<String>("已经有"  +user.getName() + "账户" , HttpStatus.BAD_REQUEST) ; 
		}
		String id = IdGenerator.generateStringId() ; 
		String check = IdGenerator.generateStringId() ;
		user.setId(id);
		user.setRole(0);
		String md5Password = Md5Util.MD5(user.getPassword()) ;
		user.setPassword(md5Password);
		user.setState(0);
		user.setCreateTime(new Date());
		user.setCheckNum(check);
		userMapper.insert(user) ; 	
		Mail mail = new Mail(user.getMail() ,"鸿达基团激活账号","请点击下面链接激活账号\n"+propertiesService.getWEB_URL()+"/confirmMail/"+user.getId()+"/"+user.getCheckNum()) ;
		sendMailUtil.sendMailAync(mail) ;
		return new ResponseEntity<String>("",HttpStatus.OK) ; 
	}
	
	
	public User getUser(String userId){
		if(userId == null){
			return null; 
		}
		UserExample example = new UserExample() ; 
		Criteria criteria = example.createCriteria() ;
		criteria.andIdEqualTo(userId) ; 
		List<User> users = userMapper.selectByExample(example) ;
		if(users != null && users.size() > 0){			
			return users.get(0) ; 
		}else{
			return null ; 
		}
	}

	@Override
	public ResponseEntity<String> doConfirmMail(String userId, String check) {
		User user = getUser(userId) ; 
		if(user ==null){
			return new ResponseEntity<String>("no user", HttpStatus.BAD_REQUEST) ;
		}
		if(!user.getCheckNum().equals(check)){
			return new ResponseEntity<String>("checkNum wrong", HttpStatus.BAD_REQUEST) ;
		}
		UserExample example = new UserExample();  
		Criteria criteria = example.createCriteria() ;
		criteria.andIdEqualTo(userId) ; 
		user.setState(1);
		userMapper.updateByExample(user, example);
		return new ResponseEntity<String>("success",HttpStatus.OK) ;
	}
	
}



















