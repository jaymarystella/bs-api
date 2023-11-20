package com.bs.searchapi.repository;

import com.bs.naver.Item;
import com.bs.naver.NaverSearchResponse;
import com.bs.naver.feign.NaverClient;
import com.bs.searchapi.controller.response.PageResult;
import com.bs.searchapi.controller.response.SearchResponse;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static com.bs.common.CommonUtils.filter;

@Repository
public class NaverSearchRepository implements SearchRepository {
    private final NaverClient naverClient;

    public NaverSearchRepository(NaverClient naverClient) {
        this.naverClient = naverClient;
    }

    @Override
    public PageResult<SearchResponse> search(String keyword,
                                             String sort,
                                             int page,
                                             int size) {
        NaverSearchResponse response = naverClient.search(keyword, sort, page, size);
        List<SearchResponse> responses = response.getItems().stream()
                .map(this::createSearchResponse)
                .collect(Collectors.toList());
        return new PageResult<>(responses, page, size, response.getTotal());
    }

    private SearchResponse createSearchResponse(Item item) {
        return SearchResponse.builder()
                .title(filter(item.getTitle()))
                .url(item.getLink())
                .thumbnail("Temporary Unavailable") // TODO 썸네일 정보 제공하지 않음. 기획을 어떻게 풀것인지 고민 필요
                .summary(filter(item.getDescription()))
                .issuedAt(item.getPostDate().atStartOfDay()) // TODO 시각 정보 제공하지 않음. 기획을 어떻게 풀것인지 고민 필요
                .build();
    }
}
