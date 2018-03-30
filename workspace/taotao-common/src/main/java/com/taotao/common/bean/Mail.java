package com.taotao.common.bean;

import java.util.ArrayList;
import java.util.List;

public class Mail {
	
	private List<String> to ; 
	
	private String subject ; 
	
	private String content ;

	
	public Mail(String toUser, String subject , String content){
		to = new ArrayList<String>() ; 
		to.add(toUser) ; 
		this.subject = subject ; 
		this.content = content ; 
	}
	
	public List<String> getTo() {
		return to;
	}

	public void setTo(List<String> to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	} 
	
	
	
	
}
