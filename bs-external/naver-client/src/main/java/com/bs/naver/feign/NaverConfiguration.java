package com.bs.naver.feign;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import static com.bs.common.ClientConstants.NAVER_CLIENT_ID_HEADER;
import static com.bs.common.ClientConstants.NAVER_CLIENT_SECRET_HEADER;

public class NaverConfiguration {
    @Bean
    public RequestInterceptor requestInterceptor(@Value("${external.naver.headers.client-id}") String clientId,
                                                 @Value("${external.naver.headers.client-secret}") String clientSecret) {
        return requestTemplate -> requestTemplate.header(NAVER_CLIENT_ID_HEADER, clientId)
                .header(NAVER_CLIENT_SECRET_HEADER, clientSecret);
    }

    @Bean
    public NaverErrorDecoder naverErrorDecoder(ObjectMapper objectMapper) {
        return new NaverErrorDecoder(objectMapper);
    }
}
