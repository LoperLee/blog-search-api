package com.blog.search.search.provider.naver;

import com.blog.search.search.domain.SearchRequest;
import com.blog.search.search.domain.SearchResult;
import com.blog.search.search.provider.SearchProvider;
import com.blog.search.search.provider.SearchType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NaverSearchProvider implements SearchProvider {
    private static final SearchType PROVIDE_TYPE = SearchType.NAVER;
    private final NaverApiClient naverApiClient;

    @Override
    public boolean support(SearchType searchType) {
        return PROVIDE_TYPE.equals(searchType);
    }

    @Override
    public SearchResult getBlogs(SearchRequest request) {
        return naverApiClient
                .getBlogs(
                        request.getQuery(),
                        request.getPage(),
                        request.getSize(),
                        getSortString(PROVIDE_TYPE, request.getSort())
                )
                .transObject();
    }
}
