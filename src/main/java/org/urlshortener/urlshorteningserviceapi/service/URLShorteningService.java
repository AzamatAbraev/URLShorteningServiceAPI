package org.urlshortener.urlshorteningserviceapi.service;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.urlshortener.urlshorteningserviceapi.model.url.URLEntity;
import org.urlshortener.urlshorteningserviceapi.model.url.URLEntityDTO;
import org.urlshortener.urlshorteningserviceapi.model.url.URLEntityRequest;
import org.urlshortener.urlshorteningserviceapi.model.url.URLEntityStatsDTO;
import org.urlshortener.urlshorteningserviceapi.repository.URLRepository;
import org.urlshortener.urlshorteningserviceapi.util.ShortURLGenerator;
import org.urlshortener.urlshorteningserviceapi.util.URLEntityMapper;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class URLShorteningService {
    private final URLRepository urlRepository;
    private final URLEntityMapper mapper;

    @Autowired
    public URLShorteningService(URLRepository urlRepository, URLEntityMapper mapper) {
        this.urlRepository = urlRepository;
        this.mapper = mapper;
    }

    @Scheduled(fixedRate = 3600000)
    public void deleteExpiredURLs() {
        List<URLEntity> expiredUrls = urlRepository.findByExpiresAtBefore(LocalDateTime.now());
        urlRepository.deleteAll(expiredUrls);
    }

    public List<URLEntityDTO> getAllURLs() {
        return urlRepository.findAll().stream().map(mapper::toDTO).toList();
    }

    public URLEntityDTO createShortURL(@Valid URLEntityRequest urlEntityRequest) {
        URLEntity urlEntity = new URLEntity();
        urlEntity.setOriginalUrl(urlEntityRequest.getOriginalUrl());
        urlEntity.setExpiresAt(urlEntityRequest.getExpiresAt());

        urlEntity = urlRepository.save(urlEntity);
        urlEntity.setShortUrl(ShortURLGenerator.encode(urlEntity.getId() + 1000));

        URLEntity savedEntity = urlRepository.save(urlEntity);
        return mapper.toDTO(savedEntity);
    }

    public URLEntityDTO getOriginalURL(String shortUrl) {
        Integer id = ShortURLGenerator.decode(shortUrl) - 1000;

        URLEntity entity = urlRepository.findById(id).orElseThrow(() -> new RuntimeException("URL is not found"));

        if (entity.getExpiresAt() != null && LocalDateTime.now().isAfter(entity.getExpiresAt())) {
            throw new ResponseStatusException(HttpStatus.GONE, "This short URL has expired.");
        }

        int accessCount = entity.getAccessCount();
        entity.setAccessCount(accessCount + 1);
        URLEntity savedEntity = urlRepository.save(entity);

        return mapper.toDTO(savedEntity);
    }

    public URLEntityDTO updateOriginalURL(String shortUrl, @Valid URLEntityRequest urlEntityRequest) {
        Integer id = ShortURLGenerator.decode(shortUrl) - 1000;
        URLEntity entity = urlRepository.findById(id).orElseThrow(() -> new RuntimeException("Url is not found"));
        entity.setOriginalUrl(urlEntityRequest.getOriginalUrl());

        URLEntity savedEntity = urlRepository.save(entity);

        return mapper.toDTO(savedEntity);
    }

    public void deleteURL(String shortUrl) {
        Integer id = ShortURLGenerator.decode(shortUrl) - 1000;

        if (!urlRepository.existsById(id)) {
            throw new RuntimeException("URL is not found");
        }

        urlRepository.deleteById(id);
    }

    public URLEntityStatsDTO getURLStats(String shortUrl) {
        Integer id = ShortURLGenerator.decode(shortUrl) - 1000;

        URLEntity entity = urlRepository.findById(id).orElseThrow(() -> new RuntimeException("URL is not found"));

        if (entity.getExpiresAt() != null && LocalDateTime.now().isAfter(entity.getExpiresAt())) {
            throw new ResponseStatusException(HttpStatus.GONE, "This short URL has expired.");
        }

        return mapper.toStatsDTO(entity);
    }


}
