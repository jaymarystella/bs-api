package com.bs.searchapi.service;

import com.bs.searchapi.controller.request.SortType;
import com.bs.searchapi.controller.response.PageResult;
import com.bs.searchapi.controller.response.SearchResponse;
import com.bs.searchapi.repository.KakaoSearchRepository;
import com.bs.searchapi.repository.NaverSearchRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SearchQueryService {
    private final KakaoSearchRepository kakaoSearchRepository;
    private final NaverSearchRepository naverSearchRepository;

    public SearchQueryService(KakaoSearchRepository kakaoSearchRepository,
                              NaverSearchRepository naverSearchRepository) {
        this.kakaoSearchRepository = kakaoSearchRepository;
        this.naverSearchRepository = naverSearchRepository;
    }

    @CircuitBreaker(name = "kakaoSearch", fallbackMethod = "fallbackToNaverSearch")
    public PageResult<SearchResponse> search(String keyword,
                                             SortType sortType,
                                             int page,
                                             int size) {
        log.info("[SearchQueryService] kakao search keyword: {}, sort: {}, page: {}, pageSize: {}", keyword, sortType, page, size);
        return kakaoSearchRepository.search(keyword, sortType.getKakaoParam(), page, size);
    }

    public PageResult<SearchResponse> fallbackToNaverSearch(String keyword,
                                                            SortType sortType,
                                                            int page,
                                                            int size,
                                                            Throwable t) {
        log.error("Kakao search circuit opened!!! Fallback to Naver search. errorMessage: {}", t.getMessage());
        return naverSearchRepository.search(keyword, sortType.getNaverParam(), page, size);
    }
}
