package com.bs.naver.feign;

import com.bs.naver.NaverSearchResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "naverClient", url = "${external.naver.url}", configuration = NaverConfiguration.class)
public interface NaverClient {
    @GetMapping("v1/search/blog.json")
    NaverSearchResponse search(@RequestParam("query") String query,
                               @RequestParam("sort") String sort,
                               @RequestParam("display") int display,
                               @RequestParam("start") int start);
}
