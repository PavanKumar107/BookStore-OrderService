package com.blz.bookstoreorderservice.exception.exceptionhandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.blz.bookstoreorderservice.exception.CustomNotFoundException;
import com.blz.bookstoreorderservice.util.OrderResponse;

@ControllerAdvice
public class CustomExceptionHandler {
	@ExceptionHandler(CustomNotFoundException.class)
	public ResponseEntity<OrderResponse> handleHiringException(CustomNotFoundException he){
		OrderResponse response=new OrderResponse();
		response.setErrorCode(400);
		response.setMessage(he.getMessage());
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}