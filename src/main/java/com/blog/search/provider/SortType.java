package com.blog.search.provider;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SortType {
    ACCURACY,
    RECENCY;

    public static SortType getSortType(String sort) {
        try {
            return SortType.valueOf(sort.toUpperCase());
        } catch (Exception e) {
            return ACCURACY;
        }
    }
}
