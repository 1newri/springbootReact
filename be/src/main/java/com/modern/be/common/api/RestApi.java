package com.modern.be.common.api;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ConfigurationProperties("restapi.appkey")
public class RestApi {
	
	private String kakao;
	private String naverId;
	private String naverSecret;
}
