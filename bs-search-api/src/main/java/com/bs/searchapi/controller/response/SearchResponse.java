package com.bs.searchapi.controller.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@ApiModel(value = "블로그 검색 결과", description = "검색 결과를 나타내는 모델")
public class SearchResponse {
    @ApiModelProperty(value = "블로그 제목", example = "치킨 먹고 싶다")
    private final String title;

    @ApiModelProperty(value = "블로그 글 요약", example = "요새 유행은 양념치킨...")
    private final String summary;

    @ApiModelProperty(value = "블로그 글 URL", example = "https://developers.kakao.com")
    private final String url;

    @ApiModelProperty(value = "블로그 이름", example = "치킨마스터")
    private final String blogName;

    @ApiModelProperty(value = "블로그 글 대표 이미지", example = "https://developers.kakao.com/assets/img/about/logos/kakaolink/kakaolink_btn_small.png")
    private final String thumbnail;

    @ApiModelProperty(value = "블로그 발생 시각", example = "2023-11-09T01:46:00")
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
