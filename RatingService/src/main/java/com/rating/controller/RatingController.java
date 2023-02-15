package com.rating.controller;

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

import com.rating.entities.Rating;
import com.rating.service.RatingService;


@RestController
@RequestMapping("/ratings")
public class RatingController {
	@Autowired 
	private RatingService ratingService;
	
	//post rating data
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping
	public ResponseEntity<Rating> postRating(@RequestBody Rating rating){
		Rating r1=this.ratingService.saveRating(rating);
		return ResponseEntity.status(HttpStatus.CREATED).body(r1);
	}
	
	//get single rating 
	@PreAuthorize("hasAuthority('SCOPE_internal')")
	@GetMapping("/{ratingId}")
	public ResponseEntity<Rating> getSingleRating(@PathVariable String ratingId){
		Rating rating=this.ratingService.getRating(ratingId);
		return ResponseEntity.ok(rating);
	}
	
	//get all rating
	@GetMapping
	public ResponseEntity<List<Rating>> getAllrating(){
		List<Rating> allRating=ratingService.getAllRating();
		return ResponseEntity.ok(allRating);
	}
	
	//get single rating 
	@PreAuthorize("hasAuthority('SCOPE_internal')")
		@GetMapping("/users/{userId}")
		public ResponseEntity<List<Rating>> getSingleRatingByUserId(@PathVariable String userId){
			return ResponseEntity.ok(this.ratingService.getRatingByUserId(userId));
		}
		//get single rating 
	@PreAuthorize("hasAuthority('SCOPE_internal')")
		@GetMapping("/hotels/{hotelId}")
		public ResponseEntity<List<Rating>> getSingleRatingByHotelId(@PathVariable String hotelId){
			return ResponseEntity.ok(this.ratingService.getRatingByHotelId(hotelId));
		}

}
