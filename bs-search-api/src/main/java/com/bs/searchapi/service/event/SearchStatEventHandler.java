package com.bs.searchapi.service.event;

import com.bs.searchapi.entity.DailyStat;
import com.bs.searchapi.service.DailyStatCommandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class SearchStatEventHandler {
    private final DailyStatCommandService dailyStatCommandService;

    public SearchStatEventHandler(DailyStatCommandService dailyStatCommandService) {
        this.dailyStatCommandService = dailyStatCommandService;
    }

    @Async
    @EventListener
    public void handleEvent(SearchStatEvent event) {
        log.info("[SearchStatEventHandler] handleEvent: {}", event);
        DailyStat dailyStat = new DailyStat(event.getKeyword(), LocalDateTime.now());
        dailyStatCommandService.save(dailyStat);
    }
}
