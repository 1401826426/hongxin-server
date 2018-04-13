package com.taotao.manage.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.util.StringUtils;

public class OrderInfo {
    private String id;

    private String userId;

    private String orderNumber;

    private String picPath;

    private Integer state;

    private String buyUser;

    private String sellUser;

    private String admin;

    private Date createTime;

    private Date updatetime;

    private String trackingNumber;

    private String orderContend;

    private Integer num;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber == null ? null : orderNumber.trim();
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath == null ? null : picPath.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getBuyUser() {
        return buyUser;
    }

    public void setBuyUser(String buyUser) {
        this.buyUser = buyUser == null ? null : buyUser.trim();
    }

    public String getSellUser() {
        return sellUser;
    }

    public void setSellUser(String sellUser) {
        this.sellUser = sellUser == null ? null : sellUser.trim();
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin == null ? null : admin.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber == null ? null : trackingNumber.trim();
    }

    public String getOrderContend() {
        return orderContend;
    }

    public void setOrderContend(String orderContend) {
        this.orderContend = orderContend == null ? null : orderContend.trim();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
    public void resetPic(String root){
    	if(!StringUtils.isEmpty(picPath)){
    		String[] pics = picPath.split(",") ;
    		StringBuilder sb = new StringBuilder("") ; 
    		for(String pic:pics){
//    			pic.replaceAll("//", "/") ;
//    			root.replaceAll("//", "/");  
//    			System.err.println(pic +"  " +root);
    			int index = pic.lastIndexOf(root);
    			if(index == -1){
    				continue; 
    			}
    			String newPic = pic.substring(index+root.length()) ;
    			sb.append(newPic+",");
    		}
    		this.picPath = sb.toString() ; 
    	}
    }
    
    
    public List<String> getPicPath(String root){
    	List<String> list = new ArrayList<String>() ; 
    	if(!StringUtils.isEmpty(picPath)){
    		String[] pics = picPath.split(",") ;
    		StringBuilder sb = new StringBuilder("") ; 
    		for(String pic:pics){  
    			list.add(root+pic) ; 
    		}
    		this.picPath = sb.toString() ; 
    	}
    	return list ; 
    }
    
}