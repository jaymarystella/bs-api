package com.bs.kakao;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KakaoErrorResponse {
    private String errorType;
    private String message;

    public KakaoErrorResponse(String errorType,
                              String message) {
        this.errorType = errorType;
        this.message = message;
    }
}
