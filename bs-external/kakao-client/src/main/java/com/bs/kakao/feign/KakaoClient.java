package com.bs.kakao.feign;

import com.bs.common.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "kakaoClient", url = "${external.kakao.url}", configuration = FeignConfiguration.class)
public interface KakaoClient {
    @GetMapping("/v2/search/blog")
    String searchBlog(@RequestParam("query") String query);
}
