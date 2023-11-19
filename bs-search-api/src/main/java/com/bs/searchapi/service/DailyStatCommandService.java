package com.bs.searchapi.service;

import com.bs.searchapi.entity.DailyStat;
import com.bs.searchapi.repository.DailyStatRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class DailyStatCommandService {
    private final DailyStatRepository dailyStatRepository;

    public DailyStatCommandService(DailyStatRepository dailyStatRepository) {
        this.dailyStatRepository = dailyStatRepository;
    }

    @Transactional
    public void save(DailyStat dailyStat) {
        log.info("save daily stat: {}", dailyStat);
        dailyStatRepository.save(dailyStat);
    }
}
