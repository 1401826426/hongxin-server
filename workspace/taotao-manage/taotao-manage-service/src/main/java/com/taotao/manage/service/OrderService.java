package com.taotao.manage.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.taotao.common.bean.Mail;
import com.taotao.common.bean.Order;
import com.taotao.common.util.IdGenerator;
import com.taotao.manage.mapper.OrderInfoMapper;
import com.taotao.manage.mapper.UserMapper;
import com.taotao.manage.pojo.OrderInfo;
import com.taotao.manage.pojo.OrderInfoExample;
import com.taotao.manage.pojo.OrderState;
import com.taotao.manage.pojo.User;
import com.taotao.manage.pojo.UserExample;
import com.taotao.manage.pojo.UserExample.Criteria;
import com.taotao.manage.service.api.IOrderService;
import com.taotao.manage.service.util.SendMailUtil;

@Service
public class OrderService implements IOrderService{
	
	@Autowired
	private OrderInfoMapper orderInfoMapper; 
	
	@Autowired
	private UserMapper userMapper ; 
	
	@Autowired
	private PropertieService propertiesService ; 
	
	@Autowired
	private SendMailUtil  sendMailUtil ; 
	
	@Override
	public ResponseEntity<Order> createOrder(OrderInfo order , String userId) {
		String id = IdGenerator.generateStringId() ; 
		String order_number = IdGenerator.generateStringId() ; 
		order.setId(id); 
		order.setOrderNumber(order_number);
		order.setCreateTime(new Date());
		order.setUpdatetime(new Date());
		order.setState(OrderState.CREATE.getState());
		order.setBuyUser(userId);
//		order.setUserId(userId);
		User admin = getAdmin() ; 
		order.setAdmin(admin.getId());
		order.resetPic(propertiesService.getIMAGE_BASE_URL());
		orderInfoMapper.insert(order) ;  
//		OrderInfo orderInfo = order.Clone() ; 
//		orderInfo.setId(IdGenerator.generateStringId());
//		orderInfo.setUserId(admin.getId());
//		orderInfoMapper.insert(orderInfo) ; 
 		return new ResponseEntity<Order>(getOrder(order) , HttpStatus.OK);
	}
	
	private User getAdmin(){
		UserExample example = new UserExample() ; 
		Criteria criteria = example.createCriteria() ;
		criteria.andRoleEqualTo(1) ; 
		List<User> users = userMapper.selectByExample(example) ; 
		return users.get(0) ; 
	}
	
	public User getUser(String userId){
		if(userId == null){
			return null; 
		}
		UserExample example = new UserExample() ; 
		Criteria criteria = example.createCriteria() ;
		criteria.andIdEqualTo(userId) ; 
		List<User> users = userMapper.selectByExample(example) ;
		if(users != null && users.size() > 0){			
			return users.get(0) ; 
		}else{
			return null ; 
		}
	}
	
	@Override
	public ResponseEntity<List<Order>> getOrder(int st, int num, int type , String userId){
		List<OrderInfo> orders = null ; 
		if(type == 0){
			orders = orderInfoMapper.selectAllOrders(st, num, userId) ; 
		}else{
			orders = orderInfoMapper.selectOrders(st, num, userId, type) ;
		}
		List<Order> result = new ArrayList<Order>() ; 
		if(orders != null && orders.size() > 0){
			for(OrderInfo orderInfo:orders){
				result.add(getOrder(orderInfo)) ; 
			}
		}
		return new ResponseEntity<List<Order>>(result,HttpStatus.OK);
	}

	private Order getOrder(OrderInfo orderInfo) {
		Order order = new Order(); 
		order.setId(orderInfo.getId()); 
		User admin = getUser(orderInfo.getAdmin()) ; 
		if(admin != null){
			order.setAdminName(admin.getName());
		}
		User buy = getUser(orderInfo.getBuyUser()) ; 
		if(buy != null){
			order.setBuyUserName(buy.getName());
		}
		User sell = getUser(orderInfo.getSellUser()) ; 
		if(sell != null){
			order.setSellUserName(sell.getName());
		}
		order.setPics(orderInfo.getPicPath(propertiesService.getIMAGE_BASE_URL()));
		order.setOrderContend(orderInfo.getOrderContend());
		order.setOrderNumber(orderInfo.getOrderContend());
		order.setCreateTime(orderInfo.getCreateTime());
		order.setUpdatetime(orderInfo.getUpdatetime());
		order.setState(orderInfo.getState());
		order.setTrackingNumber(orderInfo.getTrackingNumber());
		return order;
	}

