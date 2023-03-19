package com.blog.search.search.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Meta {
    private Integer totalCount;
    private Integer pageableCount;
    private Boolean isEnd;
}
