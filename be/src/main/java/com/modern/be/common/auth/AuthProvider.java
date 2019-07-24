package com.modern.be.common.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.modern.be.user.entity.User;
import com.modern.be.user.entity.UserDto;
import com.modern.be.user.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AuthProvider implements AuthenticationProvider{
	
	@Autowired
	UserService service;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String userId = authentication.getName();
		String userPw = authentication.getCredentials().toString();
		
		return authenticate(userId, userPw);
	}
	
	private Authentication authenticate(String id, String pw) throws AuthenticationException{
		
		User user = User.builder()
						.userId(id)
						.userPw(pw)
						.build();
		
		UserDto userDto = service.findByUserId(id);
		if(userDto == null || !userDto.getUserPw().equals(pw)) {
			log.error("{} is not exist or password is not equals", id);
			return null;
		}
		
		List<GrantedAuthority> authList = new ArrayList<>();
		
		authList.add(new SimpleGrantedAuthority("ROLE_USER"));
		return new MyAuthentication(id, pw, authList, user);

	}
		

	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}
	
}
