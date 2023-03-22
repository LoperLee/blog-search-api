package com.blog.search.provider.kakao.domin;

import com.blog.core.Translatable;
import com.blog.search.domain.SearchResult;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KakaoBlogResult implements Translatable<SearchResult> {
    private KakaoBlogMeta meta;
    private List<KakaoBlogDocument> documents;

    @Override
    public SearchResult transObject() {
        return SearchResult.builder()
                .meta(meta.transObject())
                .documents(
                        documents.stream()
                                .map(KakaoBlogDocument::transObject)
                                .collect(Collectors.toList())
                )
                .build();
    }
}
