package com.blog.search.search.provider.naver.domain;

import com.blog.search.core.Translatable;
import com.blog.search.search.domain.Meta;
import com.blog.search.search.domain.SearchResult;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NaverBlogResult implements Translatable<SearchResult> {
    private String lastBuildDate;
    private Integer total;
    private Integer start;
    private Integer display;
    private List<NaverBlogDocument> items;

    @Override
    public SearchResult transObject() {
        return SearchResult.builder()
                .meta(
                        Meta.builder()
                                .totalCount(total)
                                .pageableCount(display)
                                .isEnd((start * display) >= total)
                                .build()
                )
                .documents(
                        items.stream()
                                .map(NaverBlogDocument::transObject)
                                .collect(Collectors.toList())
                )
                .build();
    }
}