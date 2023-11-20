package com.bs.searchapi.repository


import com.bs.searchapi.entity.DailyStat
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.PageRequest
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

    def "findTopKeywords - pageable 인자값만큼 조회된다."() {
        given: "총 3가지 키워드를 저장한다."
        def dailyStat1 = new DailyStat("교촌", LocalDateTime.now())
        def dailyStat2 = new DailyStat("교촌", LocalDateTime.now())
        def dailyStat3 = new DailyStat("교촌", LocalDateTime.now())
        def dailyStat4 = new DailyStat("네네", LocalDateTime.now())
        def dailyStat5 = new DailyStat("네네", LocalDateTime.now())
        def dailyStat6 = new DailyStat("굽네", LocalDateTime.now())
        def givenPageSize = 2
        def pageRequest = PageRequest.of(0, givenPageSize)

        dailyStatRepository.saveAll(List.of(dailyStat1, dailyStat2, dailyStat3, dailyStat4, dailyStat5, dailyStat6))

        when: "상위 2개 키워드까지 조회"
        def keywords = dailyStatRepository.findTopKeywords(pageRequest)

        then:
        verifyAll(keywords) {
            size() == givenPageSize
            get(0).keyword == "교촌"
            get(0).count == 3
            get(1).keyword == "네네"
            get(1).count == 2
        }
    }
}
