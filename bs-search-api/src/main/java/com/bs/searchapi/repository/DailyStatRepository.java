package com.bs.searchapi.repository;

import com.bs.searchapi.entity.DailyStat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyStatRepository extends JpaRepository<DailyStat, Long> {
}
