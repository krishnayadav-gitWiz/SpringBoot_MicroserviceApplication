package com.user.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.dao.UserReposetory;
import com.user.entities.Hotel;
import com.user.entities.Rating;
import com.user.entities.User;
import com.user.exceptions.ResourceNotFoundException;
import com.user.external.services.HotelService;
import com.user.service.UserService;

import ch.qos.logback.classic.Logger;
@Service
public class UserServiceImpl implements UserService{
    @Autowired
	private UserReposetory userReposetory;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HotelService hotelService;
    
    private Logger logger=(Logger) LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public User saveUser(User user) {
		//to generate unique userId
		String randomUserId=UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return this.userReposetory.save(user);
	}

	@Override
	public List<User> getAllUser() {
		
		return this.userReposetory.findAll();
	}

	@Override
	public User getUser(String userId) {
//        return this.userReposetory.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with "+userId+"not found !"));

		User user= this.userReposetory.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with "+userId+"not found !"));
		//fetch rating for userID
		Rating[] ratingForUser=restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
		logger.info("{}",ratingForUser);
		
		List<Rating> ratings = Arrays.stream(ratingForUser).toList();
		
		//hotel details
		List<Rating> ratingList=ratings.stream().map(rating->{
			//api call to hotel service to get hotel details
//			ResponseEntity<Hotel> forEntity= restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
//			Hotel hotel=forEntity.getBody();

			
			//Declarative approach
			Hotel hotel=hotelService.getHotel(rating.getHotelId());
//			logger.info("response status code : {}",forEntity.getStatusCode());
			//set hotel to rating
			rating.setHotel(hotel);
//			return rating
			return rating;
		}).collect(Collectors.toList());
		
		user.setRatings(ratingList);
		return user;
	}

}
