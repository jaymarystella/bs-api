package com.bs.searchapi.controller;

import com.bs.searchapi.controller.request.SearchRequest;
import com.bs.searchapi.controller.response.PageResult;
import com.bs.searchapi.controller.response.SearchResponse;
import com.bs.searchapi.service.SearchQueryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/search")
public class SearchController {
    private final SearchQueryService searchQueryService;

    public SearchController(SearchQueryService searchQueryService) {
        this.searchQueryService = searchQueryService;
    }

    @GetMapping
    public PageResult<SearchResponse> search(@Valid SearchRequest request) {
        return searchQueryService.search(request.getKeyword(), request.getSort().name(), request.getPage(), request.getSize());
    }
}
