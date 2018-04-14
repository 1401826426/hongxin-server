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
import com.taotao.common.bean.OrderTrackingDto;
import com.taotao.common.util.IdGenerator;
import com.taotao.manage.mapper.OrderInfoMapper;
import com.taotao.manage.mapper.OrderTrackingMapper;
import com.taotao.manage.mapper.UserMapper;
import com.taotao.manage.pojo.OrderInfo;
import com.taotao.manage.pojo.OrderInfoExample;
import com.taotao.manage.pojo.OrderState;
import com.taotao.manage.pojo.OrderTracking;
import com.taotao.manage.pojo.OrderTrackingExample;
import com.taotao.manage.pojo.OrderTrackingState;
import com.taotao.manage.pojo.User;
import com.taotao.manage.pojo.UserExample;
import com.taotao.manage.pojo.UserExample.Criteria;
import com.taotao.manage.service.api.IOrderService;
import com.taotao.manage.service.util.SendMailUtil;

@Service
public class OrderService implements IOrderService {

	@Autowired
	private OrderInfoMapper orderInfoMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private OrderTrackingMapper orderTrackingMapper;

	@Autowired
	private PropertieService propertiesService;

	@Autowired
	private SendMailUtil sendMailUtil;

	// 获取订单
	@Override
	public ResponseEntity<List<Order>> getOrder(int st, int num, int type, String userId) {
		List<OrderInfo> orders = null;
		if (type == 0) {
			orders = orderInfoMapper.selectAllOrders(st, num, userId);
		} else {
			orders = orderInfoMapper.selectOrders(st, num, userId, type);
		}
		List<Order> result = new ArrayList<Order>();
		if (orders != null && orders.size() > 0) {
			for (OrderInfo orderInfo : orders) {
				result.add(getOrder(orderInfo));
			}
		}
		return new ResponseEntity<List<Order>>(result, HttpStatus.OK);
	}

	// 创建订单
	@Override
	public ResponseEntity<Order> createOrder(OrderInfo order, String userId) {
		String id = IdGenerator.generateStringId();
		String order_number = IdGenerator.generateStringId();
		order.setId(id);
		order.setOrderNumber(order_number);
		order.setCreateTime(new Date());
		order.setUpdatetime(new Date());
		order.setState(OrderState.CREATE.getState());
		order.setBuyUser(userId);
		User admin = getAdmin();
		order.setAdmin(admin.getId());
		orderInfoMapper.insert(order);
		return new ResponseEntity<Order>(getOrder(order), HttpStatus.OK);
	}

	private User getAdmin() {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andRoleEqualTo(1);
		List<User> users = userMapper.selectByExample(example);
		return users.get(0);
	}

	private User getUser(String userId) {
		if (userId == null) {
			return null;
		}
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(userId);
		List<User> users = userMapper.selectByExample(example);
		if (users != null && users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}
	}

	private Order getOrder(OrderInfo orderInfo) {
		Order order = new Order();
		order.setId(orderInfo.getId());
		User admin = getUser(orderInfo.getAdmin());
		if (admin != null) {
			order.setAdminName(admin.getName());
		}
		User buy = getUser(orderInfo.getBuyUser());
		if (buy != null) {
			order.setBuyUserName(buy.getName());
		}
		User sell = getUser(orderInfo.getSellUser());
		if (sell != null) {
			order.setSellUserName(sell.getName());
		}
		order.setPics(orderInfo.getPicPath(propertiesService.getIMAGE_BASE_URL()));
		order.setOrderContend(orderInfo.getOrderContend());
		order.setOrderNumber(orderInfo.getOrderNumber());
		order.setCreateTime(orderInfo.getCreateTime());
		order.setUpdatetime(orderInfo.getUpdatetime());
		order.setState(orderInfo.getState());
//		order.setTrackingNumber(orderInfo.getTrackingNumber());
		order.setNum(orderInfo.getNum());
		order.setBuyUserId(orderInfo.getBuyUser());
		order.setSellUserId(orderInfo.getSellUser());
		order.setAdminUserId(orderInfo.getAdmin());
		List<OrderTracking> orderTrackings = getOrderTracking(orderInfo.getId()) ; 
		List<OrderTrackingDto> orderTrackingDtos = new ArrayList<OrderTrackingDto>() ; 
		for(OrderTracking orderTracking:orderTrackings){
			OrderTrackingDto orderTrackingDto = new OrderTrackingDto() ; 
			orderTrackingDto.setOrderId(orderTracking.getOrderId());
			orderTrackingDto.setTrackingNum(orderTracking.getTrackingNumber());
			orderTrackingDto.setState(orderTracking.getState());
			orderTrackingDto.setNum(orderTracking.getNum());
			orderTrackingDtos.add(orderTrackingDto) ; 
		}
		order.setOrderTrackings(orderTrackingDtos);
		return order; 
	}

	
	
	private List<OrderTracking> getOrderTracking(String orderId) {
		OrderTrackingExample example = new OrderTrackingExample() ; 
		com.taotao.manage.pojo.OrderTrackingExample.Criteria criteria = example.createCriteria() ; 
		criteria.andOrderIdEqualTo(orderId) ; 
		List<OrderTracking> list = orderTrackingMapper.selectByExample(example) ;
		return list;
	}

