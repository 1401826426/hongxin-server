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
	
	//获取订单列表
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
	
	
	//买家创建订单
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
	
	//管理员确认订单
	@RequestMapping(value="/adminAckOrder",method=RequestMethod.POST)
	public ResponseEntity<Order> adminAckOrder(@RequestParam("orderId") String orderId , 
			HttpServletRequest request){
		Session session = getSession(request) ; 
		if(session == null){
			return new ResponseEntity<Order>(HttpStatus.FORBIDDEN) ; 
		}
		UserDto userDto = session.getUserDto() ; 
		if(userDto == null){
			return new ResponseEntity<Order>(HttpStatus.FORBIDDEN) ; 
		}
		return orderService.adminAckOrder(orderId,userDto.getId()); 
	}
	
	
	//买家上传凭证
	@RequestMapping(value="/uploadEvidence",method=RequestMethod.POST)
	public ResponseEntity<Order> uploadEvidence(@RequestParam("picPath") String picPath,@RequestParam("orderId") String orderId , 
			HttpServletRequest request){
		Session session = getSession(request) ; 
		if(session == null){
			return new ResponseEntity<Order>(HttpStatus.FORBIDDEN) ; 
		}
		UserDto userDto = session.getUserDto() ; 
		if(userDto == null){
			return new ResponseEntity<Order>(HttpStatus.FORBIDDEN) ; 
		}
		return orderService.uploadEvidence(orderId,picPath,userDto.getId()); 
	}
	
	//管理员确认凭证
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
	
	//卖家确认发货
	@RequestMapping(value="/sellAck",method=RequestMethod.POST)
	public ResponseEntity<Order> sellAck(@RequestParam("trackingNumber") String trackingNumber ,
			@RequestParam("orderId") String orderId ,@RequestParam("num") int num, HttpServletRequest request){
		Session session = getSession(request) ; 
		if(session == null){
			return new ResponseEntity<Order>(HttpStatus.FORBIDDEN) ; 
		}
		UserDto userDto = session.getUserDto() ; 
		if(userDto == null){
			return new ResponseEntity<Order>(HttpStatus.FORBIDDEN) ; 
		}
		return orderService.setSellAck(userDto.getId() , orderId , trackingNumber , num) ; 
	}
	

	@RequestMapping(value="/buyAck",method=RequestMethod.POST)
	public ResponseEntity<Order> buyAck(@RequestParam("orderId") String orderId,@RequestParam("trackingNumber")
	 String trackingNumber , HttpServletRequest request){
		Session session = getSession(request) ; 
		if(session == null){
			return new ResponseEntity<Order>(HttpStatus.FORBIDDEN) ; 
		}
		UserDto userDto = session.getUserDto() ; 
		if(userDto == null){
			return new ResponseEntity<Order>(HttpStatus.FORBIDDEN) ; 
		}
		return orderService.setBuyAck(userDto.getId() , orderId,trackingNumber) ; 
	}
	

	
	
	
}















