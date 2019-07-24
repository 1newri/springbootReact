package com.modern.be.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.modern.be.user.entity.User;
import com.modern.be.user.entity.UserDto;

@Repository
public interface UserJpaRepo extends JpaRepository<User, Long>{

	UserDto findByUserId(String id);
	
}
