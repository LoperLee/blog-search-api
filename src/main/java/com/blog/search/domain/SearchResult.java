package com.blog.search.domain;

import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class SearchResult {
    private Meta meta;
    private List<Document> documents;
}
