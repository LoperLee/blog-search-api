package com.blog.search.search;

import com.blog.search.search.domain.SearchRequest;
import com.blog.search.search.domain.SearchResult;
import com.blog.search.search.provider.SearchManager;
import com.blog.search.search.provider.SearchType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final SearchManager searchManager;

    public SearchResult getBlogs(SearchRequest searchRequest) {
        return searchManager
                .getProvider(SearchType.KAKAO)
                .getBlogs(searchRequest);
    }
}
