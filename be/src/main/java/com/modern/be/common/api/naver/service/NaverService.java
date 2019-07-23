package com.modern.be.common.api.naver.service;

import java.util.Map;


public interface NaverService {


	/**
	 * 네이버 책 검색 APi 호출 
	 * @return
	 */
	Map<String, Object> searchBook(Map<String, String> queryParam);

}
