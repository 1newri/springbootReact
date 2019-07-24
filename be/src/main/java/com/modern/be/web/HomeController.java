package com.modern.be.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@CrossOrigin(origins = "http://localhost:8090")
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
}
