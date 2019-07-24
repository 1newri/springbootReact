package com.modern.be.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.modern.be.common.auth.AuthProvider;
import com.modern.be.user.entity.User;
import com.modern.be.user.entity.UserDto;
import com.modern.be.user.repository.UserJpaRepo;
import com.modern.be.user.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserJpaRepo userJpaRepo;
	
	@Autowired
	AuthProvider authProvider;
	
	@Override
	public String userJoin(User user) {
		
		String result = "";
		
		String rawPassword = user.getUserPw();
		String encodePassword = new BCryptPasswordEncoder().encode(rawPassword);
		
		user.setUserPw(encodePassword);
		
		// 회원가입
		User joinUser = User.builder().build();
		try {
			joinUser = userJpaRepo.save(user);
			
			if(joinUser != null) {
				result = "success";
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = "fail";
		}
		
		return result;
	}

	@Override
	public UserDto findByUserId(String id) {
		return userJpaRepo.findByUserId(id);
	}

	@Override
	public String userLogin(User user) {
		
		UserDto userDto = this.findByUserId(user.getUserId());
		
		if(userDto != null) {
			String userPw = userDto.getUserPw();
		}
		
		return null;
	}
}
