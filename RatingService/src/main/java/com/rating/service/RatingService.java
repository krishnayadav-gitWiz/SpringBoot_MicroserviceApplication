package com.rating.service;

import java.util.List;

import com.rating.entities.Rating;


public interface RatingService {
	//post rating data
		Rating saveRating(Rating rating);
		
		//get all Rating
		List<Rating> getAllRating();
		//get all Rating by user id
		List<Rating> getRatingByUserId(String userId);
		//get all Rating by hotel id
		List<Rating> getRatingByHotelId(String hotelId);
		
		//get single Rating
		Rating getRating(String ratingId);
		
		//delete rating
		
		
		//update rating
}
