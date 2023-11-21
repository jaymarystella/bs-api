package com.bs.searchapi.controller.request

import spock.lang.Specification
import spock.lang.Subject

import javax.validation.Validation
import javax.validation.Validator

class SearchRequestTest extends Specification {
    @Subject
    SearchRequest searchRequest

    Validator validator = Validation.buildDefaultValidatorFactory().validator

    def "searchRequest 유효성 검증"() {
        given:
        SearchRequest request = new SearchRequest()
        request.setInput(keyword)
        request.setSort(sort)
        request.setPage(page)
        request.setSize(size)

        when:
        def result = validator.validate(request)

        then:
        result.isEmpty() == expected

        where:
        keyword       | sort              | page | size | expected
        "testKeyword" | SortType.ACCURACY | 1    | 10   | true
        ""            | SortType.ACCURACY | 1    | 20   | false
        null          | SortType.RECENCY  | -1   | 5    | false
        "keyword"     | SortType.RECENCY  | 0    | 30   | false
        "keyword"     | SortType.RECENCY  | 51   | -1   | false
    }
}