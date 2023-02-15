package com.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.entities.Hotel;
import com.hotel.service.HotelService;


	@RestController
	@RequestMapping("/hotels")
	public class HotelController {
		
		@Autowired 
		private HotelService hotelService;
		
		//post hotel data
		@PreAuthorize("hasAuthority('Admin')")
		@PostMapping
		public ResponseEntity<Hotel> postHotel(@RequestBody Hotel hotel){
			Hotel h1=this.hotelService.saveHotel(hotel);
			return ResponseEntity.status(HttpStatus.CREATED).body(h1);
		}
		
		//get single hotel 
		@PreAuthorize("hasAuthority('SCOPE_internal')")
		@GetMapping("/{id}")
		public ResponseEntity<Hotel> getSingleHotel(@PathVariable String id){
			Hotel hotel=this.hotelService.getHotel(id);
			return ResponseEntity.ok(hotel);
		}
		
		//get all hotel
		@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
		@GetMapping
		public ResponseEntity<List<Hotel>> getAllHotel(){
			List<Hotel> allHotel=hotelService.getAllHotel();
			return ResponseEntity.ok(allHotel);
		}
}
