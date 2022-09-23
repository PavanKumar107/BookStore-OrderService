package com.blz.bookstoreorderservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blz.bookstoreorderservice.dto.AddressDto;
import com.blz.bookstoreorderservice.model.AddressModel;
import com.blz.bookstoreorderservice.service.IAddressService;
import com.blz.bookstoreorderservice.util.OrderResponse;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

/**
 * Purpose: Address Controller to process Address Data APIs.
 * @version: 4.15.1.RELEASE
 * @author: Pavan Kumar G V
 *   
 */


@RestController
@RequestMapping("address")
public class AddressController {
	@Autowired
	IAddressService addressService;

	/**
	 * Purpose: to add the address
	 * @Param: addressDto,token
	 * 
	 */
	@PostMapping("/addaddress")
	public ResponseEntity<OrderResponse> addAddress(@RequestBody AddressDto addressDTO, @RequestHeader String token) {
		AddressModel addressModel = addressService.addAddress(addressDTO,token);
		OrderResponse orderResponse = new OrderResponse("address added sucessfully", 200, addressModel);
		return new ResponseEntity<OrderResponse>(orderResponse,HttpStatus.OK);
	}

	/**
	 * Purpose: to update the address
	 * @Param: addressDto,token,addressId
	 * 
	 */
	@PutMapping("/updateaddress/{addressId}")
	public ResponseEntity<OrderResponse> updateAddress(@PathVariable Long addressId, @RequestBody AddressDto addressDTO, @RequestHeader String token) {
		AddressModel addressModel = addressService.updateAddress(addressId,addressDTO,token);
		OrderResponse orderResponse = new OrderResponse("address updated uccessfully", 200, addressModel);
		return new ResponseEntity<OrderResponse>(orderResponse,HttpStatus.OK);
	}

	/**
	 * Purpose: to fetch the address
	 * @Param: token
	 * 
	 */
	@GetMapping("/fetchaddress")
	public ResponseEntity<OrderResponse> fetchAddress(@RequestHeader String token) {
		List<AddressModel> addressModel = addressService.fetchAddress(token);
		OrderResponse orderResponse = new OrderResponse("fetching address Successfull", 200, addressModel);
		return new ResponseEntity<OrderResponse>(orderResponse,HttpStatus.OK);
	}

	/**
	 * Purpose: to fetch address by id
	 * @Param: addressDto,token
	 * 
	 */
	@GetMapping("/getaddressbyid/{addressId}")
	public ResponseEntity<OrderResponse> getAddressById(@PathVariable Long addressId,@RequestHeader String token) {
		AddressModel addressModel = addressService.getAddressById(addressId,token);
		OrderResponse orderResponse = new OrderResponse("fetching address by id successfull", 200, addressModel);
		return new ResponseEntity<OrderResponse>(orderResponse,HttpStatus.OK);
	}

	/**
	 * Purpose: to delete the address
	 * @Param: token,addressId
	 * 
	 */
	@DeleteMapping("/deleteaddress/{addressId}")
	public ResponseEntity<OrderResponse> deleteAddress(@PathVariable Long addressId,@RequestHeader String token) {
		AddressModel addressModel = addressService.deleteAddress(addressId,token);
		OrderResponse orderResponse = new OrderResponse("Successfull", 200, addressModel);
		return new ResponseEntity<OrderResponse>(orderResponse,HttpStatus.OK);
	}
}
