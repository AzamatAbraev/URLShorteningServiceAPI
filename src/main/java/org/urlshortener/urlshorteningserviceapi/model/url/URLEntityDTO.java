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
@NoArgsConstructor
@AllArgsConstructor
public class URLEntityDTO {
    private Integer id;

    @NotBlank(message = "URL cannot be blank")
    @URL(message = "Invalid URL format")
    private String originalUrl;

    private String shortUrl;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