	// 管理员确认订单
	@Override
	public ResponseEntity<Order> adminAckOrder(String orderId, String userId) {
		User user = getUser(userId);
		if (user == null || !user.isAdmin()) {
			return new ResponseEntity<Order>(new Order("不是管理员"), HttpStatus.BAD_REQUEST);
		}
		OrderInfo orderInfo = getOrderInfo(orderId);
		if (orderInfo.getState() != OrderState.CREATE.getState()) {
			return new ResponseEntity<Order>(new Order("不是刚创建状态"), HttpStatus.BAD_REQUEST);
		}
		orderInfo.setState(OrderState.ADMIN_ACK_ORDER.getState());
		orderInfo.setUpdatetime(new Date());
		orderInfoMapper.updateByPrimaryKey(orderInfo);
		return new ResponseEntity<Order>(getOrder(orderInfo), HttpStatus.OK);
	}

	private User getUserByName(String sellName) {
		UserExample example1 = new UserExample();
		Criteria criteria1 = example1.createCriteria();
		criteria1.andNameEqualTo(sellName);
		List<User> user = userMapper.selectByExample(example1);
		if (user == null || user.size() <= 0) {
			return null;
		} else {
			return user.get(0);
		}
	}

	// 上传凭证
	@Override
	public ResponseEntity<Order> uploadEvidence(String orderId, String picPath, String userId) {
		User user = getUser(userId);
		OrderInfo orderInfo = getOrderInfo(orderId);
		if (!orderInfo.getBuyUser().equals(user.getId())) {
			return new ResponseEntity<Order>(new Order("不是买家"), HttpStatus.BAD_REQUEST);
		}
		if (orderInfo.getState() != OrderState.ADMIN_ACK_ORDER.getState()) {
			return new ResponseEntity<Order>(new Order("不是管理员确认订单状态"), HttpStatus.BAD_REQUEST);
		}
		orderInfo.setState(OrderState.BUYER_UPLOAD_EVIDENCE.getState());
		orderInfo.setPicPath(picPath);
		orderInfo.resetPic(propertiesService.getIMAGE_BASE_URL());
		orderInfo.setUpdatetime(new Date());
		orderInfoMapper.updateByPrimaryKey(orderInfo);
		return new ResponseEntity<Order>(getOrder(orderInfo), HttpStatus.OK);
	}

	// 管理员确认凭证
	@Override
	public ResponseEntity<Order> setAdminAck(String userid, String orderId, String sellName) {
		User admin = getUser(userid);
		if (admin == null || !admin.isAdmin()) {
			return new ResponseEntity<Order>(new Order("不是管理员"), HttpStatus.BAD_REQUEST);
		}
		User sell = getUserByName(sellName);
		if (sell == null) {
			return new ResponseEntity<Order>(new Order("没有该玩家名字"), HttpStatus.BAD_REQUEST);
		}
		OrderInfoExample example = new OrderInfoExample();
		com.taotao.manage.pojo.OrderInfoExample.Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(orderId).andAdminEqualTo(userid);
		List<OrderInfo> orders = orderInfoMapper.selectByExample(example);
		if (orders != null && orders.size() == 1) {
			OrderInfo order = orders.get(0);
			if (order.getState() != OrderState.BUYER_UPLOAD_EVIDENCE.getState()) {
				return new ResponseEntity<Order>(new Order("不是已上传凭证状态"), HttpStatus.BAD_REQUEST);
			}
			order.setSellUser(sell.getId());
			order.setState(OrderState.ADMIN_ACK.getState());
			order.setUpdatetime(new Date());
			orderInfoMapper.updateByPrimaryKey(order);
			Mail mail = new Mail(sell.getMail(), propertiesService.getSELL_MAIL_SUBJECT(),
					propertiesService.getSELL_MAIL_CONTENT());
			sendMailUtil.sendMailAync(mail);
			return new ResponseEntity<Order>(getOrder(order), HttpStatus.OK);
		} else {
			return new ResponseEntity<Order>(new Order("没有这个订单"), HttpStatus.BAD_REQUEST);
		}
	}

