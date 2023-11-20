package com.bs.searchapi.service


import com.bs.searchapi.repository.KakaoSearchRepository
import com.bs.searchapi.repository.NaverSearchRepository
import spock.lang.Specification
import spock.lang.Subject

class SearchQueryServiceTest extends Specification {
    @Subject
    SearchQueryService searchQueryService

    KakaoSearchRepository kakaoSearchRepository = Mock(KakaoSearchRepository)
    NaverSearchRepository naverSearchRepository = Mock(NaverSearchRepository)

    void setup() {
        searchQueryService = new SearchQueryService(kakaoSearchRepository, naverSearchRepository)
    }

    def "search - 기본적으로 Kakao쪽으로 검색한다."() {
        given:
        def givenKeyword = "치킨"
        def givenSort = "accuracy"
        def givenPage = 1
        def givenSize = 10

        when:
        searchQueryService.search(givenKeyword, givenSort, givenPage, givenSize)

        then: "searchRepository의 search 메소드가 들어온 인자 그대로 kakaoRepository로 호출되어야 한다."
        1 * kakaoSearchRepository.search(*_) >> {
            String keyword, String sort, int page, int size ->
                assert keyword == givenKeyword
                assert sort == givenSort
                assert page == givenPage
                assert size == givenSize
        }

        and: "Naver쪽은 호출되지 않는다."
        0 * naverSearchRepository.search(*_)
    }
}
