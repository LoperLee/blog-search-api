package com.blog.search.search.provider.kakao.domin;

import com.blog.search.core.Translatable;
import com.blog.search.search.domain.Document;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KakaoBlogDocument implements Translatable<Document> {
    private String title;
    private String contents;
    private String url;
    private String blogname;
    private String thumbnail;
    private String datetime;

    @Override
    public Document transObject() {
        return Document.builder()
                .title(title)
                .blogName(blogname)
                .contents(contents)
                .url(url)
                .thumbnail(thumbnail)
                .build();
    }
}
