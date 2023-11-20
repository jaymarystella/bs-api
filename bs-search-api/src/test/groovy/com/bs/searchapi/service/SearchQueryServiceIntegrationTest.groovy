package com.bs.searchapi.service

import com.bs.searchapi.controller.response.PageResult
import com.bs.searchapi.controller.response.SearchResponse
import com.bs.searchapi.repository.KakaoSearchRepository
import com.bs.searchapi.repository.NaverSearchRepository
import io.github.resilience4j.circuitbreaker.CircuitBreaker
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@ActiveProfiles("test")
@SpringBootTest
class SearchQueryServiceIntegrationTest extends Specification {
    @Autowired
    SearchQueryService searchQueryService

    @Autowired
    CircuitBreakerRegistry circuitBreakerRegistry

    @SpringBean
    KakaoSearchRepository kakaoSearchRepository = Mock(KakaoSearchRepository)

    @SpringBean
    NaverSearchRepository naverSearchRepository = Mock(NaverSearchRepository)

    def "circuit이 Open되면 Naver쪽으로 요청을 한다."() {
        given:
        def keyword = "치킨"
        def sort = "accuracy"
        def page = 1
        def size = 10
        def givenNaverFallbackResponse = new PageResult<>([], 1, 10, 1)

        kakaoSearchRepository.search(keyword, sort, page, size) >> { throw new RuntimeException("Circuit breaker opened") }

        when:
        PageResult<SearchResponse> result = searchQueryService.search(keyword, sort, page, size)

        then: "Naver쪽으로 fallback으로 요청한다."
        1 * naverSearchRepository.search(keyword, sort, page, size) >> givenNaverFallbackResponse

        and: "circuit이 오픈된다."
        circuitBreakerRegistry.getAllCircuitBreakers().get(0).state == CircuitBreaker.State.OPEN

        and: "Naver쪽에서 반환한 응답 반환"
        result == givenNaverFallbackResponse
    }
}
