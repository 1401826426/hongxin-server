package com.taotao.manage.controller;

import javax.servlet.http.HttpServletRequest;

import com.taotao.manage.manage.Session;

public class BaseController {
	
	public Session getSession(HttpServletRequest request){
		Object  object = request.getAttribute("session") ;
		if(object == null){
			return null ; 
		}
		return (Session)object ; 
	}
	
	
}
