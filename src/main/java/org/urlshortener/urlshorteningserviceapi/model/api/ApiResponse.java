package org.urlshortener.urlshorteningserviceapi.model.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private String message;
    private HttpStatus status;
    private int code;
    private String path;
    private LocalDateTime timestamp;
    private T data;
}