	//卖家确认发货
	@Override
	public ResponseEntity<Order> setSellAck(String userid, String orderId, String trackingNumber, int num) {
		OrderInfoExample example = new OrderInfoExample();
		com.taotao.manage.pojo.OrderInfoExample.Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(orderId).andSellUserEqualTo(userid);
		List<OrderInfo> orders = orderInfoMapper.selectByExample(example);
		if (orders != null && orders.size() == 1) {
			OrderInfo order = orders.get(0);
			User sell = getUser(userid);
			if (sell == null || !order.getSellUser().equals(sell.getId())) {
				return new ResponseEntity<Order>(new Order("不是卖家"), HttpStatus.BAD_REQUEST);
			}
			if (order.getState() != OrderState.ADMIN_ACK.getState() && order.getState() != OrderState.IN_DELIVERY.getState()) {
				return new ResponseEntity<Order>(new Order("不是发货中状态"), HttpStatus.BAD_REQUEST);
			}
			
			OrderTracking orderTracking = getOrderTracking(orderId,trackingNumber) ; 
			if(orderTracking != null){
				return new ResponseEntity<Order>(new Order(orderId + "订单已经有订单号为" + trackingNumber +"的物流信息"), HttpStatus.BAD_REQUEST);
			}
			orderTracking = new OrderTracking() ; 
			String id = IdGenerator.generateStringId() ; 
			orderTracking.setId(id);
			orderTracking.setOrderId(orderId);
			orderTracking.setTrackingNumber(trackingNumber);
			orderTracking.setNum(num);
			orderTracking.setState(OrderTrackingState.CREATE.getState());
			orderTrackingMapper.insert(orderTracking) ;
			int allnum = getOrderTrackingNum(orderId) ; 
			if(allnum >= order.getNum()){
				order.setState(OrderState.SELLER_ACK.getState());
			}else{
				order.setState(OrderState.IN_DELIVERY.getState());
			}
			order.setUpdatetime(new Date());
			orderInfoMapper.updateByPrimaryKey(order);
			User user = getUser(order.getBuyUser());
			Mail mail = new Mail(user.getMail(), propertiesService.getBUY_MAIL_SUBJECT(),
					propertiesService.getBUY_MAIL_CONTENT());
			sendMailUtil.sendMailAync(mail);
			return new ResponseEntity<Order>(getOrder(order), HttpStatus.OK);
		} else {
			return new ResponseEntity<Order>(new Order("没有这个订单"), HttpStatus.BAD_REQUEST);
		}
	}

	private int getOrderTrackingNum(String orderId) {
		List<OrderTracking> list = getOrderTracking(orderId) ; 
 		int num = 0 ; 
		if(list != null){
			for(OrderTracking orderTracking : list){
				num += orderTracking.getNum() ; 
			}
		}
		return num;
	}

	private OrderTracking getOrderTracking(String orderId, String trackingNumber) {
		OrderTrackingExample example = new OrderTrackingExample() ; 
		com.taotao.manage.pojo.OrderTrackingExample.Criteria criteria = example.createCriteria() ; 
		criteria.andOrderIdEqualTo(orderId).andTrackingNumberEqualTo(trackingNumber) ; 
		List<OrderTracking> list = orderTrackingMapper.selectByExample(example) ;
		if(list != null && list.size() > 0){
			return list.get(0) ; 
		}
		return null;
	}

	@Override
	public ResponseEntity<Order> setBuyAck(String userid, String orderId, String trackingNumber) {
		
		OrderInfoExample example = new OrderInfoExample();
		com.taotao.manage.pojo.OrderInfoExample.Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(orderId).andBuyUserEqualTo(userid);
		List<OrderInfo> orders = orderInfoMapper.selectByExample(example);
		if (orders != null && orders.size() == 1) {
			OrderInfo order = orders.get(0);
			User buy = getUser(userid);
			if (buy == null || !order.getBuyUser().equals(buy.getId())) {
				return new ResponseEntity<Order>(new Order("不是买家"), HttpStatus.BAD_REQUEST);
			}
			if (order.getState() != OrderState.SELLER_ACK.getState() && order.getState() != OrderState.IN_DELIVERY.getState()) {
				return new ResponseEntity<Order>(new Order("不是在发货中或已发货状态"), HttpStatus.BAD_REQUEST);
			}
			OrderTracking orderTracking = getOrderTracking(orderId,trackingNumber) ; 
			if(orderTracking  == null){
				return new ResponseEntity<Order>(new Order(orderId+"没有订单号为"+trackingNumber+"的物流"), HttpStatus.BAD_REQUEST);
			}
			orderTracking.setState(OrderTrackingState.ACK.getState());
			orderTrackingMapper.updateByPrimaryKey(orderTracking) ; 
			int ackNum = getOrderTrackingAckNum(orderId) ; 
			if(ackNum >= order.getNum()){
				order.setState(OrderState.DONE.getState());
				order.setUpdatetime(new Date());
				orderInfoMapper.updateByPrimaryKey(order) ; 
			}
			return new ResponseEntity<Order>(getOrder(order), HttpStatus.OK);
		} else {
			return new ResponseEntity<Order>(new Order("没有这个订单"), HttpStatus.BAD_REQUEST);
		}
	}

	private int getOrderTrackingAckNum(String orderId) {
		OrderTrackingExample example = new OrderTrackingExample() ; 
		com.taotao.manage.pojo.OrderTrackingExample.Criteria criteria = example.createCriteria() ; 
		criteria.andOrderIdEqualTo(orderId).andStateEqualTo(OrderTrackingState.ACK.getState()) ; 
		List<OrderTracking> list = orderTrackingMapper.selectByExample(example) ;
		int num = 0 ; 
		if(list != null){
			for(OrderTracking orderTracking : list){
				num += orderTracking.getNum() ; 
			}
		}
		return num;
	}

	private OrderInfo getOrderInfo(String orderId) {
		return orderInfoMapper.selectByPrimaryKey(orderId);
	}

}
