package com.bs.naver.feign

import feign.RequestTemplate
import spock.lang.Specification
import spock.lang.Subject

class NaverConfigurationTest extends Specification {
    @Subject
    NaverConfiguration naverConfiguration

    void setup() {
        naverConfiguration = new NaverConfiguration()
    }

    def "requestInterceptor에 KEY 적용"() {
        given:
        def requestTemplate = new RequestTemplate()
        def clientId = "clientId"
        def clientSecreet = "clientSecret"

        and: "intercepter 타기전에 header 존재 X"
        requestTemplate.headers()["X-Naver-Client-Id"] == null
        requestTemplate.headers()["X-Naver-Client-Secret"] == null

        when:
        def header = naverConfiguration.requestInterceptor(clientId, clientSecreet)
        header.apply(requestTemplate)

        then: "intercepter 탄 후에 header가 추가된다"
        requestTemplate.headers()["X-Naver-Client-Id"].contains(clientId)
        requestTemplate.headers()["X-Naver-Client-Secret"].contains(clientSecreet)
    }
}
