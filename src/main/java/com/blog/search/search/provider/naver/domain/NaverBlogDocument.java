package com.blog.search.search.provider.naver.domain;

import com.blog.search.core.Translatable;
import com.blog.search.search.domain.Document;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NaverBlogDocument implements Translatable<Document> {
    private String title;
    private String link;
    private String description;
    private String bloggername;
    private String bloggerlink;
    private String postdate;

    @Override
    public Document transObject() {
        return Document.builder()
                .title(title)
                .blogName(bloggername)
                .contents(description)
                .url(link)
                .build();
    }
}
