package com.bs.kakao.feign

import com.bs.common.exception.ApiException
import com.bs.common.exception.ErrorType
import com.bs.kakao.KakaoErrorResponse
import com.fasterxml.jackson.databind.ObjectMapper
import feign.Request
import feign.Response
import org.springframework.http.HttpStatus
import spock.lang.Specification

class KakaoErrorDecoderTest extends Specification {
    ObjectMapper objectMapper = Mock()
    KakaoErrorDecoder errorDecoder = new KakaoErrorDecoder(objectMapper)

    def "Kakao Error Decoder에서 에러 발생시 ApiException 으로 throw"() {
        given:
        def responseBody = Mock(Response.Body)
        def inputStream = new ByteArrayInputStream("error message".getBytes())
        def response = Response.builder()
                .status(400)
                .request(Request.create(Request.HttpMethod.GET, "http://url", [:], null as Request.Body, null))
                .body(responseBody)
                .build()

        1 * responseBody.asInputStream() >> inputStream
        1 * objectMapper.readValue(*_) >> new KakaoErrorResponse("InvalidArgument", "size is more than max")

        when:
        errorDecoder.decode("methodKey", response)

        then: "ApiException 발생"
        ApiException e = thrown()
        verifyAll {
            e.httpStatus == HttpStatus.valueOf(response.status())
            e.errorMessage == "size is more than max"
            e.errorType == ErrorType.EXTERNAL_API_ERROR
        }
    }
}
