package com.modern.be.common.auth;

import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import com.modern.be.user.entity.User;

public class MyAuthentication extends UsernamePasswordAuthenticationToken {
	
	private static final long serialVersionUID = 1L;
	
	User user;
	
	public MyAuthentication(String userId, String password, List<GrantedAuthority> authList, User user) {
		super(userId, password, authList);
		this.user = user;
	}

}
