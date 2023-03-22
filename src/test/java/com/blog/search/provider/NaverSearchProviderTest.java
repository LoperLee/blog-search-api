package com.blog.search.provider;

import com.blog.search.domain.SearchRequest;
import com.blog.search.domain.SearchResult;
import com.blog.search.provider.naver.NaverApiClient;
import com.blog.search.provider.naver.NaverSearchProvider;
import com.blog.search.provider.naver.domain.NaverBlogDocument;
import com.blog.search.provider.naver.domain.NaverBlogResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NaverSearchProviderTest {

    private NaverSearchProvider naverSearchProvider;

    @Mock
    private NaverApiClient naverApiClient;
    private NaverBlogDocument blogDocument;
    private NaverBlogResult naverBlogResult;

    @BeforeEach
    void beforeSet() {
        naverSearchProvider = new NaverSearchProvider(naverApiClient);
        blogDocument = new NaverBlogDocument("title", "link", "description", "bloggername", "bloggerlink", "postdate");
        naverBlogResult = new NaverBlogResult("lastBuildDate", 1, 1, 1, List.of(blogDocument));
    }

    @Test
    @DisplayName("네이버 Provider 지원 테스트")
    void test_support() {
        boolean result = naverSearchProvider.support(SearchType.NAVER);
        assertTrue(result);
    }

    @Test
    @DisplayName("네이버 블로그 검색 테스트")
    void testGetBlogs() {
        when(naverApiClient.getBlogs("example", 1, 10, SearchType.NAVER.getConvertSort(SortType.ACCURACY)))
                .thenReturn(naverBlogResult);
        SearchResult result = naverSearchProvider.getBlogs(
                SearchRequest.builder()
                        .query("example")
                        .page(1)
                        .size(10)
                        .sort(SortType.ACCURACY)
                        .build()
        );

        assertEquals(naverBlogResult.transObject(), result);
    }
}
