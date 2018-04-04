package com.taotao.manage.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.common.bean.Order;
import com.taotao.manage.bean.UserDto;
import com.taotao.manage.manage.Session;
import com.taotao.manage.pojo.OrderInfo;
import com.taotao.manage.service.api.IOrderService;

@Controller
@RequestMapping("/order")
public class OrderController extends BaseController{
	
	@Autowired
	private IOrderService orderService ; 
	
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public ResponseEntity<Order> createOrder(OrderInfo order,HttpServletRequest request){
		Session session = getSession(request) ; 
		if(session == null){
			return new ResponseEntity<Order>(HttpStatus.FORBIDDEN) ; 
		}
		UserDto userDto = session.getUserDto() ; 
		if(userDto == null){
			return new ResponseEntity<Order>(HttpStatus.FORBIDDEN) ; 
		}
		return orderService.createOrder(order,userDto.getId()); 
	}
	
	@RequestMapping(value = "/get" , method = RequestMethod.GET)
	public ResponseEntity<List<Order>>  getOrders(@RequestParam("st") int st , @RequestParam("num") int num , 
			@RequestParam("type") int type , HttpServletRequest request){
		Session session = getSession(request) ;
		if(session == null){
			return new ResponseEntity<List<Order>>(HttpStatus.FORBIDDEN) ; 
		}
		UserDto userDto = session.getUserDto() ; 
		if(userDto == null){
			return new ResponseEntity<List<Order>>(HttpStatus.FORBIDDEN) ; 
		}
		return orderService.getOrder(st,num,type,userDto.getId()) ; 
	}
	
	@RequestMapping(value="/adminAck",method=RequestMethod.POST)
	public ResponseEntity<Order> adminAck(@RequestParam("sell") String sellName , @RequestParam("orderId") String orderId , 
			HttpServletRequest request){
		Session session = getSession(request) ; 
		if(session == null){
			return new ResponseEntity<Order>(HttpStatus.FORBIDDEN) ; 
		}
		UserDto userDto = session.getUserDto() ; 
		if(userDto == null){
			return new ResponseEntity<Order>(HttpStatus.FORBIDDEN) ; 
		}
		return orderService.setAdminAck(userDto.getId() , orderId , sellName) ; 
	}
	
	
	@RequestMapping(value="/sellAck",method=RequestMethod.POST)
	public ResponseEntity<Order> sellAck(@RequestParam("trackingNumber") String trackingNumber ,
			@RequestParam("orderId") String orderId , HttpServletRequest request){
		Session session = getSession(request) ; 
		if(session == null){
			return new ResponseEntity<Order>(HttpStatus.FORBIDDEN) ; 
		}
		UserDto userDto = session.getUserDto() ; 
		if(userDto == null){
			return new ResponseEntity<Order>(HttpStatus.FORBIDDEN) ; 
		}
		return orderService.setSellAck(userDto.getId() , orderId , trackingNumber) ; 
	}
	
	@RequestMapping(value="/buyAck",method=RequestMethod.POST)
	public ResponseEntity<Order> buyAck(@RequestParam("orderId") String orderId,  HttpServletRequest request){
		Session session = getSession(request) ; 
		if(session == null){
			return new ResponseEntity<Order>(HttpStatus.FORBIDDEN) ; 
		}
		UserDto userDto = session.getUserDto() ; 
		if(userDto == null){
			return new ResponseEntity<Order>(HttpStatus.FORBIDDEN) ; 
		}
		return orderService.setBuyAck(userDto.getId() , orderId) ; 
	}
	
}















