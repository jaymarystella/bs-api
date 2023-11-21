package com.bs.searchapi.service

import com.bs.searchapi.controller.request.SortType
import com.bs.searchapi.controller.response.PageResult
import com.bs.searchapi.service.event.SearchStatEvent
import org.springframework.context.ApplicationEventPublisher
import spock.lang.Specification
import spock.lang.Subject

class SearchApplicationServiceTest extends Specification {
    @Subject
    SearchApplicationService searchApplicationService

    SearchQueryService searchQueryService = Mock(SearchQueryService)
    DailyStatQueryService dailyStatQueryService = Mock(DailyStatQueryService)
    ApplicationEventPublisher eventPublisher = Mock(ApplicationEventPublisher)

    void setup() {
        searchApplicationService = new SearchApplicationService(searchQueryService, dailyStatQueryService, eventPublisher)
    }

    def "search시 검색결과를 반환하면서 통계데이터를 저장한다."() {
        given:
        def givenKeyword = "치킨"
        def givenSortType = SortType.ACCURACY
        def givenPage = 1
        def givenSize = 10

        when:
        searchApplicationService.search(givenKeyword, givenSortType, givenPage, givenSize)

        then: "검색결과를 반환한다."
        1 * searchQueryService.search(*_) >> { String keyword, SortType sort, int page, int size ->
            assert keyword == givenKeyword
            assert sort == givenSortType
            assert page == givenPage
            assert size == givenSize
            new PageResult<>([], 1, 10, 1)
        }

        and: "저장 이벤트를 발행한다."
        1 * eventPublisher.publishEvent(_ as SearchStatEvent)
    }

    def "searchStat시 쿼리서비스를 통하여 조회한다."() {
        when:
        searchApplicationService.searchStat()

        then:
        1 * dailyStatQueryService.searchStat()
    }
}
