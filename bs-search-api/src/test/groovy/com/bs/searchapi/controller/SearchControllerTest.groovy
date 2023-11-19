package com.bs.searchapi.controller

import com.bs.searchapi.controller.response.PageResult
import com.bs.searchapi.service.SearchQueryService
import org.springframework.http.HttpStatus
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification
import spock.lang.Subject

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

class SearchControllerTest extends Specification {
    @Subject
    SearchController searchController

    SearchQueryService searchQueryService = Mock(SearchQueryService)
    MockMvc mockMvc

    def setup() {
        searchController = new SearchController(searchQueryService)
        mockMvc = MockMvcBuilders.standaloneSetup(searchController).build()
    }

    def "search"() {
        given:
        def givenKeyword = "치킨"
        def givenSort = "ACCURACY"
        def givenPage = 1
        def givenSize = 10

        when:
        MockHttpServletResponse response = mockMvc.perform(
                get("/v1/search?keyword=${givenKeyword}&sort=${givenSort}&page=${givenPage}&size=${givenSize}"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .response
        then:
        response.status == HttpStatus.OK.value()

        and:
        1 * searchQueryService.search(*_) >> { String keyword, String sort, int page, int size ->
            assert keyword == givenKeyword
            assert sort == givenSort
            assert page == givenPage
            assert size == givenSize

            new PageResult<>([], 1, 10, 1)
        }
    }
}
