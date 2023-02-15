package com.hotel.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hotel.entities.Hotel;


public interface HotelService {
	
	//post hotel data
		Hotel saveHotel(Hotel hotel);
		
		//get all user
		List<Hotel> getAllHotel();
		
		
		//get single user
	    Hotel getHotel(String id);
		
		//delete user
		
		
		//update user

}
