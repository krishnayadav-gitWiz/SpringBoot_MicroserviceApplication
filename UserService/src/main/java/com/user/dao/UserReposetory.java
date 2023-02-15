package com.user.dao;



import org.springframework.data.jpa.repository.JpaRepository;

import com.user.entities.User;

public interface UserReposetory extends JpaRepository<User, String>{
	
	

}
