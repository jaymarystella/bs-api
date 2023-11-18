package com.bs.external.controller;

import com.bs.kakao.feign.KakaoClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {
    private final KakaoClient kakaoClient;

    public SearchController(KakaoClient kakaoClient) {
        this.kakaoClient = kakaoClient;
    }

    @GetMapping("/test")
    public String test() {
        // FIXME TEST call
        return kakaoClient.searchBlog("치킨");
    }
}
