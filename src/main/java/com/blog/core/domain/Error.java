package com.blog.core.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum Error {
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "5001", "Unknown error"),
    NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, "4004", "Resource not found"),
    BAD_REQUEST_ERROR(HttpStatus.BAD_REQUEST, "4000", "Invalid types"),
    PROVIDER_NOT_FOUND(HttpStatus.METHOD_NOT_ALLOWED, "4005", "Not found working class"),
    ALL_DATASOURCE_BUSY(HttpStatus.INTERNAL_SERVER_ERROR, "4010", "Remote datasource is busy now"),
    ;
    private final HttpStatus status;
    private final String workCode;
    private final String message;
}
