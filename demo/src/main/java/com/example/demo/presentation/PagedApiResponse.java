package com.example.demo.presentation;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class PagedApiResponse<T> {

    private final int code;
    private final HttpStatus status;
    private final String message;
    private final T data;
    private final int pageNumber;
    private final int pageSize;

    private PagedApiResponse(HttpStatus status, String message, T data, int pageNumber, int pageSize) {
        this.code = status.value();
        this.status = status;
        this.message = message;
        this.data = data;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public static <T> PagedApiResponse<T> of(HttpStatus status, String message, T data, int pageNumber, int pageSize) {
        return new PagedApiResponse<>(status, message, data, pageNumber, pageSize);
    }

    public static <T> PagedApiResponse<T> ok(T data, int pageNumber, int pageSize) {
        return of(HttpStatus.OK, HttpStatus.OK.name(), data, pageNumber, pageSize);
    }

}

