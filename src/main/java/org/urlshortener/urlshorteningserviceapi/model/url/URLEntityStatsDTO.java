package org.urlshortener.urlshorteningserviceapi.model.url;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

public class URLEntityStatsDTO {
    private Integer id;

    @NotBlank(message = "URL cannot be blank")
    @URL(message = "Invalid URL format")
    private String originalUrl;

    @NotBlank(message = "Short URL cannot be blank")
    private String shortUrl;

    private int accessCount;

    private LocalDateTime expirationTime;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
