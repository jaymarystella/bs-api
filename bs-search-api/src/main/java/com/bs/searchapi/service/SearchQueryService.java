package com.bs.searchapi.service;

import com.bs.searchapi.controller.response.PageResult;
import com.bs.searchapi.controller.response.SearchResponse;
import com.bs.searchapi.repository.SearchRepository;
import org.springframework.stereotype.Service;

@Service
public class SearchQueryService {
    private final SearchRepository searchRepository;

    public SearchQueryService(SearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }

    public PageResult<SearchResponse> search(String keyword,
                                             String sort,
                                             int page,
                                             int size) {
        return searchRepository.search(keyword, sort, page, size);
    }
}
