package com.bs.searchapi.repository;

import com.bs.kakao.Document;
import com.bs.kakao.KakaoSearchResponse;
import com.bs.kakao.feign.KakaoClient;
import com.bs.searchapi.controller.response.PageResult;
import com.bs.searchapi.controller.response.SearchResponse;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static com.bs.common.CommonUtils.filter;

@Repository
public class KakaoSearchRepository implements SearchRepository {
    private final KakaoClient kakaoClient;

    public KakaoSearchRepository(KakaoClient kakaoClient) {
        this.kakaoClient = kakaoClient;
    }

    @Override
    public PageResult<SearchResponse> search(String keyword,
                                             String sort,
                                             int page,
                                             int size) {
        KakaoSearchResponse search = kakaoClient.search(keyword, sort, page, size);
        List<SearchResponse> responses = search.getDocuments().stream()
                .map(this::createSearchResponse)
                .collect(Collectors.toList());
        return new PageResult<>(responses, page, size, search.getMeta().getTotalCount());
    }

    private SearchResponse createSearchResponse(Document document) {
        return SearchResponse.builder()
                .title(filter(document.getTitle()))
                .url(document.getUrl())
                .thumbnail(document.getThumbnail())
                .summary(filter(document.getContents()))
                .issuedAt(document.getDatetime())
                .build();
    }
}
