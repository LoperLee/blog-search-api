package com.blog.search.domain;

import com.blog.search.provider.SortType;
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
    private SortType sort;
}
