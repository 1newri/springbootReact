package com.modern.be.common.api.naver.service.impl;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.modern.be.common.api.SnsProfileDto;
import com.modern.be.common.api.naver.service.NaverService;
import com.modern.be.common.util.UtilHttp;

import lombok.extern.slf4j.Slf4j;


/**
 * 
 * @author Administrator
 * https://songc92.tistory.com/41  
 * https://yoo-hyeok.tistory.com/88 
 */
@Service
@Slf4j
public class NaverServiceImpl implements NaverService {
	

//	@Value("#{prop['app.url']}")
	private String appUrl = "https://www.cybercoex.co.kr";  //last slash remove
	

//	@Value("#{prop['sns.naver.client.id']}")
	private String clientId = "M7qKuU9o2hyot_nwCalK";
	
//	@Value("#{prop['sns.naver.client.secret']}")
	private String clientSecret = "vrYNr2YnDE";
	

	@Override
	public String loginOauthUrl(String stateCode) {

		String naverApi = "https://nid.naver.com/oauth2.0/authorize"; 
		Map<String, String> p = new HashMap<String, String>(); 
		p.put("response_type", "code"); //fix 
		p.put("client_id", clientId); 
		p.put("redirect_uri", getNaverRedirectUri());
		p.put("state", stateCode);
		
		
		
		String fullUrl = UtilHttp.buildUrl(naverApi, p); 
		
		log.debug("네이버 로그인 인증시작 url:{}", fullUrl);
		
		return fullUrl;
	}


	/**
	 * 카카오 인증과정을 시작할 리다이렉트 uri를 리턴함. 
	 * @return
	 */
	private String getNaverRedirectUri() {
		String url = appUrl + "/sns/naver/oauth";
		return url;
	}

	@Override
	public String userAccessTokenWithCode(String codeAuthorized, String state) {
		
		String kakaoApi = "https://nid.naver.com/oauth2.0/token"; 
		Map<String, String> p = new HashMap<String, String>();
		p.put("grant_type", "authorization_code"); //고정 
		p.put("client_id", clientId); 
		p.put("client_secret", clientSecret); 
		
		p.put("redirect_uri", getNaverRedirectUri()); 
		p.put("code", codeAuthorized);  
		p.put("state", state); 
		
		//POST 방식으로 전달해야 하네... 
		//String fullUrl = UtilHttp.buildUrl(kakaoApi, p); 
		//log.info("POST방식으로 전달해야 하네...:{}", fullUrl);
		
		Map<String, Object> re = UtilHttp.getMap(kakaoApi, p); 
		
		//{access_token=_TJz7FjVOvcHGDrQCPzVWjVvrCSd9YFXy0iYQgo8BVUAAAFo5ii1iw, token_type=bearer, refresh_token=r-7hJL954P6e7jU6WWKGttVjcvoicEft1jcaZwo8BVUAAAFo5ii1iA, expires_in=21599, scope=account_email profile, refresh_token_expires_in=2591999}
		log.debug("네이버 UserAccessToken:{}", re);
		
		String accessToken = (String) re.get("access_token");   //사용자 토큰
		
		return accessToken;
	}

	@Override
	public Map<String, Object> userInfoMap(String userAccessToken) {
		
		String kakaoApi = "https://openapi.naver.com/v1/nid/me";
		
		Map<String, String> p = new HashMap<String, String>();
		p.put("Authorization", "Bearer " + userAccessToken); 
		
		
		Map<String, Object> re = UtilHttp.postHeader(kakaoApi, p);
		// {resultcode=00, message=success, response={id=25275368, age=40-49, gender=M, email=xxx@naver.com, name=xxxx, birthday=10-20}}
		log.info("네이버 사용자정보:{}", re);
		
		return re;
	}

	@Override
	public SnsProfileDto userInfo(String userAccessToken) {
		
		
		Map<String, Object> re = userInfoMap(userAccessToken); 
		
		
		SnsProfileDto profile = convertUserInfo(re);
		if ( null == profile )   return profile; 
		
		profile.setUserAccessToken(userAccessToken); 
		
		return profile;
	}

	/**
	 * 네이버 회원정보(MAP)을 DTO 객체로 변경함. 
	 * @param re
	 * @return
	 */
	private SnsProfileDto convertUserInfo(Map<String, Object> re) {
		
		if (  null == re )   return null; 
		
		// {resultcode=00, message=success, response={id=25275368, age=40-49, gender=M, email=xxx@naver.com, name=xxxx, birthday=10-20}}
		String resultCode = (String) re.get("resultcode"); // 00:정상
		if ( "00".equals(resultCode) == false ) {
			log.warn("네이버 응답값이 정상코드(00)가 아닙니다.:{}", re); 
			return null; 
		}
		
		
		@SuppressWarnings("unchecked")
		Map<String, String> res = (Map<String, String>) re.get("response"); 

		SnsProfileDto p = SnsProfileDto.builder()
							.snsType("naver")
							.snsIdSeq(res.get("id"))
							.ageRage(res.get("age"))
							.gender(res.get("gender"))
							.email(res.get("email"))
							.name(res.get("name"))
							.build();
		
		//네이버는 이메일 인증으로 설정함. 
		p.setEmailVerified(true);
		
		return p;
	}


	@Override
	public String getStateCode() {

		SecureRandom random = new SecureRandom(); 
		String state = new BigInteger(130, random).toString();
		
		return state; 
	}



	
}
