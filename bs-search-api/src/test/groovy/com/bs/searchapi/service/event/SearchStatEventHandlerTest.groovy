package com.bs.searchapi.service.event

import com.bs.searchapi.entity.DailyStat
import com.bs.searchapi.service.DailyStatCommandService
import spock.lang.Specification

import java.time.LocalDateTime

class SearchStatEventHandlerTest extends Specification {
    def "handleEvent"() {
        given:
        def dailyStatCommandService = Mock(DailyStatCommandService)
        def searchStatEventHandler = new SearchStatEventHandler(dailyStatCommandService)
        def event = new SearchStatEvent("testKeyword", LocalDateTime.now())

        when:
        searchStatEventHandler.handleEvent(event)

        then:
        1 * dailyStatCommandService.save(_ as DailyStat)
    }
}
