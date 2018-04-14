package com.taotao.manage.pojo;

public enum OrderState {
	
	CREATE(1),
	ADMIN_ACK_ORDER(2)  , 
	BUYER_UPLOAD_EVIDENCE(3),
	ADMIN_ACK(4),
	IN_DELIVERY(5),
	SELLER_ACK(6) ,
	DONE(7) ,
	;
 	
	private int state ; 
	
	private OrderState(int state){
		this.state = state ; 
	}
	
	public int getState(){
		return state ; 
	}
	
	
}
