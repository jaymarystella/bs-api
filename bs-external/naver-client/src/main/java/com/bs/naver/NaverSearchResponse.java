package com.bs.naver;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class NaverSearchResponse {
    private String lastBuildDate;

    private int total;

    private int start;

    private int display;

    private List<Item> items;
}
