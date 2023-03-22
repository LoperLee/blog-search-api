package com.blog.search.provider.naver;

import com.blog.search.domain.SearchRequest;
import com.blog.search.domain.SearchResult;
import com.blog.search.provider.SearchProvider;
import com.blog.search.provider.SearchType;
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
                        PROVIDE_TYPE.getConvertSort(request.getSort())
                )
                .transObject();
    }
}
