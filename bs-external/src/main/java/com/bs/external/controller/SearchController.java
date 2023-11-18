package com.bs.external.controller;

import com.bs.kakao.KakaoSearchResponse;
import com.bs.kakao.feign.KakaoClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// FIXME Test Controller
@RestController
@RequestMapping("/v1/search")
public class SearchController {
    private final KakaoClient kakaoClient;

    public SearchController(KakaoClient kakaoClient) {
        this.kakaoClient = kakaoClient;
    }

    @GetMapping
    public KakaoSearchResponse search(@RequestParam("keyword") String keyword,
                                      @RequestParam("sort") String sort,
                                      @RequestParam("page") int page,
                                      @RequestParam("size") int size) {
        KakaoSearchResponse response = kakaoClient.search(keyword, sort, page, size);
        return response;
    }
}
