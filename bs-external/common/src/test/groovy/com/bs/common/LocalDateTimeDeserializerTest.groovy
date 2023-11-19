package com.bs.common

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import spock.lang.Specification

import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

class LocalDateTimeDeserializerTest extends Specification {
    def "offset datetime을 localdatetime으로 파싱"() {
        given:
        def jsonParser = Mock(JsonParser)
        def deserializationContext = Mock(DeserializationContext)
        def offsetDateTimeString = "2023-11-19T17:49:37.000+09:00"
        def givenOffsetDateTime = OffsetDateTime.parse(offsetDateTimeString, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
        def expectedLocalDateTime = givenOffsetDateTime.toLocalDateTime()
        jsonParser.getText() >> offsetDateTimeString

        def deserializer = new LocalDateTimeDeserializer()

        when:
        def result = deserializer.deserialize(jsonParser, deserializationContext)

        then:
        result == expectedLocalDateTime
    }
}
