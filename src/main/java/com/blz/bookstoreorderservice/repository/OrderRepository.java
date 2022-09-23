package com.blz.bookstoreorderservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blz.bookstoreorderservice.model.OrderModel;

/**
 *  
 * Purpose:Repository for Order Service
 * 
 * @author: Pavan Kumar G V 
 * @version: 4.15.1.RELEASE
 * 
 **/ 
@Repository
public interface OrderRepository extends JpaRepository<OrderModel, Long>{

	List<OrderModel> findByUserId(Long userId);


}
