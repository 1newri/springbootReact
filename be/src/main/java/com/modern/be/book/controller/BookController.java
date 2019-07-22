package com.modern.be.book.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.modern.be.common.api.kakao.service.KakaoService;

@RestController
public class BookController {

	@Autowired
	KakaoService kakaoSerivce;
	
	@GetMapping("/test")
	public Map<String, Object> bookList(Model model){
		Map<String, Object> result = new HashMap<String, Object>();
		
		result.put("msg", "Ffffffffffffffffffffffff");
		
		return result;
	}
}
