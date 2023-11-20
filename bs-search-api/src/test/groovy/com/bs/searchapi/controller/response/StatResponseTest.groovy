package com.bs.searchapi.controller.response

import spock.lang.Specification
import spock.lang.Subject

class StatResponseTest extends Specification {
    @Subject
    StatResponse statResponse

    def "create"() {
        given:
        def givenKeyword = "치킨"
        def givenCount = 10

        when:
        def result = new StatResponse(givenKeyword, givenCount)

        then:
        verifyAll(result) {
            keyword == givenKeyword
            count == givenCount
        }
    }
}
