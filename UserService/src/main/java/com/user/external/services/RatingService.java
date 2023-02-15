package com.user.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.user.entities.Rating;
@Service
@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

	//get

	//post
	@PostMapping("/ratings")
	public ResponseEntity<Rating> postRating(Rating values);

	//update
	@PutMapping("/ratings/{ratingId}")
	public ResponseEntity<Rating> updateRating(@PathVariable("ratingId") String ratingId,Rating rating);

	//delete
	@DeleteMapping("/ratings/{ratingId}")
	public Rating deleteRating(@PathVariable("ratingId") String ratingId);

}
