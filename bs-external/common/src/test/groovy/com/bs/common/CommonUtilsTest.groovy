package com.bs.common

import spock.lang.Specification
import spock.lang.Subject

class CommonUtilsTest extends Specification {
    @Subject
    CommonUtils commonUtils

    def "html태그가 필터된다."() {
        when:
        def result = CommonUtils.filter(givenInput)

        then:
        result == expected

        where:
        givenInput | expected
        "<script>교촌</script>" | "교촌"
        "<b>네네</b>" | "네네"
    }
}
