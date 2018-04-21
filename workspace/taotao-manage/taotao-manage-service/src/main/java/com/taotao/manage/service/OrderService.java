package com.taotao.manage.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.taotao.common.bean.Mail;
import com.taotao.common.bean.Order;
import com.taotao.common.bean.OrderDetailDto;
import com.taotao.common.bean.OrderTrackingDto;
import com.taotao.common.util.IdGenerator;
import com.taotao.common.util.OrderDetailUtil;
import com.taotao.manage.mapper.OrderDetailNameMapper;
import com.taotao.manage.mapper.OrderInfoMapper;
import com.taotao.manage.mapper.OrderTrackingMapper;
import com.taotao.manage.mapper.UserMapper;
import com.taotao.manage.pojo.OrderDetailName;
import com.taotao.manage.pojo.OrderDetailNameExample;
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
	private OrderDetailNameMapper orderDetailMapper; 

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

	@Override
	public ResponseEntity<Order> getOneOrder(String orderId, String userId) {
		OrderInfo order = orderInfoMapper.selectByPrimaryKey(orderId) ; 
		if(order == null){
			return new ResponseEntity<Order>(new Order("没有订单详情"), HttpStatus.BAD_REQUEST);
		}
		if(!userId.equals(order.getBuyUser()) && !userId.equals(order.getSellUser()) && !userId.equals(order.getAdmin())){
			return new ResponseEntity<Order>(new Order("没有权限查看此订单"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Order>(getOrder(order), HttpStatus.OK);
	}
	
	// 创建订单
	@Override
	public ResponseEntity<Order> createOrder(Map<String,Integer> orderDetails , String userId) {
		if(orderDetails == null || orderDetails.size() == 0){
			return new ResponseEntity<Order>(new Order("没有订单详情"), HttpStatus.BAD_REQUEST);
		}
		OrderInfo order = new OrderInfo() ; 
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
		order.setDetail(OrderDetailUtil.get(orderDetails));
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
		order.setOrderNumber(orderInfo.getOrderNumber());
		order.setCreateTime(orderInfo.getCreateTime());
		order.setUpdatetime(orderInfo.getUpdatetime());
		order.setState(orderInfo.getState());
//		order.setTrackingNumber(orderInfo.getTrackingNumber());
		order.setBuyUserId(orderInfo.getBuyUser());
		order.setSellUserId(orderInfo.getSellUser());
		order.setAdminUserId(orderInfo.getAdmin());
		List<OrderDetailDto> orderDetails = OrderDetailUtil.parse(orderInfo.getDetail()) ;  
		order.setOrderDetails(orderDetails);
		List<OrderTracking> orderTrackings = getOrderTracking(orderInfo.getId()) ; 
		List<OrderTrackingDto> orderTrackingDtos = new ArrayList<OrderTrackingDto>() ; 
		for(OrderTracking orderTracking:orderTrackings){
			OrderTrackingDto orderTrackingDto = new OrderTrackingDto() ; 
			orderTrackingDto.setOrderId(orderTracking.getOrderId());
			orderTrackingDto.setTrackingNum(orderTracking.getTrackingNumber());
			orderTrackingDto.setState(orderTracking.getState());
//			orderTrackingDto.setNum(orderTracking.getNum()); 
			orderTrackingDto.setOrderTrackingOrderDetails(OrderDetailUtil.parse(orderTracking.getDetail()));
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
	public ResponseEntity<Order> adminAckOrder(String orderId,String adminAckTracking, String userId) {
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
		orderInfo.setAdminAckTracking(adminAckTracking);
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
	public ResponseEntity<Order> setSellAck(String userid, String orderId, String trackingNumber, int num , Map<String,Integer> orderDetails) {
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
			if(orderDetails == null){
				return new ResponseEntity<Order>(new Order("没有订单详细信息"), HttpStatus.BAD_REQUEST);
			}
			orderTracking = new OrderTracking() ; 
			String id = IdGenerator.generateStringId() ; 
			orderTracking.setId(id);
			orderTracking.setOrderId(orderId);
			orderTracking.setTrackingNumber(trackingNumber);
			orderTracking.setDetail(OrderDetailUtil.get(orderDetails));
			orderTracking.setState(OrderTrackingState.CREATE.getState());
			orderTrackingMapper.insert(orderTracking) ;
			if(checkAllSend(order)){
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

	private boolean checkAllSend(OrderInfo order) {
//		List<OrderDetailDto> orderTrackingDtos = getOrderDetails(order.getId(),OrderDetailType.ORDER.getType()) ;
		List<OrderDetailDto> orderDetails = OrderDetailUtil.parse(order.getDetail()) ; 
		Map<String,Integer> map = new HashMap<String,Integer>() ; 
		for(OrderDetailDto orderDetailDto:orderDetails){
			String name = orderDetailDto.getName() ; 
			if(map.containsKey(name)){
				map.put(name, map.get(name)+orderDetailDto.getNum()) ; 
			}else{
				map.put(name, orderDetailDto.getNum()) ; 
			}
		}
		List<OrderTracking> orderTrackings = getOrderTracking(order.getId()) ; 
		for(OrderTracking orderTracking:orderTrackings){
			List<OrderDetailDto> list  = OrderDetailUtil.parse(orderTracking.getDetail()) ; 	
			for(OrderDetailDto orderDetailDto:list){
				String name = orderDetailDto.getName() ; 
				if(map.containsKey(name)){
					map.put(name, map.get(name)-orderDetailDto.getNum()) ; 
				}
			}
		}
		for(int num:map.values()){
			if(num > 0){
				return false ; 
			}
		}
		return true;
	}


	public boolean checkAllRecieve(OrderInfo order){
		if(order.getState() != OrderState.SELLER_ACK.getState()){
			return false ;
		}
		List<OrderTracking> list = getOrderTracking(order.getId()) ;
		for(OrderTracking orderTracking:list){
			if(orderTracking.getState() != OrderTrackingState.ACK.getState()){
				return false ; 
			}
		}
		return true; 
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
			if(checkAllRecieve(order)){
				order.setState(OrderState.DONE.getState());
				order.setUpdatetime(new Date());
				orderInfoMapper.updateByPrimaryKey(order) ; 
			}
			return new ResponseEntity<Order>(getOrder(order), HttpStatus.OK);
		} else {
			return new ResponseEntity<Order>(new Order("没有这个订单"), HttpStatus.BAD_REQUEST);
		}
	}

	private OrderInfo getOrderInfo(String orderId) {
		return orderInfoMapper.selectByPrimaryKey(orderId);
	}

	@Override
	public ResponseEntity<Order> updateOrderDetailName(List<String> names, String id) {
		User user = getUser(id) ; 
		if(user == null || !user.isAdmin()){
			return new ResponseEntity<Order>(new Order("不是管理员"), HttpStatus.BAD_REQUEST);
		}
		OrderDetailNameExample example = new OrderDetailNameExample() ; 
		orderDetailMapper.deleteByExample(example) ;  
		for(String name:names){
			OrderDetailName orderDetailName =  new OrderDetailName() ;
			orderDetailName.setName(name);
			orderDetailMapper.insert(orderDetailName) ; 
		}
		return new ResponseEntity<Order>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<String>> getOrderDetailNames(String userid) {
		OrderDetailNameExample example = new OrderDetailNameExample() ;
		List<OrderDetailName> list = orderDetailMapper.selectByExample(example) ; 
		List<String> result = new LinkedList<String>() ; 
		for(OrderDetailName name:list){
			result.add(name.getName()) ; 
		}
		return new ResponseEntity<List<String>>(result , HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Order> addOrderDetailNames(String id, String name) {
		User user = getUser(id) ; 
		if(user == null || !user.isAdmin()){
			return new ResponseEntity<Order>(new Order("不是管理员"), HttpStatus.BAD_REQUEST);
		}
		if(StringUtils.isBlank(name)){
			return new ResponseEntity<Order>(new Order("添加的名字不能为空"), HttpStatus.BAD_REQUEST);
		}
		OrderDetailNameExample example = new OrderDetailNameExample() ;
		com.taotao.manage.pojo.OrderDetailNameExample.Criteria criterai = example.createCriteria() ;
		criterai.andNameEqualTo(name) ; 
		List<OrderDetailName> orderDetailNames = orderDetailMapper.selectByExample(example) ; 
		if(orderDetailNames != null && orderDetailNames.size() > 0){
			return new ResponseEntity<Order>(new Order("已经有这个名字"), HttpStatus.BAD_REQUEST);
		}
		OrderDetailName orderDetailName = new OrderDetailName() ; 
		orderDetailName.setName(name);
		orderDetailMapper.insert(orderDetailName) ; 
		return new ResponseEntity<Order>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Order> deleteOrderDetailNames(String id, String name) {
		User user = getUser(id) ; 
		if(user == null || !user.isAdmin()){
			return new ResponseEntity<Order>(new Order("不是管理员"), HttpStatus.BAD_REQUEST);
		}
		orderDetailMapper.deleteByPrimaryKey(name) ; 
		return new ResponseEntity<Order>(HttpStatus.OK);
	}

	


}









