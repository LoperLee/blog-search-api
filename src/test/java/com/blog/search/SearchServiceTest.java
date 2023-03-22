package com.blog.search;

import com.blog.exception.DataSourceException;
import com.blog.search.domain.Meta;
import com.blog.search.domain.SearchRequest;
import com.blog.search.domain.SearchResult;
import com.blog.search.provider.SearchManager;
import com.blog.search.provider.SearchType;
import com.blog.search.provider.SortType;
import com.blog.search.provider.kakao.KakaoSearchProvider;
import com.blog.search.provider.naver.NaverSearchProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchServiceTest {
    private SearchService searchService;

    @Mock
    private KakaoSearchProvider kakaoSearchProvider;
    @Mock
    private NaverSearchProvider naverSearchProvider;
    @Mock
    private ApplicationEventPublisher eventPublisher;

    private SearchManager searchManager;
    private SearchRequest request;
    private SearchResult kakaoResult;
    private SearchResult naverResult;

    @BeforeEach
    void beforeSet() {
        searchManager = new SearchManager(List.of(kakaoSearchProvider, naverSearchProvider));
        searchService = new SearchService(searchManager, eventPublisher);
        kakaoResult = new SearchResult(new Meta(1, 1, true), new ArrayList<>());
        naverResult = new SearchResult(new Meta(2, 2, true), new ArrayList<>());
        request = SearchRequest.builder()
                .query("example")
                .page(1)
                .size(1)
                .sort(SortType.ACCURACY)
                .build();
    }

    @Test
    void testKakaoDatasourceSuccess() {
        when(kakaoSearchProvider.support(SearchType.KAKAO)).thenReturn(true);
        when(kakaoSearchProvider.getBlogs(request)).thenReturn(kakaoResult);

        SearchResult result = searchService.getBlogs(request);

        assertEquals(kakaoResult, result);
        assertNotEquals(naverResult, result);

        verify(kakaoSearchProvider).getBlogs(request);
    }

    @Test
    void testKakaoDatasourceFail() {
        when(kakaoSearchProvider.support(SearchType.KAKAO)).thenReturn(true);
        when(kakaoSearchProvider.getBlogs(request)).thenThrow(new RuntimeException());
        when(naverSearchProvider.support(SearchType.NAVER)).thenReturn(true);
        when(naverSearchProvider.getBlogs(request)).thenReturn(naverResult);

        SearchResult result = searchService.getBlogs(request);

        assertNotEquals(kakaoResult, result);
        assertEquals(naverResult, result);

        verify(kakaoSearchProvider).getBlogs(request);
        verify(naverSearchProvider).getBlogs(request);
    }

    @Test
    void testAllDatasourceFail() {
        when(kakaoSearchProvider.support(SearchType.KAKAO)).thenReturn(true);
        when(kakaoSearchProvider.getBlogs(request)).thenThrow(new RuntimeException());
        when(naverSearchProvider.support(SearchType.NAVER)).thenReturn(true);
        when(naverSearchProvider.getBlogs(request)).thenThrow(new RuntimeException());

        assertThrows(DataSourceException.class, () -> searchService.getBlogs(request));

        verify(kakaoSearchProvider).getBlogs(request);
        verify(naverSearchProvider).getBlogs(request);
    }
}

