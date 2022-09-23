package com.blz.bookstoreorderservice.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.blz.bookstoreorderservice.dto.AddressDto;
import com.blz.bookstoreorderservice.exception.CustomNotFoundException;
import com.blz.bookstoreorderservice.model.AddressModel;
import com.blz.bookstoreorderservice.repository.AddressRepository;
import com.blz.bookstoreorderservice.util.TokenUtil;
import com.blz.bookstoreorderservice.util.UserResponse;

/**
 *  
 * Purpose:Service implementation of the Address Service
 * @author: Pavan Kumar G V 
 * @version: 4.15.1.RELEASE
 * 
 */
@Service
public class AddressService implements IAddressService {

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	TokenUtil tokenUtil;

	@Autowired
	RestTemplate restTemplate;

	//Purpose:method to add address
	@Override
	public AddressModel addAddress(AddressDto addressDTO, String token) {
		Long userId = tokenUtil.decodeToken(token);
		UserResponse isUserPresent = restTemplate.getForObject("http://BOOKSTORE-USERSERVICE:8068/userservice/validateuser/" + token, UserResponse.class);
		if (isUserPresent.getErrorCode() == 200) {
			AddressModel addressModel = new AddressModel(addressDTO);
			addressModel.setUserId(userId);
			addressRepository.save(addressModel);
			return addressModel;
		}
		throw new CustomNotFoundException(500, "user not found");
	}

	//Purpose:method to update the address
	@Override
	public AddressModel updateAddress(Long addressId, AddressDto addressDTO, String token) {
		Long userId = tokenUtil.decodeToken(token);
		UserResponse isUserPresent = restTemplate.getForObject("http://BOOKSTORE-USERSERVICE:8068/userservice/validateuser/" + token, UserResponse.class);
		if (isUserPresent.getErrorCode() == 200) {
			Optional<AddressModel> isAddressPresent = addressRepository.findById(addressId);
			if (isAddressPresent.isPresent()) {
				if (isAddressPresent.get().getUserId() == userId) {
					isAddressPresent.get().setName(addressDTO.getName());
					isAddressPresent.get().setPhoneNumber(addressDTO.getPhoneNumber());
					isAddressPresent.get().setAddress(addressDTO.getAddress());
					isAddressPresent.get().setPincode(addressDTO.getPincode());
					isAddressPresent.get().setLocality(addressDTO.getLocality());
					isAddressPresent.get().setLandmark(addressDTO.getLandmark());
					isAddressPresent.get().setCity(addressDTO.getCity());
					addressRepository.save(isAddressPresent.get());
					return isAddressPresent.get();
				}
				throw new CustomNotFoundException(500, "user id not found");
			}
			throw new CustomNotFoundException(500, "address not found");
		}
		throw new CustomNotFoundException(500, "user not found");
	}

	//Purpose:method to fetch the address
	@Override
	public List<AddressModel> fetchAddress(String token) {
		Long userId = tokenUtil.decodeToken(token);
		UserResponse isUserPresent = restTemplate.getForObject("http://BOOKSTORE-USERSERVICE:8068/userservice/validateuser/" + token, UserResponse.class);
		if (isUserPresent.getErrorCode() == 200) {
			List<AddressModel> isAddressPresent = addressRepository.findAll();
			if (isAddressPresent.size()>0) {
				return isAddressPresent;
			}
			throw new CustomNotFoundException(500, "address list empty");
		}
		throw new CustomNotFoundException(500, "user not found");
	}

	//Purpose:method to fetch address by id
	@Override
	public AddressModel getAddressById(Long addressId, String token) {
		Long userId = tokenUtil.decodeToken(token);
		UserResponse isUserPresent = restTemplate.getForObject("http://BOOKSTORE-USERSERVICE:8068/userservice/validateuser/" + token, UserResponse.class);
		if (isUserPresent.getErrorCode() == 200) {
			Optional<AddressModel> isAddressPresent = addressRepository.findById(addressId);
			if (isAddressPresent.isPresent()) {
				if (isAddressPresent.get().getUserId() == userId) {
					return isAddressPresent.get();
				}
				throw new CustomNotFoundException(500, "user id invalid");
			}
			throw new CustomNotFoundException(500, "address not found");
		}
		throw new CustomNotFoundException(500, "user not found");
	}

	//Purpose:method to delete address
	@Override
	public AddressModel deleteAddress(Long addressId, String token) {
		Long userId = tokenUtil.decodeToken(token);
		UserResponse isUserPresent = restTemplate.getForObject("http://BOOKSTORE-USERSERVICE:8068/userservice/validateuser/" + token, UserResponse.class);
		if (isUserPresent.getErrorCode() == 200) {
			Optional<AddressModel> isAddressPresent = addressRepository.findById(addressId);
			if (isAddressPresent.isPresent()) {
				if (isAddressPresent.get().getUserId() == userId) {
					addressRepository.delete(isAddressPresent.get());
					return isAddressPresent.get();
				}
			}
			throw new CustomNotFoundException(500, "Address Not Found");
		}
		throw new CustomNotFoundException(500, "User Not Found");
	}
}
