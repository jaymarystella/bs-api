package com.bs.searchapi.service;

import com.bs.searchapi.controller.response.StatResponse;
import com.bs.searchapi.repository.DailyStatRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DailyStatQueryService {
    private static final int PAGE = 0;
    private static final int SIZE = 10;
    private final DailyStatRepository dailyStatRepository;

    public DailyStatQueryService(DailyStatRepository dailyStatRepository) {
        this.dailyStatRepository = dailyStatRepository;
    }

    public List<StatResponse> searchStat() {
        Pageable topTen = PageRequest.of(PAGE, SIZE);
        return dailyStatRepository.findTopKeywords(topTen);
    }
}
