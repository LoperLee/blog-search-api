package com.blog.search.search.provider.naver;

import com.blog.search.search.provider.naver.domain.NaverBlogResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "naverApiClient", url = "https://openapi.naver.com", configuration = NaverFeignConfig.class)
public interface NaverApiClient {
    @GetMapping(value = "/v1/search/blog.json")
    NaverBlogResult getBlogs(@RequestParam("query") String query,
                             @RequestParam("display") Integer display,
                             @RequestParam("start") Integer start,
                             @RequestParam("sort") String sort);
}
