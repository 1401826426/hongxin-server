package com.taotao.manage.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PropertieService {
	
    @Value("${REPOSITORY_PATH}")
    private String REPOSITORY_PATH;
    
    @Value("${IMAGE_BASE_URL}")
    private String IMAGE_BASE_URL;
    
    @Value("${WEB_URL}")
    private String WEB_URL;
    
    @Value("${IMAGE_ALLOW}")
    private String IMAGE_ALLOW ;    
    
    @Value("${SELL_MAIL_SUBJECT}")
    private String SELL_MAIL_SUBJECT ; 
    
    @Value("${SELL_MAIL_CONTENT}")
    private String SELL_MAIL_CONTENT ; 
    
    @Value("${BUY_MAIL_CONTENT}")
    private String BUY_MAIL_CONTENT ; 
    
    @Value("${BUY_MAIL_SUBJECT}")
    private String BUY_MAIL_SUBJECT ; 
    
    
    private Set<String> imageAllow ; 
    
    public Set<String> getImageAllow(){
    	if(imageAllow != null){
    		return imageAllow ; 
    	}
    	String[] s = IMAGE_ALLOW.split(",") ; 
    	List<String>  list = Arrays.asList(s) ;      	
    	imageAllow = new HashSet<String>() ; 
    	imageAllow.addAll(list) ; 
    	return imageAllow ; 
    }

	public String getREPOSITORY_PATH() {
		return REPOSITORY_PATH;
	}

	public String getIMAGE_BASE_URL() {
		return IMAGE_BASE_URL;
	}

	public String getWEB_URL() {
		return WEB_URL;
	}

	public String getIMAGE_ALLOW() {
		return IMAGE_ALLOW;
	}

	public String getSELL_MAIL_SUBJECT() {
		return SELL_MAIL_SUBJECT;
	}

	public void setSELL_MAIL_SUBJECT(String sELL_MAIL_SUBJECT) {
		SELL_MAIL_SUBJECT = sELL_MAIL_SUBJECT;
	}

	public String getSELL_MAIL_CONTENT() {
		return SELL_MAIL_CONTENT;
	}

	public void setSELL_MAIL_CONTENT(String sELL_MAIL_CONTENT) {
		SELL_MAIL_CONTENT = sELL_MAIL_CONTENT;
	}

	public String getBUY_MAIL_CONTENT() {
		return BUY_MAIL_CONTENT;
	}

	public void setBUY_MAIL_CONTENT(String bUY_MAIL_CONTENT) {
		BUY_MAIL_CONTENT = bUY_MAIL_CONTENT;
	}

	public String getBUY_MAIL_SUBJECT() {
		return BUY_MAIL_SUBJECT;
	}

	public void setBUY_MAIL_SUBJECT(String bUY_MAIL_SUBJECT) {
		BUY_MAIL_SUBJECT = bUY_MAIL_SUBJECT;
	}

	public void setREPOSITORY_PATH(String rEPOSITORY_PATH) {
		REPOSITORY_PATH = rEPOSITORY_PATH;
	}

	public void setIMAGE_BASE_URL(String iMAGE_BASE_URL) {
		IMAGE_BASE_URL = iMAGE_BASE_URL;
	}

	public void setWEB_URL(String wEB_URL) {
		WEB_URL = wEB_URL;
	}

	public void setIMAGE_ALLOW(String iMAGE_ALLOW) {
		IMAGE_ALLOW = iMAGE_ALLOW;
	}

	public void setImageAllow(Set<String> imageAllow) {
		this.imageAllow = imageAllow;
	};
    
	
	
    
    
}












