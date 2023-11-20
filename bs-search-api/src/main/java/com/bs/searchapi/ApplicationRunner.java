package com.bs.searchapi;

import com.bs.searchapi.entity.DailyStat;
import com.bs.searchapi.repository.DailyStatRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

/**
 * application 실행 시점에 테스트 데이터를 넣어주기 위한 클래스
 */
@Configuration
public class ApplicationRunner implements CommandLineRunner {
    private final DailyStatRepository dailyStatRepository;

    public ApplicationRunner(DailyStatRepository dailyStatRepository) {
        this.dailyStatRepository = dailyStatRepository;
    }

    @Override
    public void run(String... args) {
        DailyStat dailyStat1 = new DailyStat("교촌", LocalDateTime.now());
        DailyStat dailyStat2 = new DailyStat("교촌", LocalDateTime.now());
        DailyStat dailyStat3 = new DailyStat("교촌", LocalDateTime.now());
        DailyStat dailyStat4 = new DailyStat("네네", LocalDateTime.now());
        DailyStat dailyStat5 = new DailyStat("네네", LocalDateTime.now());
        DailyStat dailyStat6 = new DailyStat("굽네", LocalDateTime.now());
        dailyStatRepository.saveAll(List.of(dailyStat1, dailyStat2, dailyStat3, dailyStat4, dailyStat5, dailyStat6));
    }
}
