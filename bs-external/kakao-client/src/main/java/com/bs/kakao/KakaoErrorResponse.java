package com.bs.kakao;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class KakaoErrorResponse {
    private final String errorType;
    private final String message;

    public KakaoErrorResponse(String errorType,
                              String message) {
        this.errorType = errorType;
        this.message = message;
    }
}
