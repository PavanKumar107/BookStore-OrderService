package com.blz.bookstoreorderservice.service;

import java.util.List;

import com.blz.bookstoreorderservice.model.OrderModel;

/**
 *  
 * Purpose:Service Interface for Order
 * 
 * @author: Pavan Kumar G V 
 * @version: 4.15.1.RELEASE
 * 
 **/ 
public interface IOrderService {

	OrderModel placeOrder(Long cartId, Long addressId, String token);
	
	OrderModel cancelOrder(Long orderId, String token);

	List<OrderModel> getOrderDetails(String token);

	List<OrderModel> getOrderByUserId(String token);
}
