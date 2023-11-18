package com.bs.common;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import static com.bs.common.ClientConstants.AUTHORIZATION;

public class FeignConfiguration {

    @Bean
    public RequestInterceptor requestInterceptor(@Value("${external.headers.key}") String key) {
        return requestTemplate -> requestTemplate.header(AUTHORIZATION, "KakaoAK " + key);
    }
}
