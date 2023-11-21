package com.bs.searchapi.util

import spock.lang.Specification
import spock.lang.Subject

class KeywordParserTest extends Specification {
    @Subject
    KeywordParser keywordParser

    def "키워드 파싱때는 명사만 파싱한다."() {
        when:
        def keywords = KeywordParser.parseKeywords("나는 치킨이 좋아~~ 특히 교촌치킨 너무맛있어. 근데 비싸서 한달에 한번먹어")

        then:
        keywords == ["치킨", "교촌", "치킨", "달"]
    }
}
