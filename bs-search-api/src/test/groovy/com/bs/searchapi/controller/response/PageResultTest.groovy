package com.bs.searchapi.controller.response

import spock.lang.Specification

class PageResultTest extends Specification {
    def "create"() {
        given:
        def searchResponse1 = Mock(SearchResponse)
        def searchResponse2 = Mock(SearchResponse)

        def givenPage = 1
        def givenPageSize = 10
        def givenTotalElements = 2

        when:
        def result = new PageResult<>([searchResponse1, searchResponse2], givenPage, givenPageSize, givenTotalElements)

        then:
        verifyAll(result) {
            contents.size() == 2
            page == givenPage
            size == givenPageSize
            totalElements == givenTotalElements
        }
    }
}
