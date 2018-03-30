package com.taotao.manage.manage;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import com.taotao.manage.bean.UserDto;

public class Session {
	
	private String sessionId ; 
	
	private UserDto userDto;  
	
	private volatile long expireTime ; 
	
	private Map<String,Object> map  ; 
	
	private volatile boolean invalidate ; 
	
	public Session(String sessionId, UserDto userDto) {
		super();
		this.sessionId = sessionId;
		this.userDto = userDto;
		this.expireTime = System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(30) ; 
		map = new ConcurrentHashMap<String,Object>() ; 
	}

	public boolean isInvalidate(){
		if(invalidate){
			return true ; 
		}
		return System.currentTimeMillis() > this.expireTime ; 
	}

	public void destroy() {
		map.clear();  
	}
	
	public synchronized void touch(){
		this.expireTime = System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(30) ;
	}

	public String getSessionId() {
		return sessionId;
	}

	public UserDto getUserDto() {
		return userDto;
	}
	
	public void putAttribute(String name, Object value){
		map.put(name, value) ; 
	}
	
	public Object getAttribute(String name){
		return map.get(name) ; 
	}
	
	public synchronized void setInvalid(){
		this.invalidate = true ; 
	}
	
}



















