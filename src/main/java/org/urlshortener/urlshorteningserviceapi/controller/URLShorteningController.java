package org.urlshortener.urlshorteningserviceapi.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.urlshortener.urlshorteningserviceapi.model.api.ApiResponse;
import org.urlshortener.urlshorteningserviceapi.model.url.URLEntityDTO;
import org.urlshortener.urlshorteningserviceapi.model.url.URLEntityRequest;
import org.urlshortener.urlshorteningserviceapi.service.URLShorteningService;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/shorten")
@CrossOrigin
public class URLShorteningController {
    private final URLShorteningService urlShorteningService;

    public URLShorteningController(URLShorteningService urlShorteningService) {
        this.urlShorteningService = urlShorteningService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<URLEntityDTO>> createShortUrl(@Valid @RequestBody URLEntityRequest request, HttpServletRequest servletRequest) {
        URLEntityDTO urlEntity = urlShorteningService.createShortURL(request);

        ApiResponse<URLEntityDTO> response = new ApiResponse<>();

        response.setMessage("Url created");
        response.setStatus(HttpStatus.CREATED);
        response.setCode(HttpStatus.CREATED.value());
        response.setTimestamp(LocalDateTime.now());
        response.setData(urlEntity);
        response.setPath(servletRequest.getRequestURI());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<ApiResponse<URLEntityDTO>> getOriginalUrl(@PathVariable String shortUrl, HttpServletRequest servletRequest) {
        URLEntityDTO originalURLEntity = urlShorteningService.getOriginalURL(shortUrl);

        ApiResponse<URLEntityDTO> response = new ApiResponse<>();

        response.setMessage("Success");
        response.setStatus(HttpStatus.FOUND);
        response.setCode(HttpStatus.FOUND.value());
        response.setTimestamp(LocalDateTime.now());
        response.setData(originalURLEntity);
        response.setPath(servletRequest.getRequestURI());

        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }
}
