package com.taotao.manage.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.taotao.common.util.IdGenerator;

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
    
    public OrderInfo Clone(){
    	OrderInfo orderInfo = new OrderInfo() ; 
		orderInfo.setUserId(getId()) ; 
		orderInfo.setOrderNumber(getOrderNumber());
		orderInfo.setCreateTime(getCreateTime());
		orderInfo.setUpdatetime(orderInfo.getUpdatetime());
		orderInfo.setId(IdGenerator.generateStringId());
		orderInfo.setBuyUser(getBuyUser());
		orderInfo.setSellUser(getSellUser());
		orderInfo.setAdmin(getId());
		orderInfo.setOrderContend(getOrderContend());
		orderInfo.setPicPath(getPicPath());
		orderInfo.setState(getState()) ; 
		orderInfo.setTrackingNumber(getTrackingNumber());
		return orderInfo ; 
    }
    
    public void resetPic(String root){
    	if(picPath != null){
    		String[] pics = picPath.split(",") ;
    		StringBuilder sb = new StringBuilder("") ; 
    		for(String pic:pics){
//    			pic.replaceAll("//", "/") ;
//    			root.replaceAll("//", "/");  
//    			System.err.println(pic +"  " +root);
    			String newPic = pic.substring(pic.lastIndexOf(root)+root.length()-1) ;
    			sb.append(newPic+",");
    		}
    		this.picPath = sb.toString() ; 
    	}
    }
    
    
    public List<String> getPicPath(String root){
    	List<String> list = new ArrayList<String>() ; 
    	if(picPath != null){
    		String[] pics = picPath.split(",") ;
    		StringBuilder sb = new StringBuilder("") ; 
    		for(String pic:pics){  
    			list.add(root+pic) ; 
    		}
    		this.picPath = sb.toString() ; 
    	}
    	return list ; 
    }
    
    public static void main(String[] args) {
    	OrderInfo orderInfo = new OrderInfo(); 
    	String pic = "http://123//456//789,http://123//789" ; 
    	orderInfo.setPicPath(pic); 
    	orderInfo.resetPic("http://123/");
    	System.err.println(orderInfo.getPicPath());
	}
}











