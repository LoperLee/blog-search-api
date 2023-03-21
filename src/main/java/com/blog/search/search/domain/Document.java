package com.blog.search.search.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Document {
    private String title;
    private String contents;
    private String thumbnail;
    private String blogName;
    private String url;
}
