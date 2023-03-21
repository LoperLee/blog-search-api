package com.blog.search.search;

import com.blog.search.exception.DataSourceException;
import com.blog.search.search.domain.SearchRequest;
import com.blog.search.search.domain.SearchResult;
import com.blog.search.search.provider.SearchManager;
import com.blog.search.search.provider.SearchType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchService {
    private final SearchManager searchManager;
    private final ApplicationEventPublisher publisher;

    public SearchResult getBlogs(SearchRequest request) {
        publisher.publishEvent(request);
        for (var searchType : SearchType.values()) {
            try {
                return searchManager
                        .getProvider(searchType)
                        .getBlogs(request);
            } catch (Exception e) {
                log.info("API 호출에 오류가 있어 다른 데이터 소스를 통해서 검색합니다.");
            }
        }
        throw new DataSourceException("API 호출에 실패하였습니다.");
    }
}
