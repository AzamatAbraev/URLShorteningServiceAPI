package org.urlshortener.urlshorteningserviceapi.model.url;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class URLEntityStatsDTO {
    private Integer id;

    @NotBlank(message = "URL cannot be blank")
    @URL(message = "Invalid URL format")
    private String originalUrl;

    @NotBlank(message = "Short URL cannot be blank")
    private String shortUrl;

    private int accessCount;

    private LocalDateTime expiresAt;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
