package com.bs.kakao.feign;

import com.bs.common.exception.ApiException;
import com.bs.common.exception.ErrorType;
import com.bs.kakao.KakaoErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;

import java.io.IOException;

@Slf4j
public class KakaoErrorDecoder implements ErrorDecoder {
    private final ObjectMapper objectMapper;

    public KakaoErrorDecoder(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Exception decode(String methodKey,
                            Response response) {
        try {
            final KakaoErrorResponse errorResponse = objectMapper.readValue(IOUtils.toString(response.body().asInputStream()), KakaoErrorResponse.class);
            throw new ApiException(HttpStatus.valueOf(response.status()), ErrorType.EXTERNAL_API_ERROR, errorResponse.getMessage());
        } catch (IOException e) {
            log.error("[Kakao] 에러 메세지 파싱 에러 code={}, key={}, request={}, errorMessage={}", response.status(), methodKey, response.request(), e.getMessage());
            throw new ApiException(HttpStatus.valueOf(response.status()), ErrorType.EXTERNAL_API_ERROR, "카카오 API 호출에 실패했습니다.");
        }
    }
}
