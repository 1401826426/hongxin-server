package com.taotao.manage.service.api;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.taotao.common.bean.Order;
import com.taotao.manage.pojo.OrderInfo;

public interface IOrderService {

	ResponseEntity<Order> createOrder(OrderInfo order, String userid);

	ResponseEntity<List<Order>> getOrder(int st, int num, int type, String userId);

	ResponseEntity<Order> setAdminAck(String id, String orderId, String sellName);

	ResponseEntity<Order> setSellAck(String id, String orderId, String trackingNumber, int num);

	ResponseEntity<Order> setBuyAck(String id, String orderId, String trackingNumber);


	ResponseEntity<Order> adminAckOrder(String orderId, String userId);

	ResponseEntity<Order> uploadEvidence(String orderId, String picPath, String id);

}
