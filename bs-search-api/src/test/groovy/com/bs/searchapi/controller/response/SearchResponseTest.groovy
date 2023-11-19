package com.bs.searchapi.controller.response

import spock.lang.Specification
import spock.lang.Subject

import java.time.LocalDateTime

class SearchResponseTest extends Specification {
    @Subject
    SearchResponse searchResponse

    def "create"() {
        given:
        def givenTitle = "치킨이 좋아"
        def givenSummary = "이 세상에서 맛있는 치킨집은 너무 많아. 그 중에서 제일 맛있는 치킨집은"
        def givenUrl = "https://developers.kakao.com/"
        def givenThumbnail = "https://developers.kakao.com/assets/img/about/logos/kakaolink/kakaolink_btn_small.png"
        def givenIssuedAt = LocalDateTime.of(2023, 11, 19, 0, 0, 0)

        when:
        def result = SearchResponse.builder()
                .title(givenTitle)
                .summary(givenSummary)
                .url(givenUrl)
                .thumbnail(givenThumbnail)
                .issuedAt(givenIssuedAt)
                .build()

        then:
        verifyAll(result) {
            title == givenTitle
            summary == givenSummary
            givenUrl == givenUrl
            givenThumbnail == givenThumbnail
            issuedAt == givenIssuedAt
        }
    }
}
