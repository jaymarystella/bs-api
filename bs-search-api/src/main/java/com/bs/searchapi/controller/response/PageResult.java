package com.bs.searchapi.controller.response;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class PageResult<T> {
    private final List<T> contents;
    private final int page;
    private final int size;
    private final long totalElements;

    public PageResult(List<T> contents,
                      int page,
                      int size,
                      long totalElements) {
        this.contents = contents;
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
    }
}
