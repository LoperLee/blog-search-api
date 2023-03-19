package com.blog.search.search;

import com.blog.search.search.domain.SearchRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class SearchController {
    private final SearchService searchService;

    @GetMapping
    public ResponseEntity<?> getSearchBlog(
            @RequestParam String query,
            @RequestParam Optional<Integer> size,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sort) {
        return ResponseEntity.ok(
                searchService.getBlogs(
                        SearchRequest.builder()
                                .query(query)
                                .size(size.orElse(1))
                                .page(page.orElse(10))
                                .sort(sort.orElse("accuracy"))
                                .build()
                )
        );
    }
}