	@Override
	public ResponseEntity<Order> setAdminAck(String userid, String orderId, String sellId) {
		User admin = getUser(userid) ; 
		if(admin == null){
			return new ResponseEntity<Order>(HttpStatus.BAD_REQUEST) ;
		}
		User sell = getUser(sellId) ;
		if(sell == null){
			return new ResponseEntity<Order>(HttpStatus.BAD_REQUEST) ; 
		}
		OrderInfoExample example = new OrderInfoExample() ; 
		com.taotao.manage.pojo.OrderInfoExample.Criteria criteria = example.createCriteria() ; 
		criteria.andIdEqualTo(orderId).andAdminEqualTo(userid) ; 
		List<OrderInfo> orders = orderInfoMapper.selectByExample(example) ; 
		if(orders != null && orders.size() == 1){
			OrderInfo order = orders.get(0) ; 
			if(order.getState() != OrderState.CREATE.getState()){
				return new ResponseEntity<Order>(HttpStatus.BAD_REQUEST) ;
			}
			order.setSellUser(sellId);
			order.setState(OrderState.ADMIN_ACK.getState());
			order.setUpdatetime(new Date());
			orderInfoMapper.updateByPrimaryKey(order) ; 
			Mail mail = new Mail(sell.getMail(),propertiesService.getSELL_MAIL_SUBJECT(),propertiesService.getSELL_MAIL_CONTENT()) ;
			sendMailUtil.sendMailAync(mail) ; 
			return new ResponseEntity<Order>(getOrder(order),HttpStatus.OK);
		}else{
			return new ResponseEntity<Order>(HttpStatus.BAD_REQUEST) ;
		}
	}

	@Override
	public ResponseEntity<Order> setSellAck(String userid, String orderId, String trackingNumber) {
		User sell = getUser(userid) ;
		if(sell == null){
			return new ResponseEntity<Order>(HttpStatus.BAD_REQUEST) ; 
		}
		OrderInfoExample example = new OrderInfoExample() ; 
		com.taotao.manage.pojo.OrderInfoExample.Criteria criteria = example.createCriteria() ; 
		criteria.andIdEqualTo(orderId).andSellUserEqualTo(userid) ;
		List<OrderInfo> orders = orderInfoMapper.selectByExample(example) ;
		if(orders != null && orders.size() == 1){
			OrderInfo order = orders.get(0) ; 
			if(order.getState() != OrderState.ADMIN_ACK.getState()){
				return new ResponseEntity<Order>(HttpStatus.BAD_REQUEST) ;
			}
			order.setState(OrderState.SELLER_ACK.getState());
			order.setTrackingNumber(trackingNumber);
			order.setUpdatetime(new Date());
			orderInfoMapper.updateByPrimaryKey(order) ; 
			User user = getUser(order.getBuyUser()) ; 
			Mail mail = new Mail(user.getMail() , propertiesService.getBUY_MAIL_SUBJECT(),propertiesService.getBUY_MAIL_CONTENT()) ; 
			sendMailUtil.sendMailAync(mail) ; 
			return new ResponseEntity<Order>(getOrder(order),HttpStatus.OK);
		}else{
			return new ResponseEntity<Order>(HttpStatus.BAD_REQUEST) ;
		}
	}

	@Override
	public ResponseEntity<Order> setBuyAck(String userid, String orderId) {
		User buy = getUser(userid); 
		if(buy == null){
			return new ResponseEntity<Order>(HttpStatus.BAD_REQUEST) ;
		}
		OrderInfoExample example = new OrderInfoExample() ; 
		com.taotao.manage.pojo.OrderInfoExample.Criteria criteria = example.createCriteria() ; 
		criteria.andIdEqualTo(orderId).andBuyUserEqualTo(userid) ; 
		List<OrderInfo> orders = orderInfoMapper.selectByExample(example) ;
		if(orders != null && orders.size() == 1){
			OrderInfo order = orders.get(0) ; 
			if(order.getState() != OrderState.SELLER_ACK.getState()){
				return new ResponseEntity<Order>(HttpStatus.BAD_REQUEST) ;
			}
			order.setState(OrderState.BUYER_ACK.getState());
			order.setUpdatetime(new Date());
			orderInfoMapper.updateByPrimaryKey(order) ; 
			return new ResponseEntity<Order>(getOrder(order),HttpStatus.OK);
		}else{
			return new ResponseEntity<Order>(HttpStatus.BAD_REQUEST) ;
		}
	}

}

















