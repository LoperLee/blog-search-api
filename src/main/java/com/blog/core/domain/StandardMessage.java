package com.blog.core.domain;

import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

public class StandardMessage {
    public static ResponseEntity<ErrorResponse> createStandardErrorMessage(Error error, String path) {
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .status(error.getStatus().value())
                        .message(error.getMessage())
                        .timestamp(LocalDateTime.now())
                        .path(path)
                        .workCode(error.getWorkCode())
                        .build(),
                error.getStatus()
        );
    }

    public static ResponseEntity<ErrorResponse> createStandardErrorMessage(Error error, String message, String path) {
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .status(error.getStatus().value())
                        .message(message)
                        .timestamp(LocalDateTime.now())
                        .path(path)
                        .workCode(error.getWorkCode())
                        .build(),
                error.getStatus()
        );
    }

    public static ResponseEntity<ErrorResponse> createStandardErrorMessage(Error error, List<String> messages, String path) {
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .status(error.getStatus().value())
                        .messages(messages)
                        .timestamp(LocalDateTime.now())
                        .path(path)
                        .workCode(error.getWorkCode())
                        .build(),
                error.getStatus()
        );
    }
}
