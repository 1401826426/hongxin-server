package com.taotao.manage.pojo;

public class User {

	//
	private int id;

	//
	private String name;

	//
	private String password;

	//
	private int role;

	//
	private String wechat;

	//
	private String tel;

	public void setId(int id){
	    this.id = id;
	}

	public int getId(){
	    return id;
	}

	public void setName(String name){
	    this.name = name;
	}

	public String getName(){
	    return name;
	}

	public void setPassword(String password){
	    this.password = password;
	}

	public String getPassword(){
	    return password;
	}

	public void setRole(int role){
	    this.role = role;
	}

	public int getRole(){
	    return role;
	}

	public void setWechat(String wechat){
	    this.wechat = wechat;
	}

	public String getWechat(){
	    return wechat;
	}

	public void setTel(String tel){
	    this.tel = tel;
	}

	public String getTel(){
	    return tel;
	}

}