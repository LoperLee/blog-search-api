package com.blog.search.provider;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SearchType {
    KAKAO("accuracy", "recency"),
    NAVER("sim", "date");
    private final String accuracy;
    private final String recency;

    public String getConvertSort(SortType sortType) {
        if (SortType.RECENCY.equals(sortType)) {
            return this.recency;
        }
        return accuracy;
    }
}
