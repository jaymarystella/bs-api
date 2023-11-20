package com.bs.searchapi.controller.request

import spock.lang.Specification

class SortTypeTest extends Specification {
    def "개발사별 enum 검증"() {
        expect:
        SortType.ACCURACY.description == "정확도순"
        SortType.ACCURACY.kakaoParam == "accuracy"
        SortType.ACCURACY.naverParam == "sim"

        SortType.RECENCY.description == "최신순"
        SortType.RECENCY.kakaoParam == "recency"
        SortType.RECENCY.naverParam == "date"
    }
}
