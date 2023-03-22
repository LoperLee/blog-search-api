package com.blog.search.provider.naver;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

public class NaverFeignConfig {
    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("X-Naver-Client-Id", "d_56XQY3oNBCLsPonlOy");
            requestTemplate.header("X-Naver-Client-Secret", "xmZVlFAqlJ");
        };
    }
}
