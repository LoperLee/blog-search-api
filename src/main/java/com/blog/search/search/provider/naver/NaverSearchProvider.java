package com.blog.search.search.provider.naver;

import com.blog.search.search.domain.SearchRequest;
import com.blog.search.search.domain.SearchResult;
import com.blog.search.search.provider.SearchProvider;
import com.blog.search.search.provider.SearchType;
import org.springframework.stereotype.Service;

@Service
public class NaverSearchProvider implements SearchProvider {
    @Override
    public boolean support(SearchType searchType) {
        return SearchType.NAVER.equals(searchType);
    }

    @Override
    public SearchResult getBlogs(SearchRequest searchRequest) {
        return null;
    }
}
