package com.blz.bookstoreorderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blz.bookstoreorderservice.model.AddressModel;

/**
 *  
 * Purpose:Repository for Address service
 * 
 * @author: Pavan Kumar G V 
 * @version: 4.15.1.RELEASE
 * 
 **/ 
@Repository
public interface AddressRepository extends JpaRepository<AddressModel, Long>{

}
