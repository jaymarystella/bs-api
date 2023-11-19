package com.bs.searchapi.repository;

import com.bs.searchapi.controller.response.PageResult;
import com.bs.searchapi.controller.response.SearchResponse;

public interface SearchRepository {
    PageResult<SearchResponse> search(String keyword, String sort, int page, int size);
}
