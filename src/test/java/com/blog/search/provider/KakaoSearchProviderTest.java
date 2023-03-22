package com.blog.search.provider;

import com.blog.search.domain.SearchRequest;
import com.blog.search.domain.SearchResult;
import com.blog.search.provider.kakao.KakaoApiClient;
import com.blog.search.provider.kakao.KakaoSearchProvider;
import com.blog.search.provider.kakao.domin.KakaoBlogDocument;
import com.blog.search.provider.kakao.domin.KakaoBlogMeta;
import com.blog.search.provider.kakao.domin.KakaoBlogResult;
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
class KakaoSearchProviderTest {

    private KakaoSearchProvider kakaoSearchProvider;

    @Mock
    private KakaoApiClient kakaoApiClient;
    private KakaoBlogMeta blogMeta;
    private KakaoBlogDocument blogDocument;
    private KakaoBlogResult kakaoBlogResult;

    @BeforeEach
    void beforeSet() {
        kakaoSearchProvider = new KakaoSearchProvider(kakaoApiClient);
        blogMeta = new KakaoBlogMeta(1, 1, true);
        blogDocument = new KakaoBlogDocument("title", "contents", "url", "blogname", "thumbnail", "datetime");
        kakaoBlogResult = new KakaoBlogResult(blogMeta, List.of(blogDocument));
    }

    @Test
    @DisplayName("카카오 Provider 지원 테스트")
    void test_support() {
        boolean result = kakaoSearchProvider.support(SearchType.KAKAO);
        assertTrue(result);
    }

    @Test
    @DisplayName("카카오 블로그 검색 테스트")
    void testGetBlogs() {
        when(kakaoApiClient.getBlogs("example", 1, 10, SearchType.KAKAO.getConvertSort(SortType.ACCURACY)))
                .thenReturn(kakaoBlogResult);
        SearchResult result = kakaoSearchProvider.getBlogs(
                SearchRequest.builder()
                        .query("example")
                        .page(1)
                        .size(10)
                        .sort(SortType.ACCURACY)
                        .build()
        );

        assertEquals(kakaoBlogResult.transObject(), result);
    }
}
