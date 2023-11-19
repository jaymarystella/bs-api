package com.bs.searchapi.repository

import com.bs.searchapi.entity.DailyStat
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import spock.lang.Specification

import javax.persistence.EntityManager
import java.time.LocalDateTime

@DataJpaTest
class DailyStatRepositoryTest extends Specification {
    @Autowired
    DailyStatRepository dailyStatRepository

    @Autowired
    EntityManager entityManager

    def "save and find"() {
        given:
        def keyword = "치킨"

        when:
        DailyStat dailyStat = new DailyStat(keyword, LocalDateTime.now())
        def saved = dailyStatRepository.saveAndFlush(dailyStat)

        then: "실제 저장이 된다."
        saved.id != null

        when: "엔티티매니저를 clear하고 다시 조회한다."
        entityManager.clear()
        def result = dailyStatRepository.findById(saved.id)

        then: "캐시가 아닌 실제 DB 쿼리로 데이터를 가져온다."
        verifyAll {
            result.isPresent()
            result.get().keyword == keyword
        }
    }
}
