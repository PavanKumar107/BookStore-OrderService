package com.blz.bookstoreorderservice.dto;

import lombok.Data;

/**
 *  
 * Purpose:DTO for the cart
 * 
 * @author: Pavan Kumar G V 
 * @version: 4.15.1.RELEASE
 * 
 **/
@Data
public class CartDto {
	private long cartId;
	private long UserId; 
	private long BookId; 
	private long quantity;
	private long totalPrice;
	
}
