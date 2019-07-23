package com.modern.be.common.api.naver.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.modern.be.common.api.naver.service.NaverService;
import com.modern.be.common.util.UtilHttp;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class NaverServiceImpl implements NaverService {
	
	@Value("${restapi.appkey.naver-id}")
	private String naverClientId;
	
	@Value("${restapi.appkey.naver-secret}")
	private String naverClientSecret;

	@Override
	public Map<String, Object> searchBook(Map<String, String> queryParam) {
		
		String naverApi = "https://openapi.naver.com/v1/search/book.json";
		String url = UtilHttp.buildUrl(naverApi, queryParam);
		
		Map<String, String> p = new HashMap<String, String>();
		p.put("X-Naver-Client-Id", naverClientId); 
		p.put("X-Naver-Client-Secret", naverClientSecret); 
		
		Map<String, Object> re = UtilHttp.getHeader(url, p);
		
		//TODO 호출 에러시 에러코드 처리!
		
		return re;
	}
	
}
