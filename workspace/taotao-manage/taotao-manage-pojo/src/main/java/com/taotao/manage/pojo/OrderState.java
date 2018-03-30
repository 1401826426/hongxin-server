package com.taotao.manage.pojo;

public enum OrderState {
	
	CREATE(1),
	ADMIN_ACK(2)  , 
	SELLER_ACK(3) , 
	BUYER_ACK(4) ; 
 	
	private int state ; 
	
	private OrderState(int state){
		this.state = state ; 
	}
	
	public int getState(){
		return state ; 
	}
	
	
}
