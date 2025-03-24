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
public class URLEntityRequest {
    @NotBlank(message = "URL cannot be blank")
    @URL(message = "Invalid URL format")
    private String originalUrl;

    private LocalDateTime expiresAt;
}
