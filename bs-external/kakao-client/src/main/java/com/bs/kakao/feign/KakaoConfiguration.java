package com.bs.kakao.feign;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import static com.bs.common.ClientConstants.AUTHORIZATION;

public class KakaoConfiguration {

    @Bean
    public RequestInterceptor requestInterceptor(@Value("${external.headers.key}") String key) {
        return requestTemplate -> requestTemplate.header(AUTHORIZATION, "KakaoAK " + key);
    }

    @Bean
    public KakaoErrorDecoder kakaoErrorDecoder(ObjectMapper objectMapper) {
        return new KakaoErrorDecoder(objectMapper);
    }
}
