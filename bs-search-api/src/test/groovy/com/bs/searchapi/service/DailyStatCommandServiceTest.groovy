package com.bs.searchapi.service

import com.bs.searchapi.entity.DailyStat
import com.bs.searchapi.repository.DailyStatRepository
import spock.lang.Specification
import spock.lang.Subject

import java.time.LocalDateTime

class DailyStatCommandServiceTest extends Specification {
    @Subject
    DailyStatCommandService dailyStatCommandService

    DailyStatRepository dailyStatRepository = Mock(DailyStatRepository)

    void setup() {
        dailyStatCommandService = new DailyStatCommandService(dailyStatRepository)
    }

    def "save"() {
        given:
        DailyStat givenDailyStat = new DailyStat(keyword: "치킨", eventDateTime: LocalDateTime.of(2023, 11, 19, 0, 0, 0))

        when:
        dailyStatCommandService.save(givenDailyStat)

        then: "dailyStatRepository의 save 메소드가 들어온 인자 그대로 호출되어야 한다."
        1 * dailyStatRepository.save(*_) >> { DailyStat dailyStat ->
            assert dailyStat.keyword == givenDailyStat.keyword
            assert dailyStat.eventDateTime == givenDailyStat.eventDateTime
        }
    }
}
