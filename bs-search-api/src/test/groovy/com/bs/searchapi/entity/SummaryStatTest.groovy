package com.bs.searchapi.entity

import spock.lang.Specification
import spock.lang.Subject

import java.time.LocalDateTime

class SummaryStatTest extends Specification {
    @Subject
    SummaryStat summaryStat

    def "create"() {
        given:
        def givenKeyword = "치킨"
        def givenUpdatedDateTime = LocalDateTime.of(2023, 11, 19, 0, 0, 0)

        when:
        def summaryStat = new SummaryStat(keyword: givenKeyword, updatedDateTime: givenUpdatedDateTime)

        then:
        verifyAll(summaryStat) {
            keyword == givenKeyword
            updatedDateTime == givenUpdatedDateTime
        }
    }
}
