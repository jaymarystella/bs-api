package com.bs.searchapi.repository

import com.bs.kakao.Document
import com.bs.kakao.KakaoSearchResponse
import com.bs.kakao.Meta
import com.bs.kakao.feign.KakaoClient
import spock.lang.Specification
import spock.lang.Subject

import java.time.LocalDateTime

class KakaoSearchRepositoryTest extends Specification {
    @Subject
    KakaoSearchRepository kakaoSearchRepository

    KakaoClient kakaoClient = Mock()

    void setup() {
        kakaoSearchRepository = new KakaoSearchRepository(kakaoClient)
    }

    def "search 메소드는 KakaoClient를 사용하여 검색 결과를 반환한다"() {
        given: "KakaoClient로부터의 예상되는 응답"
        def givenDocuments = [
                new Document(title: "제목1", url: "http://url1", thumbnail: "http://thumbnail1", contents: "내용1", datetime: LocalDateTime.of(2023, 11, 19, 0, 0, 0)),
                new Document(title: "제목2", url: "http://url2", thumbnail: "http://thumbnail2", contents: "내용2", datetime: LocalDateTime.of(2023, 11, 19, 0, 0, 0))
        ]
        def givenMeta = new Meta(totalCount: 2, pageableCount: 2, end: false)
        def givenKakaoResponse = new KakaoSearchResponse(documents: givenDocuments, meta: givenMeta)

        when:
        def result = kakaoSearchRepository.search("키워드", "정렬", 1, 10)

        then:
        verifyAll(result) {
            contents.size() == 2
            page == 1
            pageSize == 10
            totalElements == 2
        }

        and: "kakaoClient의 search 메소드가 호출되어야 한다"
        1 * kakaoClient.search("키워드", "정렬", 1, 10) >> givenKakaoResponse
    }

    def "search 메소드는 내부적으로 불필요한 html 태그들을 필터하여 반환한다."() {
        given: "KakaoClient로부터의 예상되는 응답"
        def givenDocuments = [
                new Document(title: givenTitle, url: "http://url1", thumbnail: "http://thumbnail1", contents: givenContents, datetime: LocalDateTime.of(2023, 11, 19, 0, 0, 0)),
                new Document(title: givenTitle, url: "http://url2", thumbnail: "http://thumbnail2", contents: givenContents, datetime: LocalDateTime.of(2023, 11, 19, 0, 0, 0))
        ]
        def givenMeta = new Meta(totalCount: 2, pageableCount: 2, end: false)
        def givenKakaoResponse = new KakaoSearchResponse(documents: givenDocuments, meta: givenMeta)

        kakaoClient.search("키워드", "정렬", 1, 10) >> givenKakaoResponse

        when:
        def result = kakaoSearchRepository.search("키워드", "정렬", 1, 10)

        then: "html 태그들이 필터되어야 한다."
        verifyAll(result) {
            contents.size() == 2
            contents[0].title == titleExpected
            contents[0].summary == summaryExpected
            contents[1].title == titleExpected
            contents[1].summary == summaryExpected
        }

        where:
        givenContents | givenTitle            || summaryExpected | titleExpected
        "<b>내용1</b>"  | "분당 치킨 맛집 ,<b>안녕</b>" || "내용1"           | "분당 치킨 맛집 ,안녕"
        "<b>내용2</b>"  | "<b><b>교촌네네</b></b>"  || "내용2"           | "교촌네네"
    }
}
