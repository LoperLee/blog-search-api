package com.blog.search.search.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class SearchResult {
    private final Meta meta;
    private final List<Document> documents;
}
