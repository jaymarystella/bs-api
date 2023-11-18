package com.bs.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Meta {
    @JsonProperty("total_count")
    private int totalCount;

    @JsonProperty("pageable_count")
    private int pageableCount;

    @JsonProperty("is_end")
    private boolean end;
}
