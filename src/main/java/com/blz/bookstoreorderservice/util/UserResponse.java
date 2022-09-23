package com.blz.bookstoreorderservice.util;

import com.blz.bookstoreorderservice.dto.BSUserDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  
 * Purpose:Response to the user
 * @author: Pavan Kumar G V 
 * @version: 4.15.1.RELEASE
 * 
 **/ 
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String message;
    private int errorCode;
    private BSUserDto token;
   
}