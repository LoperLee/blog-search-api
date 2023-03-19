package com.blog.search.core;

public interface GenericProvider<T> {
    boolean support(T t);
}
