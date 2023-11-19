package com.bs.searchapi.controller;

import com.bs.searchapi.controller.response.PageResult;
import com.bs.searchapi.controller.response.SearchResponse;
import com.bs.searchapi.service.SearchQueryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/search")
public class SearchController {
    private final SearchQueryService searchQueryService;

    public SearchController(SearchQueryService searchQueryService) {
        this.searchQueryService = searchQueryService;
    }

    @GetMapping
    public PageResult<SearchResponse> search(@RequestParam("keyword") String keyword,
                                             @RequestParam("sort") String sort,
                                             @RequestParam("page") int page,
                                             @RequestParam("size") int size) {
        return searchQueryService.search(keyword, sort, page, size);
    }
}
