package com.bs.naver.feign

import com.bs.common.exception.ApiException
import com.bs.common.exception.ErrorType
import com.bs.naver.NaverErrorResponse
import com.fasterxml.jackson.databind.ObjectMapper
import feign.Request
import feign.Response
import org.springframework.http.HttpStatus
import spock.lang.Specification

class NaverErrorDecoderTest extends Specification {
    ObjectMapper objectMapper = Mock()
    NaverErrorDecoder errorDecoder = new NaverErrorDecoder(objectMapper)

    def "Naver Error Decoder에서 에러 발생시 ApiException 으로 throw"() {
        given:
        def responseBody = Mock(Response.Body)
        def inputStream = new ByteArrayInputStream("error message".getBytes())
        def response = Response.builder()
                .status(400)
                .request(Request.create(Request.HttpMethod.GET, "http://url", [:], null as Request.Body, null))
                .body(responseBody)
                .build()

        1 * responseBody.asInputStream() >> inputStream
        1 * objectMapper.readValue(*_) >> new NaverErrorResponse("Invalid start value (부적절한 start 값입니다.)", "SE03")

        when:
        errorDecoder.decode("methodKey", response)

        then: "ApiException 발생"
        ApiException e = thrown()
        verifyAll {
            e.httpStatus == HttpStatus.valueOf(response.status())
            e.errorMessage == "Invalid start value (부적절한 start 값입니다.)"
            e.errorType == ErrorType.EXTERNAL_API_ERROR
        }
    }
}
