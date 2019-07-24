package com.modern.be.user.service;

import com.modern.be.user.entity.User;
import com.modern.be.user.entity.UserDto;

public interface UserService {

	String userJoin(User user);

	UserDto findByUserId(String id);

	String userLogin(User user);

}
