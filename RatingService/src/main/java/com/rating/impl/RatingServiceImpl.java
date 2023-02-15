package com.rating.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rating.dao.RatingRepository;
import com.rating.entities.Rating;
import com.rating.exceptions.ResourceNotFoundException;
import com.rating.service.RatingService;
@Service
public class RatingServiceImpl implements RatingService{
     
	@Autowired
	private RatingRepository ratingRepository;
	@Override
	public Rating saveRating(Rating rating) {
		//to generate unique ratingId
		String randomRatingId=UUID.randomUUID().toString();
		rating.setRatingId(randomRatingId);
		return this.ratingRepository.save(rating);
	}

	@Override
	public List<Rating> getAllRating() {
		return this.ratingRepository.findAll();
	}

	@Override
	public Rating getRating(String ratingId) {
		return this.ratingRepository.findById(ratingId).orElseThrow(()->new ResourceNotFoundException("Rating with "+ratingId+"not found !"));

	}

	@Override
	public List<Rating> getRatingByUserId(String userId) {
		return this.ratingRepository.findByUserId(userId);

	}

	@Override
	public List<Rating> getRatingByHotelId(String hotelId) {
		return this.ratingRepository.findByHotelId(hotelId);

	}

}
