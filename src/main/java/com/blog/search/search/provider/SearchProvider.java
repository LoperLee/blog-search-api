package com.blog.search.search.provider;

import com.blog.search.core.GenericProvider;
import com.blog.search.search.domain.SearchRequest;
import com.blog.search.search.domain.SearchResult;

public interface SearchProvider extends GenericProvider<SearchType> {
    SearchResult getBlogs(SearchRequest searchRequest);

    default String getSortString(SearchType searchType, String sort) {
        if ("recency".equals(sort)) {
            return searchType.getAccuracy();
        }
        return searchType.getAccuracy();
    }
}
