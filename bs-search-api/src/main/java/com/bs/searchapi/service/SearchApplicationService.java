package com.bs.searchapi.service;

import com.bs.searchapi.controller.response.PageResult;
import com.bs.searchapi.controller.response.SearchResponse;
import com.bs.searchapi.entity.DailyStat;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SearchApplicationService {
    private final SearchQueryService searchQueryService;
    private final DailyStatCommandService dailyStatCommandService;

    public SearchApplicationService(SearchQueryService searchQueryService,
                                    DailyStatCommandService dailyStatCommandService) {
        this.searchQueryService = searchQueryService;
        this.dailyStatCommandService = dailyStatCommandService;
    }

    public PageResult<SearchResponse> search(String keyword,
                                             String name,
                                             int page,
                                             int size) {
        PageResult<SearchResponse> response = searchQueryService.search(keyword, name, page, size);
        DailyStat dailyStat = new DailyStat(keyword, LocalDateTime.now());
        dailyStatCommandService.save(dailyStat);
        return response;
    }
}
