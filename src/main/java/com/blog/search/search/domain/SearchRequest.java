package com.blog.search.search.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class SearchRequest {
    private String query;
    private Integer page;
    private Integer size;
    private String sort;
}
