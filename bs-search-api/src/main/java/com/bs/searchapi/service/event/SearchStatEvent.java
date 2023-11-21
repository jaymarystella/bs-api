package com.bs.searchapi.service.event;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class SearchStatEvent {
    private final String keyword;
    private final LocalDateTime timestamp;

    public SearchStatEvent(String keyword, LocalDateTime timestamp) {
        this.keyword = keyword;
        this.timestamp = timestamp;
    }
}