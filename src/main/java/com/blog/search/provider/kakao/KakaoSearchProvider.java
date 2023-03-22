package com.blog.search.provider.kakao;

import com.blog.search.domain.SearchRequest;
import com.blog.search.domain.SearchResult;
import com.blog.search.provider.SearchProvider;
import com.blog.search.provider.SearchType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KakaoSearchProvider implements SearchProvider {
    private static final SearchType PROVIDE_TYPE = SearchType.KAKAO;
    private final KakaoApiClient kakaoApiClient;

    @Override
    public boolean support(SearchType searchType) {
        return PROVIDE_TYPE.equals(searchType);
    }

    @Override
    public SearchResult getBlogs(SearchRequest request) {
        return kakaoApiClient
                .getBlogs(
                        request.getQuery(),
                        request.getPage(),
                        request.getSize(),
                        PROVIDE_TYPE.getConvertSort(request.getSort())
                )
                .transObject();
    }
}
