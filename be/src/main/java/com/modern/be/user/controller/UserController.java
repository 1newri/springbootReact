package com.modern.be.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.modern.be.user.entity.User;
import com.modern.be.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@CrossOrigin(origins = "http://localhost:8090")
	@PostMapping("/login")
	public String userLogin(
			HttpServletRequest request,
			@RequestBody User user,
			Model model
			) {
		
		String result = "";
		
		if(user != null) {
			result = userService.userLogin(user);
		}else {
			result = "fail";
		}
		
		return result; 
	}
	
	@CrossOrigin(origins = "http://localhost:8090")
	@PostMapping("/join")
	public String userJoin(
			HttpServletRequest request,
			@RequestBody User user,
			Model model
			) {

		String result = "";
		
		if(user != null) {
			result = userService.userJoin(user);
		}else {
			result = "fail";
		}
		
		return result; 
	}
	
}
