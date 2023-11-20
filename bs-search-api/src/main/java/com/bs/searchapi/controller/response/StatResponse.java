package com.bs.searchapi.controller.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
@ApiModel(value = "블로그 검색 통계 키워드", description = "검색 통계 키워드를 나타내는 모델")
public class StatResponse {
    @ApiModelProperty(value = "키워드", example = "치킨")
    private final String keyword;

    @ApiModelProperty(value = "키워드 검색 횟수", example = "100")
    private final Long count;

    public StatResponse(String keyword,
                        Long count) {
        this.keyword = keyword;
        this.count = count;
    }
}
