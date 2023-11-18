package com.bs.kakao;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Meta {
    private int total_count;
    private int pageable_count;
    private boolean is_end;
}
