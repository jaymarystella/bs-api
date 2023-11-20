package com.bs.searchapi.service

import com.bs.searchapi.controller.response.PageResult
import com.bs.searchapi.entity.DailyStat
import spock.lang.Specification
import spock.lang.Subject

class SearchApplicationServiceTest extends Specification {
    @Subject
    SearchApplicationService searchApplicationService

    SearchQueryService searchQueryService = Mock(SearchQueryService)
    DailyStatCommandService dailyStatCommandService = Mock(DailyStatCommandService)
    DailyStatQueryService dailyStatQueryService = Mock(DailyStatQueryService)

    void setup() {
        searchApplicationService = new SearchApplicationService(searchQueryService, dailyStatQueryService, dailyStatCommandService)
    }

    def "search시 검색결과를 반환하면서 통계데이터를 저장한다."() {
        given:
        def givenKeyword = "치킨"
        def givenSort = "ACCURACY"
        def givenPage = 1
        def givenSize = 10

        when:
        searchApplicationService.search(givenKeyword, givenSort, givenPage, givenSize)

        then: "검색결과를 반환한다."
        1 * searchQueryService.search(*_) >> { String keyword, String sort, int page, int size ->
            assert keyword == givenKeyword
            assert sort == givenSort
            assert page == givenPage
            assert size == givenSize
            new PageResult<>([], 1, 10, 1)
        }

        and: "통계데이터를 저장한다."
        1 * dailyStatCommandService.save(*_) >> { DailyStat dailyStat ->
            assert dailyStat.keyword == givenKeyword
        }
    }

    def "searchStat시 쿼리서비스를 통하여 조회한다."() {
        when:
        searchApplicationService.searchStat()

        then:
        1 * dailyStatQueryService.searchStat()
    }
}
