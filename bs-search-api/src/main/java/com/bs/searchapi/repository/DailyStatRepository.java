package com.bs.searchapi.repository;

import com.bs.searchapi.controller.response.StatResponse;
import com.bs.searchapi.entity.DailyStat;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DailyStatRepository extends JpaRepository<DailyStat, Long> {
    @Query("SELECT new com.bs.searchapi.controller.response.StatResponse(ds.keyword, COUNT(ds.keyword)) " +
            "FROM DailyStat ds " +
            "GROUP BY ds.keyword ORDER BY COUNT(ds.keyword) DESC")
    List<StatResponse> findTopKeywords(Pageable pageable);
}
