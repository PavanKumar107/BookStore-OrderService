package com.blz.bookstoreorderservice.service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.blz.bookstoreorderservice.exception.CustomNotFoundException;
import com.blz.bookstoreorderservice.model.AddressModel;
import com.blz.bookstoreorderservice.model.OrderModel;
import com.blz.bookstoreorderservice.repository.AddressRepository;
import com.blz.bookstoreorderservice.repository.OrderRepository;
import com.blz.bookstoreorderservice.util.BookResponse;
import com.blz.bookstoreorderservice.util.CartResponse;
import com.blz.bookstoreorderservice.util.TokenUtil;
import com.blz.bookstoreorderservice.util.UserResponse;
import lombok.extern.slf4j.Slf4j;

/**
 *  
 * Purpose:Service implementation of the Order Service
 * @author: Pavan Kumar G V 
 * @version: 4.15.1.RELEASE
 * 
 */
@Service
@Slf4j
public class OrderService implements IOrderService {

	@Autowired
	OrderRepository orderServiceRepository;
	
	@Autowired
	TokenUtil tokenUtil;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	MailService mailService;

	//Purpose:method to place the order
	@Override
	public OrderModel placeOrder(Long cartId, Long addressId, String token) {
		Long userId = tokenUtil.decodeToken(token);
		UserResponse isUserPresent = restTemplate.getForObject("http://BOOKSTORE-USERSERVICE:8068/userservice/validateuser/" + token, UserResponse.class);
		if (isUserPresent.getToken().getUserId() == userId) {
			CartResponse isCartPresent = restTemplate.getForObject("http://BOOKSTORE-CARTSERVICE:8070/cartservice/validatecart/" + cartId, CartResponse.class);
			if (isUserPresent.getErrorCode() == 200) {
				if (isUserPresent.getToken().getUserId() == isCartPresent.getToken().getUserId()) {
					log.info("abc");
					Optional<AddressModel> isAddressPresent = addressRepository.findById(addressId);
					OrderModel order = new OrderModel();
					order.setOrderDate(LocalDateTime.now());
					order.setBookId(isCartPresent.getToken().getBookId());
					order.setPrice(isCartPresent.getToken().getTotalPrice());
					order.setQuantity(isCartPresent.getToken().getQuantity());
					order.setUserId(isCartPresent.getToken().getUserId());
					order.setCartId(cartId);
					order.setOrderCancel(false);
					if (isAddressPresent.isPresent()) {
						if (isAddressPresent.get().getUserId() == isUserPresent.getToken().getUserId()) {
							order.setAddress(isAddressPresent.get());
						}
						else {
							throw new CustomNotFoundException(500, "user id and userid of address are different");
						}
					}
					else {
						throw new CustomNotFoundException(500, "address not found for this user");
					}
					orderServiceRepository.save(order);
					String body = "Your Order Placed with order id :" +order.getOrderId();
					String subject = "Order placed sucessfully";
					mailService.send(isUserPresent.getToken().getEmailId(), subject, body);
					CartResponse isPresent = restTemplate.getForObject("http://BOOKSTORE-CARTSERVICE:8070/cartservice/deletecart/" + order.getCartId(), CartResponse.class);
					return order;
				}
				throw new CustomNotFoundException(500, "no books found in cart for this user");			
			}
			throw new CustomNotFoundException(500, "no books found in cart for this user");
		}
		return null;
	}

	//Purpose:method to cancel the order
	@Override
	public OrderModel cancelOrder(Long orderId, String token) {
		Long userId = tokenUtil.decodeToken(token);
		UserResponse isUserPresent = restTemplate.getForObject("http://BOOKSTORE-USERSERVICE:8068/userservice/validateuser/" + token, UserResponse.class);
		if (isUserPresent.getErrorCode() == 200) {
			Optional<OrderModel> isOrderPresent = orderServiceRepository.findById(orderId);
			if (isOrderPresent.isPresent()) {
				isOrderPresent.get().setOrderCancel(true);
				BookResponse isBookIdPresent = restTemplate.getForObject("http://BOOKSTORE-BOOKSERVICE:8069/bookservice/removefromcart/" + isOrderPresent.get().getBookId() +"/"+ isOrderPresent.get().getQuantity(), BookResponse.class);
				orderServiceRepository.delete(isOrderPresent.get());
				return isOrderPresent.get();
			}
			throw new CustomNotFoundException(500, "no order found for this user id");
		}
		throw new CustomNotFoundException(500, "no order found for this user id");
	}

	//Purpose:method to fetch all the orders
	@Override
	public List<OrderModel> getOrderDetails(String token) {
		Long userId = tokenUtil.decodeToken(token);
		UserResponse isUserPresent = restTemplate.getForObject("http://BOOKSTORE-USERSERVICE:8068/userservice/validateuser/" + token, UserResponse.class);
		if (isUserPresent.getErrorCode() == 200) {
			List<OrderModel> isOrdersPresent = orderServiceRepository.findAll();
			if (isOrdersPresent.size()>0) {
				return isOrdersPresent;
			}
			throw new CustomNotFoundException(500, "no orders ");
		}
		throw new CustomNotFoundException(500, "user not found");
	}

	//Purpose:method to fetch orders done by specific user
	@Override
	public List<OrderModel> getOrderByUserId(String token) {
		Long userId = tokenUtil.decodeToken(token);
		UserResponse isUserPresent = restTemplate.getForObject("http://BOOKSTORE-USERSERVICE:8068/userservice/validateuser/" + token, UserResponse.class);
		if (isUserPresent.getErrorCode() == 200) {
			List<OrderModel> isOrdersPresent = orderServiceRepository.findByUserId(userId);
			if (isOrdersPresent.size()>0) {
				return isOrdersPresent;
			}
		}
		throw new CustomNotFoundException(500, "user not found");
	}
}
	
