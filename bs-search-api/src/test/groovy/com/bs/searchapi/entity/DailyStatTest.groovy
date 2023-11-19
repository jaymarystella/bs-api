package com.bs.searchapi.entity

import spock.lang.Specification
import spock.lang.Subject

import java.time.LocalDateTime

class DailyStatTest extends Specification {
    @Subject
    DailyStat dailyStat

    def "create"() {
        given:
        def givenKeyword = "치킨"
        def givenEventDateTime = LocalDateTime.of(2023, 11, 19, 0, 0, 0)

        when:
        def result = new DailyStat(keyword: givenKeyword, eventDateTime: givenEventDateTime)

        then:
        verifyAll(result) {
            keyword == givenKeyword
            eventDateTime == givenEventDateTime
        }
    }
}
