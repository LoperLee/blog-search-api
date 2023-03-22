package com.blog.history.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class History {
    private String keyword;
    private Long count;
}
