package com.bs.searchapi.service;

import com.bs.searchapi.controller.request.SortType;
import com.bs.searchapi.controller.response.PageResult;
import com.bs.searchapi.controller.response.SearchResponse;
import com.bs.searchapi.controller.response.StatResponse;
import com.bs.searchapi.service.event.SearchStatEvent;
import com.bs.searchapi.util.KeywordParser;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SearchApplicationService {
    private final SearchQueryService searchQueryService;
    private final DailyStatQueryService dailyStatQueryService;
    private final ApplicationEventPublisher eventPublisher;

    public SearchApplicationService(SearchQueryService searchQueryService,
                                    DailyStatQueryService dailyStatQueryService,
                                    ApplicationEventPublisher eventPublisher) {
        this.searchQueryService = searchQueryService;
        this.dailyStatQueryService = dailyStatQueryService;
        this.eventPublisher = eventPublisher;
    }

    public PageResult<SearchResponse> search(String input,
                                             SortType sortType,
                                             int page,
                                             int size) {
        PageResult<SearchResponse> response = searchQueryService.search(input, sortType, page, size);

        List<String> keywords = KeywordParser.parseKeywords(input);
        keywords.stream()
                .map(keyword -> new SearchStatEvent(keyword, LocalDateTime.now()))
                .forEach(eventPublisher::publishEvent);

        return response;
    }

    public List<StatResponse> searchStat() {
        return dailyStatQueryService.searchStat();
    }
}
