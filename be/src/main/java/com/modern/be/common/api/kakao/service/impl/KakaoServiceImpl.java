package com.modern.be.common.api.kakao.service.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.modern.be.common.api.SnsProfileDto;
import com.modern.be.common.api.kakao.service.KakaoService;
import com.modern.be.common.util.UtilHttp;
import com.modern.be.common.util.UtilNull;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KakaoServiceImpl implements KakaoService{
	
//	@Value("#{sns.kakao.restapi.key}")
	private String kakaoAppRestKey = "2a4e43124da882847e76d8f8c3d741f1";
	

//	@Value("#{app.url}")
	private String appUrl = "http://localhost:8090";  //last slash remove
	
	@Override
	public String loginOauthUrl() {

		String kakaoApi = "https://kauth.kakao.com/oauth/authorize"; 
		Map<String, String> p = new HashMap<String, String>(); 
		p.put("client_id", kakaoAppRestKey); 
		p.put("redirect_uri", getKakaoRedirectUri());
		p.put("response_type", "code"); 
		
		String fullUrl = UtilHttp.buildUrl(kakaoApi, p); 
		
		log.debug("카카오로그인 코드받기 인증시작 url:{}", fullUrl);
		
		return fullUrl;
	}

	/**
	 * 카카오 인증과정을 시작할 리다이렉트 uri를 리턴함. 
	 * @return
	 */
	private String getKakaoRedirectUri() {
		
		return appUrl + "/sns/kakao/oauth";
	}

	@Override
	public String userAccessTokenWithCode(String codeAuthorized) {
		
		String kakaoApi = "https://kauth.kakao.com/oauth/token"; 
		Map<String, String> p = new HashMap<String, String>();
		p.put("grant_type", "authorization_code"); //고정 
		p.put("client_id", kakaoAppRestKey); 
		p.put("redirect_uri", getKakaoRedirectUri()); 
		p.put("code", codeAuthorized); 
		
		log.debug("==카카오 사용자토큰 받기, url:{}, 파라미터:{}", kakaoApi, p);
		Map<String, Object> re = UtilHttp.postMap(kakaoApi, p); 
		
		log.debug("==카카오 사용자토큰 받기 결과:{}", re);
		
		String accessToken = (String) re.get("access_token");   //사용자 토큰
		
		return accessToken;
	}

	@Override
	public Map<String, Object> searchBook(String userAccessToken) {
		String kakaoApi = "https://dapi.kakao.com/v3/search/book";
		
		Map<String, String> p = new HashMap<String, String>();
		p.put("Authorization", "KakaoAK " + userAccessToken); 
		
		Map<String, Object> re = UtilHttp.postHeader(kakaoApi, p); 
		log.debug("카카오사용자정보:{}", re);
		
		return re;
	}
	

	@Override
	public SnsProfileDto bookInfo(String userAccessToken) {

		Map<String, Object> re = searchBook(userAccessToken); 
		
		
		SnsProfileDto profile = convertUserInfo(re);
		if ( null == profile )   return profile; 
		
		profile.setUserAccessToken(userAccessToken); 
		
		return profile;
	}

	/**
	 * 회원정보 Map을 Dto로 변환처리함. 
	 * @param re
	 * @return
	 */
	private SnsProfileDto convertUserInfo(Map<String, Object> re) {
		if (  null == re )   return null; 
		
		Object id = re.get("id"); 
		
		String snsId = id.toString(); // 00:정상
		if (UtilNull.isEmpty(snsId)  ) {
			log.warn("카카오 회원번호가 존재하지 않습니다."); 
			return null; 
		}

		SnsProfileDto p = SnsProfileDto.builder().
						snsType("kako")
						.snsIdSeq(snsId)
						.build();
		
		@SuppressWarnings("unchecked")
		LinkedHashMap<String, Object> res = (LinkedHashMap<String, Object>) re.get("properties");
		p.setNickName((String)res.get("nickname"));
 
		
		log.info("==== kakao_account:{}", re.get("kakao_account") );
		
		@SuppressWarnings("unchecked")
		Map<String, Object> account = (Map<String, Object>) re.get("kakao_account");
		log.info("==카카오회원정보 상세:{}", account);   //카카오는 이름이 없는듯...
		log.warn("==카카오 회원정보에는 이름, 나이대역,성별 정보가 없는듯함....");
		
		p.setEmail( (String) account.get("email"));	//이메일
		
		
		//이메일 인증여부 확인
		boolean emailVerified = (Boolean) account.get("is_email_verified");
		p.setEmailVerified(emailVerified);
		
		
		
		return p;
	}

	@Override
	public Map<String, Object> bookList(String userAccessToken) {

		String kakaoApi = "https://dapi.kakao.com/v3/search/book";
		
		Map<String, String> p = new HashMap<String, String>();
		p.put("Authorization", "KakaoAK " + userAccessToken); 
		
		Map<String, Object> re = UtilHttp.postHeader(kakaoApi, p); 
		log.info("카카오사용자친구:{}", re);
		
		return re;
	}


}
