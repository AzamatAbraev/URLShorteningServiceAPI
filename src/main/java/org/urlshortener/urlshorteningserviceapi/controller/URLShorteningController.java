package org.urlshortener.urlshorteningserviceapi.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.urlshortener.urlshorteningserviceapi.model.api.ApiResponse;
import org.urlshortener.urlshorteningserviceapi.model.url.URLEntityDTO;
import org.urlshortener.urlshorteningserviceapi.model.url.URLEntityRequest;
import org.urlshortener.urlshorteningserviceapi.service.URLShorteningService;
import org.urlshortener.urlshorteningserviceapi.util.ApiResponseBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shorten")
@CrossOrigin
public class URLShorteningController {
    private final URLShorteningService urlShorteningService;

    public URLShorteningController(URLShorteningService urlShorteningService) {
        this.urlShorteningService = urlShorteningService;
    }

    @Cacheable(value = "urls")
    @GetMapping
    public ResponseEntity<ApiResponse<List<URLEntityDTO>>> getAllUrls(HttpServletRequest request) {
        List<URLEntityDTO> urlEntities = urlShorteningService.getAllURLs();
        return new ApiResponseBuilder<List<URLEntityDTO>>()
                    .message("Success")
                    .status(HttpStatus.OK)
                    .data(urlEntities)
                    .path(request.getRequestURI())
                    .build();
    }

    @PostMapping
    public ResponseEntity<ApiResponse<URLEntityDTO>> createShortUrl(@Valid @RequestBody URLEntityRequest request, HttpServletRequest servletRequest) {
        URLEntityDTO urlEntity = urlShorteningService.createShortURL(request);

        return new ApiResponseBuilder<URLEntityDTO>()
                .message("CREATED")
                .status(HttpStatus.CREATED)
                .data(urlEntity)
                .path(servletRequest.getRequestURI())
                .build();
    }

    @Cacheable(value = "urls", key = "#shortUrl")
    @GetMapping("/{shortUrl}")
    public ResponseEntity<ApiResponse<URLEntityDTO>> getOriginalUrl(@PathVariable String shortUrl, HttpServletRequest servletRequest) {
        URLEntityDTO originalURLEntity = urlShorteningService.getOriginalURL(shortUrl);

        return new ApiResponseBuilder<URLEntityDTO>()
                            .message("Success")
                            .status(HttpStatus.FOUND)
                            .data(originalURLEntity)
                            .path(servletRequest.getRequestURI())
                            .build();
    }

    @PutMapping("/{shortUrl}")
    public ResponseEntity<ApiResponse<URLEntityDTO>> updateOriginalURL(@PathVariable String shortUrl,
                                                                       @Valid @RequestBody URLEntityRequest urlEntityRequest,
                                                                       HttpServletRequest servletRequest) {
        URLEntityDTO updatedUrl = urlShorteningService.updateOriginalURL(shortUrl, urlEntityRequest);

        return new ApiResponseBuilder<URLEntityDTO>()
                .message("UPDATED")
                .status(HttpStatus.OK)
                .data(updatedUrl)
                .path(servletRequest.getRequestURI())
                .build();
    }

    @DeleteMapping("/{shortUrl}")
    public ResponseEntity<Void> deleteURL(@PathVariable String shortUrl) {
        urlShorteningService.deleteURL(shortUrl);

        return ResponseEntity.noContent().build();
    }
}
