package org.urlshortener.urlshorteningserviceapi.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.urlshortener.urlshorteningserviceapi.model.api.ApiResponse;
import org.urlshortener.urlshorteningserviceapi.model.url.URLEntityDTO;

import java.time.LocalDateTime;

public class ApiResponseBuilder<T> {
    private String message;
    private HttpStatus status;
    private T data;
    private String path;

    public ApiResponseBuilder<T> message(String message) {
        this.message = message;
        return this;
    }

    public ApiResponseBuilder<T> status(HttpStatus status) {
        this.status = status;
        return this;
    }

    public ApiResponseBuilder<T> data(T data) {
        this.data = data;
        return this;
    }

    public ApiResponseBuilder<T> path(String path) {
        this.path = path;
        return this;
    }

    public ResponseEntity<ApiResponse<T>> build() {
        ApiResponse<T> response = new ApiResponse<>();

        response.setMessage(this.message);
        response.setStatus(this.status);
        response.setCode(this.status.value());
        response.setTimestamp(LocalDateTime.now());
        response.setData(this.data);
        response.setPath(this.path);

        return new ResponseEntity<>(response, this.status);
    }
}

