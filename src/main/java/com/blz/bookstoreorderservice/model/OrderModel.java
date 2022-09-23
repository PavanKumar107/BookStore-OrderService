package com.blz.bookstoreorderservice.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.blz.bookstoreorderservice.dto.OrderDto;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  
 * Purpose:Model class for Order
 * 
 * @author: Pavan Kumar G V 
 * @version: 4.15.1.RELEASE
 * 
 **/ 
@Entity
@Table(name = "orderdetails")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long orderId;
	private LocalDateTime orderDate;
	private long price;
	private long quantity;
	@OneToOne
	private AddressModel address;
	private long userId;
	private long bookId;
	private long cartId;
	private boolean orderCancel = false;

}
