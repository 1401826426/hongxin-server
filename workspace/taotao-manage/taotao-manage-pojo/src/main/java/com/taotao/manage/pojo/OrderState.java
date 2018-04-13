package com.taotao.manage.pojo;

public enum OrderState {
	
	CREATE(1),
	ADMIN_ACK_ORDER(2)  , 
	BUYER_UPLOAD_EVIDENCE(3),
	ADMIN_ACK(4),
	IN_DELIVERY(5),
	DONE(6) ,
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
