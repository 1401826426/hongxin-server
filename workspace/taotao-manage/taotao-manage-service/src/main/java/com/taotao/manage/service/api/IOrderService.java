package com.taotao.manage.service.api;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.taotao.common.bean.Order;

public interface IOrderService {

	ResponseEntity<Order> createOrder(Map<String,Integer> orderDetails, String userid);

	ResponseEntity<List<Order>> getOrder(int st, int num, int type, String userId);

	ResponseEntity<Order> setAdminAck(String id, String orderId, String sellName);

	ResponseEntity<Order> setSellAck(String id, String orderId, String trackingNumber, int num, Map<String,Integer> orderDetails);

	ResponseEntity<Order> setBuyAck(String id, String orderId, String trackingNumber);


	ResponseEntity<Order> adminAckOrder(String orderId, String adminAckTracking , String userId);

	ResponseEntity<Order> uploadEvidence(String orderId, String picPath, String id);

	ResponseEntity<Order> updateOrderDetailName(List<String> names, String id);

	ResponseEntity<List<String>> getOrderDetailNames(String id);

	ResponseEntity<Order> addOrderDetailNames(String id, String name);

	ResponseEntity<Order> deleteOrderDetailNames(String id, String name);

	ResponseEntity<Order> getOneOrder(String orderId, String id);

}
