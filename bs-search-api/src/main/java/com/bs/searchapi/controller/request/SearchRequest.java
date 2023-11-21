package com.bs.searchapi.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
public class SearchRequest {
    @NotBlank(message = "입력은 비어 있을 수 없습니다.")
    @Size(max = 50, message = "키워드는 최대 50자를 초과할 수 없습니다.")
    private String input;

    @NotNull(message = "정렬 기준은 비어 있을 수 없습니다.")
    private SortType sort;

    @NotNull(message = "페이지 번호는 필수입니다.")
    @Min(value = 1, message = "페이지 번호는 1 이상이어야 합니다.")
    @Max(value = 50, message = "페이지 번호는 50 이상이어야 합니다.")
    private int page;

    @NotNull(message = "페이지 크기는 필수입니다.")
    @Min(value = 1, message = "페이지 크기는 1 이상이어야 합니다.")
    @Max(value = 50, message = "페이지 크기는 50 이하이어야 합니다.")
    private int size;
}
