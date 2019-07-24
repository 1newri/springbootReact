package com.modern.be.common.auth;

import java.util.Collection;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationToken {

	private String username;
	private Collection authorities;
	private String token;
	
}
