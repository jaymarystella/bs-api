package com.bs.naver;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NaverErrorResponse {
    private String errorMessage;
    private String errorCode;

    public NaverErrorResponse(String errorMessage,
                              String errorCode) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }
}
