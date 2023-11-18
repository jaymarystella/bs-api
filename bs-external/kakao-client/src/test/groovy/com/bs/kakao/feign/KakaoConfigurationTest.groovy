package com.bs.kakao.feign

import feign.RequestTemplate
import spock.lang.Specification
import spock.lang.Subject

class KakaoConfigurationTest extends Specification {
    @Subject
    KakaoConfiguration kakaoConfiguration

    void setup() {
        kakaoConfiguration = new KakaoConfiguration()
    }

    def "requestInterceptor에 KEY 적용"() {
        given:
        def requestTemplate = new RequestTemplate()
        def key = "KEY"

        and: "intercepter 타기전에 header 존재 X"
        requestTemplate.headers()["Authorization"] == null

        when:
        def header = kakaoConfiguration.requestInterceptor(key)
        header.apply(requestTemplate)

        then: "intercepter 탄 후에 header가 추가된다"
        requestTemplate.headers()["Authorization"].contains("KakaoAK "+ key)
    }
}
