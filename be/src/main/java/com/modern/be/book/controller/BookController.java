package com.modern.be.book.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.modern.be.common.api.kakao.service.KakaoService;

@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
	KakaoService kakaoSerivce;
	
	@CrossOrigin(origins = "http://localhost:8090")
	@GetMapping("/search")
	public Map<String, Object> bookList(
			HttpServletRequest request,
			Model model){
		// 파라미터 받아오기..
		
		String keyword = request.getParameter("keyword");
		String sort = request.getParameter("keyword");
		String page = request.getParameter("keyword");
		String size = request.getParameter("keyword");
		String target = request.getParameter("keyword");
		
		Map<String, String> queryParam = new HashMap<String, String>();
		queryParam.put("query", keyword);
		
		Map<String, Object> map = kakaoSerivce.searchBook(queryParam);
		
		return map;
	}
}
