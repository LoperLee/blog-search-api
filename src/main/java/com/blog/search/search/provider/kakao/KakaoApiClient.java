package com.blog.search.search.provider.kakao;

import com.blog.search.search.provider.kakao.domin.KakaoBlogResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "kakaoApiClient", url = "https://dapi.kakao.com", configuration = KakaoFeignConfig.class)
public interface KakaoApiClient {
    @GetMapping(value = "/v2/search/blog")
    KakaoBlogResult getBlogs(@RequestParam("query") String query,
                             @RequestParam("page") Integer page,
                             @RequestParam("size") Integer size,
                             @RequestParam("sort") String sort);
}
