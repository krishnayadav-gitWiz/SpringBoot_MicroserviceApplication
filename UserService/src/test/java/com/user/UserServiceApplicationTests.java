package com.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.user.entities.Rating;
import com.user.external.services.RatingService;

@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}
//	@Autowired
//	private RatingService ratingService;

//	@Test
//	void createRating() {
//		Rating rating=Rating.builder().rating(4).userId("").hotelId("").feedback("this is creating using test class").build();
////		Rating saveRating=ratingService.postRating(rating);
//		ResponseEntity<Rating> saveRating=ratingService.postRating(rating);
//		
//		System.out.println("new rating created............");
//	}
}
