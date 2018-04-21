package com.taotao.common.bean;

import java.util.List;

public class OrderTrackingDto {
	
	private String orderId ; 
	
	private String trackingNum ; 
	
	private int num ; 
	
	private int state ;
	
	private List<OrderDetailDto> orderTrackingOrderDetails ; 

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getTrackingNum() {
		return trackingNum;
	}

	public void setTrackingNum(String trackingNum) {
		this.trackingNum = trackingNum;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public List<OrderDetailDto> getOrderTrackingOrderDetails() {
		return orderTrackingOrderDetails;
	}

	public void setOrderTrackingOrderDetails(List<OrderDetailDto> orderTrackingOrderDetails) {
		this.orderTrackingOrderDetails = orderTrackingOrderDetails;
	}

	
	
	
	
	
	
}
