package com.taotao.common.util;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.taotao.common.bean.OrderDetailDto;

public class OrderDetailUtil {
	
	public static String get(List<OrderDetailDto> orderDetailDtos){
		StringBuilder sb = new StringBuilder("") ;
		if(orderDetailDtos != null){
			for(OrderDetailDto orderDetail:orderDetailDtos){
				sb.append(orderDetail.getName()+":"+orderDetail.getNum()+";") ; 
			}
		}
		return sb.toString(); 
	}
	
	public static String get(Map<String,Integer> orderDetailDtos){
		StringBuilder sb = new StringBuilder("") ;
		if(orderDetailDtos != null){
			for(Map.Entry<String, Integer> entry:orderDetailDtos.entrySet()){
				sb.append(entry.getKey()+":"+entry.getValue()+";") ; 				
			}
		}
		return sb.toString(); 
	}
	
	public static List<OrderDetailDto> parse(String orderDetail){
		List<OrderDetailDto> list = new LinkedList<OrderDetailDto>() ; 
		if(!StringUtils.isEmpty(orderDetail)){
			String[] sss = orderDetail.split(";") ; 
			for(String ss:sss){
				String[] s = ss.split(":") ; 
				OrderDetailDto orderDetailDto = new OrderDetailDto() ; 
				orderDetailDto.setName(s[0]);
				orderDetailDto.setNum(Integer.parseInt(s[1]));
			}
		}
		return list ; 
	}
	
}




















