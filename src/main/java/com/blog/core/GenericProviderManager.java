package com.blog.core;

import com.blog.exception.CustomException;

import java.util.List;

/**
 * 기본적인 검색전략을 추상화 해둔 클래스
 *
 * @param <T> 전략 검색시 기준이 될 타입
 * @param <V> 전략 검색 결과로 전달될 타입
 */
public abstract class GenericProviderManager<T, V extends GenericProvider<T>> {
    protected final List<V> providers;

    public GenericProviderManager(List<V> providers) {
        this.providers = providers;
    }

    public V getProvider(T type) {
        for (var provider : this.providers) {
            if (provider.support(type)) {
                return provider;
            }
        }
        throw CustomException.NOT_SUPPORT_PROVIDER;
    }
}