package com.blz.bookstoreorderservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blz.bookstoreorderservice.model.OrderModel;
import com.blz.bookstoreorderservice.service.IOrderService;
import com.blz.bookstoreorderservice.util.OrderResponse;
/**
 * Purpose: Order Controller to process Order Data APIs.
 * @version: 4.15.1.RELEASE
 * @author: Pavan Kumar G V
 *   
 */


@RestController
@RequestMapping("/orderservice")
public class OrderController {

	@Autowired
	IOrderService orderService;


	/**
	 * Purpose: to place the order
	 * @Param: cartId,addressId,token
	 * 
	 */
	@PostMapping("/placeorder")
	public ResponseEntity<OrderResponse> placeOrder(@RequestParam Long cartId, @RequestParam Long addressId, @RequestHeader String token){
		OrderModel order = orderService.placeOrder(cartId, addressId, token);
		OrderResponse orderResponse = new OrderResponse("order placed successfully", 200, order);
		return new ResponseEntity<OrderResponse>(orderResponse,HttpStatus.OK);
	}

	/**
	 * Purpose: to cancel the order
	 * @Param: corderId,token
	 * 
	 */
	@DeleteMapping("/cancelorder/{orderId}")
	public ResponseEntity<OrderResponse> cancelOrder(@PathVariable Long orderId,@RequestHeader String token){
		OrderModel order = orderService.cancelOrder(orderId, token);
		OrderResponse orderResponse = new OrderResponse("order canceled successfully", 200, order);
		return new ResponseEntity<OrderResponse>(orderResponse,HttpStatus.OK);
	}

	/**
	 * Purpose: to fetch order details
	 * @Param: token
	 * 
	 */
	@GetMapping("/getorderdetails")
	public ResponseEntity<OrderResponse> getOrderDetails(@RequestHeader String token){
		List<OrderModel> order = orderService.getOrderDetails(token);
		OrderResponse orderResponse = new OrderResponse("fetching order details Successfully", 200, order);
		return new ResponseEntity<OrderResponse>(orderResponse,HttpStatus.OK);
	}

	/**
	 * Purpose: to fetch order for specific user
	 * @Param: token
	 * 
	 */
	@GetMapping("/fetchorderbyuserid")
	public ResponseEntity<OrderResponse> getOrderByUserId(@RequestHeader String token){
		List<OrderModel> order = orderService.getOrderByUserId(token);
		OrderResponse orderResponse = new OrderResponse("fetching order details by userid successfully", 200, order);
		return new ResponseEntity<OrderResponse>(orderResponse,HttpStatus.OK);
	}
}
