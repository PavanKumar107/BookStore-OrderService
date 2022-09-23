package com.blz.bookstoreorderservice.dto;

import lombok.Data;
/**
 *  
 * Purpose:DTO for the address
 * 
 * @author: Pavan Kumar G V 
 * @version: 4.15.1.RELEASE
 * 
 **/ 

@Data
public class AddressDto {
	public String name;
	public long phoneNumber;
	public long pincode; 
	public String locality;
	public String address;
	private String city;
	private String landmark;
}
