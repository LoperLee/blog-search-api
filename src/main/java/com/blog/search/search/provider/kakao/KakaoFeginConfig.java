package com.blog.search.search.provider.kakao;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

public class KakaoFeginConfig {
    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("Authorization", "KakaoAK 2d8ab7bf9da28f4278bf3ee5c0e33ba9");
        };
    }
}
