package com.bs.searchapi.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Entity
@Table(name = "summary_stat")
public class SummaryStat {
    @Id
    @Column(name = "keyword")
    private String keyword;

    @Column(name = "count")
    private Long count;

    @Column(name = "updated_date_time")
    private LocalDateTime updatedDateTime;

    public SummaryStat(String keyword,
                       Long count,
                       LocalDateTime updatedDateTime) {
        this.keyword = keyword;
        this.count = count;
        this.updatedDateTime = updatedDateTime;
    }
}
