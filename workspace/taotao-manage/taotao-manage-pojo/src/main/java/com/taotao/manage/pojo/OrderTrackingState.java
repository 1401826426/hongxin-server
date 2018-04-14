package com.taotao.manage.pojo;

public enum OrderTrackingState {
	
	CREATE(0),
	ACK(1) ; 
	private int state ; 
	
	private OrderTrackingState(int state){
		this.state = state ; 
	}
	
	public int getState(){
		return state ;
	}
}
