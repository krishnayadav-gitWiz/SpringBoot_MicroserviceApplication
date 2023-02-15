package com.user.service;

import java.util.List;

import com.user.entities.User;

public interface UserService {
	
	//post user data
	User saveUser(User user);
	
	//get all user
	List<User> getAllUser();
	
	
	//get single user
	User getUser(String userId);
	
	//delete user
	
	
	//update user
	

}
