package com.taotao.common.bean;

import java.util.List;

public class OrderTrackingContentDto {
	
	private String trackingNumber ; 
	
	private List<OrderDetailDto> orderDetailDtos  ;

	public final String getTrackingNumber() {
		return trackingNumber;
	}

	public final void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public final List<OrderDetailDto> getOrderDetailDtos() {
		return orderDetailDtos;
	}

	public final void setOrderDetailDtos(List<OrderDetailDto> orderDetailDtos) {
		this.orderDetailDtos = orderDetailDtos;
	} 
	
	
	
}
