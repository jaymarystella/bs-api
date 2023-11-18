package com.bs.kakao;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class KakaoSearchResponse {
    private Meta meta;
    private List<Document> documents;
}
