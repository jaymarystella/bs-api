package com.bs.searchapi.repository;

import com.bs.searchapi.entity.SummaryStat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SummaryStatRepository extends JpaRepository<SummaryStat, String> {
}
