package com.bs.kakao;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Meta {
    private int totalCount;
    private int pageableCount;
    private boolean isEnd;
}
