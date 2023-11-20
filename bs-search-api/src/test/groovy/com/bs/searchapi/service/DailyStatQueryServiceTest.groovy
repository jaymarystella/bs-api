package com.bs.searchapi.service

import com.bs.searchapi.repository.DailyStatRepository
import org.springframework.data.domain.Pageable
import spock.lang.Specification
import spock.lang.Subject

class DailyStatQueryServiceTest extends Specification {
    @Subject
    DailyStatQueryService dailyStatQueryService

    DailyStatRepository dailyStatRepository = Mock(DailyStatRepository)

    void setup() {
        dailyStatQueryService = new DailyStatQueryService(dailyStatRepository)
    }

    def "searchStat 조회시 상위 10개조회를 위한 page쿼리가 수행된다."() {
        when:
        dailyStatQueryService.searchStat()

        then:
        1 * dailyStatRepository.findTopKeywords(*_) >> { Pageable pageable ->
            pageable.pageSize == 10
            pageable.pageNumber == 0

            []
        }
    }
}
