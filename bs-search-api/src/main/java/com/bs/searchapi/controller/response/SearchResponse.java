package com.bs.searchapi.controller.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SearchResponse {
    private final String title;
    private final String summary;
    private final String url;
    private final String blogName;
    private final String thumbnail;
    private final LocalDateTime issuedAt;

    @Builder
    public SearchResponse(String title,
                          String summary,
                          String url,
                          String blogName,
                          String thumbnail,
                          LocalDateTime issuedAt) {
        this.title = title;
        this.summary = summary;
        this.url = url;
        this.blogName = blogName;
        this.thumbnail = thumbnail;
        this.issuedAt = issuedAt;
    }
}
