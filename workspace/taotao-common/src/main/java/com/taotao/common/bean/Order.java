package com.taotao.common.bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Order extends ErrorMsg{

	private String id;

	private String username;

	private String orderNumber;

	private List<String> pics;

	private int state;

	private String buyUserName;

	private String sellUserName;

	private String adminName;

	private String buyUserId ; 
	
	private String sellUserId ; 
	
	private String adminUserId ; 
	
	private Date createTime;

	private Date updatetime;

	private String trackingNumber;

	private String orderContend;
	
	private String createTimeString ; 
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public List<String> getPics() {
		return pics;
	}

	public void setPics(List<String> pics) {
		this.pics = pics;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getBuyUserName() {
		return buyUserName;
	}

	public void setBuyUserName(String buyUserName) {
		this.buyUserName = buyUserName;
	}

	public String getSellUserName() {
		return sellUserName;
	}

	public void setSellUserName(String sellUserName) {
		this.sellUserName = sellUserName;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
		if(createTime != null){
			SimpleDateFormat sdf = new SimpleDateFormat() ; 
			this.createTimeString = sdf.format(createTime) ; 
		}
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
		this.trackingNumber = trackingNumber;
	}

	public String getOrderContend() {
		return orderContend;
	}

	public void setOrderContend(String orderContend) {
		this.orderContend = orderContend;
	}

	public Order() {

	}

	public String getCreateTimeString() {
		return createTimeString;
	}

	public void setCreateTimeString(String createTimeString) {
		this.createTimeString = createTimeString;
	}

	public String getBuyUserId() {
		return buyUserId;
	}

	public void setBuyUserId(String buyUserId) {
		this.buyUserId = buyUserId;
	}

	public String getSellUserId() {
		return sellUserId;
	}

	public void setSellUserId(String sellUserId) {
		this.sellUserId = sellUserId;
	}

	public String getAdminUserId() {
		return adminUserId;
	}

	public void setAdminUserId(String adminUserId) {
		this.adminUserId = adminUserId;
	}

	public Order(String msg) {
		super(msg);
	}
	
	
	
	
	

}
