package com.blz.bookstoreorderservice.service;

import java.util.List;

import com.blz.bookstoreorderservice.dto.AddressDto;
import com.blz.bookstoreorderservice.model.AddressModel;

/**
 *  
 * Purpose:Service Interface for Address
 * 
 * @author: Pavan Kumar G V 
 * @version: 4.15.1.RELEASE
 * 
 **/ 
public interface IAddressService {

	AddressModel addAddress(AddressDto addressDTO, String token);

	AddressModel updateAddress(Long addressId, AddressDto addressDTO, String token);

	List<AddressModel> fetchAddress(String token);

	AddressModel getAddressById(Long addressId, String token);

	AddressModel deleteAddress(Long addressId, String token);

}
