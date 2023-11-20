package com.bs.searchapi.controller.request;

import lombok.Getter;

@Getter
public enum SortType {
    ACCURACY("정확도순", "accuracy", "sim"), RECENCY("최신순", "recency", "date");

    private final String description;
    private final String kakaoParam;
    private final String naverParam;

    SortType(String description,
             String kakaoParam,
             String naverParam) {
        this.description = description;
        this.kakaoParam = kakaoParam;
        this.naverParam = naverParam;
    }
}
