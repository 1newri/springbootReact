package com.modern.be.common.api.kakao.service;

import java.util.Map;

public interface KakaoService {

	/**
	 * 카카오 책 검색 APi 호출 
	 * @return
	 */
	Map<String, Object> searchBook(Map<String, String> queryParam);
	
}
