package com.hotel.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.dao.HotelRepository;
import com.hotel.entities.Hotel;
import com.hotel.exceptions.ResourceNotFoundException;
import com.hotel.service.HotelService;
@Service
public class HotelServiceImpl implements HotelService{
	
	@Autowired
	private HotelRepository hotelRepository;

	@Override
	public Hotel saveHotel(Hotel hotel) {
		//to generate unique hotel id
				String randomId=UUID.randomUUID().toString();
				hotel.setId(randomId);
		return this.hotelRepository.save(hotel);
	}

	@Override
	public List<Hotel> getAllHotel() {
		return this.hotelRepository.findAll();
		}

	@Override
	public Hotel getHotel(String id) {
		return this.hotelRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Hotel with "+id+"not found !"));

	}
	

}
