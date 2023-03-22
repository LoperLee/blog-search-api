package com.blog.search.provider;

import com.blog.core.GenericProvider;
import com.blog.search.domain.SearchRequest;
import com.blog.search.domain.SearchResult;

public interface SearchProvider extends GenericProvider<SearchType> {
    SearchResult getBlogs(SearchRequest searchRequest);
}
