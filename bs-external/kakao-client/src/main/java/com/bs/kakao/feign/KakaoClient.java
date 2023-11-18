package com.bs.kakao.feign;

import com.bs.kakao.KakaoSearchResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "kakaoClient", url = "${external.kakao.url}", configuration = KakaoConfiguration.class)
public interface KakaoClient {
    @GetMapping("/v2/search/blog")
    KakaoSearchResponse search(@RequestParam("query") String query,
                               @RequestParam("sort") String sort,
                               @RequestParam("page") int page,
                               @RequestParam("size") int size);
}
