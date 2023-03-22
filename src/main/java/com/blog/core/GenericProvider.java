package com.blog.core;

public interface GenericProvider<T> {
    boolean support(T t);
}
