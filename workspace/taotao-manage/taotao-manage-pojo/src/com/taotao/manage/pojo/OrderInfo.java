package com.taotao.manage.pojo;
import java.util.Date;

public class OrderInfo {

	//id
	private long id;

	//订单号
	private String orderNumber;

	//支付凭证图位置
	private String picPath;

	//状态:1为买家创建订单,2为管理员确认收钱,3为卖家确认发货,4为买家确认收货
	private int state;

	//买家id
	private long buyUser;

	//卖家id
	private long sellUser;

	//创建时间
	private Date createTime;

	//更新时间
	private Date updatetime;

	//快递单号
	private String trackingNumber;

	//订单信息
	private String orderContend;

	public void setId(long id){
	    this.id = id;
	}

	public long getId(){
	    return id;
	}

	public void setOrderNumber(String orderNumber){
	    this.orderNumber = orderNumber;
	}

	public String getOrderNumber(){
	    return orderNumber;
	}

	public void setPicPath(String picPath){
	    this.picPath = picPath;
	}

	public String getPicPath(){
	    return picPath;
	}

	public void setState(int state){
	    this.state = state;
	}

	public int getState(){
	    return state;
	}

	public void setBuyUser(long buyUser){
	    this.buyUser = buyUser;
	}

	public long getBuyUser(){
	    return buyUser;
	}

	public void setSellUser(long sellUser){
	    this.sellUser = sellUser;
	}

	public long getSellUser(){
	    return sellUser;
	}

	public void setCreateTime(Date createTime){
	    this.createTime = createTime;
	}

	public Date getCreateTime(){
	    return createTime;
	}

	public void setUpdatetime(Date updatetime){
	    this.updatetime = updatetime;
	}

	public Date getUpdatetime(){
	    return updatetime;
	}

	public void setTrackingNumber(String trackingNumber){
	    this.trackingNumber = trackingNumber;
	}

	public String getTrackingNumber(){
	    return trackingNumber;
	}

	public void setOrderContend(String orderContend){
	    this.orderContend = orderContend;
	}

	public String getOrderContend(){
	    return orderContend;
	}

}