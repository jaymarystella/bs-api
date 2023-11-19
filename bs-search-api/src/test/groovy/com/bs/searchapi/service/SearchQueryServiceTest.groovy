package com.bs.searchapi.service

import com.bs.searchapi.repository.SearchRepository
import spock.lang.Specification
import spock.lang.Subject

class SearchQueryServiceTest extends Specification {
    @Subject
    SearchQueryService searchQueryService

    SearchRepository searchRepository = Mock(SearchRepository)

    void setup() {
        searchQueryService = new SearchQueryService(searchRepository)
    }

    def "search"() {
        given:
        def givenKeyword = "치킨"
        def givenSort = "accuracy"
        def givenPage = 1
        def givenSize = 10

        when:
        searchQueryService.search(givenKeyword, givenSort, givenPage, givenSize)

        then: "searchRepository의 search 메소드가 들어온 인자 그대로 호출되어야 한다."
        1 * searchRepository.search(*_) >> {
            String keyword, String sort, int page, int size ->
                assert keyword == givenKeyword
                assert sort == givenSort
                assert page == givenPage
                assert size == givenSize
        }
    }
}
