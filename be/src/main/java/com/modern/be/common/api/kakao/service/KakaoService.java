package com.modern.be.common.api.kakao.service;

import java.util.Map;

import com.modern.be.common.api.SnsProfileDto;

public interface KakaoService {

	/**
	 * 카카오 로그인 인증을 시작하기 위한 url을 리턴함. 
	 * @return
	 */
	String loginOauthUrl();

	/**
	 * 로그인 인증후 전달받은 code로 사용자 토큰을 구함.
	 * @param code
	 * @return
	 */
	String userAccessTokenWithCode(String code);

	/**
	 * accessToken을 이용하여 사용자정보(프로필)을 가져옴. 
	 * @param userAccessToken
	 * @return
	 */
	Map<String, Object> searchBook(String userAccessToken);
	
	/**
	 * 액세스 토컨을 이용하여 사용자 프로필을 가져옴. 
	 * @param userAccessToken
	 * @return
	 */
	SnsProfileDto bookInfo(String userAccessToken);

	/**
	 * 사용자 토큰을 기반으로 친구목록을 리턴함. 
	 * @param userAccessToken
	 * @return
	 */
	Map<String, Object> bookList(String userAccessToken);


	
}
