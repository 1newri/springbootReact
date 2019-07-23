package com.modern.be.common.api.kakao.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.modern.be.common.api.kakao.service.KakaoService;
import com.modern.be.common.util.UtilHttp;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KakaoServiceImpl implements KakaoService{
	
	@Value("${restapi.appkey.kakao}")
	private String kakaoAppRestKey;
	
	@Override
	public Map<String, Object> searchBook(Map<String, String> queryParam) {
		
		String kakaoApi = "https://dapi.kakao.com/v3/search/book";
		String url = UtilHttp.buildUrl(kakaoApi, queryParam);
		
		Map<String, String> p = new HashMap<String, String>();
		p.put("Authorization", "KakaoAK " + kakaoAppRestKey); 
		
		Map<String, Object> re = UtilHttp.getHeader(url, p);
		
		
		//TODO 호출 에러시 에러코드 처리!
		
		//TODO 카카오 api 호출 에러 -> 네이버 api 호출하기
		
		return re;
	}

}
