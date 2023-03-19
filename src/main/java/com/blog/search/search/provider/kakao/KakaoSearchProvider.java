package com.blog.search.search.provider.kakao;

import com.blog.search.search.domain.SearchRequest;
import com.blog.search.search.domain.SearchResult;
import com.blog.search.search.provider.SearchProvider;
import com.blog.search.search.provider.SearchType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KakaoSearchProvider implements SearchProvider {
    private final KakaoApiClient kakaoApiClient;

    @Override
    public boolean support(SearchType searchType) {
        return SearchType.KAKAO.equals(searchType);
    }

    @Override
    public SearchResult getBlogs(SearchRequest request) {
        return kakaoApiClient
                .getBlogs(
                        request.getQuery(),
                        request.getPage(),
                        request.getSize(),
                        request.getSort()
                )
                .transObject();
    }
}
