package com.blog.search.search.provider;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SearchType {
    KAKAO("accuracy", "recency"),
    NAVER("sim", "date");
    private final String accuracy;
    private final String recency;
}
