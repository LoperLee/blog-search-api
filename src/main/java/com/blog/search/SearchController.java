package com.blog.search;

import com.blog.core.valid.Enum;
import com.blog.search.domain.SearchRequest;
import com.blog.search.provider.SortType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
@Validated
public class SearchController {
    private final SearchService searchService;

    @GetMapping("/blog")
    public ResponseEntity<?> getSearchBlog(
            @NotBlank @RequestParam String query,
            @Min(1) @RequestParam Integer size,
            @Min(10) @RequestParam Integer page,
            @Enum(enumClass = SortType.class) @RequestParam String sort) {
        return ResponseEntity.ok(
                searchService.getBlogs(
                        SearchRequest.builder()
                                .query(query)
                                .size(Optional.of(size).orElse(1))
                                .page(Optional.of(page).orElse(10))
                                .sort(SortType.getSortType(Optional.of(sort).orElse("accuracy")))
                                .build()
                )
        );
    }
}
