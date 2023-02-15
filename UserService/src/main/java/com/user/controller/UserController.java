package com.user.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.entities.User;
import com.user.service.UserService;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/users")
public class UserController {
	  // Create a Logger
    private org.slf4j.Logger logger =  LoggerFactory.getLogger(UserController.class);
    
    
	@Autowired 
	private UserService userService;
	
	//post user data
	@PostMapping
	public ResponseEntity<User> postUser(@RequestBody User user){
		User u1=this.userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(u1);
	}
	
	
	int retryCount=1;
	
	//get single user 
	@GetMapping("/{userId}")
//	@CircuitBreaker(name = "ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
//	@Retry(name = "ratingHotelService",fallbackMethod = "ratingHotelFallback")
	@RateLimiter(name = "userRateLimiter",fallbackMethod = "ratingHotelFallback")
	public ResponseEntity<User> getSingleUser(@PathVariable String userId){
		logger.info("Get Single user Handler: UserController");
//		logger.info("retry count ;{} ",retryCount);
		logger.info("retry count ;{} ", retryCount);
		retryCount++;
		User user=this.userService.getUser(userId);
		return ResponseEntity.ok(user);
	}
	
	//creating fallback method for circuit breaker
	public ResponseEntity<User> ratingHotelFallback(String userId,Exception ex){
//		logger.info("Fallback is executed beacouse for some server is down : ",ex.getMessage());
		User user = User.builder().email("demo@gmail.com").name("Domo").about("this is just demo user bcoz some service is down right now!!")
		.userId("123").build();
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	//get all user
	@GetMapping
	public ResponseEntity<List<User>> getAllUser(){
		List<User> allUser=userService.getAllUser();
		return ResponseEntity.ok(allUser);
	}

}
